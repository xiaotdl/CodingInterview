# System Design Interviews: A step by step guide
https://www.educative.io/collection/page/5668639101419520/5649050225344512/5684049913839616

## Step 1: Requirements clarifications
Example questions for designing Twitter:
- What can users do? => feature scope
    => basic features
    - [write] post tweets
    - [read] read tweets (from timeline)
    - mark favorite tweets
    - reply tweets
    - follow other users
    => advanced features
    - search tweets
    - hot trending topics
    - push notification (new/important/reply tweets)
- Will tweets contain photos and videos? => file system design
- Are we focusing on backend only, or frontend as well? => backend|frontend scope

## Step 2: System interface definition
Define what APIs are expected from the system.
Some examples for our Twitter-like service would be:
```
postTweet(user_id, tweet_data, tweet_location, user_location, timestamp, ...)
generateTimeline(user_id, current_time, user_location, ...)
markTweetFavorite(user_id, tweet_id, timestamp, ...)
```

## Step 3: Back-of-the-envelope estimation
It’s always a good idea to estimate the scale of the system you’re going to design.
This would also help later when you’ll be focusing on **scaling**, **partitioning**, **load balancing** and **caching**.

- What **scale** is expected from the system?
    - number of new tweets(write)
    - number of tweet views(tweet read)
    - how many timeline generations per sec(timeline read), etc.
- How much **storage** would we need?
    We’ll have different numbers if users can have photos and videos in their tweets.
- What **network bandwidth** usage are we expecting(qps)?
    This would be crucial in deciding how would we manage traffic and balance load between servers.

## Step 4: Defining data model
Defining the data model early will clarify how data will flow among different components of the system.
Later, it will guide towards data partitioning and management.
Candidate should be able to identify various entities of the system, how they will interact with each other
and different aspect of data management like storage, transportation, encryption, etc.

Here are some entities for our Twitter-like service:
- User: UserID, Name, Email, DoB, CreationData, LastLogin, etc.
- Tweet: TweetID, Content, TweetLocation, NumberOfLikes, TimeStamp, etc.
- UserFollows: UserdID1, UserID2
- FavoriteTweets: UserID, TweetID, TimeStamp

Which database system should we use? Would NoSQL like Cassandra best fits our needs, or we should use MySQL-like solution.
What kind of block storage should we use to store photos and videos?

## Step 5: High-level design
Draw a _block diagram_ with 5-6 boxes representing core components of your system.
You should identify enough components that are needed to solve the actual problem from end-to-end.

For Twitter, at a high level,
- we would need multiple _application servers_ to serve all the read/write requests,
- with _load balancers_ in front of them for traffic distributions.
- If we’re assuming that we’ll have a lot more read traffic (as compared to write), we can decide to have separate servers for handling these scenarios.
- On the backend, we need an efficient _database_ that can store all the tweets and can support a huge number of reads.
- We would also need a _distributed file storage system_ for storing photos and videos.



User1  --> Upload Image Request <--> Image Metadata
                                 \/
                                 /\
User2 <--> Download Image Request <--> Image Storage

## Step 6: Detailed design
Dig deeper into 2-3 components; interviewers feedback should always guide you towards which parts of the system she wants you to explain further.
You should be able to provide different approaches, their **pros and cons**, and why would you choose one?
The only thing important is to consider **tradeoffs** between different options while keeping system constraints in mind.

- *[DB]* Since we’ll be storing a huge amount of data, how should we _partition our data_ to distribute it to _multiple databases_? Should we try to store all the data of a user on the same database? What issue can it cause?
- *[push/pull mechanism]* How would we _handle hot users_, who tweet a lot or follow lots of people?
- *[Data Model]* Since user’s timeline will contain most recent (and relevant) tweets, should we try to store our data in such a way that is optimized to scan latest tweets?
- *[Cache]* How much and at which layer should we introduce _cache_ to speed things up?
- *[LB]* What components need better _load balancing_?

## Step 7: Identifying and resolving bottlenecks
Try to discuss as many _bottlenecks_ as possible and different approaches to mitigate them.

- Is there any *single point of failure* in our system? What are we doing to mitigate it?
- Do we’ve enough *replicas of the data* so that if we lose a few servers, we can still serve our users?
- Similarly, do we’ve enough *copies of different services* running, such that a few failures will not cause total system shutdown?
- How are we *monitoring the performance* of our service? Do we get alerts whenever critical components fail or their performance degrade?







