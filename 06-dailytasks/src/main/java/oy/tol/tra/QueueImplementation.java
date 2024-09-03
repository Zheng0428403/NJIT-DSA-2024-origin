package oy.tol.tra;

import java.util.Arrays;

public class QueueImplementation<E> implements QueueInterface<E> {
    
    private int capacity;
    private Object[] queueArray;
    private int size;
    private int head;
    private int tail;
    private static int DEFAULT_QUEUE_SIZE = 10;

    public QueueImplementation(int initialCapacity) {
        this.capacity = initialCapacity;
        this.queueArray = new Object[initialCapacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }
    public QueueImplementation() throws QueueAllocationException {
        this(DEFAULT_QUEUE_SIZE);
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (size == capacity) {
            reallocate();
        }
        queueArray[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    private void reallocate() throws QueueAllocationException {
        int newCapacity = capacity * 2;
        Object[] newQueue = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queueArray[(head + i) % capacity];
        }
        head = 0;
        tail = size;
        capacity = newCapacity;
        queueArray = newQueue;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        @SuppressWarnings("unchecked")
        E element = (E) queueArray[head];
        queueArray[head] = null;
        head = (head + 1) % capacity;
        size--;
        return element;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        @SuppressWarnings("unchecked")
        E element = (E) queueArray[head];
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(queueArray, null);
        size = 0;
        head = 0;
        tail = 0;
    }

    //@Override
    public String remove() {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) queueArray[(head + i) % capacity];
            builder.append(element);
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}