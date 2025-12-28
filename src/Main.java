import controller.SistemaEcommerce;
import models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaEcommerce controller = new SistemaEcommerce();

        System.out.println("Você é administrador?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.print("Opção: ");

        int perfil = scanner.nextInt();
        scanner.nextLine();

        // ==========================
        // PERFIL ADMINISTRADOR
        // ==========================
        if (perfil == 1) {

            int opcaoAdm;

            do {
                System.out.println("\n===== MENU ADMIN =====");
                System.out.println("1 - Cadastrar Produto");
                System.out.println("2 - Cadastrar Cliente");
                System.out.println("3 - Criar Pedido");
                System.out.println("4 - Listar Produtos");
                System.out.println("5 - Listar Clientes");
                System.out.println("6 - Listar Pedidos");
                System.out.println("7 - Sair");
                System.out.print("Opção: ");

                opcaoAdm = scanner.nextInt();
                scanner.nextLine();

                switch (opcaoAdm) {

                    case 1:
                        System.out.println("\n===== CADASTRO DE PRODUTO =====");
                        System.out.println("\nNome do Produto: ");
                        String nomeProd = scanner.nextLine();

                        System.out.print("Preço: ");
                        double preco = scanner.nextDouble();

                        System.out.print("Estoque: ");
                        int estoque = scanner.nextInt();
                        scanner.nextLine();

                        Produto produto = controller.cadastraNovoProduto(nomeProd, preco, estoque);
                        System.out.println("Produto cadastrado!");
                        break;

                    case 2:
                        System.out.println("\n===== CADASTRO DO CLIENTE =====");
                        System.out.print("\nCPF: ");
                        String cpf = scanner.nextLine();

                        System.out.print("Nome: ");
                        String nomeCliente = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        Cliente cliente = controller.cadastrarCliente(cpf, nomeCliente, email);
                        System.out.println("Cliente cadastrado!");
                        break;

                    case 3:
                        System.out.println("Pedido criado com sucesso!");
                        break;

                    case 4:
                        System.out.println("Produtos cadastrados:");
                        controller.listarProdutos();
                        break;

                    case 5:
                        System.out.println("Clientes cadastrados:");
                        controller.listarClientes();
                        break;

                    case 6:
                        System.out.println("Pedidos realizados:");
                        controller.listarPedidos();
                        break;

                    case 7:
                        System.out.println("Sair do sistema.");
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcaoAdm != 7);
        }

        // ==========================
        // PERFIL CLIENTE
        // ==========================
        else {

            System.out.println("\n===== CADASTRO DO CLIENTE =====");

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            Cliente cliente = controller.cadastrarCliente(cpf, nome, email);
            Pedido pedido = new Pedido(cliente);

            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println("Acessando o menu principal em alguns segundos...");


            int opcaoCliente;

            do {
                System.out.println("\n===== MENU CLIENTE =====");
                System.out.println("1 - Adicionar item ao pedido");
                System.out.println("2 - Calcular total do pedido");
                System.out.println("3 - Ver status do pedido");
                System.out.println("4 - Listar itens do pedido");
                System.out.println("5 - Sair");


                opcaoCliente = scanner.nextInt();
                scanner.nextLine();

                switch (opcaoCliente) {

                    case 1:
                        controller.listarProdutos();
                        System.out.print("Digite o ID do produto: ");
                        int idProd = scanner.nextInt();

                        System.out.print("Quantidade: ");
                        int qtd = scanner.nextInt();
                        scanner.nextLine();

                        Produto produto = controller.buscaProdutoPorId(idProd);
                        if (produto != null) {
                            pedido.adicionarItem(produto, qtd);
                        }
                        break;

                    case 2:
                        pedido.calculaTotalPedidos();
                        break;

                    case 3:
                        System.out.println("Status atual: " + pedido.getStatusPedido());
                        break;

                    case 4:
                        pedido.listaItensPedido();
                        break;

                    case 5:
                        System.out.println("Obrigado pela compra!");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcaoCliente != 5);
        }

        scanner.close();
    }
}
