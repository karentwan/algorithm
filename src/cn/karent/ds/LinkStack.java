package cn.karent.ds;

/**
 * 以链表形式存储的栈
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
		if( isEmpty() )
			return null;
		T data = (T) head.next.data;
		head.next = head.next.next;
		return data;
	}
	
	public T peek() {
		if( isEmpty()) 
			return null;
		T data = (T) head.next.data;
		return data;
	}
	
	public boolean isEmpty() {
		return head.next == null;
	}
}
