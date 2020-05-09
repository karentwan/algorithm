package cn.karent.ds;


/**
 * 可变长数组
 * @author wan
 */
public class ArrayList<T> {
	
	private final static int EXPAND = 20;
	
	private int capcity = 20;
	
	private int size = 0;
	
	Object[] data = null;
	
	public ArrayList() {
		data = new Object[capcity];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * ArrayList的做法是new一个新的数组，然后使用System.copyof()方法来将
	 * 原有的数据全部copy过去，这里就直接使用循环来做
	 */
	private void grow() {
		Object[] tmp = data;
		data = new Object[capcity + EXPAND];
		for(int i = 0; i < capcity; i++) {
			data[i] = tmp[i];
		}
		capcity += EXPAND;
		
	}
	
	public void add(T item) {
		if( size >= capcity)
			grow();
		data[size++] = item;
	}
	
	public T get(int index) {
		if( index >= size)
			return null;
		return (T)data[index];
	}
	
	public int length() {
		return size;
	}
	
	public Object[] toArray() {
		Object[] tmp = new Object[size];
		for(int i = 0; i < size; i++) {
			tmp[i] = data[i];
		}
		return tmp;
	}
	
	public T[] toArray(T[] a) {
		for(int i = 0; i < size; i++) {
			a[i] = (T)data[i];
		}
		return a;
	}
}
