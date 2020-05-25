package melvin.datastructures.binarytree;

import melvin.algos.Mode;
import melvin.datastructures.binarynode.BinaryNode;
import melvin.datastructures.binarynode.Child;
import java.util.Stack;

public class BinaryTree {
  BinaryNode root;

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
  public boolean insert(BinaryNode parent, BinaryNode n, Child c, Mode m) {
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
  boolean insert(BinaryNode parent, BinaryNode currentNode, BinaryNode n, Child c) {
    if ((root == null && parent != null) || (root != null && parent == null) || currentNode == null) {
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

  /**
   * inserts the node to the given parent by loop method
   * 
   * @param parent parent node to which the node has to be inserted
   * @param n      node to be inserted
   * @param c      node should be inserted as left/right of the parent
   * @return true if the node was inserted sucessfully. false is returned if the
   *         insertion fails
   */
  boolean insert(BinaryNode parent, BinaryNode n, Child c) {
    if ((root == null && parent != null) || (root != null && parent == null)) {
      return false;
    }
    if (parent == null) {
      root = n;
      return true;
    }

    // we will use preorder to find the parent node
    Stack<BinaryNode> nodesStack = new Stack<>();
    BinaryNode currentNode = root;
    nodesStack.push(currentNode);
    while (true) {
      BinaryNode stackNode = nodesStack.peek();
      if (stackNode == null) {
        return false;
      }
      // checking whether we are on a reverse mode in traversal
      if (stackNode.value != currentNode.value) {
        // if right node is the
        if ((currentNode.right != null && stackNode.value == currentNode.right.value)
            || (currentNode.left != null && stackNode.value == currentNode.left.value && stackNode.right == null)) {
          nodesStack.pop();
          nodesStack.pop();
          BinaryNode pNode = nodesStack.peek();
          nodesStack.push(currentNode);
          currentNode = pNode;
          continue;
        }
        if (currentNode.left != null && stackNode.value == currentNode.left.value && stackNode.right != null) {
          nodesStack.pop();
          currentNode = currentNode.right;
          nodesStack.push(currentNode);
        }
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

      if (currentNode.left != null) {
        currentNode = currentNode.left;
        nodesStack.push(currentNode);
        continue;
      }
      if (currentNode.right != null) {
        currentNode = currentNode.right;
        nodesStack.push(currentNode);
        continue;
      }
      nodesStack.pop();
      BinaryNode pNode = nodesStack.peek();
      nodesStack.push(currentNode);
      currentNode = pNode;
    }
  }

  /**
   * search whether a given node is present in the BST
   * 
   * @param n node to be found
   * @return true if the node is found else false
   */
  boolean search(BinaryNode n) {
    return false;
  }

  public static void main(String[] args) {
    BinaryTree bt = new BinaryTree();
    bt.insert(null, new BinaryNode(10), Child.LEFT, Mode.BYLOOP);
    bt.insert(bt.root, new BinaryNode(20), Child.LEFT, Mode.BYLOOP);
    bt.insert(bt.root, new BinaryNode(21), Child.RIGHT, Mode.BYLOOP);
    bt.insert(bt.root.left, new BinaryNode(25), Child.RIGHT, Mode.BYLOOP);
    bt.insert(bt.root.right, new BinaryNode(27), Child.RIGHT, Mode.BYLOOP);
    bt.insert(bt.root.right.right, new BinaryNode(31), Child.RIGHT, Mode.RECURSIVE);
    System.out.println(bt);
  }
}
