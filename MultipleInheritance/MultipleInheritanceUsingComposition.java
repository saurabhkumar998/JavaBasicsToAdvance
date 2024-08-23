package MultipleInheritance;

class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Wheel {
    void rotate() {
        System.out.println("Wheel rotating");
    }
}

class Car {
    private final Engine engine;
    private final Wheel wheel;

    public Car() {
        engine = new Engine();
        wheel = new Wheel();
    }

    public void start() {
        engine.start();
    }

    public void move() {
        wheel.rotate();
    }
}
public class MultipleInheritanceUsingComposition {
    public static void main(String[] args) {
        Car car = new Car();

        car.start();
        car.move();
    }
}
