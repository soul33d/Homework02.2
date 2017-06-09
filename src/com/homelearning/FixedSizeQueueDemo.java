/* Vitaliy Ostapenko
Необходимо реализовать коллекцию, которая имеет фиксированный размер.

Размер задаётся:

по умолчанию 16
по указанию пользователя.
Размер коллекции после инициализации меняться не может.

Каждый новый элемент добавляется в конец. После того, как количество элементов достигло максимума - при добавлении нового элемента коллекция "сдвигается" влево.

1-й элемент удаляется, а каждый следующий смещается на один элемент влево.

Результатом выполнения задания должен быть репозиторий с коллекцией, классом, тестирующим данную коллекцию, описанием задания и README файлом с инструкцией по запуску приложения*/
package com.homelearning;

import java.util.Queue;

public class FixedSizeQueueDemo {
    public static void main(String[] args) {
        FixedSizeQueue<Integer> queue = new FixedSizeQueue<>(-10);
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
