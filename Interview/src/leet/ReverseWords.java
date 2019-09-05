package leet;

public class ReverseWords {

	private static String reverseWords(String sentence) {
		if(sentence == null) return sentence;
		if(sentence.isEmpty()) return sentence;
		
		StringBuilder result = new StringBuilder();
		int index = 0, wordCount = 0;
		
		for(int i = 0; i < sentence.length(); i++) {
			char ch = sentence.charAt(i);
			if(ch == ' ') { 
				result.append(" ");
				index += (wordCount + 1);
				wordCount = 0;
			} else {
				result.insert(index, ch);
				wordCount++;
			}
		}
		return result.toString();
	}
	
	private static String reverseSentence(String sentence) {
		String[] sen = sentence.split(" ");
		StringBuilder result = new StringBuilder(); 
		for(String word : sen) {
			result.append(new StringBuilder(word).reverse());
			result.append(" ");
		}
		result.setLength(result.length() - 1);
		return result.toString();
	}
	
	public static void main(String[] args) {
		String inp = "Lets see whether it works";
		String result = reverseWords(inp);
		System.out.println(result);
		result = reverseSentence(inp);
		System.out.println(result);
		
		System.out.println(new StringBuilder("hello").reverse().toString());
	}

}
