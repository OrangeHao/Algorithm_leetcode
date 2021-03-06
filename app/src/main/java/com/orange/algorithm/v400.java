package com.orange.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * created by czh on 2019/3/10
 */
public class v400 {

    //350. 两个数组的交集 II
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])){
                map.put(nums1[i],map.get(nums1[i])+1);
            }else {
                map.put(nums1[i],1);
            }
        }
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])&& map.get(nums2[i])>0){
                list.add(nums2[i]);
                map.put(nums2[i],map.get(nums2[i])-1);
            }
        }
        int result[]=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i]=list.get(i);
        }
        return result;
    }

    //350. 两个数组的交集 II(元素唯一)
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        Set<Integer> set2=new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])){
                set2.add(nums2[i]);
            }
        }
        int result[]=new int[set2.size()];
        Iterator<Integer> in=set2.iterator();
        int i=0;
        while (in.hasNext()){
            result[i]=in.next();
            i++;
        }
        return result;
    }

    //389. 找不同
    public char findTheDifference(String s, String t) {
        int sum=0;
        for (int i = 0; i < s.length(); i++) {
            sum+=t.charAt(i);
            sum-=s.charAt(i);
        }
        sum+=t.charAt(t.length()-1);
        return (char) sum;
    }

    //371. 两整数之和
    public int getSum(int a, int b) {
        int sum, carry;
        sum = a ^ b;
        carry = (a & b) << 1;
        if(carry != 0)
        {
            return getSum(sum, carry);
        }
        return sum;
    }

    public boolean isPowerOfFour(int num) {
        return num>0 && (num&-num)==num && (num&(0x55555555))==num;
    }
}
