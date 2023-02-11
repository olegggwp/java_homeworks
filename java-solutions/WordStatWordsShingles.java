import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStatWordsShingles {
    public static void main(String[] args) {

        if (args.length < 2 || args[0].isEmpty() || args[1].isEmpty()) {
            System.out.print("Problem with arguments!");
            return;
        }

        try {
            CoolScanner reader = new CoolScanner(args[0]);
            String[] arr = new String[1];
            int cntWords = 0;
            try {
                while (reader.hasNext()) {
                    String str = reader.nextWord(WordStatWordsShingles::gudchr, false);
                    if (!str.equals("")) {
                        cntWords++;
                        if (cntWords > arr.length) {
                            arr = Arrays.copyOf(arr, arr.length * 2);
                        }
                        arr[cntWords - 1] = str;
                    }
                }
            } finally {
                reader.close();
            }


            String[] arrTriples = new String[1];
            Map<String, Integer> cntIn = new HashMap<>();
            int cntTriples = 0;

            for (int i = 0; i < cntWords; i++) {
                arr[i] = arr[i].toLowerCase();
                for (int j = 0; j < arr[i].length() - 2 || j == 0; j++) {
                    String str;
                    if (arr[i].length() < 3) {
                        str = arr[i];
                        j = 4;
                    } else {
                        str = arr[i].substring(j, j + 3);
                    }
                    if (cntIn.get(str) == null) {
                        cntIn.put(str, 1);
                        cntTriples++;
                        if (cntTriples > arrTriples.length) {
                            arrTriples = Arrays.copyOf(arrTriples, arrTriples.length * 2);
                        }
                        arrTriples[cntTriples - 1] = str;
                    } else {
                        cntIn.put(str, cntIn.get(str) + 1);
                    }
                }
            }

            arrTriples = Arrays.copyOf(arrTriples, cntTriples);
            Arrays.sort(arrTriples);

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
            )) {
                for (int i = 0; i < cntTriples; i++) {
                    writer.write(arrTriples[i]);
                    writer.write(" ");
                    writer.write(cntIn.get(arrTriples[i]).toString());
                    writer.write("\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOExeption");
            System.out.println(e);
        }
    }

    static boolean gudchr(char ch) {
        return Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == 39;
    }

}
