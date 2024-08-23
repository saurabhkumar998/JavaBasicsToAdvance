package AbstractClassDemo;

public abstract class Animal {

    public abstract void move();
    public abstract void makeNoise();

    public void printClassName() {
        System.out.println(this.getClass().getSimpleName());
    }

}
