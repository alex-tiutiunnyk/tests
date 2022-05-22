package view;

import model.Laptop;

import java.util.Scanner;

public class View {
    public String printName() {
        System.out.print("Enter the name of your machine: ");
        Scanner inputStream = new Scanner(System.in);
        return inputStream.nextLine();
    }

    public double printFrequency() {
        System.out.print("Enter the frequency of your machine: ");
        Scanner inputStream = new Scanner(System.in);
        return inputStream.nextDouble();
    }

    public double printDiagonal() {
        System.out.print("Enter the diagonal of your machine: ");
        Scanner inputStream = new Scanner(System.in);
        return inputStream.nextDouble();
    }

    public void printError() {
        System.out.println("Input is invalid. Input correct command.");
    }

    public void printResult(Laptop laptop) {
        System.out.println(laptop.getName() + " " + laptop.getFrequency() + " " + laptop.getDiagonal());
    }
}
