package melvin.algos.binarytree;

import java.util.Stack;

import melvin.datastructures.binarynode.Child;

public class NodesAtKDistance {

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.insert(null, new Node(10), Child.LEFT);
        bt.insert(bt.root, new Node(20), Child.LEFT);
        bt.insert(bt.root, new Node(21), Child.RIGHT);
        bt.insert(bt.root.left, new Node(25), Child.RIGHT);
        bt.insert(bt.root.right, new Node(27), Child.RIGHT);
        bt.insert(bt.root.right.right, new Node(31), Child.RIGHT);
        Node n = bt.search(new Node(31));
    }
}

class BinaryTree {
    Node root;

    boolean insert(Node parent, Node n, Child c) {
        return insert(parent, root, n, c);
    }

    boolean insert(Node parent, Node currentNode, Node n, Child c) {
        if ((root == null && parent != null) || (root != null && parent == null)
                || (currentNode == null && root != null)) {
            return false;
        }
        if (parent == null) {
            root = n;
            return true;
        }

        // checking whether the current node is the parent node
        if (currentNode.value == parent.value) {
            if (c == Child.LEFT && currentNode.left == null) {
                currentNode.left = n;
                return true;
            }
            if (c == Child.RIGHT && currentNode.right == null) {
                currentNode.right = n;
                return true;
            }
            return false;
        }

        boolean inserted = false;
        if (currentNode.left != null) {
            inserted = insert(parent, currentNode.left, n, c);
        }

        if (!inserted && currentNode.right != null) {
            inserted = insert(parent, currentNode.right, n, c);
        }

        return inserted;
    }

    Node search(Node n) {
        // we will use the pre order search
        if (n == null || root == null) {
            return null;
        }

        Node currentNode = root;
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(currentNode);
        while (true) {
            if (nodeStack.isEmpty()) {
                return null;
            }

            Node stackNode = nodeStack.peek();
            // checking whether we are on a reverse mode in traversal
            if (stackNode.value != currentNode.value) {
                boolean rightNodeInReverseMode = stackNode.right != null && stackNode.right.value == currentNode.value;
                boolean leftNodeInReverseMode = stackNode.left != null && stackNode.left.value == currentNode.value;
                boolean rightNodeIsNull = stackNode.right == null;

                if (!rightNodeInReverseMode && !leftNodeInReverseMode) {
                    // no idea why would it happen
                    return null;
                }

                if (rightNodeInReverseMode || (leftNodeInReverseMode && rightNodeIsNull)) {
                    currentNode = nodeStack.pop();
                    if (nodeStack.isEmpty()) {
                        return null;
                    }
                    continue;
                }
                if (leftNodeInReverseMode && !rightNodeIsNull) {
                    currentNode = stackNode.right;
                    nodeStack.push(currentNode);
                }
            }

            // check whether the current node is the given node
            if (currentNode.value == n.value) {
                while (!nodeStack.isEmpty()) {
                    Node p = nodeStack.pop();
                    p.visited = true;
                }
                return currentNode;
            }

            if (currentNode.left != null) {
                currentNode = currentNode.left;
                nodeStack.push(currentNode);
                continue;
            }

            if (currentNode.right != null) {
                currentNode = currentNode.right;
                nodeStack.push(currentNode);
                continue;
            }

            // no node is available for further, need to go back
            nodeStack.pop();
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;
    boolean visited;

    public Node(int value) {
        this.value = value;
    }
}
