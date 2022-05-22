package interfaces;

public interface ILaptop {

    boolean isComfortable();

    void play() throws InterruptedException;

    double getDiagonal();

    void setDiagonal(double val);

}
