package InnerClass;

public class AnonymousClassDemo {
    public static void main(String[] args) {

        // this is an object of an anonymous class which extends the Animal class and overrides its makeNoise method
        // anonymous inner classes are useful when you only ever need one instance of it (for e.g. comparator while sorting)
        Animal anonymousClassObject = new Animal() {
            @Override
            public void makeNoise() {
                System.out.println("makeNoise from Anonymous class");
            }
        };

        anonymousClassObject.makeNoise();

        // this is an object of an anonymous class which implements an interface and implements its methods
        Runnable anonymousClassObj2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside the run method of anonymous class");
            }
        };

        anonymousClassObj2.run();
    }
}
