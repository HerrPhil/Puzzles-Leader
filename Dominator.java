import java.util.*;

public class Dominator {

    public static void main(String [] args) {
        System.out.printf("Hello dominator solution%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java Dominator%n");
            return;
        }
        int [] A = new int [] {6, 8, 4, 6, 8, 6, 6};
        Dominator dominator = new Dominator();
        int index = dominator.solution(A);
        System.out.printf("The first index of the dominator value of %s is %d%n", Arrays.toString(A), index);
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
        int leaderIndex = -1;
        int candidateIndex = -1;
        int count = 0;
        boolean foundFirstIndex = false;
        for (int i = 0; i < n; i++) {

            iterations++;

            checks++;
            if (A[i] == candidate) {

                count++;
                if (!foundFirstIndex) {

                    foundFirstIndex = true;
                    candidateIndex = i;

                }

            }

        }

        checks++;
        if (count > n / 2) {

            leaderIndex = candidateIndex;

        }

        System.out.printf("Performance: checks = %d, iterations = %d%n", checks, iterations);

        return leaderIndex;

    }

}
