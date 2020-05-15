package cn.karent.tree;

/**
 * 二叉平衡树
 * @author wan
 *
 */
public class BalanceTree {
	
	private enum Factor {
		LEFT, EQ, RIGHT
	}
	
	public class BalanceTreeNode {
		int data;               // 数据
		BalanceTreeNode lchild; // 左子树 
		BalanceTreeNode rchild; // 右子树
		Factor balanceFactor = Factor.EQ;      // 平衡因子
		
		public BalanceTreeNode() {}
		
		public BalanceTreeNode(int data) {
			this.data = data;
		}
	}
	
	/**
	 * 左旋
	 * @param node
	 * @return
	 */
	private BalanceTreeNode leftRotate(BalanceTreeNode node) {
		BalanceTreeNode rchild = node.rchild;
		node.rchild = rchild.lchild;
		rchild.lchild = node;
		return rchild;
	}
	
	/**
	 * 右旋
	 * @param node
	 * @return
	 */
	private BalanceTreeNode rightRotate(BalanceTreeNode node) {
		BalanceTreeNode lchild = node.lchild;
		node.lchild = lchild.rchild;
		lchild.rchild = node;
		return lchild;
	}
	
	private BalanceTreeNode leftBalance(BalanceTreeNode node) {
		BalanceTreeNode B = node.lchild;
		BalanceTreeNode result = null;
		switch( B.balanceFactor ) {
		case LEFT:
			node.balanceFactor = Factor.EQ;
			B.balanceFactor = Factor.EQ;
			result = rightRotate(node);
			break;
		case RIGHT:  // LR
			// 先左旋
			BalanceTreeNode C = B.rchild;
			switch( C.balanceFactor ) {
			case LEFT:
				B.balanceFactor = Factor.EQ;
				C.balanceFactor = Factor.EQ;
				node.balanceFactor = Factor.RIGHT;
				break;
			case RIGHT:
				B.balanceFactor = Factor.LEFT;
				C.balanceFactor = Factor.EQ;
				node.balanceFactor = Factor.EQ;
				break;
			case EQ:
				B.balanceFactor = Factor.EQ;
				C.balanceFactor = Factor.EQ;
				node.balanceFactor = Factor.EQ;
				break;
			}
			// 先左旋后右旋
			result = leftRotate(B);
			node.lchild = result;
			result = rightRotate(node);
			break;
		}
		return result;
	}
	
	private BalanceTreeNode rightBalance(BalanceTreeNode node) {
		BalanceTreeNode B = node.rchild;
		BalanceTreeNode result = null;
		switch( B.balanceFactor ) {
		case LEFT: // RL
			BalanceTreeNode C = B.lchild;
			switch( C.balanceFactor ) {
			case LEFT:
				C.balanceFactor = Factor.EQ;
				node.balanceFactor = Factor.EQ;
				B.balanceFactor = Factor.RIGHT;
				break;
			case RIGHT:
				C.balanceFactor = Factor.EQ;
				node.balanceFactor = Factor.LEFT;
				B.balanceFactor = Factor.EQ;
				break;
			case EQ:
				C.balanceFactor = Factor.EQ;
				node.balanceFactor = Factor.EQ;
				B.balanceFactor = Factor.EQ;
				break;
			}
			// 先右旋再左旋
			result = rightRotate(B);
			node.rchild = result;
			result = leftRotate(node);
			break;
		case RIGHT:
			node.balanceFactor = Factor.EQ;
			B.balanceFactor = Factor.EQ;
			result = leftRotate(node);
			break;
		}
		return result;
	}
	
	private boolean taller = false;  // 判断树是否长高高
	
	private BalanceTreeNode result;   // 记录每次返回的结果
	
	/**
	 * 插入, 每次都返回当前的元素
	 * @param node
	 * @param data
	 * @return 当前节点
	 */
	public BalanceTreeNode insert(BalanceTreeNode node, int data) {
		// 边界条件
		if( node == null) {
			BalanceTreeNode n = new BalanceTreeNode(data);
			taller = true;
			result = n;
			return n;
		}
		if( data == node.data) {
			taller = false;
			result = node;
			return node;
		} else if( data < node.data ){  // 应该插入左子树
			BalanceTreeNode n = insert(node.lchild, data);
			node.lchild = n;
			n = node;
			if( taller ) { // 如果树左子树长高了，此时就应该分类讨论是否要旋转
				switch( node.balanceFactor ) {
				case LEFT:  // LL, 应该直接右旋
					n = leftBalance(node);
					taller = false;
					break;
				case EQ:  // 左子树长高, 那么当前子树就不需要调整, 应该向上
					node.balanceFactor = Factor.LEFT;
					n = node;
					break;
				case RIGHT:
					node.balanceFactor = Factor.EQ;
					taller = false;
					n = node;
					break;
				}
			} 
			return n;
		} else { // 应该插入右子树
			BalanceTreeNode n = insert(node.rchild, data);
			node.rchild = n;
			n = node;
			if( taller ) {
				switch( node.balanceFactor ) {
				case LEFT: 
					node.balanceFactor = Factor.EQ;
					n = node;
					taller = false;
					break;
				case EQ:
					node.balanceFactor = Factor.RIGHT;
					n = node;
					break;
				case RIGHT:  // RR
					n = rightBalance(node);
					taller = false;
					break;
				}
			} 
			return n;
		}
	}
	
	public void preTraverse(BalanceTreeNode node) {
		if( node == null)
			return;
		System.out.print(node.data + " ");
		preTraverse(node.lchild);
		preTraverse(node.rchild);
	}
	
}
