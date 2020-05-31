package melvin.datastructures.binarysearchtree;

import melvin.datastructures.binarynode.IBinaryNode;
import melvin.datastructures.binarynode.BinaryNode;
import melvin.algos.Mode;

public class BinarySearchTree {
  public IBinaryNode root;

  /**
   * inserts a node to the binary search tree
   * 
   * @param n node to be inserted
   * @param m mode - recursion or loop
   */
  public boolean insert(IBinaryNode n, Mode m) {
    if (m == Mode.RECURSIVE) {
      insert(null, root, n);
    } else if (m == Mode.BYLOOP) {
      insert(n);
    } else {
      return false;
    }
    return true;
  }

  /**
   * will insert an node to the the bst using recursive method
   * 
   * @param parentNode  parent node of the current node. It should be given null
   *                    for a root node
   * @param currentNode current node with which the given node is checked with
   * @param point       is the node to be inserted
   */
  void insert(IBinaryNode parentNode, IBinaryNode currentNode, IBinaryNode point) {
    if (root == null) {
      root = point;
      return;
    }
    if (currentNode == null) {
      currentNode = point;
      if (parentNode.getValue() > currentNode.getValue()) {
        parentNode.setLeft(currentNode);
      } else if (parentNode.getValue() < currentNode.getValue()) {
        parentNode.setRight(currentNode);
      }
      return;
    }
    if (currentNode.getValue() > point.getValue()) {
      insert(currentNode, currentNode.getLeft(), point);
      return;
    }
    if (currentNode.getValue() < point.getValue()) {
      insert(currentNode, currentNode.getRight(), point);
      return;
    }
  }

  /**
   * will insert an node to the the bst using loop method
   * 
   * @param n node to be inserted
   */
  void insert(IBinaryNode n) {
    IBinaryNode currentNode = root;
    IBinaryNode parentNode = null;
    while (true) {
      if (currentNode == null) {
        currentNode = n;
        if (parentNode == null) {
          root = n;
          return;
        }
        if (parentNode.getValue() > currentNode.getValue()) {
          parentNode.setLeft(currentNode);
        } else if (parentNode.getValue() < currentNode.getValue()) {
          parentNode.setRight(currentNode);
        }
        return;
      }
      if (currentNode.getValue() != n.getValue()) {
        parentNode = currentNode;
        currentNode = currentNode.getValue() > n.getValue() ? currentNode.getLeft() : currentNode.getRight();
        continue;
      }
    }
  }

  /**
   * search whether a given node is present in the BST
   * 
   * @param n node to be found
   * @param m mode of search recursive or loop
   * @return true if the node is found else false
   */
  public boolean search(IBinaryNode n, Mode m) {
    if (m == Mode.RECURSIVE) {
      return search(this.root, n);
    }
    return search(n);
  }

  /**
   * search a given node using recursive method
   * 
   * @param currentNode node being compared with the given node
   * @param n           node to be searched
   * @return true if the node is existing in the sub tree from this node
   */
  boolean search(IBinaryNode currentNode, IBinaryNode n) {
    if (currentNode == null) {
      return false;
    }
    if (currentNode.getValue() == n.getValue()) {
      return true;
    }
    if (currentNode.getValue() > n.getValue()) {
      return search(currentNode.getLeft(), n);
    }
    if (currentNode.getValue() < n.getValue()) {
      return search(currentNode.getRight(), n);
    }
    return false;
  }

  /**
   * search a given node using loop method
   * 
   * @param n node to be found
   * @return true if the node is existing in the BST
   */
  boolean search(IBinaryNode n) {
    IBinaryNode currentNode = root;
    while (true) {
      if (currentNode == null) {
        return false;
      }
      if (currentNode.getValue() == n.getValue()) {
        return true;
      }
      currentNode = currentNode.getValue() > n.getValue() ? currentNode.getLeft() : currentNode.getRight();
    }
  }

  public static void main(String[] args) {
    int[] list = { 10, 20, 40, 28, 5, 6 };
    BinarySearchTree bst = new BinarySearchTree();
    BinarySearchTree bstO = new BinarySearchTree();
    for (int i : list) {
      bst.insert(new BinaryNode(i), Mode.RECURSIVE);
      bstO.insert(new BinaryNode(i), Mode.BYLOOP);
    }
    System.out.println("Recursive mode should be true. got " + bst.search(new BinaryNode(28), Mode.RECURSIVE));
    System.out.println("Loop by mode should be true. got " + bst.search(new BinaryNode(28), Mode.BYLOOP));
    System.out.println("Recursive mode should be false. got " + bst.search(new BinaryNode(23), Mode.RECURSIVE));
    System.out.println("Loop by mode should be false. got " + bst.search(new BinaryNode(23), Mode.BYLOOP));
  }
}
