package com.solvd.project.LinkedList;

public class CustomLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void insertTail(T value) {
        if (size == 0) {
            head = tail = new Node<>(value);
        } else {
            tail.next = new Node<>(value);
            tail = tail.next;
        }
        size++;
    }

    public void insertHead(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;

        if (size == 0) {
            tail = newNode;
        }

        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    public void removeHead() {
        if (head == null) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    public void removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            removeHead();
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        if (current.next == tail) {
            tail = current;
        }

        current.next = current.next.next;
        size--;
    }

    public int size() {
        return size;
    }
}
