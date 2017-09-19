package ru.stqa.pft.sandbox;

import com.sun.org.apache.xpath.internal.SourceTree;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("This is my first program");

        Point p1 = new Point ();
        Point p2 = new Point ();
       p1.x = 4;
       p1.y = 9;
       p2.x = 16;
       p2.y = 25;
        System.out.println ("Расстояние между точками с координатами: " + p1.x + ";" + p1.y + " и " + p2.x + ";" + p2.y + " = " + distance(p1, p2));
    }

    public static double distance (Point p1, Point p2) {
        return Math.sqrt(((p1.x-p2.x)*(p1.x-p2.x))+((p1.y-p2.y)*(p1.y-p2.y)));
    }
}



