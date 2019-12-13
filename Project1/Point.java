//Name: Ani Khachatryan
//Project: Project 1: Boundaries/Convex Hull
//Class: COMP282
//File: Point.java

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
    public int compareTo(Point c2) {
        int xVal = ((Point)c2).getX();
        return this.x - xVal;
    }
}