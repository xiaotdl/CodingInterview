# Designing a URL Shortening service like TinyURL
https://www.educative.io/collection/page/5668639101419520/5649050225344512/5668600916475904

Let's design a URL shortening service like TinyURL. This service will provide short aliases redirecting to long URLs.

Similar services: bit.ly, goo.gl, 2020.fm etc.
Difficulty Level: Easy


## 1. Why do we need URL shortening?
URL shortening is used to create shorter aliases for long URLs.
URL shortening is used for optimizing links across devices, tracking individual links to analyze audience and campaign performance, and hiding affiliated original URLs, etc.


## 2. Requirements and Goals of the System
Ask questions to find the exact scope of the system that the interviewer has in mind.

### Functional Requirements:
- [write] Given a URL, our service should generate a shorter and unique alias of it.
- [write custom] Users should optionally be able to pick a custom alias for their URL.
- [read] When users access a shorter URL, our service should redirect them to the original link.
- [record lifespan] Links will *expire* after a specific timespan automatically; users should also be able to specify expiration time.

### Non-Functional Requirements:
- [HA] The system should be highly available. This is required because if our service is down, all the URL redirections will start failing.
- [RealTime] URL redirection should happen in real-time with minimum latency.
- [Random] Shortened links should not be guessable (not predictable).

### Extended Requirements:
- [Stats] Analytics, e.g., how many times a redirection happened?
- [API] Our service should also be accessible through REST APIs by other services.


## 3. Capacity Estimation and Constraints
Our system would be read-heavy; there would be lots of redirection requests compared to new URL shortenings.

### Traffic/Queries Per Second (QPS: write, read) estimates
Assumptions:
- 500 million new URL writes/month
- 100:1 read to write ratio
- 500 * 100 = 50 billion redirection reads/month

*[write/second]* New URLs shortenings per second:
500 million / (30 days * 24 hours * 3600 seconds) ~= 190 write requests/s

*[read/second]* URLs redirections per second:
50 billion / (30 days * 24 hours * 3600 seconds) ~= 19K read requests/s

### Bandwidth estimates
*[write rate]* Upload:
190 * 500 bytes = 95 KB/s

*[read rate]* Download:
19K * 500 bytes ~= 9.5 MB/s

### Storage/Disk Space estimates
Assumptions:
- 500 million new URLs/month
- 500 bytes/URL # just a ballpark, we will dig into it later
- 5 years expiration time

*[disk space]* Daily data:
500 million / 30 * 500 bytes ~= 8.3 GB/day

*[disk space]* Total data:
8.3 GB/day * 5 * 365 days  = 15 TB

*[records]* Total records/files:
500 million * 5 years * 12 months = 30 billion

*[disk space]* Total keys:
==> encoding:
Using base64 encoding, 6 chars, 64^6 = 68.7 billion > 30 billion
30 billion * 6 bytes ~= 180 GB <<< 36 TB
180GB is negligible compared to 36TB.

### Memory estimates
Follow 80-20 rule, cache 20% hot URLs.

*[read/day]*
19K/s * 24 * 3600s = 1.6 billion requests/day.

*[cache data]* Daily:
20% * 1.6 billion * 500 bytes ~= 160GB

### High level estimates
Assuming 500 million new URLs per month and 100:1 read:write ratio, following is the summary of the high level estimates for our service:

[write req/s]  New URLs                190/s
[read req/s]   URL redirections        19K/s
[write data/s] Incoming data           95KB/s
[read data/s]  Outgoing data           9.5MB/s
[disk space]   Storage for 5 years     15TB
[cache]        Memory for cache        160GB


## 4. System APIs

```
creatURL(api_dev_key, original_url, custom_alias=None user_name=None, expire_date=None)

Parameters:
api_dev_key (string): The API developer key of a registered account. This will be used to, among other things, throttle users based on their allocated quota.
original_url (string)
custom_alias (string): Optional - custom key for the URL.
user_name (string): Optional - user name to be used in encoding.
expire_date (string): Optional - expiration date for the shortened URL.

Returns:
shortened URL (string)
```

```
deleteURL(api_dev_key, url_key)

Parameters:
api_dev_key (string)
url_key (string): shortened URL.

Returns:
success (boolean)
```


## 5. Database Design
Defining the _DB schema_ in the early stages of the interview would help to understand the data flow among various components and later would guide towards the data partitioning.

A few observations about the nature of the data to be stored:
- billions of records
- each record is small (less than 1 kb)
- no relationships between records
- read heavy

### Database Schema
We would need two tables:
1) one for storing information about the URL mappings
2) the other for users’ data.

URL
--+--
PK| Hash: varchar(16)
FK| UserID: int
    OriginalURL: varchar(512)
    CreationDate: datetime
    ExpirationDate: datetime

User
--+--
PK| UserID: int
    Name: varchar(20)
    Email: varchar(32)
    DOB: datetime
    CreationDate: datetime
    LastLogin: datetime

What kind of database should we use?
billion records + no relationships between records => NoSQL key-value store, e.g. Dynamo, Cassandra

 If we choose NoSQL, we cannot store UserID in the URL table (as there are no foreign keys in NoSQL), for that we would need a third table which will store the mapping between URL and the user.


## 6. Basic System Design and Algorithm
Algorithms to generate a short and unique key for a given URL.

### Encoding actual URL
We can compute a unique hash (e.g., MD5 or SHA256, etc.) of the given URL. The hash can then be encoded for displaying.
This encoding could be:
base36 ([a-z ,0-9])
base62 ([a-z, A-Z, 0-9])
base64 ([a-z, A-Z, 0-9, ., -])

A reasonable question would be: what should be the length of the short key? 6, 8 or 10 characters?
Using base64 encoding, 6 chars, 64^6 = 68.7 billion possible keys > 30 billion total files we estimated for 5 years

We will use MD5 algo to produce a 128 bit hash value(32 hex nums) and only keep the first 6 chars.

Potential issues:
1) diff URLs have same key
2) duplicated URL entry, e.g. plain url vs. percentage encoded url vs. has url query params

### Generating keys offline
We can have a _standalone Key Generation Service (KGS)_ that generates random six letter strings beforehand and stores them in a database (let’s call it *key-DB*).

Whenever we want to shorten a URL, we will just take one of the already generated keys and use it. This approach will make things quite simple and fast since we will not be encoding the URL or worrying about duplications or collisions. KGS will make sure all the keys inserted in key-DB are unique.

Can concurrency cause problems?
Need an atomic operation to mark a key is used.
Two tables, one for all used and one for all not used.
Preload some keys into memory to reduce latency. It's ok to lost keys as we have way more.
Use lock assign one key to a server.

What would be the key-DB size?
6 chars/key * 68.7 billion keys = 412 GB

Isn’t KGS the single point of failure?
Yes, it is. To solve this, we can have a standby replica of KGS, and whenever the primary server dies, it can take over to generate and provide keys.

Can each app server cache some keys from key-DB?
Yes, this can surely speed things up. Although in this case, if the application server dies before consuming all the keys, we will end up losing those keys. This could be acceptable since we have 68B unique six letters keys

How would we perform a key lookup?
We can look up the key in our database or key-value store to get the full URL.  If it’s present, issue a “HTTP 302 Redirect” status back to the browser, passing the stored URL in the “Location” field of the request. If that key is not present in our system, issue a “HTTP 404 Not Found” status, or redirect the user back to the homepage.

Clients <--> Application Server <--> KGS
                    |                 |
                    DB              Key-DB


## 7. Data Partitioning and Replication
To scale out our DB, we need to partition it so that it can store information about billions of URL.
We need to come up with a *partitioning scheme* that would divide and store our data to different DB servers.

### Range Based Partitioning
Store URLs in separate partitions based on the first letter of the URL or the hash key.

The main problem with this approach is that it can lead to unbalanced servers, for instance; if we decide to put all URLs starting with letter ‘E’ into a DB partition, but later we realize that we have too many URLs that start with letter ‘E’, which we can’t fit into one DB partition.

### Hash Based Partitioning
Consistent Hashing to evenly distribute URLs into different partitions.


## 8. Cache
We can cache URLs that are frequently accessed. We can use some off-the-shelf solution like _Memcache_ or _Redis_, that can store full URLs with their respective hashes. The application servers, before hitting backend storage, can quickly check if the cache has desired URL.

Cache size?
We can start with *20% of daily traffic* and based on clients’ usage pattern we can adjust how many cache servers we need.

Cache eviction policy?
Least Recently Used (LRU) can be a reasonable policy for our system. Under this policy, we discard the least recently used URL first. We can use a Linked Hash Map or a similar data structure to store our URLs and Hashes, which will also keep track of which URLs are accessed recently.

How can each cache replica be updated?
Whenever there is a cache miss, our servers would be hitting backend database. Whenever this happens, we can update the cache and pass the new entry to all the cache replicas. Each replica can update their cache by adding the new entry. If a replica already has that entry, it can simply ignore it.

Client -- access shorten URL --> Server -- find original URL --> Cache
    \       <-- URL not found --        \   <-- URL not found --
     \                         /         \      <-- URL found --
<check isExpired && check isUserPermitted>\    ---------------->
                                           \  /   update
                                            DB

## 9. Load Balancer (LB)
We can add Load balancing layer at three places in our system:
1) Between Clients and Application servers
2) Between Application Servers and database servers
3) Between Application Servers and Cache servers

Load Balancing algo:
1) Round Robin LB
2) Server load based ratio


## 10. Purging or DB cleanup
lazy cleanup:
- Whenever a user tries to access an expired link, we can delete the link and return an error to the user.
- A separate Cleanup service can run periodically to remove expired links from our storage and cache. This service should be very lightweight and can be scheduled to run only when the user traffic is expected to be low.
- We can have a default expiration time for each link, e.g., two years.
- After removing an expired link, we can put the key back in the key-DB to be reused.

### Detailed component design for URL shortening
Clients --> LBs --> App Server --> LBs --> Cache Servers
                        |              \     |
                        |               ---> DBs
                        |                    |
                    Key Generation Service   |
                        |                    |
    Key-DB standby -- Key-DB <-- Cleanup Service


## 11. Telemetry
How many times a short URL has been used
What were user locations, etc.?
How would we store these statistics?


## 12. Security and Permissions
Can users create private URLs or allow a particular set of users to access a URL?
We can store permission level (public/private) with each URL in the database.
We can also create a separate table to store UserIDs that have permission to see a specific URL.


