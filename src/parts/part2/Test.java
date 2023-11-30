package parts.part2;

import java.io.IOException;

public class Test {
    private int x = 1;
    private int y = 1;
    private int z = 1;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public double b = 1.0;

    public void getValue() {
        System.out.println(b);
    }

    public void setB(){
        b = Math.round((Math.pow(y, x) + Math.sqrt(Math.abs(x) + Math.pow(2.71, y)) - (Math.pow(z, 3)*Math.pow(Math.sin(y), 2)/(y + Math.pow(z, 3)/(y-Math.pow(z, 3))))));
    }

    public void setBBBBBB(int x, int y, int z){
        b = Math.round((Math.pow(y, x) + Math.sqrt(Math.abs(x) + Math.pow(2.71, y)) - (Math.pow(z, 3)*Math.pow(Math.sin(y), 2)/(y + Math.pow(z, 3)/(y-Math.pow(z, 3))))));
    }

    public double getB(){
        return b;
    }

    public static void main(String[] args) { //метод main

        Test test = new Test();
        test.setB();
        test.getValue();
    }
}