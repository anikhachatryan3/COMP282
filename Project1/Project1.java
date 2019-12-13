//Name: Ani Khachatryan
//Project: Project 1: Boundaries/Convex Hull
//Class: COMP282
//File: Project1.java

import java.util.*;
import java.io.*;

class Project1 {

    //the 3 arraylists used to store the points in.
    ArrayList<Point> myArr;     //arraylist that will contain all 
                                //the points.
    ArrayList<Point> upper;     //arraylist that will contain all 
                                //upper points (points greater than line).
    ArrayList<Point> lower;     //arraylist that will contain all 
                                //lower points (points less than line).

    public Project1() {
        myArr = new ArrayList<Point>();
        upper = new ArrayList<Point>();
        lower = new ArrayList<Point>();
    }

    //this method reads the "input.txt" file for the 
    //coordinates and stores them in myArr, 
    //upper, and lower (all three arraylists).
    public void read() {    
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while(sc.hasNext()) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                Point pt = new Point(x, y);
                myArr.add(pt);      //adding points to myArr.
                upper.add(pt);      //adding points to upper.
                lower.add(pt);      //adding points to lower.
            }
            sc.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
    
    //this method sorts the points left to right by 
    //the x-coordinates.
    public void sortArray() { 
        Collections.sort(myArr);    //sorting myArr
        Collections.sort(upper);    //sorting upper
        Collections.sort(lower);    //sorting lower
    }
    
    //method finds all upper points in the arraylist 
    //and stores them in upper (arraylist).
    public void setUpperPoints() {
        for(int i = 0; i < upper.size() - 2; i++) {
            int a1 = upper.get(i).getX();       //getting x1
            int b1 = upper.get(i).getY();       //getting y1
            int a2 = upper.get(i+1).getX();     //getting x2
            int b2 = upper.get(i+1).getY();     //getting y2
            int a3 = upper.get(i+2).getX();     //getting x3
            int b3 = upper.get(i+2).getY();     //getting y3
            if(a1 == a3) {
                break;
            }
            if(isBelow(a1, a2, a3, b1, b2, b3)) {
                upper.remove(i+1);      //remove points if below the line.
                i = -1;                 //start again from the beginning (i = 0).
            }
        }
    }

    //method finds all lower points in the arraylist and stores 
    //them in lower (arraylist).
    public void setLowerPoints() { 
        for(int i = 0; i < lower.size() - 2; i++) {
            int a1 = lower.get(i).getX();       //getting x1
            int b1 = lower.get(i).getY();       //getting y1
            int a2 = lower.get(i+1).getX();     //getting x2
            int b2 = lower.get(i+1).getY();     //getting y2
            int a3 = lower.get(i+2).getX();     //getting x3
            int b3 = lower.get(i+2).getY();     //getting y3
            if(a1 == a3) {
                break;
            }
            if(isAbove(a1, a2, a3, b1, b2, b3)) {
                lower.remove(i+1);      //remove points if above the line.
                i = -1;                 //start again from the beginning (i = 0).
            }
        }
    }

    //method checks if user's x and y values fall under
    //one of the special cases
    private boolean specialCheck(int xVal, int yVal) {
        boolean answer;
        //if user's x-value is less than the 
        //first/far left/smallest x-value in 
        //the upper arraylist
        if(xVal < upper.get(0).getX()) {
            answer = false;
        }
        //is user's x-value too far right?
        else if(xVal > upper.get(upper.size()-1).getX()) {
            answer = false;
        }
        //is user's x-value too high?
        else if(aboveUpper(xVal, yVal)) {
            answer = false;
        }
        //is user's x-value too low?
        else if(belowLower(xVal, yVal)) {
            answer = false;
        }
        //or is user's x-value good?
        else{
            answer = true;
        }
        //if answer is true
        if(answer) {
            //user's x and y values are inside
            System.out.println("Inside");
        }
        //if answer is not true
        else {
            //user's x and y values are outside
            System.out.println("Outside");
        }
        return answer;
    }

    //method will display welcome messages when called.
    public void welcomeMessages() {
        System.out.println("\nWelcome to Project 1: Boundaries");
        System.out.println("Loading points from input.txt");
    }

    //method will interact with user and user's input.
    public void interact() {
        Scanner input = new Scanner(System.in);
        try {
            do {
                System.out.println("\nTest point:");
                int xVal = input.nextInt();     //user inputs x-value
                int yVal = input.nextInt();     //user inputs y-value
                specialCheck(xVal, yVal);       //check if user's x and y
                                                //values are one of the special cases.
            } while(!input.nextLine().equals("quit"));
            input.close();
        }
        catch(InputMismatchException e) {
            input.close();
        }
    }
    
    //method finds whether y2 is above the slope of the line.
    private boolean isAbove(int x1, int x2, int x3, int y1, int y2, int y3) {
        float a = (((float)(y3-y1))/(x3-x1))*x2;
        float b = (((float)(y3-y1))/(x3-x1))*x1;
        float c = a + y1 - b;     //equation of the line.
        return y2 > c;      //return points greater than the line.
    }

    //method finds whether y2 is below the slope of the line.
    private boolean isBelow(int x1, int x2, int x3, int y1, int y2, int y3) { 
        float a = (((float)(y3-y1))/(x3-x1))*x2;
        float b = (((float)(y3-y1))/(x3-x1))*x1;
        float c = a + y1 - b;     //equation of the line.
        return y2 < c;      //return points lower than the line.
    }

    //method finds whether inserted x-value and y-value 
    //are above the upper boundary.
    private boolean aboveUpper(int xVal, int yVal) {
        boolean answer = false;
        for(int i = 0; i < upper.size(); i++) {
            //if user's x-value is the same as an x-value in
            //the upper arraylist.
            if(xVal == upper.get(i).getX()) {
                //check if user's y-value is greater than current y-value
                //in upper arraylist.
                answer = (yVal > upper.get(i).getY());
            } 
        }
        for(int i = 0; i < upper.size() - 1; i++) {
            //if input is between 2 x-values
            if(xVal > upper.get(i).getX() && xVal < upper.get(i+1).getX()) {
                int x1 = upper.get(i).getX();    //set as x1 for upper.
                int x3 = upper.get(i+1).getX();  //set as x3 for upper.
                int y1 = upper.get(i).getY();    //set as y1 for upper.
                int y3 = upper.get(i+1).getY();  //set as y3 for upper.
                //checks if user's x and y values are above
                //the line.
                answer = isAbove(x1,xVal,x3,y1,yVal,y3);
            }
        }
        return answer;
    }

    //method finds whether inserted x-value and y-value 
    //are below the lower boundary.
    private boolean belowLower(int xVal, int yVal) {
        boolean answer = false;
        for(int i = 0; i < lower.size(); i++) {
            //if user's x-value is the same as an x-value in
            //the lower arraylist
            if(xVal == lower.get(i).getX()) {
                //checks if user's y-value is less than current y-value
                //in lower arraylist
                answer = (yVal < lower.get(i).getY());
            } 
        }
        for(int i = 0; i < upper.size() - 1; i++) {
            //if input is between 2 x-values
            if(xVal > lower.get(i).getX() && xVal < upper.get(i+1).getX()) {
                int x1 = lower.get(i).getX();    //set as x1 for lower
                int x3 = lower.get(i+1).getX();  //set as x3 for lower
                int y1 = lower.get(i).getY();    //set as y1 for lower
                int y3 = lower.get(i+1).getY();  //set as y3 for lower
                //checks if user's x and y values are below
                //the line.
                answer = isBelow(x1,xVal,x3,y1,yVal,y3);
            }
        }
        return answer;
    }

}