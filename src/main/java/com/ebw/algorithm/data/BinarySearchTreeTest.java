package com.ebw.algorithm.data;

/**
 * @author binglang
 * @since 2020/5/1
 */
public class BinarySearchTreeTest {

    public static void main(String[] args) {
        int[] array = new int[]{7, 9, 5, 3, 6, 4, 1, 2, 8, 10};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int it : array) {
            tree.add(it);
        }
        tree.printOrder();
        System.out.println(System.lineSeparator() + tree.get(5));
        System.out.println(tree.get(11));
        System.out.println(tree.remove(0));
        System.out.println(tree.remove(2));
        tree.printOrder();
    }
}
