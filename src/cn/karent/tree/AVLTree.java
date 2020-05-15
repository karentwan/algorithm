package cn.karent.tree;

/**
 * 二叉平衡树
 * @author wan
 *
 */
public class AVLTree {
	
	private enum Factor {
		LEFT, EQ, RIGHT
	}
	
	public class AVLNode {
		int data;               // 数据
		AVLNode lchild; // 左子树 
		AVLNode rchild; // 右子树
		Factor balanceFactor = Factor.EQ;      // 平衡因子
		
		public AVLNode() {}
		
		public AVLNode(int data) {
			this.data = data;
		}
	}
	
	/**
	 * 左旋
	 * @param node
	 * @return
	 */
	private AVLNode leftRotate(AVLNode node) {
		AVLNode rchild = node.rchild;
		node.rchild = rchild.lchild;
		rchild.lchild = node;
		return rchild;
	}
	
	/**
	 * 右旋
	 * @param node
	 * @return
	 */
	private AVLNode rightRotate(AVLNode node) {
		AVLNode lchild = node.lchild;
		node.lchild = lchild.rchild;
		lchild.rchild = node;
		return lchild;
	}
	
	private AVLNode leftBalance(AVLNode node) {
		AVLNode B = node.lchild;
		AVLNode result = null;
		switch( B.balanceFactor ) {
		case LEFT: // LL
			node.balanceFactor = Factor.EQ;
			B.balanceFactor = Factor.EQ;
			result = rightRotate(node);
			break;
		case RIGHT:  // LR
			// 先左旋
			AVLNode C = B.rchild;
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
	
	private AVLNode rightBalance(AVLNode node) {
		AVLNode B = node.rchild;
		AVLNode result = null;
		switch( B.balanceFactor ) {
		case LEFT: // RL
			AVLNode C = B.lchild;
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
		case RIGHT: // RR
			node.balanceFactor = Factor.EQ;
			B.balanceFactor = Factor.EQ;
			result = leftRotate(node);
			break;
		}
		return result;
	}
	
	private boolean taller = false;  // 判断树是否长高高
	
	private AVLNode result;   // 记录每次返回的结果
	
	/**
	 * 插入, 每次都返回当前的元素
	 * @param node
	 * @param data
	 * @return 当前节点
	 */
	public AVLNode insert(AVLNode node, int data) {
		// 边界条件
		if( node == null) {
			AVLNode n = new AVLNode(data);
			taller = true;
			result = n;
			return n;
		}
		if( data == node.data) {
			taller = false;
			result = node;
			return node;
		} else if( data < node.data ){  // 应该插入左子树
			AVLNode n = insert(node.lchild, data);
			node.lchild = n;
			n = node;
			if( taller ) { // 如果左子树长高了，此时就应该分类讨论是否要旋转
				switch( node.balanceFactor ) {
				case LEFT:  
					n = leftBalance(node);
					taller = false;
					break;
				case EQ:  // 左子树长高, 那么当前子树就不需要调整, 应该向上
					node.balanceFactor = Factor.LEFT;
					break;
				case RIGHT:
					node.balanceFactor = Factor.EQ;
					taller = false;
					break;
				}
			} 
			return n;
		} else { // 应该插入右子树
			AVLNode n = insert(node.rchild, data);
			node.rchild = n;
			n = node;
			if( taller ) {
				switch( node.balanceFactor ) {
				case LEFT: 
					node.balanceFactor = Factor.EQ;
					taller = false;
					break;
				case EQ:
					node.balanceFactor = Factor.RIGHT;
					break;
				case RIGHT: 
					n = rightBalance(node);
					taller = false;
					break;
				}
			} 
			return n;
		}
	}
	
	public void preTraverse(AVLNode node) {
		if( node == null)
			return;
		System.out.print(node.data + " ");
		preTraverse(node.lchild);
		preTraverse(node.rchild);
	}
	
}
