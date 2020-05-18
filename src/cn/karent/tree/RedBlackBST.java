package cn.karent.tree;

/**
 * 红黑树
 * @author wan
 *
 */
public class RedBlackBST {
	
	private enum Color {
		RED, BLACK
	}
	
	private RedBlackNode NIL; // 所有新插入的节点的孩子节点都是这个节点
	
	public class RedBlackNode {
		Color color = Color.RED;
		RedBlackNode left;   // 左子树
		RedBlackNode right;  // 右子树
		RedBlackNode parent; // 双亲节点
		int data;
		
		public RedBlackNode() {}
		
		public RedBlackNode(int data) {
			this.data = data;
			left = NIL;
			right = NIL;
		}
	}
	
	public RedBlackBST() {
		NIL = new RedBlackNode();
		NIL.color = Color.BLACK;
	}
	
	/**
	 * 右旋
	 * @param A
	 * @return
	 */
	private RedBlackNode rightRotate(RedBlackNode A) {
		RedBlackNode B = A.left;
		B.parent = null;
		A.left = B.right;
		B.right = A;
		A.parent = B;
		return B;
	}
	
	/**
	 * 左旋
	 * @param A
	 * @return
	 */
	private RedBlackNode leftRotate(RedBlackNode A) {
		RedBlackNode B = A.right;
		B.parent = null;
		A.right = B.left;
		B.left = A;
		A.parent = B;
		return B;
	}
	
	/**
	 * 调整完毕后，返回根节点
	 * @param node
	 * @return
	 */
	private RedBlackNode nodeAdjust(RedBlackNode node) {
		RedBlackNode p = node;
		RedBlackNode parent = p.parent;
		RedBlackNode grandparent = null;
		while( parent != null && parent.color == Color.RED && parent.color == p.color) {
			grandparent = parent.parent;
			if( parent == grandparent.left) {  // 如果双亲节点是祖父节点的左子树
				if( grandparent.right.color == Color.RED) { // 只需要变色即可
					parent.color = Color.BLACK;
					grandparent.right.color = Color.BLACK;
					grandparent.color = Color.RED;
					p = grandparent;
					parent = p.parent;
				} else {  // 需要旋转
					// 如果是双亲节点的左孩子, 那么直接右旋即可
					if( parent.left == p) {
						parent.color = Color.BLACK;
						grandparent.color = Color.RED;
						parent = grandparent.parent;
						p = rightRotate(grandparent);
						if( parent != null ) {
							if( parent.left == grandparent) {
								parent.left = p;
								p.parent = parent;
							} else {
								parent.right = p;
								p.parent = parent;
							}
						}
					} else {  // 如果是双亲节点的右孩子, 那么需要先左旋再右旋
						p.color = Color.BLACK;
						grandparent.color = Color.RED;
						grandparent.left = leftRotate(parent);
						parent = grandparent.parent;
						p = rightRotate(grandparent);
						if( parent != null) {
							if( parent.left == grandparent) {
								parent.left = p;
								p.parent = parent;
							} else {
								parent.right = p;
								p.parent = parent;
							}
						}
					}
				}
			} else { // 如果双亲节点是祖父节点的右子树
				if( grandparent.left.color == Color.RED) { // 只需要变色即可
					parent.color = Color.BLACK;
					grandparent.left.color = Color.BLACK;
					grandparent.color = Color.RED;
					p = grandparent;
					parent = p.parent;
				} else {  // 需要旋转
					// 如果是双亲节点的右孩子, 那么直接左旋即可
					if( parent.right == p) {
						parent.color = Color.BLACK;
						grandparent.color = Color.RED;
						parent = grandparent.parent;
						p = leftRotate(grandparent);
						if( parent != null ) {
							if( parent.left == grandparent) {
								parent.left = p;
								p.parent = parent;
							} else {
								parent.right = p;
								p.parent = parent;
							}
						}
					} else {  // 如果是双亲节点的左孩子, 那么需要先右旋再左旋
						p.color = Color.BLACK;
						grandparent.color = Color.RED;
						grandparent.left = rightRotate(parent);
						parent = grandparent.parent;
						p = leftRotate(grandparent);
						if( parent != null) {
							if( parent.left == grandparent) {
								parent.left = p;
								p.parent = parent;
							} else {
								parent.right = p;
								p.parent = parent;
							}
						}
					}
				}
			}
		}
		// 如果parent为空, 说明此时的p指向的是根节点, 此时直接将根节点的颜色变为黑色
		if( parent == null) { 
			p.color = Color.BLACK;
			return p;
		}
		return null;
	}
	
	/**
	 * 红黑树的插入
	 * @param root
	 * @param data
	 * @return
	 */
	public RedBlackNode insert(RedBlackNode root, int data) {
		RedBlackNode pre = null;
		RedBlackNode p = root;
		if( p == null) {
			p = new RedBlackNode(data);
			p.color = Color.BLACK;
			return p;
		}
		while( p != NIL ) {
			if( p.data == data) {
				break;
			} else {
				pre = p;
				if( data < p.data) 
					p = p.left;
				else
					p = p.right;
			}
		}
		if( p == NIL) { // 未找到
			if( data < pre.data) {
				pre.left = new RedBlackNode(data);
				pre.left.parent = pre;
				p = pre.left;
			} else {
				pre.right = new RedBlackNode(data);
				pre.right.parent = pre;
				p = pre.right;
			}
			p = nodeAdjust(p);
		}
		if( p != null)  // 如果p不为空, 说明根节点已经改变了, 如果为空说明根节点未变 
			return p;
		return root;
	}
	
	public void preOrderTraverse(RedBlackNode node) {
		if( node == NIL) {
			return;
		}
		System.out.print(node.data+" ");
		preOrderTraverse(node.left);
		preOrderTraverse(node.right);
	}
}
