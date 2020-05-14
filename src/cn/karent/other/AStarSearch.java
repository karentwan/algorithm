package cn.karent.other;
import cn.karent.ds.LinkList;

/**
 * A星寻路算法
 * @author wan
 *
 */
public class AStarSearch {
	
	class Grid {
		int g;   // g值：已经走过多少路 
		int h;   // h值：到终点还有多少路
		int f;   // g + h：代表从起点经过该点到终点一共走了多少路
		int x, y;
		Grid parent;
		public Grid() {}
		
		public Grid(int g, int h, int x, int y, Grid parent) {
			this.g = g;
			this.h = h;
			this.f = g + h;
			this.x = x;
			this.y = y;
			this.parent = parent;
		}
		
		@Override
		public boolean equals(Object obj) {
			if( this == obj)
				return true;
			if( obj == null)
				return false;
			Grid g = (Grid)obj;
			if( g.x == this.x && g.y == this.y)
				return true;
			return false;
		}
	}
	
	public static final int[][] MAZE = {
			{0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0},
	};
	
	private LinkList<Grid> openList = new LinkList<Grid>();
	
	private LinkList<Grid> closeList = new LinkList<Grid>();
	
	private Grid end = null;
	
	private boolean canAdd(Grid item) {
		// 判断坐标是否越界
		if( item.x < 0 || item.x >= 7)
			return false;
		if( item.y < 0 || item.y >= 5)
			return false;
		// 判断前进方向是否有墙
		if( MAZE[item.y][item.x] == 1)
			return false;
		// 判断要加入openList的路径是否已经走过
		if( closeList.exist(item) )
			return false;
		// 判断要加入openList的路径是否与openList本身存在的重复
		if( openList.exist(item) )
			return false;
		return true;
	}

	private int restPath(int x, int y) {
		return Math.abs(x - end.x) + Math.abs(y - end.y);
	}
	
	private Grid getMinF() {
		LinkList<Grid>.Iterator iter = openList.iterator();
		Grid data = iter.next();
		int minF = data.f;
		Grid p = iter.next();
		while( p != null) {
			if( p.f < minF) {
				minF = p.f;
				data = p;
			}
			p = iter.next();
		}
		openList.remove(data);
		return data;
	}
	
	private void enOpenList(Grid p) {
		// 这里就是上下左右四个位置, 看看能不能是不是存在于closeList或者是不是有墙
		Grid top = new Grid(p.g + 1, restPath(p.x, p.y-1), p.x, p.y-1, p);
		Grid bottom = new Grid(p.g + 1, restPath(p.x, p.y+1), p.x, p.y+1, p);
		Grid left = new Grid(p.g + 1, restPath(p.x-1, p.y), p.x-1, p.y, p);
		Grid right = new Grid(p.g + 1, restPath(p.x+1, p.y), p.x+1, p.y, p);
		if( canAdd(top))
			openList.add(top);
		if( canAdd(bottom))
			openList.add(bottom);
		if( canAdd(left))
			openList.add(left);
		if( canAdd(right))
			openList.add(right);
	}
	
	/**
	 * 寻找一条从起点到终点的路
	 * @param start 起点
	 * @param end   终点
	 */
	public void searchPath(Grid start, Grid end) {
		this.end = end;
		Grid p = start;
		do {
			closeList.add(p);
			enOpenList(p);
			p = getMinF();
		}while(p.x != end.x || p.y != end.y);
		// 逆序打印这条路径
		while( p != null) {
			System.out.println("(" + p.x + ", " + p.y + ")");
			p = p.parent;
		}
	}
	
	public void runPath() {
		Grid start = new Grid(0, 0, 1, 2, null);
		Grid end = new Grid(0, 0, 5, 2, null);
		searchPath(start, end);
	}
	
}
