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
        ListNode current = list;
        StringBuilder sb = new StringBuilder();

        while(current != null){
            sb.append(current.val);
            sb.append(" -> ");
            current = current.next;
        }
        sb.setLength(sb.toString().length() - 2);
        System.out.println(sb.toString());
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

        long firstNumber = getNumber(first), secondNumber = getNumber(second);
        System.out.println(firstNumber);
        System.out.println(secondNumber);
        ListNode head = calculate(first, second);
        printList(head);
    }
}
