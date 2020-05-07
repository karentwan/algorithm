package cn.karent.sort;

public class QuickSort<T> implements Sort<T> {
	
	private Compare c = null;
	
	public QuickSort() {
		c = new CompareIntImp();
	}
	
	public QuickSort(Compare c) {
		this.c = c;
	}
	
	private int partition(T[] data, int m, int n) {
		T tmp = data[m];
		while( m < n) {
			while( m < n && c.compare(data[n], tmp) >= 0)
				n--;
			data[m] = data[n];
			while(m < n && c.compare(data[m], tmp) < 0)
				m++;
			data[n] = data[m];
		}
		data[m] = tmp;
		return m;
	}
	
	private void quickSort(T[] data, int m, int n) {
		if( m >= n )
			return;
		int k = partition(data, m, n);
		quickSort(data, m, k-1);
		quickSort(data, k+1, n);
	}
	
	public void sort(T[] data) {
		quickSort(data, 0, data.length-1);
	}

}
