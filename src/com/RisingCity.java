package com;

import java.io.*;
import java.util.*;

/**
 * RisingCity with main class to construct a city with buildings
 */
public class RisingCity {
    static int days = 0;
    static int maxBuildings = 2000;
    static String outputFile="out/output_file.txt";
    static MinHeap minHeap = new MinHeap(maxBuildings); /**Initializing array for minHeap*/

    public static void main(String[] args) throws IOException {
        /*if(args.length==0){
            System.out.println("No input file entered.");
            System.exit(0);
        }*/

        String filename="input/input_1.txt";
        File file = new File(filename);

        String line;
        Scanner sc = new Scanner(file);
        List<String> commandList = new ArrayList<>();
        /** Storing all the commands in a list called commandList*/
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if(!line.trim().equals("") && line.trim().contains(":")) {/** Add only valid commands */
                if("Insert".equals(line.split(":")[1].split("\\(")[0].trim()) || ("PrintBuilding").equals(line.split(":")[1].split("\\(")[0].trim())){
                    commandList.add(line);
                }

            }
        }
        StringBuilder str = new StringBuilder();
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
        writer.flush();
        List<Building> buildingList = new ArrayList<>();
        RedBlackTree rbt = new RedBlackTree();
        int completedBuildingNum = -1;
        /**read each command from commandList*/
        for(int i=0;i<commandList.size();i++) {
            String currentLine = commandList.get(i);
            int commandTime =Integer.parseInt(currentLine.split(":")[0]);
            String currentCommand=currentLine.split(":")[1];
            int nextCommandTime =Integer.MAX_VALUE;
            String nextCommand=null;
            if(i!=commandList.size()-1) {
                nextCommand = commandList.get(i+1);
                nextCommandTime = Integer.parseInt(nextCommand.split(":")[0]);
            }
            while(days>=commandTime && days<nextCommandTime) {
                if (currentCommand.contains("Insert") && commandTime==days) {
                    /** If the command is Insert and the time of the command matches the current day*/
                    int[] params = stringToParams(currentCommand);
                    if(rbt.printBuilding(params[0])==null) {
                        BuildingProperties buildingProperties = new BuildingProperties(params[0], params[1], params[2]);
                        Building building = new Building(buildingProperties);
                        RedBlackTree.RBTProperties rbtProperties = new RedBlackTree.RBTProperties(new BuildingProperties(buildingProperties.buildingNum, buildingProperties.executionTime, buildingProperties.totalTime));
                        rbt.insertRBT(rbtProperties);
                        building.setRBTProperties(rbtProperties);
                        buildingList.add(building);
                        /** If there is no building currently under construction, only then insert into the heap and remove from the list of building in the queue*/
                        if(minHeap.heapSize==0 || minHeap.heap[0].getProgress()==0) {
                            minHeap.insert(buildingList.get(0));
                            buildingList.remove(0);
                        }
                    } /*No need to handle according to TA
                    else {
                        str.append("Building with buildingNum ").append(params[0]).append(" exists\n");
                        System.exit(0);
                    }*/
                } else if(currentCommand.contains("PrintBuilding") && commandTime==days){
                    /** If the command is PrintBuilding and the time of the command matches the current day*/
                    if(currentCommand.contains(",")){
                        /** If the command is PrintBuilding between two building numbers*/
                        int[] buildingNums = stringToParams(currentCommand);
                        List<RedBlackTree.RBTProperties> rangeBuildings = rbt.printBuilding(buildingNums[0],buildingNums[2]);
                        if(rangeBuildings.size()==0){/** If there are no buildings between the given buildingNumbers*/
                            str.append("(" + 0 + "," + 0 + "," + 0 + ")");
                        } else {/** If there are buildings between the given buildingNumbers, loop through each building and print one after the other*/
                            int j=0;
                            for(RedBlackTree.RBTProperties rbtBuilding: rangeBuildings){
                                str.append("(").append(rbtBuilding.buildingProperties.getBuildingNum()).append(",").append(rbtBuilding.buildingProperties.getExecutionTime()).append(",").append(rbtBuilding.buildingProperties.getTotalTime()).append(")");
                                j++;
                                if(j!=rangeBuildings.size())  str.append(",");
                            }
                        }
                        str.append("\n");
                    } else {
                        /** If the command is to PrintBuilding with a particular building number*/
                        String part = currentCommand.split("\\(")[1];
                        int buildingNum = Integer.parseInt(part.split("\\)")[0]);
                        RedBlackTree.RBTProperties printBuilding = rbt.printBuilding(buildingNum);
                        if (printBuilding == null) {
                            str.append("(" + 0 + "," + 0 + "," + 0 + ")\n");
                        } else {
                            str.append("(").append(printBuilding.buildingProperties.getBuildingNum()).append(",").append(printBuilding.buildingProperties.getExecutionTime()).append(",").append(printBuilding.buildingProperties.getTotalTime()).append(")\n");
                        }
                    }

                }
                /** If the construction of a building was completed from before it is safe to delete from RedBlackTree and print */
                if(completedBuildingNum!=-1) {
                    RedBlackTree.RBTProperties completedBuilding = rbt.printBuilding(completedBuildingNum);
                    if (completedBuilding!=null && (completedBuilding.buildingProperties.getExecutionTime() == completedBuilding.buildingProperties.getTotalTime())) {
                        rbt.delete(completedBuilding);
                        str.append("(").append(completedBuildingNum).append(",").append(days).append(")\n");
                    }
                }
                days++;
                /** If completedBuildingNum is not -1, it means a building construction is completed */
                if((completedBuildingNum = construct(nextCommand, buildingList, minHeap.heap[0], rbt))!=-1) {
                    if(nextCommand==null || !nextCommand.contains("Print")){
                        str.append("(").append(completedBuildingNum).append(",").append(days).append(")\n");
                    }
                }
                /** If the heap is empty and there are no more commands left then stop */
                if(nextCommand==null && minHeap.isEmpty()){
                    break;
                }
            }
        }
        /**write to the output file*/
        writer.append(str);
        writer.close();
    }
    /**
     * This method constructs the building and returns building number if construction is completed
     * @param nextCommand
     * @param buildingList
     * @param building
     * @param rbt
     * @return
     */
    public static int construct(String nextCommand, List<Building> buildingList, Building building, RedBlackTree rbt)
    {
        int completedBuildingNum=-1;
        int minTime = Math.min(building.getBuildingProperties().getTotalTime(), 5);/** Find the min of totalTime and 5 so we can run our construction those many days */
        if(minHeap.heapSize>0 && building.getBuildingProperties().getExecutionTime()+1<=building.getBuildingProperties().getTotalTime() && building.getProgress()<minTime){
            building.getBuildingProperties().setExecutionTime(building.getBuildingProperties().getExecutionTime()+1);
            building.getRBTProperties().buildingProperties=building.getBuildingProperties();
            building.setProgress(building.getProgress()+1);
            if(building.getBuildingProperties().getExecutionTime()==building.getBuildingProperties().getTotalTime()){
                /** If the execution time and total time are same, it means the building construction is completed*/
                completedBuildingNum = building.getBuildingProperties().getBuildingNum();
                if(nextCommand==null ||(nextCommand!=null && !nextCommand.contains("Print"))) {
                    rbt.delete(building.getRBTProperties());
                }
                minHeap.delete(0);
                /**After building construction is completed, we can add the buildings in the queue and heapify to get the building with least execution time*/
                if(!buildingList.isEmpty()){
                    for(int i=0;i<buildingList.size();i++) {
                        minHeap.insert(buildingList.get(0));
                        buildingList.remove(0);
                    }
                }
            } else if(building.getProgress()==minTime){
                /**If the building is constructed for 5 days or total time, whichever is small, reset progress back to 0 and insert the buildings in the queue*/
                building.setProgress(0);
                minHeap.heapifyCompletedBuilding(0);
                if(!buildingList.isEmpty()){
                    for(int i=0;i<buildingList.size();i++) {
                        minHeap.insert(buildingList.get(0));
                        buildingList.remove(0);
                    }
                }
            }
        }
        return completedBuildingNum;
    }
    /**
     * Convert a string to array of parameters
     * @param input
     * @return
     */
    public static int[] stringToParams(String input) {
        input = input.trim();
        if (input.length() == 0) {
            return new int[0];
        }

        String[] params = input.split(",");
        int[] output = new int[3];
        /** Placing the parameters into an output array*/
        String part = params[0].split("\\(")[1];
        output[0] = Integer.parseInt(part);
        output[1]=0;
        part = params[1].split("\\)")[0];
        output[2] = Integer.parseInt(part);
        return output;
    }
}
