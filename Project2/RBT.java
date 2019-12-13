//Name: Ani Khachatryan
//Project: Project 2 - Height of Trees
//Class: COMP282
//File: RBT.java

import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RBT {

    public static double sumRBT = 0;
    public static double count = 0;

    public Node root;   //was static
    public RBT() {
        this.root = null;
    }
    
    // public boolean RBTfind(int id) {
    //     Node current = root;
    //     while(current != null) {
    //         if(current.data == id) {
    //             return true;
    //         }
    //         else if(current.data > id) {
    //             current = current.left;
    //         }
    //         else {
    //             current = current.right;
    //         }
    //     }
    //     return false;
    // }

    // //find Node in tree
    // public Node findNode(int id) {
    //     Node current = root;
    //     while(current != null) {
    //         if(current.data == id) {
    //             return current;
    //         }
    //         else if(current.data > id) {
    //             current = current.left;
    //         }
    //         else {
    //             current = current.right;
    //         }
    //     }
    //     return null;
    // }

    //gets all possible combos/permutations for RBT
    public static void RBTCombos(int num, int[] arr) {
        if(num == 1) {
            RBT rbt = printArr(arr);
            int height = rbt.getHeight(rbt.root);
            rbt.preorderTraversal(rbt.root);
            sumRBT += height;
            count++;
        }
        else {
            for(int i = 0; i < num-1; i++) {
                RBTCombos(num-1, arr);
                if(num%2 == 0) {
                    swap(arr, i, num-1);
                }
                else {
                    swap(arr, 0, num-1);
                }
            }
            RBTCombos(num-1, arr);
        }
    }

    //method to swap
    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    //print array of nodes/inputs method
    private static RBT printArr(int[] input) {
        RBT rbt = new RBT();
        for(int i = 0; i < input.length; i++) {
            rbt.insert(input[i]);
        }
        return rbt;
    }

    //insert node method
    public void insert(int id) {
        Node newNode = new Node(id);
        if(root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while(true) {
            parent = current;
            if(id < current.data) {			
                current = current.left;
                if(current == null) {
                    parent.left = newNode;
                    return;
                }
            }
            else {
                current = current.right;
                if(current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    //Preorder Traversal Method
    public void preorderTraversal(){
        preorderTraversal(root); 
    }
    private void preorderTraversal(Node root) {
        if(root != null){
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    //get height of tree
    public int getHeight(Node n) {
        if(n == null) {
            return 0;
        }
        else {
            int heightLeft = getHeight(n.left);
            int heightRight = getHeight(n.right);
            if(heightLeft > heightRight) {
                return (heightLeft+1);
            }
            else {
                return (heightRight+1);
            }
        }
    }

    //interacts with user
    public void interactRBT(int rb) {
        DecimalFormat newForm = new DecimalFormat("0.0000");
        newForm.setRoundingMode(RoundingMode.DOWN);
        // Scanner val = new Scanner(System.in);
        // int rb = Integer.parseInt(val);
        int[] arr = new int[rb];
        for(int j = 0; j < rb; j++) {
            arr[j] = j+1;
        }
        RBTCombos(rb, arr);
        double heightRBT = sumRBT/count;
        String ave = newForm.format(heightRBT);
        System.out.println();
        System.out.print("The average height of a RBT with " + rb + " nodes is " + ave);
        System.out.println("\n");
    }

}