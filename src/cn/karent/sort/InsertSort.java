package cn.karent.sort;

public class InsertSort<T> implements Sort<T>{
	
	private Compare c = null;
	
	public InsertSort() {
		c = new CompareIntImp();
	}
	
	public InsertSort(Compare c) {
		this.c = c;
	}
	
	public void sort(T[] data) {
		for(int i = 1; i < data.length; i++) {
			T tmp = data[i];
			int j = i - 1;
			for(; j >= 0 && c.compare(tmp, data[j]) < 0; j--) {
				data[j + 1] = data[j];
			}
			data[j + 1] = tmp;
		}
	}

}
