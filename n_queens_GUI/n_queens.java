package Tareas_Progra.n_queens_GUI;

import java.util.LinkedList;

public class N_queens {
  // public static void main(String args[]) {
  // LinkedList<LinkedList<Integer>> resultado = solve(4);
  // for (LinkedList<Integer> solucion : resultado) {
  // System.out.println(solucion);
  // }
  // }

  public static LinkedList<LinkedList<Integer>> solve(int n) {
    LinkedList<LinkedList<Integer>> resultado = new LinkedList<>();
    solveQueens(n, 0, new LinkedList<Integer>(), resultado);
    return resultado;
  }

  private static void solveQueens(int n, int row, LinkedList<Integer> colPlacements,
      LinkedList<LinkedList<Integer>> resultado) {

    if (row == n) {
      resultado.add(new LinkedList<Integer>(colPlacements));
    } else {
      for (int col = 0; col < n; col++) {
        colPlacements.add(col);
        if (isValid(colPlacements))
          solveQueens(n, row + 1, colPlacements, resultado);
        colPlacements.remove(colPlacements.size() - 1);
      }
    }
  }

  private static boolean isValid(LinkedList<Integer> colPlacements) {
    int rowId = colPlacements.size() - 1;
    for (int i = 0; i < rowId; i++) {
      int diff = Math.abs(colPlacements.get(i) - colPlacements.get(rowId));
      if (diff == 0 || diff == rowId - i)
        return false;
    }
    return true;
  }
}
