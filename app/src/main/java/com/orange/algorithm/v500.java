package com.orange.algorithm;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

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
}
