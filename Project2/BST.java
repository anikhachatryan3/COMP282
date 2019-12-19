//Name: Ani Khachatryan
//Project: Project 2 - Height of Trees
//Class: COMP282
//File: BST.java

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BST {

    //creating needed vars
    public static double sumBST = 0;
    public static double count = 0;

    public Node root;
    public BST() {
        this.root = null;
    }

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
        root = insertRecursion(root, id);
    }
    public Node insertRecursion(Node root, int num) {
        if(root == null) {
			return new Node(num);
		}
		if(num < root.data) {
			root.left = insertRecursion(root.left, num);
		}
		else {
			root.right = insertRecursion(root.right, num);
		}
		return root;
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
        //getting four decimal places
        DecimalFormat newForm = new DecimalFormat("0.0000");
        newForm.setRoundingMode(RoundingMode.DOWN);
        int[] arr = new int[bs];
        for(int j = 0; j < bs; j++) {
            arr[j] = j+1;
        }
        //find all possible combos
        BSTCombos(bs, arr);
        double heightBST = sumBST/count;
        String bsA = newForm.format(heightBST);
        System.out.print("The average height of a BST with " + bs + " nodes is " + bsA);
        System.out.println();
    }

}
