package application;

class IyadHeap<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] heap;
    private int size;

    public IyadHeap() {
        this.heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void add(T item) {
        if (size == heap.length - 1) {
            resizeHeap();
        }
        size++;
        int index = size;
        heap[index] = item;
        Upshift(index);
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = heap[1];
        heap[1] = heap[size];
        heap[size] = null;
        size--;
        Downshift(1);
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeHeap() {
        int newSize = heap.length * 2;
        T[] newHeap = (T[]) new Comparable[newSize];
        System.arraycopy(heap, 1, newHeap, 1, size);
        heap = newHeap;
    }

    private void Upshift(int index) {
        while (index > 1 && heap[index].compareTo(heap[index / 2]) < 0) {
            swap(index, index / 2);
            index = index / 2;
        }
    }

    private void Downshift(int index) {
        int smallestChild;
        while (2 * index <= size) {
            if (2 * index == size || heap[2 * index].compareTo(heap[2 * index + 1]) < 0) {
                smallestChild = 2 * index;
            } else {
                smallestChild = 2 * index + 1;
            }
            if (heap[index].compareTo(heap[smallestChild]) > 0) {
                swap(index, smallestChild);
            } else {
                break;
            }
            index = smallestChild;
        }
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
