//Wang Yaxin A0258848H
import java.util.*;
import java.io.*;
class Vertex<T> {//create a class to store the vertices
	public ArrayList<Vertex<T>> previousNodes;
	public ArrayList<Vertex<T>> nextNodes;
	public T value;
	public boolean[] isVisited;	
	public Vertex(T value) {
		this.value = value;
		this.previousNodes = new ArrayList<Vertex<T>>();
		this.nextNodes = new ArrayList<Vertex<T>>();
		isVisited = new boolean[2];
	}
}
class DirectedGraph {//create a class for directed graph
	public ArrayList<Vertex<Integer>> vertexSet;
	public int totalVertices;	
	public DirectedGraph(int totalVertices) {
		vertexSet = new ArrayList<Vertex<Integer>>();
		this.totalVertices = totalVertices;
		for (int i = 0; i < totalVertices + 1; i++) {
			vertexSet.add(new Vertex<Integer>(i));
		}
	}	
	public void addEdge(int head, int tail) {//add edge based on the given lines
		vertexSet.get(head).nextNodes.add(vertexSet.get(tail));
		vertexSet.get(tail).previousNodes.add(vertexSet.get(head));
	}	
	public ArrayList<Integer> depthFirstSearch(Vertex<Integer> currentVertex, ArrayList<Integer> result) {//apply DFS
		if (currentVertex.isVisited[0]) {
			return result;
		}
		currentVertex.isVisited[0] = true;
		if (currentVertex.nextNodes.isEmpty()) {
			result.add(currentVertex.value);
			return result;
		}
		for (int i = 0; i < currentVertex.nextNodes.size(); i++) {
			result = depthFirstSearch(currentVertex.nextNodes.get(i), result);
		}
		result.add(currentVertex.value);
		return result;
	}	
	public void depthFirstSearch(Vertex<Integer> currentVertex) {
		if (currentVertex.isVisited[1]) {
			return;
		}
		currentVertex.isVisited[1] = true;
		if (currentVertex.nextNodes.isEmpty()) {
			return;
		}
		for (int i = 0; i < currentVertex.nextNodes.size(); i++) {
			depthFirstSearch(currentVertex.nextNodes.get(i));
		}		
	}
	public ArrayList<Integer> topologicalSort() {//apply topological sort
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i < vertexSet.size(); i++) {
			if (!vertexSet.get(i).isVisited[0]) {
				result = depthFirstSearch(vertexSet.get(i), result);
			}
		}
		Collections.reverse(result);
		return result;
	}	
	public int countStronglyConnectedComponents() {//count the number of SCCs
		int SCCCount = 0;
		ArrayList<Integer> list = this.topologicalSort();
		for (int i = 0; i < list.size(); i++) {
			if (!vertexSet.get(list.get(i)).isVisited[1]) {
				SCCCount++;
				depthFirstSearch(vertexSet.get(list.get(i)));
			}
		}
		return SCCCount;
	}
}
public class dominos {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		int testCases = io.getInt();//read in the number of test cases
		for (int i = 0; i < testCases; i++) {
			int dominoCount = io.getInt();//read in the number of tiles
			int lineCount = io.getInt();//read in the number of commands
			DirectedGraph dominoGraph = new DirectedGraph(dominoCount);
			for (int j = 0; j < lineCount; j++) {
				int head = io.getInt();
				int tail = io.getInt();
				dominoGraph.addEdge(head, tail);					
			}
            io.println(dominoGraph.countStronglyConnectedComponents());//output the result
		}
        io.close();
	}
}
//Original Kattio code
class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public boolean hasMoreTokens() {
        return peekToken() != null;
    }
    public int getInt() {
        return Integer.parseInt(nextToken());
    }
    public double getDouble() {
        return Double.parseDouble(nextToken());
    }
    public long getLong() {
        return Long.parseLong(nextToken());
    }
    public String getWord() {
        return nextToken();
    }
    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;
    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }
    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}