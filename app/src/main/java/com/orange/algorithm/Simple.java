package com.orange.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * created by czh on 2019/2/19
 */
public class Simple {

    //28 implement strStr()
    public int strStr(String ts, String ps) {

        if (ps.length() == 0) {
            return 0;
        }
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();
        int i = 0; // 主串的位置
        int j = 0; // 模式串的位置
        int[] next = getNext(ps);
        while (i < t.length && j < p.length) {
            if (j == -1 || t[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == p.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }


    //N_queen

    public List<List<String>> solveNQueens(int n) {
        int[] des = new int[n];
        for (int i = 0; i < des.length; i++) {
            des[i] = -1;
        }
        List<List<String>> res = new ArrayList<>();
        findPlace(n, 0, des, res);
        return res;
    }

    private void findPlace(int n, int row, int[] des, List<List<String>> res) {
        if (row == n) {
            List<String> out = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (des[i] == j) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                out.add(sb.toString());
            }
            res.add(out);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValidPlace(des, row, i)) {
                des[row] = i;
                findPlace(n, row + 1, des, res);
            }
        }
    }

    private boolean isValidPlace(int[] des, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (des[i] == col || row - i == Math.abs(col - des[i])) {
                return false;
            }
        }
        return true;
    }


    //682. Baseball Game
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if (op.equals("+")) {
                int top = stack.pop();
                int newtop = top + stack.peek();
                stack.push(top);
                stack.push(newtop);
            } else if (op.equals("C")) {
                stack.pop();
            } else if (op.equals("D")) {
                stack.push(stack.peek() * 2);
            } else {
                stack.push(Integer.valueOf(op));
            }
        }
        int ans = 0;
        for (Integer value : stack) {
            ans += value;
        }
        return ans;
    }


    //155. Min Stack  -2 0 -3
    class MinStack {

        Stack<Integer> stack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(x);
                stack.push(x);
            } else {
                int temp = stack.peek();
                stack.push(x);
                if (temp < x) {
                    stack.push(temp);
                } else {
                    stack.push(x);
                }
            }
        }

        public void pop() {
            stack.pop();
            stack.pop();
        }

        public int top() {
            return stack.get(stack.size() - 2);
        }

        public int getMin() {
            return stack.peek();
        }
    }

    class MinStackBest {
        int[] nums;
        int[] minNum;
        int elems;
        int minElems;

        int stackMax = 1000;

        /**
         * initialize your data structure here.
         */
        public MinStackBest() {
            nums = new int[stackMax];
            minNum = new int[stackMax];
            elems = 0;
            minElems = 0;
        }

        public void push(int x) {
            // 扩容
            if (elems == stackMax) {
                nums = new int[stackMax * 2];
                minNum = new int[stackMax * 2];
                stackMax *= 2;
            }
            // 元素放入栈顶
            nums[elems++] = x;
            // 添加最小值到最小值数组中
            if (minElems == 0 || x <= minNum[minElems - 1]) {
                minNum[minElems++] = x;
            }
        }

        public void pop() {
            if (minNum[minElems - 1] == nums[--elems]) {
                --minElems;
            }
        }

        public int top() {
            return nums[elems - 1];
        }

        public int getMin() {
            return minNum[minElems - 1];
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */


    //225. Implement Stack using Queues
    class MyStack {

        Queue<Integer> queue;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            queue=new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            int size=queue.size();
            queue.offer(x);
            for(int i=0;i<size;i++)//换序列
            {
                int temp=queue.poll();
                queue.offer(temp);

            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return queue.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return queue.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */


    //232. Implement Queue using Stacks

    class MyQueue {

        Stack<Integer> inStack=new Stack<>();
        Stack<Integer> saveStack=new Stack<>();

        /** Initialize your data structure here. */
        public MyQueue() {

        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            while (!saveStack.isEmpty()){
                inStack.push(saveStack.pop());
            }
            inStack.push(x);
            while (!inStack.isEmpty()){
                saveStack.push(inStack.pop());
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return saveStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            return saveStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return saveStack.isEmpty();
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */

    //844. Backspace String Compare
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s=new Stack<>();
        Stack<Character> t=new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i)=='#'){
                if (!s.isEmpty()){
                    s.pop();
                }
            }else {
                s.push(S.charAt(i));
            }
        }

        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i)=='#'){
                if (!t.isEmpty()){
                    t.pop();
                }
            }else {
                t.push(T.charAt(i));
            }
        }

        return s.equals(t);
    }


    //496. Next Greater Element I
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            int temp=nums1[i];
            int result=-1;
            for (int j = i+1; j <nums2.length ; j++) {
                if (temp<nums2[j]){
                    result=nums2[j];
                    break;
                }
            }
            nums1[i]=result;
        }
        return nums1;
    }







}
