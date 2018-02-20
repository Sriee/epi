public class TestInsertionSort{
	
	private static InsertionSort is = null;

	@Before
	public void setUp(){
		is = new InsertionSort();
	}

	@Test
	public void testEdgeCases(){
		
		ListNode<Integer> toSort = new ListNode<>(1);

		assertEquals(toSort, is.insertionSort(toSort));
		assertEquals(null, is.insertionSort(null));
	}

	@Test
	public void testCorrectness(){

		// To sort
		ListNode<Integer> toSort = new ListNode<>(Integer.MIN_VALUE);
		toSort.next = new ListNode<>(0);
		toSort.next.next = new ListNode<>(-41);
		toSort.next.next.next = new ListNode<>(Integer.MAX_VALUE);
		toSort.next.next.next.next = new ListNode<>(77);
		toSort.next.next.next.next.next = new ListNode<>(3167);
		toSort.next.next.next.next.next.next = new ListNode<>(44);
		toSort.next.next.next.next.next.next.next = new ListNode<>(-1);

		// Sorted
		ListNode<Integer> sorted = new ListNode<>(Integer.MIN_VALUE);
		sorted.next = new ListNode<>(-41);
		sorted.next.next = new ListNode<>(-1);
		sorted.next.next.next = new ListNode<>(0);
		sorted.next.next.next.next = new ListNode<>(44);
		sorted.next.next.next.next.next = new ListNode<>(44);
		sorted.next.next.next.next.next.next = new ListNode<>(3167);
		sorted.next.next.next.next.next.next.next = new ListNode<>(Integer.MAX_VALUE);

		ListNode<Integer> sorted = is.insertionSort(toSort);

		assertEquals(, ListUtil.length(sorted));

		ListNode<Integer> _to = toSort, _so = sorted;

		while(_to != null){

		}  

	}
}