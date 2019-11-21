package com.ads;

import java.io.*;
import java.util.*;

import static com.ads.MinHeap2.stringToParams;

public class Main {
    static int counter = 0;
    private Node[] nodes;

    /*public Main(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE;
    }*/

    public static void main(String[] args) throws IOException {
        // write your code here

        long start1 = System.currentTimeMillis();
        // start of function



        // end of function

        // ending time

        Main main = new Main();
        MinHeap2 minHeap = new MinHeap2(2000); //Initializing empty array for minHeap
        RedBlackTree2 rbt = new RedBlackTree2(); //
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
                new File("C:\\Me_Florida\\UF_courses\\ADS\\ADSProject\\ADS_ProgramminProject\\input_wip.txt");
        StringBuilder str = new StringBuilder();
        BufferedWriter writer = new BufferedWriter(new FileWriter("output_wip", false));
        writer.flush();



        Scanner sc = new Scanner(file);
        Scanner scCopy=new Scanner(file);
        if(scCopy.hasNextLine()) scCopy.nextLine();
        int completedBuildingNum;
        HeapNode processNode;
        while (sc.hasNextLine()) {
            //long start = System.currentTimeMillis();
            int commandTime =Integer.parseInt(sc.next().split(":")[0]);
            String currentCommand=sc.next();
            //System.out.println(currentCommand);
            int nextCommandTime =Integer.MAX_VALUE;
            if(sc.hasNextLine()) {
                String nextCommand = scCopy.nextLine();
                nextCommandTime = Integer.parseInt(nextCommand.split(":")[0]);
            }
            while(counter>=commandTime && counter<nextCommandTime) {
                //System.out.println("counter : " + counter);

                if (currentCommand.contains("Insert") && commandTime==counter) {
                    int[] params = stringToParams(currentCommand);
                    if(rbt.printBuilding(params[0])==null) {
                        Node node1 = new Node(params[0], params[1], params[2]);
                        HeapNode heapNode1 = new HeapNode(node1);
                        if(minHeap.heap[0].getTempProgress()>0)
                            processNode = minHeap.heap[0];
                        minHeap.insert(heapNode1);
                        RedBlackTree2.RBTNode rbtNode1 = new RedBlackTree2.RBTNode(new Node(node1.buildingNum, node1.executionTime, node1.totalTime));
                        rbt.insertRBT(rbtNode1);
                        heapNode1.setRBTNode(rbtNode1);
                    } else {
                        str.append("Building with buildingNum ").append(params[0]).append(" exists\n");
                        //writer.append(str);
                    }

                    //minHeap.printHeap();
                    //rbt.printLevelOrder();


                    //if((completedBuildingNum = minHeap.process(minHeap, rbt))!=-1) System.out.println("("+completedBuildingNum+","+(counter+1)+")");
                } else if(currentCommand.contains("PrintBuilding") && commandTime==counter){
                    //System.out.println(currentCommand);
                    if(currentCommand.contains(",")){
                        int[] params = stringToParams(currentCommand);
                        List<RedBlackTree2.RBTNode> rangeBuildings = rbt.printBuilding(params[0],params[2]);
                        if(rangeBuildings.size()==0){
                            str.append("(" + 0 + "," + 0 + "," + 0 + ")\n");
                        } else {
                            int i=0;
                            for(RedBlackTree2.RBTNode rbtNode: rangeBuildings){
                                str.append("(").append(rbtNode.key.getBuildingNum()).append(",").append(rbtNode.key.getExecutionTime()).append(",").append(rbtNode.key.getTotalTime()).append(")");
                                i++;
                                if(i!=rangeBuildings.size())  str.append(",");
                            }
                        }
                        str.append("\n");
                    } else {
                        String part = currentCommand.split("\\(")[1];
                        part = part.split("\\)")[0];
                        RedBlackTree2.RBTNode printBuildingNode = rbt.printBuilding(Integer.parseInt(part));
                        if (printBuildingNode == null) {
                            str.append("(" + 0 + "," + 0 + "," + 0 + ")\n");
                        } else {
                            str.append("(").append(printBuildingNode.key.getBuildingNum()).append(",").append(printBuildingNode.key.getExecutionTime()).append(",").append(printBuildingNode.key.getTotalTime()).append(")\n");
                        }
                        //str.append("\n");
                    }
                    //if((completedBuildingNum = minHeap.process(minHeap, rbt))!=-1) System.out.println("("+completedBuildingNum+","+(counter+1)+")");
                } //else {
                    //if((completedBuildingNum = minHeap.process(minHeap, rbt))!=-1) str.append("(").append(completedBuildingNum).append(",").append(counter+1).append(")\n");

                //}

                counter++;
                if((completedBuildingNum = minHeap.process(counter, minHeap, rbt))!=-1) str.append("(").append(completedBuildingNum).append(",").append(counter).append(")\n");
                /*if(!minHeap.isEmpty() && currentCommand.contains("Insert")){
                    minHeap.heapifyUp(minHeap.heapSize-1);;
                }*/
                if(minHeap.isEmpty() && !sc.hasNextLine())
                    break;
            }
            //long end = System.currentTimeMillis();
//            if(end - start>0)
//                System.out.println(currentCommand+" takes " + (end - start) + "ms");
            //st=nextSt;
            //if(minHeap.heap[0].getBuildingNum()!=9999) break;
        }
        /*str="Completion day: "+counter;
        writer.append(str);*/
        writer.append(str);
        //System.out.println(str);
        writer.close();
        long end1 = System.currentTimeMillis();
        System.out.println("Application on the whole takes " + (end1 - start1) + "ms");
        System.gc();
        Runtime rt = Runtime.getRuntime();
        long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
//        System.out.println( "memory usage " + usedMB+"MB");
        //System.out.println(minHeap.heap[0].getBuildingNum());
        //while (counter > 0){
        //int commandTime = ;
        /*while ((st) != null) {
            int commandTime =Integer.parseInt(st.split(":")[0]);
            String nextSt=br.readLine();
            int nextCommandTime =commandTime+1;
            if(nextSt!=null){
                nextCommandTime = Integer.parseInt(nextSt.split(":")[0]);
            }
            while(counter>=commandTime && counter<nextCommandTime) {
                System.out.println("counter : " + counter);
                if (st.contains("Insert") && commandTime==counter) {
                    int[] params = stringToParams(st);
                    Node node1 = new Node(params[0], params[1], params[2]);
                    minHeap.insert(node1);
                    RedBlackTree2.RBTNode rbtNode1 = new RedBlackTree2.RBTNode(node1);
                    rbt.insertRBT(rbtNode1);
                    node1.setRBTNode(rbtNode1);
                    minHeap.printHeap();
                    rbt.printLevelOrder();
                    minHeap.process(minHeap, rbt);
                } else {
                    minHeap.process(minHeap,rbt);
                }
                counter++;
            }
            //System.out.println(st);
            st=nextSt;
        }*/

    //}
        /*File file = new File("C:\\Me_Florida\\UF_courses\\ADS\\ADSProject\\ADS_ProgramminProject\\input.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");
        System.out.println(sc.next());*/


        /** Hard coded insert samples and delete
         * */
        /*int[] params = stringToParams("1,10,5");
        Node node1 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node1);
        RedBlackTree2.RBTNode rbtNode1 = new RedBlackTree2.RBTNode(node1);

        rbt.insertRBT(rbtNode1);
        node1.setRBTNode(rbtNode1);
//        System.out.print("After inserting 1,10,5");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("4,6,10");
        Node node2 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node2);
        RedBlackTree2.RBTNode rbtNode2 = new RedBlackTree2.RBTNode(node2);
        rbt.insertRBT(rbtNode2);
        node2.setRBTNode(rbtNode2);
//        System.out.print("After inserting 4,6,10");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("9,6,15");
        Node node3 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node3);
        RedBlackTree2.RBTNode rbtNode3 = new RedBlackTree2.RBTNode(node3);
        rbt.insertRBT(rbtNode3);
        node3.setRBTNode(rbtNode3);
//        System.out.print("After inserting 9,6,15");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("6,10,4");
        Node node4 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node4);
        RedBlackTree2.RBTNode rbtNode4 = new RedBlackTree2.RBTNode(node4);
        rbt.insertRBT(rbtNode4);
        node4.setRBTNode(rbtNode4);
//        System.out.print("After inserting 6,10,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("5,5,4");
        Node node5 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node5);
        RedBlackTree2.RBTNode rbtNode5 = new RedBlackTree2.RBTNode(node5);
        rbt.insertRBT(rbtNode5);
        node5.setRBTNode(rbtNode5);
//        System.out.print("After inserting 5,5,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("28,1,4");
        Node node6 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node6);
        RedBlackTree2.RBTNode rbtNode6 = new RedBlackTree2.RBTNode(node6);
        rbt.insertRBT(rbtNode6);
        node6.setRBTNode(rbtNode6);
//        System.out.print("After inserting 28,1,5");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("13,17,4");
        Node node7 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node7);
        RedBlackTree2.RBTNode rbtNode7 = new RedBlackTree2.RBTNode(node7);
        rbt.insertRBT(rbtNode7);
        node7.setRBTNode(rbtNode7);
//        System.out.print("After inserting 13,17,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("7,3,4");
        Node node8 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node8);
        RedBlackTree2.RBTNode rbtNode8 = new RedBlackTree2.RBTNode(node8);
        rbt.insertRBT(rbtNode8);
        node8.setRBTNode(rbtNode8);
//        System.out.print("After inserting 7,3,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("17,11,4");
        Node node9 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node9);
        RedBlackTree2.RBTNode rbtNode9 = new RedBlackTree2.RBTNode(node9);
        rbt.insertRBT(rbtNode9);
        node9.setRBTNode(rbtNode9);
//        System.out.print("After inserting 17,11,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("15,9,4");
        Node node10 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node10);
        RedBlackTree2.RBTNode rbtNode10 = new RedBlackTree2.RBTNode(node10);
        rbt.insertRBT(rbtNode10);
        node10.setRBTNode(rbtNode10);
//        System.out.print("After inserting 15,9,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("3,9,4");
        Node node11 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node11);
        RedBlackTree2.RBTNode rbtNode11 = new RedBlackTree2.RBTNode(node11);
        rbt.insertRBT(rbtNode11);
        node11.setRBTNode(rbtNode11);
//        System.out.print("After inserting 3,9,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("8,10,4");
        Node node12 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node12);
        RedBlackTree2.RBTNode rbtNode12 = new RedBlackTree2.RBTNode(node12);
        rbt.insertRBT(rbtNode12);
        node12.setRBTNode(rbtNode12);
//        System.out.print("After inserting 8,10,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("11,10,4");
        Node node13 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node13);
        RedBlackTree2.RBTNode rbtNode13 = new RedBlackTree2.RBTNode(node13);
        rbt.insertRBT(rbtNode13);
        node13.setRBTNode(rbtNode13);
//        System.out.print("After inserting 11,10,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
        params = stringToParams("2,23,4");
        Node node14 = new Node(params[0],params[1],params[2]);
        minHeap.insert(node14);
        RedBlackTree2.RBTNode rbtNode14 = new RedBlackTree2.RBTNode(node14);
        rbt.insertRBT(rbtNode14);
        node14.setRBTNode(rbtNode14);*/
//        System.out.print("After inserting 2,23,4");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
//        rbt.printLevelOrder();
        //
//        minHeap.process(minHeap,rbt);
//        System.out.println("After deleting");
//        System.out.print("Heap is : ");
//        minHeap.printHeap();
//        System.out.print("RBT is : ");
        //rbt.printLevelOrder();

        /**
         * Need for PrintBuilding
         */
        /*RedBlackTree2.RBTNode printBuildingNode = rbt.printBuilding(10);
        if(printBuildingNode==null)
            System.out.println("(" + 0 + "," + 0 + "," + 0 + ")");
        else
            System.out.println("("+printBuildingNode.key.getBuildingNum()+","+printBuildingNode.key.getExecutionTime()+","+printBuildingNode.key.getTotalTime()+")");
        List<RedBlackTree2.RBTNode> rangeBuildings = rbt.printBuilding(5,10);
        if(rangeBuildings.size()==0){
            System.out.println("(" + 0 + "," + 0 + "," + 0 + ")");
        } else {
            int i=0;
            for(RedBlackTree2.RBTNode rbtNode: rangeBuildings){
                System.out.print("(" + rbtNode.key.getBuildingNum() + "," + rbtNode.key.getExecutionTime() + "," + rbtNode.key.getTotalTime() + ")");
                i++;
                if(i!=rangeBuildings.size())  System.out.print(",");
            }
        }*/


    }

}
