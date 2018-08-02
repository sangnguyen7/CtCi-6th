package code.practice.ch4;

import java.util.LinkedList;
import java.util.Random;

public class RandomNode {
    private TreeNode ROOT;

    public TreeNode getRoot(){
        return ROOT;
    }
    public TreeNode getRandomNode(){
        if (ROOT == null) return null;

        int n = ROOT.numOfChild;
        int rand = (new Random()).nextInt(n)+1;

        return helper(ROOT, rand);
    }

    private TreeNode helper(TreeNode root, int rand){
        if (root == null) return null;
        int left = root.left != null? root.left.numOfChild : 0;
        int right = root.right != null? root.right.numOfChild : 0;
        if ((rand - left) == 1) return root;
        if (rand <= left)
            return helper(root.left, rand);
        return helper(root.right, rand-left+1);
    }

    public void insert(int val){
        if (ROOT == null){
            ROOT = new TreeNode(val);
            return;
        }

        insertHelper(ROOT, val);
    }

    private TreeNode insertHelper(TreeNode root, int val){
        if (root == null) {
            return new TreeNode(val);
        }
        root.numOfChild++;
        int left = root.left != null ? root.left.numOfChild : 0;
        int right = root.right != null? root.right.numOfChild : 0;
        if (left <= right){
            root.left = insertHelper(root.left, val);
        }
        else{
            root.right = insertHelper(root.right, val);
        }

        return root;
    }

    public TreeNode find(int val){
        if (ROOT == null) return null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(ROOT);
        while (!queue.isEmpty()){
            TreeNode p = queue.poll();
            if (p.val == val) return p;
            if (p.left != null){
                queue.add(p.left);
            }

            if (p.right != null){
                queue.add(p.right);
            }
        }

        return null;
    }

    public void delete(TreeNode node){
        TreeNode parent = findParentOf(ROOT, node);
        TreeNode replace = findFirstLeafNode(node);
        if (replace != null){
            replace.left = node.left;
            replace.right = node.right;
            replace.numOfChild = node.numOfChild-1;
        }

        if (parent != null){
            if (parent.left == node){
                parent.left = replace;
            }
            else{
                parent.right = replace;
            }
            node.left = null;
            node.right = null;
        }
        else{
            ROOT = replace;
        }
    }

    private TreeNode findParentOf(TreeNode root, TreeNode node){
        if (root == null || root == node) return null;
        if (root.left == node || root.right == node) return root;
        TreeNode left = findParentOf(root.left, node);
        if (left != null)
            return left;
        return findParentOf(root.right, node);
    }

    private TreeNode findFirstLeafNode(TreeNode root){
        if (root == null || (root.left == null) && (root.right == null))
            return root;
        root.numOfChild--;
        if (root.left != null){
            TreeNode left = findFirstLeafNode(root.left);
            if (left == root.left){
                root.left = null;
            }
            return left;
        }

        TreeNode right = findFirstLeafNode(root.right);
        if(right == root.right){
            root.right = null;
        }
        return right;
    }

    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}
