package com.ebw.algorithm.data;

/**
 * 单链表
 *
 * @author binglang
 * @since 2020/4/29
 */
public class SingleLinkedList<T> {

    private Node<T> head;

    private Node<T> tail;

    private int size;

    public SingleLinkedList() {
    }

    public SingleLinkedList(Node<T> node) {
        this.head = node;
        Node<T> t = node;
        while (t != null) {
            size++;
            if (t.next == null) {
                tail = t;
                break;
            }
            t = t.next;
        }
    }

    private static class Node<T> {

        T value;
        Node<T> next;

        public Node(T value) {
            this(value, null);
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public void add(T value) {
        Node<T> n = new Node<>(value);
        if (size == 0) {
            head = n;
            tail = n;
        } else if (size == 1) {
            head.next = n;
            tail = n;
        } else {
            Node<T> t = tail;
            t.next = n;
            tail = n;
        }
        size++;
    }

    public void add(int index, T value) {
        if (index == size + 1) {
            add(value);
        } else if (index >= 0 && index <= size) {
            Node<T> n = head;
            Node<T> newNode = new Node<>(value);
            if (index == 0) {
                newNode.next = n;
                head = newNode;
                size++;
                return;
            }
            for (int i = 1; i < size; i++) {
                if (i == index) {
                    newNode.next = n.next;
                    n.next = newNode;
                    size++;
                    return;
                }
                n = n.next;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public Node<T> get(int index) {
        checkIndexOutOfBounds(index);
        Node<T> n = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return n;
            }
            n = n.next;
        }
        return null;
    }

    public boolean remove(T value) {
        Node<T> temp;
        if (size < 1) {
            return false;
        }
        if (value == null) {
            for (Node<T> n = head; n != null; n = n.next) {
                if (n == head && n.value == null) {
                    head = n.next;
                    if (head == null) {
                        tail = null;
                    }
                    size--;
                    return true;
                } else if (n.next != null && n.next.value == null) {
                    temp = n.next;
                    n.next = temp.next;
                    temp.next = null;
                    size--;
                    return true;
                }
            }
        } else {
            for (Node<T> n = head; n != null; n = n.next) {
                if (n == head && n.value.equals(value)) {
                    head = n.next;
                    if (head == null) {
                        tail = null;
                    }
                    size--;
                    return true;
                } else if (n.next != null && n.next.value.equals(value)) {
                    temp = n.next;
                    n.next = temp.next;
                    temp.next = null;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    public Node<T> remove(int index) {
        checkIndexOutOfBounds(index);
        Node<T> n = head;
        if (index == 0) {
            head = n.next;
            if (head == tail) {
                tail = null;
            }
            size--;
            return n;
        }
        Node<T> temp;
        for (int i = 0, len = size - 1; i < len; i++) {
            if (i + 1 == index) {
                if (n.next == tail) {
                    temp = tail;
                    n.next = null;
                    tail = n;
                } else {
                    temp = n.next;
                    n.next = temp.next;
                    temp.next = null;
                }
                size--;
                return temp;
            }
            n = n.next;
        }
        throw new IndexOutOfBoundsException();
    }

    private void checkIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 单链表反转
     */
    public SingleLinkedList<T> reverseList() {
        Node<T> pre = null, cur, next;
        if (size < 2) {
            return this;
        }
        cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head = pre;
        return this;
    }

    /**
     * 利用多指针方式删除倒数第 n 个节点（不通过 size 变量）
     */
    public Node<T> removeByReverseOrder(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        if (head == null) {
            return null;
        }
        Node<T> p1 = head, p2 = p1.next, pre = null;
        int i = 0;
        // n 是从 1 开始计数，所以这里需要减 1 操作
        n -= 1;
        while (p2 != null) {
            // 先找到初始 p2 位置，p1、p2 再同时移动，同时记录 p1 的上一个节点为 pre，用于删除
            if (i >= n) {
                pre = p1;
                p1 = p1.next;
            }
            p2 = p2.next;
            i++;
        }
        if (pre == null) {
            throw new IndexOutOfBoundsException();
        }
        pre.next = p1.next;
        p1.next = null;
        return p1;
    }

    /**
     * 快慢指针方式寻找中间节点（不通过 size 变量）
     */
    public Node<T> findMiddleNode() {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node<T> slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 快慢指针方式检测环的存在
     */
    public boolean hasLoop() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 有序链表合并
     */
    public static <T extends Comparable<T>> SingleLinkedList<T> merge(
        SingleLinkedList<T> singleLinkedList1,
        SingleLinkedList<T> singleLinkedList2) {
        if (singleLinkedList1.head == null && singleLinkedList2.head == null) {
            return null;
        }
        if (singleLinkedList1.head == null) {
            return singleLinkedList1;
        }
        if (singleLinkedList2.head == null) {
            return singleLinkedList2;
        }
        Node<T> n1 = singleLinkedList1.head, n2 = singleLinkedList2.head, cur = new Node<>(
            null), first = cur;
        while (n1 != null || n2 != null) {
            if (n1 == null) {
                cur.next = n2;
                n2 = n2.next;
            } else if (n2 == null) {
                cur.next = n1;
                n1 = n1.next;
            } else if (n1.value.compareTo(n2.value) <= 0) {
                cur.next = n1;
                n1 = n1.next;
            } else {
                cur.next = n2;
                n2 = n2.next;
            }
            cur = cur.next;
        }
        return new SingleLinkedList<>(first.next);
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node<T> n = head; n != null; n = n.next) {
            sb.append(n.value.toString()).append(", ");
        }
        if (sb.length() == 1) {
            sb.append("]");
        } else {
            sb.replace(sb.length() - 2, sb.length(), "]");
        }
        return sb.toString();
    }
}
