package hash_table;

class _387_FirstUnique {
    public int firstUniqChar(String s) {
        int[] table = new int[128];

        for (char ch : s.toCharArray())
            table[ch]++;

        for (int i = 0; i < s.length(); i++) {
            if (table[s.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        _387_FirstUnique fu = new _387_FirstUnique();
        String[] strings = {"afjbg", "uweirtnuvx", "abcda"};
        for (String s : strings)
            System.out.println(fu.firstUniqChar(s));
    }
}