import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        CoolScanner in = new CoolScanner(System.in);
        int[][] arr = new int[1][];
        int[] tmp = new int[1];
        int cntlines = 0;
        int cntNums = 0;
        try {
            while (in.hasNext()) {
                String str = in.nextWord(Reverse::gudchr, true);
                if (!str.equals("")) {
                    if (str.equals("\n")) {
                        cntlines++;
                        if (arr.length < cntlines) {
                            arr = Arrays.copyOf(arr, arr.length * 2);
                        }
                        arr[cntlines - 1] = Arrays.copyOf(tmp, cntNums);
                        cntNums = 0;
                    } else {
                        cntNums++;
                        if (tmp.length < cntNums) {
                            tmp = Arrays.copyOf(tmp, tmp.length * 2);
                        }
                        tmp[cntNums - 1] = Integer.parseInt(str);
                    }
                }
            }

            for (int i = cntlines - 1; i >= 0; i--) {
                if (arr[i] != null) {
                    for (int j = arr[i].length - 1; j >= 0; j--) {
                        System.out.print(arr[i][j]);
                        System.out.print(" ");
                    }
                }
                System.out.print("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static boolean gudchr(char ch) {
        return Character.isDigit(ch) || ch == '-';
    }
}