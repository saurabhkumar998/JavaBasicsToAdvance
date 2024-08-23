package LambdaExpressionFunctionalInterfaceAndMethodReference;

import java.util.function.BinaryOperator;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {

        Integer result = calculator((a,b) -> a+b, 5, 4);
        var result2 = calculator((a,b) -> a/b, 10.0, 2.5);
        var result3 = calculator2((a,b) -> a.toUpperCase() + " " + b.toUpperCase(), "Ryan", "Reynolds");

    }

    // we can also pass java's inbuilt BinaryOperator interface in-place of the custom Operation interface
    public static <T> T calculator(Operation<T> function, T value1, T value2) {
        T result = function.operate(value1, value2);
        System.out.println("Result of Operation: "+ result);
        return result;
    }

    // BinaryOperator is Java's Built-in Functional Interface
    public static <T> T calculator2(BinaryOperator<T> function, T value1, T value2) {
        T result = function.apply(value1, value2);
        System.out.println("Result of Operation: "+ result);
        return result;
    }

}
