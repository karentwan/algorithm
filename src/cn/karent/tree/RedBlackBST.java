package cn.karent.tree;
import cn.karent.sort.Compare;
import cn.karent.sort.CompareIntImp;

/**
 * 红黑树
 * @author wan
 *
 */
public class RedBlackBST<T> {
	
	private enum Color {
		RED, BLACK
	}
	
	private RedBlackNode NIL; // 所有新插入的节点的孩子节点都是这个节点
	
	private Compare c;
	
	public class RedBlackNode {
		Color color = Color.RED;
		RedBlackNode left;   // 左子树
		RedBlackNode right;  // 右子树
		RedBlackNode parent; // 双亲节点
		Object data;
		
		public RedBlackNode() {}
		
		public RedBlackNode(Object data) {
			this.data = data;
			left = NIL;
			right = NIL;
		}
	}
	
	private void initNIL() {
		NIL = new RedBlackNode();
		NIL.color = Color.BLACK;
	}
	
	public RedBlackBST() {
		initNIL();
		this.c = new CompareIntImp();
	}
	
	public RedBlackBST(Compare c) {
		initNIL();
		this.c = c;
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
	 * 将旋转之后的节点p附加到parent中, 其中parent是oldNode的parent
	 * @param p 旋转之后的节点
	 * @param parent oldNode的双亲节点, 附加之后p的节点
	 * @param oldNode 旋转之前的节点
	 */
	private void attachNodeToParent(RedBlackNode p, RedBlackNode parent, RedBlackNode oldNode) {
		if( parent != null ) {
			if( parent.left == oldNode) {
				parent.left = p;
				p.parent = parent;
			} else {
				parent.right = p;
				p.parent = parent;
			}
		}
	}
	
	private RedBlackNode leftBalance(RedBlackNode p, RedBlackNode parent, RedBlackNode grandparent) {
		// 如果叔叔节点是红色, 那么只需要变色即可
		if( grandparent.right.color == Color.RED) { 
			parent.color = Color.BLACK;
			grandparent.right.color = Color.BLACK;
			grandparent.color = Color.RED;
			p = grandparent;
		} else {  // 如果叔叔节点是黑色, 那么就要分情况旋转
			// 如果是双亲节点的左孩子, 那么直接右旋即可(对应AVL树里面的LL)
			if( parent.left == p) {
				parent.color = Color.BLACK;
				grandparent.color = Color.RED;
				parent = grandparent.parent;
				p = rightRotate(grandparent);
				attachNodeToParent(p, parent, grandparent);
			} else {  // 如果是双亲节点的右孩子, 那么需要先左旋再右旋(对应AVL树里面的LR)
				p.color = Color.BLACK;
				grandparent.color = Color.RED;
				grandparent.left = leftRotate(parent);
				parent = grandparent.parent;
				p = rightRotate(grandparent);
				attachNodeToParent(p, parent, grandparent);
			}
		}
		return p;
	}
	
	private RedBlackNode rightBalance(RedBlackNode p, RedBlackNode parent, RedBlackNode grandparent) {
		if( grandparent.left.color == Color.RED) { // 只需要变色即可
			parent.color = Color.BLACK;
			grandparent.left.color = Color.BLACK;
			grandparent.color = Color.RED;
			p = grandparent;
		} else {  // 需要旋转
			// 如果是双亲节点的右孩子, 那么直接左旋即可(相当于AVL树里面的RR)
			if( parent.right == p) {
				parent.color = Color.BLACK;
				grandparent.color = Color.RED;
				parent = grandparent.parent;
				p = leftRotate(grandparent);
				attachNodeToParent(p, parent, grandparent);
			} else {  // 如果是双亲节点的左孩子, 那么需要先右旋再左旋(相当于AVL树里面的RL)
				p.color = Color.BLACK;
				grandparent.color = Color.RED;
				grandparent.left = rightRotate(parent);
				parent = grandparent.parent;
				p = leftRotate(grandparent);
				attachNodeToParent(p, parent, grandparent);
			}
		}
		return p;
	}
	
	/**
	 * 调整完毕后, 当调整一直打到了根节点的时候说明根节点已经改变了, 此时需要返回根节点
	 * 如果没有调整到根节点, 那么就直接返回空就可以了
	 * @param node 待调整的节点
	 * @return 根节点或者空
	 */
	private RedBlackNode nodeAdjust(RedBlackNode node) {
		RedBlackNode p = node;
		RedBlackNode parent = p.parent;
		RedBlackNode grandparent = null;
		while( parent != null && parent.color == Color.RED && parent.color == p.color) {
			grandparent = parent.parent;
			if( parent == grandparent.left) {  // 如果双亲节点是祖父节点的左子树
				p = leftBalance(p, parent, grandparent);
				parent = p.parent;
			} else { // 如果双亲节点是祖父节点的右子树
				p = rightBalance(p, parent, grandparent);
				parent = p.parent;
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
	public RedBlackNode insert(RedBlackNode root, Object data) {
		RedBlackNode pre = null;
		RedBlackNode p = root;
		if( p == null) {
			p = new RedBlackNode(data);
			p.color = Color.BLACK;
			return p;
		}
		while( p != NIL ) {
			if( c.compare(data, p.data) == 0 ) {
				break;
			} else {
				pre = p;
				if( c.compare(data, p.data) < 0 ) 
					p = p.left;
				else
					p = p.right;
			}
		}
		if( p == NIL) { // 未找到
			if( c.compare(data, pre.data) < 0 ) {
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
		System.out.print(node.color + "-" + node.data+" ");
		preOrderTraverse(node.left);
		preOrderTraverse(node.right);
	}
	
	public void inOrderTraverse(RedBlackNode node) {
		if( node == NIL) {
			return;
		}
		inOrderTraverse(node.left);
		System.out.print(node.color + "-" + node.data+" ");
		inOrderTraverse(node.right);
	}
}
