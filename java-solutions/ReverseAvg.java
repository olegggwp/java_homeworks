import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseAvg {
    public static void main(String[] args) {
        try {
            CoolScanner in = new CoolScanner(System.in);
            int[][] arr = new int[1][];
            int[] tmp = new int[1];
            int cntlines = 0;
            int maxStrokeLength = 0;
            int cntNums = 0;
            while (in.hasNext()) {
                String str = in.nextWord(Reverse::gudchr, true);
                if (!str.equals("")) {
                    if (str.equals("\n")) {
                        cntlines++;
                        if (arr.length < cntlines) {
                            arr = Arrays.copyOf(arr, arr.length * 2);
                        }
                        arr[cntlines - 1] = Arrays.copyOf(tmp, cntNums);
                        if (maxStrokeLength < cntNums) {
                            maxStrokeLength = cntNums;
                        }
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


            long[] col = new long[maxStrokeLength];
            long[] strokeSum = new long[cntlines];
            int[] colCnt = new int[maxStrokeLength];

            for (int i = 0; i < cntlines; i++) {
                if (arr[i] != null) {
                    for (int j = 0; j < arr[i].length; j++) {
                        strokeSum[i] += arr[i][j];
                    }
                }
            }

            for (int j = 0; j < maxStrokeLength; j++) {
                for (int i = 0; i < cntlines; i++) {
                    if (arr[i].length > j) {
                        col[j] += arr[i][j];
                        colCnt[j]++;
                    }
                }
            }

            for (int i = 0; i < cntlines; i++) {
                if (arr[i] != null) {
                    for (int j = 0; j < arr[i].length; j++) {
                        System.out.print((col[j] + strokeSum[i] - arr[i][j]) / (colCnt[j] + arr[i].length - 1));
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
