package com.ads;

import java.io.*;
import java.util.*;

import static com.ads.MinHeap.stringToParams;

public class RisingCity {
    static int days = 0;
    static int maxBuildings = 2000;

    public static void main(String[] args) throws IOException {
        // write your code here
        long start1 = System.currentTimeMillis();
        MinHeap minHeap = new MinHeap(maxBuildings); //Initializing empty array for minHeap
        RedBlackTree rbt = new RedBlackTree();
        /*File file = new File("C:\\Me_Florida\\UF_courses\\ADS\\ADSProject\\ADS_ProgramminProject\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while((st=br.readLine())!=null){
        System.out.println(st);
        }*/
        /*FileReader fr =
        new FileReader("C:\\Me_Florida\\UF_courses\\ADS\\ADSProject\\ADS_ProgramminProject\\input.txt");

        int i;
        while ((i=fr.read()) != -1)
        System.out.print((char) i);*/
        File file =
                new File("C:\\Me_Florida\\UF_courses\\ADS\\ADSProject\\ADS_ProgramminProject\\input_2.txt");
        StringBuilder str = new StringBuilder();
        BufferedWriter writer = new BufferedWriter(new FileWriter("output_wip", false));
        writer.flush();
        List<Building> buildingList = new ArrayList<>();
        List<String> commandList = new ArrayList<>();


        Scanner sc = new Scanner(file);
        Scanner scCopy=new Scanner(file);
        if(scCopy.hasNextLine()) scCopy.nextLine();
        int completedBuildingNum = -1;
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if(!line.trim().equals(""))
                commandList.add(line);
        }
        /*for (String command : commandList) {
            System.out.println(command);
        }*//*
        String currentCommandLine = commandList.get(0);
        String nextCommandLine = commandList.get(1);
        int currentCommandDay =Integer.parseInt(currentCommandLine.split(":")[0]);
        int nextCommandDay =Integer.parseInt(nextCommandLine.split(":")[0]);
        String currentCommand=currentCommandLine.split(":")[1];
        while(!commandList.isEmpty()){
            while(days>=currentCommandDay && days<nextCommandDay){
                System.out.println("current command day = "+currentCommandDay);
                System.out.println("current command = "+currentCommand);
                System.out.println("next command day = "+nextCommandDay);

                commandList.remove(0);

                days++;
            }
            currentCommandLine = commandList.get(0);
            nextCommandLine = commandList.get(1);
            currentCommandDay=Integer.parseInt(currentCommandLine.split(":")[0]);
            nextCommandDay =Integer.parseInt(nextCommandLine.split(":")[0]);
            currentCommand=currentCommandLine.split(":")[1];
        }*/

    for(int i=0;i<commandList.size();i++) {
            String currentLine = commandList.get(i);//sc.nextLine();
            int commandTime =Integer.parseInt(currentLine.split(":")[0]);
            String currentCommand=currentLine.split(":")[1];
            int nextCommandTime =Integer.MAX_VALUE;
            String nextCommand=null;
            if(i!=commandList.size()-1) {
                nextCommand = commandList.get(i+1);//scCopy.nextLine();
                if(!nextCommand.equalsIgnoreCase("")) nextCommandTime = Integer.parseInt(nextCommand.split(":")[0]);
            }
            while(days>=commandTime && days<nextCommandTime) {
                if (currentCommand.contains("Insert") && commandTime==days) {
                    int[] params = stringToParams(currentCommand);
                    if(rbt.printBuilding(params[0])==null) {
//                        long startInsert = System.currentTimeMillis();
                        BuildingProperties buildingProperties = new BuildingProperties(params[0], params[1], params[2]);
                        Building building = new Building(buildingProperties);
                        RedBlackTree.RBTProperties rbtProperties = new RedBlackTree.RBTProperties(new BuildingProperties(buildingProperties.buildingNum, buildingProperties.executionTime, buildingProperties.totalTime));
                        rbt.insertRBT(rbtProperties);
                        building.setRBTProperties(rbtProperties);
                        buildingList.add(building);
                        if(minHeap.heapSize==0 || minHeap.heap[0].getProgress()==0) {
                            minHeap.insert(buildingList.get(0));
                            buildingList.remove(0);
                        }
//                        long endInsert = System.currentTimeMillis();
//                        System.out.println("Insert takes " + (endInsert - startInsert) + "ms");
                    } else {
                        str.append("Building with buildingNum ").append(params[0]).append(" exists\n");
                    }
                } else if(currentCommand.contains("PrintBuilding") && commandTime==days){
                    if(currentCommand.contains(",")){
//                        long startPrint1 = System.currentTimeMillis();
                        int[] buildingNums = stringToParams(currentCommand);
                        List<RedBlackTree.RBTProperties> rangeBuildings = rbt.printBuilding(buildingNums[0],buildingNums[2]);
                        for(Building building : buildingList){
                            if(building.getBuildingProperties().getBuildingNum()>buildingNums[0] && building.getBuildingProperties().getBuildingNum()<buildingNums[2])
                                rangeBuildings.add(building.getRBTProperties());
                        }
                        if(rangeBuildings.size()==0){
                            //str.append("here");
                            str.append("(" + 0 + "," + 0 + "," + 0 + ")");
                        } else {
                            int j=0;
                            for(RedBlackTree.RBTProperties rbtBuilding: rangeBuildings){
                                //str.append("here1");
                                str.append("(").append(rbtBuilding.key.getBuildingNum()).append(",").append(rbtBuilding.key.getExecutionTime()).append(",").append(rbtBuilding.key.getTotalTime()).append(")");
                                j++;
                                if(j!=rangeBuildings.size())  str.append(",");
                            }
                        }
                        str.append("\n");
//                        long endPrint1 = System.currentTimeMillis();
//                        System.out.println("Print all takes " + (endPrint1 - startPrint1) + "ms");
                    } else {
//                        long startPrint2 = System.currentTimeMillis();
                        String part = currentCommand.split("\\(")[1];
                        int buildingNum = Integer.parseInt(part.split("\\)")[0]);
                        RedBlackTree.RBTProperties printBuilding = rbt.printBuilding(buildingNum);
                        for(Building building : buildingList){
                            if(building.getBuildingProperties().getBuildingNum()==buildingNum)
                                printBuilding=building.getRBTProperties();
                        }
                        //str.append("here4");
                        if (printBuilding == null) {
                            //str.append("here2");
                            str.append("(" + 0 + "," + 0 + "," + 0 + ")\n");
                        } else {
                            //str.append("here3");
                            str.append("(").append(printBuilding.key.getBuildingNum()).append(",").append(printBuilding.key.getExecutionTime()).append(",").append(printBuilding.key.getTotalTime()).append(")\n");
                        }
//                        long endPrint2 = System.currentTimeMillis();
//                        System.out.println("Print all takes " + (endPrint2 - startPrint2) + "ms");
                    }
                    if(completedBuildingNum!=-1) {
                        RedBlackTree.RBTProperties completedBuilding = rbt.printBuilding(completedBuildingNum);
                        if (completedBuilding.key.getExecutionTime() == completedBuilding.key.getTotalTime()) {
                            rbt.delete(completedBuilding);
                            //minHeap.delete(0);
                        }
                    }
                }
                days++;
                if((completedBuildingNum = minHeap.construct(nextCommand, buildingList, minHeap.heap[0], rbt))!=-1) str.append("(").append(completedBuildingNum).append(",").append(days).append(")\n");
                //if(nextCommand.)
                if(nextCommand==null){//if(i==commandList.size()-1){
                    //while(rbt.size()!=0){
                        //rbt.deleteCompleted();
                    //}
                   /* while(rbt.findProperties(z, rbt.root)!=null){
                        if(minHeap.heap[k].getBuildingProperties().getExecutionTime()==minHeap.heap[k].getBuildingProperties().getTotalTime())
                            rbt.delete(minHeap.heap[k].getRBTProperties());
                            //minHeap.delete(k);
                    }*/
                    /*if(minHeap.heap[0].getBuildingProperties().getExecutionTime()==minHeap.heap[0].getBuildingProperties().getTotalTime())
                        minHeap.delete(0);*/
                    if(minHeap.isEmpty())
                        break;
                }
            }
        }
        writer.append(str);
        writer.close();
        long end1 = System.currentTimeMillis();
        System.out.println("Application on the whole takes " + (end1 - start1) + "ms");
        System.gc();
        Runtime rt = Runtime.getRuntime();
        long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
        System.out.println( "memory usage " + usedMB+"MB");
    }

}
