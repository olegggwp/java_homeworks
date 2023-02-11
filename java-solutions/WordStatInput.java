import java.util.*;
import java.io.*;

public class WordStatInput {
    public static void main(String[] args) {

        if (args.length < 2 || args[0].isEmpty() || args[1].isEmpty() ) {
            System.out.print("Problem with arguments!");
            return;
        }

        try{
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf8")
            );

            HashMap<String, Integer> hm = new HashMap<String, Integer> ();
            String[] arr = new String[1]; 
            int cntWords = 0;
            String line = reader.readLine();


            try {
                while (line != null) {
                    line = line.toLowerCase();
                    int numberStart = 0;
                    for(int j = 0; j <= line.length(); j++) {
                        if (j == line.length() || !( Character.getType(line.charAt(j)) == Character.LOWERCASE_LETTER
                        || Character.getType(line.charAt(j)) == Character.DASH_PUNCTUATION || line.charAt(j) == 39 ) ) { 
                            if ( numberStart < j ) {
                                cntWords++;
                                if (cntWords > arr.length) {
                                    arr = Arrays.copyOf(arr, arr.length*2);
                                }
                                arr[cntWords-1] = line.substring(numberStart, j);
                            }
                            numberStart = j + 1;
                        } 
                    }
                    line = reader.readLine();
                }
            } finally {
                reader.close();
            }


            for (int i = 0; i < cntWords; i++){
                hm.merge(arr[i], 1, Integer::sum);
            }


            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]), "utf8")
            );

            try {
                for (int i = 0; i < cntWords; i++){
                    Integer cnt = hm.get(arr[i]);
                    if (cnt != -1) {
                        writer.write(arr[i]);
                        writer.write(' ');
                        writer.write(cnt.toString());
                        writer.write('\n');
                        hm.put(arr[i], -1);
                    }
                }
            } finally {
                writer.close();
                System.err.println("lol1");
            }



        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
