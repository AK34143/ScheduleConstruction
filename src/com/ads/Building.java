package com.ads;

public class Building {
    BuildingProperties buildingProperties;
    int tempProgress;
    RedBlackTree.RBTNode rbtNode;

    /* Constructor */
    public Building(BuildingProperties basicNode)
    {
        buildingProperties=basicNode;
    }

    public Building(BuildingProperties basicNode, int tempProg, RedBlackTree.RBTNode rbt) {
        buildingProperties = basicNode;
        tempProgress = tempProg;
        rbtNode = rbt;
    }

    //Getter
    public BuildingProperties getNode() {
        return buildingProperties;
    }

    // Setter
    public void setNode(BuildingProperties newnode) {
        this.buildingProperties = newnode;
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

