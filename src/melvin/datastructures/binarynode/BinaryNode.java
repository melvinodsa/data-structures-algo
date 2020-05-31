package melvin.datastructures.binarynode;

public class BinaryNode implements IBinaryNode {
    public int value;
    public IBinaryNode left;
    public IBinaryNode right;

    public BinaryNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public IBinaryNode getLeft() {
        return left;
    }

    public void setLeft(IBinaryNode node) {
        left = node;
    }

    public IBinaryNode getRight() {
        return right;
    }

    public void setRight(IBinaryNode node) {
        right = node;
    }
}
