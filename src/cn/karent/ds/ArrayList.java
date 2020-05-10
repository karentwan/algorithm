package cn.karent.ds;
import cn.karent.sort.Compare;
import cn.karent.sort.CompareIntImp;

/**
 * 可变长数组
 * @author wan
 */
public class ArrayList<T> {
	
	private final static int EXPAND = 20;
	
	private int capcity = 20;
	
	private int size = 0;
	
	private Object[] data = null;
	
	private Compare c;
	
	public ArrayList() {
		data = new Object[capcity];
		c = new CompareIntImp();
	}
	
	public ArrayList(int capcity) {
		this.capcity = capcity;
		data = new Object[capcity];
	}
	
	public ArrayList(Compare c) {
		this.c = c;
	}
	
	public void setCompare(Compare c) {
		this.c = c;
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
	
	/**
	 * 有序插入, 插入之后的ArrayList一定是有序的
	 * @param item
	 */
	public void orderAdd(T item) {
		if( size >= capcity)
			grow();
		int i = size - 1;
		while( i >= 0 && c.compare(item, data[i]) < 0) {
			data[i+1] = data[i];
			i--;
		}
		data[i+1] = item;
		size++;
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
