package cn.karent.other;
import cn.karent.ds.Stack;
import java.lang.Exception;
import cn.karent.ds.ArrayList;
import java.util.regex.*;
//import java.util.ArrayList;


public class Calc {
	
	private char[] operator = {'+', '-', '*', '/', '(', ')', '#'};
	
	// 1表示大于, 0表示等于, -1表示小于, -2表示不合法
//	private int[][] priors = {	{1, 1, -1,-1,-1, 1, 1},
//								{1, 1, -1,-1,-1, 1, 1},
//								{1, 1, 1, 1, -1, 1, 1},
//								{1, 1, 1, 1, -1, 1, 1},
//								{-1,-1,-1,-1,-1, 0, -2},
//								{ 1, 1, 1, 1, -2, 1, 1},
//								{-1,-1,-1,-1,-1, -2, 0}};
	
	// 表达式优先级改版, 横坐标是新输入的字符, 纵坐标是栈顶字符
	private int[][] priors = {	{-1,-1,-1,-1, 1, -2, 1},
								{-1, -1, -1,-1, 1, -2, 1},
								{1, 1, -1, -1, 1, -2, 1},
								{1, 1, -1, -1, 1, -2, 1},
								{1, 1, 1, 1, 1, -2, 1},
								{ -1, -1, -1, -1, 0, -2, 1},
								{-1,-1,-1,-1,-1, -2, 0}};
	
	private Pattern reg = null; 
	
	public Calc() {
		// 生成正则表达式
		String m = "[^0123456789\\.]";
		reg = Pattern.compile(m);
	}
	
	/**
	 * 根据字符a得到其在symbol数组中的下标
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
		assert indexA != -1 || indexB != -1 : "出现了未识别的操作符";
		return priors[indexA][indexB];
	}
	
	private float operateUseOp(float n1, float n2, char op) {
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
		if( !m.find() ) 
			return true;
		return false;
	}
	
	private boolean isOp(char a) {
		boolean flag = true;
		switch ( a ) {
		case '+':
			break;
		case '-':
			break;
		case '*':
			break;
		case '/':
			break;
		case '(':
			break;
		case ')':
			break;
		case '#':
			break;
		default:
			flag = false;
			break;
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
//		char[] strs = exp.toCharArray();
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
				if( flag == -1) {  // 新入栈的操作符小于上一次入栈的操作符，故计算上一个操作符的
					float n1 = numberStack.pop();
					float n2 = numberStack.pop();
					op = opStack.pop();
					float result = operateUseOp(n2, n1, op);
					numberStack.push(result);
					if( newOp != '#' && newOp != ')') {
						opStack.push(tmp);
						tmp = strs[i++];
					} 
				} else if( flag == 1) {
					opStack.push(newOp);
					tmp = strs[i++];
				} else if( flag == 0){
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
