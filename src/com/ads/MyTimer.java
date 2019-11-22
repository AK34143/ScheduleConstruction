package com.ads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    static int counter=0;
    public static void main(String[] args){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(counter);
                counter++;//increments the counter
            }
        };

        Timer timer = new Timer();//create a new Timer

        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);

        if(counter==1){
            System.out.println("Hello");
        }
        /*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Main2 minHeap = new Main2(15);
        Timer timer = new Timer();

        timer.schedule( new TimerTask() {
            public void run() {
                // do your work
                System.out.println(counter);
                counter++;
            }
        }, 0, 60*1000);*/
        /*try {
            while (true) {
                System.out.println(new Date());
                Thread.sleep(5 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
