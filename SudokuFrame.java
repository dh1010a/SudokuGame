import java.awt.*;
import javax.swing.*;


public class SudokuFrame extends JFrame {
	private SudokuBoard sudoku;
	private PuzzleButton[][] button_board;
	private NumButton[] numbutton_board;
	JLabel diff; 
	
    public SudokuFrame(SudokuBoard s) {
       sudoku = s;
       button_board = new PuzzleButton[9][9];
       numbutton_board = new NumButton[9];
       Container cp = getContentPane();
       cp.setLayout(new BorderLayout());
       if (sudoku.countHoles() ==  27) {
    	   diff = new JLabel("Easy Mode");
       }
       if (sudoku.countHoles() ==  36) {
    	   diff = new JLabel("Medium Mode");
       }
       if (sudoku.countHoles() ==  45) {
    	   diff = new JLabel("Hard Mode");
       }
       diff.setHorizontalAlignment(SwingConstants.CENTER);
       cp.add(diff,BorderLayout.NORTH);
       JPanel p1 = new JPanel(new GridLayout(9,9));
       JPanel p2 = new JPanel(new GridLayout(1,9));
       for (int row = 0; row < 9; row++)
           for (int col = 0; col < 9; col++) {
               button_board[row][col] = new PuzzleButton(sudoku,this,row,col);
               p1.add(button_board[row][col]);
           }

       for (int col = 0; col < 9; col++) {
    	   numbutton_board[col] = new NumButton(sudoku,this,col);
           p2.add(numbutton_board[col]);
       }
       
       cp.add(p1,BorderLayout.CENTER);
       cp.add(p2,BorderLayout.SOUTH);
       update();
       setTitle("Sudoku");
       setSize(400,500);
       setVisible(true);
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void update() {
    	int[][] board = sudoku.getPuzzleBoard();
    	 for (int row = 0; row < 9; row++)
             for (int col = 0; col < 9; col++) {
                 if (board[row][col] != 0) {
                	 String n = Integer.toString(board[row][col]);
                	 button_board[row][col].setText(n);
                 }
                 else
                	 button_board[row][col].setText("");
             }
    	 int[] numboard = sudoku.getNumBoard();
    	 for (int col = 0; col < 9; col++) {
    		 if (numboard[col] != 0) {
    			 String n = Integer.toString(numboard[col]);
            	 numbutton_board[col].setText(n);
    		 }
    		 else
    			 numbutton_board[col].setText("");
    	 }
    	 
    }
    
    
}
