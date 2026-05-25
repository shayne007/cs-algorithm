package practices.hsbc.commonProductSet;

/**
 *
 * @since 2026/5/23
 */

import java.util.*;

public class CommonProduct {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int customers = sc.nextInt();
    int products = sc.nextInt();

    Set<Integer> commonSet = new HashSet<>(Math.max(16, products * 2));

    for (int j = 0; j < products; j++) {
      commonSet.add(sc.nextInt());
    }

    for (int i = 1; i < customers; i++) {
      if (commonSet.isEmpty()) {
        for (int j = 0; j < products; j++) {
          sc.nextInt();
        }
        continue;
      }

      Set<Integer> currentCustomerSet = new HashSet<>(Math.max(16, products * 2));
      for (int j = 0; j < products; j++) {
        currentCustomerSet.add(sc.nextInt());
      }

      commonSet.retainAll(currentCustomerSet);
    }

    // If no common product exists
    if (commonSet.isEmpty()) {
      System.out.println("NA");
      sc.close();
      return;
    }

    // Convert to list and sort
    List<Integer> result = new ArrayList<>(commonSet);
    Collections.sort(result);

    // Print result
    for (int i = 0; i < result.size(); i++) {
      if (i > 0) System.out.print(" ");
      System.out.print(result.get(i));
    }
    System.out.println();
    sc.close();
  }
}
