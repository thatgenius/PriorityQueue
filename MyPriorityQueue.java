import java.util.*;

public class MyPriorityQueue<T> implements Iterable<T> {

    private Object[] arr;
    private int head;
    private int tail;
    private Comparator<T> comp;
    public boolean isEmpty = true;

    public MyPriorityQueue() {
        arr = new Object[10];
    }

    public MyPriorityQueue(int capacity) {
        arr = new Object[capacity];
    }

    public MyPriorityQueue(int capacity, Comparator<T> comp) {
        arr = new Object[capacity];
        this.comp = comp;
    }

    public int length() {
        return tail - head + 1;
    }

    public boolean add(T element) {
        if (element == null) throw new NullPointerException();
        if (tail >= arr.length - 1) {
            if (worthIncreasing()) {
                increaseCapacity();
            }
            else {
                moveToStart();
            }
        }
        if (isEmpty) {
            arr[tail] = element;
            isEmpty = false;
        }
        else {
            arr[++tail] = element;
        }
        sort();
        return true;
    }

    private boolean worthIncreasing() {
        int appendage = (arr.length / 2 * 3 + 1) - arr.length;
        int existentUnused = arr.length - length();
        return existentUnused < appendage;
    }

    private boolean increaseCapacity() {
        Object[] arr2 = new Object[length() / 2 * 3 + 1];
        System.arraycopy(arr, head, arr2, 0, length());
        arr = arr2;
        return true;
    }

    private boolean moveToStart() {
        System.arraycopy(arr, head, arr, 0, length());
        tail = tail - head;
        head = 0;
        return true;
    }

    private void sort() {
        Arrays.sort((T[])arr, head, tail + 1, comp);
    }

    public boolean offer(T element) {
        return add(element);
    }

    public T peek() {
        return isEmpty ? null : (T)arr[head];
    }

    public T element() throws NoSuchElementException {
        T element = peek();
        if (element == null) {
            return element;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public T poll() {
        return isEmpty ? null : (tail == head ? getLast() : (T)arr[head++]);
    }

    public T remove() throws NoSuchElementException {
        T element = poll();
        if (element == null) {
            return (T)element;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    private T getLast() {
        int lastId = head;
        head = tail = 0;
        isEmpty = true;
        return (T)arr[lastId];
    }

    public boolean trimToSize() {
        if ((tail - head + 1) < arr.length) {
            Object[] arr2 = new Object[length()];
            System.arraycopy(arr, head, arr2, 0, length());
            arr = (T[])arr2;
            return true;
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = head;
            private int length = length();
            @Override
            public T next() {
                return (T)arr[current++];
            }

            @Override
            public boolean hasNext() {
                return current<length;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}