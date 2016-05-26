/**
 * Created by Disa on 13.03.2016.
 */
public class LinkedQueue extends AbstractQueue implements Queue {
    private static class Node {
        Object val;
        Node next;

        public Node() {
        }

        public Node(Object val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node head, tail;

    public void doEnqueue(Object q) {
        Node newNode = new Node(q, null);
        Node old = tail;
        tail = newNode;
        if (old == null) {
            head = newNode;
        } else {
            old.next = newNode;
        }
    }


    public Object doElement() {
        return head.val;
    }


    public Object doDequeue() {
        Object res = head.val;
        Node next = head.next;
        head = next;
        if (next == null)
            tail = null;
        return res;
    }

}
