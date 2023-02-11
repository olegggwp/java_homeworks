package game;

public class NPlayerGame {
    private final Board board;
    private final int cntPlayers;
    private final Player[] players;

    public NPlayerGame(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        cntPlayers = players.length;
    }

    public int play(boolean log) {
        while (true) {
            for (int i = 0; i < cntPlayers; i++) {
                final int result = makeMove(players[i], i + 1, log);
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        final Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        return switch (result) {
            case WIN -> no;
            case LOOSE -> cntPlayers - no;
            case DRAW -> 0;
            case UNKNOWN -> -1;
        };
    }
}
