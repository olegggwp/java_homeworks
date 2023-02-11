package expression;

import java.util.Objects;

public class Const implements CoolExpression {
    final int c; // int влезает в дабл
    final double dc;
    private final boolean isInt;
    public Const(int i) {
        c = i;
        dc = i;
        isInt = true;
    }

    public Const(double i) {
        c = (int) i;
        dc = i;
        isInt = false;
    }


    @Override
    public String toString() {
        return isInt ? String.valueOf(c) : String.valueOf(dc);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        if  (isInt != aConst.isInt) return false;
        return isInt ? c == aConst.c : dc == aConst.dc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, dc);
    }

    @Override
    public String toMiniString() {
        return toString();
    }
    @Override
    public int evaluate(int x) {
        return c;
    }
    @Override
    public double evaluate(double x) {
        return dc;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return c;
    }
}
