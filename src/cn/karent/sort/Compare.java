package cn.karent.sort;

/**
 * 排序用来比较的接口，如果需要给自定义数据结构排序，需要实现该接口
 * @author wan
 *
 */
public interface Compare {
	
	public int compare(Object o1, Object o2);

}
