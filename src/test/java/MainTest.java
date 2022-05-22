import annotation.CustomAnnotation;
import model.Computer;
import model.Laptop;
import model.MyProxy;
import enums.CPU;
import enums.Modifiers;
import interfaces.ILaptop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MainTest {
    public Computer computer;
    public Laptop laptop;

    @BeforeEach
    public void init() {
        computer = new Computer("ComputerTest", 2.6, enums.CPU.THIRD, 2, 16, 512);
        laptop = new Laptop("LaptopTest", 3.2, CPU.FOURTH, 4, 16, 512, 15.2, 1.2, 2600);
    }

    @Test
    public void checkAnnotationsTest() throws InvocationTargetException, IllegalAccessException {
        Main.checkAnnotation(computer, CustomAnnotation.class);
        assertEquals(517, computer.getAmountROM());
    }

    @Test
    public void getModifiers() {
        List<Modifiers> classModifiers = Main.getClassModifiers(laptop);
        List<Modifiers> mocked = new ArrayList<>();
        mocked.add(Modifiers.PRIVATE);
        mocked.add(Modifiers.PUBLIC);
        mocked.add(Modifiers.PRIVATE);

        assertNotEquals(mocked, classModifiers);
    }

    @Test
    public void checkProxy() {
        ILaptop proxy = (ILaptop) MyProxy.newProxyInstance(laptop);
        assertEquals(15.2, proxy.getDiagonal());
    }

    @Test
    public void throwProxyException() {
        Assertions.assertThrows(UndeclaredThrowableException.class, () -> {
            ILaptop proxy = (ILaptop) MyProxy.newProxyInstance(laptop);
            System.out.println(proxy.getDiagonal());
            proxy.setDiagonal(15.2);
        });
    }

    @Test
    public void printAllMethods() {
        Arrays.stream(laptop.getClass().getDeclaredMethods())
                .map(method -> Modifier.toString(
                        method.getModifiers()) + " " +
                        method.getReturnType().getSimpleName() + " " +
                        method.getName()).forEach(System.out::println);
    }

    @Test
    public void printClassInformation() {
        Main.getAllMethods(laptop);
    }

    @Test
    public void getAnnotations() {
        Main.getClassAnnotations(computer);
    }
}
