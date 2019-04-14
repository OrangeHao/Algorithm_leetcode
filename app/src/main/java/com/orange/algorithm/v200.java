package com.orange.algorithm;

/**
 * created by czh on 2019/3/10
 */
public class v200 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private class TreeResult{
        int depth;
        boolean isBTree;
        public TreeResult(int depth,boolean isBTree){
            this.depth=depth;
            this.isBTree=isBTree;
        }
    }

    //110. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        return isBTree(root).isBTree;
    }

    private TreeResult isBTree(TreeNode node){
        if (node==null){
            return new TreeResult(0,true);
        }
        TreeResult left=isBTree(node.left);
        TreeResult right=isBTree(node.right);
        if (!left.isBTree || !right.isBTree || Math.abs(left.depth-right.depth)>1){
            return new TreeResult(0,false);
        }
        return new TreeResult(Math.max(left.depth,right.depth)+1,true);
    }
}
