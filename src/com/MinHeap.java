package com;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class MinHeap {
    /**
     * MinHeap implemented using Array
     */


    private static final int d= 2;
    public Building[] heap;
    private RedBlackTree.RBTProperties rbtProperties;
    public int heapSize;
    public BuildingProperties nullBuilding=new BuildingProperties(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);

    /**
     * This will initialize our heap with default size.
     * @param capacity
     */
    public MinHeap(int capacity){
        heapSize = 0;
        heap = new Building[capacity+1];
        Arrays.fill(heap, new Building(nullBuilding));

    }

    /**
     * This will check if the heap is empty or not
     * Complexity: O(1)
     * @return
     */
    public boolean isEmpty(){
        return heapSize==0;
    }

    /**
     * This will check if the heap is full or not
     * Complexity: O(1)
     * @return
     */
    public boolean isFull(){
        return heapSize == heap.length;
    }


    private int parent(int i){
        return (i-1)/d;
    }

    private int kthChild(int i,int k){
        return d*i  +k;
    }

    /**
     * This will insert new element in to heap
     * Complexity: O(log N)
     * As worst case scenario, we need to traverse till the root
     * @param heapx
     */
    public void insert(Building heapx){
        if(isFull())
            throw new NoSuchElementException("Heap is full, No space to insert new element");
        heap[heapSize++] = heapx;
        heapifyUp(heapSize-1);
    }

    /**
     * This will delete element at index
     * Complexity: O(log N)
     * @param index
     * @return
     */
    public int delete(int index){
        if(isEmpty())
            throw new NoSuchElementException("Heap is empty, No element to delete");
        int key = heap[index].getBuildingProperties().getBuildingNum();

        heap[index].setBuildingProperties(heap[heapSize -1].getBuildingProperties());
        heap[index].setProgress(heap[heapSize -1].getProgress());
        heap[index].setRBTProperties(heap[heapSize -1].getRBTProperties());
        heapSize--;
        heapifyDown(index);
        int lastIndex = heapSize;
        heap[lastIndex].setBuildingProperties(nullBuilding);
        heap[lastIndex].setProgress(0);
        heap[lastIndex].setRBTProperties(null);
        return key;
    }

    /**
     * This method used to maintain the heap property while inserting an element.
     * @param i
     */
    public void heapifyUp(int i) {
        Building temp = heap[i];
        while(i>0 && ((temp.getBuildingProperties().getExecutionTime() < heap[parent(i)].getBuildingProperties().getExecutionTime())
                || (temp.getBuildingProperties().getExecutionTime()==heap[parent(i)].getBuildingProperties().getExecutionTime() && temp.getBuildingProperties().getBuildingNum()<heap[parent(i)].getBuildingProperties().getBuildingNum()))){
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = temp;
    }

    /**
     * This method used to maintain the heap property while deleting an element.
     * @param i
     */
    public void heapifyDown(int i){
        int child;
        Building temp = new Building(heap[i].getBuildingProperties(),heap[i].getProgress(),heap[i].getRBTProperties());
        while(kthChild(i, 1) < heapSize){
            child = minChild(i);// Finding out the min of the children
            if(temp.getBuildingProperties().getExecutionTime() > heap[child].getBuildingProperties().getExecutionTime()
            || temp.getBuildingProperties().getExecutionTime()==heap[child].getBuildingProperties().getExecutionTime() && temp.getBuildingProperties().getBuildingNum()>heap[child].getBuildingProperties().getBuildingNum()){ // Comparing parent with the min of the children
                /** If execution time of the parent is more than that of min of the children
                 * Or If execution time of the parent is equal to that of min of the children, and building number of parent is equal more than that of min of the children
                 * place the minChild as parent and below place the parent in minChild's place*/
                heap[i].setBuildingProperties(heap[child].getBuildingProperties());
                heap[i].setProgress(heap[child].getProgress());
                heap[i].setRBTProperties(heap[child].getRBTProperties());
            /*}else if(){

                heap[i].setBuildingProperties(heap[child].getBuildingProperties());
                heap[i].setProgress(heap[child].getProgress());
                heap[i].setRBTProperties(heap[child].getRBTProperties());*/
            } else
                break;

            i = child;
        }
        heap[i].setBuildingProperties(temp.getBuildingProperties());
        heap[i].setProgress(temp.getProgress());
        heap[i].setRBTProperties(temp.getRBTProperties());
    }

    /**
     * Get the min of the children
     * @param i
     * @return
     */
    private int minChild(int i) {
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);
        int minChild=leftChild;
        if(heap[leftChild].getBuildingProperties().getExecutionTime()>heap[rightChild].getBuildingProperties().getExecutionTime()){
            minChild=rightChild;
        } else if(heap[leftChild].getBuildingProperties().getExecutionTime()==heap[rightChild].getBuildingProperties().getExecutionTime()){
            if(heap[leftChild].getBuildingProperties().getBuildingNum()>heap[rightChild].getBuildingProperties().getBuildingNum()){
                minChild=rightChild;
            }
        }
        return minChild;
    }

    /**
     * This method used to print all element of the heap
     */
    public void printHeap(){
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print("("+heap[i].getBuildingProperties().getBuildingNum()+","+heap[i].getBuildingProperties().getExecutionTime() +","+heap[i].getBuildingProperties().getTotalTime()+") ");
        System.out.println();
    }

    /**
     * This method returns the min element of the heap.
     * complexity: O(1)
     * @return
     */
    /*public Building findMin(){
        if(isEmpty())
            throw new NoSuchElementException("Heap is empty.");
        return heap[0];
    }*/

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
        // For input file
        String part = params[0].split("\\(")[1];
        output[0] = Integer.parseInt(part);
        output[1]=0;
        part = params[1].split("\\)")[0];
        output[2] = Integer.parseInt(part);
        // For hard coded input
        /*String part = params[0].trim();
        output[0] = Integer.parseInt(part);
        part = params[1].trim();
        output[1]=Integer.parseInt(part);
        part = params[2].trim();
        output[2] = Integer.parseInt(part);*/
        return output;
    }

    /**
     * This method constructs the building and returns building number if construction is completed
     * @param nextCommand
     * @param buildingList
     * @param building
     * @param rbt
     * @return
     */
    public int construct(String nextCommand, List<Building> buildingList,Building building, RedBlackTree rbt)
    {
        int completedBuildingNum=-1;
        int minTime = Math.min(building.getBuildingProperties().getTotalTime(), 5);// Find the min of totalTime and 5 so we can run our construction those many days
        if(heapSize>0 && building.getBuildingProperties().getExecutionTime()+1<=building.getBuildingProperties().getTotalTime() && building.getProgress()<minTime){
            building.getBuildingProperties().setExecutionTime(building.getBuildingProperties().getExecutionTime()+1);
            building.getRBTProperties().buildingProperties=building.getBuildingProperties();
            building.setProgress(building.getProgress()+1);
            if(building.getBuildingProperties().getExecutionTime()==building.getBuildingProperties().getTotalTime()){
                /** If the execution time and total time are same, it means the building construction is completed*/
                completedBuildingNum = building.getBuildingProperties().getBuildingNum();

                //if(nextCommand==null ||(nextCommand!=null && !nextCommand.contains("Print"))) {
                    rbt.delete(building.getRBTProperties());

                //}
                delete(0);
                /**After building construction is completed, we can add the buildings in the queue and heapify to get the building with least execution time*/
                if(!buildingList.isEmpty()){
                    for(int i=0;i<buildingList.size();i++) {
                        insert(buildingList.get(0));
                        buildingList.remove(0);
                    }
                }
            } else if(building.getProgress()==minTime){
                /**If the building is constructed for 5 days or total time, whichever is small, reset progress back to 0 and insert the buildings in the queue*/
                building.setProgress(0);
                heapifyDown(0);
                if(!buildingList.isEmpty()){
                    for(int i=0;i<buildingList.size();i++) {
                        insert(buildingList.get(0));
                        buildingList.remove(0);
                    }
                }
            }
        }
        return completedBuildingNum;
    }
}
