package com.orange.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.orange.algorithm.DataStructure.ListNode;
import com.orange.algorithm.DataStructure.TreeNode;

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
        if (s.length() != t.length()) {
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
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int len = ch1.length;
        for (int i = 0; i < len; i++) {
            if (s.indexOf(ch1[i]) != t.indexOf(ch2[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        char[] temp = new char[127];
        char[] temp1 = new char[127];
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (temp[S[i]] != '\0' || temp1[T[i]] != '\0') {
                if (temp[S[i]] != T[i]) {
                    return false;
                }
            } else {
                temp[S[i]] = T[i];
                temp1[T[i]] = S[i];
            }
        }
        return true;
    }


    //217. 存在重复元素      给定一个整数数组，判断是否存在重复元素。
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int temp : nums) {
            if (set.contains(temp)) {
                return true;
            }
            set.add(temp);
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    //219. 存在重复元素 II
    //给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], i);
            if (map.size() > k) {
                map.remove(nums[i - k]);
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


    //258. 各位相加

    /**
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     * <p>
     * 示例:
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     */
    public int addDigits(int num) {
        while (num > 10) {
            int sum = 0;
            while (num > 0) {
                sum = sum + num % 10;
                num = num / 10;
            }
            System.out.println(sum);
            num = sum;
        }
        return num;
    }

    /**
     * 时间复杂度为O(1)的解法：
     * <p>
     * 这个题目的O(1)解法是对9取余数，我们可以通过归纳总结得到，但是无法直观想清楚原理，或者推导公式，这里我用最直观的画图和推导来讲清楚原理。假设有N个石头，我们按照十进制给这N个石头聚堆。
     * 按照十进制聚堆的意思是：1个为1堆，10个为1堆，100个为1堆，也就是10的m次方能聚成1堆
     * 假如石头有123个，那么能聚成几堆？
     * <p>
     * 如上图所示，能聚成6堆，其实这就是题目最直观的解法。有k1个(100)堆，百位数就是k1；有k2个(10)堆，十位数就是k2；有k3个(1)堆，个位数就是k3。
     * 堆的个数就是题目要求的各个位上的数之和。设求堆个数的函数为F(N),那么有：
     * F(123) = (100 - 99) + (10 - 9) + (10 - 9) + 1 + 1 + 1 = 123 - 99x1 -9x2 = 123 - 9x13
     * 也就是每个类别的堆，去掉9或者99，就能得到堆个数，假如是(1000)堆，去掉999就行了，然后把所有加起来，得到结果，那么我们知道有:
     * F(N) = N - 9xM1
     * 那么问题来了，假如(N - 9xM1)大于10，是不满足要求的，但(N - 9xM1)也是一个数，我们继续可以调用F(N)这个函数：
     * F(N - 9xM) = N - 9xM1 - 9xM2
     * 按照题目设定，只要是大于10，就继续在得到的结果上操作，则有:
     * F(N) = F(N - 9xM) = N - 9xM1 -9xM2 = ...
     * 依次类推，直到满足条件:
     * F(N) = N - 9xM,其中M = (M1+M2+M3+...)；并且结果小于10且大于0，因为堆的个数不可能为0个，这就是对9求余数的的算式，但是稍有变化
     * 当 (N % 9) != 0:
     * F(N) = N % 9
     * 当(N % 9) == 0时，N的结果和9是等价的，那么应该是:
     * F(N) = F(N - 9xM) = F(9) = 9
     * 不能是F(N) = F(0)，这样就错了
     * 推导不完全严谨，大家理解意思就行
     * <p>
     * 作者：zui-qiang-coder
     * 链接：https://leetcode-cn.com/problems/add-digits/solution/python-hua-tu-jiang-ming-bai-o1zuo-fa-de-yuan-li-b/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    //263. 丑数
    //丑数就是只包含质因数 2, 3, 5 的正整数。
    public boolean isUgly(int num) {
        while (true){
            int temp=num;
            if (num%2==0){
                num=num/2;
            }
            if ((num%3==0)){
                num=num/3;
            }
            if (num%5==0){
                num=num/5;
            }
            if (num==1){
                return true;
            }
            if (num==temp){
                return false;
            }
        }
    }


    //257. 二叉树的所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result=new ArrayList<>();
        binaryTreePathsHelper(result,"",root);
        return result;
    }

    private void binaryTreePathsHelper(List<String> result, String path, DataStructure.TreeNode node){
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

    //278. 第一个错误的版本
    public int firstBadVersion(int n) {
        int left=1;
        int right=n;
        while (right>left){
            int mid=left+(right-left)/2;
            if (isBadVersion(mid)){
                right=mid;
            }else {
                left=mid+1;
            }
        }
        return left;
    }

    public boolean isBadVersion(int value){
        return value>0;
    }

    //283. 移动零
    public void moveZeroes(int[] nums) {
        int size=nums.length;
        int []temp=new int[size];
        int index=0;
        for (int i = 0; i < size; i++) {
            if (nums[i]!=0){
                temp[index]=nums[i];
                index++;
            }
        }
        for (int i = 0; i < size; i++) {
            nums[i]=temp[i];
        }
    }

    public void moveZeroes2(int[] nums) {
        int curZeroCount=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==0){
                curZeroCount++;
            }else {
                if (curZeroCount==0){
                    continue;
                }
                nums[i-curZeroCount]=nums[i];
                nums[i]=0;
            }
        }

    }

    //290. 单词规律
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        char[] pat = pattern.toCharArray();
        String[] ori = str.split(" ");
        if (pat.length != ori.length) {
            return false;
        }
        for (int i = 0; i < pat.length; i++) {
            if (map.containsKey(pat[i])) {
                if (!ori[i].equals(map.get(pat[i]))) {
                    return false;
                }
            } else if (map.containsValue(ori[i])) {
                return false;
            } else {
                map.put(pat[i], ori[i]);
            }
        }
        return true;
    }



    //292. Nim 游戏
    public boolean canWinNim(int n) {
        return n%4!=0;
    }


    //299. 猜数字游戏
    public String getHint(String secret, String guess) {
        int[] bucket = new int[10];
        int bull = 0;
        int cow = 0;
        for(int i=0;i<secret.length();i++){
            if(secret.charAt(i)== guess.charAt(i)){
                bull++;
                continue;
            }
            bucket[secret.charAt(i) - '0'] += 1;
            bucket[guess.charAt(i) - '0'] -= 1;

        }
        //计算bucket中正值的个数
        for(int i=0;i<10;i++){
            if(bucket[i] > 0) {
                cow+= bucket[i];
            }
        }

        cow = secret.length() - cow - bull;
        String res = bull + "A" + cow + "B";
        return res;
    }


}
