package cn.karent.tree;
import cn.karent.ds.Stack;

public class BinaryTree {
	
	/**
	 * 以递归方式实现前序遍历
	 * @param n
	 */
	public void preOrderTraverseRecursion(BinaryTreeNode n) {
		if( n != null) {
			System.out.print(n.data);
			preOrderTraverseRecursion(n.lchild);
			preOrderTraverseRecursion(n.rchild);
		}
	}
	
	/**
	 * 以递归方式实现中序遍历
	 * @param n
	 */
	public void inOrderTraverseRecursion(BinaryTreeNode n) {
		if( n != null) {
			inOrderTraverseRecursion(n.lchild);
			System.out.print(n.data);
			inOrderTraverseRecursion(n.rchild);
		}
	}
	
	/**
	 * 以递归的方式实现后序遍历
	 * @param n
	 */
	public void postOrderTraverseRecursion(BinaryTreeNode n) {
		if( n != null) {
			postOrderTraverseRecursion(n.lchild);
			postOrderTraverseRecursion(n.rchild);
			System.out.print(n.data);
		}
	}

	public void preOrderTraverse(BinaryTreeNode<Character> n) {
		Stack<BinaryTreeNode<Character>> stack = new Stack<BinaryTreeNode<Character>>();
		stack.push(n);
		BinaryTreeNode<Character> node = null;
		while( !stack.isEmpty() ) {
			node = stack.pop();
			System.out.print(node.data);
			BinaryTreeNode<Character> rchild = node.rchild;
			BinaryTreeNode<Character> lchild = node.lchild;
			if( rchild != null) {
				stack.push(rchild);
			}
			if( lchild != null) {
				stack.push(lchild);
			}
		}
	}

	public void inOrderTraverse(BinaryTreeNode<Character> n) {
		Stack<BinaryTreeNode<Character>> stack = new Stack<BinaryTreeNode<Character>>();
		BinaryTreeNode<Character> p = n;
		while(p != null || !stack.isEmpty() ) {
			while( p != null ) {
				stack.push(p);
				p = p.lchild;
			}
			p = stack.pop();
			System.out.print(p.data);
			p = p.rchild;
		}
	}
	
	public void postOrderTraverse(BinaryTreeNode<Character> n) {
		Stack<BinaryTreeNode<Character>> stack = new Stack<BinaryTreeNode<Character>>();
		BinaryTreeNode<Character> p = n;
		BinaryTreeNode<Character> visited = null;  
		while( p != null || !stack.isEmpty()) {
			while( p != null) {
				stack.push(p);
				p = p.lchild;
			}
			p = stack.peek();
			p = p.rchild;
			// 如果右子树为空或者右子树已经被访问过, 此时应该访问双亲节点
			// 右子树的根节点一定是最后一个被访问的节点
			if( p == null || visited == p) {
				p = stack.pop();
				System.out.print(p.data);
				visited = p;
				p = null;
			}
		}
	}
}
