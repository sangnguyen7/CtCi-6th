package code.practice.ch4;

public class TreeNode{
    // Node's value
    int val;
    // the number of child nodes including itself
    int numOfChild;
    TreeNode left, right;
    public TreeNode(int v, int n){
        this.val = v;
        this.numOfChild = n;
    }

    public TreeNode(int v){
        this(v, 1);
    }

    public int getVal() {
        return val;
    }

    public int getNumOfChild() {
        return numOfChild;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }
}
