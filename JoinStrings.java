import java.util.*;
import java.io.*;
class Node {//form a class for each node containing a string and a next pointer
    public String string;
    public Node next;
    public Node(String string) {
        this.string = string;
        this.next = null;
    }
}
class writtenTLL {//form a handwritten linked list
    public Node head;
    public Node tail;
    public writtenTLL() {
        this.head = null;
        this.tail = null;
    }
    public void addNode(Node node) {//write a function to add node to this linked list
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
    }
}
public class JoinStrings {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);        
        int n = io.getInt();//read in the number of words
        writtenTLL[] array = new writtenTLL[n + 1];//form an array of linked list
        for (int i = 1; i <= n; i++) {
            array[i] = new writtenTLL();
            array[i].addNode(new Node(io.getWord()));//read in and store all the word in terms of linked list
        }
        int first = 1;
        int second = 1;
        for (int i = 0; i < n - 1 ; i++) {
            first = io.getInt();//read in the head word
            second = io.getInt();//read in the tail word
            array[first].tail.next = array[second].head;
            array[first].tail = array[second].tail;//update the linked list array
        }
        Node current;
        for (current = array[first].head; current.next!= null; current= current.next) {
            io.print(current.string);//print out throughout the linked list array
        }
        io.print(current.string);
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
