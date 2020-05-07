package cn.karent.sort;

public class ShellSort<T> implements Sort<T>{
	
	private Compare c = null;
	
	public ShellSort() {
		c = new CompareIntImp();
	}
	
	public ShellSort(Compare c) {
		this.c = c;
	}
	
	public void sort(T[] data) {
		for(int d = data.length / 2; d >= 1; d /= 2) {
			for(int i = d; i < data.length; i++) {
				T tmp = data[i];
				int j = i - d;
				for(; j >= 0 && c.compare(data[j], tmp) > 0; j -= d) {
					data[j+d] = data[j];
				}
				data[j+d] = tmp;
			}
		}
	}

}
