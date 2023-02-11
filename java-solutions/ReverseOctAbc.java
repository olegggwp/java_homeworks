import java.io.IOException;
import java.util.Arrays;

public class ReverseOctAbc {
    public static void main(String[] args) {
        try {
            CoolScanner in = new CoolScanner(System.in);
            int[][] arr = new int[1][];
            int[] tmp = new int[1];
            int cntlines = 0;
            int cntNums = 0;

            while (in.hasNext()) {
                String str = in.nextWord(ReverseOctAbc::gudchr, true);
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
                        tmp[cntNums - 1] = toInt(str);
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

    public static int toInt(String str) {

        boolean minus = str.charAt(0) == '-';
        if (minus) {
            str = str.substring(1);
        }
        int a;
        if (Character.isDigit(str.charAt(0))) {
            if (Character.toLowerCase(str.charAt(str.length() - 1)) == 'o') {
                a = (int) Integer.parseUnsignedInt(str.substring(0, str.length()-1), 8);

            } else {
                a = (int) Integer.parseUnsignedInt(str);
            }
        } else {
            char[] word = new char[str.length()];
            for (int i = 0; i < str.length(); i++) {
                word[i] = (char) ('0' + str.charAt(i) - 'a');
            }
            a = Integer.parseUnsignedInt(new String(word));
        }
        if (minus) {
            a = -a;
        }
        return a;
    }

    static boolean gudchr(char ch) {
        return Character.isDigit(ch) || ch == '-' || ch == 'o'|| ch == 'O' || ('a' <= ch && ch <= 'a' + 9);
    }

}
