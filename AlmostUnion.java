import java.io.*;
class writtenUFDS {//create a class to store this UFDS
    public int[] a;
    public int[] b;
    public int[] numOfElement;
    public long[] sumOfSet;
    public writtenUFDS(int numOfInt) {
        this.a = new int[numOfInt + 1];
        this.b = new int[numOfInt + 1];
        this.numOfElement = new int[numOfInt + 1];
        this.sumOfSet = new long[numOfInt + 1];
        for (int i = 1; i < numOfInt + 1; i++) {//initialize the set
            a[i] = i;
            b[i] = i;
            numOfElement[i] = 1;
            sumOfSet[i] = i;
        }
    }
    public int findSet(int element) {//find the set that this element belongs to
        int c = b[element];
        while (c != a[c]) {
            c = a[c];
        }
        b[element] = c;
        return c;
    }
    public void cmd1(int firstElement, int secondElement) {//union the two sets containing the two elements if these two elements are not in the same set
        if (findSet(firstElement) != findSet(secondElement)) {
            int first = findSet(firstElement);
            int second = findSet(secondElement);
            a[first] = second;
            b[firstElement] = second;
            numOfElement[second] += numOfElement[first];
            sumOfSet[second] += sumOfSet[first];
        }
    }
    public void cmd2(int firstElement, int secondElement) {//move first element to the set of second element
        if (findSet(firstElement) != findSet(secondElement)) {
            int first = findSet(firstElement);
            int second = findSet(secondElement);
            b[firstElement] = second;
            numOfElement[first] -= 1;
            numOfElement[second] += 1;
            sumOfSet[first] -= firstElement;
            sumOfSet[second] += firstElement;
        }
    }
    public int findNumber(int element) {//find the number of elements in a given set
        return numOfElement[findSet(element)];
    }
    public long findSum(int element) {//find the sum of elements in a given set
        return sumOfSet[findSet(element)];
    }
    public String numAndSum(int element) {//return the string needed
        int findNumber = numOfElement[findSet(element)];
        long findSum = sumOfSet[findSet(element)];
        return findNumber + " " + findSum;
    }
}
public class AlmostUnion {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split(" ");
            int numOfInt = Integer.parseInt(parts[0]);//read in the number of integer
            int numOfCmd = Integer.parseInt(parts[1]);//read in the number of commands
            writtenUFDS ufds = new writtenUFDS(numOfInt);//create a written UFDS to store the input
            for (int i = 0; i < numOfCmd; i++) {
                line = reader.readLine();
                parts = line.split(" ");
                int cmd = Integer.parseInt(parts[0]);
                if (cmd == 1) {//if command 1, then do the union command
                    int firstElement = Integer.parseInt(parts[1]);
                    int secondElement = Integer.parseInt(parts[2]);
                    ufds.cmd1(firstElement, secondElement);
                } else if (cmd == 2) {//if command 2, then do the move command
                    int firstElement = Integer.parseInt(parts[1]);
                    int secondElement = Integer.parseInt(parts[2]);
                    ufds.cmd2(firstElement, secondElement);
                } else if (cmd == 3) {//if command 3, then output the number and the sum
                    int element = Integer.parseInt(parts[1]);           
                    int numElements = ufds.findNumber(element);
                    long sumElements = ufds.findSum(element);
                    writer.write(numElements + " " + sumElements + "\n");
                }
            }
        }
        reader.close();
        writer.close();
    }
}
