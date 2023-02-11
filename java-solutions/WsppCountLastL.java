import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class WsppCountLastL {
    public static void main(String[] args) {
        if (args.length < 2 || args[0].isEmpty() || args[1].isEmpty()) {
            System.out.print("Problem with arguments!");
            return;
        }

        try {
            int cntWords = 0;
            int cntCol = 0;
            int cntInCol = 0;
            Map<String, IntList> occurrences = new TreeMap<>();
            Map<String, Integer> lastStroke = new TreeMap<>();
            Map<String, Integer> cntOcc = new TreeMap<>();
            Map<String, Integer> firstOcc = new TreeMap<>();
            try (CoolScanner reader = new CoolScanner(args[0])) {
                while (reader.hasNext()) {
                    String word = reader.nextWord(WsppCountLastL::gudchr, true).toLowerCase();
                    if (word.equals("\n")) {
                        cntCol++;
                        cntInCol = 0;
                    } else if (!word.equals("")) {
                        cntWords++;
                        cntInCol++;
                        if (!occurrences.containsKey(word)) {
                            occurrences.put(word, new IntList());
                            firstOcc.put(word, cntWords);
                        }
                        Integer lc = lastStroke.get(word);
                        IntList list = occurrences.get(word);
                        if (lc == null || lc < cntCol) {
                            list.add(cntInCol);
                        } else {
                            list.set(cntInCol);
                        }
                        cntOcc.merge(word, 1, Integer::sum);
                        lastStroke.put(word, cntCol);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            Triple[] arr = new Triple[occurrences.size()];
            int cntadded = 0;
            for (Map.Entry<String, IntList> a : occurrences.entrySet()) {
                String word = a.getKey();
                arr[cntadded] = new Triple(word, cntOcc.get(word), firstOcc.get(word));
                cntadded++;
            }

            Arrays.sort(arr);


            try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
            )) {
                for (Triple triple : arr) {
                    writer.write(triple.str);
                    writer.write(" ");
                    writer.write(Integer.toString(cntOcc.get(triple.str)));
                    for (int j = 0; j < occurrences.get(triple.str).length(); j++) {
                        writer.write(" ");
                        writer.write(Integer.toString(occurrences.get(triple.str).get(j)));
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

