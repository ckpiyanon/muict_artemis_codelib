import java.util.ArrayList;
import java.util.BitSet;
import java.util.Stack;

public class Main {
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(8);
	static ArrayList<ArrayList<Integer>> graphi = new ArrayList<ArrayList<Integer>>(8);
	static void addEdge(int u,int v) {
		graph.get(u).add(v); graphi.get(v).add(u);
	}
	public static void main(String args[]) throws Exception {
		for(int i = 0;i < 8;i++) {
			graph.add(new ArrayList<Integer>());
			graphi.add(new ArrayList<Integer>());
		}
		int u[] = {0,1,2,3,3,4,5,7,6},v[] = {1,3,1,2,4,5,7,6,4};
		for(int i = 0;i < u.length;i++)	addEdge(u[i],v[i]);
		for(int i = 0;i < 8;i++) if(!visited.get(i))	dfs(i);
		int numscc = 0;
		visited.clear();
		while(!stack.empty()) {
			int node = stack.pop();
			if(visited.get(node))	continue;
			dfsi(node);
			numscc++;
			System.out.println();
		}
		System.out.println("There are " + numscc + " SCCs.");
	}
	static BitSet visited = new BitSet(8);
	static Stack<Integer> stack = new Stack<Integer>();
	static void dfs(int u) {
		visited.set(u);
		for(Integer v:graph.get(u)) if(!visited.get(v))	dfs(v);
		stack.push(u);
	}
	static void dfsi(int u) {
		visited.set(u);
		System.out.print(u + " ");
		for(Integer v:graphi.get(u)) if(!visited.get(v))	dfsi(v);
	}
}
