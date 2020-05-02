package com.ebw.algorithm.data;

/**
 * @author binglang
 * @since 2020/4/29
 */
public class SingleLinkedListTest {

    /**
     * 单链表测试
     */
    public static void main(String[] args) {
        SingleLinkedList<String> singleLinkedList = new SingleLinkedList<>();
        // add(value)
        System.out.println("add(value): ");
        for (int i = 0; i < 10; i++) {
            singleLinkedList.add(i + "");
        }
        System.out.println(singleLinkedList + " size:" + singleLinkedList.size());

        // add(index, value)
        System.out.println("add(index, value): ");
        for (int i = 0; i < 10; i++) {
            singleLinkedList.add(i, i + 10 + "");
        }
        System.out.println(singleLinkedList + " size:" + singleLinkedList.size());

        // get(index)
        System.out.println("get(): ");
        for (int i = 2; i < 4; i++) {
            System.out.print(singleLinkedList.get(i) + ", ");
        }
        System.out.println();

        // remove(index)
        System.out.println("remove(index): ");
        for (int i = 17; i < 19; i++) {
            System.out.println(singleLinkedList.remove(i));
        }
        System.out.println(singleLinkedList + " size:" + singleLinkedList.size());

        // remove(value)
        System.out.println("remove(value): ");
        for (int i = 18; i < 22; i++) {
            System.out.println(singleLinkedList.remove(i + ""));
        }
        System.out.println(singleLinkedList + " size:" + singleLinkedList.size());

        // 1. 单链表反转
        System.out.println(singleLinkedList.reverseList());

        // 2. 检测是否存在环
        System.out.println(singleLinkedList.hasLoop());

        // 3. 删除链表倒数第 n 个结点
        System.out.println(singleLinkedList.remove(singleLinkedList.size() - 2));
        System.out.println(singleLinkedList);
        System.out.println(singleLinkedList.removeByReverseOrder(2));
        System.out.println(singleLinkedList);

        // 4. 求链表的中间结点
        System.out.println(singleLinkedList.get(singleLinkedList.size() / 2));
        System.out.println(singleLinkedList.findMiddleNode());

        // 5. 两个有序的链表合并
        SingleLinkedList<Integer> singleLinkedList1 = new SingleLinkedList<>();
        SingleLinkedList<Integer> singleLinkedList2 = new SingleLinkedList<>();
        for (int i = 0; i < 8; i += 2) {
            singleLinkedList1.add(i);
        }
        for (int i = 1; i < 10; i += 2) {
            singleLinkedList2.add(i);
        }
        System.out.println(singleLinkedList1);
        System.out.println(singleLinkedList2);
        System.out
            .println("merge: " + SingleLinkedList.merge(singleLinkedList1, singleLinkedList2));
    }

}
