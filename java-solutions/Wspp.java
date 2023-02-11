import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Wspp {
    public static void main(String[] args) {
        if (args.length < 2 || args[0].isEmpty() || args[1].isEmpty()) {
            System.out.print("Problem with arguments!");
            return;
        }
        try {
            CoolScanner reader = new CoolScanner(args[0]);
            int cntWords = 0;
            Map<String, IntList> occurrences = new TreeMap<String, IntList>();
            try {
                while (reader.hasNext()) {
                    String word = reader.nextWord(Wspp::gudchr, false).toLowerCase();
                    if (!word.equals("")) {
                        cntWords++;
                        if (!occurrences.containsKey(word)) {
                            occurrences.put(word, new IntList());
                        }
                        occurrences.get(word).add(cntWords);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            reader.close();
            Pair[] arr = new Pair[occurrences.size()];
            int cntadded = 0;
            for (Map.Entry<String, IntList> a : occurrences.entrySet()) {
                arr[cntadded] = new Pair(a.getKey(), a.getValue());
                cntadded++;
            }   
            Arrays.sort(arr);

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
            )) {
                for (Pair pair : arr) {
                    writer.write(pair.str);
                    writer.write(" ");
                    writer.write(Integer.toString(pair.list.length()));
                    for (int j = 0; j < pair.list.length(); j++) {
                        writer.write(" ");
                        writer.write(Integer.toString(pair.list.get(j)));
                    }
                    writer.write("\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!\n" + e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    static boolean gudchr(char ch) {
        return Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == 39;
    }


}

