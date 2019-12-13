//Name: Ani Khachatryan
//Project: Project 2 - Height of Trees
//Class: COMP282
//File: BST.java

import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BST {

    public static double sumBST = 0;
    public static double count = 0;

    public Node root;   //was static
    public BST() {
        this.root = null;
    }
    
    // public boolean BSTfind(int id) {
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

    //gets all possible combos/permutations for BST
    public static void BSTCombos(int num, int[] arr) {
        if(num == 1) {
            BST bst = printArr(arr);
            int height = bst.getHeight(bst.root);
            bst.preorderTraversal(bst.root);
            sumBST += height;
            count++;
        }
        else {
            for(int i = 0; i < num-1; i++) {
                BSTCombos(num-1, arr);
                if(num%2 == 0) {
                    swap(arr, i, num-1);
                }
                else {
                    swap(arr, 0, num-1);
                }
            }
            BSTCombos(num-1, arr);
        }
    }

    //method to swap
    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    //print array of nodes/inputs method
    private static BST printArr(int[] input) {
        BST bst = new BST();
        for(int i = 0; i < input.length; i++) {
            bst.insert(input[i]);
        }
        return bst;
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

    //prints the introduction statements for user to see
    public void printIntro() {
        System.out.println("\nWelcome to Project 2: Average Heights");
        System.out.println("Enter a value for n: ");
    }

    //interacts with user
    public void interactBST(int bs) {
        DecimalFormat newForm = new DecimalFormat("0.0000");
        newForm.setRoundingMode(RoundingMode.DOWN);
        // Scanner val = new Scanner(System.in);
        // int bs = Integer.parseInt(val);
        int[] arr = new int[bs];
        for(int j = 0; j < bs; j++) {
            arr[j] = j+1;
        }
        //find all possible combos
        BSTCombos(bs, arr);
        double heightBST = sumBST/count;
        String bsA = newForm.format(heightBST);
        System.out.print("The average height of a BST with " + bs + " nodes is " + bsA);
    }

}