import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/discuss/post/2544063/oa-uber-by-anonymous_user-o5jx/
 */
public class CableConnection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of systems
        int N = scanner.nextInt();

        // Read system states (1=ON, 0=OFF)
        int[] states = new int[N];
        for (int i = 0; i < N; i++) {
            states[i] = scanner.nextInt();
        }

        // Read distances from first system
        long[] distances = new long[N];
        for (int i = 0; i < N; i++) {
            distances[i] = scanner.nextLong();
        }

        long result = calculateMinCableLength(N, states, distances);
        System.out.println(result);

        long result2 = minCableLength(states, Arrays.stream(distances).mapToInt(d -> (int) d).toArray());
        System.out.println(result2);

        scanner.close();
    }

    public static int minCableLength(int[] systemState, int[] dist) {
        int minCableLength = 0;

        List<Integer> offIndex = IntStream.range(0, systemState.length)
                .filter(i -> systemState[i] == 0)
                .boxed()
                .collect(Collectors.toList());
        for (Integer i : offIndex) {
            if (i == 0) {
                minCableLength += dist[i];
            } else {
                Integer leftOnComputerDist = dist[i] - dist[i - 1];
                Integer rightOnComputerDist = null;
                Integer rightOnComputerInd = i + 1;
                while (rightOnComputerInd < systemState.length && systemState[rightOnComputerInd] != 1) {
                    rightOnComputerInd++;
                }
                if (rightOnComputerInd < systemState.length && systemState[rightOnComputerInd] == 1) {
                    rightOnComputerDist = dist[rightOnComputerInd] - dist[i];
                }
                if (rightOnComputerDist != null) {
                    minCableLength += leftOnComputerDist < rightOnComputerDist ? leftOnComputerDist
                            : rightOnComputerDist;
                } else {
                    minCableLength += leftOnComputerDist;
                }
            }
        }
        return minCableLength;
    }

    public static long calculateMinCableLength(int N, int[] states, long[] distances) {
        long totalCableLength = 0;

        // Create an array to mark which systems are powered (either originally ON or
        // connected)
        boolean[] powered = new boolean[N];

        // Initially, mark all ON systems as powered
        for (int i = 0; i < N; i++) {
            if (states[i] == 1) {
                powered[i] = true;
            }
        }

        // First pass: connect each OFF system to the nearest powered system
        for (int i = 0; i < N; i++) {
            if (states[i] == 0) {
                // Find the nearest powered system
                int leftPowered = -1;
                for (int j = i - 1; j >= 0; j--) {
                    if (powered[j]) {
                        leftPowered = j;
                        break;
                    }
                }

                int rightPowered = -1;
                for (int j = i + 1; j < N; j++) {
                    if (powered[j]) {
                        rightPowered = j;
                        break;
                    }
                }

                // Connect to the closer powered system
                if (leftPowered != -1 && rightPowered != -1) {
                    long leftDist = distances[i] - distances[leftPowered];
                    long rightDist = distances[rightPowered] - distances[i];
                    if (leftDist <= rightDist) {
                        totalCableLength += leftDist;
                        powered[i] = true; // This system is now powered
                    } else {
                        totalCableLength += rightDist;
                        powered[i] = true; // This system is now powered
                    }
                } else if (leftPowered != -1) {
                    totalCableLength += distances[i] - distances[leftPowered];
                    powered[i] = true;
                } else if (rightPowered != -1) {
                    totalCableLength += distances[rightPowered] - distances[i];
                    powered[i] = true;
                }
            }
        }

        return totalCableLength;
    }
}