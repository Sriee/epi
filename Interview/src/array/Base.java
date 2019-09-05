package array;

public class Base{
	public static void main(String[] args){
        long temp = Integer.toUnsignedLong(-1);
	    if (temp < 0) {
            temp = temp * -1;
            temp = temp ^ 2147483647;
            temp = temp + 1;
        }
        System.out.println(temp);
		long num = temp;
		char[] ch = {'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
		while(num != 0){
			int r = (int)num % 16;
			if(r > 9)
				sb.append(ch[r - 10]);
			else
				sb.append(r);
			num /= 16;
        }

        System.out.println(sb.reverse().toString());
	}
}
