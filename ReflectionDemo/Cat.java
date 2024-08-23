package ReflectionDemo;

public class Cat {
    private final String name;
    private Integer age;
    private static final String field = "This is Private Static Final member variable";

    private static String field2 = "This is private static field";
    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void greet() {
        System.out.println("Hello!!");
    }

    private void privateMethod() {
        System.out.println("This is a Private Method");
    }

    public static void publicStaticMethod() {
        System.out.println("This is a Public Static Method");
    }

    private static void privateStaticMethod() {
        System.out.println("This is a Private Static Method");
    }
}
