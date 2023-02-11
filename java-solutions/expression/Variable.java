package expression;

import java.util.Objects;

public class Variable implements CoolExpression {
    final String v; // :NOTE: правайт

    public Variable(String v) {
        this.v = v;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public String toString() {
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(v, variable.v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v);
    }

    @Override
    public String toMiniString() {
        return v;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (v) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> 0;
        };
    }
}
