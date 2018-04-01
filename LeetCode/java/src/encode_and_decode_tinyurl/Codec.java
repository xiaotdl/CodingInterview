package encode_and_decode_tinyurl;

import java.util.*;

/**
 * Created by Xiaotian on 6/14/17.
 */
public class Codec {
    // tag: hash, base62
    // time: O(1)
    // space: O(n)
    Map<String, String> l2s = new HashMap<>();
    Map<String, String> s2l = new HashMap<>();
    public final static int SHORT_URL_LEN = 6;

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (l2s.containsKey(longUrl)) return l2s.get(longUrl);

        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // base62
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        String shortUrl = "";
        // find a unique tiny url
        while (true) {
            int i = rand.nextInt(charSet.length());
            sb.append(charSet.charAt(i));
            if (sb.length() == SHORT_URL_LEN) {
                shortUrl = sb.toString();
                if (!s2l.containsKey(shortUrl)) break;
                sb = new StringBuilder();
            }
        }
        l2s.put(longUrl, shortUrl);
        s2l.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return s2l.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
