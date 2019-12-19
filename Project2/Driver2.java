//Name: Ani Khachatryan
//Project: Project 2 - Height of Trees
//Class: COMP282
//File: Driver2.java

import java.util.Scanner;

class Driver2 {
    public static void main(String[] args) {
        BST bst = new BST();                        //new object for BST
        RBT rbt = new RBT();                        //new object for RBT
        bst.printIntro();                           //printing introduction
        Scanner val = new Scanner(System.in);       //new scanner for user input
        int bs = val.nextInt();                     //changing user input to int
        val.close();                                //closing scanner/program done
        if(bs == 1) {
            System.out.println("The average height of a BST with 1 node is 1.");
            System.out.println("The average height of a RBT with 1 node is 1.");
        }
        else {
            bst.interactBST(bs);                    //interacting with user for BST
            rbt.interactRBT(bs);                    //interacting with user for RBT
        }
    }
}
