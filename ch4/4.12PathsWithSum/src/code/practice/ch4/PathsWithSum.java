package code.practice.ch4;

import java.util.LinkedList;

/*
* Paths with Sum: You are given a binary tree in which each node contains and integer value
* (which might be positive or negative). Design an algorithm to count the number of
* paths that sum to a given value. The path does not need to start or end at the root or a leaf,
* but it must go downwards (Traveling only form parent nodes to child nodes)
*/

public class PathsWithSum {
    public static int countPathsWithSum(TreeNode root, int k){
        if (root == null) return 0;

        int result = root.val == k? 1:0;
        result += countPathsWithSum(root.left, k) +
                  countPathsWithSum(root.left, k - root.val) +
                  countPathsWithSum(root.right, k) +
                  countPathsWithSum(root.right, k - root.val);
        return result;
    }

    public static int countPathsWithSumBFS(TreeNode root, int k){
        if (root == null) return 0;
        LinkedList<Object[]> queue = new LinkedList<>();
        queue.add(new Object[]{root, k});
        int result = 0;
        while(!queue.isEmpty()){
            Object[] o = queue.poll();
            TreeNode node = (TreeNode) o[0];
            Integer v = (Integer) o[1];
            result += node.val == v? 1: 0;
            queue.add(new Object[]{root.left, v});
            queue.add(new Object[]{root.left, v-node.val});
            queue.add(new Object[]{root.right, v});
            queue.add(new Object[]{root.right, v-node.val});
        }

        return result;
    }
}
