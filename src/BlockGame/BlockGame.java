package BlockGame;

/**
 * @author song
 * @date 2022/11/3 15:55
 */
public class BlockGame {

    public static boolean isFull(int[][] board, int row) {
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean lockDot(int[][] board, int row, int col) {
        //防止越界
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
            return false;
        }
        return board[row][col] == 0;
    }

    public static boolean lockPiece(int[][] board, int row, int col, int[][] piece) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    if (!lockDot(board, row + i, col + j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
