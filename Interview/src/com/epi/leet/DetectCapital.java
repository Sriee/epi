package com.epi.leet;

public class DetectCapital {

    private static boolean detectCapital(String word){
        // Optimized Approach
	    if(word == null) return true;
	    int count = 0;
	    for(char ch : word.toCharArray()){
	        if('Z' - ch >= 0) count++;
	    }
        System.out.println(count);
        return (count == 0 || count == word.length() || (count == 1 && ('Z' - word.charAt(0) > 0)));
    }

    private static boolean detectCapitalUse(String word){
        // Regular approach
        if(word == null) return true;
        if(word.length() == 0) return true;

        word = word.replaceAll(" ", "");

        if(word.length() == 0) return false;
        if(word.length() == 1) return true;

        boolean low = Character.isLowerCase(word.charAt(0));
        boolean title = !low && Character.isLowerCase(word.charAt(1));

        for(int i = 1; i < word.length(); i++){
            if(low && Character.isUpperCase(word.charAt(i))) return false;
            else if(title && Character.isUpperCase(word.charAt(i))) return false;
            else if(!low && !title && Character.isLowerCase(word.charAt(i))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Both the methods will work
        System.out.println(detectCapital(null));
        System.out.println(detectCapital("G"));
        System.out.println(detectCapitalUse("USA"));
        System.out.println(detectCapital("India"));
        System.out.println(detectCapital("uZfa"));
    }
}
