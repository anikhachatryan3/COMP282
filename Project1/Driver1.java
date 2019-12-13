//Name: Ani Khachatryan
//Project: Project 1: Boundaries/Convex Hull
//Class: COMP282
//File: Driver1.java

class Driver1 {

    public static void main(String[] args) {
        Project1 p1 = new Project1();
        p1.read();              //read input.txt.
        p1.sortArray();         //sort all arrays.
        p1.setUpperPoints();    //set all upper points.
        p1.setLowerPoints();    //set all lower points.
        p1.welcomeMessages();   //displaying welcome messages.
        p1.interact();          //interacting with user.
    }

}