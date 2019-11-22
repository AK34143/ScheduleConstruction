package com.ads;

public class HeapNode {
    /*int buildingNum;
    int executionTime;
    int totalTime;*/
    Node node;
    int tempProgress;
    RedBlackTree.RBTNode rbtNode;

    /* Constructor */
    //public HeapNode(int buildingNumber, int execTime, int totTime)
    public HeapNode(Node basicNode)
    {
        node=basicNode;
        /*buildingNum = buildingNumber;
        executionTime = execTime;
        totalTime = totTime;*/
    }

    //public HeapNode(int buildingNumber, int execTime, int totTime, int tempProg, RedBlackTree2.RBTNode rbt) {
    public HeapNode(Node basicNode, int tempProg, RedBlackTree.RBTNode rbt) {
        /*buildingNum = buildingNumber;
        executionTime = execTime;
        totalTime = totTime;*/
        node = basicNode;
        tempProgress = tempProg;
        rbtNode = rbt;
    }

    //Getter
    /*public int getBuildingNum() {
        return buildingNum;
    }

    // Setter
    public void setBuildingNum(int newBuildingNum) {
        this.buildingNum = newBuildingNum;
    }

    //Getter
    public int getExecutionTime() {
        return executionTime;
    }

    // Setter
    public void setExecutionTime(int newExecTime) {
        this.executionTime = newExecTime;
    }

    //Getter
    public int getTotalTime() {
        return totalTime;
    }

    // Setter
    public void setTotalTime(int newTotalTime) {
        this.totalTime = newTotalTime;
    }*/

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

