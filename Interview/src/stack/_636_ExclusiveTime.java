package stack;

import java.util.*;

public class _636_ExclusiveTime {
    private class Log {
        public int id, time, childTime;
        public boolean isStart;

        public Log(String logStatement) {
            int i = 0;

            // Optimized version of extracting tokens from log statement. Reduces the solution time from 10 ms to 5 ms
            while (logStatement.charAt(i) != ':') {
                id = id * 10 + logStatement.charAt(i++) - '0';
            }

            if (logStatement.charAt(++i) == 's') {
                isStart = true;
                i += 6;
            } else {
                i += 4;
            }

            while (i < logStatement.length()) {
                time = time * 10 + logStatement.charAt(i++) - '0';
            }
        }
    }

    /**
     * Each statement has function id, start/end characters, and time.
     * The start/end characters are constant.
     * How many digits does the function id has? n
     * How many digits does the timestamp has? t where 0 <= t <= 10^9
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Deque<Log> stack = new ArrayDeque<>();

        for (String logStatement : logs) {
            Log log = new Log(logStatement);
            if (log.isStart)
                stack.push(log);
            else {
                Log startLog = stack.pop();
                result[log.id] += (log.time - startLog.time + 1) - startLog.childTime;

                if (!stack.isEmpty()) {
                    stack.peek().childTime += log.time - startLog.time + 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        _636_ExclusiveTime et = new _636_ExclusiveTime();
        List<List<String>> events = Arrays.asList(
                Arrays.asList("0:start:0", "1:start:2", "1:end:3", "2:start:4", "2:end:7", "0:end:8"),
                Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"),
                Arrays.asList("0:start:0", "1:start:5", "1:end:6", "0:end:7"),
                Arrays.asList("0:start:0", "1:start:5", "2:start:8", "3:start:12", "4:start:15", "5:start:19", "5:end:22", "4:end:24", "3:end:27", "2:end:32", "1:end:35", "0:end:36"),
                Arrays.asList("0:start:0", "1:start:3", "1:end:6", "0:end:10"),
                Arrays.asList("0:start:0", "1:start:2", "2:start:3", "3:start:5", "3:end:6", "2:end:7", "1:end:9", "0:end:10")
                );

        int[] n = {3, 2, 2, 6, 2, 4};

        for (int i = 0; i < n.length; i++) {
            System.out.println(i + 1 + ".\tn = " + n[i]);
            System.out.println("\tevents = " + events.get(i));
            System.out.println("\tOutput: " + Arrays.toString(et.exclusiveTime(n[i], events.get(i))));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
