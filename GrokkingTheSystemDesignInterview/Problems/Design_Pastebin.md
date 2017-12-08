# Designing Pastebin
https://www.educative.io/collection/page/5668639101419520/5649050225344512/5653164804014080

Let's design a Pastebin like web service, where users can store plain text.
Users of the service will enter a piece of text and get a randomly generated URL to access it.

Similar Services: pasted.co, hastebin.com, chopapp.com
Difficulty Level: Easy


## 1. What is Pastebin?
Pastebin like services enable users to store plain text or images over the network (typically the Internet) and generate unique URLs to access the uploaded data.
Such services are also used to share data over the network quickly, as users would just need to pass the URL to let other users see it.


## 2. Requirements and Goals of the System

### Functional Requirements
- [write] Users should be able to upload or “paste” their data and get a unique URL.
- [write limit] Users will only be able to upload text.
- [write custom] Users should optionally be able to pick a custom alias for their paste.
- [read] Users can access their data through the given URL.
- [record lifespan] Data and links will expire after a specific timespan automatically; users should also be able to specify expiration time.

### Non-Functional Requirements
[Data replica] The system should be highly reliable, any data uploaded should not be lost.
[HA] The system should be highly available. This is required because if our service is down, users will not be able to access their Pastes.
[RealTime] Users should be able to access their Pastes in real-time with minimum latency.
[Random] Paste links should not be guessable (not predictable).

### Extended Requirements
[Stats] Analytics, e.g., how many times a redirection happened?
[API] Our service should also be accessible through REST APIs by other services.


## 3. Some Design Considerations
Pastebin shares some requirements with URL Shortening service, but there are some additional design considerations we should keep in mind.

What should be the limit on the amount of text user can paste at a time?
We can limit users not to have Pastes bigger than 10MB to stop the abuse of the service.

Should we impose size limits on custom URLs?
It is reasonable (and often desirable) to impose a size limit on custom URLs, so that we have a consistent URL database.


## 4. Capacity Estimation and Constraints
Our services would be read heavy; there will be more read requests compared to new Pastes creation.

### Traffic estimates
Assumptions:
- 1 million new paste writes/day
- 5:1 write to read ratio
- 5 million reads/day

*[write/second]* New Pastes per second:
1M / (24 hours * 3600 seconds) ~= 12 pastes/sec

*[read/second]* Paste reads per second:
5M / (24 hours * 3600 seconds) ~= 58 reads/sec

### Bandwidth estimates
*[write rate]* Upload:
12 pastes/sec * 10 KB/paste = 120 KB/s

*[read rate]* Download:
As for read request, we expect 58 requests per second. Therefore, total data egress (sent to users) will be 0.6 MB/s.
58 reads/sec * 10 KB/paste = 580 KB/s

### Storage estimates
Assumptions:
- 1 million new paste writes/day
- average paste data size: 10KB/file
- 10 years expiration time

*[disk space]* Daily data:
1M * 10KB = 10 GB/day

*[disk space]* Total data:
10 GB/day * 10 * 365 days = 36 TB

*[records]* Total records/files:
1M  * 10 * 365 days = 3.6 billion

*[disk space]* Total keys:
==> encoding:
Using base64 encoding, 6 chars, 64^6 = 68.7 billion > 3.6 billion
3.6 billion * 6 bytes ~= 22 GB <<< 36 TB
22GB is negligible compared to 36TB.

To keep some margin, we will assume a 70% capacity model (meaning we don’t want to use more than 70% of our total storage capacity at any point), which raises our storage needs up to 36/70% = 51TB.

### Memory estimates
We can cache some of the hot pastes that are frequently accessed. Following 80-20 rule, meaning 20% of hot pastes generate 80% of traffic, we would like to cache these 20% pastes

Since we have 5M read requests per day, to cache 20% of these requests, we would need:

0.2 * 5M * 10KB ~= 10 GB

Follow 80-20 rule, cache 20% hot URLs.

*[read/day]*
58/s * 24 * 3600s = 5 million requests/day.

*[cache data]* Daily:
20% * 5 million * 10 KB ~= 10 GB

### High level estimates
Assuming 1 million new URLs per day and 5:1 read:write ratio, following is the summary of the high level estimates for our service:

[write req/s]  New pastes              12/s
[read req/s]   paste access            58/s
[write data/s] Incoming data           120KB/s
[read data/s]  Outgoing data           580KB/s
[disk space]   Storage for 10 years    36TB
[cache]        Memory for cache        10GB


## 5. System APIs

```
addPaste(api_dev_key, paste_data, custom_url=None user_name=None, paste_name=None, expire_date=None)

Parameters:
api_dev_key (string): The API developer key of a registered account. This will be used to, among other things, throttle users based on their allocated quota.
paste_data (string): Textual data of the paste.
custom_url (string): Optional - custom URL.
user_name (string): Optional - user name to be used to generate URL.
paste_name (string): Optional - name of the paste.
expire_date (string): Optional - expiration date for the paste.

Returns:
the URL through which the paste can be accessed (string)
```

```
getPaste(api_dev_key, api_paste_key)

Parameters:
api_dev_key (string)
api_paste_key (string)

Returns:
the textual data of the paste (string)
```
```
deletePaste(api_dev_key, api_paste_key)

Parameters:
api_dev_key (string)
api_paste_key (string)

Returns:
success (boolean)
```


## 6. Database Design
A few observations about nature of the data we are going to store:
- billions of records
- metadata record is small (less than 100 bytes)
- paste object is medium size (it can be a few MB)
- no relationships between records, except if we want to store which user created what Paste.
- read heavy

### Database Schema
We would need two tables:
1) one for storing information about the Pastes
2) the other for users’ data.

Paste
--+--
PK| Hash: varchar(16)
FK| UserID: int
    OriginalURL: varchar(512)
    ContentPath: varchar(512)
    CreationDate: datetime
    ExpirationDate: datetime

User
--+--
PK| Name: varchar(20)
    Email: varchar(32)
    DOB: datetime
    CreationDate: datetime
    LastLogin: datetime


## 7. High Level Design

### High level design for Pastebin
Clients --> Application Server <--> Metadata DB
                               <--> Block Storage


## 8. Component Design

### a. Application layer
Our application layer will process all incoming and outgoing requests.
The application servers will be talking to the backend data store components to serve the requests.

How to handle a write request?
Upon receiving a write paste request, save the paste data along with a unique key.
Use KGS to generate unique key.

How to handle a paste read request?
Upon receiving a read paste request, the application service layer contacts the datastore. The datastore searches for the key, and if it is found, returns the paste’s contents. Otherwise, an error code is returned.

### b. Datastore layer
We can divide our datastore layer into two:
1) Metadata database
    We can use a relational database like MySQL or a Distributed Key-Value store like Dynamo or Cassandra.
2) Block storage
    We can store our contents in a block storage that could be a distributed file storage or an SQL-like database. Whenever we feel like hitting our full capacity on content storage, we can easily increase it by adding more servers.
