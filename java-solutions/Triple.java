public class Triple implements Comparable<Triple> {
    public String str;
    public int fInt;
    public int sInt;

    public Triple(String a, int b, int c) {
        str = a;
        fInt = b;
        sInt = c;
    }

    public Triple() {
        str = "";
    }

    @Override
    public int compareTo(Triple o) {
        if (fInt != o.fInt){
            return Integer.compare(fInt, o.fInt);
        }
        return Integer.compare(sInt, o.sInt);
    }
}
