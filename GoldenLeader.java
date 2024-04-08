import java.util.*;

public class GoldenLeader {

    public static void main(String [] args) {
        System.out.printf("Hello golden leader solution%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java GoldenLeader%n");
            return;
        }
        int [] A = new int [] {6, 8, 4, 6, 8, 6, 6};
        GoldenLeader goldenLeader = new GoldenLeader();
        int leader = goldenLeader.solution(A);
        System.out.printf("The leader of %s is %d%n", Arrays.toString(A), leader);
    }

    public int solution(int [] A) {

        int n = A.length;
        int size = 0;
        int value = -1;

        // performance
        int checks = 0;
        int iterations = 0;

        for (int i = 0; i < n; i++) {

            iterations++;

            checks++;

            if (size == 0) {

                size++;
                value = A[i];

            } else {

                checks++;

                if (value != A[i]) {

                    size--;

                } else {

                    size++;

                }

            }

            System.out.printf("stack pair value = %d, index = %d, A[i] = %d, size = %d%n", value, i,  A[i], size);

        }

        int candidate = -1;

        checks++;
        if (size > 0) {

            candidate = value;

        }
        int leader = -1;
        int count = 0;
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
