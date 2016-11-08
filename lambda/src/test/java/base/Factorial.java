package base;

import java.util.function.IntUnaryOperator;

/**
 * Created by akui on 16/11/6.
 */
public class Factorial {

    IntUnaryOperator fact;

    public Factorial() {
        fact = i -> i <= 0 ? 1 : i + fact.applyAsInt(i - 1);
    }

    public IntUnaryOperator getFact() {
        return fact;
    }

    public static void main(String[] args) {
        int res = new Factorial().getFact().applyAsInt(4);
        System.out.println(res);
    }
}
