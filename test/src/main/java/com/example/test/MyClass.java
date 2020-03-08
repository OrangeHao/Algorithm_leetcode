package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyClass {

    public static int titleToNumber(String s) {
        char[] list=s.toCharArray();
        int sum=0;
        for (int i = 0; i < list.length; i++) {
            int val=list[i]-64;
            sum+=val*Math.pow(26,list.length-1-i);
        }
        return sum;
    }

    public static int titleToNumber2(String s) {
        char[] list=s.toCharArray();
        int sum=0;
        for (int i = 0; i < list.length; i++) {
            sum=sum*26+(list[i]-64);
        }
        return sum;
    }

    public static int getValue(int n){
        int sum=0;
        while (n>0){
            sum+=Math.pow(n%10,2);
            n/=10;
        }
        return sum;
    }

    public static int threeSumClosest(int[] nums, int target) {
        int nearSum=nums[1]+nums[2]+nums[3];
        int len=nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {

            if (i>0 && nums[i]==nums[i-1]){
                continue;
            }

            int L=i+1;
            int R=len-1;
            while (L<R){
                int near=(nums[i]+nums[L]+nums[R]);
                System.out.println(nums[i]+"+"+nums[L]+"+"+nums[R]+"="+near);
                if (near-target==0){
                    return nums[i]+nums[L]+nums[R];
                }

                if (Math.abs(near-target)<Math.abs(nearSum-target)){
                    nearSum=near;
                }

//                while (L<R && nums[L]==nums[L+1]) L++;
//                while (L<R && nums[R]==nums[R-1]) R--;

                if ((near-target)<0){
                    L++;
                }else {
                    R--;
                }

            }
        }
        return nearSum;
    }


    private static void testInt(){
        int a=-2147483648;
        int b=Math.abs(a);
        long c=Math.abs((long)a);
        int d=-(1<<31);
        int e=(1<<31)-1;
        long f=1L<<32;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head==null){
            return head;
        }
        printListNode(head);

        int count=0;
        List<ListNode> nodes=new ArrayList<>();
        while (head.next!=null){
            if (count%k==0){
                nodes.add(head);
            }
            if (count%k==(k-1)){
                ListNode pre=head;
                head=head.next;
                pre.next=null;
            }else {
                head=head.next;
            }
            count++;
        }
        if (count%k==0){
            nodes.add(head);
        }
        boolean tailReverse=(count+1)%k==0;
        System.out.println(count+" cut:");
        for (ListNode temp :nodes){
            printListNode(temp);
        }

        List<ListNode> resultList=new ArrayList<>();
        List<ListNode> tailLis=new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            tailLis.add(nodes.get(i));

            if (!tailReverse && i==nodes.size()-1){
                resultList.add(nodes.get(i));
            }else {
                resultList.add(reversList(nodes.get(i)));
            }
        }
        System.out.println("tailLis:");
        for (ListNode temp :tailLis){
            printListNode(temp);
        }

        System.out.println("reverse:");
        for (ListNode temp :resultList){
            printListNode(temp);
        }

        for (int i = 0; i < tailLis.size()-1; i++) {
            tailLis.get(i).next=resultList.get(i+1);
        }
        System.out.println("result:");
        printListNode(resultList.get(0));
        return resultList.get(0);
    }

    public static ListNode reversList(ListNode node){
        if (node==null){
            return null;
        }
        ListNode cur=null;
        while (node.next!=null){
            ListNode temp=node.next;
            node.next=cur;
            cur=node;
            node=temp;
        }
        node.next=cur;
        return node;
    }

    private static ListNode getTestListNode(int count){
        ListNode head=new ListNode(1);
        ListNode cur=head;
        for (int i = 2; i < count+1; i++) {
            cur.next=new ListNode(i);
            cur=cur.next;
        }
        return head;
    }

    private static void printListNode(ListNode node){
        if (node==null){
            System.out.println("null");
        }
        while (node.next!=null){
            System.out.print(node.val);
            node=node.next;
        }
        System.out.print(node.val+"\n");
    }


    public static int longestValidParentheses(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValit(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }

    public static boolean isValit(String s){
        int left=0,right=0;
        for (int i = 0; i < s.length(); i++) {
            char cha=s.charAt(i);
            if (cha=='('){
                left++;
            }else if (cha==')'){
                right++;
            }
            if (right>left){
                return false;
            }
        }
        return left==right;
    }

    public static int jump(int[] nums) {
        if (nums.length==1){
            return 0;
        }
        int curIndex=0;
        int count=1;
        int targetIndex=nums.length-1;
        while (curIndex<=targetIndex){
            if (nums[curIndex]+curIndex>=targetIndex){
                return count;
            }
            int nextIndex=findMax(nums,curIndex);
            System.out.print("index:"+nextIndex+"\n");
            if (nextIndex==targetIndex){
                return count+1;
            }else {
                curIndex=nextIndex;
            }
            count++;
        }
        return count;
    }

    public static int findMax(int[] nums,int index){
        int maxIndex=index+1;
        int temp=0;
        for (int i = index+1; i <=nums[index]+index && i<nums.length; i++) {
            if (i+nums[i]>=nums.length-1){
                return nums.length-1;
            }
            if (i+nums[i]>temp){
                temp=nums[i]+i;
                maxIndex=i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args){
        String test="((()()(())))((()(())";
        int[] ts=new int[]{1,2,1,1,1};
        System.out.print(jump(ts));
    }
}
