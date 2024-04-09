import java.util.*;

public class EquiLeader {

    public static void main(String [] args) {
        System.out.printf("Hello equi leader solution%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java EquiLeader%n");
            return;
        }
        int [] A = new int [] {4, 3, 4, 4, 4, 2};
        EquiLeader equiLeader = new EquiLeader();
        int equiLeaders = equiLeader.solution(A);
        System.out.printf("The number of equileaders of %s is %d%n", Arrays.toString(A), equiLeaders);
    }

    public int solution(int [] A) {

        if (A == null || A.length == 0 || A.length == 1) return 0;

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

        int count = 0;
        for (int i = 0; i < n; i++) {

            iterations++;

            checks++;
            if (A[i] == candidate) {

                count++;

            }

        }

        checks++;
        if (count <= n / 2) {

            return 0;

        }

        int leader = candidate;
        int equiCount = 0;
        int leaderCount = 0;
        for (int i = 0; i < n; i++) {

            iterations++;

            checks++;
            if (A[i] == leader) {

                // count of leader in first sub-array
                leaderCount++;

            }

            // the magic sauce
            // we know the count is all leaders in the array.
            // conceptually check the size of two sub-arrays.
            // we also know that the leader count is the count in the first sub-array.
            // therefore the count in the second sub-array must be the remainder of count,
            // since count minus leader count is the remainder of the count of leaders.
            // both counts must be greater than n / 2 to be a leader.

            // threshold to be leader in first sub-array is half of sub-array size
            int leaderThreshold = (i + 1) / 2;
            // remaining leader values in second sub-array
            int otherLeaderCount = count - leaderCount;
            // threshold to be leader in second sub-array is half of sub-array size
            int otherLeaderThreshold = (n - i - 1) / 2;
            checks += 2;
            if (leaderCount > leaderThreshold && otherLeaderCount > otherLeaderThreshold) {

                equiCount++; // both sub-arrays have same leader

            }
        }

        System.out.printf("Performance: checks = %d, iterations = %d%n", checks, iterations);

        return equiCount;

    }

}
