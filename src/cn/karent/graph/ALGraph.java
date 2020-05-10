package cn.karent.graph;


/**
 * 邻接表
 * @author wan
 */
public class ALGraph {
	int m, n;
	VertexNode[] vertex;
	
	public ALGraph(int m, int n) {
		this.m = m;
		this.n = n;
		this.vertex = new VertexNode[m];
	}
}

class AdjNode {
	int data;
	AdjNode next;
}

class VertexNode {
	int data;
	AdjNode firstNode;
}
