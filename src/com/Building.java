package com;

/**
 * Building object that contains buildingProperties(buildingNu,  exectionTime, totalTime) , progess and corresponding RedBlackTree node
 */
public class Building {
    BuildingProperties buildingProperties;
    int progress;
    RedBlackTree.RBTProperties rbtProperties;

    /* Constructor */
    public Building(BuildingProperties basicBuildingProperties)
    {
        buildingProperties=basicBuildingProperties;
    }

    public Building(BuildingProperties basicBuildingProperties, int prog, RedBlackTree.RBTProperties rbt) {
        buildingProperties = basicBuildingProperties;
        progress = prog;
        rbtProperties = rbt;
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
    public RedBlackTree.RBTProperties getRBTProperties() {
        return rbtProperties;
    }

    // Setter
    public void setRBTProperties(RedBlackTree.RBTProperties newRBTProperties) {
        this.rbtProperties = newRBTProperties;
    }

}

