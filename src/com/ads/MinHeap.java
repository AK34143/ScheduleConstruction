package com.ads;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class MinHeap {
    /**
     * MinHeap implemented using Array
     */


        private static final int d= 2;
        public HeapNode[] heap;
        private RedBlackTree.RBTNode node;
        public int heapSize;
        public Node nullNode=new Node(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);

        /**
         * This will initialize our heap with default size.
         */
        public MinHeap(int capacity){
            heapSize = 0;
            heap = new HeapNode[capacity+1];
            Arrays.fill(heap, new HeapNode(nullNode));

        }

        /**
         *  This will check if the heap is empty or not
         *  Complexity: O(1)
         */
        public boolean isEmpty(){
            return heapSize==0;
        }

        /**
         *  This will check if the heap is full or not
         *  Complexity: O(1)
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
         *  This will insert new element in to heap
         *  Complexity: O(log N)
         *  As worst case scenario, we need to traverse till the root
         */
        public void insert(HeapNode heapx){
            //Node heapx= new Node(x[0],x[1],x[2]);
            if(isFull())
                throw new NoSuchElementException("Heap is full, No space to insert new element");
            heap[heapSize++] = heapx;
            heapifyUp(heapSize-1);
        }

        /**
         *  This will delete element at index x
         *  Complexity: O(log N)
         *
         */
        public int delete(int x){
            if(isEmpty())
                throw new NoSuchElementException("Heap is empty, No element to delete");
            int key = heap[x].getNode().getBuildingNum();

            heap[x].setNode(heap[heapSize -1].getNode());
            heap[x].setTempProgress(heap[heapSize -1].getTempProgress());
            heap[x].setRBTNode(heap[heapSize -1].getRBTNode());
            heapSize--;
            heapifyDown(x);
            int lastIndex = heapSize;
            heap[lastIndex].setNode(nullNode);
            heap[lastIndex].setTempProgress(0);
            heap[lastIndex].setRBTNode(null);
            return key;
        }

        /**
         *  This method used to maintain the heap property while inserting an element.
         *
         */
        public void heapifyUp(int i) {
            HeapNode temp = heap[i];
            while(i>0 && ((temp.getNode().getExecutionTime() < heap[parent(i)].getNode().getExecutionTime())
                    || (temp.getNode().getExecutionTime()==heap[parent(i)].getNode().getExecutionTime() && temp.getNode().getBuildingNum()<heap[parent(i)].getNode().getBuildingNum()))){
                heap[i] = heap[parent(i)];
                i = parent(i);
            }
            heap[i] = temp;
        }

        /**
         *  This method used to maintain the heap property while deleting an element.
         *
         */
        public void heapifyDown(int i){
            int child;
            HeapNode temp = new HeapNode(heap[i].getNode(),heap[i].getTempProgress(),heap[i].getRBTNode());
            while(kthChild(i, 1) < heapSize){
                child = minChild(i);// Finding out the min of the children
                if(temp.getNode().getExecutionTime() > heap[child].getNode().getExecutionTime()){ // Comparing parent with the min of the children
                    heap[i].setNode(heap[child].getNode()); // If parent is more than min of the children, place the minChild as parent and below place the parent in minChild's place
                    heap[i].setTempProgress(heap[child].getTempProgress()); // If parent is more than min of the children, place the minChild as parent and below place the parent in minChild's place
                    heap[i].setRBTNode(heap[child].getRBTNode()); // If parent is more than min of the children, place the minChild as parent and below place the parent in minChild's place
                }else if(temp.getNode().getExecutionTime()==heap[child].getNode().getExecutionTime() && temp.getNode().getBuildingNum()>heap[child].getNode().getBuildingNum()){
                    heap[i].setNode(heap[child].getNode());
                    heap[i].setTempProgress(heap[child].getTempProgress());
                    heap[i].setRBTNode(heap[child].getRBTNode());
                } else
                    break;

                i = child;
            }
            heap[i].setNode(temp.getNode());
            heap[i].setTempProgress(temp.getTempProgress());
            heap[i].setRBTNode(temp.getRBTNode());
        }

        private int minChild(int i) {
            int leftChild = kthChild(i, 1);
            int rightChild = kthChild(i, 2);
            int minChild=leftChild;
            if(heap[leftChild].getNode().getExecutionTime()>heap[rightChild].getNode().getExecutionTime()){
                minChild=rightChild;
            } else if(heap[leftChild].getNode().getExecutionTime()==heap[rightChild].getNode().getExecutionTime()){
                if(heap[leftChild].getNode().getBuildingNum()>heap[rightChild].getNode().getBuildingNum()){
                    minChild=rightChild;
                }
            }
            return minChild;
        }

        /**
         *  This method used to print all element of the heap
         *
         */
        public void printHeap()
        {
            System.out.print("\nHeap = ");
            for (int i = 0; i < heapSize; i++)
                System.out.print("("+heap[i].getNode().getBuildingNum()+","+heap[i].getNode().getExecutionTime() +","+heap[i].getNode().getTotalTime()+") ");
            System.out.println();
        }
        /**
         *  This method returns the min element of the heap.
         *  complexity: O(1)
         */
        public HeapNode findMin(){
            if(isEmpty())
                throw new NoSuchElementException("Heap is empty.");
            return heap[0];
        }

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

    public int process(List<HeapNode> minHeapList,HeapNode processNode, RedBlackTree rbt)
    {
        int completedBuildingNum=-1;
        int minTime = Math.min(processNode.getNode().getTotalTime(), 5);
        if(heapSize>0 && processNode.getNode().getExecutionTime()+1<=processNode.getNode().getTotalTime() && processNode.getTempProgress()<minTime){
            processNode.getNode().setExecutionTime(processNode.getNode().getExecutionTime()+1);
            processNode.getRBTNode().key=processNode.getNode();
            processNode.setTempProgress(processNode.getTempProgress()+1);
            if(processNode.getNode().getExecutionTime()==processNode.getNode().getTotalTime()){
                completedBuildingNum = processNode.getNode().getBuildingNum();
                rbt.delete(processNode.getRBTNode());
                delete(0);
                if(!minHeapList.isEmpty()){
                    for(int i=0;i<minHeapList.size();i++) {
                        insert(minHeapList.get(0));
                        minHeapList.remove(0);
                    }
                }
            } else if(processNode.getTempProgress()==minTime){
                processNode.setTempProgress(0);
                heapifyDown(0);
                if(!minHeapList.isEmpty()){
                    for(int i=0;i<minHeapList.size();i++) {
                        insert(minHeapList.get(0));
                        minHeapList.remove(0);
                    }
                }
            }
        }
        return completedBuildingNum;
    }
}
