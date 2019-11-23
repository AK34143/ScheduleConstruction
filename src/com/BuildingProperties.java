package com;

/**
 * BuildingProperties object that contains the main three properties of the building :- buildingNum, executionTime, totalTime
 */
public class BuildingProperties {
    int buildingNum;
    int executionTime;
    int totalTime;

    /* Constructor */
    public BuildingProperties(int buildingNumber, int execTime, int totTime)
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

}

