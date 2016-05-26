package expression;

/**
 * Created by Disa on 27.03.2016.
 */
public class Variable implements AllExpression {
    private String variable;
    public Variable(String variable){
        this.variable = variable;
    }

    public int evaluate(int number) {
        return number;
    }

    public double evaluate(double number) {
        return number;
    }
}
