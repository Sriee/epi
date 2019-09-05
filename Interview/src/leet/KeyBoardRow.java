package leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyBoardRow {
	
	private static Set<Character> stringToCharSet(String inp){
		Set<Character> load = new HashSet<Character>();
		inp = inp.toUpperCase();
		for(int i = 0; i < inp.length(); i++) load.add(inp.charAt(i));
		return load;
	}
	
	private static String[] findWords(String[] words) {
		Set<Character> top = stringToCharSet("QWERTYUIOP");
		Set<Character> middle = stringToCharSet("ASDFGHJKL");
		Set<Character> bottom = stringToCharSet("ZXCVBNM");
		
		List<String> result = new ArrayList<String>();
		Set<Character> dest = null;
		
		for(int index = 0; index < words.length; index++) {
			dest = stringToCharSet(words[index]);
			if(top.containsAll(dest)) {
				result.add(words[index]);
			} else if(middle.containsAll(dest)) {
				result.add(words[index]);
			} else if(bottom.containsAll(dest)) {
				result.add(words[index]);
			}
		}
		
		return result.toArray(new String[0]);
	}
	
	public static void main(String[] args) {
		String[] words= {"asdfghjkl", "dad", "was"};
		String[] results = findWords(words);
		
		for(int i = 0; i < results.length; i++)
			System.out.println(results[i]);
	}
}