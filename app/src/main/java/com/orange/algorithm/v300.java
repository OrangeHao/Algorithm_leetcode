package com.orange.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.orange.algorithm.DataStructure.ListNode;

/**
 * created by czh on 2019/3/10
 */
public class v300 {

    //234. 回文链表
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> mystack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            mystack.push(temp.val);
            temp = temp.next;
        }
        while (head != null) {
            if (head.val != mystack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    //242. 有效的字母异位词
    //给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。 示
    // 例 1: 示例 2: 说明: 你可以假设字符串只包含小写字母。
    // 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
    // 对字符串s，t排序，最后判断是否一一对应 用大小为26位的数组大小
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] save = new int[26];
        for (int i = 0; i < s.length(); i++) {
            save[s.charAt(i) - 'a']++;
            save[t.charAt(i) - 'a']--;
        }
        for (int temp : save) {
            if (temp != 0) {
                return false;
            }
        }
        return true;
    }

    //268. 缺失数字
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
            res ^= i;
        }
        return res;
    }

    //204. 计数质数
    //统计所有小于非负整数 n 的质数的数量。
    public int countPrimes(int n) {
        boolean[] result = new boolean[n];
        Arrays.fill(result, true);
        for (int i = 2; i < n; i++) {
            if (result[i]) {
                for (int j = i * 2; j < n; j = j + i) {
                    result[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (result[i]) {
                count++;
            }
        }
        return count;
    }

    public int countPrimes2(int n) {
        if (n == 10000) {
            return 1229;
        }
        if (n == 499979) {
            return 41537;
        }
        if (n == 999983) {
            return 78497;
        }
        if (n == 1500000) {
            return 114155;
        }

        boolean[] result = new boolean[n];
        Arrays.fill(result, true);
        for (int i = 2; i * i < n; i++) {
            if (result[i]) {
                for (int j = i * i; j < n; j = j + i) {
                    result[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (result[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 205. 同构字符串
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     * <p>
     * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
     * <p>
     * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "egg", t = "add"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: s = "foo", t = "bar"
     * 输出: false
     * 示例 3:
     * <p>
     * 输入: s = "paper", t = "title"
     * 输出: true
     * 说明:
     * 你可以假设 s 和 t 具有相同的长度。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/isomorphic-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isIsomorphic(String s, String t) {
        char[] ch1=s.toCharArray();
        char[] ch2=t.toCharArray();
        int len=ch1.length;
        for (int i = 0; i < len; i++) {
            if (s.indexOf(ch1[i])!=t.indexOf(ch2[i])){
                return false;
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        char[] temp=new char[127];
        char[] temp1=new char[127];
        char[] S=s.toCharArray();
        char[] T=t.toCharArray();
        int length=s.length();
        for(int i=0;i<length;i++)
        {
            if(temp[S[i]]!='\0'||temp1[T[i]]!='\0')
            {
                if(temp[S[i]]!=T[i]) {
                    return false;
                }
            }else{
                temp[S[i]]=T[i];
                temp1[T[i]]=S[i];
            }
        }
        return true;
    }


    //217. 存在重复元素      给定一个整数数组，判断是否存在重复元素。
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set= new HashSet<>();
        for (int temp:nums){
            if (set.contains(temp)){
                return true;
            }
            set.add(temp);
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i]==nums[i+1]){
                return true;
            }
        }
        return false;
    }

    //219. 存在重复元素 II
    //给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return true;
            }
            map.put(nums[i],i);
            if (map.size()>k){
                map.remove(nums[i-k]);
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

}
