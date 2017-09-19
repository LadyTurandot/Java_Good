package ru.stqa.pft.sandbox;

import com.sun.org.apache.xpath.internal.SourceTree;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("This is my first program");

        double x = 4;
        double y = 9;
        double x1 = 16;
        double y1 = 25;
        System.out.println ("Расстояние между точками с координатами: " + x + ";" + y + " и " + x1 + ";" + y1 + " = " + distance(x, y, x1, y1));
    }

    public static double distance (double x, double y, double x1, double y1) {
        return Math.sqrt(((x-x1)*(x-x1))+((y-y1)*(y-y1)));
    }
}



