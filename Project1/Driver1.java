//Name: Ani Khachatryan
//Project: Project 1: Boundaries/Convex Hull
//Class: COMP282
//File: Driver1.java

import java.util.*;

class Driver1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        Project1 p1 = new Project1();
        p1.read();              //read input.txt.
        p1.sortArray();         //sort all arrays.
        p1.setUpperPoints();    //set all upper points
        p1.setLowerPoints();    //set all lower points
        System.out.println("\nWelcome to Project 1: Boundaries");
        System.out.println("Loading points from input.txt");
        p1.interact();
        /*try {
            do {
                System.out.println("\nTest point:");
                int xVal = input.nextInt();     //user inputs x-value
                int yVal = input.nextInt();     //user inputs y-value
                int x1 = 0;                     //will be used for above and below methods
                int x3 = 0;                     //will be used for above and below methods
                int y1 = 0;                     //will be used for above and below methods
                int y3 = 0;                     //will be used for above and below methods
                // Point pt = new Point(xVal, yVal);
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
                }
            } while(!input.nextLine().equals("quit"));
            input.close();
        }
        catch(InputMismatchException e) {
            input.close();
        }
        */
    }

}