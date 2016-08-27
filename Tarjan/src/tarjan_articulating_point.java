import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class tarjan_articulating_point {
	StringTokenizer tk;
	int num[], low[], rank, prev[], n, root, nChildren, count;
	ArrayList<Integer> adjList[];
	boolean isCritical[];
	LinkedList<Integer> node[];
	
	public static void main(String[] args)throws Exception {
		new tarjan_articulating_point().run();
	}
	
	public void run()throws Exception {
		System.setIn(new FileInputStream("src/UVA00315/in.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int no = Integer.parseInt(bf.readLine())+1;
		
		while(no>1){
			adjList = new ArrayList[no];
			for(int i=0; i<no; i++) adjList[i] = new ArrayList<Integer>();
			prev = new int[no]; num = new int[no]; low = new int[no]; isCritical = new boolean[no];
			for(int i=0; i<no; i++) prev[i] = i;
			String network = bf.readLine();
			root = Integer.parseInt(network.substring(0,1));
			while(!network.equals("0")){
				tk = new StringTokenizer(network);
				int head = Integer.parseInt(tk.nextToken());
				while(tk.hasMoreTokens()){
					int next = Integer.parseInt(tk.nextToken());
					adjList[head].add(next);
					if(!adjList[next].contains(head)) adjList[next].add(head);
				}
				network = bf.readLine();
			}
			count = 0;
			nChildren = 0;
			dfs(root);
			int ans = 0;
			for(int i=0; i<no; i++){
				if(isCritical[i]) ans++;
			}
			if(nChildren>1)System.out.println(ans); 
			else System.out.println(ans-1);
			no = Integer.parseInt(bf.readLine())+1;
		}
		bf.close();
	}
	
	void dfs(int u){								// IMPORTANT TARJAN PART
		num[u] = low[u] = ++count;
		for(int v : adjList[u]){
			if(v>0 && num[v]==0){
				if(u==root) nChildren++;			// CHECK IF ROOT IS ARTICULATING POINT
				prev[v] = u;
				dfs(v);
				isCritical[u] |= (num[u]<=low[v]);   // COUNT THOSE num[u] < low[v]
				low[u] = Math.min(low[u], low[v]);	 // BACKWARD LOW CHECK
			}else if(v>0 && prev[u]!=v) low[u] = Math.min(low[u], num[v]);
		}
	}
}