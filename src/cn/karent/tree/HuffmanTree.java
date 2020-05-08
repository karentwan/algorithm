package cn.karent.tree;
import cn.karent.ds.PriorQueue;
import cn.karent.sort.Compare;

public class HuffmanTree {
	
	class Node {
		int lchild;
		int rchild;
		int parent;
		int data;
	}
	
	class QueNode {
		int i;
		Node node;
		public QueNode(int i, Node node) {
			this.i = i;
			this.node = node;
		}
	}
	
	private int[] data;
	
	private Node[] tree;
	
	public HuffmanTree(int[] data) {
		this.data = data;
		int n = data.length;
		tree = new Node[2 * n - 1];
	}
	
	public void generateHuffmanTree() {
		PriorQueue<QueNode> q = new PriorQueue<QueNode>(new Compare() {
			@Override
			public int compare(Object o1, Object o2) {
				Node item1 = ((QueNode)o1).node;
				Node item2 = ((QueNode)o2).node;
				return item1.data - item2.data;
			}
			
		});
		for(int i = 0; i < data.length; i++) {
			Node n = new Node();
			n.data = data[i];
			tree[i] = n;
			q.enqueue(new QueNode(i, n));
		}
		for(int i = data.length; i < tree.length; i++) {
			QueNode lchild = q.dequeue();
			QueNode rchild = q.dequeue();
			Node p = new Node();
			tree[i] = p;
			p.parent = -1;
			p.lchild = lchild.i;
			p.rchild = rchild.i;
			lchild.node.parent = i;
			rchild.node.parent = i;
			p.data = lchild.node.data + rchild.node.data;
			q.enqueue(new QueNode(i, p));
		}
//		从叶子节点到根节点求树路径
		for(int i = 0; i < data.length; i++) {
			int p = i;
			while( p != -1) {
				Node n = tree[p];
				System.out.print(n.data + " ");
				p = n.parent;
			}
			System.out.println();
		}
	}

}
