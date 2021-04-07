package stack;

import java.util.Stack;

public class _71_SimplifyPath {

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        String token;
        int i = 1, start;

        while(i < path.length()) {
            start = i;
            while(i < path.length() && path.charAt(i) != '/')
                i++;

            token = path.substring(start, i);

            if(token.equals(".") || token.equals("/") || token.equals("")) {
                i++;
                continue;
            }

            if(token.equals("..")) {
                if(!stack.isEmpty()) stack.pop();
            } else {
                stack.push(token);
            }

            i++;
        }

        while(!stack.isEmpty()) {
            sb.insert(0, stack.pop());
            sb.insert(0, "/");
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }

    public static void main(String[] args) {
        _71_SimplifyPath sp = new _71_SimplifyPath();
        String[] inp = { "/a/./b/../c", "/home//a//f", "/../../", "/a/./b/../../c/"};

        for(String s : inp)
            System.out.println(sp.simplifyPath(s));
    }
}
