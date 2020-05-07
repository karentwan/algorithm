package cn.karent.sort;

public class CompareIntImp implements Compare{

	@Override
	public int compare(Object o1, Object o2) {
		Integer item1 = (Integer)o1;
		Integer item2 = (Integer)o2;
		return item1 - item2;
	}

}
