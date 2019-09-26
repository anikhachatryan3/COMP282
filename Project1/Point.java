//Name: Ani Khachatryan
//Project: Project 1: Boundaries/Convex Hull
//Class: COMP282
//File: Point.java

import java.util.*;
import java.io.*;

class Point implements Comparable<Point> {
    private int x;
    private int y;

    Point(int x, int y) {   //constructor for Point
        this.x = x;
        this.y = y;
    }

    public int getX() {     //method used to get x
        return this.x;
    }

    public int getY() {     //method used to get y
        return this.y;
    }

    @Override
    public int compareTo(Point co) {
        int compareX = ((Point)co).getX();
        return this.x - compareX;
    }
}