package com.ebw.algorithm.data;

/**
 * @author binglang
 * @since 2020/4/30
 */
public class SortAlgorithmTest {

    public static void main(String[] args) {
        int[] array = new int[]{8, 9, 1, 4, 6, 3, 0, 2, 5, 7};
        // bubbleSort(array);
        // insertionSort(array);
        // selectionSort(array);
        quickSort(array, 0, array.length - 1);
        for (int value : array) {
            System.out.print(value + ",");
        }
        // 二分查找第一个值为 value 的位置
        System.out.println();
        array = new int[]{1, 2, 3, 3, 3, 4, 5, 7};
        System.out.println(searchFirstIndex(array, 3));
        System.out.println(searchLastIndex(array, 3));
        System.out.println(searchFirstBiggerIndex(array, 6));
        System.out.println(searchLastSmallerIndex(array, 6));
    }

    /**
     * 查找最后一个小于等于给定值的元素
     */
    public static int searchLastSmallerIndex(int[] array, int value) {
        int start = 0, end = array.length - 1;
        int middle;
        while (start <= end) {
            middle = start + ((end - start) >> 1);
            if (array[middle] <= value) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        if (end >= 0 && array[end] <= value) {
            return end;
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     */
    public static int searchFirstBiggerIndex(int[] array, int value) {
        int start = 0, end = array.length - 1;
        int middle;
        while (start <= end) {
            middle = start + ((end - start) >> 1);
            if (array[middle] >= value) {
                if (middle == 0 || array[middle - 1] < value) {
                    return middle;
                }
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找第一个值为 value 的位置
     */
    public static int searchFirstIndex(int[] array, int value) {
        int start = 0, end = array.length - 1;
        int middle;
        while (start <= end) {
            middle = start + ((end - start) >> 1);
            if (array[middle] >= value) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        if (start < array.length - 1 && array[start] == value) {
            return start;
        }
        return -1;
    }

    /**
     * 二分查找最后一个值为 value 的位置
     */
    public static int searchLastIndex(int[] array, int value) {
        int start = 0, end = array.length - 1;
        int middle;
        while (start <= end) {
            middle = start + ((end - start) >> 1);
            if (array[middle] <= value) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        if (end < array.length - 1 && array[end] == value) {
            return end;
        }
        return -1;
    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] array) {
        int temp;
        boolean isSwap;
        for (int i = 0; i < array.length; i++) {
            isSwap = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
        }
    }

    /**
     * 插入排序（分为有序、无序两个区间） 扩展希尔排序 shellSort : https://www.cnblogs.com/chengxiao/p/6104371.html
     */
    private static void insertionSort(int[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (temp < array[j]) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
    }

    /**
     * 选择排序
     */
    private static void selectionSort(int[] array) {
        int min;
        for (int i = 0; i < array.length; i++) {
            min = array[i];
            for (int j = i; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                }
                if (array[i] > min) {
                    array[j] = array[i];
                    array[i] = min;
                }
            }
        }
    }

    /**
     * 快速排序（分治、分区）
     */
    private static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int middleValue = array[end];
        int middleIndex = start;
        int tempValue;
        for (int j = start; j < end; j++) {
            if (array[j] < middleValue) {
                tempValue = array[middleIndex];
                array[middleIndex] = array[j];
                array[j] = tempValue;
                middleIndex++;
            }
        }
        tempValue = array[end];
        array[end] = array[middleIndex];
        array[middleIndex] = tempValue;

        quickSort(array, start, middleIndex - 1);
        quickSort(array, middleIndex + 1, end);
    }

    /**
     * 归并排序（需要额外空间）
     */
    private static void mergeSort(int[] array) {
    }

    /**
     * 线性排序 1. 桶排序： 2. 计数排序（桶排序的一种特殊应用）： 例. 50万高考考生分数如何获得某个分数排在第几名， 0-900 分为 901 个桶， 桶内存储该分数的人数 3.
     * 基数排序： 例. 10 万个手机号码从小到大排序，利用稳定性排序的特性从后往前依次按一位数字进行排序，如排序的数据长度不一致，可采用补齐方式
     */
    private static void lineSort(int[] array) {
    }
}
