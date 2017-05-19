package com.homelearning;

import java.util.*;

public class FixedSizeQueue<E> implements Queue<E> {
    private static final int DEFAULT_SIZE = 16;
    private Deque<E> queue;

    private int maxSize;

    public FixedSizeQueue() {
        queue = new LinkedList<>();
        maxSize = DEFAULT_SIZE;
    }

    public FixedSizeQueue(int size) {
        if (size > 0) maxSize = size;
        else maxSize = DEFAULT_SIZE;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

    @Override
    public boolean add(E e) {
        if (queue.size() < maxSize) return queue.add(e);
        else {
            queue.removeFirst();
            return queue.add(e);
        }
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) return false;
        for (E aC : c) add(aC);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean offer(E e) {
        return queue.offer(e);
    }

    @Override
    public E remove() {
        return queue.remove();
    }

    @Override
    public E poll() {
        return queue.poll();
    }

    @Override
    public E element() {
        return queue.element();
    }

    @Override
    public E peek() {
        return queue.peek();
    }

    @Override
    public String toString() {
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