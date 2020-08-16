package cn.karent.tree;

/**
 * 区间求和线段树
 * @author wan
 */
public class SegmentTree {

    private int[] data;

    class Node {
        public int l, r;  // 左右区间
        public int val;   // 值
        public int lazy;  // 懒标记
        public Node() {

        }

        public Node(int l, int r, int val) {
            this.l = l;
            this.r = r;
            this.val = val;
        }
    }

    private Node[] nodes;

    public SegmentTree(int[] data) {
        this.data = data;
        nodes = new Node[data.length * 4];
        build(0, 0, data.length-1);
    }

    public void pushUp(int node, int l, int r) {
        nodes[node].l = l;
        nodes[node].r = r;
        nodes[node].val = nodes[node * 2+1].val + nodes[node*2+2].val;
    }

    /**
     * 构建线段树, 使用完全二叉树存储
     * @param node 下标
     * @param l    左区间
     * @param r    右区间
     */
    public void build(int node, int l, int r) {
        if( l == r) {
            nodes[node] = new Node(l, r, this.data[l]);
            return;
        }
        int mid = (l+r)/2;
        build(node * 2 + 1, l, mid);
        build(node*2+2, mid+1, r);
        nodes[node] = new Node();
        pushUp(node, l, r);
    }

    public void pushDown(int node, int nl, int nr, int l, int r) {
        Node cur = nodes[node];
        int mid = (cur.r + cur.l)/2;
        Node left = nodes[node * 2 + 1];
        left.lazy += cur.lazy;
        left.val += (left.r - left.l + 1) * cur.lazy;
        Node right = nodes[node * 2 + 2];
        right.lazy += cur.lazy;
        right.val += (right.r - right.l + 1) * cur.lazy;
        cur.lazy = 0;  // 当前懒标记清零
    }

    /**
     * 区间修改, 从[l, r]这个区间内每个元素加val
     * @param l
     * @param r
     * @param val
     */
    public void update(int node, int l, int r, int val) {
        Node cur = nodes[node];
        if( l > cur.r || r < cur.l) {
            return;
        }
        if( l <= cur.l && r >= cur.r) {
            nodes[node].val += (cur.r - cur.l + 1) * val;
            nodes[node].lazy = val;
            return;
        }
        pushDown(node, cur.l, cur.r, l, r);
        int mid = ( cur.l + cur.r) / 2;
        update(node * 2 + 1, l, r, val);
        update(node * 2 + 2, l, r, val);
        pushUp(node, cur.l, cur.r);
    }

    /**
     * 查询
     * @param node
     * @param l
     * @param r
     * @return
     */
    public int query(int node, int l, int r) {
        Node cur = nodes[node];
        if( l > cur.r || r < cur.l) {
            return 0;
        }
        if( l <= cur.l && r >= cur.r)  {
            return nodes[node].val;
        }
        // 这个pushDown是传以前的lazy而不是当前的lazy
        pushDown(node, cur.l, cur.l, l, r);
        int result = 0;
        result = query(node * 2 + 1, l, r);
        result += query(node*2 + 2, l, r);
        return result;
    }

}
