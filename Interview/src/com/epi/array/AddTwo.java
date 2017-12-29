package com.epi.array;

public class AddTwo {

    /**
     *
     * @param node List Node
     * @return
     */
    private static long getNumber(ListNode node){
        ListNode current = node;
        long number = 0, idx =  0;

        while(current != null){
            number += (current.val * (Math.pow(10, idx)));
            idx++;
            current = current.next;
        }

        return number;
    }

    private static ListNode getListNode(long number){
        ListNode head = null, current = null;

        if(number == 0){
            head = new ListNode(0);
            return head;
        }
        while(number > 0){
            if(head == null){
                head = new ListNode((int)(number % 10));
                current = head;
            } else {
                current.next = new ListNode((int)(number % 10));
                current = current.next;
            }
            number = number / 10;
        }
        return head;
    }

    private static ListNode calculate(ListNode l1, ListNode l2){
        ListNode head = null, current = null, first = l1, second = l2;
        int total = 0, sum = 0, carry = 0;

        while(first != null && second != null) {
            total = first.val + second.val + carry;
            sum = total % 10;
            carry = total / 10;
            if (head == null) {
                head = new ListNode(sum);
                current = head;
            } else {
                current.next = new ListNode(sum);
                current = current.next;
            }
            first = first.next;
            second = second.next;
        }

        while(first != null){
            total = first.val + carry;
            sum = total % 10;
            carry = total / 10;
            if (head == null) {
                head = new ListNode(sum);
                current = head;
            } else {
                current.next = new ListNode(sum);
                current = current.next;
            }
            first = first.next;
        }

        while (second != null){
            total = second.val + carry;
            sum = total % 10;
            carry = total / 10;
            if (head == null) {
                head = new ListNode(sum);
                current = head;
            } else {
                current.next = new ListNode(sum);
                current = current.next;
            }
            second = second.next;
        }
        if(carry != 0) current.next = new ListNode(carry);;

        return head;
    }

    private static void printList(ListNode list){
    	if(list == null) return;
    	
    	if(list.next == null){
    		System.out.println(list.val);
    		return;
    	}
        ListNode current = list;
        StringBuilder sb = new StringBuilder();

        while(current != null){
            sb.append(current.val);
            sb.append(" -> ");
            current = current.next;
        }
        sb.setLength(sb.toString().length() - 4);
        System.out.println(sb.toString());
    }

    public static boolean hasLoop(ListNode head) {
    	ListNode slow = head, fast = head;
    	
    	while(slow != null && fast != null  && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    		if(slow == fast) {
    			removeLoop(head, slow);
    			return true;
    		}
    	}
    	return false;
    }
    
    private static ListNode removeLoop(ListNode head, ListNode node) {
    	ListNode first = head, second = node;
    	
    	while(second.next != first.next) {
    		first = first.next;
    		second = second.next;
    	}
    	second.next = null;
    	return head; 
    }
    
    public static ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode iter = dummy.next, temp = null;
		while (iter.next != null) {
			temp = iter.next;
			iter.next = temp.next;
			temp.next = dummy.next;
			dummy.next = temp;
		}
		return dummy.next;    
    }
    
    public static void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reversed = reverse(slow.next);
        
        slow.next = null;
        ListNode reverseIter = reversed, iter = head, temp, temp2;
        printList(iter);
        printList(reversed);
        while(reverseIter != null){
            temp = iter.next;
            temp2 = reverseIter.next;
            iter.next = reverseIter;
            reverseIter.next = temp;
            iter = reverseIter.next;
            reverseIter = temp2;
        }
    }
    
    public static void main(String[] args) {
        ListNode first = new ListNode(9);

        ListNode second = new ListNode(1);
        second.next = new ListNode(9);
        second.next.next = new ListNode(9);
        second.next.next.next = new ListNode(9);
        second.next.next.next.next = new ListNode(9);
        second.next.next.next.next.next = new ListNode(9);
        second.next.next.next.next.next.next = new ListNode(9);
        second.next.next.next.next.next.next.next = new ListNode(9);
        second.next.next.next.next.next.next.next.next = new ListNode(9);
        second.next.next.next.next.next.next.next.next.next = new ListNode(9);

        ListNode looped = new ListNode(2);
        looped.next = new ListNode(567);
        looped.next.next = new ListNode(48);
        looped.next.next.next = new ListNode(74);
        looped.next.next.next.next = new ListNode(5);
        looped.next.next.next.next.next = new ListNode(9);
        
        looped.next.next.next.next.next.next = looped.next.next;

        long firstNumber = getNumber(first), secondNumber = getNumber(second);
        System.out.println(firstNumber);
        System.out.println(secondNumber);
        ListNode head = calculate(first, second);
        ListNode node = getListNode(firstNumber + secondNumber);
        printList(head);
        printList(node);
        System.out.println(hasLoop(looped));
        printList(looped);
    }
}
