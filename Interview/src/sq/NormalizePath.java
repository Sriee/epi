package sq;

import java.util.Deque;
import java.util.LinkedList;

public class NormalizePath {

    /**
     * Leet code problem. Solution -> Accepted
     * 
     * @param path absolute or relative path in unix style format
     * @return absolute path
     */
    public String simplifyPath(String path) {

        StringBuilder sb = new StringBuilder();
        Deque<String> stack = new LinkedList<>();

        if (path.startsWith("/"))
            stack.addFirst("/");

        String prev = "";
        String[] symbols = path.split("/");

        for (String token : symbols) {
            if (token.isEmpty())
                continue;

            if (token.equals("."))
                continue;
            else if (token.equals("..")) {
                if (stack.isEmpty())
                    stack.addFirst("/");
                else
                    stack.removeFirst();
            } else
                stack.addFirst(token);
        }

        while (!stack.isEmpty()) {
            String temp = stack.removeLast();
            if (!prev.equals("/")) {
                sb.append("/");
            }

            sb.append(temp);
            prev = temp;
        }

        if (sb.length() == 0) {
            return "/";
        } else {
            if (sb.charAt(1) == '/')
                sb.deleteCharAt(1);
            return sb.toString();
        }
    }
}
