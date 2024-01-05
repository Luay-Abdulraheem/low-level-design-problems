package design;


// Definition for a binary tree node.
public class BinaryTreeNode {
    int val;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    BinaryTreeNode() {
    }

    public BinaryTreeNode(int val) {
        this.val = val;
    }

    BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

