package cn.karent.sort;

/**
 * 归并排序(利用分治的思想来实现)
 * @author wan
 *
 */
public class MergeSort<T> implements Sort<T> {
	
	private Compare c;
	
	public MergeSort() {
		c = new CompareIntImp();
	}
	
	public MergeSort(Compare c) {
		this.c = c;
	}
	
	private void merge(T[] data, int left, int mid, int right, Object[] result) {
		int i = left, j = mid+1, k = left;
		while( i <= mid && j <= right ) {
			if( c.compare(data[i], data[j]) < 0) {
				result[k++] = data[i++];
			} else {
				result[k++] = data[j++];
			}
		}
		while( i <= mid) {
			result[k++] = data[i++];
		}
		while( j <= right) {
			result[k++] = data[j++];
		}
		for(i = left; i <= right; i++) {
			data[i] = (T)result[i];
		}
	}
	
	private void mergeSort(T[] data, int left, int right, Object[] result) {
		// 边界条件
		if( left == right ) 
			return;
		// 分
		int mid = (right + left) / 2;
		mergeSort(data, left, mid, result);
		mergeSort(data, mid+1, right, result);
		// 合并子问题的结果
		merge(data, left, mid, right, result);
	}
	
	public void sort(T[] data) {
		Object[] result = new Object[data.length];
		mergeSort(data, 0, data.length - 1, result);
	}

}
