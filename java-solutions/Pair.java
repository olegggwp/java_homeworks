public class Pair implements Comparable<Pair> {
    public String str;
    public IntList list;

    public Pair(String a, IntList b) {
        str = a;
        list = b;
    }
    public Pair() {
        str = "";
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(list.get(0), o.list.get(0));
    }
}
