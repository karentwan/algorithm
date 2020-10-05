package cn.karent.tree;

import java.util.Arrays;

/**
 * B-树  m = 3
 */
public class BTree {
    class Node {
        private int[] key;  // 0号单元未用
        private Node[] children;
        private Node parent;

        private int n;

        public Node(int m) {
            key = new int[m];
            Arrays.fill(key, Integer.MAX_VALUE);
            children = new Node[m];
            n = 0;
            parent = null;
        }

        public Node() {
            this(3);
        }

        public void setKey(int index, int data, Node p) {
            this.key[index] = data;
            children[index-1] = p;
        }

        // 右移
        public void rightShift(int index) {
            key[index+1] = key[index];
            children[index+1] = children[index];
            children[index] = children[index-1];
        }

        public int getKey(int index) {
            return key[index];
        }

        public Node getNode(int index) {
            return children[index];
        }
    }

    private Node root = null;
    private int m = 0;

    public BTree() {
        m = 3;
        root = new Node();
    }

    /**
     * TODO need to be completed!!
     * 每次插入都应该插入到最下面一层非终端节点当中
     * @param data
     * @return
     */
    public Node insert(int data) {
        return null;
    }

    public Node search(int data) {
        Node p = root;
        int i = 0;
        while( p != null) {
           i = 1;
           while( i < m && p.getKey(i) < data) {
               i++;
           }
           if( i < m && p.getKey(i) == data) {
               return p;
           }
           p = p.getNode(i-1);
        }
        return null;
    }

}
