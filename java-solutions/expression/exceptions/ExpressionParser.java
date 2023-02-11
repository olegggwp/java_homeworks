package expression.exceptions;

import expression.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpressionParser implements TripleParser {
    public ExpressionParser() {
    }

    @Override
    public TripleExpression parse(String expression) {
        System.out.println(expression);
        return new TripleExpressionParser(new StringSource(expression)).parseTripleExpression();
    }

    private static class TripleExpressionParser extends BaseParser {
        private static final Map<String, Integer> PRIORITY = Map.of("*", 1, "/", 1, "+", 2, "-", 2, "set", 3, "clear", 3);

        public TripleExpressionParser(CharSource source) {
            super(source);
        }

        public TripleExpression parseTripleExpression() {
            TripleExpression expression = parseTE();
            skipWhitespace();
//            if (!eof()) {
//                throw error("End of expression expected");
//            }
            return expression;
        }


        private TripleExpression parseTE() {
            List<TripleExpression> elements = new ArrayList<>(0);
            List<String> dv = new ArrayList<>();
            skipWhitespace();
            elements.add(parseValue());
            skipWhitespace();
            while (isin(new char[]{'+', '-', '*', '/', 's', 'c'})) {
                String op = String.valueOf(take());
                if (op.equals("s")) {
                    expect("et");
                    op = "set";
                } else if (op.equals("c")) {
                    expect("lear");
                    op = "clear";
                }
                dv.add(op);
                skipWhitespace();
                elements.add(parseValue());
                skipWhitespace();
            }
            updElementsArr(1, elements, dv);
            updElementsArr(2, elements, dv);
            updElementsArr(3, elements, dv);
            return elements.get(0);
        }

        void updElementsArr(int priority, List<TripleExpression> elements, List<String> dv) {
            int i = 0;
            int j;
            while (i < elements.size() - 1) {
                if (elements.get(i) != null) {
                    j = i;
                    while (j < dv.size() && (dv.get(j) == null || PRIORITY.get(dv.get(j)) == priority)) {
                        String ch = dv.get(j);
                        if (ch != null) {
                            elements.set(i, binOp(ch, elements.get(i), elements.get(j + 1)));
                            elements.set(j + 1, null);
                            dv.set(j, null);
                        }
                        j++;
                    }
                }
                i++;
            }
        }

        private TripleExpression binOp(String op, TripleExpression exp1, TripleExpression exp2) {
            return switch (op) {
                case "*" -> new CheckedMultiply(exp1, exp2);
                case "/" -> new CheckedDivide(exp1, exp2);
                case "+" -> new CheckedAdd(exp1, exp2);
                case "-" -> new CheckedSubtract(exp1, exp2);
//                case "set" -> new SetOp(exp1, exp2);
//                case "clear" -> new ClearOp(exp1, exp2);
                default -> throw new IllegalStateException("Unexpected value in binOp: " + op);
            };
        }

        private TripleExpression parseValue() {
            if (take('(')) {
                return parseExpInBr();
            } else if (take('-')) {
//                skipWhitespace();
                if (between('1', '9')) {
                    return parseInteger(true);
                }
                skipWhitespace();
                return new CheckedNegate(parseValue());
            } else if (take('c')) {
                expect("ount");
                skipWhitespace();
                return new CheckedCount(parseValue());
            } else if (isin(new char[]{'x', 'y', 'z'})) {
                return new Variable(String.valueOf(take()));
            } else {
                return parseInteger(false);
            }
        }

        private TripleExpression parseInteger(boolean negative) {
            StringBuilder sb = new StringBuilder(negative ? "-" : "");
            takeInteger(sb);
            try {
                return new Const(Integer.parseInt(String.valueOf(sb)));
            } catch (NumberFormatException e) {
                throw error("Number can't be formated in int: " + sb);
            }
        }

        private TripleExpression parseExpInBr() {
            skipWhitespace();
            if (take(')')) {
                throw error("Expected expression, found nothing");
            }
            TripleExpression exp = parseTE();
            expect(')');
            return exp;
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        private void takeInteger(final StringBuilder sb) {
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                takeDigits(sb);
            } else {
                throw error("Invalid number: " + take() + "\n");
            }
        }


        private void skipWhitespace() {
            while (isWs()) {
                take();
            }
        }
    }


}
