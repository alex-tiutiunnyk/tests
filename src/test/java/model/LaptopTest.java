package model;

import enums.CPU;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LaptopTest {
    public Laptop laptop;

    @BeforeEach
    public void init() {
        laptop = new Laptop("LaptopTest", 3.2, CPU.FOURTH, 4, 16, 512, 15.2, 1.2, 2600);
    }

    @Test
    public void add() {
        laptop.addROM(8);
        assertEquals(520, laptop.getAmountROM());
    }

    @Test
    public void checkComfortable() {
        assertFalse(laptop.isComfortable());
    }

    @Test
    public void editLaptop() {
        laptop.setDiagonal(14.2);
        assertEquals(14.2, laptop.getDiagonal());
    }

    @Test
    public void negativeDiagonal() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            laptop.setDiagonal(-2);
        });
    }

    @Test
    public void playTest() throws InterruptedException {
        laptop.play();
    }

    @Test
    public void createNewEntity() {
        Laptop laptop = new Laptop("LaptopTest", 3.2, CPU.FOURTH, 4, 16, 512, 15.2, 1.2, 2600);
    }
}
