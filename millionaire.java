//Wang Yaxin A0258848H
import java.util.*;
import java.io.*;
class vault {//create a class to store the height difference between each element of the grid
    public int row;
    public int col;
    public int heightDifference;
    public vault (int row, int col, int heightDifference) {
        this.row = row;
        this.col = col;
        this.heightDifference = heightDifference;
    }
}
class vaultComparator implements Comparator<vault> {//implement a comparator to compare between two elements based on the height difference
    @Override
    public int compare (vault v1, vault v2) {
        if (v1.heightDifference < v2.heightDifference) {
            return -1;
        }
        else if (v1.heightDifference > v2.heightDifference) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

public class millionaire {
    public static void main(String[] args) {//apply Prim's algo
        Kattio io = new Kattio(System.in, System.out);
        int length = io.getInt();//read in the length of grid
        int width = io.getInt();//read in the width of the grid
        int[][] vaultGrid = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                vaultGrid[i][j] = io.getInt();//read in all the height
            }
        }
        PriorityQueue<vault> pQueue = new PriorityQueue<vault>(new vaultComparator());//create a pqueue to store the distance
        pQueue.add(new vault(0, 0, 0));
        boolean[][] visitedArray = new boolean[length][width];//create a boolean array to keep track on visited
        visitedArray[0][0] = true;
        int maxHeight = 0;
        int[] rowDirectoin = {1, -1, 0, 0};//horizontal direction
        int[] colDirection = {0, 0, -1, 1};//vertical direction
        while (!pQueue.isEmpty() && !visitedArray[length - 1][width - 1]) {
            vault temp = pQueue.poll();//poll the smallest height difference
            visitedArray[temp.row][temp.col] = true;
            maxHeight = Math.max(maxHeight, temp.heightDifference);//update the maxHeight
            for (int i = 0; i < 4; i++) {
                int nextRow = temp.row + rowDirectoin[i];
                int nextCol = temp.col + colDirection[i];
                if (nextRow >= 0 && nextRow < length && nextCol >= 0 && nextCol < width && !visitedArray[nextRow][nextCol]) {
                    int nextHeightDifference = vaultGrid[nextRow][nextCol] - vaultGrid[temp.row][temp.col];
                    vault next = new vault(nextRow, nextCol, nextHeightDifference);
                    pQueue.add(next);//add new element into the pqueue
                }
            }
        }
        io.println(maxHeight);//output the maxHeight
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