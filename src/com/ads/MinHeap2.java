package com.ads;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.ads.Main2.triple;

public class MinHeap2 {



    /**
     * MinHeap implemented using Array
     */

        private static final int d= 2;
        private int[][] heap;
        private int heapSize;

        /**
         * This will initialize our heap with default size.
         */
        public MinHeap2(int capacity){
            heapSize = 0;
            heap = new int[capacity+1][2];
            Arrays.fill(heap, new int[]{-1,-1});

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
        public void insert(int[] x){
            if(isFull())
                throw new NoSuchElementException("Heap is full, No space to insert new element");
            heap[heapSize++] = x;
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
            int key = heap[x][0];
            heap[x] = heap[heapSize -1];
            heapSize--;
            heapifyDown(x);
            return key;
        }

        /**
         *  This method used to maintain the heap property while inserting an element.
         *
         */
        private void heapifyUp(int i) {
            int[] temp = heap[i];
            while(i>0 && temp[1] < heap[parent(i)][1]){
                heap[i] = heap[parent(i)];
                i = parent(i);
            }
            heap[i] = temp;
        }

        /**
         *  This method used to maintain the heap property while deleting an element.
         *
         */
        private void heapifyDown(int i){
            int child;
            int[] temp = heap[i];
            while(kthChild(i, 1) < heapSize){
                child = minChild(i);
                if(temp[1] > heap[child][1]){
                    heap[i] = heap[child];
                }else
                    break;

                i = child;
            }
            heap[i] = temp;
        }

        private int minChild(int i) {
            int leftChild = kthChild(i, 1);
            int rightChild = kthChild(i, 2);

            return heap[leftChild][1]<heap[rightChild][1]?leftChild:rightChild;
        }

        /**
         *  This method used to print all element of the heap
         *
         */
        public void printHeap()
        {
            System.out.print("\nHeap = ");
            for (int i = 0; i < heapSize; i++)
                System.out.print("("+heap[i][0]+","+heap[i][1] +","+heap[i][2]+") ");
            System.out.println();
        }
        /**
         *  This method returns the min element of the heap.
         *  complexity: O(1)
         */
        public int[] findMin(){
            if(isEmpty())
                throw new NoSuchElementException("Heap is empty.");
            return heap[0];
        }

    public static int[] stringToParams(String input) {
        input = input.trim();
        //input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }


        String[] params = input.split(",");
        int[] output = new int[3];
      /*  String part = params[0].split("\\(")[1];
        output[0] = Integer.parseInt(part);
        output[1]=0;
        part = params[1].split("\\)")[0];
        output[2] = Integer.parseInt(part);*/
        String part = params[0].trim();
        output[0] = Integer.parseInt(part);
        part = params[1].trim();
        output[1]=Integer.parseInt(part);
        part = params[2].trim();
        output[2] = Integer.parseInt(part);
        /*for(int index = 0; index < params.length; index++) {
            if(index==1){
                output[index]=0;
            }else {
                String part = params[index].trim();
                output[index] = Integer.parseInt(part);
            }
        }*/
        return output;
    }

        public static void main(String[] args){
            MinHeap2 minHeap = new MinHeap2(10);
            int[] params = stringToParams("1,5,25");
            minHeap.insert(params);
            minHeap.printHeap();
            params = stringToParams("4,6,10");
            minHeap.insert(params);
            minHeap.printHeap();
            params = stringToParams("9,3,15");
            minHeap.insert(params);
            minHeap.printHeap();
            params = stringToParams("10,7,3");
            minHeap.insert(params);
            minHeap.printHeap();
             params = stringToParams("7,10,4");
            minHeap.insert(params);
            minHeap.printHeap();
            params = stringToParams("5,10,4");
            minHeap.insert(params);
            minHeap.printHeap();
            params = stringToParams("6,10,4");
            minHeap.insert(params);
            minHeap.printHeap();
            minHeap.delete(0);
            minHeap.printHeap();
        }

}
