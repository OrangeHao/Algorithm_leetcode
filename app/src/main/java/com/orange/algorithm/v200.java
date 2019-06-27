package com.orange.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.orange.algorithm.DataStructure.ListNode;

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

    private class TreeResult {
        int depth;
        boolean isBTree;

        public TreeResult(int depth, boolean isBTree) {
            this.depth = depth;
            this.isBTree = isBTree;
        }
    }

    //110. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        return isBTree(root).isBTree;
    }

    private TreeResult isBTree(TreeNode node) {
        if (node == null) {
            return new TreeResult(0, true);
        }
        TreeResult left = isBTree(node.left);
        TreeResult right = isBTree(node.right);
        if (!left.isBTree || !right.isBTree || Math.abs(left.depth - right.depth) > 1) {
            return new TreeResult(0, false);
        }
        return new TreeResult(Math.max(left.depth, right.depth) + 1, true);
    }


    //141. 环形链表
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    //160. 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> setA = new HashSet<>();
        Set<ListNode> setB = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null) {
                setA.add(headA);
            }
            if (headB != null) {
                setB.add(headB);
            }
            if (headA != null && setB.contains(headA)) {
                return headA;
            }
            if (headB != null && setA.contains(headB)) {
                return headB;
            }
            if (headA != null && headB != null && headA == headB){
                return headA;
            }
            if (headA!=null){
                headA=headA.next;
            }
            if (headB!=null){
                headB=headB.next;
            }
        }
        return null;
    }

    //160. 相交链表
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        /**
         定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
         两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
         **/
        if(headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while(pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    //190. 颠倒二进制位
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result=0;
        for (int i = 0; i < 32; i++) {
            result<<=1;
            result=result+(n&1);
            n>>=1;
        }
        return result;
    }

    //169. 求众数
    //摩尔投票法
    public int majorityElement(int[] nums) {
        int ret=nums[0];
        int count=1;
        for (int temp:nums){
            if (temp==ret){
                count++;
            }else {
                count--;
                if (count==0){
                    ret=temp;
                    count=1;
                }
            }
        }
        return ret;
    }

    //136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int ret=0;
        for (int temp:nums){
            ret^=temp;
        }
        return ret;
    }

    //191. 位1的个数
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ret=0;
        while (n>0){
            ret=ret+(n&1);
            n>>=1;
        }
        return ret;
    }


    //二叉树的层次遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

    }

    //144. 二叉树的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        preorder(result,root);
        return result;
    }

    public void preorder(List<Integer> list,TreeNode node){
        if (node==null){
            return;
        }
        list.add(node.val);
        if (node.left!=null ){
            preorder(list,node.left);
        }
        if (node.right!=null){
            preorder(list,node.right);
        }
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        LinkedList<TreeNode> stack=new LinkedList<>();
        List<Integer> outPut=new ArrayList<>();
        if (root==null){
            return outPut;
        }
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node=stack.pollLast();
            outPut.add(node.val);
            if (node.right!=null){
                stack.add(node.right);
            }
            if (node.left!=null){
                stack.add(node.left);
            }
        }
        return outPut;
    }

    //94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> result=new ArrayList<>();
        TreeNode cur=root;
        while (cur!=null || !stack.isEmpty()){
            while (cur!=null){
                stack.add(cur);
                cur=cur.left;
            }
            cur=stack.pop();
            result.add(cur.val);
            cur=cur.right;
        }
        return result;
    }
}
