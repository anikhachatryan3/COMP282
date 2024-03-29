//Name: Ani nhachatryan
//Project: Project 2 - Height of Trees
//Class: COMP282
//File: RBT.java

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RBT {

  //creating vars needed
  public Node root;
  public Node NNULL;
  public static double sumBST = 0;
  public static double count = 0;
  
  //method to actually traverse tree
  private void preOrderTraversal(Node node) {
    if(node != NNULL) {
      preOrderTraversal(node.left);
      preOrderTraversal(node.right);
    }
  }

  //gets all possible permutations/combinations
  public static void RBTCombos(int num, int[] arr) {
    if(num == 1) {
      RBT bst = printArr(arr);
      int height = bst.getHeight(bst.root);
      bst.preOrderTraversal(bst.root);
      sumBST += height;
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

  //method to get tree's height
  public int getHeight(Node root) {
    if(root == NNULL) {
      return 1;
    }
    int leftBlackHeight = getHeight(root.left);
    if(leftBlackHeight == 0) {
      return leftBlackHeight;
    }
    int rightBlackHeight = getHeight(root.right);
    if(rightBlackHeight == 0) {
      return rightBlackHeight;
    }
    if(leftBlackHeight != rightBlackHeight) {
      return 0;
    }
    else {
      return leftBlackHeight + (root.isBlack() ? 1 : 0);
    }
  }

  //method to swap
  private static void swap(int[] input, int a, int b) {
    int temp = input[a];
    input[a] = input[b];
    input[b] = temp;
  }

  //print array of nodes/inputs method
  private static RBT printArr(int[] input) {
    RBT bst = new RBT();
    for(int i = 0; i < input.length; i++) {
      bst.insert(input[i]);
    }
    return bst;
  }

  //method to actually search the tree
  private Node searchTreeHelper(Node node, int num) {
    if(node == NNULL || num == node.data) {
      return node;
    }
    if(num < node.data) {
      return searchTreeHelper(node.left, num);
    }
    return searchTreeHelper(node.right, num);
  }

  //method to update the node's color
  private void updateColor(Node n, String child) {
    //if curr node is left child, update left
    Node m = (child == "left" ? n.parent.parent.left : n.parent.parent.right);
    if(m.color == 1) {
      m.color = 0;
      n.parent.color = 0;
      n.parent.parent.color = 1;
      n = n.parent.parent;
    } else {
      //if curr node is left child, update left
      if(n == (child == "left" ? n.parent.left : n.parent.right)) {
        n = n.parent;
        if(child == "left") {
          turnRight(n);
        } else {
          turnLeft(n);
        }
      }
      n.parent.color = 0;
      n.parent.parent.color = 1;
      if(child == "left") {
        turnLeft(n.parent.parent);
      } else {
        turnRight(n.parent.parent);
      }
    }
  }

  //method to fix coloring of nodes
  private void fixColor(Node n) {
    while(n.parent.color == 1) {
      if(n.parent == n.parent.parent.right) {
        updateColor(n, "left");
      } else {
        updateColor(n, "right");
      }
      if(n == root) {
        break;
      }
    }
    root.color = 0;
  }

  public RBT() {
    NNULL = new Node(-1);
    NNULL.color = 0;
    NNULL.left = null;
    NNULL.right = null;
    root = NNULL;
  }

  //calls method to traverse tree
  public void preOrderTraverse() {
    preOrderTraversal(this.root);
  }
 
  //calls method to search tree
  public Node searchTree(int k) {
    return searchTreeHelper(this.root, k);
  }
  
  //left side of tree
  public void turnLeft(Node x) {
    Node y = x.right;
    x.right = y.left;
    if(y.left != NNULL) {   //if left side is not null
      y.left.parent = x;    //go down tree
    }
    y.parent = x.parent;
    if(x.parent == null) {
      this.root = y;
    } 
    else if(x == x.parent.left) {
      x.parent.left = y;
    } 
    else {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  //right side of tree
  public void turnRight(Node x) {
    Node y = x.left;
    x.left = y.right;
    if(y.right != NNULL) {    //if right side is not null
      y.right.parent = x;     //go down the tree
    }
    y.parent = x.parent;
    if(x.parent == null) {
      this.root = y;
    } 
    else if(x == x.parent.right) {
      x.parent.right = y;
    } 
    else {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }

  //method to insert nodes
  public void insert(int num) {
    Node node = new Node(num, null, NNULL, NNULL, 1);
    Node curr = null;
    Node par = this.root;
    while(par != NNULL) {
      curr = par;
      if(node.data < par.data) {
        par = par.left;
      } else {
        par = par.right;
      }
    }
    node.parent = curr;
    if(curr == null) {
      root = node;
    } else if(node.data < curr.data) {
      curr.left = node;
    } else {
      curr.right = node;
    }
    if(node.parent == null) {
      node.color = 0;
      return;
    }
    if(node.parent.parent == null) {
      return;
    }
    fixColor(node);
  }

  //method to interact with user
  public void interactRBT(int rb) {
    //getting four decimal places
    DecimalFormat newForm = new DecimalFormat("0.0000");
    newForm.setRoundingMode(RoundingMode.DOWN);
    int[] arr = new int[rb];
    //inserts the amount of "nodes" into array
    for(int j = 0; j < rb; j++) {
      arr[j] = j+1;
    }
    //find all possible combos
    RBTCombos(rb, arr);
    double heightBST = sumBST/count;
    String rbA = newForm.format(heightBST);
    System.out.print("The average height of a RBT with " + rb + " nodes is " + rbA);
    System.out.println();
  }

}
