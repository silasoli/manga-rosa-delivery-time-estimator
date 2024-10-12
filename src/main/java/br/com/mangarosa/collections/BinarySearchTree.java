package br.com.mangarosa.collections;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
    private BinaryTreeNode<T> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        this.root = addRecursive(this.root, value);
        this.size++;
    }

    private BinaryTreeNode<T> addRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return new BinaryTreeNode<>(value);
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(addRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(addRecursive(node.getRightChild(), value));
        }
        return node;
    }

    @Override
    public void remove(T value) {
        this.root = removeRecursive(this.root, value);
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(removeRecursive(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(removeRecursive(node.getRightChild(), value));
        } else {
            // Caso 1: Nó folha
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            }

            // Caso 2: Um filho
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            }

            if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            // Caso 3: Dois filhos
            // Substituir pelo menor valor da subárvore direita
            T smallestValue = findSmallestValue(node.getRightChild());
            node.setValue(smallestValue);
            node.setRightChild(removeRecursive(node.getRightChild(), smallestValue));
        }

        return node;
    }

    private T findSmallestValue(BinaryTreeNode<T> node) {
        return node.getLeftChild() == null ? node.getValue() : findSmallestValue(node.getLeftChild());
    }

    @Override
    public boolean contains(T value) {
        return containsRecursive(this.root, value);
    }

    private boolean containsRecursive(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return false;
        }
        if (value.equals(node.getValue())) {
            return true;
        }
        return value.compareTo(node.getValue()) < 0
                ? containsRecursive(node.getLeftChild(), value)
                : containsRecursive(node.getRightChild(), value);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isLeaf(T value) {
        BinaryTreeNode<T> node = findNode(this.root, value);
        return node != null && node.getLeftChild() == null && node.getRightChild() == null;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (value.equals(node.getValue())) {
            return node;
        }
        return value.compareTo(node.getValue()) < 0
                ? findNode(node.getLeftChild(), value)
                : findNode(node.getRightChild(), value);
    }

    @Override
    public BinaryTreeNode<T> root() {
        return this.root;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T[] toArray() {
        List<T> elements = new ArrayList<>();
        inOrderTraversal(this.root, elements);
        return (T[]) elements.toArray();
    }

    private void inOrderTraversal(BinaryTreeNode<T> node, List<T> elements) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.getLeftChild(), elements);
        elements.add(node.getValue());
        inOrderTraversal(node.getRightChild(), elements);
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }
}
