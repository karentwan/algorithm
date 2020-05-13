package cn.karent.graph;
import cn.karent.ds.LinkQueue;

class Vertes {  // 顶点节点
	Object data;
}

/**
 * 图的定义，节点与节点之间的关系使用邻接矩阵保存
 * @author wan
 *
 */
public class MGraph<T> {

	private int n, e;  // 节点数量和弧的数量
	
	private int[][] edges;
	
	private Vertes[] vertes;
	
	private final static int EXPAND = 20;
	
	private int capcity = 20;
	
	public MGraph() {
		n = 0;
		e = 0;
		this.edges = new int[capcity][capcity];
		this.vertes = new Vertes[capcity];
	}
	
	/**
	 * 保存图数据的容量增长
	 */
	public void grow() {
		Vertes[] tmp = vertes;
		vertes = new Vertes[capcity + EXPAND];
		for(int i = 0; i < capcity; i++) {
			vertes[i] = tmp[i];
		}
		int[][] tmpEdges = edges;
		edges = new int[capcity + EXPAND][capcity + EXPAND];
		for(int i = 0; i < capcity; i++) {
			for(int j = 0; j < capcity; j++) {
				edges[i][j] = tmpEdges[i][j];
			}
		}
		capcity += EXPAND;
	}
	
	/**
	 * 往图里面插入顶点
	 * @param v
	 */
	public void insertVex(T v) {
		if( n >= capcity ) 
			grow();
		Vertes node = new Vertes();
		node.data = v;
		vertes[n++] = node;
	}
	
	public void insertArc(int v, int w) {
		if( v >= n || w >= n) {
			System.out.println("没有这个节点");
			return;
		}
		edges[v][w] = 1;
		edges[w][v] = 1;
		e++;
	}
	
	/**
	 * 求出顶点v的第一条边
	 * @param v 顶点v
	 * @return 与顶点v相连的第一个顶点的坐标
	 */
	public int firstAdjVex(int v) {
		for(int i = 0; i < n; i++) {
			if( edges[v][i] != 0)
				return i;
		}
		return -1;
	}
	
	/**
	 * 求出与顶点v的邻接顶点并且是顶点w的下一个节点
	 * @param v 顶点v
	 * @param w 顶点w
	 * @return 与顶点v相连并且在顶点w的后一个顶点
	 */
	public int nextAdjVex(int v, int w) {
		for(int i = w+1; i < n; i++) {
			if( edges[v][i] != 0)
				return i;
		}
		return -1;
	}
	
	private T get(int v) {
		return (T) vertes[v].data;
	}
	
	private void DFS(int v, int[] visited) {
		System.out.print(get(v) + " ");
		visited[v] = 1;  // 访问节点
		for(int i = firstAdjVex(v); i != -1; i = nextAdjVex(v, i)) {
			if( visited[i] == 0) {
				DFS(i, visited);
			}
		}
	}
	
	/**
	 * 深度优先遍历
	 * @param v
	 */
	public void DFSTraverse(int v) {
		int[] visited = new int[n];
		DFS(v, visited);
	}
	
	/**
	 * 广度优先遍历
	 * @param v 要遍历的起始顶点
	 */
	public void BFSTraverse(int v) {
		LinkQueue<Integer> queue = new LinkQueue<Integer>();
		int[] visited = new int[n];
		queue.enqueue(v);
		visited[v] = 1;
		while( !queue.isEmpty() ) {
			int node = queue.dequeue();
			System.out.print(get(node) + " ");
			for(int i = firstAdjVex(node); i != -1; i = nextAdjVex(node, i)) {
				if( visited[i] == 0 ) {
					queue.enqueue(i);
					visited[i] = 1;
				}
			}
		}
	}
}

