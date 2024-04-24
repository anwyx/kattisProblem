//Wang Yaxin A0258848H
import java.util.*;
import java.io.*;
class Operation {//form a class to store each operation
    public String command;
    public int integer;
    public Operation(String command, int integer) {
        this.command = command;
        this.integer = integer;
    }
}
public class Teque {
    public static void main(String[] args){
    Kattio io = new Kattio(System.in, System.out);
        int number = io.getInt();//read in the number of operations
        HashMap<Integer, Integer> HM1 = new HashMap<>();
        HashMap<Integer, Integer> HM2 = new HashMap<>();//form two hash map to store the data, HM1 is the first half, HM2 is the second half
        int HM1first = 0;
        int HM1last = 1;
        int HM2first = 0;
        int HM2last = 1;//initialize the first and last index of the elements in two hash maps
        for (int i = 0; i < number; i++) {
            Operation input = new Operation(io.getWord(), io.getInt());                 
            if (input.command.equals("get")) {
                if (input.integer < HM1.size()) {
                    io.println(HM1.get(input.integer + HM1first + 1));//if index wanted to find is smaller than the size of the first half, find it in HM1
                } else {
                    io.println(HM2.get(input.integer - HM1.size() + HM2first + 1));//else find it in HM2
                }
            }
            else if (input.command.equals("push_front")){
                HM1.put(HM1first--, input.integer);//store the data in the front of HM1
            }
            else if (input.command.equals("push_back")) {
                HM2.put(HM2last++, input.integer);//store the data in the back of HM2
            } else {
                HM1.put(HM1last++, input.integer);//store the data at the back of HM1
            }     
            if (HM1.size() < HM2.size()) {//move the first element of HM2 to the back of HM1 to ensure the middle element's position
                HM1.put(HM1last, HM2.get(HM2first + 1));
                HM1last++;
                HM2first++;
                HM2.remove(HM2first);
            }
            if (HM1.size() - 1 > HM2.size()) {//move the last element of HM1 to the front of HM2 to ensure the middle element's position
                HM2.put(HM2first, HM1.get(HM1last - 1));
                HM2first--;
                HM1last--;
                HM1.remove(HM1last);
            }
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