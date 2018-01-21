package com.hzgc.collect.expand.processer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
        for (int i = 0; i < 100; i++) {
            queue.offer("test");
        }
        for (int j = 0; j < 1001; j++) {
            try {
                queue.take();
                queue.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
