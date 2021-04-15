package string;

public class _151_ReverseString {

    public String reverseWords(String s) {
        s = s.trim();
        String[] arr = s.split(" ");
        StringBuilder res = new StringBuilder();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i].trim().length() == 0)
                continue;

            res.append(arr[i]);
            res.append(" ");
        }

        res.deleteCharAt(res.length() - 1);

        return res.toString();
    }

    public static void main(String[] args) {
        _151_ReverseString r = new _151_ReverseString();
        System.out.println(r.reverseWords("  the  r yu ai ug ads  fgu ew   h  j f"));
        System.out.println(r.reverseWords("  bal  "));
    }
}
