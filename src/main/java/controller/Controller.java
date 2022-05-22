package controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Laptop;
import view.View;

@Getter
@Setter
@ToString
public class Controller {
    private Laptop laptop;
    private View view;

    public Controller(Laptop laptop, View view) {
        this.laptop = laptop;
        this.view = view;
    }

    public void processUserInput() {
        String name = view.printName();
        if (name.matches("[0-9]+")) {
            view.printError();
            processUserInput();
        } else {
            setLaptopName(name);
            processUserFrequency();
        }
    }

    public void processUserFrequency() {
        double frequency = view.printFrequency();
        if (frequency < 0 || frequency > 10) {
            view.printError();
            processUserFrequency();
        } else {
            setLaptopFrequency(frequency);
            processUserDiagonal();
        }
    }

    public void processUserDiagonal() {
        double diagonal = view.printDiagonal();
        if (diagonal < 0 || diagonal > 18) {
            view.printError();
            processUserDiagonal();
        } else {
            setLaptopDiagonal(diagonal);
            view.printResult(laptop);
        }
    }

    public void setLaptopName(String name) {
        laptop.setName(name);
    }

    public void setLaptopFrequency(double frequency) {
        laptop.setFrequency(frequency);
    }

    public void setLaptopDiagonal(double diagonal) {
        laptop.setDiagonal(diagonal);
    }

    public String mergeLaptopName(String name) {
        return laptop.getName() + name;
    }

    public Double getLaptopDiagonal() {
        return laptop.getDiagonal();
    }
}
