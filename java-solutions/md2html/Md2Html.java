package md2html;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class Md2Html {
    public static void main(String[] args) {
        try {
            CoolScanner sc = new CoolScanner(args[0]);

            List<String> list = new java.util.ArrayList<>(List.of());
            StringBuilder txt = new StringBuilder();
            List<Integer> emp1 = new ArrayList<>();
            List<Integer> emp2 = new ArrayList<>();
            List<Integer> strongT1 = new ArrayList<>();
            List<Integer> strongT2 = new ArrayList<>();
            List<Integer> strikeout = new ArrayList<>();
            List<Integer> code = new ArrayList<>();
            boolean isHeadOpen = false;
            boolean hasRefStart = false;
            int hrefStart = 0;
            int headLevel = 0;
            char ch;
            list.add("\n\n");

            while (sc.hasNext()) {
                if (sc.isNextSep()) {
                    ch = sc.nextSep();
                } else {
                    ch = sc.nextChar(false);
                }

                if (!txt.isEmpty() && txt.charAt(txt.length() - 1) == '\\') {
                    txt.deleteCharAt(txt.length() - 1);
                    txt.append(ch);
                } else if (ch == '\n') {
                    if (sc.hasNext() && (sc.nextChar(true) == '\n' || sc.nextChar(true) == '\r')) {
                        emp1 = new ArrayList<>();
                        emp2 = new ArrayList<>();
                        strikeout = new ArrayList<>();
                        strongT1 = new ArrayList<>();
                        strongT2 = new ArrayList<>();
                        code = new ArrayList<>();
                        hasRefStart = false;
                        if (!txt.isEmpty()) {
                            list.add(String.valueOf(txt));
                            txt = new StringBuilder();
                        }
                        if (isHeadOpen) {
                            list.add("</h" + headLevel + ">\n");
                            isHeadOpen = false;
                        }
                        if (!list.get(list.size() - 1).equals("\n\n")) {
                            list.add("\n\n");
                        }
                    } else if (!(txt.isEmpty() && list.get(list.size() - 1).equals("\n\n"))) {
                        txt.append(ch);
                    }
                } else if (ch == '<' || ch == '>' || ch == '&') {
                    txt.append(replaceToSpecial(ch));
                } else if (ch == '#' && list.get(list.size() - 1).equals("\n\n") && txt.isEmpty()) {
                    int cnt = 1;
                    txt.append(ch);
                    while (sc.hasNext() && sc.nextChar(true) == '#') {
                        cnt++;
                        txt.append(sc.nextChar(false));
                    }
                    if (sc.hasNext() && sc.nextChar(true) == ' ' && cnt < 9) {
                        sc.nextChar(false);
                        txt = new StringBuilder();
                        list.add("<h" + cnt + ">");
                        headLevel = cnt;
                        isHeadOpen = true;
                    }
                } else if (isMd(ch) || ch == '[') {
                    String md = String.valueOf(ch);
                    if (sc.nextChar(true) == ch && isDoubleMd(ch)) {
                        sc.nextChar(false);
                        md = md + md;
                    }
                    List<Integer> curr = null;
                    switch (md) {
                        case "*" -> curr = emp1;
                        case "_" -> curr = emp2;
                        case "**" -> curr = strongT1;
                        case "__" -> curr = strongT2;
                        case "--" -> curr = strikeout;
                        case "`" -> curr = code;
                    }
                    if (!txt.isEmpty()) {
                        list.add(String.valueOf(txt));
                        txt = new StringBuilder();
                    }
                    if (curr == null) {
                        if (md.equals("[") && !hasRefStart) {
                            hasRefStart = true;
                            hrefStart = list.size();
                        }
                        list.add(md);
                    } else if (!curr.isEmpty()) {
                        int pos = curr.get(curr.size() - 1);
                        list.set(pos, md2Html(md, false));
                        addMd(list, md);
                        curr.remove(curr.size() - 1);
                    } else {
                        list.add(md);
                        curr.add(list.size() - 1);
                    }
                } else if (hasRefStart && ch == ']' && sc.hasNext() && sc.nextChar(true) == '(') {
                    sc.nextChar(false);
                    if (!txt.isEmpty()) {
                        list.add(String.valueOf(txt));
                        txt = new StringBuilder();
                    }
                    while (sc.hasNext() && sc.nextChar(true) != ')' && !(sc.isNextSep() && txt.charAt(txt.length() - 1) == '\n')) {
                        txt.append(sc.nextChar(false));
                    }
                    if (sc.hasNext() && sc.nextChar(true) == ')') {
                        sc.nextChar(false);
                        list.set(hrefStart, refStart(txt));
                        list.add("</a>");
                        hasRefStart = false;
                        txt = new StringBuilder();
                    }
                } else {
                    txt.append(ch);
                }
            }
            if (!txt.isEmpty()) {
                list.add(String.valueOf(txt));
            }
            if (!list.isEmpty()) {
                String str = list.get(list.size() - 1);
                if (str.charAt(str.length() - 1) == '\n' && !str.equals("\n\n")) {
                    list.set(list.size() - 1, str.substring(0, str.length() - 1));
                }
            }
            if (isHeadOpen) {
                list.add("</h" + headLevel + ">\n");
            }

            sc.close();

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
            )) {
                writeHtml(writer, list);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String refStart(StringBuilder txt) {
        return "<a href='" + txt + "'>";
    }

    private static void writeHtml(BufferedWriter writer, List<String> list) throws IOException {
        boolean isPOpen = false;
        for (int i = 0; i <= list.size(); i++) {
            String str = "";
            if(i < list.size()){
                str = list.get(i);
            }
            if (isPOpen && (i == list.size() || str.equals("\n\n"))) {
                writer.write("</p>\n");
                isPOpen = false;
            }
            if (str.equals("\n\n")) {
                if (i < list.size() - 1 && notHeader(list.get(i + 1))) {
                    writer.write("<p>");
                    isPOpen = true;
                }
            } else {
                writer.write(str);
            }
        }
    }

    private static boolean isDoubleMd(char ch) {
        return ch == '*' || ch == '-' || ch == '_';
    }

    private static boolean isMd(char ch) {
        return ch == '*' || ch == '-' || ch == '_' || ch == '`';
    }

    private static String md2Html(String md, boolean isEnd) {
        StringBuilder res = new StringBuilder("<");
        if (isEnd) {
            res.append("/");
        }
        switch (md) {
            case "*", "_" -> res.append("em>");
            case "**", "__" -> res.append("strong>");
            case "`" -> res.append("code>");
            case "--" -> res.append("s>");
        }
        return String.valueOf(res);
    }

    private static void addMd(List<String> list, String md) {
        String res = md2Html(md, true);
        list.add(res);
    }

    private static String replaceToSpecial(char ch) {
        String res = "";
        switch (ch) {
            case '<' -> res = "&lt;";
            case '>' -> res = "&gt;";
            case '&' -> res = "&amp;";
        }
        return res;
    }

    private static boolean notHeader(String str) {
        return !(str.charAt(0) == '<' && str.charAt(1) == 'h');
    }

}
