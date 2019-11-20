package com.ads;

public class Node {
    int buildingNum;
    int executionTime;
    int totalTime;
    //RedBlackTree2.RBTNode rbtNode;

    /* Constructor */
    public Node(int buildingNumber, int execTime, int totTime)
    {
        buildingNum = buildingNumber;
        executionTime = execTime;
        totalTime = totTime;
    }
    //Getter
    public int getBuildingNum() {
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
    }

    //Getter
    /*public RedBlackTree2.RBTNode getRBTNode() {
        return rbtNode;
    }*/

    // Setter
    /*public void setRBTNode(RedBlackTree2.RBTNode newRBTNode) {
        this.rbtNode = newRBTNode;
    }*/
}

