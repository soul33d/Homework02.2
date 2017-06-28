package com.homelearning;

import java.util.*;
import java.util.stream.Stream;

/** Synchronized (thread-safe) queue.
 * It is imperative that the user manually synchronize on the returned
 * queue when traversing it via {@link Iterator}, {@link Spliterator}
 * or {@link Stream}:
 * <pre>
 *  FixedSizeQueue c = new FixedSizeQueue();
 *     ...
 *  synchronized (c) {
 *      Iterator i = c.iterator(); // Must be in the synchronized block
 *      while (i.hasNext())
 *         foo(i.next());
 *  }
 * </pre>
 * Failure to follow this advice may result in non-deterministic behavior.*/
public class FixedSizeQueue<E> implements Queue<E> {
    private static final int DEFAULT_SIZE = 16;
    private Queue<E> queue;

    private final int maxSize;

    public FixedSizeQueue() {
        this(DEFAULT_SIZE);
    }

    /**
     * @param maxSize mast be positive number.
     * @throws ZeroQueueMaxSizeException if maxSize equals 0.
     * @throws NegativeQueueMaxSizeException if maxSize is negative.
     * */
    public FixedSizeQueue(int maxSize) {
        if (maxSize == 0) throw new ZeroQueueMaxSizeException(maxSize);
        if (maxSize < 0) throw new NegativeQueueMaxSizeException(maxSize);
        this.maxSize = maxSize;
        queue = new LinkedList<>();
    }

    @Override
    public synchronized int size() {
        return queue.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return queue.contains(o);
    }

    /**Must be manually synchronized*/
    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    @Override
    public synchronized Object[] toArray() {
        return queue.toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public synchronized  <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

    /**@param e not null*/
    @Override
    public synchronized boolean add(@SuppressWarnings("ConstantConditions") E e){
        if (e == null) throw new NullPointerException("null element is not permitted.");
        if (queue.size() == maxSize) queue.poll();
        return queue.add(e);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public synchronized boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public synchronized boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) return false;
        for (E aC : c) add(aC);
        return true;
    }

    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public synchronized boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public synchronized void clear() {
        queue.clear();
    }

    @Override
    public synchronized boolean offer(E e) {
        return add(e);
    }

    @Override
    public synchronized E remove() {
        return queue.remove();
    }

    @Override
    public synchronized E poll() {
        return queue.poll();
    }

    @Override
    public synchronized E element() {
        return queue.element();
    }

    @Override
    public synchronized E peek() {
        return queue.peek();
    }

    @Override
    public synchronized String toString() {
        if (isEmpty()) return "Queue is empty.";
        StringBuilder sb = new StringBuilder("[");
        for (E e : this) {
            sb.append(e).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()).append("]");
        return sb.toString();
    }

    public int getMaxSize() {
        return maxSize;
    }
}