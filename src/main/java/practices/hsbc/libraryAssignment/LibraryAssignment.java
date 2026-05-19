package practices.hsbc.libraryAssignment;

/**
 *
 *
 * @since 2026/5/19
 */
import java.util.*;

public class LibraryAssignment {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Read number of subjects
    int M = scanner.nextInt();

    // Read available books
    int[] availableBooks = new int[M];
    for (int i = 0; i < M; i++) {
      availableBooks[i] = scanner.nextInt();
    }

    // Read number of students and number of subjects of issued books per student
    int N = scanner.nextInt();
    int issuedSubjectsNumber = scanner.nextInt(); // should be equal to M

    // Read issued books for each student
    int[][] issued = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        issued[i][j] = scanner.nextInt();
      }
    }

    // Read required books info
    int requiredBookStudents = scanner.nextInt(); // should be equal to N
    int requiredBookSubjects = scanner.nextInt(); // should be equal to M

    // Read required books for each student
    int[][] required = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        required[i][j] = scanner.nextInt();
      }
    }


    // Calculate needs for each student
    int[][] needs = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        needs[i][j] = Math.max(0, required[i][j] - issued[i][j]);
      }
    }

    // Find optimal sequence
    List<Integer> result = findOptimalSequence(N, M, availableBooks, needs, issued);

    // Print result
    if (result.size() == 1 && result.get(0) == -1) {
      System.out.println(-1);
    } else {
      for (int i = 0; i < result.size(); i++) {
        if (i > 0) System.out.print(" ");
        System.out.print(result.get(i));
      }
      System.out.println();
    }

    scanner.close();
  }

  private static List<Integer> findOptimalSequence(int N, int M, int[] available,
      int[][] needs, int[][] issued) {
    List<Integer> result = new ArrayList<>();
    boolean[] completed = new boolean[N];
    int[] currentAvailable = available.clone();

    while (result.size() < N) {
      boolean found = false;

      // Try to find a student who can complete, starting from smallest ID
      for (int studentId = 0; studentId < N; studentId++) {
        if (!completed[studentId] && canComplete(studentId, needs, currentAvailable, M)) {
          // Student can complete
          result.add(studentId);
          completed[studentId] = true;
          found = true;

          // Return issued books to available
          for (int j = 0; j < M; j++) {
            currentAvailable[j] += issued[studentId][j];
          }

          // Break to start scanning from smallest ID again
          break;
        }
      }

      // If no student found in normal scan, try to find any student that can complete
      if (!found) {
        for (int studentId = 0; studentId < N; studentId++) {
          if (!completed[studentId] && canComplete(studentId, needs, currentAvailable, M)) {
            result.add(studentId);
            completed[studentId] = true;
            found = true;

            for (int j = 0; j < M; j++) {
              currentAvailable[j] += issued[studentId][j];
            }
            break;
          }
        }
      }

      // If still no student found, it's impossible
      if (!found) {
        List<Integer> impossible = new ArrayList<>();
        impossible.add(-1);
        return impossible;
      }
    }

    return result;
  }

  private static boolean canComplete(int studentId, int[][] needs, int[] available, int M) {
    for (int j = 0; j < M; j++) {
      if (needs[studentId][j] > available[j]) {
        return false;
      }
    }
    return true;
  }
}
