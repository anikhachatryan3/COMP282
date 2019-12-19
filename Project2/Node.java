//Name: Ani Khachatryan
//Project: Project 2 - Height of Trees
//Class: COMP282
//File: Node.java

class Node {

    //creating the vars needed
    int data;
    Node left;
    Node right;
    Node parent;
    int color;
    int black_count;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.color = 1;
    }

    //setting all vars needed
    public Node(int data, Node parent, Node left, Node right, int color) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.color = color;
    }

    //method used to find if node is black
    public boolean isBlack() {
        return (this.color == 0);
    }

}
