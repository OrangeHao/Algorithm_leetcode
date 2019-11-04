package com.orange.algorithm;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.orange.algorithm.DataStructure.*;


/**
 * created by czh on 2019/3/10
 */
public class v600 {

    List<Integer> result=new ArrayList<>();
    int cur=1;
    int max=0;
    TreeNode pre=null;
    //501. 二叉搜索树中的众数
    public int[] findMode(TreeNode root) {
        if (root==null){
            return new int[0];
        }
        inOrder(root);
        int[]ans=new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i]=result.get(i);
        }
        return ans;
    }

    private void inOrder(TreeNode node){
        if (node==null){
            return;
        }
        inOrder(node.left);
        if (pre!=null){
            if (node.val==pre.val){
                cur++;
            }else {
                cur=1;
            }
        }
        if (cur==max){
            result.add(node.val);
        }
        if (cur>max){
            result.clear();
            result.add(node.val);
            max=cur;
        }
        pre=node;
        inOrder(node.right);

    }


}
