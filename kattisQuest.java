//Wang Yaxin A0258848H
import java.util.*;
import java.io.*;
class addedQuest {//create a class to store the added energy, gold and index
    public long energy;
    public long gold;
    public int index;
    public addedQuest (long energy, long gold, int index) {
        this.energy = energy;
        this.gold = gold;
        this.index = index;
    }
}
public class kattisQuest {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        TreeMap<addedQuest, Long> questMap = new TreeMap<>(new questCompare());//create a tree map to store the quest and its gold earned
        for (int i = 0; i < n; i++) {
            String command = io.getWord();
            if (command.equals("add")) {//if command is add, read in the energy and gold and store them in the tree map
                long energy = io.getLong();
                long gold = io.getLong();
                addedQuest quest = new addedQuest(energy, gold, i);
                questMap.put(quest, gold);
            } else if (command.equals("query")) {//if command is query, perform query method and use the given greedy algorithm to iterate through the tree map
                long energy = io.getLong();
                long goldEarned = query(questMap, energy, n);
                io.println(goldEarned);//output the total gold earned
            }
        }
        io.close();
    }
    public static long query(TreeMap<addedQuest, Long> questMap, long energy, int n) {
        long goldEarned = 0;//initialize the total gold earned
        while (energy > 0 && !questMap.isEmpty()) {
            Map.Entry<addedQuest, Long> nextSmallEntry = questMap.floorEntry(new addedQuest(energy, Long.MAX_VALUE, n));//create the next smaller entry such that the entry is just left to the energy we want to find
            if (nextSmallEntry == null) {
                break;//if there is no such left entry, break the loop
            }
            long nseEnergy = nextSmallEntry.getKey().energy;
            long nseGold = nextSmallEntry.getValue();
            goldEarned += nseGold;//increment the total gold earned
            energy -= nseEnergy;//decrement the energy left
            questMap.remove(nextSmallEntry.getKey());//remove the quest that we have used
        }
        return goldEarned;
    }
}
class questCompare implements Comparator<addedQuest> {//create a comparator for the tree map
    public int compare(addedQuest aq1, addedQuest aq2) {
        if (aq1.energy < aq2.energy) {
            return -1;//compare the energy first, if energy is smaller, put to the left in the tree map
        }
        else if (aq1.energy > aq2.energy) {
            return 1;
        }
        else if (aq1.gold < aq2.gold) {
            return -1;//when energy is equal, compare the gold earned, if gold earned is less, put to the left in the tree map
        }
        else if (aq1.gold > aq2.gold) {
            return 1;
        }
        else if (aq1.index > aq2.index) {
            return -1;//when gold is equal, compare the index which is the order of the command, if index is less, put to the right in the tree map
        }
        else if (aq1.index < aq2.index) {
            return 1;
        } else {
            return 0;
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