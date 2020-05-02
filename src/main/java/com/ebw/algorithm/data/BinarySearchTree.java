package com.ebw.algorithm.data;

/**
 * 二叉搜索树
 *
 * @author binglang
 * @since 2020/5/1
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public BinarySearchTree() {
    }

    private static class Node<T> {

        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public Node<T> get(T data) {
        Node<T> cur = root;
        return get(data, cur);
    }

    private Node<T> get(T data, Node<T> cur) {
        if (cur == null) {
            return null;
        }
        int value = cur.data.compareTo(data);
        if (value == 0) {
            return cur;
        } else if (value < 0) {
            cur = cur.right;
        } else {
            cur = cur.left;
        }
        return get(data, cur);
    }

    public boolean add(T data) {
        if (data == null) {
            return false;
        }
        Node<T> cur = root;
        if (cur == null) {
            root = new Node<>(data);
            return true;
        }
        return add(data, cur);
    }

    private boolean add(T data, Node<T> cur) {
        int compareValue = cur.data.compareTo(data);
        // 不允许重复值
        if (compareValue == 0) {
            return false;
        } else if (compareValue < 0) {
            if (cur.right == null) {
                cur.right = new Node<>(data);
                return true;
            } else {
                cur = cur.right;
            }
        } else {
            if (cur.left == null) {
                cur.left = new Node<>(data);
                return true;
            } else {
                cur = cur.left;
            }
        }
        return add(data, cur);
    }

    public boolean remove(T data) {
        Node<T> parent = null;
        Node<T> removeNode = root;
        while (removeNode != null && removeNode.data.compareTo(data) != 0) {
            parent = removeNode;
            if (removeNode.data.compareTo(data) < 0) {
                removeNode = removeNode.right;
            } else {
                removeNode = removeNode.left;
            }
        }
        if (removeNode == null) {
            return false;
        }
        return remove(parent, removeNode);
    }

    private boolean remove(Node<T> parent, Node<T> removeNode) {
        if (removeNode.left == null && removeNode.right == null) {
            replaceNode(parent, removeNode, null);
        } else if (removeNode.left == null || removeNode.right == null) {
            replaceNode(parent, removeNode,
                removeNode.left == null ? removeNode.right : removeNode.left);
        } else {
            replaceNode(parent, removeNode, findLastLeft(removeNode));
        }
        return true;
    }

    private void replaceNode(Node<T> parent, Node<T> removeNode, Node<T> child) {
        if (parent == null) {
            root = child;
        } else if (parent.left == removeNode) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    private Node<T> findLastLeft(Node<T> node) {
        if (node.left == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 找到某个值的前驱节点
     */
    public Node<T> findPreValue(T data) {
        return null;
    }

    /**
     * 找到某个值的后继节点
     */
    public Node<T> findNextValue(T data) {
        return null;
    }


    /**
     * 前序遍历
     */
    private void preOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        print(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 遍历打印
     */
    public void printOrder() {
        System.out.print("in-order: ");
        inOrder(root);
        System.out.print(System.lineSeparator() + "pre-order: ");
        preOrder(root);
        System.out.print(System.lineSeparator() + "post-order: ");
        postOrder(root);
    }

    /**
     * 中序遍历
     */
    public void inOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        print(node);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    private void postOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        print(node);
    }

    private void print(Node<T> node) {
        if (node != null) {
            System.out.print(node.data.toString() + "\t");
        }
    }
}
