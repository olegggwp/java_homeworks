package expression;

import java.util.Objects;

public abstract class BinaryOper implements CoolExpression {
    protected final CoolExpression exp1, exp2;
    final protected int priority, secondPriority;
    final boolean isAssoc, canUnbraceWithOth;

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final BinaryOper oper = (BinaryOper) o;
        return Objects.equals(exp1, oper.exp1)
                && Objects.equals(exp2, oper.exp2);

    }

    @Override
    public int hashCode() {
        return Objects.hash(exp1, exp2, getStringOper());
    } // :NOTE: хеш по тому, по чему сравниваешь

    protected BinaryOper(CoolExpression exp1, CoolExpression exp2, int priority, int secondPriority, boolean isAssoc, boolean canUnbraceWithOth) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.priority = priority;
        this.secondPriority = secondPriority;
        this.isAssoc = isAssoc;
        this.canUnbraceWithOth = canUnbraceWithOth;
    }

    protected abstract String getStringOper();

    @Override
    public String toString() {
        return "(" + exp1.toString() + " " + getStringOper() + " " + exp2.toString() + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder e1 = new StringBuilder("");
        StringBuilder e2 = new StringBuilder("");
        e1.append(exp1.toMiniString());
        if (exp1.getClass() != Const.class
                && exp1.getClass() != Variable.class
                && !((exp1 instanceof BinaryOper)
                && ((BinaryOper) exp1).getPriority() <= getPriority()
        )
        ) {
            e1.insert(0, "(");
            e1.append(")");
        }
        e1.append(" ");
        e1.append(getStringOper());
        e1.append(" ");

        e2.append(exp2.toMiniString());
        if (exp2.getClass() == Const.class
                || exp2.getClass() == Variable.class
                || (
                (exp2 instanceof BinaryOper)
                        && (
                        ((BinaryOper) exp2).getPriority() < getPriority()
                                || (((BinaryOper) exp2).getPriority() == getPriority()) && ((isAssoc && getClass() == exp2.getClass()) || (isAssoc && getClass() != exp2.getClass() && canUnbraceWithOth))
                )
        )
        ) {
        } else {
            e2.insert(0, "(");
            e2.append(")");
        }
        e1.append(e2);
        return e1.toString();
    }
}
