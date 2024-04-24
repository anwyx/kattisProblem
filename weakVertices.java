import java.util.*;
import java.io.*;
public class weakVertices {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();//read in the number of vertices
        while (n != -1) {
            int[][] adjacencyMatrix = new int[n][n];//create the adjacency matrix
            boolean[] checkWeak = new boolean[n];//create a check array to check whether a vertex is weak or not
            int countEdge = 0;
            for (int i = 0; i < n; i++) {
                int[] edge = new int[n];
                checkWeak[i] = false;//set false as default
                for (int j = 0; j < n; j++) {
                    edge[j] = io.getInt();
                    if (edge[j] == 1) {
                        countEdge++;
                    }
                    adjacencyMatrix[i][j] = edge[j];
                }
            }
            if (countEdge == n - 1) {//if this graph is a tree, there is no cycle, hence output all the vertices
                for (int i = 0; i < n - 1; i++) {
                    io.print(i + " ");
                }
                io.print(n - 1);
            }
            else {
                for (int i = 0; i < n; i++) {
                    if (checkWeak[i]) {
                        continue;//if vertex if already not weak, continue
                    }
                    else {
                        for (int j = 0; j < n; j++) {
                            for (int k = j + 1; k < n; k++) {
                                if (adjacencyMatrix[i][j] == 1 && adjacencyMatrix[i][k] == 1 && adjacencyMatrix[j][k] == 1) {
                                    checkWeak[i] = true;
                                    checkWeak[j] = true;
                                    checkWeak[k] = true;//when there is a triangle with vertex i, j, k, set these vertices true
                                }
                            }
                        }
                    }
                    if (!checkWeak[i]) {
                        io.print(i + " ");//print out the vertex if there is no triangle on it
                    }
                }
            }
            io.println();
            n = io.getInt();//read in the next number of vertices
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
