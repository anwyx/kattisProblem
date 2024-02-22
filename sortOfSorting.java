import java.util.*;
public class sortOfSorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String[]> cases = new ArrayList<>();//store different cases of array into an arraylist
        for (int i = 0; i <= 500; i++) {
            int n = sc.nextInt();
            if (n == 0) break;
            sc.nextLine();
            String[] studentName = new String[n];//new an array to read in and store the names of the students
            for (int j = 0; j < n; j++) {
                studentName[j] = sc.nextLine();
            }
            cases.add(studentName);//add the arrays to the arraylist of cases
        }
        for (String[] studentName : cases) {
            radixSort(studentName);//sort all the name using radix sort
        }
        for (int k = 0; k < cases.size(); k++) {
            String[] studentName = cases.get(k);
            for (String name : studentName) {
                System.out.println(name);//print out all the output
            }
            if (k < cases.size() - 1) {
                System.out.println();//print a blank line bewteen each cases
            }
        }
    }
    private static void countingSort (String[] studentName, int digit) {
        int n = studentName.length;
        int[] count = new int[58];
        for (String name : studentName) {
            char a = name.charAt(digit);
            int b = a - 'A';
            count[b]++;//count the number of each letter
        }
        for (int i = 1; i < 58; i++) {
            count[i] += count[i - 1];//store the position of the array
        }
        String[] output = new String[n];
        for (int j = n - 1; j >= 0; j--) {
            char c = studentName[j].charAt(digit);
            int d = c - 'A';
            output[count[d] - 1] = studentName[j];
            count[d]--;
        }
        System.arraycopy(output, 0, studentName, 0, n);
    }
    private static void radixSort(String[] studentName) {
        countingSort(studentName, 1);//sort second letter first
        countingSort(studentName, 0);//then sort first letter
    }
}
