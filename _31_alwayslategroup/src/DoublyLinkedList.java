

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap; // import the HashMap class

public class DoublyLinkedList<E> {

    private static class Node<E> {
        private HashMap<Integer, Boolean> data;
        private Node<E> next;
        private Node<E> prev;

        public Node(HashMap<Integer, Boolean> e, Node<E> p, Node<E> n) {
            data = new HashMap<Integer, Boolean>();

            prev = p;
            next = n;
        }

        public HashMap<Integer, Boolean> getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }


    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(new HashMap<Integer, Boolean>(), null, null);
        tail = new Node<E>(new HashMap<Integer, Boolean>(), head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
    }
    public int size() {
        // TODO
        size = 0;
        Node<E> curr;

        if (head.getNext() == tail) {
            return size;
        } else {
            curr = head;
            while (curr.getNext() != tail) {
                curr = curr.getNext();
                size++;
            }
        }


        return size;
    }
    public boolean isEmpty() {
        // TODO
        if (head.getNext() == tail) {
            return true;
        }
        return false;
    }
    public HashMap<Integer, Boolean> get(int i) {
        // TODO

        int pos = i;

        if (pos >= this.size()) {
            throw new IndexOutOfBoundsException("Index greater than size of linked list");
        }

        Node<E> curr_node = head.next;

        while (pos > 0)
        {
            curr_node = curr_node.next;
            pos--;
        }

        return curr_node.getData();
    }
    public void add(int i, HashMap<Integer, Boolean> e) {
        // TODO
        int pos = i;

        if (pos >= this.size()) {
            throw new IndexOutOfBoundsException("Index greater than size of linked list");
        }

        Node<E> prev_node = head.next;
        Node<E> curr_node = prev_node.next;
        Node<E> new_node;

//        if (prev_node.getNext() == tail) {
//            new_node = new Node(e, prev_node, tail);
//            prev_node.next = new_node;
//            tail.prev = new_node;
//        }
//
//        Node<E> curr_node = prev_node.next;
//
//        if (curr_node.getNext() == tail) {
//            new_node = new Node(e, curr_node, tail);
//            curr_node.next = new_node;
//            tail.prev = new_node;
//        }

        while (pos > 1)
        {
            prev_node = curr_node;
            curr_node = curr_node.next;
            pos--;
//            System.out.println("WHILE");
        }

//        System.out.println("WOAH");

        new_node = new Node(e, prev_node, curr_node);
        prev_node.next = new_node;
        curr_node.prev = new_node;

    }
    public HashMap<Integer, Boolean> remove(int i) {
        // TODO
        int pos = i;

        if (pos >= this.size()) {
            throw new IndexOutOfBoundsException("Index greater than size of linked list");
        }

        Node<E> prev_node = head.next;
        Node<E> curr_node = prev_node.next;
        Node<E> deleted_node;

        while (pos > 1)
        {
            prev_node = curr_node;
            curr_node = curr_node.next;
            pos--;
//            System.out.println("WHILE");
        }

//        System.out.println("WOAH");


        deleted_node = curr_node;
        curr_node = curr_node.getNext();
        prev_node.next = curr_node;
        curr_node.prev = prev_node;

        return deleted_node.getData();
    }

//    private class DoublyLinkedListIterator<E> implements Iterator<E> {
//        Node<E> curr = (Node<E>) head.next;
//        public boolean hasNext() {
//            return curr != tail;
//        }
//        public E next() {
//            E res = curr.data;
//            curr = curr.next;
//            return res;
//        }
//    }
//    public Iterator<E> iterator() {
//        return new DoublyLinkedListIterator<E>();
//    }

    private E remove(Node<E> n) {
        // TODO
        return null;
    }

    public HashMap<Integer, Boolean> first() {
        if (isEmpty()) {
            return null;
        }
        return head.getNext().getData();
    }

    public HashMap<Integer, Boolean> last() {
        // TODO
        if (head.getNext() == tail) {
            return null;
        }
        return tail.getPrev().getData();
    }
    public HashMap<Integer, Boolean> removeFirst() {
        // TODO
        if (isEmpty()) {
            return null;
        } else {
            Node<E> temp = head.getNext();
            head.next = temp.getNext();
            return temp.getData();
        }
    }
    public HashMap<Integer, Boolean> removeLast() {
        // TODO
        if (isEmpty()) {
            return null;
        } else {
            Node<E> curr_node = tail.getPrev();
            Node<E> prev_node = curr_node.getPrev();
            prev_node.next = tail;
            tail.prev = prev_node;
            return curr_node.getData();
        }
    }
    public void addLast(HashMap<Integer, Boolean> e) {
        // TODO
        Node<E> new_node;


        if (head.getNext() == tail) {
            new_node = new Node(e, head, tail);
            head.next = new_node;
            tail.prev = new_node;
        } else {
            new_node = new Node(e, tail.getPrev(), tail);
            tail.prev.next = new_node;
            tail.prev = new_node;
        }


    }
    public void addFirst(HashMap<Integer, Boolean> e) {
        // TODO
//        Node<E> new_node;


        if (head.getNext() == tail) {
            Node<E> new_node = new Node(e, head, tail);
            head.next = new_node;
            tail.prev = new_node;
        } else {
            Node<E> curr_node = head.getNext();
            Node<E> new_node = new Node(e, head, curr_node);
            curr_node.prev = new_node;
            head.next = new_node;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.getNext();
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.getNext();
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
//        ll.addFirst(0);
//        ll.addFirst(1);
//        ll.addFirst(2);
//        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

    }
}