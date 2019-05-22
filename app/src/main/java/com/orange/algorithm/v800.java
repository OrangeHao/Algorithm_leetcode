package com.orange.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * created by czh on 2019/3/10
 */
public class v800 {




    //703. 数据流中的第K大元素
    class KthLargest {

        PriorityQueue<Integer> q;
        int k;

        public KthLargest(int k, int[] nums) {
            this.q=new PriorityQueue<>(k, new Comparator<Integer>() {
                @Override
                public int compare(Integer integer, Integer t1) {
                    return integer-t1;
                }
            });
            this.k=k;
            for (int i:nums){
                q.add(i);
            }
        }

        public int add(int val) {
            if (q.size()<k){
                q.offer(val);
            }else if (val>q.peek()){
                q.poll();
                q.offer(val);
            }
            return q.peek();
        }
    }
}
