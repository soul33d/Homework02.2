/* Vitaliy Ostapenko
 ДЗ 03.1
 Необходимо проанализировать коллекции, созданные для ДЗ по модулю 1.2 и выявить потенциально опасные моменты,
 в которых могут произойти сбои. Создать кастомные исключения и бросать в случае ошибки.*/
package com.homelearning;

import java.util.Queue;
import java.util.concurrent.*;

public class FixedSizeQueueDemo {
    public static void main(String[] args) {
        int iterations = 10_000;
        FixedSizeQueue<Integer> queue = new FixedSizeQueue<>(iterations);
        printQueue(queue);
        queue.add(999);
        queue.add(800000);
        printQueue(queue);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Semaphore addSemaphore = new Semaphore(10);
        Semaphore removeSemaphore = new Semaphore(0);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        executorService.execute(() -> {
            for (int i = 1; i <= iterations; i++) {
                try {
                    addSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(i);
                if (i%10 == 0) {
                    removeSemaphore.release(5);
                }
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            for (int i = 1; i <= iterations / 2; i++) {
                try {
                    removeSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.poll();
                if (i%5 == 0)addSemaphore.release(10);
            }
            countDownLatch.countDown();
        });

        /*additional task daemon queue*/
        ThreadPoolExecutor daemonPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        daemonPoolExecutor.setThreadFactory(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        daemonPoolExecutor.execute(() -> {
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printQueue(queue);
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printQueue(queue);
        executorService.shutdown();
        daemonPoolExecutor.shutdown();
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    private static void printQueue(Queue<Integer> queue) {
        synchronized (queue) {
            System.out.println(queue);
            System.out.println("Current size is " + queue.size());
            System.out.println();
        }
    }
}
