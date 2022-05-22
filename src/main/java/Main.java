import annotation.CustomAnnotation;
import enums.CPU;
import enums.Modifiers;
import interfaces.ILaptop;
import model.Computer;
import model.Laptop;
import model.MyProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InterruptedException {

        Laptop laptop = new Laptop("Laptop1", 3.2, CPU.FOURTH, 4, 16, 512, 15.2, 1.2, 2600);
        Computer computer = new Computer("Computer1", 2.6, CPU.THIRD, 2, 16, 512);
        System.out.println();
        System.out.println(laptop);
        System.out.println("The CPU of the new laptop: " + laptop.getCPU());
        System.out.println(computer);

        System.out.println();
        System.out.println("########################################################");
        System.out.println();

        System.out.println("This computer is modern: " + computer.isModern());
        checkAnnotation(computer, CustomAnnotation.class);

        System.out.println();
        System.out.println("########################################################");
        System.out.println();

        System.out.println(laptop.getClass().getName());
        getAllMethods(laptop);

        Annotation[] annotations = getClassAnnotations(computer);
        System.out.println(Arrays.toString(annotations));

        System.out.println();
        System.out.println("########################################################");
        System.out.println();
        //назва суперкласу
        System.out.println("SuperClass name: " + laptop.getClass().getSuperclass().getName());
        //модифікатори класу
        List<Modifiers> classModifiers = getClassModifiers(laptop);
        System.out.println("Class modifiers: " + classModifiers);
        //назва пакета та коротку(просту) назву класу
        String packName = computer.getClass().getPackage().getName();
        String className = computer.getClass().getSimpleName();
        System.out.println("Package name: " + packName + " simple class name: " + className);

        System.out.println();
        System.out.println("########################################################");
        System.out.println();

        Laptop laptop2 = new Laptop();
        ILaptop proxy = (ILaptop) MyProxy.newProxyInstance(laptop2);
        System.out.println(proxy.getDiagonal());
//        proxy.setDiagonal(15.2);

//        laptop.play();
//        System.out.println(laptop.isComfortable());
    }

    public static void checkAnnotation(Object object, Class<CustomAnnotation> annotation) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method :
                methods) {
            if (method.isAnnotationPresent(annotation)) {
                System.out.println("I invoke this method: " + method.getName());
                method.invoke(object, 5);
            }
        }
    }

    public static List<Modifiers> getClassModifiers(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        List<Modifiers> mods = new ArrayList<>();
        for (Field field :
                fields) {
            int modNumber = field.getModifiers();
            enums.Modifiers mod;

            switch (modNumber) {
                case 1:
                    mod = Modifiers.PUBLIC;
                    break;
                case 2:
                    mod = Modifiers.PRIVATE;
                    break;
                case 4:
                    mod = Modifiers.PROTECTED;
                    break;
                default:
                    mod = Modifiers.OTHER;
            }
            mods.add(mod);
        }
        return mods;
    }

    public static void getAllMethods(Object object) {
        Method[] allMethods = object.getClass().getMethods();
        for (Method method :
                allMethods) {
            Annotation[] annotation = method.getAnnotations();
            Class<?>[] types = method.getParameterTypes();

            if (Arrays.stream(annotation).findAny().isPresent() && Arrays.stream(types).findAny().isPresent()) //виводить методи з наявною анотацією та параметрами
//            if(Arrays.stream(annotation).findAny().isPresent()) //якщо потрібні методи тільки з наявністю анотацій
                System.out.println("Method: " + method.getName() + " annotation: " + Arrays.toString(annotation) + " parameter type: " + Arrays.toString(types)); //тут виводяться всі методи
        }
    }

    public static Annotation[] getClassAnnotations(Object object) {
        Annotation[] annotations = new Annotation[5];
        for (Annotation annotation :
                object.getClass().getAnnotations()) {
            Arrays.fill(annotations, annotation);
        }
        return annotations;
    }
}
