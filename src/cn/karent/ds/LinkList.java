package cn.karent.ds;

/**
 * 链表
 * @author wan
 *
 */
public class LinkList<T> {
	
	private class Node {
		Object data;
		Node next;
	}
	
	private int n = 0;
	
	private Node head = new Node();
	
	public void add(T item) {
		Node n = new Node();
		n.data = item;
		n.next = head.next;
		head.next = n;
		this.n++;
	}
	
	public void remove(T item) {
		Node p = head.next;
		Node pre = head;
		while( p != null && p.data != item) {
			pre = p;
			p = p.next;
		}
		if( p != null) {
			pre.next = p.next;
		}
		n--;
	}
	
	public T get() {
		return (T)head.next;
	}
	
	/**
	 * 判断链表里面是否存在item这一项元素
	 * @param item
	 * @return
	 */
	public boolean exist(T item) {
		Node p = head.next;
		while( p != null) {
			if( p.data.equals(item) )
				return true;
			p = p.next;
		}
		return false;
	}
	
	public Iterator iterator() {
		return new Iterator();
	}
	
	/**
	 * 内部迭代器, 可以迭代访问LinkList的内部元素
	 * 使用的迭代器模式
	 * @author wan
	 */
	public class Iterator {
		
		private Node p = null;
		
		public Iterator() {
			p =  head.next;
		}
		
		public T next() {
			if( p == null)
				return null;
			T data = (T) p.data;
			p = p.next;
			return data;
		}
	}

}
