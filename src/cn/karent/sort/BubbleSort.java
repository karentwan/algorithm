package cn.karent.sort;

public class BubbleSort<T> implements Sort<T>{
	
	private Compare c = null;
	
	public BubbleSort() {
		c = new CompareIntImp();
	}
	
	public BubbleSort(Compare c) {
		this.c = c;
	}
	
	public void sort(T[] data) {
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data.length - i - 1; j++) {
				if( c.compare(data[j], data[j+1]) > 0) {
					T tmp = data[j];
					data[j] = data[j+1];
					data[j+1] = tmp;
				}
			}
		}
	}
	
	

}
