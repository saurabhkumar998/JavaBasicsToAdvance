package AbstractClassDemo;

public class Dog extends Animal{
    @Override
    public void move() {
        System.out.println("Dog is moving");
    }

    @Override
    public void makeNoise() {
        System.out.println("Dog is Barking");
    }
}
