package encode_and_decode_tinyurl;

import java.util.*;

/**
 * Created by Xiaotian on 6/14/17.
 */
// tag: hash
// time: O(1)
// space: O(n)
public class Codec {
    Map<String, String> long2short = new HashMap<>();
    Map<String, String> short2long = new HashMap<>();
    static String HOST = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (short2long.containsKey(longUrl)) return HOST + long2short.get(longUrl);
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String shortUrl = null;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int j = (int) (Math.random() * charSet.length());
                sb.append(charSet.charAt(j));
            }
            shortUrl = sb.toString();
        }
        while (long2short.containsKey(shortUrl));
        long2short.put(longUrl, shortUrl);
        short2long.put(shortUrl, longUrl);
        return HOST + shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return short2long.get(shortUrl.replace(HOST, ""));
    }
}
