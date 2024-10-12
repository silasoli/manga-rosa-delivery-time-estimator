package br.com.mangarosa.collections;

public class BinaryTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    // Método auxiliar para calcular a altura de um nó
    private int height(BinaryTreeNode<T> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    // Método para calcular o fator de balanceamento
    private int balanceFactor(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    // Rotação à direita
    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> y) {
        BinaryTreeNode<T> x = y.getLeftChild();
        BinaryTreeNode<T> T2 = x.getRightChild();

        // Realizar a rotação
        x.setRightChild(y);
        y.setLeftChild(T2);

        return x; // Nova raiz após a rotação
    }

    // Rotação à esquerda
    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> x) {
        BinaryTreeNode<T> y = x.getRightChild();
        BinaryTreeNode<T> T2 = y.getLeftChild();

        // Realizar a rotação
        y.setLeftChild(x);
        x.setRightChild(T2);

        return y; // Nova raiz após a rotação
    }

    // Método para balancear o nó, chamado após cada inserção ou remoção
    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        int balanceFactor = balanceFactor(node);

        // Caso de rotação à direita
        if (balanceFactor > 1) {
            if (balanceFactor(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild())); // Rotação dupla (esquerda-direita)
            }
            return rotateRight(node);
        }

        // Caso de rotação à esquerda
        if (balanceFactor < -1) {
            if (balanceFactor(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild())); // Rotação dupla (direita-esquerda)
            }
            return rotateLeft(node);
        }

        return node; // Nenhum balanceamento necessário
    }

    // Sobrescrever o método add para balancear após a inserção
    @Override
    public void add(T value) {
        super.add(value);
        // Chamar o balanceamento aqui após a adição (precisa de ajuste conforme a inserção específica)
    }
}
