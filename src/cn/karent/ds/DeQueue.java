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
	
	public void push(Object item) {
		Node n = new Node();
		n.data = item;
		n.pre = end.pre;
		n.next = end;
		end.pre = n;
		n.pre.next = n;
	}
	
	public T pop() {
		Node tmp = end.pre;
		T d = (T) tmp.data;
		end.pre = tmp.pre;
		tmp.pre.next = end;
		return d;
	}
	
	public void enqueue(Object item) {
		push(item);
	}
	
	public T dequeue() {
		Node tmp = head.next;
		T d = (T) tmp.data;
		head.next = tmp.next;
		tmp.next.pre = head;
		return d;
	}
	
	public boolean isEmpty() {
		return head.next == end;
	}
	
}
