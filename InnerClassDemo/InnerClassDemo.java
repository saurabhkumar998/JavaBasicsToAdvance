package InnerClassDemo;

public class InnerClassDemo {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.greet();

        OuterClass.InnerClass inner = new OuterClass().new InnerClass();
        inner.greet();

        // creating an instance of static inner class without creating an instance of the outer class
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();

        staticInnerClass.greet();
        System.out.println(staticInnerClass.getClass().getSimpleName());

    }
}
