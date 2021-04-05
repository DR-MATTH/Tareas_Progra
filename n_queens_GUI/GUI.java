package Tareas_Progra.n_queens_GUI;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class GUI extends JFrame {

  public static void main(String[] args) {
    new GUI();
  }

  private static final long serialVersionUID = 1L;

  private JPanel panelBoard;
  private JLabel total_solutions;
  private int num_reinas = 1; // por defecto
  private int solucion_actual = 0;
  private final static int SIZE = 600;
  private JButton[][] cells;
  LinkedList<LinkedList<Integer>> soluciones;

  public GUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(SIZE, SIZE);

    BorderLayout layout = new BorderLayout();
    setLayout(layout);

    panelBoard = new JPanel();
    total_solutions = new JLabel("0");
    soluciones = N_queens.solve(num_reinas);  // 1 por defecto
    add(BorderLayout.NORTH, top_panel());
    add(BorderLayout.SOUTH, bottom_panel());
    add(BorderLayout.CENTER, getBoard());
    
    if (soluciones.size() > 0) {
      paintSolution(0);
    }

    setLocationRelativeTo(this);
    setVisible(true);
  }

  public JPanel getBoard() {
    panelBoard.removeAll();
    panelBoard.revalidate();
    panelBoard.repaint();

    cells = new JButton[num_reinas][num_reinas];
    GridLayout layout = new GridLayout(num_reinas, num_reinas);
    panelBoard.setLayout(layout);
    for (int i = 0; i < num_reinas; i++) {
      for (int j = 0; j < num_reinas; j++) {
        JButton cell = new JButton();
        cell.setFont(new Font("Arial", Font.PLAIN, 60));
        if ((i + j) % 2 == 0) {
          cell.setBackground(Color.white);
        } else {
          cell.setBackground(Color.gray);
        }
        cells[i][j] = cell;
        cell.setEnabled(false);
        panelBoard.add(cell);
      }
    }

    panelBoard.revalidate();
    panelBoard.repaint();
    return panelBoard;
  }
  
  public JPanel top_panel() {
    JPanel panelNorth = new JPanel();
    JLabel text = new JLabel("NUMERO DE REINAS: ");
    panelNorth.add(text);
    JTextField num_reinas_input = new JTextField(10);
    panelNorth.add(num_reinas_input);
    JButton btnSolve = new JButton("resolver");

    btnSolve.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        num_reinas = Integer.parseInt(num_reinas_input.getText());
        soluciones = N_queens.solve(num_reinas);
        total_solutions.setText(""+soluciones.size());
        getBoard();
        if (soluciones.size() > 0) {
          paintSolution(0);
        }
      }
    });

    panelNorth.add(btnSolve);
    return panelNorth;
  }

  public JPanel bottom_panel() {
    JPanel panelSouth = new JPanel();
    JLabel total_text = new JLabel("CANT SOLUCION: ");

    JButton previous = new JButton("<<");
    JLabel actual_text = new JLabel("0");
    JButton next = new JButton(">>");

    previous.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        resetCells();
        if (solucion_actual > 0)
          solucion_actual -= 1;
        actual_text.setText("" + solucion_actual);
        paintSolution(solucion_actual);
      }
    });

    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        resetCells();
        if (solucion_actual < soluciones.size() - 1)
          solucion_actual += 1;
        actual_text.setText("" + solucion_actual);
        paintSolution(solucion_actual);
      }
    });

    panelSouth.add(total_text);
    panelSouth.add(total_solutions);
    panelSouth.add(previous);
    panelSouth.add(actual_text);
    panelSouth.add(next);
    return panelSouth;
  }
  
  private void paintSolution(int indexSolution) {
    LinkedList<Integer> actual = soluciones.get(indexSolution);
    for (int i = 0; i < num_reinas; i++) {
      cells[actual.get(i)][i].setText("X");
    }
  }

  private void resetCells() {
    for (int i = 0; i < num_reinas; i++)
      for (int j = 0; j < num_reinas; j++)
        cells[i][j].setText("");
  }
}

class N_queens {
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