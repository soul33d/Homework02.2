/* Vitaliy Ostapenko
 ДЗ 03.1
 Необходимо проанализировать коллекции, созданные для ДЗ по модулю 1.2 и выявить потенциально опасные моменты,
 в которых могут произойти сбои. Создать кастомные исключения и бросать в случае ошибки.*/
package com.homelearning;

import java.util.Queue;

public class FixedSizeQueueDemo {
    public static void main(String[] args) {
        FixedSizeQueue<Integer> queue = new FixedSizeQueue<>(10);
        printQueue(queue);
        queue.add(999);
        queue.add(800000);
        printQueue(queue);
        for (int i = 0; i < 16; i++) {
            System.out.println("Adding " + i + " to queue.");
            queue.add(i);
            printQueue(queue);
        }
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            System.out.print("Removing ");
            System.out.println(queue.poll());
            printQueue(queue);
        }
    }

    private static void printQueue(Queue<Integer> queue) {
        System.out.println(queue);
        System.out.println("Current size is " + queue.size());
        System.out.println();
    }
}
