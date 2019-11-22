package com.ads;

public class Building {
    BuildingProperties buildingProperties;
    int progress;
    RedBlackTree.RBTNode rbtNode;

    /* Constructor */
    public Building(BuildingProperties basicNode)
    {
        buildingProperties=basicNode;
    }

    public Building(BuildingProperties basicNode, int prog, RedBlackTree.RBTNode rbt) {
        buildingProperties = basicNode;
        progress = prog;
        rbtNode = rbt;
    }

    //Getter
    public BuildingProperties getBuildingProperties() {
        return buildingProperties;
    }

    // Setter
    public void setBuildingProperties(BuildingProperties newBuildingProps) {
        this.buildingProperties = newBuildingProps;
    }

    //Getter
    public int getProgress() {
        return progress;
    }

    // Setter
    public void setProgress(int newProgress) {
        this.progress = newProgress;
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

