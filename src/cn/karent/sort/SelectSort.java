package cn.karent.sort;

public class SelectSort<T> implements Sort<T>{
	
	private Compare c = null;
	
	public SelectSort() {
		c = new CompareIntImp();
	}
	
	public SelectSort(Compare c) {
		this.c = c;
	}

	public void sort(T[] data) {
		for(int i = 0; i < data.length; i++) {
			int k = i;
			for(int j = i + 1; j < data.length; j++) {
				if(c.compare(data[k], data[j]) > 0) {
					k = j;
				}
			}
			T tmp = data[k];
			data[k] = data[i];
			data[i] = tmp;
		}
	}
	
}
