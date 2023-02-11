package game;

import java.util.*;

public class NMBoard implements Board, Position {
    protected static HashMap<Cell, String> CELL_TO_STRING = new HashMap<>();
    protected static List<Cell> CELL_ORDER = new ArrayList<>();
    private final int N, M, K;
    private int lastMoves;
    private final Cell[][] field;
    private Cell turn;

    public NMBoard(int n, int m, int k, int cntPlayers, boolean obstacles) {
        N = n > 0 ? n : 3;
        M = m > 0 ? m : 3;
        K = k > 0 ? k : 3;
        lastMoves = N * M;
        field = new Cell[N][M];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        CELL_TO_STRING.put(Cell.E, ".");
        CELL_TO_STRING.put(Cell.X, "X");
        CELL_TO_STRING.put(Cell.O, "0");
        CELL_ORDER.add(Cell.X);
        CELL_ORDER.add(Cell.O);
        if (cntPlayers == 3) {
            CELL_TO_STRING.put(Cell.P3, "-");
            CELL_ORDER.add(Cell.P3);
        }
        if (cntPlayers == 4) {
            CELL_TO_STRING.put(Cell.P4, "|");
            CELL_ORDER.add(Cell.P3);
        }
        if (obstacles) {
            CELL_TO_STRING.put(Cell.OB, "#");
        }
    }

    public NMBoard() {
        this(11, 11, 3, 2, true);
        for (int i = 0; i < 11; i++) {
            field[i][i] = Cell.OB;
            field[i][11 - i - 1] = Cell.OB;
        }
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return new NMBoardPosition(this);
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        lastMoves--;
        if (checkWin(move)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }
        turn = CELL_ORDER.get((CELL_ORDER.indexOf(turn) + 1) % CELL_ORDER.size());
        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {
        return lastMoves == 0;
    }

    private boolean checkWin(Move mv) {
        int[] cnts = new int[4];
        cnts[0] = cellsInRow(mv, 0, 1) + cellsInRow(mv, 0, -1) - 1;
        cnts[1] = cellsInRow(mv, 1, 0) + cellsInRow(mv, -1, 0) - 1;
        cnts[2] = cellsInRow(mv, 1, 1) + cellsInRow(mv, -1, -1) - 1;
        cnts[3] = cellsInRow(mv, 1, -1) + cellsInRow(mv, -1, 1) - 1;
        boolean ans = false;
        for (int cnt : cnts) {
            ans = ans || cnt >= K;
        }
        return ans;
    }

    private int cellsInRow(Move mv, int stepRow, int stepCol) {
        int cnt = 0;
        int c = mv.getCol();
        int r = mv.getRow();
        while (isValidCell(c + stepCol * cnt, r + stepRow * cnt)
            && getCell(r + stepRow * cnt, c + stepCol * cnt) == mv.getValue()) {
            cnt++;
        }
        return cnt;
    }

    public boolean isValid(final Move move) {
        return isValidCell(move.getCol(), move.getRow()) &&
            field[move.getRow()][move.getCol()] == Cell.E &&
            turn == move.getValue();
    }

    public boolean isValidCell(final int c, final int r) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < M; i++) {
            sb.append(i + 1);
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < N; r++) {
            sb.append(r + 1);
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
