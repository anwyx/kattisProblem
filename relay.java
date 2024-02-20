//Wang Yaxin A0258848H
import java.util.*;
//construct a class to store the name, first leg time and other leg time
class Runner {
    String name;
    double firstLegTime;
    double otherLegTime;

    public Runner(String name, double firstLegTime, double otherLegTime) {
        this.name = name;
        this.firstLegTime = firstLegTime;
        this.otherLegTime = otherLegTime;
    }
}
public class relay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //read input and sotre into an arraylist
        int n = sc.nextInt();
        List<Runner> runners = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            double firstLegTime = sc.nextDouble();
            double otherLegTime = sc.nextDouble();
            runners.add(new Runner(name, firstLegTime, otherLegTime));
        }
        sc.close();
        Collections.sort(runners, new Comparator<Runner>() {
            public int compare(Runner r1, Runner r2) {
                return Double.compare(r1.otherLegTime, r2.otherLegTime);
            }
        });
        double minTotalTime = Double.MAX_VALUE;
        List<Runner> bestTeam = new ArrayList<>();
        //iterate through all combinations of runners
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k < 4; k++) {
                    for (int l = 2; l < 4; l++) {
                        //check if all runners are distinct
                        if (i != j && i != k && i != l && j != k && j != l && k != l) {
                            //calculate total time for this combination
                            double totalTime = runners.get(i).firstLegTime + runners.get(j).otherLegTime + runners.get(k).otherLegTime + runners.get(l).otherLegTime;
                            //update minimum total time and best team and store them
                            if (totalTime < minTotalTime) {
                                minTotalTime = totalTime;
                                bestTeam.clear();
                                bestTeam.add(runners.get(i));
                                bestTeam.add(runners.get(j));
                                bestTeam.add(runners.get(k));
                                bestTeam.add(runners.get(l));
                            }
                        }
                    }
                }
            }
        }
        //output the best team and their total time
        System.out.printf("%.2f\n", minTotalTime);
        for (Runner runner : bestTeam) {
            System.out.println(runner.name);
        }
    }
}