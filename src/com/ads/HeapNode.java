package com.ads;

public class HeapNode {
    Node node;
    int tempProgress;
    RedBlackTree.RBTNode rbtNode;

    /* Constructor */
    public HeapNode(Node basicNode)
    {
        node=basicNode;
    }

    public HeapNode(Node basicNode, int tempProg, RedBlackTree.RBTNode rbt) {
        node = basicNode;
        tempProgress = tempProg;
        rbtNode = rbt;
    }

    //Getter
    public Node getNode() {
        return node;
    }

    // Setter
    public void setNode(Node newnode) {
        this.node = newnode;
    }

    //Getter
    public int getTempProgress() {
        return tempProgress;
    }

    // Setter
    public void setTempProgress(int newTempProgress) {
        this.tempProgress = newTempProgress;
    }

    //Getter
    public RedBlackTree.RBTNode getRBTNode() {
        return rbtNode;
    }

    // Setter
    public void setRBTNode(RedBlackTree.RBTNode newRBTNode) {
        this.rbtNode = newRBTNode;
    }

}

