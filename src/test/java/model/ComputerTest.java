package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComputerTest {
    public Computer computer;

    @BeforeEach
    public void init() {
        computer = new Computer("ComputerTest", 2.6, enums.CPU.THIRD, 2, 16, 512);
    }

    @Test
    @DisplayName("It upgrades the computer")
    public void add() {
        computer.addROM(8);
        assertEquals(520, computer.getAmountROM());
    }

    @Test
    public void addNegativeNumber() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            computer.addROM(-12);
        });
    }

    @Test
    @DisplayName("Check if the computer is modern")
    public void checkModern() {
        assertTrue(computer.isModern());
    }

    @Test
    public void createNewEntity() {
        Computer computer = new Computer("ComputerTest", 2.6, enums.CPU.THIRD, 2, 16, 512);
    }

    @Test
    public void createEmptyComputer() {
        Computer computer = new Computer();
    }
}
