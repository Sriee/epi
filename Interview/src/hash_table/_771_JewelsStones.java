package hash_table;

class _771_JewelsStones {
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        int[] table = new int[128];

        for (char ch : jewels.toCharArray())
            table[ch]++;

        for (char ch : stones.toCharArray()) {
            if (table[ch] != 0)
                count++;
        }

        return count;
    }

    public static void main(String[] args) {
        _771_JewelsStones js = new _771_JewelsStones();
        System.out.println(js.numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(js.numJewelsInStones("z", "ZZ"));
    }
}