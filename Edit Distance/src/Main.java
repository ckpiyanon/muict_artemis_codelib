public class Main {
	static int ed(String s1,String s2) {
		int table[][] = new int[s1.length() + 1][s2.length() + 1];
		for(int i = 0;i <= s1.length();i++) for(int j = 0;i <= s2.length();j++) {
			if(i == 0 || j == 0)	table[i][j] = Math.max(i,j);
			else if(s1.charAt(i - 1) == s2.charAt(j-1))
				table[i][j] = table[i-1][j-1];
			else {
				int min = Math.min(table[i-1][j-1],Math.min(table[i][j-1],table[i-1][j]));
				/*
				 * table[i-1][j-1]	=> change
				 * table[i][j-1]	=> insert to s1 || delete from s2
				 * table[i-1][j]	=> insert to s2 || delete from s1
				 */
				table[i][j] = min;
			}
		}
		return table[s1.length()][s2.length()];
	}
	public static void main(String args[]) {
		String s1 = "bcacbcabbaccbab";
		String s2 = "bccabccbbabacbc";
		System.out.println(ed(s1,s2));
	}
}
