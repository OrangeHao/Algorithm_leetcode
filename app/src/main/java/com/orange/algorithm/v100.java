package com.orange.algorithm;

import android.net.rtp.RtpStream;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        int upNum = 0;
        ListNode hold = head;
        while (l1 != null || l2 != null || upNum > 0) {
            int temp = upNum;
            if (l1 != null) {
                temp += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                temp += l2.val;
                l2 = l2.next;
            }
            head.next = new ListNode(temp % 10);
            head = head.next;
            upNum = temp / 10;
        }
        return hold.next;
    }

    //3. 无重复字符的最长子串

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    //58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        int result = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) == ' ') {
                if (result > 0) {
                    return result;
                }
                result = 0;
            } else {
                result++;
            }
        }
        return result;
    }

    //66. 加一
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int upNum = 0;
        for (int i = len - 1; i >= 0; i--) {
            int temp = digits[i] + upNum;
            upNum = 0;
            if (i == len - 1) {
                temp += 1;
            }
            if (temp >= 10) {
                upNum = 1;
                digits[i] = temp % 10;
            } else {
                digits[i] = temp;
            }
            if (upNum == 0) {
                return digits;
            } else if (i == 0) {
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                for (int j = 0; j < digits.length; j++) {
                    res[j + 1] = digits[j];
                }
                return res;
            }
        }
        return digits;
    }

    //83. 删除排序链表中的重复元素

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates(head.next);
        if (head.val == head.next.val) {
            head = head.next;
        }
        return head;
    }


    //78. 子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        int size = nums.length;
        int total = 2 << (size - 1);
        for (int i = 0; i < total; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                int a = 1 << j;
                if ((a & i) == a) {
                    temp.add(nums[j]);
                }
            }
            res.add(temp);
        }
        return res;
    }

    //102. 二叉树的层次遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            result.add(new ArrayList<Integer>());
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.remove();
                result.get(level).add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            level++;
        }
        return result;
    }

    //67
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ca = sum / 2;
            sb.append(sum % 2);
        }
        sb.append(ca == 1 ? ca : "");
        return sb.reverse().toString();
    }


    //4. 寻找两个有序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = (nums1.length + nums2.length);
        int mid = length / 2 + 1;
        int i = 0;
        int j = 0;
        double result = 0;
        while (mid > 0) {
            double temp = 0;
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    temp = nums1[i];
                    i++;
                } else {
                    temp = nums2[j];
                    j++;
                }
            } else if (i >= nums1.length) {
                temp = nums2[j];
                j++;
            } else if (j >= nums2.length) {
                temp = nums1[i];
                i++;
            }
            if (mid == 2 && length % 2 == 0) {
                result = result + temp;
            }
            if (mid == 1) {
                result += temp;
            }
            mid--;
        }
        if (length % 2 == 0) {
            return result / 2;
        }
        return result;
    }


    //5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expendLength(s, i, i);
            int len2 = expendLength(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end);
    }

    private int expendLength(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    private String preProgress(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        String res = "^";
        for (int i = 0; i < n; i++) {
            res = res + "#" + s.charAt(i);
        }
        res = res + "#$";
        return res;
    }

    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        String after = preProgress(s);
        int len = after.length();
        int p[] = new int[len];
        int max = 0;
        int id = 0;
        for (int i = 0; i < len; i++) {
            if (i < max) {
                p[i] = Math.min(p[2 * id - i], max - i);
            } else {
                p[i] = 1;
            }

            while (i - p[i] >= 0 && i + p[i] < len && after.charAt(i - p[i]) == after.charAt(i + p[i])) {
                p[i]++;
            }

            if (i + p[i] > max) {
                id = i;
                max = i + p[i];
            }
        }

        int maxLength = 0;
        int maxIndex = 0;
        for (int i = 0; i < len; i++) {
            if (p[i] > maxLength) {
                maxLength = p[i] - 1;
                maxIndex = i;
            }
        }

        int start = (maxIndex - maxLength) / 2;
        return s.substring(start, start + maxLength);
    }


    //6. Z 字形变换
    public String convert(String s, int numRows) {
        if (s.length() < numRows) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goDown = false;

        for (char item : s.toCharArray()) {
            rows.get(curRow).append(item);
            if (curRow == 0 || curRow == (numRows - 1)) {
                goDown = !goDown;
            }
            curRow = curRow + (goDown ? 1 : -1);
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder item : rows) {
            sb.append(item);
        }
        return sb.toString();
    }

    //11. 盛最多水的容器

    public int maxArea2(int[] height) {
        if (height.length <= 1) {
            return -1;
        }
        int i = 0, j = height.length - 1, max = 0;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            max = Math.max(max, h * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    //15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++;
                    while (L < R && nums[R] == nums[R - 1]) R--;
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return res;
    }


    //16. 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {
        int nearSum = nums[1] + nums[2] + nums[3];
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int near = (nums[i] + nums[L] + nums[R]);
                if (near == 0) {
                    return nums[i] + nums[L] + nums[R];
                }

                if (Math.abs(near - target) < Math.abs(nearSum - target)) {
                    nearSum = near;
                }

                while (L < R && nums[L] == nums[L + 1]) L++;
                while (L < R && nums[R] == nums[R - 1]) R--;

                if ((near - target) < 0) {
                    L++;
                } else {
                    R--;
                }

            }
        }
        return nearSum;
    }


    //17. 电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        String[] letter_map = {
                "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        List<String> res = new ArrayList<>();
        res.add("");

        for (int i = 0; i < digits.length(); i++) {
            String letters = letter_map[digits.charAt(i) - '2'];
            int size = res.size();
            for (int j = 0; j < size; j++) {
                String item = res.remove(0);
                for (int k = 0; k < letters.length(); k++) {
                    res.add(item + letters.charAt(k));
                }
            }
        }

        return res;
    }

    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        iterStr(digits, "", 0);
        return res;
    }

    private List<String> res = new ArrayList<>();
    String[] letter_map = {
            "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    private void iterStr(String digits, String letters, int index) {
        if (index == digits.length()) {
            res.add(letters);
            return;
        }

        char c = digits.charAt(index);
        String numberLetters = letter_map[c - '2'];
        for (int i = 0; i < numberLetters.length(); i++) {
            iterStr(digits, letters + numberLetters.charAt(i), index + 1);
        }
    }


    //18. 四数之和
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 4) {
            return res;
        }
        Arrays.sort(nums);

        int length = nums.length;
        for (int j = 0; j < length - 3; j++) {

            if (j > 0 && nums[j] == nums[j - 1]) {
                continue;
            }

            int min = nums[j] + nums[j + 1] + nums[j + 2] + nums[j + 3];
            if (min > target) {
                break;
            }

            int max = nums[j] + nums[length - 1] + nums[length - 3] + nums[length - 2];
            if (max < target) {
                continue;
            }

            int newTarget = target - nums[j];

            for (int i = j + 1; i < len; i++) {

                if (i > j + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int L = i + 1;
                int R = len - 1;

                int min1 = nums[j] + nums[i] + nums[L] + nums[L + 1];
                if (min1 > target) {
                    break;
                }

                int max1 = nums[j] + nums[i] + nums[R] + nums[R - 1];
                if (max1 < target) {
                    continue;
                }


                while (L < R) {
                    int sum = nums[i] + nums[L] + nums[R];
                    if (sum == newTarget) {
                        res.add(Arrays.asList(nums[j], nums[i], nums[L], nums[R]));
                        while (L < R && nums[L] == nums[L + 1]) L++;
                        while (L < R && nums[R] == nums[R - 1]) R--;
                        L++;
                        R--;
                    } else if (sum < newTarget) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }

        return res;
    }


    //22. 括号生成

    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     * <p>
     * 例如，给出 n = 3，生成结果为：
     * <p>
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //思路：保证目前的左括号数大于或等于右括号数即可
    //只有在当前序列中的左括号数大于右括号数的时候加上右括号才能形成有效序列
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, "", 0, 0, n);
        return res;
    }

    private void generate(List<String> res, String ans, int countLeft, int countRight, int n) {

        if (countLeft > n || countRight > n) {
            return;
        }

        if (countLeft == n && countRight == n) {
            res.add(ans);
        }

        if (countLeft < n) {
            generate(res, ans + "(", countLeft + 1, countRight, n);
        }

        if (countLeft > countRight) {
            generate(res, ans + ")", countLeft, countRight + 1, n);
        }
//
//        if (countLeft>=countRight){
//            generate(res,ans+"(",countLeft+1,countRight,n);
//            generate(res,ans+")",countLeft,countRight+1,n);
//        }
    }


    //23. 合并K个排序链表

    //1.优先队列发，把所有链表的头部放在优先级队列中，每次取队列的最小值，取完添加这个点的next到队列中
    //直至取完所有的点
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        ListNode head = new ListNode(0);
        ListNode cur = head;

        for (ListNode item : lists) {
            if (item == null) {
                continue;
            }
            queue.add(item);
        }

        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) {
                queue.add(cur.next);
            }
        }

        return head.next;
    }


    //2.分治法，两两链表合并，知道所有合并完
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null) return null;
        int length = lists.length;
        if (length == 0) return null;

        while (length > 1) {
            for (int i = 0; i < length / 2; i++) {
                lists[i] = mergeTwoList(lists[i], lists[length - 1 - i]);
            }

            length = (length + 1) / 2;
        }
        return lists[0];
    }

    private ListNode mergeTwoList(ListNode one, ListNode two) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (one != null && two != null) {
            if (one.val < two.val) {
                cur.next = one;
                one = one.next;
            } else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }

        if (one != null) {
            cur.next = one;
        }
        if (two != null) {
            cur.next = two;
        }
        return head.next;
    }

    private ListNode mergeTwoList1(ListNode one, ListNode two) {
        if (one == null) return two;
        if (two == null) return one;

        if (one.val < two.val) {
            one.next = mergeTwoList1(one.next, two);
            return one;
        } else {
            two.next = mergeTwoList1(one, two.next);
            return two;
        }
    }


    //29. 两数相除
    //给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
    //
    //返回被除数 dividend 除以除数 divisor 得到的商。
    //
    //示例 1:
    //
    //输入: dividend = 10, divisor = 3
    //输出: 3

    //要点：1，判断商的正负，2.怎么提高效率
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        boolean negative = (dividend ^ divisor) < 0;

        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            if ((t >> i) >= d) {
                t = t - (d << i);
                result = result + (1 << i);
            }
        }
        return negative ? -result : result;
    }

    //25. K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int count = 0;
        List<ListNode> nodes = new ArrayList<>();
        while (head.next != null) {
            if (count % k == 0) {
                nodes.add(head);
            }
            if (count % k == (k - 1)) {
                ListNode pre = head;
                head = head.next;
                pre.next = null;
            } else {
                head = head.next;
            }
            count++;
        }
        if (count % k == 0) {
            nodes.add(head);
        }
        boolean tailReverse = (count + 1) % k == 0;
        List<ListNode> resultList = new ArrayList<>();
        List<ListNode> tailLis = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            tailLis.add(nodes.get(i));
            if (!tailReverse && i == nodes.size() - 1) {
                resultList.add(nodes.get(i));
            } else {
                resultList.add(reversList(nodes.get(i)));
            }
        }
        for (int i = 0; i < tailLis.size() - 1; i++) {
            tailLis.get(i).next = resultList.get(i + 1);
        }
        return resultList.get(0);
    }

    public ListNode reversList(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode cur = null;
        while (node.next != null) {
            ListNode temp = node.next;
            node.next = cur;
            cur = node;
            node = temp;
        }
        node.next = cur;
        return node;
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) {
            curr = curr.next;
            count++;
        }
        if (count == k) {
            curr = reverseKGroup1(curr, k);
            while (count-- > 0) {
                ListNode temp = head.next;
                head.next = curr;
                curr = head;
                head = temp;
            }
            return curr;
        }
        return head;
    }


    //30. 串联所有单词的子串
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Integer> findSubstring(String s, String[] words) {
        int wordNum = words.length;// 单词个数
        if (wordNum == 0) {// 单词个数为0
            return new ArrayList<>();
        }

        int wordLen = words[0].length();// 每个单词的长度
        if (s.length() < wordNum * wordLen) {// 字符串长度不够
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();

        Map<String, Integer> allwords = new HashMap<>();// 保存所有单词
        for (String word : words) {
            allwords.put(word, allwords.getOrDefault(word, 0) + 1);
        }

        int max = s.length() - wordNum * wordLen;// 最大下标

        //窗口每次滑动都是一个单词的长度，所以窗口初始位置的情况有一个单词长度那么多种
        for (int offset = 0; offset < wordLen; ++offset) {// 偏移量
            for (int i = offset; i <= max; i += wordLen) {
                // 保存当前子串的单词情况
                Map<String, Integer> map = new HashMap<>();
                //从后往前一个个单词截，匹配哈希表
                for (int j = wordNum - 1; j >= 0; --j) {
                    int begin = i + j * wordLen;
                    int end = i + (j + 1) * wordLen;
                    String word = s.substring(begin, end);

                    int value = map.getOrDefault(word, 0) + 1;// 个数加1

                    if (value > allwords.getOrDefault(word, 0)) {// 个数超出
                        i += j * wordLen;// 后移j个单词的宽度
                        break;
                    }

                    // 个数符合，添加到map中
                    map.put(word, value);

                    if (j == 0) {
                        res.add(i);// 子串符合，添加到结果中
                    }
                }
            }
        }
        return res;
    }


    //32. 最长有效括号
    public int longestValidParentheses(String s) {
        int length = s.length();
        for (int i = length; i > 0; i--) {
            int left = 0, right = i - 1;
            for (int j = 0; j < length - right; j++) {
                String sub = s.substring(left, right + 1);
                if (isValit(sub)) {
                    return i;
                }
            }
        }
        return 0;
    }

    public boolean isValit(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char cha = s.charAt(i);
            if (cha == '(') {
                left++;
            } else if (cha == ')') {
                right++;
            }
            if (right > left) {
                return false;
            }
        }
        return left == right;
    }


    public int longestValidParentheses1(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public int longestValidParentheses2(String s) {
        char[] arr = s.toCharArray();
        int max = 0;
        int count = 0;
        int point = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                point++;
            } else {
                point--;
            }
            if (point >= 0) {
                count++;
                if (point == 0) {
                    max = count > max ? count : max;
                }
            } else {
                point = 0;
                count = 0;
            }
        }
        //System.out.print(max);
        count = 0;
        point = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == ')') {
                point++;
            } else {
                point--;
            }
            if (point >= 0) {
                count++;
                if (point == 0) {
                    max = count > max ? count : max;
                }
            } else {
                point = 0;
                count = 0;
            }
        }
        return max;
    }

    public int longestValidParentheses3(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (arr[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }


    //33. 搜索旋转排序数组
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (target == nums[mid]) return mid;

            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    //34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                result = mid;
                break;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (result == -1) {
            return new int[]{-1, -1};
        }

        left = right = result;
        while ((left - 1) >= 0 && nums[left - 1] == target) {
            left--;
        }

        while ((right + 1) < nums.length && nums[right + 1] == target) {
            right++;
        }

        return new int[]{left, right};
    }

    //36. 有效的数独
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isValidSudoku(char[][] board) {
        HashMap<Integer, Integer>[] row = new HashMap[9];
        HashMap<Integer, Integer>[] colums = new HashMap[9];
        HashMap<Integer, Integer>[] box = new HashMap[9];

        for (int i = 0; i < 9; i++) {
            row[i] = new HashMap<>();
            colums[i] = new HashMap<>();
            box[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int boxIndex = (i / 3) * 3 + j / 3;
                    int n = (int) num;

                    row[i].put(n, row[i].getOrDefault(n, 0) + 1);
                    colums[j].put(n, colums[j].getOrDefault(n, 0) + 1);
                    box[boxIndex].put(n, box[boxIndex].getOrDefault(n, 0) + 1);

                    if (row[i].get(n) > 1 || colums[j].get(n) > 1 || box[boxIndex].get(n) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //37. 解数独

    class solution {
        // box size
        int n = 3;
        // row size
        int N = n * n;

        int[][] rows = new int[N][N + 1];
        int[][] columns = new int[N][N + 1];
        int[][] boxes = new int[N][N + 1];

        char[][] board;

        boolean sudokuSolved = false;

        public boolean couldPlace(int d, int row, int col) {
            int idx = (row / n) * n + col / n;
            return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
        }

        public void placeNumber(int d, int row, int col) {
            int idx = (row / n) * n + col / n;
            rows[row][d]++;
            columns[col][d]++;
            boxes[idx][d]++;
            board[row][col] = (char) (d + '0');
        }

        public void removeNumber(int d, int row, int col) {
            int idx = (row / n) * n + col / n;
            rows[row][d]--;
            columns[col][d]--;
            boxes[idx][d]--;
            board[row][col] = '.';
        }

        public void placeNextNumbers(int row, int col) {
            if ((col == N - 1) && (row == N - 1)) {
                sudokuSolved = true;
            } else {
                if (col == N - 1) backtrack(row + 1, 0);
                else backtrack(row, col + 1);
            }
        }

        public void backtrack(int row, int col) {
            if (board[row][col] == '.') {
                for (int d = 1; d < 10; d++) {
                    if (couldPlace(d, row, col)) {
                        placeNumber(d, row, col);
                        placeNextNumbers(row, col);
                        if (!sudokuSolved) removeNumber(d, row, col);
                    }
                }
            } else placeNextNumbers(row, col);
        }

        public void solveSudoku(char[][] board) {
            this.board = board;
            // init rows, columns and boxes
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    char num = board[i][j];
                    if (num != '.') {
                        int d = Character.getNumericValue(num);
                        placeNumber(d, i, j);
                    }
                }
            }
            backtrack(0, 0);
        }
    }


    class suduSolution {

        int n = 9;

        //1~9 0 unuse
        int[][] rows = new int[n][n + 1];
        int[][] columns = new int[n][n + 1];
        int[][] box = new int[n][n + 1];

        char[][] board;

        boolean solved = false;

        public void solveSudoku(char[][] board) {
            this.board = board;

            //init data
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    char item = this.board[i][j];
                    if (item != '.') {
                        placeNumber(i, j, Character.getNumericValue(item));
                    }
                }
            }


            backTrack(0, 0);
        }


        public void backTrack(int row, int column) {
            if (board[row][column] == '.') {
                for (int i = 1; i <= n; i++) {
                    if (couldPlaceNumber(row, column, i)) {
                        placeNumber(row, column, i);
                        playNextNumber(row, column);
                        if (!solved) removeNumber(row, column, i);
                    }
                }
            } else {
                playNextNumber(row, column);
            }
        }

        public boolean couldPlaceNumber(int row, int column, int value) {
            int boxIndex = (row / 3) * 3 + column / 3;
            return (rows[row][value] + columns[column][value] + box[boxIndex][value]) == 0;
        }

        public void playNextNumber(int row, int column) {

            if (row == n - 1 && column == n - 1) {
                solved = true;
                return;
            }

            if (column == n - 1) {
                row++;
                column = 0;
            } else {
                column++;
            }

            backTrack(row, column);
        }


        public void placeNumber(int row, int column, int value) {
            int boxIndex = (row / 3) * 3 + column / 3;

            board[row][column] = (char) (value + '0');

            rows[row][value]++;
            columns[column][value]++;
            box[boxIndex][value]++;
        }


        public void removeNumber(int row, int column, int value) {
            int boxIndex = (row / 3) * 3 + column / 3;

            board[row][column] = '.';

            rows[row][value]--;
            columns[column][value]--;
            box[boxIndex][value]--;
        }

    }

    //77. 组合

    private List<List<Integer>> result = new ArrayList<>();
    int n;
    int k;

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) {
            return result;
        }
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<Integer>());
        return result;
    }

    public void backtrack(int first, LinkedList<Integer> stack) {
        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = first; i <= n - (k - stack.size()) + 1; i++) {
            stack.add(i);
            backtrack(i + 1, stack);
            stack.removeLast();
        }
    }


    //39. 组合总和
    //给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

    class combiSolution {

        private List<List<Integer>> result = new ArrayList<>();

        private int[] candiates;
        private int length;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            int length = candidates.length;
            if (length == 0) {
                return result;
            }

            Arrays.sort(candidates);
            this.candiates = candidates;
            this.length = length;

            backTrack(0, target, new Stack<Integer>());

            return result;
        }

        public void backTrack(int arrayIndex, int residue, Stack<Integer> answerStack) {

            if (residue == 0) {
                result.add(new ArrayList<>(answerStack));
                return;
            }

            for (int i = arrayIndex; i < length && residue >= candiates[i]; i++) {
                answerStack.push(candiates[i]);
                backTrack(i, residue - candiates[i], answerStack);
                answerStack.pop();
            }

        }

    }

    //41. 缺失的第一个正数
    class Solution41 {
        public int firstMissingPositive(int[] nums) {
            int length = nums.length;

            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < length; i++) {
                set.add(nums[i]);
            }

            int result;
            for (result = 1; result <= length; result++) {
                if (!set.contains(result)) {
                    return result;
                }
            }
            return result + 1;
        }

        public int firstMissingPositive1(int[] nums) {
            int length = nums.length;

            Arrays.sort(nums);

            for (int i = 1; i <= length; i++) {
                if (!binarySearch(nums, i)) {
                    return i;
                }
            }
            return length + 1;
        }

        public boolean binarySearch(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return false;
        }


        public int firstMissingPositive2(int[] nums) {
            int length = nums.length;

            for (int i = 0; i < length; i++) {
                while (nums[i] > 0 && nums[i] <= length && nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                }
            }

            for (int i = 0; i < length; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            return length + 1;
        }


        public void swap(int[] nums, int one, int two) {
            int temp = nums[one];
            nums[one] = nums[two];
            nums[two] = temp;
        }
    }

    //接雨水
    public int trap(int[] height) {

        int length = height.length;

        if (length == 0) {
            return 0;
        }

        int maxIndex = 0;
        int tempHeight = 0;
        for (int i = 0; i < length; i++) {
            if (height[i] > tempHeight) {
                maxIndex = i;
                tempHeight = height[i];
            }
        }

        int result = 0;

        int leftMax = height[0];
        for (int i = 0; i < maxIndex; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            } else {
                result = result + (leftMax - height[i]);
            }
        }

        int rightMax = height[length - 1];
        for (int i = length - 1; i > maxIndex; i--) {
            if (height[i] > rightMax) {
                rightMax = height[i];
            } else {
                result = result + (rightMax - height[i]);
            }
        }

        return result;
    }


    //43. 字符串相乘
    class Solution {

        //竖式计算
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            int[] result = new int[num1.length() + num2.length()];

            for (int i = num2.length() - 1; i >= 0; i--) {
                int n1 = num2.charAt(i) - '0';
                for (int j = num1.length() - 1; j >= 0; j--) {
                    int n2 = num1.charAt(j) - '0';
                    int current = n1 * n2;
                    int sum = current + result[i + j + 1];
                    //update result[i+j,i+j+1]; 本位和进位
                    result[i + j + 1] = sum % 10;
                    result[i + j] = result[i + j] + sum / 10;
                }
            }

            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (result[i] == 0) {
                i++;
            }
            for (; i < result.length; i++) {
                sb.append(result[i]);
            }

            return sb.toString();
        }

        //竖式计算
        public String multiply2(String num1, String num2) {
            int sumLen = num1.length() + num2.length();
            int[] res = new int[sumLen];
            for (int i = 0; i < num1.length(); i++) {
                int num11 = num1.charAt(num1.length() - 1 - i) - '0';//3
                for (int j = 0; j < num2.length(); j++) {
                    int num22 = num2.charAt(num2.length() - 1 - j) - '0';//6,5,4
                    res[i + j] += num11 * num22;            //序列和相同相加
                }
            }
            for (int i = 0; i < res.length - 1; i++) {
                if (res[i] >= 10) {
                    res[i + 1] += res[i] / 10;//后位加上
                    res[i] %= 10;//余数
                }
            }
            int i = res.length - 1;
            for (; i > 0 && res[i] == 0; i--) {
            } // 去除结果前面的 0
            StringBuilder sb = new StringBuilder();
            for (; i >= 0; i--) {
                sb.append(res[i]);
            }
            return sb.toString();
        }
    }


    //44. 通配符匹配
    class Solution44 {
        public boolean isMatch(String s, String p) {
            int sLen = s.length(), pLen = p.length();
            int sIdx = 0, pIdx = 0;
            int starIdx = -1, sTmpIdx = -1;

            while (sIdx < sLen) {
                if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                    ++sIdx;
                    ++pIdx;
                } else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                    starIdx = pIdx;
                    sTmpIdx = sIdx;
                    ++pIdx;
                } else if (starIdx == -1) {
                    return false;
                } else {
                    pIdx = starIdx + 1;
                    sIdx = sTmpIdx + 1;
                    sTmpIdx = sIdx;
                }
            }

            // The remaining characters in the pattern should all be '*' characters
            for (int i = pIdx; i < pLen; i++)
                if (p.charAt(i) != '*') return false;
            return true;
        }

        public boolean isMatch1(String s, String p) {
            int sLen = s.length(), pLen = p.length();
            int sIndex = 0, pIndex = 0;
            int sStarSaveIndex = -1, pStarIndex = -1;

            while (sIndex < sLen) {
                if (pIndex < pLen && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')) {
                    sIndex++;
                    pIndex++;
                } else if (pIndex < pLen && p.charAt(pIndex) == '*') {
                    sStarSaveIndex = sIndex;
                    pStarIndex = pIndex;
                    pIndex++;
                } else {  //nothing match
                    //no star before
                    if (sStarSaveIndex == -1) {
                        return false;
                    } else {
                        sIndex = sStarSaveIndex + 1;
                        pIndex = pStarIndex + 1;
                        sStarSaveIndex = sIndex;
                    }
                }
            }

            for (int i = pIndex; i < pLen; i++) {
                if (p.charAt(i) != '*') return false;
            }

            return true;
        }
    }


    //55. 跳跃游戏
    class Solution55 {
        /**
         * 1如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
         * 2可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
         * 3如果可以一直跳到最后，就成功了。
         * <p>
         * 作者：ikaruga
         * 链接：https://leetcode-cn.com/problems/jump-game/solution/55-by-ikaruga/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         *
         * @param nums
         * @return
         */
        public boolean canJump(int[] nums) {
            int k = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i > k) {
                    return false;
                }
                k = Math.max(k, i + nums[i]);
            }
            return true;
        }
    }

    //45. 跳跃游戏 II
    class Solution45 {
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            int curIndex = 0;
            int count = 1;
            int targetIndex = nums.length - 1;
            while (curIndex <= targetIndex) {
                if (nums[curIndex] + curIndex >= targetIndex) {
                    return count;
                }
                int nextIndex = findMax(nums, curIndex);
                System.out.print("index:" + nextIndex + "\n");
                if (nextIndex == targetIndex) {
                    return count + 1;
                } else {
                    curIndex = nextIndex;
                }
                count++;
            }
            return count;
        }

        public int findMax(int[] nums, int index) {
            int maxIndex = index + 1;
            int temp = 0;
            for (int i = index + 1; i <= nums[index] + index && i < nums.length; i++) {
                if (i + nums[i] >= nums.length - 1) {
                    return nums.length - 1;
                }
                if (i + nums[i] > temp) {
                    temp = nums[i] + i;
                    maxIndex = i;
                }
            }
            return maxIndex;
        }


        public int jump1(int[] nums) {
            int end = 0;
            int maxPosition = 0;
            int count = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                maxPosition = Math.max(maxPosition, nums[i] + i);
                if (i == end) {
                    end = maxPosition;
                    count++;
                }
            }
            return count;
        }
    }

    //46. 全排列
    class Solution46 {

        List<List<Integer>> result = new ArrayList<>();

        int length;
        int[] nums;

        public List<List<Integer>> permute(int[] nums) {
            this.length = nums.length;
            this.nums = nums;
            selected(new Stack<Integer>());
            return result;
        }

        public void selected(Stack<Integer> stack) {
            if (stack.size() == length) {
                result.add(new ArrayList<>(stack));
                return;
            }

            for (int i = 0; i < length; i++) {
                if (stack.contains(nums[i])) {
                    continue;
                }
                stack.push(nums[i]);
                selected(stack);
                stack.pop();
            }
        }
    }

    //47. 全排列 II
    class Solution47 {

        List<List<Integer>> result = new ArrayList<>();

        int length;
        int[] nums;
        boolean[] used;

        public List<List<Integer>> permuteUnique(int[] nums) {
            this.length = nums.length;
            this.nums = nums;
            Arrays.sort(this.nums);
            used = new boolean[length];
            selected(0, new Stack<Integer>());
            return result;
        }

        public void selected(int level, Stack<Integer> stack) {
            if (level == length) {
                result.add(new ArrayList<>(stack));
                return;
            }

            for (int i = 0; i < length; i++) {
                if (!used[i]) {

                    if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                        continue;
                    }

                    used[i] = true;
                    stack.push(nums[i]);
                    selected(level + 1, stack);
                    stack.pop();
                    used[i] = false;
                }
            }
        }
    }


    //48. 旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    //49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String key = String.valueOf(temp);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    //50. Pow(x, n)
    class solution50 {
        private double fastPow(double x, long n) {
            if (n == 0) {
                return 1.0;
            }
            double half = fastPow(x, n / 2);
            if (n % 2 == 0) {
                return half * half;
            } else {
                return half * half * x;
            }
        }

        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            return fastPow(x, N);
        }
    }


    //52 N queen
    class Solution52 {
        public boolean is_not_under_attack(int row, int col, int n, int[] rows, int[] hills, int[] dales) {
            int res = rows[col] + hills[row - col + 2 * n] + dales[row + col];
            return (res == 0) ? true : false;
        }

        public int backtrack(int row, int count, int n, int[] rows, int[] hills, int[] dales) {
            for (int col = 0; col < n; col++) {
                if (is_not_under_attack(row, col, n, rows, hills, dales)) {
                    // place_queen
                    rows[col] = 1;
                    hills[row - col + 2 * n] = 1;  // "hill" diagonals
                    dales[row + col] = 1;   //"dale" diagonals

                    // if n queens are already placed
                    if (row + 1 == n) count++;
                        // if not proceed to place the rest
                    else count = backtrack(row + 1, count, n,
                            rows, hills, dales);
                    // remove queen
                    rows[col] = 0;
                    hills[row - col + 2 * n] = 0;
                    dales[row + col] = 0;
                }
            }
            return count;
        }

        public int totalNQueens(int n) {
            int rows[] = new int[n];
            // "hill" diagonals
            int hills[] = new int[4 * n - 1];
            // "dale" diagonals
            int dales[] = new int[2 * n - 1];

            return backtrack(0, 0, n, rows, hills, dales);
        }
    }
}
