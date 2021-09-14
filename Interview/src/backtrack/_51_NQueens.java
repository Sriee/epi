package backtrack;

public class _51_NQueens {
    private int n;
    private boolean[][] board;
    
    private boolean isUnderAttack(int row, int col) {
        return board[row][col];    
    }

    private void placeQueen(int row, int col) {
        mark(row, col, true);
    }

    private void removeQueen(int row, int col) {
        mark(row, col, false);
    }
    
    private void mark(int row, int col, boolean val) {
        int i, j;
        // Mark row & col
        for(i = 0; i < n; i++) {
            this.board[row][i] = val;
            this.board[i][col] = val;
        }
        
        // Mark current to Top-Left 
        i = row; j = col;
        while(i >= 0 && j >= 0)
            this.board[i--][j--] = val;
        
        // Mark current to Top-Right
        i = row; j = col;
        while(i >= 0 && j < n)
            this.board[i--][j++] = val;
        
        // Mark current to Bottom-Left
        i = row; j = col;
        while(i < n && j >= 0)
            this.board[i++][j--] = val;
        
        // Mark current to Bottom-Right
        i = row; j = col;
        while(i < n && j < n)
            this.board[i++][j++] = val;
    } 
    
    private int backtrack(int row) {
        if(row == this.n)
            return 1;
        
        int count = 0;
        for(int j = 0; j < this.n; j++) {
            if(!isUnderAttack(row, j)) {
                this.placeQueen(row, j);
                count += backtrack(row + 1);
                this.removeQueen(row, j);
            }
        }
        
        return count;
    }
    
    public int totalQueens(int n) {
        this.n = n;
        this.board = new boolean[n][n];
        
        return backtrack(0);
    }
    
    public static void main(String[] args) {
        _51_NQueens nq = new _51_NQueens();
        System.out.println(nq.totalQueens(3));
    }
}
