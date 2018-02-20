    public ListNode merge(ListNode list1, ListNode list2){
 		ListNode dummy = new ListNode(0);
		ListNode c1 = list1, c2 = list2, current = dummy;
		while (c1 != null && c2 != null) {
			if (c1.val <= c2.val) {
				current.next = c1;
				c1 = c1.next;
			} else {
				current.next = c2;
				c2 = c2.next;
			}
			current = current.next;
		}
		current.next = c1 != null ? c1 : c2;
		return dummy.next;
 	}
    
    public ListNode sortList(ListNode head) {
        if(head.next == null)
 			return head;
 		
 		ListNode slow = head, fast = head;
 		
 		while(fast.next != null && fast.next.next != null) {
 			slow = slow.next;
 			fast = fast.next.next; 
 		}
 		
 		ListNode right = this.sortList(slow.next);
 		slow.next = null;
 		ListNode left = this.sortList(head);
 		
 		return this.merge(left, right);
    }
