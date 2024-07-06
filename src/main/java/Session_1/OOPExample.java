package Session_1;

// This example shows a class with 2 instance variables represents its state

public class OOPExample {
    private double x;
    private double y;

    // A constructor to set the variables
    public OOPExample(double x1, double y1){
        x = x1;
        y = y1;
    }

    // This method takes a double value v, and calculates the z after reading x and y to use in its calculations
    // It's clear that it's not a pure function
    public double getZ(double v){
        double z;
        if(x > 10){
            z = (x + y) / 2 + v;
        }else {
            z = (x - y) / 2 - v;
        }
        return z;
    }

    // This method takes a value v and updates x after reading y to use it in its calculations
    public void updateX(double v){
        x = y + 2 * v;
    }

    // This method takes a value v and updates y after reading x to use it in its calculations
    public  void updateY(double v){
        y = x - 2 * v;
    }
}
