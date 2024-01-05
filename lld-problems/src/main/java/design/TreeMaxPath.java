package design;

public class TreeMaxPath {

    public int getMaxSumBT(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        }
        /*case 1: If the node has no children, then the only path is the singleton path*/
        if (root.left == null && root.right == null)
            return root.val;

        /* case 2: If the node has only one child, then all paths go through that child. */
        if (root.right == null)
            return root.val + getMaxSumBT(root.left);
        else if (root.left == null)
            return root.val + getMaxSumBT(root.right);

        /* case 3: Otherwise, the node has two children. Then, all paths go through one of its two children. */
        return Math.max(root.val + getMaxSumBT(root.left), root.val + getMaxSumBT(root.right));
    }
}
