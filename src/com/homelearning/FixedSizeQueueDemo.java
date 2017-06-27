/* Vitaliy Ostapenko
 ДЗ 03.1
 Необходимо проанализировать коллекции, созданные для ДЗ по модулю 1.2 и выявить потенциально опасные моменты,
 в которых могут произойти сбои. Создать кастомные исключения и бросать в случае ошибки.*/
package com.homelearning;

import java.util.Queue;
import java.util.concurrent.*;

public class FixedSizeQueueDemo {
    public static void main(String[] args) {
        int iterations = 1_000_000;
        FixedSizeQueue<Integer> queue = new FixedSizeQueue<>(iterations);
        printQueue(queue);
        queue.add(999);
        queue.add(800000);
        printQueue(queue);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Semaphore addSemaphore = new Semaphore(1);
        Semaphore removeSemaphore = new Semaphore(0);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        executorService.execute(() -> {
            for (int i = 0; i < iterations; i++) {
                try {
                    addSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(i);
                if (i%2 == 0) {
                    addSemaphore.release();
                    removeSemaphore.release();
                }
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            for (int i = 0; i < iterations / 2; i++) {
                try {
                    removeSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.poll();
                addSemaphore.release();
            }
            countDownLatch.countDown();
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printQueue(queue);
        executorService.shutdown();
    }

    private static void printQueue(Queue<Integer> queue) {
        System.out.println(queue);
        System.out.println("Current size is " + queue.size());
        System.out.println();
    }
}
