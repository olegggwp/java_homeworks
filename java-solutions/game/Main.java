package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0, m = 0, k = 0;
        Player[] players = {
                new RandomPlayer(),
                new RandomPlayer(),
                new RandomPlayer()
//                new CheatingPlayer()
//                new HumanPlayer(new Scanner(System.in))
        };
        //Tournment tr = new Tournment(new String[]{"man A", "man B", "man C"}, 3, 3, 3, sc, players);
        //tr.play();
        try {
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();
        } catch (RuntimeException e) {
            System.out.println("You tried to cause exception, why would you do that?");
            n = 3;
            m = 3;
            k = 3;
        }
        final int result =
                new NPlayerGame(
//                new NMBoard(),
//                new TicTacToeBoard(),
                new NMBoard(m, n, k, 3, false),
                players
        ).play(true);
        switch (result) {
            case 1 -> System.out.println("First player won");
            case 2 -> System.out.println("Second player won");
            case 0 -> System.out.println("Draw");
            default -> throw new AssertionError("Unknown result " + result);
        }
    }
}
