package LambdaExpressionFunctionalInterfaceAndMethodReference;

import java.util.Arrays;
import java.util.function.Supplier;

class PlainOld {
    public static int lastId = 1;
    public int id;

    public PlainOld() {
        id = lastId++;
        System.out.println("Creating PlainOld Object with id : " + id);
    }
}
public class MethodReferenceDemo {

    public static void main(String[] args) {

        //Supplier<PlainOld> supplier = () -> new PlainOld();

        // the above line can also be written using method references
        Supplier<PlainOld> supplier = PlainOld::new;
        supplier.get();

        PlainOld[] objects = new PlainOld[10];

        Arrays.setAll(objects, i -> supplier.get());
    }
}
