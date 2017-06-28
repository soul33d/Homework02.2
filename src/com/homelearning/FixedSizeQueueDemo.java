/* Vitaliy Ostapenko
 ДЗ 05.1
 Необходимо внести изменения в задание к модулю 1.2 (Очередь) таким образом, чтобы данный класс стал многопоточным
 (потоко-безопасным).*/
package com.homelearning;

import java.util.Queue;
import java.util.concurrent.*;

public class FixedSizeQueueDemo {
    public static void main(String[] args) {
        int iterations = 100_000;
        FixedSizeQueue<Integer> queue = new FixedSizeQueue<>(iterations);
        printQueue(queue);
        queue.add(999);
        queue.add(800000);
        printQueue(queue);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        int addCountPermits = 10;
        int removeCountPermits = 2; //must be positive value from 1 to addCountPermits
        Semaphore addSemaphore = new Semaphore(addCountPermits);
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
                if (i% addCountPermits == 0) {
                    removeSemaphore.release(9);
                }
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            for (int i = 1; i <= iterations / removeCountPermits; i++) {
                try {
                    removeSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.poll();
                if (i%(addCountPermits/removeCountPermits) == 0)addSemaphore.release(addCountPermits);
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
