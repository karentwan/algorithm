package cn.karent.tree;

public class BinaryTreeNode<T> {
		public BinaryTreeNode<T> lchild;
		public BinaryTreeNode<T> rchild;
		public T data;
		
		public BinaryTreeNode() {
			
		}
		
		public BinaryTreeNode(T a) {
			data = a;
		}
}
