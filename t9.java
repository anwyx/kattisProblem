//Wang Yaxin A0258848H
import java.util.Scanner;
public class t9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < number; i++) {
            String message = sc.nextLine();
            String alreadyConverted = "";
            for (int j = 0; j < message.length(); j++) {
                String toBeConverted = convertToKey(message.substring(j, j + 1));
                if (!alreadyConverted.isEmpty() && !toBeConverted.isEmpty() && alreadyConverted.charAt(alreadyConverted.length() - 1) == toBeConverted.charAt(0)) {//chech whether the two adjacent char are same
                    alreadyConverted += (" " + toBeConverted);
                }
                else {
                    alreadyConverted += toBeConverted;
                }
            }
            int index = i + 1;
            System.out.println("Case #" + index + ": " + alreadyConverted);
        }
    }
    public static String convertToKey(String message) {
        String converted = "";//form an empty string to store future converted digits
        String[][] T9 = {
            {" "},
            {},
            {"a","b", "c"},
            {"d", "e", "f"},
            {"g", "h", "i"},
            {"j", "k", "l"},
            {"m", "n", "o"},
            {"p", "q", "r", "s"},
            {"t", "u", "v"},
            {"w", "x", "y", "z"}};//form a 2D array to simulate Keypresses
        for (int i = 0; i < T9.length; i ++) {
            for (int j = 0; j < T9[i].length; j++) {
                if (T9[i][j].equals(message)) {
                    for (int k = 0; k <= j; k++) {
                        String add = String.valueOf(i);
                        converted += add;//add the amount of time of that corresponding digit into the output string
                    }
                }
            }
        }
        return converted;
    }
}
