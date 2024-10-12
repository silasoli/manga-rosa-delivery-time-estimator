package br.com.mangarosa.collections;

import java.util.List;
import java.util.ArrayList;

public class InOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        inOrder(tree.root(), result);
        return result;
    }

    private void inOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeftChild(), result);  // Visita a subárvore esquerda
        result.add(node.getValue());  // Processa o nó
        inOrder(node.getRightChild(), result); // Visita a subárvore direita
    }
}
