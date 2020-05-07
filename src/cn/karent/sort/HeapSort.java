package cn.karent.sort;

public class HeapSort<T> implements Sort<T> {
	
	private Compare c = null;
	
	public HeapSort() {
		c = new CompareIntImp();
	}
	
	public HeapSort(Compare c) {
		this.c = c;
	}
	
	/**
	 * 构建大顶堆
	 * @param data
	 * @param m
	 * @param n
	 */
	private void down(T[] data, int m, int n) {
		T tmp = data[m];
		int p = m;
		int i = 2 * p + 1;
		for( ; i < n; i = 2 * i + 1 ) {
			if( c.compare(data[i+1], data[i]) > 0 ) 
				i++;
			if( c.compare(tmp, data[i]) >= 0)
				break;
			data[p] = data[i];
			p = i;
		}
		data[p] = tmp;
	}

	private void exchange(T[] data, int m, int n) {
		T tmp = data[m];
		data[m] = data[n];
		data[n] = tmp;
	}
	
	@Override
	public void sort(T[] data) {
		// TODO Auto-generated method stub
		int i = (data.length - 2) / 2;
//		生成大顶堆
		while( i >= 0) {
			down(data, i, data.length - 1);
			i--;
		}
		for(i = data.length - 1; i > 0; i--) {
			exchange(data, 0, i);
			down(data, 0, i - 1);
		}
		
	}

}
