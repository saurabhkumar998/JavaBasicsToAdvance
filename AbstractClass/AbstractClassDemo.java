package AbstractClass;

public class AbstractClassDemo {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.makeNoise();
        dog.move();
        dog.printClassName();
    }
}
