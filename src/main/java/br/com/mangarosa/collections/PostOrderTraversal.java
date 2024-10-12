package br.com.mangarosa.collections;

import java.util.List;
import java.util.ArrayList;

public class PostOrderTraversal<T extends Comparable<T>> implements TreeTraversal<T> {
    @Override
    public List<T> traverse(Tree<T> tree) {
        List<T> result = new ArrayList<>();
        postOrder(tree.root(), result);
        return result;
    }

    private void postOrder(BinaryTreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeftChild(), result);  // Visita a subárvore esquerda
        postOrder(node.getRightChild(), result); // Visita a subárvore direita
        result.add(node.getValue());  // Processa o nó
    }
}
