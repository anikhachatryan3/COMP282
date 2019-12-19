//Name: Ani Khachatryan
//Project: Project 2 - Height of Trees
//Class: COMP282
//File: Node.java

class Node {

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

    public boolean isBlack() {
        return (this.color == 0);
    }

    // public static void isBlack(rbnode root) {
    //     if (root == null)
    //         return; 

    //     if (root.color == 0) {
    //         root.black_count = root.parent.black_count+1;

    //     } else {
    //         root.black_count = root.parent.black_count;
    //     }
    //     if ((root.left == null) && (root.right == null)) {              
    //         bh = root.black_count;              
    //     }               
    //     isBlack(root.left);
    //     isBlack(root.right);
    // } 

}
