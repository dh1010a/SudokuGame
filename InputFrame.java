import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class InputFrame extends JFrame {
	
	public InputFrame() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new FlowLayout());
		JButton btn1 = new JButton("Easy");
		JPanel p2 = new JPanel(new FlowLayout());
		JButton btn2 = new JButton("Medium");
		JPanel p3 = new JPanel(new FlowLayout());
		JButton btn3 = new JButton("Hard");
        p1.add(btn1);
        p2.add(btn2);
        p3.add(btn3);
        cp.add(p3,BorderLayout.EAST);
        cp.add(p2,BorderLayout.CENTER);
        cp.add(p1,BorderLayout.WEST);
        setTitle("Select level");
        setSize(250,90);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        btn1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		new SudokuFrame(new SudokuBoard(27));
        		setVisible(false);
        	}
        });
        btn2.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		new SudokuFrame(new SudokuBoard(36));
        		setVisible(false);
        	}
        });
        btn3.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		new SudokuFrame(new SudokuBoard(45));
        		setVisible(false);
        	}
        });
        
	}
}
