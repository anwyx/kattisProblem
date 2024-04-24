import java.io.*;
import java.util.*;
class Road implements Comparable<Road> {//create a class to store each edge's vertices and weight
    public int firstVillage;
    public int secondVillage;
    public int distance;
    public Road(int firstVillage, int secondVillage, int distance) {
        this.firstVillage = firstVillage;
        this.secondVillage = secondVillage;
        this.distance = distance;
    }
    @Override
    public int compareTo (Road other) {//write a compare to compare the weight of two given edges
        if (this.distance < other.distance) {
            return -1;
        }
        else if (this.distance > other.distance) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
public class lostMap {
    public static void main(String[] args) {//use Kruskal algo to generate mst on the given graph
        Kattio io = new Kattio(System.in, System.out);
        int numVillages = io.getInt();//read in the number of vertices
        List<Road> roadList = new ArrayList<>();//create a list to store the edges
        for (int i = 0; i < numVillages; i++) {
            for (int j = 0; j < numVillages; j++) {
                int distance = io.getInt();
                if (i < j) {
                    roadList.add(new Road(i + 1, j + 1, distance));
                }
            }
        }
        Collections.sort(roadList);//sort the edges based on their weights
        List<Road> mstRoad = new ArrayList<>();//create a list to store the minimum spanning tree of the graph
        int[] parent = new int[numVillages + 1];//create the parent array of each edge element
        for (int i = 1; i <= numVillages; i++) {
            parent[i] = i;
        }        
        for (Road road : roadList) {
            int firstParent = findParent(parent, road.firstVillage);
            int secondParent = findParent(parent, road.secondVillage);            
            if (firstParent != secondParent) {
                mstRoad.add(road);//if the two edge are not in the same tree, add it to the mst
                parent[firstParent] = secondParent;//union the two edges into same set
            }
        }
        for (Road road : mstRoad) {
            io.println(road.firstVillage + " " + road.secondVillage);//print out the edges that are connected in mst
        }
        io.close();
    }
    private static int findParent(int[] parent, int village) {//write a method to find the parent of each edge element
        if (parent[village] != village) {
            parent[village] = findParent(parent, parent[village]);
        }
        return parent[village];
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
