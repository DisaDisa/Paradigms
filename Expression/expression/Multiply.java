package expression;

/**
 * Created by Disa on 27.03.2016.
 */
public class Multiply extends AbstractBinaryOperator {
    public  Multiply (AllExpression leftExp, AllExpression rightExp) {
        super(leftExp, rightExp);
    }

    protected int binaryOperation(int left, int right) {
        return left * right;
    }

    protected double binaryOperation(double left, double right) {
        return left * right;
    }
}
