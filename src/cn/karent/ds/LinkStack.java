package cn.karent.ds;

/**
 * ��������ʽ�洢��ջ
 * @author wan
 *
 * @param <T>
 */
public class LinkStack<T> {
	
	class Node {
		public Object data;
		public Node next;
	}
	
	private Node head = new Node();
	
	public void push(Object item) {
		Node n = new Node();
		n.data = item;
		n.next = head.next;
		head.next = n;
	}
	
	public T pop() {
		T data = (T) head.next.data;
		head.next = head.next.next;
		return data;
	}
	
	public boolean isEmpty() {
		return head.next == null;
	}
}
