package cn.karent.other;

/**
 * 给定一个数字，其中有两个数字被交换了，现在需要将这两个被交换的数字换回来
 * @author wan
 *
 */
public class FindSwapNumber {

	
	public void swapNumber(Integer[] nums) {
		int x = -1, y = -1;
		for(int i = 0; i < nums.length - 1; i++) {
			if( nums[i] > nums[i+1]) {
				if( x == -1)
					x = i;
				else 
					y = i+1;
			}
		}
		int val = nums[x];
		nums[x] = nums[y];
		nums[y] = val;
	}
}
