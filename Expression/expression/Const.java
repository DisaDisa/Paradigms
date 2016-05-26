package expression;

/**
 * Created by Disa on 27.03.2016.
 */
public class Const implements AllExpression {
    private int cnst;

    public Const (int cnst) {
        this.cnst = cnst;
    }

    public int evaluate(int x) {
        return cnst;
    }

    public double evaluate(double x) {
        return cnst;
    }
}
