package practices.hsbc.cellStateofHouses;

import java.util.Scanner;

public class Solution {
    
    public static int[] stateOfCells(int[] cell, int days) {
        int n = cell.length;
        int[] newCell = new int[n];
        
        for (int d = 0; d < days; d++) {
            for (int i = 0; i < n; i++) {
                // left neighbor: if at start, treat as inactive (0)
                int left = (i - 1 >= 0) ? cell[i - 1] : 0;
                // right neighbor: if at end, treat as inactive (0)
                int right = (i + 1 < n) ? cell[i + 1] : 0;
                
                // Rule: active (1) if exactly one neighbor is active
                newCell[i] = (left != right) ? 1 : 0;
            }
            // Copy newCell to cell for next iteration
            System.arraycopy(newCell, 0, cell, 0, n);
        }
        
        return cell;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read number of cells (always 8, but we read it as per input format)
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        // Read cell states
        int[] cell = new int[n];
        for (int i = 0; i < n; i++) {
            cell[i] = scanner.nextInt();
        }
        
        // Read number of days
        int days = scanner.nextInt();
        
        // Get result after given days
        int[] result = stateOfCells(cell, days);
        
        // Print result
        for (int i = 0; i < n; i++) {
            System.out.print(result[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        
        scanner.close();
    }
}