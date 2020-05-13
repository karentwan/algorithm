package cn.karent.graph;
import cn.karent.ds.LinkQueue;

class ArcNode {
	int number;   // 顶点编号
	ArcNode next; // 下一条弧
}

class VertexNode {
	Object data;
	ArcNode firstNode;
}

/**
 * 邻接表
 * @author wan
 */
public class ALGraph<T> {

	private int n, e;
	
	private VertexNode[] vertex;
	
	private int capcity = 20;
	
	private final static int EXPAND = 20;
	
	public ALGraph() {
		this.vertex = new VertexNode[capcity];
	}
	
	private void grow() {
		VertexNode[] tmp = vertex;
		vertex = new VertexNode[capcity + EXPAND];
		for(int i = 0; i < capcity; i++) {
			vertex[i] = tmp[i];
		}
	}
	
	public void insertVex(T v) {
		if( n >= capcity ) 
			grow();
		VertexNode node = new VertexNode();
		node.data = v;
		vertex[n++] = node;
	}
	
	public void insertArc(int v, int w) {
		if( v >= n || w >= n) {
			System.out.println("没有该节点");
			return;
		}
		ArcNode in = new ArcNode();
		in.number = w;
		ArcNode out = new ArcNode();
		out.number = v;
		in.next = vertex[v].firstNode;
		vertex[v].firstNode = in;
		out.next = vertex[w].firstNode;
		vertex[w].firstNode = out;
		e++;
	}
	
	public ArcNode firstArc(int v) {
		return vertex[v].firstNode;
	}
	
	public ArcNode nextArc(ArcNode node) {
		return node.next;
	}
	
	private void DFS(int v, int[] visited) {
		System.out.print(vertex[v].data + " ");
		visited[v] = 1;
		for(ArcNode node = vertex[v].firstNode; node != null; node = nextArc(node)) {
			if( visited[node.number] == 0) {
				DFS(node.number, visited);
			}
		}
	}
	
	/**
	 * 深度优先搜索
	 * @param v
	 */
	public void DFSTraverse(int v) {
		int[] visited = new int[n];
		DFS(v, visited);
	}
	
	public void BFSTraverse(int v) {
		LinkQueue<Integer> q = new LinkQueue<Integer>();
		int[] visited = new int[n];
		q.enqueue(v);
		visited[v] = 1;
		while( !q.isEmpty() ) {
			int node = q.dequeue();
			System.out.print(vertex[node].data + " ");
			for(ArcNode p = vertex[node].firstNode; p != null; p = nextArc(p)) {
				node = p.number;
				if( visited[node] == 0) {
					q.enqueue(node);
					visited[node] = 1;
				}
			}
		}
	}
}

