package com.orange.algorithm;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import com.orange.algorithm.DataStructure.*;

/**
 * created by czh on 2019/3/10
 */
public class v500 {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack=new Stack<Integer>();
        HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();

        int[]result=new int[nums1.length];
        for(int item:nums2){
            while(!stack.isEmpty() && stack.peek()<item){
                map.put(stack.pop(),item);
            }
            stack.push(item);
        }
        for(int i=0;i<nums1.length;i++){
            result[i]=map.getOrDefault(nums1[i],-1);
        }
        return result;
    }


    //455. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        int child=0;
        int cookie=0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (child<g.length && cookie<s.length){
            if (g[child]<=s[cookie]){
                child++;
            }
            cookie++;
        }
        return child;
    }

    //404. 左叶子之和
    public int sumOfLeftLeaves(TreeNode root) {
        return sumLeft(root,false);
    }

    public int sumLeft(TreeNode node,boolean isLeft) {
        if (node==null){
            return 0;
        }
        int result=0;
        if (node.left==null && node.right==null){
            if (isLeft){
                result+=node.val;
            }
        }
        if (node.left!=null){
            result+=sumLeft(node.left,true);
        }
        if (node.right!=null){
            result+=sumLeft(node.right,false);
        }
        return result;
    }

    //429. N叉树的层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result=new ArrayList<>();
        levelOrderHelper(result,root,1);
        return result;
    }

    public void levelOrderHelper(List<List<Integer>> list,Node node,int level){
        if (node==null){
            return;
        }
        if (list.size()<level){
            list.add(new ArrayList<Integer>());
        }
        list.get(level-1).add(node.val);
        for (Node temp:node.children){
            levelOrderHelper(list,temp,level+1);
        }
    }
}
