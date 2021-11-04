package design;
import java.util.*;

class _981_TimeBasedKVStore {

	private Map<String, List<String>> valueMap;
    private Map<String, List<Integer>> tsMap;

	public _981_TimeBasedKVStore() {
		valueMap = new HashMap<>();
        tsMap = new HashMap<>();
	}
	//

	/**
	 * Set TimeStamp data variables
	 *
	 * Let M be number of set function calls and L be the average length of Key and Value strings
	 * 	 TC = O(M L)
	 * 	 SC = O(M L)
	 */
    public void set(String key, String value, int timestamp) {
        if (valueMap.containsKey(key)) {
            valueMap.get(key).add(value);
            tsMap.get(key).add(timestamp);
        } else {
            List<String> valueList = new ArrayList<>();
            valueList.add(value);
            valueMap.put(key, valueList);

            List<Integer> tsList = new ArrayList<>();
            tsList.add(timestamp);
            tsMap.put(key, tsList);
        }
    }

	/**
	 * Get the value for the given key and timestamp
	 *
	 * Let N be number of get function calls and L be the average length of Key and Value strings
	 * 	 TC = O(N L log M)
	 * 	 SC = O(1)
	 */
    public String get(String key, int timestamp) {
        String value = "";

        if (!valueMap.containsKey(key)) {
            return value;
        }

        List<Integer> tsList = tsMap.get(key);
        int start = 0, end = tsList.size();

        if (tsList.isEmpty() || tsList.get(start) > timestamp) {
            return value;
        } else if (tsList.get(end - 1) <= timestamp) {
            return valueMap.get(key).get(end - 1);
        } else {
            while (start < end) {
                int mid = start + (end - start) / 2;

                if (tsList.get(mid) == timestamp) {
                    return valueMap.get(key).get(end - 1);
                } else if (tsList.get(mid) < timestamp) {
                    start = mid + 1;
                 } else {
                    end = mid;
                }
            }
        }

        return end == 0 ? value : valueMap.get(key).get(start - 1);
    }

	public static void main(String args[]) {
		_981_TimeBasedKVStore ts = new _981_TimeBasedKVStore();
		ts.set("foo", "bar", 2);
		System.out.println(ts.get("foo", 1)); // ""
		System.out.println(ts.get("foo", 3)); // "bar"

		ts.set("foo", "bar2", 4);
		System.out.println(ts.get("foo", 2)); // "bar"
		System.out.println(ts.get("foo", 5)); // "bar2"
	}
}