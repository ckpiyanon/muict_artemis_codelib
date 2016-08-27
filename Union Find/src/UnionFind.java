import java.util.Arrays;

public class UnionFind {
	private int[] parent,rank;
	private int size;
	public UnionFind(int size) {
		parent = new int[size];
		rank = new int[size];
		this.size = size;
		Arrays.fill(rank,0);
		for(int i = 0;i < size;i++)	parent[i] = i;
	}
	public boolean isSameSet(int m,int n) {return find(m) == find(n);}
	public int find(int n) {
		if(n == parent[n])	return n;
		return parent[n] = find(parent[n]);
	}
	public void merge(int m,int n) {
		m = find(m); n = find(n);
		if(m == n)	return;
		if(rank[m] == rank[n]) {
			parent[m] = n;
			rank[n]++;
		}
		else if(rank[m] > rank[n])
			parent[n] = m;
		else
			parent[m] = n;
		size--;
	}
	public int getSize() {return size;}
}
