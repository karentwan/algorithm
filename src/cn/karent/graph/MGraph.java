package cn.karent.graph;

/**
 * 图的定义，节点与节点之间的关系使用邻接矩阵保存
 * @author wan
 *
 */
public class MGraph {
	int m, n;  // 节点数量和弧的数量
	Adj[][] adj;
	Vertes[] vertes;
	
	public MGraph(int m, int n) {
		this.m = m;
		this.n = n;
		this.adj = new Adj[m][m];
		this.vertes = new Vertes[m];
	}
}

/**
 * 边节点
 * @author wan
 *
 */
class Adj {
	int data;
}

class Vertes {
	int data;
}