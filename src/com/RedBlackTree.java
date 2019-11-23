package com;

import java.util.ArrayList;
import java.util.List;

/**
 * RedBlackTree implementation
 */
public class RedBlackTree {

    private final int RED = 0;
    private static final int BLACK = 1;
    public List<RBTProperties> rangeBuildings;

    public static class RBTProperties {

        BuildingProperties buildingProperties;
        int color = BLACK;
        RBTProperties left = nil, right = nil, parent = nil;
        int size;
        RBTProperties(BuildingProperties buildingProperties) {
            this.buildingProperties = buildingProperties;
        }
    }

    private static final RBTProperties nil = new RBTProperties(new BuildingProperties(-1,-1,-1));
    public RBTProperties root = nil;

    /**
     * Print Building for a given buildingNum
     * @param buildingNum
     * @return
     */
    public RBTProperties printBuilding( int buildingNum ) {
        RBTProperties current = root;
        while(current!=nil) {
            if( buildingNum < current.buildingProperties.getBuildingNum() )
                current = current.left;
            else if( buildingNum > current.buildingProperties.getBuildingNum())
                current = current.right;
            else if( buildingNum == current.buildingProperties.getBuildingNum() )
                return current;
        }
        return null;
    }

    /**
     * Print building between 2 buildingNumbers
     * @param x
     * @param y
     * @return
     */
    public List<RBTProperties> printBuilding(int x, int y) {
        rangeBuildings=new ArrayList<>();
        RBTProperties current = root;
        printBetween(current,x,y);
        return rangeBuildings;
    }

    /**
     * Get buildings between two building numbers
     * @param current
     * @param x
     * @param y
     */
    public void printBetween(RBTProperties current,int x, int y){
        if(current==nil){

        }else {
            /**If building number of the root is larger than first parameter , then we can get building
             in left subtree */
            if (x < current.buildingProperties.getBuildingNum()) {
                printBetween(current.left, x, y);
            }

            /**if root's data lies in range, then return root's data*/
            if (x <= current.buildingProperties.getBuildingNum() && y >= current.buildingProperties.getBuildingNum()) {
                rangeBuildings.add(current);
            }

            /**If building number of the root is smaller than second parameter , then only we can get building
            in right subtree */
            if (y > current.buildingProperties.getBuildingNum()) {
                printBetween(current.right, x, y);
            }
        }
        /*if(current==nil){

        } else if( x <= current.key.getBuildingNum() && y >= current.key.getBuildingNum()) {
            rangeBuildings.add(current);
            printBetween(current.left, x,Math.min(y,current.key.getBuildingNum()));
            printBetween(current.right, Math.max(x,current.key.getBuildingNum()),y);
        } else if ( x > current.key.getBuildingNum() && y > current.key.getBuildingNum()) {
            printBetween(current.right, Math.max(x,current.key.getBuildingNum()),y);
        } else if (x < current.key.getBuildingNum() && y < current.key.getBuildingNum()){
            printBetween(current.left, x,Math.min(y,current.key.getBuildingNum()));
        }*/
    }

    private RBTProperties findProperties(RBTProperties findRBTProperties, RBTProperties node) {
        if (root == nil) {
            return null;
        }

        if (findRBTProperties.buildingProperties.getBuildingNum() < node.buildingProperties.getBuildingNum()) {
            if (node.left != nil) {
                return findProperties(findRBTProperties, node.left);
            }
        } else if (findRBTProperties.buildingProperties.getBuildingNum() > node.buildingProperties.getBuildingNum()) {
            if (node.right != nil) {
                return findProperties(findRBTProperties, node.right);
            }
        } else if (findRBTProperties.buildingProperties.getBuildingNum() == node.buildingProperties.getBuildingNum()) {
            return node;
        }
        return null;
    }

    /**
     * Insert into RedBlackTree
     * @param node
     */
    public void insertRBT(RBTProperties node) {
        RBTProperties temp = root;
        if (root == nil) {
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } else {
            node.color = RED;
            while (true) {
                if (node.buildingProperties.getBuildingNum() < temp.buildingProperties.getBuildingNum()) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.buildingProperties.getBuildingNum() >= temp.buildingProperties.getBuildingNum()) {
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

    /**
     * Fix the tree after inserting a node into RedBlackTree
     * @param node
     */
    private void fixTree(RBTProperties node) {
        while (node.parent.color == RED) {
            RBTProperties uncle = nil;
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
                    /**Double rotation needed*/
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                /**if the "else if" code hasn't executed, this
                *  is a case where we only need a single rotation*/
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
                    /**Double rotation needed*/
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                /**if the "else if" code hasn't executed, this
                is a case where we only need a single rotation*/
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    /**
     * Rotate left at the node
     * @param node
     */
    void rotateLeft(RBTProperties node) {
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
            RBTProperties right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    /**
     * Rotate right at the node
     * @param node
     */
    void rotateRight(RBTProperties node) {
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
            RBTProperties left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    //Deletes whole tree
    /*void deleteTree(){
        root = nil;
    }*/

    /**
     * Deletion Code .
     * This operation doesn't care about the new Node's connections
     * with previous node's left and right. The caller has to take care
     * of that.
     */

    /**
     *
     * @param target
     * @param with
     */
    void transplant(RBTProperties target, RBTProperties with){
        if(target.parent == nil){
            root = with;
        }else if(target == target.parent.left){
            target.parent.left = with;
        }else
            target.parent.right = with;
        with.parent = target.parent;
    }

    /**
     * Delete the node
     * @param node
     * @return
     */
    boolean delete(RBTProperties node){
        if((node = findProperties(node, root))==null)return false;
        RBTProperties x;
        RBTProperties y = node; // temporary reference y
        int y_original_color = y.color;

        if(node.left == nil){
            x = node.right;
            transplant(node, node.right);
        }else if(node.right == nil){
            x = node.left;
            transplant(node, node.left);
        }else{
            y = treeMinimum(node.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == node)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }
            transplant(node, y);
            y.left = node.left;
            y.left.parent = y;
            y.color = node.color;
        }
        if(y_original_color==BLACK)
            deleteFixup(x);
        return true;
    }

    /*public void deleteCompleted(){
        for(int i=0;i<size();i++) {
            if(root.key
            delete(root);
        }
    }*/

    /**
     *
     * @param node
     */
    private void deleteFixup(RBTProperties node){
        while(node!=root && node.color == BLACK){
            if(node == node.parent.left){
                RBTProperties w = node.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    w = node.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    node = node.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = node.parent.right;
                }
                if(w.right.color == RED){
                    w.color = node.parent.color;
                    node.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            }else{
                RBTProperties w = node.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    w = node.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    node = node.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = node.parent.left;
                }
                if(w.left.color == RED){
                    w.color = node.parent.color;
                    node.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }

    private RBTProperties treeMinimum(RBTProperties subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
    /*private int size(RBTProperties x) {
        if (x == null) return 0;
        return x.size;
    }


    *//**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     *//*
    public int size() {
        return size(root);
    }*/
}
