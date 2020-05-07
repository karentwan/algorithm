package cn.karent;
import cn.karent.ds.*;
import java.lang.Exception;
import cn.karent.sort.*;

public class Main {
	
	public static void ds() throws Exception{
		int[] data = {1, 2, 3, 8, 9, 5, 6, 4, 3};
//		Stack<Integer> s = new Stack<Integer>();
//		for(int i = 0; i < data.length; i++) {
//			s.push(data[i]);
//		}
//		while( !s.isEmpty() ) {
//			System.out.print(s.pop() + " ");
//		}
//		Queue<Integer> q = new Queue<Integer>();
//		q.enqueue(1);
//		q.enqueue(1);
//		q.dequeue();
//		q.dequeue();
//		for( int i = 0; i < data.length; i++) {
//			q.enqueue(data[i]);
//		}
//		while( !q.isEmpty() ) {
//			System.out.print(q.dequeue() + " ");
//		}
//		LinkQueue<Integer> q = new LinkQueue<Integer>();
//		q.enqueue(1);
//		q.enqueue(1);
//		q.dequeue();
//		q.dequeue();
//		for( int i = 0; i < data.length; i++) {
//			q.enqueue(data[i]);
//		}
//		while( !q.isEmpty() ) {
//			System.out.print(q.dequeue() + " ");
//		}
//		LinkStack<Integer> q = new LinkStack<Integer>();
//		for( int i = 0; i < data.length; i++) {
//			q.push(data[i]);
//		}
//		while( !q.isEmpty() ) {
//			System.out.print(q.pop() + " ");
//		}
//		PriorQueue<Integer> p= new PriorQueue<Integer>(new PriorQueue.Compare()  {
//			@Override
//			public int compare(Object o1, Object o2) {
//				Integer item1 = (Integer)o1;
//				Integer item2 = (Integer)o2;
//				return item2 - item1;
//			}
//		});
//		for(int i = 0; i < data.length; i++) {
//			p.enqueue(data[i]);
//		}
//		while( !p.isEmpty() ) {
//			System.out.print(p.dequeue() + " ");
//		}
//		DeQueue<Integer> d = new DeQueue<Integer>();
//		for(int i = 0; i < data.length; i++) {
////			System.out.print(i);
//			d.enqueue(data[i]);
//		}
//		while( !d.isEmpty() ) {
//			System.out.print(d.dequeue() + " ");
//		}
//		System.out.println("\n------------��ջ����\n");
//		for(int i = 0; i < data.length; i++) {
////			System.out.print(i);
//			d.push(data[i]);
//		}
//		while( !d.isEmpty() ) {
//			System.out.print(d.pop() + " ");
//		}
	}
	
	public static void print(Integer[] data) {
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println("\n");
	}
	
	public static void sort() {
		Integer[] data = {49, 38, 65, 97, 76, 13, 27, 49, 37};	
		Compare c = new Compare() {

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				Integer item1 = (Integer)o1;
				Integer item2 = (Integer)o2;
				return item2 - item1;
			}
			
		};
		// 冒泡排序
//		BubbleSort<Integer> b1 = new BubbleSort<Integer>(c);
//		print(data);
//		b1.sort(data);
//		print(data);
//		SelectSort<Integer> s = new SelectSort<Integer>(c);
//		print(data);
//		s.sort(data);
//		print(data);
//		InsertSort<Integer> i = new InsertSort<Integer>();
//		print(data);
//		i.sort(data);
//		print(data);
		ShellSort<Integer> s = new ShellSort<Integer>();
		print(data);
		s.sort(data);
		print(data);
//		Sort<Integer> q = new QuickSort<Integer>(c);
//		print(data);
//		q.sort(data);
//		print(data);
//		HeapSort<Integer> h = new HeapSort<Integer>(c);
//		print(data);
//		h.sort(data);
//		print(data);
	}
	
	public static void main(String[] args) throws Exception{
		sort();
	}
}
