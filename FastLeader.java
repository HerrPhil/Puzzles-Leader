import java.util.*;

public class FastLeader {

    public static void main(String [] args) {
        System.out.printf("Hello fast leader solution%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java FastLeader%n");
            return;
        }
        int [] A = new int [] {6, 8, 4, 6, 8, 6, 6};
        FastLeader fastLeader = new FastLeader();
        int leader = fastLeader.solution(A);
        System.out.printf("The leader of %s is %d%n", Arrays.toString(A), leader);
    }

    public int solution(int [] A) {

        int n = A.length;
        int leader = -1;
        Arrays.sort(A);
        int candidate = A[n / 2];
        int count = 0;

        // performance
        int checks = 0;
        int iterations = 0;

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

        System.out.printf("Performance: checks = %d, iterations = %d%n", checks, iterations);

        return leader;

    }

}
