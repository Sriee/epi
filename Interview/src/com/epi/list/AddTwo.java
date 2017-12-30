package com.epi.list;

public class AddTwo {

    /**
     *
     * @param node List Node
     * @return
     */
    private static long getNumber(ListNode<Integer> node){
        ListNode<Integer> current = node;
        long number = 0, idx =  0;

        while(current != null){
            number += (current.data * (Math.pow(10, idx)));
            idx++;
            current = current.next;
        }

        return number;
    }

    private static ListNode<Integer> getListNode(long number){
        ListNode<Integer> head = null, current = null;

        if(number == 0){
            head = new ListNode<>(0);
            return head;
        }
        while(number > 0){
            if(head == null){
                head = new ListNode<>((int)(number % 10));
                current = head;
            } else {
                current.next = new ListNode<>((int)(number % 10));
                current = current.next;
            }
            number = number / 10;
        }
        return head;
    }

    private static ListNode<Integer> calculate(ListNode<Integer> l1, ListNode<Integer> l2){
        ListNode<Integer> head = null, current = null, first = l1, second = l2;
        int total = 0, sum = 0, carry = 0;

        while(first != null && second != null) {
            total = first.data + second.data + carry;
            sum = total % 10;
            carry = total / 10;
            if (head == null) {
                head = new ListNode<>(sum);
                current = head;
            } else {
                current.next = new ListNode<>(sum);
                current = current.next;
            }
            first = first.next;
            second = second.next;
        }

        while(first != null){
            total = first.data + carry;
            sum = total % 10;
            carry = total / 10;
            if (head == null) {
                head = new ListNode<>(sum);
                current = head;
            } else {
                current.next = new ListNode<>(sum);
                current = current.next;
            }
            first = first.next;
        }

        while (second != null){
            total = second.data + carry;
            sum = total % 10;
            carry = total / 10;
            if (head == null) {
                head = new ListNode<>(sum);
                current = head;
            } else {
                current.next = new ListNode<>(sum);
                current = current.next;
            }
            second = second.next;
        }
        if(carry != 0) current.next = new ListNode<>(carry);;

        return head;
    }

    public static boolean hasLoop(ListNode<Integer> head) {
    	ListNode<Integer> slow = head, fast = head;
    	
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
    
    private static ListNode<Integer> removeLoop(ListNode<Integer> head, ListNode<Integer> node) {
    	ListNode<Integer> first = head, second = node;
    	
    	while(second.next != first.next) {
    		first = first.next;
    		second = second.next;
    	}
    	second.next = null;
    	return head; 
    }
       
    public static void reorderList(ListNode<Integer> head) {
        if(head == null || head.next == null) return;
        
        ListNode<Integer> slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode<Integer> reversed = ListUtil.reverse(slow.next);
        
        slow.next = null;
        ListNode<Integer> reverseIter = reversed, iter = head, temp, temp2;
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
        ListNode<Integer> first = new ListNode<>(9);

        ListNode<Integer> second = new ListNode<>(1);
        second.next = new ListNode<>(9);
        second.next.next = new ListNode<>(9);
        second.next.next.next = new ListNode<>(9);
        second.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next.next.next.next.next = new ListNode<>(9);

        ListNode<Integer> looped = new ListNode<>(2);
        looped.next = new ListNode<>(567);
        looped.next.next = new ListNode<>(48);
        looped.next.next.next = new ListNode<>(74);
        looped.next.next.next.next = new ListNode<>(5);
        looped.next.next.next.next.next = new ListNode<>(9);
        
        looped.next.next.next.next.next.next = looped.next.next;

        long firstNumber = getNumber(first), secondNumber = getNumber(second);
        System.out.println(firstNumber);
        System.out.println(secondNumber);
        ListNode<Integer> head = calculate(first, second);
        ListNode<Integer> node = getListNode(firstNumber + secondNumber);
        ListUtil.print(head);
        ListUtil.print(node);
        
        System.out.println(hasLoop(looped));
        ListUtil.print(looped);
    }
}
