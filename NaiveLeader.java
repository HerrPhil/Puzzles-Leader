import java.util.*;

public class NaiveLeader {

    public static void main(String [] args) {
        System.out.printf("Hello naive leader solution%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java NaiveLeader%n");
            return;
        }
        int [] A = new int [] {6, 8, 4, 6, 8, 6, 6};
        NaiveLeader naiveLeader = new NaiveLeader();
        int leader = naiveLeader.solution(A);
        System.out.printf("The leader of %s is %d%n", Arrays.toString(A), leader);
    }

    public int solution(int [] A) {

        int n = A.length;
        int leader = -1;

        // performance
        int checks = 0;
        int iterations = 0;
        for (int k = 0; k < n; k++) {

            int candidate = A[k];
            int count = 0;
            iterations++;

            for (int i = 0; i < n; i++) {

                iterations++;
                checks++;
                if (A[i] == candidate) {

                    count++;

                }

            }

            checks++;
            if (count > n / 2) {

                leader = candidate;

            }

        }

        System.out.printf("Performance: checks = %d, iterations = %d%n", checks, iterations);

        return leader;

    }

}
