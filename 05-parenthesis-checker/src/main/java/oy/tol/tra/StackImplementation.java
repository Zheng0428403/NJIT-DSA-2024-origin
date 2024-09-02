package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
import java.util.*;

public class StackImplementation<E> implements StackInterface<E> {

    private static final int DEFAULT_STACK_SIZE = 0;
   private Object[] itemArray;
    private int capacity;
    private int currentIndex = -1;
    private int size;

    public StackImplementation() throws StackAllocationException {
        this(DEFAULT_STACK_SIZE);
    }

    public StackImplementation(int capacity) throws StackAllocationException {
        if (capacity < 2) {
            throw new StackAllocationException("Stack capacity must be at least 2.");
        }
        this.capacity = capacity;
        this.itemArray = new Object[capacity];
        this.size = 0;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot push null element into the stack");
        }
        if (size == capacity) {
            resize();
        }
        itemArray[++currentIndex] = element;
        size++;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        itemArray = Arrays.copyOf(itemArray, newCapacity);
        capacity = newCapacity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Stack is empty");
        }
        E item = (E) itemArray[currentIndex];
        itemArray[currentIndex] = null;
        currentIndex--;
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Stack is empty");
        }
        return (E) itemArray[currentIndex];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(itemArray, null);
        currentIndex = -1;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == -1;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int index = 0; index <= currentIndex; index++) {
            joiner.add(itemArray[index].toString());
        }
        return joiner.toString();
    }
}//2