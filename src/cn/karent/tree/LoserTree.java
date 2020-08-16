package cn.karent.tree;

/**
 * 败者树, 多路平衡归并排序, 一般可用在外排
 * 大文件排序：
 * 1.首先将文件分割成一个个小文件
 * 2. 在内存中对小文件进行排序
 * 3. 使用多路平衡归并排序来对排好序的小文件再进行排序(2路平衡排序是将两个排好序的小文件进行排序，
 * 多路平衡归并排序是对多个小文件来进行排序, 使用败者树就可以避免重复排序)
 */
public class LoserTree {

    private void adjust(int[] ls, int[] b, int s, int k) {
        int t = (s + k)/2;  // 叶子节点的父节点
        // 这里默认s为胜者, 从该叶子节点到根节点, 更新胜者
        while( t > 0) {
            if( b[s] > b[ls[t]]) {
                int tmp = s;
                s = ls[t];
                ls[t] = tmp;
            }
            t /= 2;
        }
        ls[0] = s;
    }

    private void createLoserTree(int[] ls, int[] b, int k) {
        b[k] = Integer.MIN_VALUE;
        // 初始化败者树
        for(int i = 0; i < ls.length; i++) {
            ls[i] = k;
        }
        // 开始调整第一轮败者树
        for(int i = k-1; i >= 0; i--) {
            adjust(ls, b, i, k);
        }

    }

    private void input(int[][] data, int[] b, int[] index, int i) {
        if(index[i] >= data[i].length) {
            b[i] = Integer.MAX_VALUE;
        } else {
            b[i] = data[i][index[i]++];
        }
    }

    /**
     * 这里以5路归并排序为例
     */
    public void loserTree() {
        int[][] data = {{10, 15, 16}, {9, 18, 20}, {20, 22, 40}, {6, 15, 25}, {12, 37, 48}};
        //败者树的叶子节点和非叶子节点之所以用两个数组来保存, 是因为叶子节点存储的是输入元素值, 非叶子节点存储的是下标值
        int[] b = new int[6];  // 败者树叶子节点
        int[] ls = new int[5];  // 败者树非叶子节点
        int[] index = {1, 1, 1, 1, 1};
        for(int i = 0; i < data.length; i++) {
            b[i] = data[i][0];
        }
        createLoserTree(ls, b, 5);
        System.out.print("[");
        while (b[ls[0]] < Integer.MAX_VALUE) {
            System.out.print(data[ls[0]][index[ls[0]]-1] + ",");
            input(data, b, index, ls[0]);
            adjust(ls, b, ls[0], 5);
        }
        System.out.println("]");
    }


}
