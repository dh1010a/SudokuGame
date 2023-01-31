import Sudoku.PlayerInput;
import Sudoku.Sudoku;
import Sudoku.SudokuWriter;

public class SudokuController {

    private Sudoku sudoku;
    private PlayerInput reader;
    private SudokuWriter writer;

    public SudokuController() {
        reader = new PlayerInput();
        int hole_count = reader.selectLevel();
        sudoku = new Sudoku(hole_count);
        writer = new SudokuWriter(sudoku);
    }

    // [배점 0.5/2.0]
    /** 스도쿠 퍼즐 게임을 진행한다. */
    public void playSudokuPuzzle() {
    	while (sudoku.countHoles() > 0) {
    		int r = reader.selectNumber("가로줄을 선택해주세요");
    		int c = reader.selectNumber("세로줄을 선택해주세요");
    		int d = reader.selectNumber("숫자를 입력해주세요");
    		sudoku.check(d, r, c);
    		writer.repaint();
    	}


    }
}