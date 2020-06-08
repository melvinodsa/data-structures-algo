package melvin.datastructures.heap.maxheap;

public class MaxHeap {
    Node[] heap;
    int maxSize;
    int size;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        size = 0;
        heap = new Node[maxSize + 1];
        heap[0] = new Node(Integer.MAX_VALUE);
    }

    int parentIndex(int i) {
        return i / 2;
    }

    Node parentNode(int i) {
        return heap[i / 2];
    }

    Node leftNode(int i) {
        return heap[leftNodeIndex(i)];
    }

    int leftNodeIndex(int i) {
        return i * 2;
    }

    Node rightNode(int i) {
        return heap[rightNodeIndex(i)];
    }

    int rightNodeIndex(int i) {
        return i * 2 + 1;
    }

    void swap(int i, int j) {
        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    boolean isLeaf(int i) {
        if (i <= size && i >= size / 2) {
            return true;
        }
        return false;
    }

    void heapify(int i) {
        if (isLeaf(i)) {
            return;
        }
        Node left = leftNode(i);
        Node right = rightNode(i);
        if (heap[i].compareTo(left) >= 0 || heap[i].compareTo(right) >= 0) {
            return;
        }
        if (heap[i].compareTo(left) < 0) {
            swap(i, leftNodeIndex(i));
            heapify(leftNodeIndex(i));
            return;
        }
        swap(i, rightNodeIndex(i));
        heapify(rightNodeIndex(i));
    }

    void insert(int element) {
        heap[++size] = new Node(element);

        // Traverse up and fix violated property
        int current = size;
        while (heap[current].compareTo(heap[parentIndex(current)]) > 0) {
            swap(current, parentIndex(current));
            current = parentIndex(current);
        }
    }

    int pop() {
        int popped = heap[1].value;
        heap[1] = heap[size--];
        heapify(1);
        return popped;
    }

    public static void main(String[] arg) {
        MaxHeap heap = new MaxHeap(15);
        heap.insert(5);
        heap.insert(3);
        heap.insert(17);
        heap.insert(10);
        heap.insert(84);
        heap.insert(19);
        heap.insert(6);
        heap.insert(22);
        heap.insert(9);
        System.out.println("Max value - " + heap.pop());
        System.out.println("Max value - " + heap.pop());
        System.out.println("Max value - " + heap.pop());
    }
}