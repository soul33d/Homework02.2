#Task 02.2
Необходимо реализовать коллекцию, которая имеет фиксированный размер.

Размер задаётся:

по умолчанию 16
по указанию пользователя.
Размер коллекции после инициализации меняться не может.

Каждый новый элемент добавляется в конец. После того, как количество элементов достигло максимума - при добавлении нового элемента коллекция "сдвигается" влево.

1-й элемент удаляется, а каждый следующий смещается на один элемент влево.

Результатом выполнения задания должен быть репозиторий с коллекцией, классом, тестирующим данную коллекцию, описанием задания и README файлом с инструкцией по запуску приложения

##FixedSizeQueueDemo demonstrates all required functional

##FixedSizeQueue description:

`com.homelearning.FixedSizeQueue<E> implements java.util.Queue<E>`

`public FixedSizeQueue()` - creates the `Queue` with default size of 16 elements.

`public FixedSizeQueue(int maxSize)` - creates `FixedSizeQueue` with predefined `maxSize` if `maxSize > 0` else with `DEFAULT_SIZE`

`public boolean add(E e)` - adds `e` to the end of the queue if `queue.size() < maxSize`, else removes first element before adding to the end of queue.

`public boolean addAll(Collection<? extends E> c)` - adds every element from `Collection<? extends E> c` to the end of queue, if reached `maxSize` removes first element of queue before adding every element to the end of queue.

`public String toString()` - return String representation of the queue, if `isEmpty` returns `"Queue is empty."`

All other methods from the `java.util.Queue<E>` implemented with default behaviour for queue and was delegated from `java.util.LinkedList<E>`