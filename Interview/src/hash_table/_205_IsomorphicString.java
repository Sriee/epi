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

    public static void main(String[] args) {
        _205_IsomorphicString is = new _205_IsomorphicString();

        String[] first = {"egg", "foo", "paper"};
        String[] second = {"add", "bar", "title"};

        for (int i = 0; i < first.length; i++) {
            System.out.printf("\"%s\" and \"%s\" are%s isomorphic\n",
                    first[i], second[i], is.isIsomorphic(first[i], second[i]) ? "" : " not");
        }
    }
}