package double_linked_list;

import java.util.Objects;

public class DoubleLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> tail;
    private int size;

    public ListItem<T> getHeadElement() {
        return head;
    }

    public ListItem<T> getTailElement() {
        return tail;
    }

    public ListItem<T> popHeadElement() {
        // TODO
        ListItem<T> item = head;
        if (head != null) {
            head = head.next;
            size--;
        }
        return item;
    }

    public ListItem<T> popTailElement() {
        // TODO
        ListItem<T> item = tail;
        if (tail != null) {
            tail = tail.prev;
            size--;
        }
        return item;
    }

    public void removeHeadElement() {
        // TODO
        if (head != null && size > 1) {
            head = head.next;
            size--;
        } else {
            head = null;
            tail = null;
            size = 0;
        }
    }

    public void removeTailElement() {
        // TODO
        if (tail != null && size > 1) {
            tail = tail.prev;
            tail.next = null;
            size--;
        } else {
            tail = null;
            head = null;
            size = 0;
        }
    }

    public void addToHead(T data) {
        // TODO
        ListItem item = new ListItem(data);
        if (head == null) {
            tail = item;
        } else {
            item.next = head;
            head.prev = item;
        }
        head = item;
        size++;
    }

    public void addToTail(T data) {
        // TODO
        ListItem item = new ListItem(data);
        if (tail == null) {
            head = item;
        } else {
            item.prev = tail;
            tail.next = item;
        }
        tail = item;
        size++;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleLinkedList<T> that = (DoubleLinkedList<T>) o;
        return Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder(head.toString());
        ListItem<T> item = head;
        while (item.next != null) {
            if (item.next.prev == item) {
                stringBuilder.append("<-");
            }

            stringBuilder.append(" -> ").append(item.next);
            item = item.next;
        }

        return "DoubleLinkedList{size=" + size + "\n" + stringBuilder.toString() + "}";
    }
}