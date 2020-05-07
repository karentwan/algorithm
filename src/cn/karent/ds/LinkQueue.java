package cn.karent.ds;


/**
 * 以链表形式存储的队列
 * @author wan
 *
 * @param <T>
 */
public class LinkQueue<T> {

	private QueueNode front = new QueueNode();
	
	private QueueNode rear = front;
	
	private class QueueNode {
		public Object data;
		public QueueNode next;
	}
	
	public void enqueue(Object item) {
		QueueNode node = new QueueNode();
		node.data = item;
		rear.next = node;
		rear = node;
	}
	
	public T dequeue() {
		T data = (T) front.next.data;
		front.next = front.next.next;
		if( front.next == null)
			rear = front;
		return data;
	}
	
	public boolean isEmpty() {
		return front.next == null;
	}
}
