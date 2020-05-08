package cn.karent.string;

/**
 * 字符串快速匹配算法
 * @author wan
 *
 */
public class KMP {
	
	// 要匹配的母串
	private String str;
	
	// next数组
	private int next[];
	
	public KMP() {
		
	}
	
	public KMP(String str) {
		this.str = str;
	}
	
	/**
	 * 根据母串生成next数组
	 */
	private void generateNext() {
		int n = str.length();
		int i = 0;
		int j = 1;
	}
	
	public void match(String substring) {
		
	}

}
