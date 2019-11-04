package com.orange.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.orange.algorithm.DataStructure.*;

/**
 * created by czh on 2019/3/10
 */
public class v300 {

    //234. 回文链表
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> mystack = new Stack<>();
        ListNode temp=head;
        while(temp!=null){
            mystack.push(temp.val);
            temp=temp.next;
        }
        while(head!=null){
            if(head.val!=mystack.pop()){
                return false;
            }
            head=head.next;
        }
        return true;
    }


    //242. 有效的字母异位词
    //给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。 示
    // 例 1: 示例 2: 说明: 你可以假设字符串只包含小写字母。
    // 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
    // 对字符串s，t排序，最后判断是否一一对应 用大小为26位的数组大小
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int []save=new int[26];
        for(int i=0;i<s.length();i++){
            save[s.charAt(i)-'a']++;
            save[t.charAt(i)-'a']--;
        }
        for(int temp:save){
            if(temp!=0){
                return false;
            }
        }
        return true;
    }

    //268. 缺失数字
    public int missingNumber(int[] nums) {
        int res=nums.length;
        for (int i = 0; i < nums.length; i++) {
            res^=nums[i];
            res^=i;
        }
        return res;
    }

    //257. 二叉树的所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result=new ArrayList<>();
        binaryTreePathsHelper(result,"",root);
        return result;
    }

    private void binaryTreePathsHelper(List<String> result,String path,TreeNode node){
        if (node==null){
            return;
        }
        path+=node.val;
        if (node.left==null&&node.right==null){
            result.add(path);
        }
        if (node.left!=null || node.right!=null){
            path+="->";
            binaryTreePathsHelper(result,path,node.left);
            binaryTreePathsHelper(result,path,node.right);
        }
    }

}
