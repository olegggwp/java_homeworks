package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CoolScanner implements Closeable {
    private final BufferedReader reader;
    private final char[] block;
    private int read, pointer;

    public CoolScanner(String source) throws FileNotFoundException {
        reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(source), StandardCharsets.UTF_8)
        );
        block = new char[512];
        read = 0;
        pointer = 0;
    }

    public CoolScanner(InputStream source) {
        reader = new BufferedReader(
                new InputStreamReader(source, StandardCharsets.UTF_8)
        );
        block = new char[512];
        read = 0;
        pointer = 0;
    }

    public void close() throws IOException {
        reader.close();
    }

    public boolean hasNext() throws IOException {
        while (read == 0 || (read > -1 && pointer >= read)) {
            read = reader.read(block);
            pointer = 0;
        }
        return read != -1;
    }

    public char nextChar(Boolean predict) throws IOException {
        if (hasNext()) {
            while (read == 0 || pointer >= read) {
                read = reader.read(block);
                pointer = 0;
            }
            if (!predict) {
                pointer++;
            }
            return predict ? block[pointer] : block[pointer-1];
        }
        throw new java.util.NoSuchElementException("There's no elements next, but you tried to get nextChar");
    }

    public boolean isNextSep() throws IOException {
        return hasNext() && (nextChar(true) == '\n' || nextChar(true) == '\r');
    }

    public char nextSep() throws IOException {
        if (!isNextSep()) {
            throw new java.util.NoSuchElementException("No Such element, but you tried to get nextSep");
        }
        char ch = nextChar(false);
        if (ch == '\r' && hasNext() && nextChar(true) == '\n') {
            nextChar(false);
        }
        return '\n';
    }

    public String nextWord(CharCheck cc, boolean parseSeparators) throws IOException {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("No Such element, but you tried to get next word");
        }
        if (parseSeparators && isNextSep()) {
            return String.valueOf(nextSep());
        }
        char[] word = new char[1];
        int wordLength = 0;
        while (hasNext()) {
            char ch = nextChar(true);
            if (parseSeparators && wordLength == 0 && isNextSep()) {
                return String.valueOf(nextSep());
            }
            if (cc.isGoodChar(ch)) {
                wordLength++;
                if (wordLength > word.length) {
                    word = Arrays.copyOf(word, word.length * 2);
                }
                word[wordLength - 1] = ch;
            } else if (wordLength > 0) {
                return new String(Arrays.copyOf(word, wordLength));
            }
            if (cc.isGoodChar(ch) || (!cc.isGoodChar(ch) && wordLength == 0)) {
                nextChar(false);
            }
        }
        return new String(Arrays.copyOf(word, wordLength));
    }


    interface CharCheck {
        boolean isGoodChar(char ch);
    }

}


