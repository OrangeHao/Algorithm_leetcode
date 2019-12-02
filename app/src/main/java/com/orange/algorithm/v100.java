package com.orange.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.orange.algorithm.DataStructure.TreeNode;
/**
 * created by czh on 2019/3/10
 */
public class v100 {

    /**
     * //53. 最大子序和Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * //
     * //Example:
     * //
     * //Input: [-2,1,-3,4,-1,2,1,-5,4],
     * //Output: 6
     * //Explanation: [4,-1,2,1] has the largest sum = 6.
     * //Follow up:
     * //
     * //If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0) {
                sum = sum + nums[i];
            } else {
                sum = nums[i];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //2. 两数相加
    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        int upNum=0;
        ListNode hold=head;
        while (l1!=null || l2!=null || upNum>0){
            int temp=upNum;
            if (l1!=null){
                temp+=l1.val;
                l1=l1.next;
            }
            if (l2!=null){
                temp+=l2.val;
                l2=l2.next;
            }
            head.next=new ListNode(temp%10);
            head=head.next;
            upNum=temp/10;
        }
        return hold.next;
    }

    //3. 无重复字符的最长子串

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */

    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        Set<Character> set=new HashSet<>();
        int ans=0,i=0,j=0;
        while(i<n && j<n){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j++;
                ans=Math.max(ans,j-i);
            }else{
                set.remove(s.charAt(i));
                i++;
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n=s.length();
        int ans=0;
        HashMap<Character,Integer> map=new HashMap<>();
        for (int i = 0,j=0; j < n; j++) {
            if (map.containsKey(s.charAt(j))){
                i=Math.max(map.get(s.charAt(j)),i);
            }
            ans=Math.max(ans,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return ans;
    }

    //58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        int result=0;
        for (int i = s.length()-1; i >0; i--) {
            if (s.charAt(i)==' '){
                if (result>0){
                    return result;
                }
                result=0;
            }else {
                result++;
            }
        }
        return result;
    }

    //66. 加一
    public int[] plusOne(int[] digits) {
        int len=digits.length;
        int upNum=0;
        for (int i = len-1; i >=0 ; i--) {
            int temp=digits[i]+upNum;
            upNum=0;
            if (i==len-1){
                temp+=1;
            }
            if (temp>=10){
                upNum=1;
                digits[i]=temp%10;
            }else {
                digits[i]=temp;
            }
            if (upNum==0){
                return digits;
            }else if (i==0){
                int[] res=new int[digits.length+1];
                res[0]=1;
                for (int j = 0; j < digits.length; j++) {
                    res[j+1]=digits[j];
                }
                return res;
            }
        }
        return digits;
    }

    //83. 删除排序链表中的重复元素

    public ListNode deleteDuplicates(ListNode head) {
        if (head==null || head.next==null){
            return head;
        }
        head.next=deleteDuplicates(head.next);
        if (head.val==head.next.val){
            head=head.next;
        }
        return head;
    }


    //78. 子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        res.add(new ArrayList<Integer>());
        for (int i = 0; i <nums.length; i++) {
            int size=res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        res.add(new ArrayList<Integer>());
        int size=nums.length;
        int total=2<<(size-1);
        for (int i = 0; i <total; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                int a=1<<j;
                if((a&i)==a){
                    temp.add(nums[j]);
                }
            }
            res.add(temp);
        }
        return res;
    }

    //102. 二叉树的层次遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int level=0;
        while (!queue.isEmpty()){
            result.add(new ArrayList<Integer>());
            int size=queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode  temp=queue.remove();
                result.get(level).add(temp.val);
                if (temp.left!=null){
                    queue.add(temp.left);
                }
                if (temp.right!=null){
                    queue.add(temp.right);
                }
            }
            level++;
        }
        return result;
    }

    //67
    public String addBinary(String a, String b) {
        StringBuilder sb=new StringBuilder();
        int ca=0;
        for (int i=a.length()-1, j=b.length()-1;i>=0 || j>=0;i--,j--){
            int sum=ca;
            sum+=i>=0?a.charAt(i)-'0':0;
            sum+=j>=0?b.charAt(j)-'0':0;
            ca=sum/2;
            sb.append(sum%2);
        }
        sb.append(ca==1?ca:"");
        return sb.reverse().toString();
    }


    //4. 寻找两个有序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length=(nums1.length+nums2.length);
        int mid=length/2+1;
        int i=0;
        int j=0;
        double result=0;
        while (mid>0){
            double temp=0;
           if (i<nums1.length && j<nums2.length){
               if (nums1[i]<nums2[j]){
                   temp=nums1[i];
                   i++;
               }else {
                   temp=nums2[j];
                   j++;
               }
           }else if (i>=nums1.length){
               temp=nums2[j];
               j++;
           }else if (j>=nums2.length){
               temp=nums1[i];
               i++;
           }
           if (mid==2 && length%2==0){
               result=result+temp;
           }
           if (mid==1){
               result+=temp;
           }
            mid--;
        }
        if (length%2==0){
            return result/2;
        }
        return result;
    }
}
