//Wang Yaxin
import java.util.*;
import java.io.*;
class Researcher {//add a class to store the arriving time and staying duration of the researchers
    public int arrive;
    public int stay;
    public Researcher (int arrive, int stay) {
        this.arrive = arrive;
        this.stay = stay;
    }
}
public class assignWorkstation {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int m = io.getInt();
        PriorityQueue<Researcher> researchersList = new PriorityQueue<Researcher>(n, new researcherCompare());//create a priority queue to store the time of each researcher
        PriorityQueue<Integer> workingWorkstation = new PriorityQueue<>();//create a priority queue to store the locking time of each working workstation         
        for (int i = 0; i < n; i++) {
            int arrive = io.getInt();
            int stay = io.getInt(); 
            researchersList.add(new Researcher(arrive, stay));    
        }
        int numOfSavingUnlock = 0;//initialize the number of saving unlocks
        if (n == 1) {
            io.println(0);
        }    
        else {
            for (int i = 0; i < n; i++) {
                Researcher rs = researchersList.remove(); 
                while (workingWorkstation.size() > 0 && workingWorkstation.peek() < rs.arrive) { 
                    workingWorkstation.remove();//if the workstation reaches the locking time, remove this workstation from the working list
                }        
                if (workingWorkstation.size() > 0 && rs.arrive >= workingWorkstation.peek() - m && rs.arrive <= workingWorkstation.peek()) {//if a researcher arrive just between the finish of previous researcher and the locking time of that workstation
                    workingWorkstation.remove();//we remove this workstation from the list first
                    numOfSavingUnlock++;//add the number of saving unlocks
                }
                int next = rs.arrive + rs.stay;
                workingWorkstation.add(next + m);//add another workstation locking time to the list
            }      
            io.println(numOfSavingUnlock);
        }
        io.close();
    }
}
class researcherCompare implements Comparator<Researcher> {//write a comparator for the priority queue
    public int compare(Researcher rs1, Researcher rs2) {
        if (rs1.arrive < rs2.arrive) {
            return -1;
        }
        else if (rs1.arrive == rs2.arrive) {
            return 0;
        } else {
            return 1;
        }
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