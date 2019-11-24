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
     * @param buildingNum1
     * @param buildingNum2
     * @return
     */
    public List<RBTProperties> printBuilding(int buildingNum1, int buildingNum2) {
        rangeBuildings=new ArrayList<>();
        RBTProperties current = root;
        printBetween(current,buildingNum1,buildingNum2);
        return rangeBuildings;
    }

    /**
     * Get buildings between two building numbers
     * @param current
     * @param buildingNum1
     * @param buildingNum2
     */
    public void printBetween(RBTProperties current,int buildingNum1, int buildingNum2){
        if(current==nil){

        }else {
            /**If building number of the root is larger than first parameter , then we can get building
             in left subtree */
            if (buildingNum1 < current.buildingProperties.getBuildingNum()) {
                printBetween(current.left, buildingNum1, buildingNum2);
            }

            /**if root's data lies in range, then return root's data*/
            if (buildingNum1 <= current.buildingProperties.getBuildingNum() && buildingNum2 >= current.buildingProperties.getBuildingNum()) {
                rangeBuildings.add(current);
            }

            /**If building number of the root is smaller than second parameter , then only we can get building
            in right subtree */
            if (buildingNum2 > current.buildingProperties.getBuildingNum()) {
                printBetween(current.right, buildingNum1, buildingNum2);
            }
        }
    }

    /**
     * Find RBTProperties node with reference to another node
     * @param findRBTProperties
     * @param refRBTProperties
     * @return
     */
    private RBTProperties findProperties(RBTProperties findRBTProperties, RBTProperties refRBTProperties) {
        if (root == nil) {
            return null;
        }

        if (findRBTProperties.buildingProperties.getBuildingNum() < refRBTProperties.buildingProperties.getBuildingNum()) {
            if (refRBTProperties.left != nil) {
                return findProperties(findRBTProperties, refRBTProperties.left);
            }
        } else if (findRBTProperties.buildingProperties.getBuildingNum() > refRBTProperties.buildingProperties.getBuildingNum()) {
            if (refRBTProperties.right != nil) {
                return findProperties(findRBTProperties, refRBTProperties.right);
            }
        } else if (findRBTProperties.buildingProperties.getBuildingNum() == refRBTProperties.buildingProperties.getBuildingNum()) {
            return refRBTProperties;
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
        int yOriginalColor = y.color;

        if(node.left == nil){
            x = node.right;
            transplant(node, node.right);
        }else if(node.right == nil){
            x = node.left;
            transplant(node, node.left);
        }else{
            y = treeMinimum(node.right);
            yOriginalColor = y.color;
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
        if(yOriginalColor==BLACK)
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
                RBTProperties sibling = node.parent.right;
                if(sibling.color == RED){
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }
                if(sibling.left.color == BLACK && sibling.right.color == BLACK){
                    sibling.color = RED;
                    node = node.parent;
                    continue;
                }
                else if(sibling.right.color == BLACK){
                    sibling.left.color = BLACK;
                    sibling.color = RED;
                    rotateRight(sibling);
                    sibling = node.parent.right;
                }
                if(sibling.right.color == RED){
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            }else{
                RBTProperties sibling = node.parent.left;
                if(sibling.color == RED){
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }
                if(sibling.right.color == BLACK && sibling.left.color == BLACK){
                    sibling.color = RED;
                    node = node.parent;
                    continue;
                }
                else if(sibling.left.color == BLACK){
                    sibling.right.color = BLACK;
                    sibling.color = RED;
                    rotateLeft(sibling);
                    sibling = node.parent.left;
                }
                if(sibling.left.color == RED){
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    sibling.left.color = BLACK;
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
}
