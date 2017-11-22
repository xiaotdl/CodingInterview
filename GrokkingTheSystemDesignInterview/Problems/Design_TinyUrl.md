# Designing a URL Shortening service like TinyURL
https://www.educative.io/collection/page/5668639101419520/5649050225344512/5668600916475904

## 1. Why do we need URL shortening?
URL shortening is used to create shorter aliases for long URLs.
URL shortening is used for optimizing links across devices, tracking individual links to analyze audience and campaign performance, and hiding affiliated original URLs, etc.


## 2. Requirements and Goals of the System
Ask questions to find the exact scope of the system that the interviewer has in mind.

### Functional Requirements:
- [write] Given a URL, our service should generate a shorter and unique alias of it.
- [read] When users access a shorter URL, our service should redirect them to the original link.
- [write] Users should optionally be able to pick a custom alias for their URL.
- Links will *expire* after a specific timespan automatically; users should also be able to specify expiration time.

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
- 500 million new URLs/month
- 100:1 read to write ratio
- 500 * 100 = 50 billion redirections/month

*[write/second]* New URLs shortenings per second:
500 million / (30 days * 24 hours * 3600 seconds) ~= 190 requests/s

*[read/second]* URLs redirections per second:
50 billion / (30 days * 24 hours * 3600 seconds) ~= 19K requests/s

### Storage/Disk Space estimates
Assumptions:
- 500 million new URLs/month;
- 5 years expiration time;
- 500 bytes/URL # just a ballpark, we will dig into it later

Total objects/files:
500 million * 5 years * 12 months = 30 billion

*[disk space]* Total disk storage:
30 billion * 500 bytes = 15 TB

### Bandwidth estimates
Upload/write rate:
190 * 500 bytes = 95 KB/s

Download/read rate:
19K * 500 bytes ~= 9.5 MB/s

### Memory estimates
Follow 80-20 rule, cache 20% hot URLs.
Cache per day, 19K * 24 * 3600 = 1.6 billion requests/day.

To cache 20% of these requests, we would need 160GB of memory.
0.2 * 1.6 billion * 500 bytes ~= 160GB

### High level estimates
Assuming 500 million new URLs per month and 100:1 read:write ratio, following is the summary of the high level estimates for our service:

[write req/s]  New URLs                190/s
[read req/s]   URL redirections        19K/s
[write data/s] Incoming data           95KB/s
[read data/s]  Outgoing data           9.5MB/s
[disk space]   Storage for 5 years     15TB
[cache]        Memory for cache        160GB


## 4. System APIs

