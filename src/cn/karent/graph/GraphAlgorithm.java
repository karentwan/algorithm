package cn.karent.graph;
import cn.karent.ds.Stack;


public class GraphAlgorithm {
	
	
	private MGraph<String> createGraph() {
		String[] node = {"V1", "V2", "V3", "V4", "V5", "V6"};
		MGraph<String> graph = new MGraph<String>();
		for(int i = 0; i < node.length; i++) {
			graph.insertVex(node[i]);
		}
		graph.insertArc(0, 1, 6);
		graph.insertArc(0, 2, 1);
		graph.insertArc(0, 3, 5);
		graph.insertArc(1, 2, 5);
		graph.insertArc(1, 4, 3);
		graph.insertArc(2, 3, 5);
		graph.insertArc(2, 4, 6);
		graph.insertArc(2, 5, 4);
		graph.insertArc(3, 5, 2);
		graph.insertArc(4, 5, 6);
		return graph;
	}
	
	final int INFINITY = 65535;
	
	private int findMin(VexArcPair[] lowest) {
		int min = INFINITY;
		int index = -1;
		for(int i = 0; i < lowest.length; i++) {
			if(lowest[i].weight != 0 && lowest[i].weight < min) {
				min = lowest[i].weight; 
				index = i;
			}
		}
		return index;
	}
	
	class VexArcPair {
		int parent; 
		int weight;
		
		public VexArcPair() {
			
		}
		
		public VexArcPair(int parent, int weight) {
			this.parent = parent;
			this.weight = weight;
		}
	}
	
	/**
	 * 普利姆最小生成树算法
	 */
	public void prim(int u) {
		MGraph<String> graph = createGraph();
		int n = graph.getVexNumber();
		VexArcPair[] lowest = new VexArcPair[n];
		lowest[u] = new VexArcPair(u, 0);
		for(int i = 0; i < n; i++) {
			if( i == u)
				continue;
			lowest[i] = new VexArcPair(u, graph.getArcWeight(u, i));
			if( lowest[i].weight == 0 ) {
				lowest[i].weight = INFINITY;
			} 
		}
		int w = 0;
		while( (w = findMin(lowest)) != -1 ) {
			System.out.print("(" + (lowest[w].parent+1) + ", " + (w+1) + ", " + graph.getArcWeight(lowest[w].parent, w) + ") ");
			lowest[w].weight = 0;
			// 更新lowest
			for(int i = graph.firstAdjVex(w); i != -1; i = graph.nextAdjVex(w, i)) {
				int weight = graph.getArcWeight(w, i);
				// lowest[i].weight != 0 代表该节点不在集合U中(U是生成树的节点, S是未加入进来的节点)
				if( lowest[i].weight != 0 && lowest[i].weight > weight) {
					lowest[i].weight = weight;
					lowest[i].parent = w;  
				}
			}
		}
	}
	
	public MGraph<String> createTopSortGraph() {
		MGraph<String> graph = new MGraph<String>();
		graph.setDirected(true);
		String[] node = {"V1", "V2", "V3", "V4", "V5", "v6"};
		for(int i = 0; i < node.length; i++) {
			graph.insertVex(node[i]);
		}
		graph.insertArc(0, 1, 1);
		graph.insertArc(0, 2, 1);
		graph.insertArc(0, 3, 1);
		graph.insertArc(2, 1, 1);
		graph.insertArc(2, 4, 1);
		graph.insertArc(3, 4, 1);
		graph.insertArc(5, 3, 1);
		graph.insertArc(5, 4, 1);
//		graph.insertArc(4, 5, 1);
		return graph;
	}
	
	/**
	 * 拓扑排序, 判断图是否有环
	 */
	public void toplogicalSort() {
		MGraph<String> graph = createTopSortGraph();
		int n = graph.getVexNumber();
		int[] indegree = new int[n];
		// 初始化入度数组
		for(int i = 0; i < n; i++) {
			for(int j = graph.firstAdjVex(i); j != -1; j = graph.nextAdjVex(i, j)) {
				indegree[j]++;
			}
		}
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < indegree.length; i++) {
			if( indegree[i] == 0) {
				stack.push(i);
			}
		}
		int count = 0;
		while( !stack.isEmpty() ) {
			int v = stack.pop();
			count++;
			for(int i = graph.firstAdjVex(v); i != -1; i = graph.nextAdjVex(v, i)) {
				indegree[i]--;
				if( indegree[i] == 0) {
					stack.push(i);
				}
			}
		}
		if( count >= n ) {
			System.out.println("图没环");
		} else {
			System.out.println("图有环");
		}
	}
	
	private MGraph<String> createMinPathGraph() {
		MGraph<String> graph = new MGraph<String>();
		graph.setDirected(true);
		String[] node = {"V0", "V1", "V2", "V3", "v4", "v5"};
		for(int i = 0; i < node.length; i++) {
			graph.insertVex(node[i]);
		}
		graph.insertArc(0, 2, 10);
		graph.insertArc(0, 4, 30);
		graph.insertArc(0, 5, 100);
		graph.insertArc(1, 2, 5);
		graph.insertArc(2, 3, 50);
		graph.insertArc(3, 5, 10);
		graph.insertArc(4, 3, 20);
		graph.insertArc(4, 5, 60);
		return graph;
	}
	
	/**
	 * 迪杰斯特拉算法, 最短路径
	 */
	public void dijkstra(int v) {
		MGraph<String> graph = createMinPathGraph();
		int n = graph.getVexNumber();
		// parent保存v节点到该节点的的直接父类, weight是直接父类到该节点的权重
		VexArcPair[] path = new VexArcPair[n];
		path[v] = new VexArcPair(-1, 0);
		// 同path一样, parent保存该节点的直接父类, 但是weight确是从v到该节点的权重累加和
		VexArcPair[] lowest = new VexArcPair[n];
		lowest[v] = new VexArcPair(-1, 0);
		for(int i = 0; i < n; i++) {
			if( i != v) {
				lowest[i] =new VexArcPair(v, graph.getArcWeight(v, i));
				if( lowest[i].weight == 0) 
					lowest[i].weight = INFINITY;
			}
		}
		int w = 0;
		int k = v; // 保存顶点v的值
		while((w = findMin(lowest)) != -1) {
			v = lowest[w].parent;
			int weight = graph.getArcWeight(v, w);
			path[w] = new VexArcPair(v, weight);
			for(int j = graph.firstAdjVex(w); j != -1; j = graph.nextAdjVex(w, j)) {
				if( lowest[w].weight + graph.getArcWeight(w, j) < lowest[j].weight) {
					lowest[j].weight = lowest[w].weight + graph.getArcWeight(w, j);
					lowest[j].parent = w;
				}
			}
			lowest[w].weight = 0;  // lowest[w].weight设为0代表已经找到v到w的最短路径了
		}
		v = k;
		for(int i = 0; i < n; i++) {
			if(i != v && path[i] != null) {
				int p = path[i].parent;
				System.out.print(graph.get(i) + " weight:" + path[i].weight + " ");
				while( p != -1 ) {
					System.out.print(graph.get(p) + " weight:" + path[i].weight + " ");
					p = path[p].parent;
				}
				System.out.println();
			}
		}
	}
}
