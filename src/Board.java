import java.util.Arrays;

public class Board {
    private static final int DEFAULT_SIZE = 12;

    private static final char BLACK = '●';
    private static final char WHITE = '○';
    private static final char EMPTY = '+';

    private char[][] data;
    private int size;

    public Board() {
        this(DEFAULT_SIZE);
    }

    public Board(int size) {
        if (size < 5) {
            System.out.println("棋盘大小不能小于5");
            size = 5;
        }
        data = new char[size][size];
        this.size = size;
        for (char[] row : data) {
            Arrays.fill(row, EMPTY);
        }
    }

    /**
     * @return success
     */
    boolean setPiece(int row, int col, boolean isWhite) {
        if (row < 0 || row >= size) {
            System.out.println("row " + (row + 1) + "不合法，应大于0且小于等于" + size);
            return false;
        } else if (col < 0 || col >= size) {
            System.out.println("col " + (col + 1) + "不合法，应大于0且小于等于" + size);
            return false;
        } else if (data[row][col] != EMPTY) {
            System.out.println("当前位置存在棋子");
            return false;
        } else {
            char target;
            if (isWhite) {
                target = WHITE;
            } else {
                target = BLACK;
            }
            data[row][col] = target;
            //print
            print();
            //check win
            if (isWin(row, col, target)) {
                if (isWhite) {
                    System.out.println("白方获胜");
                } else {
                    System.out.println("黑方获胜");
                }
                System.exit(0);
            }
            return true;
        }
    }

    void print() {
        for (char[] row : data) {
            StringBuilder sb = new StringBuilder();
            for (char cell : row) {
                sb.append(cell).append(" ");
            }
            System.out.println(sb);
        }
    }

    private boolean isWin(int row, int col, char target) {
        return canWinInRow(row, target)
                || canWinInCol(col, target)
                || canWinInLeftUpOblique(row, col, target)
                || canWinInRightDownOblique(row, col, target);
    }

    private boolean canWinInRow(int row, int target) {
        int constantNum = 0;
        for (char cell : data[row]) {
            if (cell == target) {
                constantNum++;
            } else {
                constantNum = 0;
            }
            if (constantNum == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean canWinInCol(int col, int target) {
        int constantNum = 0;
        for (int i = 0; i < size; i++) {
            char cell = data[i][col];
            if (cell == target) {
                constantNum++;
            } else {
                constantNum = 0;
            }
            if (constantNum == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean canWinInLeftUpOblique(int row, int col, int target) {
        int constantNum = 0;
        int startRow, startCol;
        if (row + col <= size - 1) {
            //在棋盘左上半部分
            startRow = row + col;
            startCol = 0;
            if (startRow < 4) {
                //明显不能构成5子
            } else {
                for (int i = startRow; i >= 0; i--) {
                    if (data[i][startCol + startRow - i] == target) {
                        constantNum++;
                    } else {
                        constantNum = 0;
                    }
                    if (constantNum == 5) {
                        return true;
                    }
                }
            }
        } else {
            //在棋盘右下半部分
            startRow = size - 1;
            startCol = col - (size - 1 - row);
            if (startCol > size - 1 - 4) {
                //明显不能构成5子
            } else {
                for (int j = startCol; j < size; j++) {
                    if (data[startRow - (j - startCol)][j] == target) {
                        constantNum++;
                    } else {
                        constantNum = 0;
                    }
                    if (constantNum == 5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canWinInRightDownOblique(int row, int col, int target) {
        int constantNum = 0;
        int startRow, startCol;
        if (col - row <= 0) {
            //在棋盘左下半部分
            startRow = row - col;
            startCol = 0;
            if (startRow > size - 1 - 4) {
                //明显不能构成5子
            } else {
                for (int i = startRow; i <= size - 1; i++) {
                    if (data[i][startCol + i - startRow] == target) {
                        constantNum++;
                    } else {
                        constantNum = 0;
                    }
                    if (constantNum == 5) {
                        return true;
                    }
                }
            }
        } else {
            //在棋盘右上半部分
            startRow = 0;
            startCol = col - row;
            if (startCol > size - 1 - 4) {
                //明显不能构成5子
            } else {
                for (int j = startCol; j < size; j++) {
                    if (data[startRow + j - startCol][j] == target) {
                        constantNum++;
                    } else {
                        constantNum = 0;
                    }
                    if (constantNum == 5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
