package game;

import java.util.Scanner;

public class Tournment {
    final private String[] people;
    final int cntPeople;
    final int N, M, K;
    final Scanner sc;
    private final int[] table;

    private final Player[] players;

    public Tournment(String[] people, int n, int m, int k, Scanner scanner, Player[] players) {
        this.people = people;
        cntPeople = people.length;
        this.players = players;
        table = new int[cntPeople];
        N = n;
        M = m;
        K = k;
        sc = scanner;
    }

    public void play(){
        for (int i = 0; i < cntPeople; i++) {
            for (int j = i+1; j < cntPeople; j++) {
                printGame(i, j);
            }
        }
        System.out.println("Tournment is over. Results:");
        for (int p = 0; p < cntPeople; p++) {
            System.out.println(people[p] + " : " + table[p]);
        }
    }
    private void printGame(int fp, int sp){
        System.out.println("First player is : " + people[fp]);
        System.out.println("Second player is : " + people[sp]);
        final int result = new NPlayerGame(
                new NMBoard(N, M, K, 2, false),
                new Player[]{
                        players[fp],
                        players[sp]
                }
        ).play(true);
        switch (result) {
            case 1 -> {
                table[fp] += 3;
                System.out.println("First player won");
            }
            case 2 -> {
                table[sp] += 3;
                System.out.println("Second player won");
            }
            case 0 -> {
                table[fp] += 1;
                table[sp] += 1;
                System.out.println("Draw");
            }
            default -> throw new AssertionError("Unknown result " + result);
        }
    }
}
