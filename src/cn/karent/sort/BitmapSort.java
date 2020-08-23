package cn.karent.sort;

/**
 * 位图排序, 可以用在大文件里面排序
 * 限制: 必须知道排序的数组最大值, 且数据不能重复
 */
public class BitmapSort {

    private int[] bit;

    public BitmapSort() {
        bit = new int[20];
    }

    private void insert(int d) {
        int index = d / 32;
        int pos = d % 32;
        bit[index] |= 1 << (31 - pos);
    }

    private void showBit(int d, int shift) {
        for(int i = 31; i >= 0; i--) {
            int tmp = d & (1 << i);
            if( tmp != 0) {
                System.out.print(shift * 32 + (31 - i) + " ");
            }
        }
    }

    private void show() {
        for(int i = 0; i < 20; i++) {
            showBit(bit[i], i);
        }
    }

    public void sort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
        show();
    }

}
