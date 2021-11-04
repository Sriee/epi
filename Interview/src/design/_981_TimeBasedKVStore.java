package design;
import java.util.*;

class _981_TimeBasedKVStore {

	Map<String, TreeMap<Integer, String>>  timeStampMap;
	public _981_TimeBasedKVStore() {
		timeStampMap = new HashMap<>();
	}
	//

	/**
	 * Set TimeStamp data variables
	 *
	 * Let M be number of set function calls and L be the average length of Key and Value strings
	 * 	 TC = O(M L log M)
	 * 	 SC = O(M. L)
	 */
	public void setValue(String key, String value, int timestamp) {
		TreeMap<Integer, String> tsMap = timeStampMap.get(key);

		if (tsMap == null) {
			tsMap = new TreeMap<>();
		}

		tsMap.put(timestamp, value);
		timeStampMap.put(key, tsMap);
	}

	/**
	 * Get the value for the given key and timestamp
	 *
	 * Let N be number of get function calls and L be the average length of Key and Value strings
	 * 	 TC = O(N (L log M + log M))
	 * 	 SC = O(1)
	 */
	public String getValue(String key, int timeStamp) {
		// Replace this placeholder return statement with your code
		TreeMap<Integer, String> tsMap = timeStampMap.get(key);
		String value = "";

		if (tsMap != null) {
			Map.Entry<Integer, String> entry = tsMap.floorEntry(timeStamp);
			if (entry != null) value = entry.getValue();
		}

		return value;
	}

	public static void main(String args[]) {
		_981_TimeBasedKVStore ts = new _981_TimeBasedKVStore();
		ts.setValue("foo", "bar", 2);
		System.out.println(ts.getValue("foo", 1)); // ""
		System.out.println(ts.getValue("foo", 3)); // "bar"

		ts.setValue("foo", "bar2", 4);
		System.out.println(ts.getValue("foo", 2)); // "bar"
		System.out.println(ts.getValue("foo", 5)); // "bar2"
	}
}