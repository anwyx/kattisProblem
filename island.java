import java.util.*;
import java.io.*;
class GridCell {//create a class to store the row index and column index of each grid element
    public int rowIndex;
    public int colIndex;
    public GridCell (int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }
}
public class island {
    public static int handWrittenBFS (char[][] gridElement, boolean[][] visitedArray, int rowNum, int colNum) {//write a BFS
        int output = 0;
        int[] rowDirectoin = {1, -1, 0, 0};//horizontal direction
        int[] colDirection = {0, 0, -1, 1};//vertical direction
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (gridElement[i][j] == 'L' && !visitedArray[i][j]) {//if element is land and not be visited
                    visitedArray[i][j] = true;//mark as visited
                    Queue<GridCell> connectedCells = new ArrayDeque<GridCell>();
                    connectedCells.add(new GridCell(i, j));//add the elment to the queue
                    while (!connectedCells.isEmpty()) {
                        GridCell currentCell = connectedCells.poll();
                        for (int k = 0; k < 4; k++) {//go to the four direction of the selected element
                            int nextRow = currentCell.rowIndex + rowDirectoin[k];
                            int nextCol = currentCell.colIndex + colDirection[k];
                            if (nextRow >= 0 && nextRow < rowNum && nextCol >= 0 && nextCol < colNum && gridElement[nextRow][nextCol] != 'W' && !visitedArray[nextRow][nextCol]) {
                                visitedArray[nextRow][nextCol] = true;//if the next element got from the four directions is still in the grid and is not water and not visited, mark as visited
                                connectedCells.add(new GridCell(nextRow, nextCol));//add the next element into the queue
                            }
                        }
                    }
                    output ++;//increment the number of island each time
                } else if (gridElement[i][j] == 'W') {
                    visitedArray[i][j] = true;//mark water element as visited
                }
            }
        }
        return output;
    }
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int rowNum = io.getInt();
        int colNum = io.getInt();
        char[][] gridElement = new char[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            String rowString = io.getWord();
            for (int j = 0; j < colNum; j++) {
                gridElement[i][j] = rowString.charAt(j);//read in the grid as a matrix
            }
        }
        boolean[][] visitedArray = new boolean[rowNum][colNum];//initialize the visited boolean array
        int output = handWrittenBFS(gridElement, visitedArray, rowNum, colNum);
        io.println(output);//output the result
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
