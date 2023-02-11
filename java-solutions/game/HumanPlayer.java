package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        int row = -1, col = -1;
        Move move= new Move(row-1, col-1, position.getTurn());
        while (!position.isValid(move)) {
            System.out.println("Enter you move for " + position.getTurn());
            try {
                row = in.nextInt();
                col = in.nextInt();
            } catch (Exception e) {
                System.out.println("You tried to cause exception, so I consider that your move was illegal.");
                break;
            }
            move = new Move(row - 1, col - 1, position.getTurn());
        }
        return move;
    }
}
