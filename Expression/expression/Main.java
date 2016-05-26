package expression;

import java.util.Scanner;

/**
 * Created by Disa on 27.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int ans = new Add(
                new Subtract(
                        new Multiply(
                                new Variable("x"),
                                new Variable("x")
                        ),
                        new Multiply(
                                new Const(2),
                                new Variable("x")
                        )
                ), new Const(1)
        ).evaluate(x);
        System.out.println(ans);
    }
}
