package com.ads;

import java.io.*;
import java.util.*;

import static com.ads.MinHeap2.stringToParams;

public class RisingCity {
    static int days = 0;
    private Node[] nodes;
    static int maxBuildings = 2000;

    public static void main(String[] args) throws IOException {
        // write your code here
        long start1 = System.currentTimeMillis();
        MinHeap2 minHeap = new MinHeap2(maxBuildings); //Initializing empty array for minHeap
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
                new File("C:\\Me_Florida\\UF_courses\\ADS\\ADSProject\\ADS_ProgramminProject\\input_5.txt");
        StringBuilder str = new StringBuilder();
        BufferedWriter writer = new BufferedWriter(new FileWriter("output_wip", false));
        writer.flush();
        List<HeapNode> minHeapList = new ArrayList<>();
        List<String> commandList = new ArrayList<>();


        Scanner sc = new Scanner(file);
        Scanner scCopy=new Scanner(file);
        if(scCopy.hasNextLine()) scCopy.nextLine();
        int completedBuildingNum;
        while (sc.hasNextLine()) {
            /*commandList.add(sc.nextLine());
        }*/
            String currentLine = sc.nextLine();
            int commandTime =Integer.parseInt(currentLine.split(":")[0]);
            String currentCommand=currentLine.split(":")[1];
            int nextCommandTime =Integer.MAX_VALUE;
            if(sc.hasNextLine()) {
                String nextCommand = scCopy.nextLine();
                if(!nextCommand.equalsIgnoreCase("")) nextCommandTime = Integer.parseInt(nextCommand.split(":")[0]);
            }
            while(days>=commandTime && days<nextCommandTime) {
                if (currentCommand.contains("Insert") && commandTime==days) {
                    int[] params = stringToParams(currentCommand);
                    if(rbt.printBuilding(params[0])==null) {
                        Node node1 = new Node(params[0], params[1], params[2]);
                        HeapNode heapNode1 = new HeapNode(node1);
                        RedBlackTree.RBTNode rbtNode1 = new RedBlackTree.RBTNode(new Node(node1.buildingNum, node1.executionTime, node1.totalTime));
                        rbt.insertRBT(rbtNode1);
                        heapNode1.setRBTNode(rbtNode1);
                        minHeapList.add(heapNode1);
                        if(minHeap.heapSize==0 || minHeap.heap[0].getTempProgress()==0) {
                            minHeap.insert(minHeapList.get(0));
                            minHeapList.remove(0);
                        }
                    } else {
                        str.append("Building with buildingNum ").append(params[0]).append(" exists\n");
                    }
                } else if(currentCommand.contains("PrintBuilding") && commandTime==days){
                    if(currentCommand.contains(",")){
                        int[] params = stringToParams(currentCommand);
                        List<RedBlackTree.RBTNode> rangeBuildings = rbt.printBuilding(params[0],params[2]);
                        for(HeapNode minHeapNode : minHeapList){
                            if(minHeapNode.getNode().getBuildingNum()>params[0] && minHeapNode.getNode().getBuildingNum()<params[2])
                                rangeBuildings.add(minHeapNode.getRBTNode());
                        }
                        if(rangeBuildings.size()==0){
                            str.append("(" + 0 + "," + 0 + "," + 0 + ")");
                        } else {
                            int i=0;
                            for(RedBlackTree.RBTNode rbtNode: rangeBuildings){
                                str.append("(").append(rbtNode.key.getBuildingNum()).append(",").append(rbtNode.key.getExecutionTime()).append(",").append(rbtNode.key.getTotalTime()).append(")");
                                i++;
                                if(i!=rangeBuildings.size())  str.append(",");
                            }
                        }
                        str.append("\n");
                    } else {
                        String part = currentCommand.split("\\(")[1];
                        part = part.split("\\)")[0];
                        RedBlackTree.RBTNode printBuildingNode = rbt.printBuilding(Integer.parseInt(part));
                        for(HeapNode minHeapNode : minHeapList){
                            if(minHeapNode.getNode().getBuildingNum()==Integer.parseInt(part))
                                printBuildingNode=minHeapNode.getRBTNode();
                        }
                        if (printBuildingNode == null) {
                            str.append("(" + 0 + "," + 0 + "," + 0 + ")\n");
                        } else {
                            str.append("(").append(printBuildingNode.key.getBuildingNum()).append(",").append(printBuildingNode.key.getExecutionTime()).append(",").append(printBuildingNode.key.getTotalTime()).append(")\n");
                        }
                    }
                }

                days++;
                if((completedBuildingNum = minHeap.process(minHeapList, minHeap.heap[0], rbt))!=-1) str.append("(").append(completedBuildingNum).append(",").append(days).append(")\n");
                if(minHeap.isEmpty() && !sc.hasNextLine())
                    break;
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
