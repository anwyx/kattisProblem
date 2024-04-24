import java.util.*;
import java.io.*;
class coordinate {//create a class to store the coordinate
    public double xCoor;
    public double yCoor;
    public coordinate(double xCoor, double yCoor) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }
}
class weightPair {//create a class to store the index and weight pair
    public int index;
    public double weight;
    public weightPair (int index, double weight) {
        this.index = index;
        this.weight = weight;
    }
}
class weightComparator implements Comparator<weightPair> {//comparator of the weight
    @Override
    public int compare (weightPair wp1, weightPair wp2) {
        if (wp1.weight < wp2.weight) {
            return -1;
        }
        else if (wp1.weight > wp2.weight) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
public class humanCannonball {
    static double getDistance (coordinate c1, coordinate c2) {//method to calculate the distance between two coordinates
        double a = Math.pow(c1.xCoor - c2.xCoor, 2);
        double b = Math.pow(c1.yCoor - c2.yCoor, 2);
        double c = Math.sqrt(a + b);
        return c;
    }
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        double initialxCoor = io.getDouble();//read in the coordinate of initial point
        double initialyCoor = io.getDouble();
        coordinate initialCoor = new coordinate(initialxCoor, initialyCoor);
        double finalxCoor = io.getDouble();//read in the coordinate of final point
        double finalyCoor = io.getDouble();
        coordinate finalCoor = new coordinate(finalxCoor, finalyCoor);
        int numOfCannon = io.getInt();//read in number of cannons
        double[][] cannonCoordinate = new double[numOfCannon][2];
        for (int i = 0; i < numOfCannon; i++) {//read in all the cooordiantes of cannons and store them inside a 2D array
            cannonCoordinate[i][0] = io.getDouble();
            cannonCoordinate[i][1] = io.getDouble();
        }
        double[][] adjacencyMatrix = new double[numOfCannon + 2][numOfCannon + 2];//create an adjacency matrix to store the time between each vertex
        adjacencyMatrix[0][1] = getDistance(initialCoor, finalCoor) / 5;
        adjacencyMatrix[1][0] = getDistance(initialCoor, finalCoor) / 5;
        for (int i = 0; i < numOfCannon; i++) {
            double dis = Math.sqrt(Math.pow((initialxCoor - cannonCoordinate[i][0]), 2) + Math.pow((initialyCoor - cannonCoordinate[i][1]), 2));
            adjacencyMatrix[0][i + 2] = dis / 5; 
            double t = Math.abs(dis - 50) / 5;
            adjacencyMatrix[i + 2][0] = t + 2; 
        }
        for (int i = 0; i < numOfCannon; i++) {
            double dis = Math.sqrt(Math.pow((finalxCoor - cannonCoordinate[i][0]), 2) + Math.pow((finalyCoor - cannonCoordinate[i][1]), 2));
            adjacencyMatrix[1][i + 2] = dis / 5; 
            double t = Math.abs(dis - 50) / 5;
            adjacencyMatrix[i + 2][1] = t + 2; 
        }
        for (int i = 0; i < numOfCannon; i++) {
            for (int j = 0; j < numOfCannon; j++) {
                double dis = Math.sqrt(Math.pow((cannonCoordinate[j][0] - cannonCoordinate[i][0]), 2) + Math.pow((cannonCoordinate[j][1] - cannonCoordinate[i][1]), 2));
                double t = Math.abs(dis - 50) / 5;
                adjacencyMatrix[i + 2][j + 2] = t + 2;
                adjacencyMatrix[j + 2][i + 2] = t + 2;
            }
        }
        PriorityQueue<weightPair> pq = new PriorityQueue<weightPair>(new weightComparator());//create a pq to use for Dijkstra algo
        int[] visitedArray = new int[numOfCannon + 2];
        double[] time = new double[numOfCannon + 2];
        for (int i = 0; i < numOfCannon + 2; i++) {
            time[i] = 10000000000.0;
        }
        pq.add(new weightPair(0, 0));
        while (pq.size() > 0) {
            weightPair temp = pq.poll();
            if (visitedArray[temp.index] == 0) {
                visitedArray[temp.index] = 1;
                for (int i = 0; i < numOfCannon + 2; i++) {
                    boolean reached = false;
                    if (visitedArray[i] == 0 && time[i] > temp.weight + adjacencyMatrix[temp.index][i] && i != temp.index) {
                        reached = true;
                    }
                    if (reached == true) {
                        time[i] = temp.weight + adjacencyMatrix[temp.index][i];
                        pq.add(new weightPair(i, time[i]));
                    }
                }
            }
        }
        io.println(time[1]);//print out the answer
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
