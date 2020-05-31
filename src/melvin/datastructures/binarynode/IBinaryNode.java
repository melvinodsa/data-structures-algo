package melvin.datastructures.binarynode;

public interface IBinaryNode {
    public int getValue();

    public void setValue(int value);

    public IBinaryNode getLeft();

    public void setLeft(IBinaryNode node);

    public IBinaryNode getRight();

    public void setRight(IBinaryNode node);
}