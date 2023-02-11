package expression.exceptions;

import expression.CoolExpression;
import expression.Multiply;
import expression.TripleExpression;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(CoolExpression exp1, CoolExpression exp2) {
        super(exp1, exp2);
    }
}
