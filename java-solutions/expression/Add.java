package expression;

public class Add extends BinaryOper {
    public Add(CoolExpression e1, CoolExpression e2) {
        super(e1, e2, 2, 1, true, true);
    }

    @Override
    protected String getStringOper() {
        return "+";
    }

    @Override
    public int evaluate(int x) {
        return exp1.evaluate(x) + exp2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return exp1.evaluate(x) + exp2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return exp1.evaluate(x, y, z) + exp2.evaluate(x, y, z); // :NOTE: копипаста
    }
}

/**
 * Exception in thread "main" java.lang.AssertionError: Equals to 10.0
 *         at base.Asserts.error(Asserts.java:75)
 *         at base.Asserts.assertTrue(Asserts.java:41)
 *         at expression.ExpressionTester.lambda$checkEqualsAndToString$3(ExpressionTester.java:85)
 *         at base.TestCounter.lambda$test$0(TestCounter.java:58)
 *         at base.TestCounter.lambda$testV$2(TestCounter.java:71)
 *         at base.Log.silentScope(Log.java:40)
 *         at base.TestCounter.testV(TestCounter.java:70)
 *         at base.TestCounter.test(TestCounter.java:57)
 *         at expression.ExpressionTester.checkEqualsAndToString(ExpressionTester.java:81)
 *         at expression.ExpressionTester$Generator.lambda$testRandom$7(ExpressionTester.java:249)
 *         at expression.common.Generator.lambda$testRandom$7(Generator.java:111)
 *         at base.Log.lambda$action$0(Log.java:18)
 *         at base.Log.silentScope(Log.java:40)
 *         at base.Log.scope(Log.java:31)
 *         at base.Log.scope(Log.java:24)
 *         at expression.common.Generator.testRandom(Generator.java:99)
 *         at expression.common.Generator.testRandom(Generator.java:86)
 *         at expression.ExpressionTester$Generator.testRandom(ExpressionTester.java:237)
 *         at base.Log.lambda$action$0(Log.java:18)
 *         at base.Log.silentScope(Log.java:40)
 *         at base.Log.scope(Log.java:31)
 *         at base.Log.scope(Log.java:24)
 *         at expression.ExpressionTester.test(ExpressionTester.java:63)
 *         at expression.ExpressionTest.lambda$v$0(ExpressionTest.java:23)
 *         at base.Selector.lambda$test$2(Selector.java:79)
 *         at base.Log.lambda$action$0(Log.java:18)
 *         at base.Log.silentScope(Log.java:40)
 *         at base.Log.scope(Log.java:31)
 *         at base.Log.scope(Log.java:24)
 *         at base.Selector.lambda$test$3(Selector.java:79)
 *         at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
 *         at base.Selector.test(Selector.java:79)
 *         at base.Selector.main(Selector.java:51)
 *         at expression.ExpressionTest.main(ExpressionTest.java:27)
 */