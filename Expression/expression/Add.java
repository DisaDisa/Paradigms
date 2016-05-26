package expression;

/**
 * Created by Disa on 27.03.2016.
 */
public class Add extends AbstractBinaryOperator {
    public Add (AllExpression leftExp, AllExpression rightExp) {
        super(leftExp, rightExp);
    }

    protected int binaryOperation(int left, int right) {
        return left + right;
    }

    protected double binaryOperation(double left, double right) {
        return left + right;
    }
}
