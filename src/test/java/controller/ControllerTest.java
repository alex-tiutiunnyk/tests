package controller;

import model.Laptop;
import org.junit.Before;
import org.junit.Test;
import view.View;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControllerTest {
    private Laptop laptop;
    private View view;
    private Controller controller;

    @Before
    public void init() {
        laptop = mock(Laptop.class);
        view = mock(View.class);
        controller = mock(Controller.class);
    }

    @Test
    public void testMock() {
        controller.setLaptopDiagonal(15.1);
        verify(controller).setLaptopDiagonal(anyDouble());
    }

    @Test
    public void testMockView() {
        view.printName();
        verify(view).printName();
    }

    @Test
    public void testPrintLaptop() {
        view.printResult(laptop);
        verify(view).printResult(anyObject());
    }

    @Test
    public void testStubParameter() {
        laptop.setDiagonal(15.1);
        when(controller.getLaptopDiagonal()).thenReturn(15.1);
        stub(controller.getLaptopDiagonal()).toReturn(11.0);
        doReturn(11.0).when(laptop).getDiagonal();

        assertEquals(11.0, controller.getLaptopDiagonal());
    }

    @Test
    public void test() {
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        String result = i.next() + " " + i.next();
        assertEquals("Hello World", result);
    }

    @Test
    public void testMatchers() {
        laptop.setName("My");

        when(controller.mergeLaptopName(endsWith("top"))).thenReturn("MyLaptop");
        stub(controller.mergeLaptopName(contains("Test"))).toReturn("MyLaptop_0");
        when(controller.mergeLaptopName(eq("Hello"))).thenReturn("MyHello");

        assertEquals("MyLaptop_0", controller.mergeLaptopName("LapTest"));
        assertEquals("MyLaptop", controller.mergeLaptopName("Laptop"));
        assertEquals("MyHello", controller.mergeLaptopName("Hello"));
    }
}
