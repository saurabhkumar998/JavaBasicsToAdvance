package InnerClassDemo;

public class OuterClass {
    Integer number = 6;

    public OuterClass() {
    }

    public OuterClass(Integer number) {
        this.number = number;
    }

    public void greet() {
        System.out.println("Hey there!! From Outer Class");

        class LocalInnerClass {
            void greet() {
                System.out.println("Hey there!! From Local Inner Class");
            }
        }

        LocalInnerClass localInnerClass = new LocalInnerClass();
        localInnerClass.greet();
    }


    public class InnerClass {
        public void greet() {
            System.out.println("Hey there!! From Inner Class.");
        }
    }

    public static class StaticInnerClass {
        public void greet() {
            System.out.println("Hey there!! From Static Inner Class.");
        }
    }
}
