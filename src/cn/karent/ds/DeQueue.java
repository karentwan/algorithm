package cn.karent.ds;

/**
 * 双端队列
 * @author wan
 *
 */
public class DeQueue<T> {
	
	private class Node {
		public Object data;
		public Node next;
		public Node pre;
	}
	
	private Node head = null;
	private Node end = null;
	
	public DeQueue() {
		head = new Node();
		end = new Node();
		head.next = end;
		end.pre = head;
	}
	
	/**
	 * 在队尾加入元素
	 * @param item
	 */
	public void addLast(Object item) {
		Node n = new Node();
		n.data = item;
		n.pre = end.pre;
		n.next = end;
		end.pre = n;
		n.pre.next = n;
	}
	
	/**
	 * 在队首加入元素
	 * @param item
	 */
	public void addFirst(Object item) {
		Node n = new Node();
		n.data = item;
		n.pre = head;
		n.next = head.next;
		head.next = n;
		n.next.pre = n;
	}
	
	/**
	 * 获取队尾元素并删除
	 * @return
	 */
	public T getLast() {
		if( !isEmpty() )
			return null;
		Node tmp = end.pre;
		T d = (T) tmp.data;
		end.pre = tmp.pre;
		tmp.pre.next = end;
		return d;
	}
	
	/**
	 * 获取队首元素并删除
	 * @return
	 */
	public T getFirst() {
		if( !isEmpty() )
			return null;
		Node tmp = head.next;
		T d = (T) tmp.data;
		head.next = tmp.next;
		tmp.next.pre = head;
		return d;
	}
	
	/**
	 * 获取队尾元素, 但不删除
	 * @return
	 */
	public T peekLast() {
		if( !isEmpty() )
			return null;
		T d = (T) end.pre.data;
		return d;
	}
	
	/**
	 * 获取队首元素, 但不删除
	 * @return
	 */
	public T peekFirst() {
		if( !isEmpty() )
			return null;
		T d = (T) head.next.data;
		return d;
	}
	
	public boolean isEmpty() {
		return head.next == end;
	}
	
}
