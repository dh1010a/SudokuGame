public class SudokuBoard {
	private int[][] solution = new int[9][9];
    private int hole_count;
    private int[][] puzzle_board = new int[9][9];
    public int[] select_board = new int[9];
    public int push_row = -1;
    public int push_col = -1;

    /* * 객체 초기화 메소드
     *
     * @param count - 빈칸의 개수
     */
    public SudokuBoard(int count) {
        createSolutionBoard();
        hole_count = count;
        createPuzzleBoard(count);
        createSelectBoard();
    }
    
    public void createSelectBoard() {
    	for (int i = 0; i < 9; i++) {
    		select_board[i] = i+1;
    	}
    }

    /* * 퍼즐 보드 배열을 리턴 한다.
     *
     * @return 퍼즐 보드 배열
     */
    public int[][] getPuzzleBoard() {
        return puzzle_board;
    }
    public int[] getNumBoard() {
        return select_board;
    }

    /* * 빈칸의 개수를 리턴 한다.
     *
     * @return 빈칸의 개수
     */
    public int countHoles() {
        return hole_count;
    }

    // [배점 = 0.5/2.0]
    /* * 해답 스도쿠 보드인 solution 배열을 무작위로 섞어서 만든다. */
    private void createSolutionBoard() {
        // 1~9 범위의 무작위 시퀀스 {n1,n2,n3,n4,n5,n6,n7,n8,n9}를 만들고,
        // 이를 문서에 첨부한 그림 1과 같이 solution 배열에 배치 한다.
    	Random random = new Random();
        int[] sequence = new int[9];
        for (int i = 0; i < 9; i++) {
            int d = random.nextInt(i+1);
            sequence[i] = sequence[d];
            sequence[d] = i;
        }
        for (int i = 0; i < 9; i++)
        	sequence[i] = sequence[i] + 1;
        for (int i = 0; i < 9; i++)
        	solution[0][i] = sequence[i];
        for (int i = 0; i < 9; i++)
        	solution[1][i] = sequence[(i+3)%9];
        for (int i = 0; i < 9; i++)
        	solution[2][i] = sequence[(i+6)%9];
        solution[3][0] = sequence[1];solution[3][1] = sequence[2];solution[3][2] = sequence[0];
        solution[3][3] = sequence[4];solution[3][4] = sequence[5];solution[3][5] = sequence[3];
        solution[3][6] = sequence[7];solution[3][7] = sequence[8];solution[3][8] = sequence[6];
        solution[4][0] = sequence[4];solution[4][1] = sequence[5];solution[4][2] = sequence[3];
        solution[4][3] = sequence[7];solution[4][4] = sequence[8];solution[4][5] = sequence[6];
        solution[4][6] = sequence[1];solution[4][7] = sequence[2];solution[4][8] = sequence[0];
        solution[5][0] = sequence[7];solution[5][1] = sequence[8];solution[5][2] = sequence[6];
        solution[5][3] = sequence[1];solution[5][4] = sequence[2];solution[5][5] = sequence[0];
        solution[5][6] = sequence[4];solution[5][7] = sequence[5];solution[5][8] = sequence[3];
        solution[6][0] = sequence[2];solution[6][1] = sequence[0];solution[6][2] = sequence[1];
        solution[6][3] = sequence[5];solution[6][4] = sequence[3];solution[6][5] = sequence[4];
        solution[6][6] = sequence[8];solution[6][7] = sequence[6];solution[6][8] = sequence[7];
        solution[7][0] = sequence[5];solution[7][1] = sequence[3];solution[7][2] = sequence[4];
        solution[7][3] = sequence[8];solution[7][4] = sequence[6];solution[7][5] = sequence[7];
        solution[7][6] = sequence[2];solution[7][7] = sequence[0];solution[7][8] = sequence[1];
        solution[8][0] = sequence[8];solution[8][1] = sequence[6];solution[8][2] = sequence[7];
        solution[8][3] = sequence[2];solution[8][4] = sequence[0];solution[8][5] = sequence[1];
        solution[8][6] = sequence[5];solution[8][7] = sequence[3];solution[8][8] = sequence[4];
        
        
        // 문서에 첨부한 그림 2와 같이 가로줄 바꾸기와 세로줄 바꾸기를 무작위로 한다.
        // 무작위로 줄 바꾸기를 한다는 말은 바꿀지 말지를 무작위로 결정한다는 의미이다.
        // 가로줄 바꾸기
        shuffleRibbons();
        // 세로줄 바꾸기
        transpose();
        shuffleRibbons();
        transpose();
        // 테스트용 메소드
        showBoard(solution);
    }

    /* * 0~n-1 범위의 정수 수열을 무작위로 섞은 배열을 리턴 한다.
     *
     * @param n - 수열의 길이
     * @return 0~n-1 범위의 정수를 무작위로 섞어 만든 배열
     */
    private int[] generateRandomPermutation(int n) {
        Random random = new Random();
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            int d = random.nextInt(i+1);
            permutation[i] = permutation[d];
            permutation[d] = i;
        }
        return permutation;
    }

    /* * 문서에 첨부한 그림 2와 같은 전략으로 solution 배열의 가로줄을 무작위로 섞는다. */
    private void shuffleRibbons() {
        int[][] shuffled = new int[9][9];
        int[] random_index;
        for (int i = 0; i < 3; i++) {
            random_index = generateRandomPermutation(3);
            for (int j = 0; j < 3; j++)
                shuffled[i*3+random_index[j]] = solution[i*3+j];
        }
        solution = shuffled;
    }

    /* * solution 배열의 행과 열을 바꾼다. */
    private void transpose() {
        int[][] transposed = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                transposed[i][j] = solution[j][i];
        solution = transposed;
    }

    /* * 2차원 배열 b를 콘솔 윈도우에 보여준다. (테스트용 메소드)
     *
     * @param b - 2차원 배열
     */
    private void showBoard(int[][] b) {
        System.out.println("스도쿠 보드");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(b[i][j] + " ");
            System.out.println();
        }
    }

    // [배점 = 0.5/2.0]
    /* * solution 배열에서 count 만큼 무작위로 빈칸을 채워 puzzle_board 배열을 만들어 리턴한다.
     *  
     * @param count - 빈칸의 개수
     */
    private void createPuzzleBoard(int count) {
        // solution 보드를 그대로 puzzle_board에 복제한다.
    	puzzle_board = solution;
    	for (int i = 0; i < count; i++) {
    		int d = new Random().nextInt(9);
    		int d2 = new Random().nextInt(9);
    		if (puzzle_board[d][d2] != 0)
    			puzzle_board[d][d2] = 0;
    		else
    			i--;
    	}
    	
        // 무작위로 빈칸을 선정한다. 빈칸은 구별을 위해서 0으로 채운다.
        // new Random().nextInt(n) 메소드를 호출하면
        // 0~n-1 범위의 정수 중에서 무작위로 하나를 고를 수 있다.

    }
    public void checknumBoard() {
    	int cnt;
    	for (int i = 0; i < 9; i++) {
    		cnt = 0;
    		for (int row = 0; row < 9; row++)
    			for (int col = 0; col < 9; col++) {
    				if (puzzle_board[row][col] == i+1) {
    					cnt++;
    				}
    			}
    		if (cnt == 9) {
    			select_board[i] = 0;
    		}
    	}
    }

    // [배점 0.5/2.0]
    /* * row번 가로줄, col번 세로줄에 digit을 채울 수 있는지 검사하여,
     *  가능하면 채우고 true를 리턴하고, 불가능하면 false를 리턴 한다.
     *
     * @param digit - 빈칸에 채울 수 (1~9 중 하나)
     * @param row - 가로줄 번호
     * @param col - 세로줄 번호
     * @return 퍼즐 보드 조건에 만족하여 빈칸을 채웠으면 true, 만족하지 않으면 false
     */
    public boolean check(int digit, int row, int col) {
    	if (puzzle_board[row][col] != 0)
    		return false;
    	
    	for (int i = 0; i < 9; i++) {
    		if (puzzle_board[row][i] == digit) {
    			return false;
    		}
    	}
    	for (int i = 0; i < 9; i++) {
    		if (puzzle_board[i][col] == digit) {
    			return false;
    		}
    	}
    	int block_r = (row) / 3;
    	int block_c = (col) / 3;
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (puzzle_board[i + block_r * 3][j + block_c * 3] == digit)
    				return false;
    		}
    	}
    	
    	puzzle_board[row][col] = digit;
    	hole_count -= 1;
    	return true;

    }
}
