package cn.karent.string;

/**
 * 字符串快速匹配算法
 * @author wan
 *
 */
public class KMP {
	
	// 要匹配的模式串
	private char[] str;
	
	private int length;
	
	// next数组
	private int[] next;
	
	public KMP() {
		
	}
	
	public KMP(String str) {
		this.str = str.toCharArray();
		generateNext();
		print();
	}
	
	private void print() {
		System.out.print("模板串的next数组为:");
		for(int i = 0; i < length; i++) {
			System.out.print(next[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * 根据母串生成next数组
	 * 生成next数组的诀窍，当str[j]与str[k]相等，那么说明
	 * next[j+1] = next[k] + 1
	 */
	private void generateNext() {
		length = str.length;
		next = new int[length];
		int i = -1;
		int j = 0;
		next[0] = -1;
		next[1] = 0;
		while( j < length - 1) {
			// 当i==-1的时候，说明母串当前字符跟模式串第一个字符都不匹配
			// 此时应该将母串和子串同时右移一位，同时将next[j+1]赋值为0
			if( i == -1 || str[i] == str[j]) {
				next[++j] = ++i;
			} else {
				i = next[i];
			}
		}
	}
	
	/**
	 * 匹配模式串在s串第一次出现的起始位置
	 * @param s 要匹配的母串
	 * @return 模式串出现的起始位置
	 */
	public int match(String s) {
		int i = 0;
		int j = 0;
		char[] chs = s.toCharArray();
		int length = chs.length;
		while( i < length && j < this.length) {
			if(j == -1 || chs[i] == str[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		int loc = -1;
		if( i < length && j > 0)
			loc = i - this.length;
		return loc;
	}

}
