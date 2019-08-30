package com.whjlim.algorithm;

/**
 * Created by BlackWatch on 2019/8/30 22:14
 */
public class RedBTree<T> {
    RedBTree<T> left;
    RedBTree<T> right;
    RedBTree<T> parent;
    boolean red;
    T val;
    //左旋转
    RedBTree<T> rotateLeft(RedBTree<T> root, RedBTree<T> x){
        if(x != null && x.right != null){
            RedBTree<T> y = x.right;
            x.right = y.left;
            y.left.parent = x;
            y.parent = x.parent;
            if(x.parent == null){
                root = y;
                root.red =false; //根节点是黑色
            }else if(x == x.parent.left){
                x.parent.left = y;
            }else{
                x.parent.right = y;
            }
            y.left = x;
            x.parent = y;
        }
        return root;
    }

    RedBTree<T> rotateRight(RedBTree<T> root, RedBTree<T> y){
        if(y != null && y.left != null){
            RedBTree<T> x = y.left;
            y.left = x.right;
            x.right.parent = y;
            x.parent = y.parent;
            if(y.parent == null){
                root = x;
                root.red = false; //根几点黑色
            }else if(y == y.parent.left){
                y.parent.left = x;
            }else{
                y.parent.right = x;
            }
            x.right = y;
            y.parent = x;
        }
        return root;
    }

}
