package MultipleInheritanceDemo;

interface A {
    void hello();
}

interface B {
    void hello();
}

class C implements A, B {

    @Override
    public void hello() {
        System.out.println("Hello from C!!!");
    }
}

public class MultipleInheritanceUsingInterface {
    public static void main(String[] args) {
        C c = new C();
        c.hello();
    }
}
