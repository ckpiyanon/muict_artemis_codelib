public class Main {
	public static int kmp(char s[],char t[]) {
		int m = s.length,n = t.length,p[] = new int[m+1];
		p[0] = p[1] = 0;
		for(int i = 0,j = 2;j <= m;j++) {
			while(i > 0 && s[i] != s[j-1])	i = p[i];
			if(s[i] == s[j-1])	i++;
			p[j] = i;
		}
		for(int i = 0,j = 1;j < n;j++) {
			while(i > 0 && s[i] != t[j])	i = p[i];
			if(s[i] == t[j])	i++;
			if(i == m)	return j + 1 - m;
		}
		return -1;
	}
	public static void main(String args[]) {
		String s = "ABCDABD";
		String t = "ABC ABCDAB ABCDABCDABDE";
		System.out.println(kmp(s.toCharArray(),t.toCharArray()));
	}
}
