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
		int vex;
		int weight;
		
		public VexArcPair() {
			
		}
		
		public VexArcPair(int vex, int weight) {
			this.vex = vex;
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
			System.out.print("(" + (lowest[w].vex+1) + ", " + (w+1) + ", " + graph.getArcWeight(lowest[w].vex, w) + ") ");
			lowest[w].weight = 0;
			// 更新lowest
			for(int i = 0; i < n; i++) {
				int weight = graph.getArcWeight(w, i);
				if( lowest[i].weight != 0 && weight != 0 && lowest[i].weight > weight) {
					lowest[i].weight = weight;
					lowest[i].vex = w;
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
	
	/**
	 * 地杰斯特拉算法, 最短路径
	 */
	public void dijkstra() {
		
	}
	
	
	
	
	
	
	
	

}
