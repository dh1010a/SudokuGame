import java.awt.event.*;
import javax.swing.*;


public class NumButton extends JButton implements ActionListener {
	
	private SudokuBoard board;
    private SudokuFrame frame;
    private int col;
    
    public NumButton(SudokuBoard b, SudokuFrame f,int c) {
        board = b;
        frame = f;
        col = c;
        addActionListener(this);
    }
    
    public int col() {
    	return col;
    }
    
    public void actionPerformed(ActionEvent e) {
        String s = getText();
        if (! s.equals("") && board.push_col >= 0 && board.push_row >= 0) {
        	board.check(Integer.parseInt(s), board.push_row, board.push_col);
        	board.push_col = -1;
        	board.push_row = -1;
        }
        board.checknumBoard();
        frame.update();
        
        
    }
}
