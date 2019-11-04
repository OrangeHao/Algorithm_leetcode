package com.orange.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.orange.algorithm.DataStructure.*;
/**
 * created by czh on 2019/3/10
 */
public class v200 {
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

    //107. 二叉树的层次遍历 II
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        List<List<Integer>> result=new LinkedList<>();
        if (root==null){
            return result;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            result.add(0,new ArrayList<Integer>());
            int size=queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp=queue.poll();
                result.get(0).add(temp.val);
                if (temp.left!=null){
                    queue.add(temp.left);
                }
                if (temp.right!=null){
                    queue.add(temp.right);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        helper(result,root,0);
        Collections.reverse(result);
        return result;
    }

    public void helper(List<List<Integer>> list, TreeNode node, int level){
        if (node==null){
            return;
        }
        if (list.size()==level){
            list.add(new ArrayList<Integer>());
        }
        list.get(level).add(node.val);
        if (node.left!=null){
            helper(list,node.left,level+1);
        }
        if (node.right!=null){
            helper(list,node.right,level+1);
        }
    }


    //111. 二叉树的最小深度
    public int minDepth(TreeNode root) {
        if (root==null){
            return 0;
        }

        if (root.left==null && root.right==null){
            return 1;
        }

        int mindep=Integer.MAX_VALUE;
        if (root.left!=null){
            mindep=Math.min(minDepth(root.left),mindep);
        }
        if (root.right!=null){
            mindep=Math.min(minDepth(root.right),mindep);
        }
        return mindep+1;

    }

    //112. 路径总和
    public boolean hasPathSum(TreeNode root, int sum) {
        return sum(root,sum,0);
    }

    private boolean sum(TreeNode node,int sum,int cur){
        if (node==null){
            return false;
        }
        cur=cur+node.val;

        if (cur==sum && node.left==null && node.right==null){
            return true;
        }

        boolean left=false;
        if (node.left!=null){
            left=sum(node.left,sum,cur);
        }
        boolean right=false;
        if (node.right!=null){
            right=sum(node.right,sum,cur);
        }
        return left || right;
    }

    //113. 路径总和 II
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res=new ArrayList<>();
        pathSumhelper(root,res,sum,new ArrayList<Integer>());
        return res;
    }

    private void pathSumhelper(TreeNode node,List<List<Integer>> res,int sum,ArrayList<Integer> list){
        if (node==null){
            return;
        }
        list.add(node.val);
        sum=sum-node.val;
        if (sum==0&&node.left==null&&node.right==null){
            res.add(new ArrayList<Integer>(list));
        }
        pathSumhelper(node.left,res,sum,list);
        pathSumhelper(node.right,res,sum,list);
        list.remove(list.size()-1);
    }

    //125. 验证回文串
    public boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<j){
            while(!isChar(s.charAt(i)) && i<j){
                i++;
            }
            while (!isChar(s.charAt(j)) && i<j){
                j--;
            }
            char ic=s.charAt(i);
            char jc=s.charAt(j);
            if (ic>='A' && ic<='Z'){
                ic=(char) (ic+32);
            }
            if (jc>='A' && jc<='Z'){
                jc=(char) (jc+32);
            }
            if (ic!=jc){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isChar(char item){
        if((item>='0' && item<='9')||(item>='a' && item<='z')){
            return true;
        }
        return false;
    }

    //121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int result=0;
        for (int i = 0; i < prices.length; i++) {
            int cur=prices[i];
            for (int j = i+1; j < prices.length; j++) {
                if (prices[j]>cur){
                    result=Math.max(result,prices[j]-cur);
                }
            }
        }
        return result;
    }
    public int maxProfit2(int[] prices) {
        int low=Integer.MAX_VALUE;
        int hight=0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i]<low){
                low=prices[i];
            }else if (prices[i]-low>hight){
                hight=prices[i]-low;
            }
        }
        return hight;
    }

    //167. 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int left=target-numbers[i];
            for (int j = i+1; j < numbers.length; j++) {
                if (numbers[j]==left){
                    return new int[]{i+1,j+1};
                }
                if (numbers[j]>left){
                    break;
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSum2(int[] numbers, int target) {
        int i=0;
        int j=numbers.length-1;
        while (i<j){
            int sum=numbers[i]+numbers[j];
            if (target==sum){
                return new int[]{i+1,j+1};
            }
            if (sum>target){
                j--;
            }
            if (sum<target){
                i++;
            }
        }
        return new int[]{};
    }

    //168. Excel表列名称
    public String convertToTitle(int n) {
        StringBuilder sb=new StringBuilder();
        while (n!=0){
            sb.append((char)(n%26+64));
            n=n/26;
        }
        sb.reverse();
        return sb.toString();
    }

    //171. Excel表列序号
    //  A -> 1
    //    B -> 2
    //    C -> 3
    //    ...
    //    Z -> 26
    //    AA -> 27
    //    AB -> 28
    public int titleToNumber(String s) {
        char[] list=s.toCharArray();
        int sum=0;
        for (int i = 0; i < list.length; i++) {
            int val=list[i]-64;
            sum+=val*Math.pow(26,list.length-1-i);
        }
        return sum;
    }
    public int titleToNumber2(String s) {
        char[] list=s.toCharArray();
        int sum=0;
        for (int i = 0; i < list.length; i++) {
            sum=sum*26+(list[i]-64);
        }
        return sum;
    }


    public int rob(int[] nums) {
        int premax=0;
        int curmax=0;
        for (int x:nums){
            int temp=curmax;
            curmax=Math.max(premax+x,curmax);
            premax=temp;
        }
        return curmax;
    }


    //202. 快乐数
    public boolean isHappy(int n) {
        int count=2000;
        while (count>0){
            n=getValue(n);
            if (n==1){
                return true;
            }
            count--;
        }
        return false;
    }

    public int getValue(int n){
        int sum=0;
        while (n>0){
            sum+=Math.pow(n%10,2);
            n/=10;
        }
        return sum;
    }

    //278. 第一个错误的版本
    public int firstBadVersion(int n) {

    }
}
