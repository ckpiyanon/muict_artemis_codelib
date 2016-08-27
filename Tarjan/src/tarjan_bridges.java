import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class tarjan_bridges {
	int num[], low[], count, prev[], size;
	ArrayList<Integer> adjList[];
	ArrayList<PriorityQueue<Integer>> bridge;
	StringTokenizer tk;
	PriorityQueue<Integer> inner;

	public static void main(String[] args)throws Exception {
		new tarjan_bridges().run();
	}
	@SuppressWarnings("unchecked")
	public void run()throws Exception {
		System.setIn(new FileInputStream("src/UVA00796/in.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		while(bf.ready()){
			int no = Integer.parseInt(bf.readLine());
			num = new int[no];
			adjList = new ArrayList[no];
			for(int i=0; i<no; i++) adjList[i] = new ArrayList<Integer>();
			prev = new int[no]; num = new int[no]; low = new int[no];
			for(int i=0; i<no; i++) prev[i] = i;
			
			int line = 0;
			while(line<no){
				String network = bf.readLine();
				tk = new StringTokenizer(network);
				int head = Integer.parseInt(tk.nextToken());
				String tmp = tk.nextToken();
				while(tk.hasMoreTokens()){
					int next = Integer.parseInt(tk.nextToken());
					adjList[head].add(next);
				}
				line++;
			}
			
			bridge = new ArrayList<PriorityQueue<Integer>>();
			for(int i=0 ; i<=no; i++){
				inner = new PriorityQueue<Integer>();
				bridge.add(inner);
			}
			count = 0;
			size = 0;
			for(int i=0; i<no; i++){
				tarjan(i);
			}
			System.out.println(size + " critical links");
//			System.out.println(size);
			for(int i=0; i<=no; i++){
				if(bridge.get(i).size()>0){
					while(!bridge.get(i).isEmpty()){
						System.out.println(i + " - " + bridge.get(i).poll());
					}
				}
			}
			System.out.println();
			bf.readLine();
		}
		bf.close();
	}
	
	void tarjan (int u){
		num[u] = low[u] = ++count;    					// HAPPEN WHEN IT HAD ONLY 1 NODE CONNECT ;D
		for(int v: adjList[u]){
			if(num[v]==0){
				prev[v] = u;
				tarjan(v);
				low[u] = Math.min(low[u],  low[v]);
				if(num[v]==low[v]){  					// CHECK IF IT A BRIDGE OR NOT
					size++;
					if(v<u)bridge.get(v).add(u);		// TRY TO ORDER IT AS AESENDING (1,2,3,4,...)
					else bridge.get(u).add(v);
				}
			}else if(prev[u]!=v) low[u] = Math.min(low[u], num[v]);
		}
	}

}
