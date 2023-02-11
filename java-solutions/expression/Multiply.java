package expression;

public class Multiply extends BinaryOper {
    public Multiply(CoolExpression exp1, CoolExpression exp2) {
        super(exp1, exp2, 1, 0, true, false);
    }

    @Override
    protected String getStringOper() {
        return "*";
    }

    @Override
    public int evaluate(int x) {
        return exp1.evaluate(x) * exp2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return exp1.evaluate(x) * exp2.evaluate(x);
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return exp1.evaluate(x, y, z) * exp2.evaluate(x, y, z);
    }
}
