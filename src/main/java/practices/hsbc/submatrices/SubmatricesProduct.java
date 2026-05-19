package practices.hsbc.submatrices;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://leetcode.com/problems/distribute-elements-into-two-arrays-i
 * 
 * Count the number of submatrices that contain the top-left element (grid[0][0])
 * and have a product of all elements ≤ k.
 *
 * @since 2026/3/28
 */
public class SubmatricesProduct {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read grid dimensions
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Read grid
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] line = sc.nextLine().split(",");
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }

        // Read k
        int k = sc.nextInt();

        System.out.println(countValidSubmatrices(grid, k));
    }

    /**
     * Count the number of valid submatrices containing grid[0][0] with product ≤ k.
     *
     * @param grid The input matrix
     * @param k The product threshold
     * @return Count of valid submatrices
     */
    public static int countValidSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        // Handle edge case: grid[0][0] > k
        if (grid[0][0] > k) {
            return 0;
        }

        // Special case: if grid[0][0] is 0, then all submatrices are valid
        if (grid[0][0] == 0) {
            return (m * (m + 1) / 2) * (n * (n + 1) / 2);
        }

        // Check all possible submatrices starting at (0,0)
        for (int rows = 1; rows <= m; rows++) {
            for (int cols = 1; cols <= n; cols++) {
                // Calculate product of submatrix from (0,0) to (rows-1, cols-1)
                long product = 1;
                boolean valid = true;

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        // Check for overflow before multiplying
                        if (product > k / grid[i][j] && grid[i][j] != 0) {
                            valid = false;
                            break;
                        }
                        product *= grid[i][j];
                    }
                    if (!valid) break;
                }

                if (valid) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Optimized solution using prefix products and binary search for larger grids.
     * This approach is more efficient for larger grids (O(m*n)).
     */
    public static int countValidSubmatricesOptimized(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        // Check if grid[0][0] is already > k
        if (grid[0][0] > k) {
            return 0;
        }

        // If grid[0][0] is 0, all submatrices are valid
        if (grid[0][0] == 0) {
            return (m * (m + 1) / 2) * (n * (n + 1) / 2);
        }

        // For each row, calculate the prefix product array
        for (int i = 0; i < m; i++) {
            long[] prefix = new long[n];
            prefix[0] = grid[i][0];

            // Calculate prefix products for the current row
            for (int j = 1; j < n; j++) {
                // Check for overflow
                if (prefix[j-1] > k / grid[i][j] && grid[i][j] != 0) {
                    prefix[j] = Long.MAX_VALUE;
                } else {
                    prefix[j] = prefix[j-1] * grid[i][j];
                }
            }

            // Count valid submatrices ending at row i
            for (int j = 0; j < n; j++) {
                if (prefix[j] <= k) {
                    count += (i + 1);
                } else {
                    break;
                }
            }
        }

        return count;
    }

    /**
     * Brute force solution for small grids (for testing and understanding)
     */
    public static int countValidSubmatricesBruteForce(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        // Generate all possible submatrices containing (0,0)
        for (int rows = 1; rows <= m; rows++) {
            for (int cols = 1; cols <= n; cols++) {
                // Consider submatrix from (0,0) to (rows-1, cols-1)
                long product = 1;
                boolean valid = true;

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        product *= grid[i][j];

                        // Early termination if product exceeds k
                        if (product > k) {
                            valid = false;
                            break;
                        }

                        // Check for overflow
                        if (product > Long.MAX_VALUE / grid[i][j] && i < rows - 1 && j < cols - 1) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) break;
                }

                if (valid) {
                    count++;
                }
            }
        }

        return count;
    }
}