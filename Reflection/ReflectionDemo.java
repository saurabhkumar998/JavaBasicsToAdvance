package Reflection;

import java.lang.reflect.*;

public class ReflectionDemo {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        Cat myCat = new Cat("MewMew", 5);
        // name of the cat before updating using reflection
        System.out.println(myCat.getName());

        // #### REFLECTION ON FIELDS/MEMBER VARIABLES ########
        // accessing the list of fields on the cat object
        //getField() is used to access the public fields on the class and
        // on the other hand getDeclaredFields() is used to access all the fields/member variables (public/private) on the class
        //getClass() is used to determine the class at runtime
        Field[] myCatFields = myCat.getClass().getDeclaredFields();

        // the class is used when you know the type of the class at compile time
        //Field[] myCatFields = Cat.class.getDeclaredFields();

        // here we are updating the name of the cat using reflection, even though the name field is private and final
        for(Field field : myCatFields) {
            System.out.println(field.getName());
            if(field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(myCat, "Jimmy");
            }
            // accessing a private static final field is not possible even using reflection because it is static final, these fields are initialized at compile time when the class is loaded
            if(field.getName().equals("field")) {
             //   field.setAccessible(true);
              //  field.set(myCat, "This is private static final member variable but I am able to update it");
            }
            // although we can manipulate a private static field. they are initialized when class is loaded but they are not final
            if(field.getName().equals("field2")) {
                field.setAccessible(true);
                field.set(myCat, "test");
            }
        }
        // name of the cat after updating using reflection
        System.out.println(myCat.getName());

        // output of the greet method before updating the hello method
        myCat.greet();
        // another way to access a particular field directly
        Field fieldRef = Cat.class.getDeclaredField("name");
        fieldRef.setAccessible(true);
        fieldRef.set(myCat, "Jacky");

        System.out.println(myCat.getName());


        //###### REFLECTION ON METHODS #########
        // accessing the list of methods on the cat object
        Method[] myCatMethods = myCat.getClass().getDeclaredMethods();

        for(Method method : myCatMethods) {
            //System.out.println(method.getName());
            if(method.getName().equals("privateMethod")) {
                // making the method accessible
                method.setAccessible(true);
                method.invoke(myCat);
            }
            else if(method.getName().equals("publicStaticMethod")) {
                method.setAccessible(true);
                method.invoke(null);
            }
            else if(method.getName().equals("privateStaticMethod")) {
                method.setAccessible(true);
                method.invoke(null);
            }
        }

        // Another way to access a particular method

        Method privateStaticMethodRef = Cat.class.getDeclaredMethod("privateStaticMethod");
        privateStaticMethodRef.setAccessible(true);
        privateStaticMethodRef.invoke(null);

        // ##### REFLECTION ON CONSTRUCTORS ###########
        // accessing the list of constructors on the cat object
        Constructor[] myCatConstructors = myCat.getClass().getConstructors();

        for(Constructor constructor : myCatConstructors) {
            System.out.println(constructor.getName());
            // getting the list of parameters of a particular constructor
            Parameter[] myCatConstructorParameters = constructor.getParameters();

            for(Parameter parameter : myCatConstructorParameters) {
                System.out.println(parameter.getType());
            }
        }
    }
}
