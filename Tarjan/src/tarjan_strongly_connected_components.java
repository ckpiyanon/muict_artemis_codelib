import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class tarjan_strongly_connected_components {
	Stack<Integer> CYCLE;
	StringTokenizer tk;
	ArrayList<Integer> street[];
	int num[], low[], count, prev[], size;
	ArrayList<Integer> adjList[];
	int countCC;
	boolean satisfied;

	public static void main(String[] args)throws Exception {
		new tarjan_strongly_connected_components().run();
	}
	
	public void run()throws Exception {
		System.setIn(new FileInputStream("src/UVA11838/in.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String set = bf.readLine();
		while(!set.equals("0 0")){
			tk = new StringTokenizer(set);
			int n = Integer.parseInt(tk.nextToken())+1;
			int m = Integer.parseInt(tk.nextToken());
			
			adjList = new ArrayList[n];
			for(int i=0; i<n; i++) adjList[i] = new ArrayList<Integer>();
			
			for(int i=0; i<m; i++){
				String data = bf.readLine();
				tk = new StringTokenizer(data);
				int v = Integer.parseInt(tk.nextToken()), w = Integer.parseInt(tk.nextToken()), p = Integer.parseInt(tk.nextToken());
				if(p==1) adjList[v].add(w);
				else{
					adjList[v].add(w);
					adjList[w].add(v);
				}
			}
			CYCLE = new Stack<Integer>();
			set = bf.readLine();
			
			satisfied = true;
			prev = new int[n]; num = new int[n]; low = new int[n];
			//------------------------//
			countCC = 1;
			count = 1;
			tarjan(1); 
			if(countCC != n-1) satisfied = false;
			if(satisfied) System.out.println("1");
			else System.out.println("0");
		}
	}
	
	// STRONGLY CONNECTED COMPANENT LOOK AT MINI CYCLE IN THE GRAPH
	// USALLY COUNT HOW MANY CYCLE IN THE WHOLE GRAPH
	public void tarjan(int u){
		CYCLE.push(u);				// STACK THE NODE THE FIRST ONE MAY NOT BE THE FIRST TO POP
		num[u] = low[u] = ++count;
		for(int v : adjList[u]){
			if(num[v]==0){
				tarjan(v);
				low[u] = Math.min(low[u], low[v]);
			}
			else if (CYCLE.contains(v)) low[u] = Math.min(low[u], num[v]);
		}
		if(num[u]==low[u]){			// CYCLE HAPPEN
			int v = CYCLE.pop();
			while(u!=v){			// POP THE STACK TO CLEAR IT (THE LAST CYCLE GOES FIRST)
				v=CYCLE.pop();
				countCC++;			// COUNT THE CYCLE
			}						// END OF THAT GROUP
		}
	}
}
