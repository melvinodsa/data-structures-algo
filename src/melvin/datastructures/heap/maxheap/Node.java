package melvin.datastructures.heap.maxheap;

public class Node implements Comparable<Node> {
    int value;

    public Node(int i) {
        value = i;
    }

    public int compareTo(Node o) {
        return value - o.value;
    }
}