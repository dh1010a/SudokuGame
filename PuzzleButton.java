import java.awt.event.*;
import javax.swing.*;

public class PuzzleButton extends JButton implements ActionListener {

    private SudokuBoard board;
    private SudokuFrame frame;
    private int row;
    private int col;
    
    public PuzzleButton(SudokuBoard b, SudokuFrame f, int r, int c) {
        board = b;
        frame = f;
        row = r;
        col = c;
        addActionListener(this);
    }
    
    public int row() {
    	return row;
    }
    public int col() {
    	return col;
    }
    
    public void actionPerformed(ActionEvent e) {
        String s = getText();
        
        if (s.equals("")) {
        	board.push_col = col();
        	board.push_row = row();
        }
        if (! s.equals("")) {
        	board.push_col = -1;
        	board.push_row = -1;
        }
    }
    
    
}