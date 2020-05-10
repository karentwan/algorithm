package cn.karent.other;
import cn.karent.ds.Stack;
import java.lang.Exception;
import cn.karent.ds.ArrayList;
import java.util.regex.*;
import java.math.*;

public class Calc {
	
	// a表示log
	private char[] operator = {'+', '-', '*', '/', '(', ')', '#'};
	
	// 1表示大于, 0表示等于, -1表示小于, -2表示不合法
	private int[][] priors = {	
								{-1,-1,-1,-1, 1, -2, 1},    // +
								{-1, -1, -1,-1, 1, -2, 1},  // -
								{1, 1, -1, -1, 1, -2, 1},   // *
								{1, 1, -1, -1, 1, -2, 1},   // /
								{1, 1, 1, 1, 1, -2, 1},     // (
								{ -1, -1, -1, -1, 0, -2, 1},// )
								{-1,-1,-1,-1,-1, -2, 0},    // #
							};
	
	private Pattern reg = null; 
	
	public Calc() {
		// 生成正则表达式
		String regStr = "[^0123456789\\.]";
		reg = Pattern.compile(regStr);
	}
	
	/**
	 * 根据字符a得到其在operator数组中的下标
	 * @param a 要获取下标的字符
	 * @return 字符a的下标
	 */
	private int getIndex(char a) {
		for(int i = 0; i < operator.length; i++) {
			if( a == operator[i])
				return i;
		}
		return -1;
	}
	
	/**
	 * 判断a的优先级是否大于b
	 * @param a 要判断优先级的字符
	 * @param b 要判断优先级的字符
	 * @return
	 */
	private int gt(char a, char b){
		int indexA = getIndex(a);
		int indexB = getIndex(b);
		assert indexA != -1 && indexB != -1 : "出现了未识别的操作符";
		return priors[indexA][indexB];
	}
	
	private float operateUseOp(float n1, char op, float n2) {
		float result = 0;
		switch( op ) {
			case '+':
				result = n1 + n2;
				break;
			case '-':
				result = n1 - n2;
				break;
			case '*':
				result = n1 * n2;
				break;
			case '/':
				result = n1 / n2;
				break;
			default:
				break;
		}
		return result;
	}
	
	private boolean isDigital(String a) {
		Matcher m = reg.matcher(a);
		return !m.find();
	}
	
	private boolean isOp(char a) {
		boolean flag = false;
		for(int i = 0; i < operator.length; i++) {
			if( a == operator[i]) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 将表达式按照操作符分开
	 * @param exp
	 * @return
	 */
	private String[] splitFromOp(String exp) {
		ArrayList<String> splites = new ArrayList<String>();
		char[] s = exp.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < s.length; i++) {
			if( isOp(s[i]) ) {
				if( sb.length() > 0) {
					splites.add(sb.toString());
					sb.delete(0, sb.length());
				}
				splites.add(s[i] + "");
			} else {
				sb.append(s[i]);
			}
		}
		String[] a = new String[splites.length()];
		splites.toArray(a);
		return a;
	}
	
	public float calc(String exp) throws Exception{
		Stack<Character> opStack = new Stack<Character>();
		Stack<Float> numberStack = new Stack<Float>();
		exp = exp + "#";
		opStack.push('#');
		String[] strs = splitFromOp(exp);
		String tmp = strs[0];
		int i = 1;
		while(!opStack.isEmpty()) {
			if( isDigital(tmp) ) {
				float n = Float.parseFloat(tmp);
				numberStack.push(n);
				tmp = strs[i++];
			} else {
				char op = opStack.peek();
				char newOp = tmp.toCharArray()[0];
				int flag = gt(newOp, op);
				if( flag == -1) {  // newOp小于op, 此时应计算
					float n1 = numberStack.pop();
					float n2 = numberStack.pop();
					op = opStack.pop();
					float result = operateUseOp(n2, op, n1);
					numberStack.push(result);
					if( newOp != '#' && newOp != ')') {
						opStack.push(tmp);
						tmp = strs[i++];
					} 
				} else if( flag == 1) {  // newOp大于op, 此时操作符进栈
					opStack.push(newOp);
					tmp = strs[i++];
				} else if( flag == 0){  // newOp等于op, 此时op出栈
					opStack.pop();
					if( newOp == ')')
						tmp = strs[i++];
				} else { 
					throw new Exception("表达式不合法");
				}
			} 
		}
		float result = numberStack.pop();
		return result;
	}
}
