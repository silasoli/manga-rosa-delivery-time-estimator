package br.com.mangarosa;

import br.com.mangarosa.collections.*;
import br.com.mangarosa.model.Rule;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        displayTreeImage();

        Scanner scanner = new Scanner(System.in);
        BinarySearchTree<Rule> tree = new BinarySearchTree<>();
        int option = 0;

        do {
            displayMenu();
            option = getValidOption(scanner);

            switch (option) {
                case 1:
                    addRule(scanner, tree);
                    break;

                case 2:
                    removeRule(scanner, tree);
                    break;

                case 3:
                    checkRule(scanner, tree);
                    break;

                case 4:
                    checkIfEmpty(tree);
                    break;

                case 5:
                    displayTreeInOrder(tree);
                    break;

                case 6:
                    displayTreePreOrder(tree);
                    break;

                case 7:
                    displayTreePostOrder(tree);
                    break;

                case 8:
                    clearTree(tree);
                    break;

                case 9:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 9);

        scanner.close();
    }

    private static void displayTreeImage() {
        System.out.println("\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⡰⡇⠁⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠰⢾⠇⠨⡦⠀⠂⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⡀⠀⢈⣹⠜⠻⢾⠃⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠁⢠⣿⡵⠾⣻⣶⠿⠦⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⢀⠀⢀⣠⣮⣦⡶⠻⠛⢶⣄⡀⠁⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⢀⣽⠏⠁⣠⣂⣦⣈⣝⣦⣄⠀⠈⠁⠀\n"
                + "⠀⠀⠀⠀⠁⣠⣾⣵⣾⣾⠟⡙⠟⠿⣍⡉⠀⠀⠆⠀⠀\n"
                + "⠀⠰⠀⠀⠄⣠⣶⣾⣭⡶⠟⠻⣶⡰⣜⣳⣦⣄⠀⡀⠀\n"
                + "⠀⠀⠀⢀⣠⣴⣿⣋⡴⣪⠎⣄⡙⠻⠿⣯⣉⠉⠀⠀⠀\n"
                + "⠀⠂⠀⢀⣉⡭⢿⡛⣛⣵⠎⠀⠙⢾⣶⣦⣭⣷⣦⠐⠀\n"
                + "⠀⠀⢈⣙⣿⡿⠿⠟⣋⢅⡄⡄⡐⣄⢤⣉⠷⢦⣄⣀⠠\n"
                + "⠐⠾⠿⢽⣷⡶⠶⡿⣓⣭⣾⣿⢷⣬⣓⢿⠿⠿⣯⣉⣁\n"
                + "⠀⠀⠀⠉⠉⠉⠛⠛⠉⢀⣿⢿⡀⠙⠋⠓⠿⠿⠏⠉⠉\n"
                + "⠀⠀⠀⠀⠀⠀⠠⠤⠶⠾⢿⡯⠷⠶⠤⠄⠀⠀⠀\n"
        );
        System.out.println(""
                + "╔═════════════════════════════════════════════════════════╗\n"
                + "║          Bem-vindo ao Sistema de Árvore Binária         ║\n"
                + "║  Por favor, siga as instruções para manipular a árvore: ║\n"
                + "╚═════════════════════════════════════════════════════════╝\n"
        );
    }

    private static void displayMenu() {
        System.out.println("\n========== Menu ==========");
        System.out.println("1. Inserir uma regra");
        System.out.println("2. Remover uma regra");
        System.out.println("3. Verificar se uma regra está na árvore");
        System.out.println("4. Verificar se a árvore está vazia");
        System.out.println("5. Exibir a árvore (in-order)");
        System.out.println("6. Exibir a árvore (pre-order)");
        System.out.println("7. Exibir a árvore (post-order)");
        System.out.println("8. Limpar a árvore");
        System.out.println("9. Sair");
        System.out.println("==========================");
    }

    private static int getValidOption(Scanner scanner) {
        int option = -1;
        try {
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erro: Por favor, insira um número válido.");
            scanner.nextLine();
        }
        return option;
    }

    private static void addRule(Scanner scanner, BinarySearchTree<Rule> tree) {
        try {
            System.out.print("Digite o ID da regra a ser inserida: ");
            int ruleId = scanner.nextInt();
            Rule ruleToAdd = new Rule(ruleId);
            tree.add(ruleToAdd);
            System.out.println("Regra com ID " + ruleId + " inserida.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido. Por favor, insira um número inteiro.");
            scanner.nextLine();  
        }
    }

    private static void removeRule(Scanner scanner, BinarySearchTree<Rule> tree) {
        try {
            System.out.print("Digite o ID da regra a ser removida: ");
            int ruleId = scanner.nextInt();
            Rule ruleToRemove = new Rule(ruleId);
            if (tree.contains(ruleToRemove)) {
                tree.remove(ruleToRemove);
                System.out.println("Regra com ID " + ruleId + " removida.");
            } else {
                System.out.println("Regra não encontrada na árvore.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido. Por favor, insira um número inteiro.");
            scanner.nextLine(); 
        }
    }

    private static void checkRule(Scanner scanner, BinarySearchTree<Rule> tree) {
        try {
            System.out.print("Digite o ID da regra a ser verificada: ");
            int ruleId = scanner.nextInt();
            Rule ruleToCheck = new Rule(ruleId);
            if (tree.contains(ruleToCheck)) {
                System.out.println("A regra com ID " + ruleId + " está na árvore.");
            } else {
                System.out.println("A regra com ID " + ruleId + " não está na árvore.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido. Por favor, insira um número inteiro.");
            scanner.nextLine(); 
        }
    }

    private static void checkIfEmpty(BinarySearchTree<Rule> tree) {
        if (tree.isEmpty()) {
            System.out.println("A árvore está vazia.");
        } else {
            System.out.println("A árvore não está vazia.");
        }
    }

    private static void displayTreeInOrder(BinarySearchTree<Rule> tree) {
        System.out.println("Exibindo árvore (in-order):");
        InOrderTraversal<Rule> inOrderTraversal = new InOrderTraversal<>();
        List<Rule> inOrder = inOrderTraversal.traverse(tree);
        System.out.println(inOrder);
    }

    private static void displayTreePreOrder(BinarySearchTree<Rule> tree) {
        System.out.println("Exibindo árvore (pre-order):");
        PreOrderTraversal<Rule> preOrderTraversal = new PreOrderTraversal<>();
        List<Rule> preOrder = preOrderTraversal.traverse(tree);
        System.out.println(preOrder);
    }

    private static void displayTreePostOrder(BinarySearchTree<Rule> tree) {
        System.out.println("Exibindo árvore (post-order):");
        PostOrderTraversal<Rule> postOrderTraversal = new PostOrderTraversal<>();
        List<Rule> postOrder = postOrderTraversal.traverse(tree);
        System.out.println(postOrder);
    }

    private static void clearTree(BinarySearchTree<Rule> tree) {
        tree.clear();
        System.out.println("A árvore foi limpa.");
    }
}
