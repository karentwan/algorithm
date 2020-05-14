package cn.karent.other;
import cn.karent.ds.Stack;

/**
 * 汉诺塔
 * @author wan
 *
 */
public class Hanoi {
	
	private void move(char x, char y) {
		System.out.println("将一个盘子" + x + "移动到" + y);
	}
	
	/**
	 * n个盘子,从x借助y移动到z
	 * @param n
	 * @param x
	 * @param y
	 * @param z
	 */
	public void hanoiRecursion(int n, char x, char y, char z) {
		if( n == 1) {
			move(x, z);
			return;
		}
		hanoiRecursion(n-1, x, z, y);
		move(x, z);
		hanoiRecursion(n-1, y, x, z);
	}

	class Info {
		int n; 
		char x;
		char y;
		char z;
		public Info() {}
		
		public Info(int n, char x, char y, char z) {
			this.n = n;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	} 
	
	/**
	 * 以汉诺塔的递推式可以发现，本质上是一棵二叉树的中序遍历方式, 可以画出递归树
	 * 来帮助理解, 而递归树的每一个节点都是一个状态, 因此本身不是树结构但是使用
	 * 递归求解本质上都可以划分为求解状态空间
	 * @param n 要移动的多少个盘子
	 * @param x n个盘子存在的柱子
	 * @param y n个盘子移动过程中可以使用的柱子
	 * @param z n个盘子移动时的目标柱子
	 */
	public void hanoi(int n, char x, char y, char z) {
		Stack<Info> stack = new Stack<Info>();
		Info p = new Info(n, x, y, z);
		while( p != null || !stack.isEmpty() ) {
			while( p != null) {
				stack.push(p);
				if( p.n > 1) {
					p = new Info(p.n - 1, p.x, p.z, p.y);
				} else {
					p = null;
				}
			}
			p = stack.pop();
			move(p.x, p.z);
			if( p.n == 1)
				p = null;
			else
				p = new Info(p.n-1, p.y, p.x, p.z);
		}
	}
	
}
