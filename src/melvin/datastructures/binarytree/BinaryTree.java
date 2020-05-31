package melvin.datastructures.binarytree;

import melvin.algos.Mode;
import melvin.datastructures.binarynode.IBinaryNode;
import melvin.datastructures.binarynode.BinaryNode;
import melvin.datastructures.binarynode.Child;

import java.util.Stack;

public class BinaryTree {
  public IBinaryNode root;

  /**
   * inserts a node to the binary search tree
   * 
   * @param parent node to which the node has to be inserted
   * @param n      node to be inserted
   * @param c      node should be inserted as left/right of the parent
   * @param m      mode of insertion byloop or recursion
   * @return true if the node was inserted sucessfully. false is returned if the
   *         insertion fails
   */
  public boolean insert(IBinaryNode parent, IBinaryNode n, Child c, Mode m) {
    if (m == Mode.RECURSIVE) {
      return insert(parent, root, n, c);
    } else if (m == Mode.BYLOOP) {
      return insert(parent, n, c);
    } else {
      return false;
    }
  }

  /**
   * inserts the node to the given parent by recursive method
   * 
   * @param parent      parent node to which the node has to be inserted
   * @param currentNode node being evaluated now
   * @param n           node to be inserted
   * @param c           node should be inserted as left/right of the parent
   * @return true if the node was inserted sucessfully. false is returned if the
   *         insertion fails
   */
  boolean insert(IBinaryNode parent, IBinaryNode currentNode, IBinaryNode n, Child c) {
    if ((root == null && parent != null) || (root != null && parent == null) || currentNode == null) {
      return false;
    }
    if (parent == null) {
      root = n;
      return true;
    }

    // checking whether the current node is the parent node
    if (currentNode.getValue() == parent.getValue()) {
      if (c == Child.LEFT && currentNode.getLeft() == null) {
        currentNode.setLeft(n);
        return true;
      }
      if (c == Child.RIGHT && currentNode.getRight() == null) {
        currentNode.setRight(n);
        return true;
      }
      return false;
    }

    boolean inserted = false;
    if (currentNode.getLeft() != null) {
      inserted = insert(parent, currentNode.getLeft(), n, c);
    }

    if (!inserted && currentNode.getRight() != null) {
      inserted = insert(parent, currentNode.getRight(), n, c);
    }

    return inserted;
  }

  /**
   * inserts the node to the given parent by loop method
   * 
   * @param parent parent node to which the node has to be inserted
   * @param n      node to be inserted
   * @param c      node should be inserted as left/right of the parent
   * @return true if the node was inserted sucessfully. false is returned if the
   *         insertion fails
   */
  boolean insert(IBinaryNode parent, IBinaryNode n, Child c) {
    if ((root == null && parent != null) || (root != null && parent == null)) {
      return false;
    }
    if (parent == null) {
      root = n;
      return true;
    }

    IBinaryNode foundNode = search(parent);
    if (foundNode == null) {
      return false;
    }
    if (c == Child.LEFT && foundNode.getLeft() == null) {
      foundNode.setLeft(n);
      return true;
    }
    if (c == Child.RIGHT && foundNode.getRight() == null) {
      foundNode.setRight(n);
      return true;
    }
    return false;
  }

  /**
   * search whether a given node is present in the BST
   * 
   * @param n node to be found
   * @return the node if found else null
   */
  public IBinaryNode search(IBinaryNode n) {
    if (root == null) {
      return null;
    }

    // we will use preorder to find the node
    Stack<IBinaryNode> nodesStack = new Stack<>();
    IBinaryNode currentNode = root;
    nodesStack.push(currentNode);
    while (true) {
      if (nodesStack.isEmpty()) {
        return null;
      }
      IBinaryNode stackNode = nodesStack.peek();
      // checking whether we are on a reverse mode in traversal
      if (stackNode.getValue() != currentNode.getValue()) {
        // if right node is the
        if ((currentNode.getRight() != null && stackNode.getValue() == currentNode.getRight().getValue())
            || (currentNode.getLeft() != null && stackNode.getValue() == currentNode.getLeft().getValue()
                && stackNode.getRight() == null)) {
          nodesStack.pop();
          nodesStack.pop();
          if (nodesStack.isEmpty()) {
            return null;
          }
          IBinaryNode pNode = nodesStack.peek();
          nodesStack.push(currentNode);
          currentNode = pNode;
          continue;
        }
        if (currentNode.getLeft() != null && stackNode.getValue() == currentNode.getLeft().getValue()
            && stackNode.getRight() != null) {
          nodesStack.pop();
          currentNode = currentNode.getRight();
          nodesStack.push(currentNode);
        }
      }

      // checking whether the current node is the node to be found
      if (currentNode.getValue() == n.getValue()) {
        return currentNode;
      }

      if (currentNode.getLeft() != null) {
        currentNode = currentNode.getLeft();
        nodesStack.push(currentNode);
        continue;
      }

      if (currentNode.getRight() != null) {
        currentNode = currentNode.getRight();
        nodesStack.push(currentNode);
        continue;
      }

      nodesStack.pop();
      IBinaryNode pNode = nodesStack.peek();
      nodesStack.push(currentNode);
      currentNode = pNode;
    }
  }

  public static void main(String[] args) {
    BinaryTree bt = new BinaryTree();
    bt.insert(null, new BinaryNode(10), Child.LEFT, Mode.BYLOOP);
    bt.insert(bt.root, new BinaryNode(20), Child.LEFT, Mode.BYLOOP);
    bt.insert(bt.root, new BinaryNode(21), Child.RIGHT, Mode.BYLOOP);
    bt.insert(bt.root.getLeft(), new BinaryNode(25), Child.RIGHT, Mode.BYLOOP);
    bt.insert(bt.root.getRight(), new BinaryNode(27), Child.RIGHT, Mode.BYLOOP);
    bt.insert(bt.root.getRight().getRight(), new BinaryNode(31), Child.RIGHT, Mode.RECURSIVE);
    IBinaryNode bn = bt.search(new BinaryNode(51));
    System.out.println(bn);
  }
}
