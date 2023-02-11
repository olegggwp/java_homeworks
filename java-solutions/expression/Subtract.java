package expression;

public class Subtract extends BinaryOper {
    public Subtract(CoolExpression exp1, CoolExpression exp2) {
        super(exp1, exp2, 2, 0, false, false);
    }

    @Override
    protected String getStringOper() {
        return "-";
    }

    @Override
    public int evaluate(int x) {
        return exp1.evaluate(x) - exp2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return exp1.evaluate(x) - exp2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return exp1.evaluate(x, y, z) - exp2.evaluate(x, y, z);
    }
}
