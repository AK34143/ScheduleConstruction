package com.ads;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static int counter = 0;

    /*public Main(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE;
    }*/

    public static void main(String[] args) {
	// write your code here
        //For Global Time schedule
        /*TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(counter);
                counter++;//increments the counter
            }
        };

        Timer timer = new Timer();//create a new Timer

        timer.scheduleAtFixedRate(timerTask, 30, 3000);*///this line starts the timer at the same time its executed

        System.out.println("The Min Heap is ");
        // minHeap = new Main(15);
        /*minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);
        minHeap.minHeap();

        minHeap.print();
        System.out.println("The Min val is " + minHeap.remove());*/
    }
}
