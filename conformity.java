import java.util.*;
import java.io.*;
public class conformity {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);        
        int n = io.getInt();//read in the number of students
        HashMap<String, Integer> HM = new HashMap<>();//create a hashmap to store the combined string of courses of students and number of this combi
        int max = 0;//initialize maximum combi to be zero
        int numOfPop = 0;//initialize number of maximum
        for (int i = 0; i < n; i++) {
            String[] courseCombi = new String[5];
            for (int j = 0; j < 5; j++) {
                courseCombi[j] = io.getWord();//read in 5 courses a student takes
            }
            Arrays.sort(courseCombi);//sort the courses in ascending order
            String combinedCourse = courseCombi[0] + courseCombi[1] + courseCombi[2] + courseCombi[3] + courseCombi[4];//combine them to be one string
            HM.put(combinedCourse,HM.getOrDefault(combinedCourse, 0) + 1);//increase the number of that string
            if (HM.get(combinedCourse) > max) {
                max = HM.get(combinedCourse);//update the maximum number
                numOfPop = 1;//number of popular combi is 1
            }
            else if (HM.get(combinedCourse) == max) {
                numOfPop++;//number of popular combi increase
            }
        }
        if (max > 1) {
            io.println(max * numOfPop);//print out the maximum number
        } else {
            io.println(n);//if all of them is one, print out the number of students
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
