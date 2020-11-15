package Tareas_Progra;

import java.util.LinkedList;

public class n_knights {

  public static void main(String args[]) {
    nKnights(4);
  }

  public static void nKnights(int n) {
    solveKnights(n, 0, new LinkedList<Integer>());
  }

  private static void solveKnights(int n, int row, LinkedList<Integer> colPlacements) {
    if (row == n) {
      System.out.print("solution: ");
      System.out.println(colPlacements);
    } else {
      for (int col = 0; col < n; col++) {
        colPlacements.add(col);
        if (isValid(colPlacements))
          solveKnights(n, row + 1, colPlacements);
        colPlacements.remove(colPlacements.size() - 1);
      }
    }
  }

  private static boolean isValid(LinkedList<Integer> colPlacements) {
    int rowId = colPlacements.size() - 1;
    for (int i = 0; i < rowId; i++) {
      int diff_x = Math.abs(i - rowId);
      int diff_y = Math.abs(colPlacements.get(i) - colPlacements.get(rowId));
      if ((diff_x == 2 && diff_y == 1) || (diff_x == 1 && diff_y == 2))
        return false;
    }
    return true;
  }
}
