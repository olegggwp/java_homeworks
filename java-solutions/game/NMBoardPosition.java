package game;

public class NMBoardPosition implements Position {
    private final NMBoard board;

    public NMBoardPosition(NMBoard board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return board.toString();
    }

    @Override
    public Cell getCell(int row, int column) {
        return board.getCell(row, column);
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public Cell getTurn() {
        return board.getTurn();
    }
}
