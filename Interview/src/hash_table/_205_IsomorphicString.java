package hash_table;

/*
 * Design Custom Key Technique
 *
 * This is the first problem we encountered to design a custom hash function based on the input.
 */
class _205_IsomorphicString {
    public boolean isIsomorphic(String s, String t) {
        return transform(s).equals(transform(t));
    }

    /*
     * Transformation logic
     *
     * We create character to index mapping and create a string. We add a delimiter("#") to avoid corner cases.
     *
     * "egg" = 0#1#1
     */
    private String transform(String str) {
        int[] table = new int[256];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (table[ch] == 0)
                table[ch] = i + 1;

            sb.append(table[ch]).append("#");
        }

        return sb.toString();
    }

    /**
     * Using two hash map approach
     */
    public boolean isIsomorphic2(String s, String t) {
        char[] _1to2 = new char[256], _2to1 = new char[256];

        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i), ch2 = t.charAt(i);

            if (_1to2[ch1] != '\0' && _1to2[ch1] != ch2)
                return false;

            if (_2to1[ch2] != '\0' && _2to1[ch2] != ch1)
                return false;

            _1to2[ch1] = ch2;
            _2to1[ch2] = ch1;
        }

        return true;
    }

    public static void main(String[] args) {
        _205_IsomorphicString is = new _205_IsomorphicString();

        String[] first = {"egg", "foo", "paper", "ab", "aaeaa"};
        String[] second = {"add", "bar", "title", "aa", "uuxyy"};

        for (int i = 0; i < first.length; i++) {
            System.out.printf("\"%s\" and \"%s\" are%s isomorphic\n",
                    first[i], second[i], is.isIsomorphic2(first[i], second[i]) ? "" : " not");
        }
    }
}