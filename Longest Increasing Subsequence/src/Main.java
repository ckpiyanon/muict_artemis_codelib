import java.util.Arrays;

public class Main {
	static int ceilIndex(int[] list,int l,int r,int val) {
		while(r - l > 1) {
			int m = (l + r) / 2;
			if(list[m] >= val)	r = m;
			else	l = m;
		}
		return r;
	}
	static int lis(int[] list) {
		int tailTable[] = new int[list.length];
		int len = 1;
		tailTable[0] = list[0];
		lis[0] = 1;
		for(int i = 1;i < list.length;i++) {
			if(list[i] < tailTable[0]) {
				tailTable[0] = list[i];
				lis[i] = 1;
			}
			else if(list[i] > tailTable[len-1]) {
				tailTable[len++] = list[i];
				lis[i] = len;
			}
			else {
				int idx = ceilIndex(tailTable,-1,len-1,list[i]);
				tailTable[idx] = list[i];
				lis[i] = idx + 1;
			}
		}
		return len;
	}
	static int[] lis;
	public static void main(String args[]) {
		int[] list = {2,5,3,7,11,8,10,13,6};
		lis = new int[list.length];
		System.out.println(lis(list));
		System.out.println(Arrays.toString(lis));
	}
}
