package com.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    private int[][] Heap;
    private int size;
    private int maxsize;
    private static int triple=2;

    private static final int FRONT = 1;

    public Main2(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1][this.triple + 1];
        Heap[0][0] = Integer.MIN_VALUE;
    }

    // Function to return the position of
    // the parent for the node currently
    // at pos
    private int parent(int pos)
    {
        return pos / 2;
    }

    // Function to return the position of the
    // left child for the node currently at pos
    private int leftChild(int pos)
    {
        return (2 * pos);
    }

    // Function to return the position of
    // the right child for the node currently
    // at pos
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    // Function that returns true if the passed
    // node is a leaf node
    private boolean isLeaf(int pos)
    {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    // Function to swap two nodes of the heap
    private void swap(int fpos, int spos)
    {
        int[] tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // Function to heapify the node at pos
    private void minHeapify(int pos)
    {

        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(pos)) {
            if (Heap[pos][0] > Heap[leftChild(pos)][0]
                    || Heap[pos][0] > Heap[rightChild(pos)][0]) {

                // Swap with the left child and heapify
                // the left child
                if (Heap[leftChild(pos)][0] < Heap[rightChild(pos)][0]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    // Function to insert a node into the heap
    public void insert(int[] element)
    {
        if (size >= maxsize) {
            return;
        }
        Heap[++size] = element;
        int current = size;

        while (Heap[current][0] < Heap[parent(current)][0]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Function to print the contents of the heap
    public void print()
    {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(" PARENT : (" + Heap[i][0] +","+ Heap[i][1]+","+ Heap[i][2]+")"
                    + " LEFT CHILD : (" + Heap[2 * i][0] +","+Heap[2 * i][1]+","+Heap[2 * i][2]+")"
                    + " RIGHT CHILD : (" + Heap[2 * i + 1][0]+","+Heap[2 * i + 1][1]+","+Heap[2 * i + 1][2]+")");
            System.out.println();
        }
    }

    // Function to build the min heap using
    // the minHeapify
    public void minHeap()
    {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    // Function to remove and return the minimum
    // element from the heap
    /*public int remove()
    {
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
        return popped;
    }*/

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static int[] stringToParams(String input) {
        input = input.trim();
        //input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] params = input.split(",");
        int[] output = new int[triple+1];
        String part = params[0].trim();
        output[0] = Integer.parseInt(part);
        output[1]=0;
        part = params[1].trim();
        output[2] = Integer.parseInt(part);
        /*for(int index = 0; index < params.length+1; index++) {
            if(index==1){
                output[index]=0;
            }else {
                String part = params[index].trim();
                output[index] = Integer.parseInt(part);
            }
        }*/
        return output;
    }

    // Driver code
    public static void main(String[] arg) throws IOException
    {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Main2 minHeap = new Main2(15);
        while ((line = in.readLine()) != null) {
            int[] params = stringToParams(line);
            minHeap.insert(params);
            minHeap.print();
            //int[] ret = new RevealCards().deckRevealedIncreasing(deck);

            //String out = integerArrayToString(ret);

            //System.out.print(out);
        }
        //System.out.println("The Min Heap is ");

        /*minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);*/



       // System.out.println("The Min val is " + minHeap.remove());
    }
}
