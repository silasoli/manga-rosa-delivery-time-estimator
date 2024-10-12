package br.com.mangarosa.collections;

public class BinaryTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    private int height(BinaryTreeNode<T> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    private int balanceFactor(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getLeftChild();
        BinaryTreeNode<T> T2 = x.getRightChild();

        x.setRightChild(y);
        y.setLeftChild(T2);

        return x; 
    }

    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> x) {
        BinaryTreeNode<T> y = x.getRightChild();
        BinaryTreeNode<T> T2 = y.getLeftChild();

        y.setLeftChild(x);
        x.setRightChild(T2);

        return y;
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        int balanceFactor = balanceFactor(node);

        if (balanceFactor > 1) {
            if (balanceFactor(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }

        if (balanceFactor < -1) {
            if (balanceFactor(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }

        return node; 
    }

    @Override
    public void add(T value) {
        super.add(value);
    }
}
