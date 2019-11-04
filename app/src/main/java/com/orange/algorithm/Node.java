package com.orange.algorithm;

import java.util.List;

/**
 * created by czh on 2019/7/25
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
