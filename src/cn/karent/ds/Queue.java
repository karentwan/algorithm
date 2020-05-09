package cn.karent.ds;
import java.lang.Exception;

/**
 * 循环队列, 中间队列容量不可再改变
 * @author wan
 *
 * @param <T>
 */
public class Queue<T> {
	
	private int capcity;
	
	private Object[] data;
	
	private int front = 0;
	
	private int rear = 0;
	
	public Queue() {
		capcity = 50;
		data = new Object[capcity];
	}
	
	public Queue(int length) {
		capcity = length;
		data = new Object[capcity];
	}
	
	public void enqueue(Object item) throws Exception{
		if( (rear + 1) % capcity == front ) {
			throw new Exception("队列已满");
		}
		data[rear] = item;
		rear = (rear + 1) % capcity;
	}
	
	public T dequeue() {
		if( isEmpty() )
			return null;
		T d = (T) data[front];
		front = (front + 1) % capcity;
		return d;
	}
	
	public T getHead() {
		if( isEmpty() )
			return null;
		return (T) data[front];
	}
	
	public boolean isEmpty() {
		return rear == front;
	}

}
