package token_bucket;

/**
 * Created by Xiaotian on 4/3/18.
 */
public class TokenBucket {
    // credit: http://blog.gssxgss.me/not-a-simple-problem-rate-limiting
    // credit: https://gist.github.com/petehunt/08c9fef5703e79ca26ceed845163dcdc

    // credit: https://medium.com/smyte/rate-limiter-df3408325846
    // The industry standard algorithm for rate limiting is called a token bucket,
    // sometimes called a “leaky bucket”.
    // Each bucket has a string key and initially contains the maximum number of tokens.
    // Every time an event occurs, you check if the bucket contains enough tokens
    // and reduce the number of tokens in the bucket by the requested amount.
    // After a period of time called the refill time, the number of tokens
    // in the bucket is increased by the refill amount. Over time, these refills
    // will fill up the bucket to the maximum number of tokens.

    String id;
    int capacity; // max tokens can be put inside bucket
    int token_cnt;
    int refill_token_cnt;
    int refill_interval; // unit: second
    long last_update;

    public TokenBucket(String id, int capacity, int refill_token_cnt, int refill_interval) {
        this.id = id;
        this.capacity = capacity;
        this.token_cnt = capacity;
        this.refill_token_cnt = refill_token_cnt;
        this.refill_interval = refill_interval;
        last_update = System.currentTimeMillis();
    }

    public boolean take() {
        // refill tokens into bucket
        long now = System.currentTimeMillis();
        int elapsed_seconds = (int) ((now - last_update) / 1000);
        int refill_cnt = elapsed_seconds / refill_interval;
        if (refill_cnt > 0) {
            token_cnt += refill_token_cnt * refill_cnt;
            token_cnt = Math.min(token_cnt, capacity);
            last_update = now;
        }
        System.out.println("tokens: " + token_cnt);

        if (token_cnt == 0) return false;
        token_cnt--;
        System.out.println("Taking one token...");
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket tokenBucket = new TokenBucket("rate_limiter", 5, 5, 1);
        long last_update = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            int elapsed_seconds = (int) ((System.currentTimeMillis() - last_update) / 1000);
            System.out.println("== " + elapsed_seconds + " ==");

            System.out.println(tokenBucket.take());

            Thread.sleep(100);
        }
    }

}
