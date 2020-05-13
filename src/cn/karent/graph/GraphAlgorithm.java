package cn.karent.graph;


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
	public void prim() {
		MGraph<String> graph = createGraph();
		int n = graph.getVexNumber();
		VexArcPair[] lowest = new VexArcPair[n];
		lowest[0] = new VexArcPair(0, 0);
		for(int i = 1; i < n; i++) {
			lowest[i] = new VexArcPair(0, graph.getArcWeight(0, i));
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
	
	
	
	
	
	
	
	
	
	
	

}
