package cn.karent.ds;

/**
 * 优先队列
 * @author wan
 *
 * @param <T>
 */
public class PriorQueue<T> {
	
	public interface Compare {
		public int compare(Object o1, Object o2);
	}
	
	private class CompareInt implements Compare {
		
		public int compare(Object o1, Object o2) {
			int item1 = (Integer)o1;
			int item2 = (Integer)o2;
			return item1 - item2;
		}
		
	}
	
	public PriorQueue() {
		
	}
	
	public PriorQueue(Compare c) {
		this.c = c;
	}
	
	private final static int EXPAND = 20;
	
	private int capcity = 20;
	
	private Object[] data = new Object[capcity];
	private int n = 0;
	
	/**
	 * 队列扩容
	 */
	private void resize() {
		Object[] tmp = data;
		data = new Object[capcity + EXPAND];
		for(int i = 0; i < capcity; i++) {
			data[i] = tmp[i];
		}
		capcity += EXPAND;
	}
	
	private Compare c = new CompareInt();
	
	/**
	 * 上浮
	 */
	private void up() {
		int children = n - 1;
		Object t = data[n-1];
		int i = (children - 1) / 2;  
		for(; children > 0 && c.compare(t, data[i]) > 0; i = (i - 1) / 2) {
			data[children] = data[i];
			children = i;
		}
		data[children] = t;
	}
	
	/**
	 * 下沉
	 */
	private void down() {
		int p = 0;
		int i = 1;
		Object t = data[0];
		for( ; i <= n-1; i = 2 * i + 1) {
			if( c.compare(data[i], data[i+1]) < 0) 
				i++;
			if( c.compare(t, data[i]) > 0)
				break;
			data[p] = data[i];
			p = i;
		}
		data[p] = t;
	}
	
	public void enqueue(Object item) {
		if(n >= capcity) {
			resize();
		}
		data[n] = item;
		n++;
		up();
	}
	
	public T dequeue() {
		T item = (T) data[0];
		data[0] = data[n-1];
		n--;
		down();
		return item;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
}
