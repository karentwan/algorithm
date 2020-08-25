package cn.karent;
import cn.karent.ds.*;
import java.lang.Exception;
import cn.karent.sort.*;
import cn.karent.string.*;
import cn.karent.tree.*;
import cn.karent.other.*;
import cn.karent.graph.*;

class Data {
	int item;
	String name;
	public Data(int item, String name) {
		this.item = item;
		this.name = name;
	}
}

public class Main {
	
	public static void ds() throws Exception{
		int[] data = {49, 38, 65, 97, 76, 13, 27, 27, 37};
		Compare c = new Compare() {
			@Override
			public int compare(Object o1, Object o2) {
				Integer item1 = (Integer)o1;
				Integer item2 = (Integer)o2;
				return item2 - item1;
			}
			
		};
//		Stack<Integer> s = new Stack<Integer>();
//		for(int i = 0; i < data.length; i++) {
//			s.push(data[i]);
//		}
//		while( !s.isEmpty() ) {
//			System.out.print(s.pop() + " ");
//		}
//		Queue<Integer> q = new Queue<Integer>();
//		q.enqueue(1);
//		q.enqueue(1);
//		q.dequeue();
//		q.dequeue();
//		for( int i = 0; i < data.length; i++) {
//			q.enqueue(data[i]);
//		}
//		while( !q.isEmpty() ) {
//			System.out.print(q.dequeue() + " ");
//		}
//		LinkQueue<Integer> q = new LinkQueue<Integer>();
//		q.enqueue(1);
//		q.enqueue(1);
//		q.dequeue();
//		q.dequeue();
//		for( int i = 0; i < data.length; i++) {
//			q.enqueue(data[i]);
//		}
//		while( !q.isEmpty() ) {
//			System.out.print(q.dequeue() + " ");
//		}
//		LinkStack<Integer> q = new LinkStack<Integer>();
//		for( int i = 0; i < data.length; i++) {
//			q.push(data[i]);
//		}
//		while( !q.isEmpty() ) {
//			System.out.print(q.pop() + " ");
//		}
		PriorQueue<Integer> p= new PriorQueue<Integer>(c);
		for(int i = 0; i < data.length; i++) {
			p.enqueue(data[i]);
		}
		while( !p.isEmpty() ) {
			System.out.print(p.dequeue() + " ");
		}
//		DeQueue<Integer> d = new DeQueue<Integer>();
//		for(int i = 0; i < data.length; i++) {
////			System.out.print(i);
//			d.enqueue(data[i]);
//		}
//		while( !d.isEmpty() ) {
//			System.out.print(d.dequeue() + " ");
//		}
//		for(int i = 0; i < data.length; i++) {
////			System.out.print(i);
//			d.push(data[i]);
//		}
//		while( !d.isEmpty() ) {
//			System.out.print(d.pop() + " ");
//		}
	}
	
	public static void print(Integer[] data) {
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println("\n");
	}
	
	public static void printData(Data[] data) {
		for(int i = 0; i < data.length; i++) {
			System.out.print("(" + data[i].item + "," + data[i].name + ") ");
		}
		System.out.println();
	}
	
	public static void sort() {
//		Integer[] data = {49, 38, 65, 97, 76, 13, 27, 49, 37};
//		Data[] d = {new Data(49, "a"), new Data(38, "b"), new Data(65, "c"),
//				new Data(97, "xiao"), new Data(76, "hsd"), new Data(13, "ha"),
//				new Data(27, "xi"), new Data(37, "tt")};
//		Compare cd = new Compare() {
//
//			@Override
//			public int compare(Object o1, Object o2) {
//				Data d1 = (Data)o1;
//				Data d2 = (Data)o2;
//				return d1.item - d2.item;
//			}
//		};
//		Compare c = new Compare() {
//
//			@Override
//			public int compare(Object o1, Object o2) {
//				Integer item1 = (Integer)o1;
//				Integer item2 = (Integer)o2;
//				return item2 - item1;
//			}
//		};
		// 冒泡排序
//		BubbleSort<Integer> b1 = new BubbleSort<Integer>(c);
//		print(data);
//		b1.sort(data);
//		print(data);
//		SelectSort<Integer> s = new SelectSort<Integer>(c);
//		print(data);
//		s.sort(data);
//		print(data);
//		InsertSort<Integer> i = new InsertSort<Integer>();
//		print(data);
//		i.sort(data);
//		print(data);
//		ShellSort<Integer> s = new ShellSort<Integer>();
//		print(data);
//		s.sort(data);
//		print(data);
//		Sort<Integer> q = new QuickSort<Integer>(c);
//		print(data);
//		q.sort(data);
//		print(data);
//		HeapSort<Integer> h = new HeapSort<Integer>(c);
//		print(data);
//		h.sort(data);
//		print(data);
//		HeapSort<Data> h = new HeapSort<Data>(cd);
//		printData(d);
//		h.sort(d);
//		printData(d);
//		BucketSort<Integer> b = new BucketSort<Integer>();
//		print(data);
//		b.sort(data);
//		print(data);
//		MergeSort<Integer> m = new MergeSort<Integer>(c);
//		print(data);
//		m.sort(data);
//		print(data);
		BitmapSort bs = new BitmapSort();
		int[] arrs = {9, 5, 7, 8, 12, 32, 6, 4, 1, 2, 3};
		bs.sort(arrs);
	}
	
	public static void str() {
//		KMP k = new KMP("abd");
//		int loc = k.match("abaabcac");
//		System.out.println(loc);
		Manacher m = new Manacher();
		int result = m.palindrome("abbababa");
		System.out.println(result);
		result = m.palindrome("abcbbabbc");
		System.out.println(result);
		result = m.palindrome("abccbaba");
		System.out.println(result);
	}
	
	public static BinaryTreeNode<Character> createCharacterTree() {
		BinaryTreeNode<Character> A = new BinaryTreeNode<Character>('A');
		BinaryTreeNode<Character> B = new BinaryTreeNode<Character>('B');
		BinaryTreeNode<Character> C = new BinaryTreeNode<Character>('C');
		BinaryTreeNode<Character> D = new BinaryTreeNode<Character>('D');
		BinaryTreeNode<Character> E = new BinaryTreeNode<Character>('E');
		BinaryTreeNode<Character> F = new BinaryTreeNode<Character>('F');
		BinaryTreeNode<Character> G = new BinaryTreeNode<Character>('G');
		BinaryTreeNode<Character> H = new BinaryTreeNode<Character>('H');
		A.lchild = B;
		A.rchild = F;
		F.rchild = G;
		B.lchild = C;
		B.rchild = D;
		D.lchild = E;
		D.rchild = H;
		return A;
	}
	
	public static void tree() {
//		int[] data = {7, 5, 2, 4, 7, 5};
//		HuffmanTree h = new HuffmanTree(data);
//		h.generateHuffmanTree();
//		BinaryTreeNode<Character> root = createCharacterTree();
//		BinaryTree tree = new BinaryTree();
//		tree.preOrderTraverseRecursion(root);
//		tree.inOrderTraverseRecursion(root);
//		tree.postOrderTraverseRecursion(root);
//		tree.preOrderTraverse(root);
//		tree.inOrderTraverse(root);
//		tree.postOrderTraverse(root);
//		AVLTree balanceTree = new AVLTree();
//		AVLTree.AVLNode root = null;
//		root = balanceTree.insert(root, 12);
//		root = balanceTree.insert(root, 75);
//		root = balanceTree.insert(root, 62);
//		root = balanceTree.insert(root, 88);
//		root = balanceTree.insert(root, 50);
//		root = balanceTree.insert(root, 25);
//		root = balanceTree.insert(root, 37);
//		balanceTree.preTraverse(root);
//		RedBlackBST<Integer> rbTree = new RedBlackBST<Integer>();
//		RedBlackBST<Integer>.RedBlackNode root = null;
//		root = rbTree.insert(root, 1);
//		root = rbTree.insert(root, 5);
//		root = rbTree.insert(root, 6);
//		root = rbTree.insert(root, 7);
//		root = rbTree.insert(root, 12);
//		root = rbTree.insert(root, 13);
//		root = rbTree.insert(root, 19);
//		root = rbTree.insert(root, 30);
//		root = rbTree.insert(root, 35);
//		root = rbTree.insert(root, 19);
//		root = rbTree.insert(root, 5);
//		root = rbTree.insert(root, 30);
//		root = rbTree.insert(root, 1);
//		root = rbTree.insert(root, 12);
//		root = rbTree.insert(root, 35);
//		root = rbTree.insert(root, 7);
//		root = rbTree.insert(root, 13);
//		root = rbTree.insert(root, 6);
//		rbTree.preOrderTraverse(root);
//		System.out.println();
//		rbTree.inOrderTraverse(root);
//		int[] nums = {7, 5, 3, 9, 1, 2, 8, 6, 4};
//		SegmentTree st = new SegmentTree(nums);
//		int result = st.query(0, 3, 6);
//		System.out.println(result);
//		st.update(0, 5, 6, 3);
//		st.update(0, 4, 4, 1);
//		result = st.query(0, 3, 6);
//		System.out.println(result);
		LoserTree lt = new LoserTree();
		lt.loserTree();
	}
	
	public static void other() throws Exception{
//		Calc c = new Calc();
//		float result = c.calc("(4*(21.4+3.22))-80/20");
//		System.out.println("表达式求解的结果:" + result);
//		c.calc("123456789");
//		Hanoi h = new Hanoi();
//		h.hanoiRecursion(6, 'A', 'B', 'C');
//		System.out.println("===============================");
//		h.hanoi(6, 'A', 'B', 'C');
//		AStarSearch ass = new AStarSearch();
//		ass.runPath();
//		FindSwapNumber fs = new FindSwapNumber();
//		Integer[] nums = {1, 2, 7, 4, 5, 6, 3};
//		fs.swapNumber(nums);
//		print(nums);
		LRUCache lru = new LRUCache(5);
		lru.put("001", "用户1信息");
		lru.put("002", "用户1信息");
		lru.put("003", "用户1信息");
		lru.put("004", "用户1信息");
		lru.put("005", "用户1信息");
		System.out.println(lru.get("002"));
		lru.put("004", "用户2信息更新");
		lru.put("006", "用户6信息");
		System.out.println(lru.get("001"));
		System.out.println(lru.get("006"));
	}
	
	public static MGraph createGraph() {
		String[] n = {"V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8"};
//		int[] n = {1, 2, 3, 4, 5, 6, 7, 8};
		MGraph<String> graph = new MGraph<String>();
//		ALGraph<String> graph = new ALGraph<String>();
		// 插入顶点
		for(int i = 0; i < n.length; i++) {
			graph.insertVex(n[i]);
		}
		// 插入边
		graph.insertArc(0, 1, 1);
		graph.insertArc(0, 2, 1);
		graph.insertArc(1, 3, 1);
		graph.insertArc(1, 4, 1);
		graph.insertArc(2, 5, 1);
		graph.insertArc(2, 6, 1);
		graph.insertArc(3, 7, 1);
		graph.insertArc(4, 7, 1);
		graph.insertArc(5, 6, 1);
		return graph;
	}
	
	public static void graph() {
//		MGraph graph = createGraph();
//		ALGraph graph = createGraph();
//		graph.DFSTraverse(0);
//		System.out.println();
//		graph.BFSTraverse(0);
		GraphAlgorithm algo = new GraphAlgorithm();
//		algo.prim(0);
//		algo.toplogicalSort();
		algo.dijkstra(0);
	}
	
	public static void main(String[] args) throws Exception{
//		sort();
//		ds();
		str();
//		tree();
//		other();
//		graph();
	}
}
