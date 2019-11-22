package com.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ads.MinHeap2.stringToParams;

public class RedBlackTree {

    private final int RED = 0;
    private static final int BLACK = 1;
    public List<RBTNode> rangeBuildings;
    public static class RBTNode {

        Node key;
        int color = BLACK;
        RBTNode left = nil, right = nil, parent = nil;

        RBTNode(Node key) {
            this.key = key;
        }
    }

    private static final RBTNode nil = new RBTNode(new Node(-1,-1,-1));
    private RBTNode root = nil;

    public RBTNode printBuilding( int buildingNum ) {
        RBTNode current = root;
        while(current!=nil) {
            if( buildingNum < current.key.getBuildingNum() )
                current = current.left;
            else if( buildingNum > current.key.getBuildingNum())
                current = current.right;
            else if( buildingNum == current.key.getBuildingNum() )
                return current;
        }
        return null;
    }

    public List<RBTNode> printBuilding(int x, int y) {
        rangeBuildings=new ArrayList<>();
        RBTNode current = root;
        printBetween(current,x,y);
        return rangeBuildings;
    }
    public void printBetween(RBTNode current,int x, int y){
        if(current==nil){

        } else if( x <= current.key.getBuildingNum() && y >= current.key.getBuildingNum()) {
            rangeBuildings.add(current);
            printBetween(current.left, x,Math.min(y,current.key.getBuildingNum()));
            printBetween(current.right, Math.max(x,current.key.getBuildingNum()),y);//current = current.left;
        } else if ( x > current.key.getBuildingNum() && y > current.key.getBuildingNum()) {
            printBetween(current.right, Math.max(x,current.key.getBuildingNum()),y);//current = current.left;
        } else if (x < current.key.getBuildingNum() && y < current.key.getBuildingNum()){
            printBetween(current.left, x,Math.min(y,current.key.getBuildingNum()));
        }
    }

    private RBTNode findNode(RBTNode findNode, RBTNode node) {
        if (root == nil) {
            return null;
        }

        if (findNode.key.getBuildingNum() < node.key.getBuildingNum()) {
            if (node.left != nil) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.key.getBuildingNum() > node.key.getBuildingNum()) {
            if (node.right != nil) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.key.getBuildingNum() == node.key.getBuildingNum()) {
            return node;
        }
        return null;
    }

    public void insertRBT(RBTNode node) {
        RBTNode temp = root;
        if (root == nil) {
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } else {
            node.color = RED;
            while (true) {
                if (node.key.getBuildingNum() < temp.key.getBuildingNum()) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key.getBuildingNum() >= temp.key.getBuildingNum()) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    //Takes as argument the newly inserted node
    private void fixTree(RBTNode node) {
        while (node.parent.color == RED) {
            RBTNode uncle = nil;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.right) {
                    //Double rotation needed
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotateRight(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    //Double rotation needed
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    void rotateLeft(RBTNode node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {//Need to rotate root
            RBTNode right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    void rotateRight(RBTNode node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate root
            RBTNode left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    //Deletes whole tree
    void deleteTree(){
        root = nil;
    }

    //Deletion Code .

    //This operation doesn't care about the new Node's connections
    //with previous node's left and right. The caller has to take care
    //of that.
    void transplant(RBTNode target, RBTNode with){
        if(target.parent == nil){
            root = with;
        }else if(target == target.parent.left){
            target.parent.left = with;
        }else
            target.parent.right = with;
        with.parent = target.parent;
    }

    boolean delete(RBTNode z){
        if((z = findNode(z, root))==null)return false;
        RBTNode x;
        RBTNode y = z; // temporary reference y
        int y_original_color = y.color;

        if(z.left == nil){
            x = z.right;
            transplant(z, z.right);
        }else if(z.right == nil){
            x = z.left;
            transplant(z, z.left);
        }else{
            y = treeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(y_original_color==BLACK)
            deleteFixup(x);
        return true;
    }

    private void deleteFixup(RBTNode x){
        while(x!=root && x.color == BLACK){
            if(x == x.parent.left){
                RBTNode w = x.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                RBTNode w = x.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    private RBTNode treeMinimum(RBTNode subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
}
