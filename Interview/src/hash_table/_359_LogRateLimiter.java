package hash_table;

import java.util.*;

public class _359_LogRateLimiter {

    Map<String, Integer> haveSeen;

    public _359_LogRateLimiter() {
        haveSeen = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean res = false;
        if (haveSeen.containsKey(message)) {
            Integer val = haveSeen.get(message);

            if (timestamp >= val) {
                haveSeen.put(message, timestamp + 10);
                res = true;
            }
        } else {
            haveSeen.put(message, timestamp + 10);
            res = true;
        }
        return res;
    }

    public static void main(String[] args) {
        _359_LogRateLimiter lr = new _359_LogRateLimiter();
        // return true, next allowed timestamp for "foo" is 1 + 10 = 11
        System.out.println(lr.shouldPrintMessage(1, "foo"));

        // return true, next allowed timestamp for "bar" is 2 + 10 = 12
        System.out.println(lr.shouldPrintMessage(2, "bar"));
        System.out.println(lr.shouldPrintMessage(3, "foo"));  // 3 < 11, return false
        System.out.println(lr.shouldPrintMessage(8, "bar"));  // 8 < 12, return false
        System.out.println(lr.shouldPrintMessage(10, "foo")); // 10 < 11, return false

        // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21
        System.out.println(lr.shouldPrintMessage(11, "foo"));
    }
}
