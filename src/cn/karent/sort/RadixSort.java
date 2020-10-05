package cn.karent.sort;

/**
 * 基数排序, 按照每一个位排序
 * 稳定排序算法
 */
public class RadixSort<T> {

    interface Data<T> {
        public T get(T t);
    }

    private Data data = new Data<Integer>() {
        @Override
        public Integer get(Integer o) {
            return o;
        }
    };

    public void sort(T[] nums) {
        sort(nums, false);
    }

    public void sort(T[] nums, boolean reverse) {
        int exp = 1;
        int max = getMax(nums);
        while( max / exp > 0) {
            sort_(nums, exp, reverse);
            exp *= 10;
        }
    }

    private void sort_(T[] nums, int exp, boolean reverse) {
        int[] bucket = new int[10];
        int n = nums.length;
        Object[] output = new Object[n];
        for(int i = 0; i < n; i++) {
            bucket[((int)data.get(nums[i])/exp)%10]++;
        }
        for(int i = 1; i < 10; i++) {
            bucket[i] += bucket[i-1];
        }
        for(int i = n-1; i >= 0; i--) {
            int idx = ((int)data.get(nums[i])/exp)%10;
            if( reverse ) {
                output[n - bucket[idx]] = nums[i];
            } else {
                output[bucket[idx]-1] = nums[i];
            }
            bucket[idx]--;
        }
        for(int i = 0; i < n; i++) {
            nums[i] = (T)output[i];
        }
    }

    private int getMax(T[] nums) {
        int max = 0;
        for(T i : nums) {
            max = Math.max(max, (int)data.get(i));
        }
        return max;
    }
}
