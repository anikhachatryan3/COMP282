//Name: Ani nhachatryan
//Project: Project 2 - Height of Trees
//Class: COMP282
//File: RBT.java

import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RBT {

    public Node root;
    public Node NNULL;
    public static double sumBST = 0;
    public static double count = 0;
    
    private void preOrderTraversal(Node node) {
      if(node != NNULL) {
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
      }
    }

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
    int tmp = input[a];
    input[a] = input[b];
    input[b] = tmp;
  }

  //print array of nodes/inputs method
  private static RBT printArr(int[] input) {
    RBT bst = new RBT();
    for(int i = 0; i < input.length; i++) {
      bst.insert(input[i]);
    }
    return bst;
  }

  private Node searchTreeHelper(Node node, int key) {
    if(node == NNULL || key == node.data) {
      return node;
    }
    if(key < node.data) {
      return searchTreeHelper(node.left, key);
    }
    return searchTreeHelper(node.right, key);
  }

  private void updateColor(Node n, String child) {
    Node m = (child == "left" ? n.parent.parent.left : n.parent.parent.right);
    if(m.color == 1) {
      m.color = 0;
      n.parent.color = 0;
      n.parent.parent.color = 1;
      n = n.parent.parent;
    } else {
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

  public void preOrderTraverse() {
    preOrderTraversal(this.root);
  }
 
  public Node searchTree(int k) {
    return searchTreeHelper(this.root, k);
  }
    
  public void turnLeft(Node x) {
    Node y = x.right;
    x.right = y.left;
    if(y.left != NNULL) {
      y.left.parent = x;
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

  public void turnRight(Node x) {
    Node y = x.left;
    x.left = y.right;
    if(y.right != NNULL) {
      y.right.parent = x;
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

    public void insert(int key) {
      Node node = new Node(key, null, NNULL, NNULL, 1);
      Node y = null;
      Node x = this.root;
      while(x != NNULL) {
        y = x;
        if(node.data < x.data) {
          x = x.left;
        } else {
          x = x.right;
        }
      }
      node.parent = y;
      if(y == null) {
        root = node;
      } else if(node.data < y.data) {
        y.left = node;
      } else {
        y.right = node;
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

    public void interactRBT(int rb) {
        //getting four decimal places
        DecimalFormat newForm = new DecimalFormat("0.0000");
        newForm.setRoundingMode(RoundingMode.DOWN);
        int[] arr = new int[rb];
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
