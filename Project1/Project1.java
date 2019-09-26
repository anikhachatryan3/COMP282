//Name: Ani Khachatryan
//Project: Project 1: Boundaries/Convex Hull
//Class: COMP282
//File: Project1.java

import java.util.*;

import javax.lang.model.util.ElementScanner6;

import java.io.*;

class Project1 {

    //the 3 arraylists used to store the points in.
    ArrayList<Point> myArr;     //arraylist that will contain all 
                                //the points.
    ArrayList<Point> upper;     //arraylist that will contain all 
                                //upper points (points greater than line).
    ArrayList<Point> lower;     //arraylist that will contain all 
                                //lower points (points less than line).
    boolean done;

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
                //System.out.println(x + " " + y);
                Point pt = new Point(x, y);
                myArr.add(pt);      //adding points to myArr.
                upper.add(pt);      //adding points to upper.
                lower.add(pt);      //adding points to lower.
                //idx++;
                // System.out.println(x + " : " + y);
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
        int a1, a2, a3, b1, b2, b3;
        for(int i = 0; i < upper.size() - 2; i++) {
            a1 = upper.get(i).getX();       //getting x1
            b1 = upper.get(i).getY();       //getting y1
            a2 = upper.get(i+1).getX();     //getting x2
            b2 = upper.get(i+1).getY();     //getting y2
            a3 = upper.get(i+2).getX();     //getting x3
            b3 = upper.get(i+2).getY();     //getting y3
            if(a1 == a3) {
                break;
            }
            if(isBelow(a1, a2, a3, b1, b2, b3)) {
                upper.remove(i+1);      //remove points if below the line.
                i = -1;                 //start again from the beginning (i = 0).
            }
        }
        // System.out.println("Upper HERE!!!!!!");
        // print(upper);
    }

    //method finds all lower points in the arraylist and stores 
    //them in lower (arraylist).
    public void setLowerPoints() { 
        int a1, a2, a3, b1, b2, b3;
        for(int i = 0; i < lower.size() - 2; i++) {
            a1 = lower.get(i).getX();       //getting x1
            b1 = lower.get(i).getY();       //getting y1
            a2 = lower.get(i+1).getX();     //getting x2
            b2 = lower.get(i+1).getY();     //getting y2
            a3 = lower.get(i+2).getX();     //getting x3
            b3 = lower.get(i+2).getY();     //getting y3
            if(a1 == a3) {
                break;
            }
            if(isAbove(a1, a2, a3, b1, b2, b3)) {
                lower.remove(i+1);      //remove points if above the line.
                i = -1;                 //start again from the beginning (i = 0).
            }
        }
        // System.out.println("Lower HERE!!!!!!");
        // print(lower);
    }
    public void interact() {
        boolean answer;
        do {
            System.out.println("\nTest point:");
            int xVal = input.nextInt();     //user inputs x-value
            int yVal = input.nextInt();     //user inputs y-value
            
            // too far left?
            if (xVal < upper.get(0).getX())
               answer =false;
            //too far right?
            else if(xVal > upper.get(upper.size()-1).getX())
               answer = false;
            // too high?
            else if (aboveUpper(xVal, yVal))
               answer = false;
            //too low?
            else if(belowLower())
               answer =false;
            // or is it good?
            else
               answer = true;
            if (answer)
               System.out.println("Inside");
            else
                System.out.println("Outside");
        // }
             /*   
            if(p1.insideOutside(xVal, yVal)) {
                System.out.println("Inside");
            }
            else {
                System.out.println("Outside");
            }
            // if (xVal < p1.upper.get(0).getX())
            // //outside
            // else if (xVal > p1.upper.get(n-1).getX())
            // //outside
            // else {
            //     done=false;
            // }

            // for(int i = 0; i < p1.upper.size(); i++) {
            //     if( xVal==p1.upper.get(i).getX())
            //     done =true;
            //       //special check
            // }
            // if (!done) {
            //     for( -1)

            //     //if input is between 2 x-values
            //     if(xVal > p1.upper.get(i).getX() && xVal < p1.upper.get(i+1).getX()) {
            //         x1 = p1.upper.get(i).getX();    //set as x1 for upper
            //         x3 = p1.upper.get(i+1).getX();  //set as x3 for upper
            //         y1 = p1.upper.get(i).getY();    //set as y1 for upper
            //         y3 = p1.upper.get(i+1).getY();  //set as y3 for upper
            //     }
            // }

            //if input (P2) is above line between P1 and P3 for upper
            if(p1.isAbove(x1, xVal, x3, y1, yVal, y3)) {
                System.out.println("Outside");  //input is outside
            }
            //if input (P2) is not above line between P1 and P3 for upper
            if(!p1.isAbove(x1, xVal, x3, y1, yVal, y3)) {
                for(int i = 0; i <= p1.lower.size() - 2; i++) {
                    //if input is between 2 x-values
                    if(xVal > p1.lower.get(i).getX() && xVal < p1.lower.get(i+1).getX()) {
                        x1 = p1.lower.get(i).getX();    //set as x1 for lower
                        x3 = p1.lower.get(i+1).getX();  //set as x3 for lower
                        y1 = p1.lower.get(i).getY();    //set as y1 for lower
                        y3 = p1.lower.get(i+1).getY();  //set as y3 for lower
                    }
                }
                //if input (P2) is above line between P1 and P3 for lower
                if(p1.isAbove(x1, xVal, x3, y1, yVal, y3)) {
                    System.out.println("Inside"); //input is inside
                }
                //if input (P2) is below line between P1 and P3 for lower
                if(p1.isBelow(x1, xVal, x3, y1, yVal, y3)) {
                    System.out.println("Outside");
                }
            }*/
        } while(!input.nextLine().equals("quit"));
        input.close();
    }
    
    //method finds whether y2 is above the slope of the line.
    public boolean isAbove(int x1, int x2, int x3, int y1, int y2, int y3) { 
        float a;
        float b;
        float c;
        a = (((float)(y3-y1))/(x3-x1))*x2;
        b = (((float)(y3-y1))/(x3-x1))*x1;
        c = a + y1 - b;     //equation of the line.
        return y2 > c;      //return points greater than the line.
    }

    //method finds whether y2 is below the slope of the line.
    public boolean isBelow(int x1, int x2, int x3, int y1, int y2, int y3) { 
        float a;
        float b;
        float c;
        a = (((float)(y3-y1))/(x3-x1))*x2;
        b = (((float)(y3-y1))/(x3-x1))*x1;
        c = a + y1 - b;     //equation of the line.
        return y2 < c;      //return points lower than the line.
    }

 /*   private boolean insideOutside(int xVal, int yVal) {
        if (xVal < p1.upper.get(0).getX()) {
            System.out.println("Outside");
        }
        else if (xVal > p1.upper.get(n-1).getX()) {
            System.out.println("Outside");
        }
        else {
            done = false;
        }
        return (belowUpper(xVal, yVal) && aboveLower(xVal, yVal));
    }
*/
    public boolean aboveUpper(int xVal, int yVal) {
        boolean answer;
        for(int i = 0; i < p1.upper.size(); i++) {
            if(xVal==p1.upper.get(i).getX()) {
                answer = (yVal > upper.get(i).getY());
            } 
        }
        for(int i = 0; i < upper.size() - 1; i++) {
            //if input is between 2 x-values
            if(xVal > p1.upper.get(i).getX() && xVal < p1.upper.get(i+1).getX()) {
                    x1 = p1.upper.get(i).getX();    //set as x1 for upper
                    x3 = p1.upper.get(i+1).getX();  //set as x3 for upper
                    y1 = p1.upper.get(i).getY();    //set as y1 for upper
                    y3 = p1.upper.get(i+1).getY();  //set as y3 for upper
                    answer = isAbove(x1,xVal,x3,y1,yVal,y3);
            }
        }
        return answer;
    }

    public boolean aboveLower(int xVal, int yVal) {
        for(int i = 0; i < p1.lower.size(); i++) {
            if(xVal==p1.lower.get(i).getX())
            done =true;
            if(yVal < p1.lower.get(i).getY()) {
                System.out.println("Outside");
            } 
        }
        if (!done) {
            for(int i = 0; i < lower.size() - 1; i++) {
                //if input is between 2 x-values
                if(xVal > p1.lower.get(i).getX() && xVal < p1.lower.get(i+1).getX()) {
                    x1 = p1.lower.get(i).getX();    //set as x1 for upper
                    x3 = p1.lower.get(i+1).getX();  //set as x3 for upper
                    y1 = p1.lower.get(i).getY();    //set as y1 for upper
                    y3 = p1.lower.get(i+1).getY();  //set as y3 for upper
                }
            }
        }
    }

    // public void print(ArrayList<Point> myArr) { //using this method to print the coordinates.
    //     for(int i = 0; i <= myArr.size()-1; i++) {
    //         Point p = myArr.get(i);
    //         System.out.println("(" + p.getX() + ", " + p.getY() + ")");
    //     }
    // }
}