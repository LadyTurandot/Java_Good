package ru.stqa.pft.sandbox;

import com.sun.org.apache.xpath.internal.SourceTree;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("This is my first program");

        Point p1 = new Point (4,9);
        Point p2 = new Point (16,25);
       
        System.out.println ("Расстояние между точками с координатами: " + p1.x + ";" + p1.y + " и " + p2.x + ";" + p2.y + " = " + p1.distance(p2));
    }

}



