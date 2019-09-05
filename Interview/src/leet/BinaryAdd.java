package leet;

public class BinaryAdd {

	private static String add(String a, String b) {
		if(a == null || a.length() == 0) return b;
		if(b == null || b.length() == 0) return a;
		
		int l = 0, s = 0; 
		String large, small, carry = "0";
		StringBuilder sb = new StringBuilder();
		
		if(a.length() > b.length()) {
			large = a;
			small = b;
		} else {
			large = b;
			small = a;
		}

		l = large.length(); s = small.length();
		System.out.println("Large " + large + " Small " + small);
		while(s >= 0) {
			if(carry == "0") {
				System.out.println(large.substring(s, s + 1) + " " + small.substring(s, s + 1));
				if(large.substring(s, s + 1) == "1" && small.substring(s, s + 1) == "1") {
					sb.insert(0, "0");
					carry = "1";
				} else if(large.substring(s, s + 1) == "0" && small.substring(s, s + 1) == "0") {
					sb.insert(0, "0");
					carry = "0";
				} else {
					sb.insert(0, "1");
					carry = "0";
				}
			} else if (carry == "1"){
				if(large.substring(s, s + 1) == "1" && small.substring(s, s + 1) == "1") {
					sb.insert(0, "0");
					carry = "1";
				} else if(large.substring(s, s + 1) == "0" && small.substring(s, s + 1) == "0") {
					sb.insert(0, "0");
					carry = "0";
				} else {
					sb.insert(0, "1");
					carry = "0";
				}
			}
			s--; l--;
		}
		
		while(l >= 0) {
			if(carry == "1" && large.substring(l, l + 1) == "1") {
				sb.insert(0, "0");
				carry = "1";
			} else {
				sb.insert(0, large.substring(l, l + 1));
				carry = "0";
			}
			l--;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		 System.out.println(add("11", "1"));
	}

}
