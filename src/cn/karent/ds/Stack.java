package cn.karent.ds;

/**
 * 栈
 * @author wan
 *
 * @param <T>
 */
public class Stack<T> {
	
	private static final int EXPAND_DATA = 20;
	
	private static int capcity = 20;
	
	private Object[] data = new Object[20];
	
	private int top = 0;
	
	private void resize() {
		Object[] tmp = data;
		data = new Object[capcity + EXPAND_DATA];
		for(int i = 0; i < capcity; i++) {
			data[i] = tmp[i];
		}
		capcity += EXPAND_DATA;
	}
	
	public boolean isEmpty() {
		return (top - 1) < 0 ? true : false;
	}
	
	public void push(Object item) {
		if( top >= capcity) {
			resize();
		}
		data[top++] = item;
	}
	
	public T pop() {
		if( (top - 1) < 0)
			return null;
		return (T) data[--top];
	}
	
	public T peek() {
		if( top < 0)
			return null;
		return (T) data[top];
	}
	

}
