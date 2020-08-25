package cn.karent.string;

/**
 * 最长回文串匹配, 时间复杂度可以降到O(n)
 */
public class Manacher {

    private String pad(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for(int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        return sb.toString();
    }

    public int palindrome(String s) {
        if( s.length() == 0) {
            return 0;
        }
        s = pad(s);
        int[] mp = new int[s.length()];
        int r = 0;
        int c = 0;
        int max = 0;
        for(int i = 0; i < s.length(); i++) {
            mp[i] = r > i ? Math.min(mp[2*c-i], r-i):1;
            while( i+mp[i] < s.length() && i-mp[i] >= 0 && s.charAt(i+mp[i]) == s.charAt(i-mp[i])) {
                mp[i]++;
            }
            if( i + mp[i] > r) {
                r = i+mp[i];
                c = i;
            }
            max = Math.max(mp[i], max);
        }
        return max - 1;
    }


}
