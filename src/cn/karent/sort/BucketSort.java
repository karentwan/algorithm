package cn.karent.sort;
import cn.karent.ds.ArrayList;

/**
 * 桶排序
 * @author wan
 */
public class BucketSort<T> implements Sort<T> {
	
	private Compare c;
	
	private CalcIndex calcIndex;
	
	/**
	 * 根据给定的数据计算桶的索引
	 * @author wan
	 */
	public interface CalcIndex {
		public int getIndex(Object data, Object min, Object max, int length);
	}
	
	class CalcIndexIntImpl implements BucketSort.CalcIndex {
	
		@Override
		public int getIndex(Object data, Object min, Object max, int length) {
			Integer minI = (Integer)min;
			Integer dataI = (Integer)data;
			Integer maxI = (Integer)max;
			int index = (dataI - minI) * (length - 1) / (maxI - minI);
			return index;
		}
		
	}
	
	public BucketSort() {
		c = new CompareIntImp();
		calcIndex = new CalcIndexIntImpl();
	}
	
	public BucketSort(Compare c, CalcIndex calc) {
		this.c = c;
		this.calcIndex = calc;
	}
	
	public void sort(T[] data) {
		T min = data[0];
		T max = data[0];
		// 求数组里面最大和最小的元素
		for(int i = 1; i < data.length; i++) {
			if(c.compare(data[i], min) < 0) {
				min = data[i];
			}
			if( c.compare(data[i], max) > 0) {
				max = data[i];
			}
		}
		// 创建一个用来保存数据的桶
		ArrayList<T>[] bucketArr = new ArrayList[data.length];
		// 给定一个数，求出它所属的桶： \frac{x - 1}{d}, d = \frac{max - min}{length - 1}
		for(int i = 0; i < data.length; i++) {
			int index = calcIndex.getIndex(data[i], min, max, data.length);
			if( bucketArr[index] == null ) {
				bucketArr[index] = new ArrayList<T>();
			}
			bucketArr[index].orderAdd(data[i]);
		}
		for(int i = 0, k = 0; i < data.length; i++) {
			if( bucketArr[i] != null) {
				for(int j = 0; j < bucketArr[i].length(); j++) {
					data[k++] = bucketArr[i].get(j);
				}
			}
		}
		
	}

}
