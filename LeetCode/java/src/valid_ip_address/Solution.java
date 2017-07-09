package valid_ip_address;

/**
 * Created by Xiaotian on 7/8/17.
 */
public class Solution {
    // tag: str
    // time: O(n)
    // space: O(1)
    public String validIPAddress(String IP) {
        return isValidIPv4(IP) ? "IPv4" :
               isValidIPv6(IP) ? "IPv6" :
                                 "Neither";
    }

    private boolean isValidIPv4(String IP) {
        if (IP.length() < 7 || IP.length() > 15) return false;
        String[] tokens = IP.split("\\.", -1);
        if (tokens.length != 4) return false;
        for (String token : tokens) {
            if (!isValidIPv4Token(token)) return false;
        }
        return true;
    }

    private boolean isValidIPv4Token(String token) {
        if (token.length() == 0 || token.length() > 3) return false;
        if (token.length() > 1 && token.charAt(0) == '0') return false;
        int num = 0;
        for (int i = 0; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i))) return false;
            num = num * 10 + Integer.parseInt(String.valueOf(token.charAt(i)));
        }
        return 0 <= num && num <= 255;
    }

    private boolean isValidIPv6(String IP) {
        if (IP.length() < 15 || IP.length() > 39) return false;
        String[] tokens = IP.split("\\:", -1);
        if (tokens.length != 8) return false;
        for (String token : tokens) {
            if (!isValidIPv6Token(token)) return false;
        }
        return true;
    }

    private boolean isValidIPv6Token(String token) {
        if (token.length() == 0 || token.length() > 4) return false;
        for (int i = 0; i < token.length(); i++) {
            if (!isHexDigit(token.charAt(i))) return false;
        }
        return true;
    }

    private boolean isHexDigit(char c) {
        return (c >= '0' && c <= '9')
                || (c >= 'a' && c <= 'f')
                || (c >= 'A' && c <= 'F');
    }
}
