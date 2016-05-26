package expression;
/**
 * Created by Disa on 27.03.2016.
 */
public abstract class AbstractBinaryOperator implements AllExpression {
    private AllExpression leftExp, rightExp;
    public AbstractBinaryOperator (AllExpression left, AllExpression right) {
        this.leftExp = left;
        this.rightExp = right;
    }

    public int evaluate(int number) {
        int left = leftExp.evaluate(number);
        int right = rightExp.evaluate(number);
        return binaryOperation(left, right);
    }

    public double evaluate(double number) {
        double left = leftExp.evaluate(number);
        double right = rightExp.evaluate(number);
        return binaryOperation(left, right);
    }


    protected abstract int binaryOperation(int left, int right);

    protected abstract double binaryOperation(double left, double right);
}
