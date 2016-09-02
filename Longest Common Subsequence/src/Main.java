public class Main {
	static int lcs(String s1,String s2) {
		int[][] table = new int[s1.length() + 1][s2.length() + 1];
		for(int i = 1;i <= s1.length();i++)	for(int j = 1;j <= s2.length();j++)
			table[i][j] = s1.charAt(i-1) == s2.charAt(j-1) ?
					(table[i-1][j-1] + 1):(Math.max(table[i-1][j-1],Math.max(table[i-1][j],table[i][j-1])));
		return table[s1.length()][s2.length()];
	}
	public static void main(String args[]) {
		String s1 = "bcacbcabbaccbab";
		String s2 = "bccabccbbabacbc";
		System.out.println(lcs(s1,s2));
	}
}
