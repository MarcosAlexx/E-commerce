import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;

import models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ClienteService clienteService = new ClienteService();
        PedidoService pedidoService = new PedidoService();
        ProdutoService produtoService = new ProdutoService();

        Pedido pedido = null;
        Produto produto = null;
        Cliente cliente = null;

        int perfilPrincipal;

        do {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE E-COMMERCE");
            System.out.println("========================================");
            System.out.println("1 - Área do Administrador");
            System.out.println("2 - Área do Cliente");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Opção: ");

            perfilPrincipal = scanner.nextInt();
            scanner.nextLine();

            // ==========================
            // PERFIL ADMINISTRADOR
            // ==========================
            if (perfilPrincipal == 1) {

                int opcaoAdm;

                do {
                    System.out.println("\n===== MENU ADMIN =====");
                    System.out.println("1 - Cadastrar Produto");
                    System.out.println("2 - Cadastrar Cliente");
                    System.out.println("3 - Criar Pedido");
                    System.out.println("4 - Listar Produtos");
                    System.out.println("5 - Listar Clientes");
                    System.out.println("6 - Listar Pedidos");
                    System.out.println("0 - Voltar ao Menu Principal");
                    System.out.print("Opção: ");

                    opcaoAdm = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoAdm) {

                        case 1:
                            System.out.println("\n===== CADASTRO DE PRODUTO =====");
                            System.out.print("Nome do Produto: ");
                            String nomeProd = scanner.nextLine();

                            System.out.print("Preço: ");
                            double preco = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("Estoque: ");
                            int estoque = scanner.nextInt();
                            scanner.nextLine();

                            produto = produtoService.cadastraNovoProduto(nomeProd, preco, estoque);
                            System.out.println("✓ Produto cadastrado com sucesso!");
                            break;

                        case 2:
                            System.out.println("\n===== VERIFICAR SE CLIENTE JÁ POSSUI CADASTRO =====");
                            System.out.print("CPF do cliente: ");
                            String cpfAdm = scanner.nextLine();

                            boolean encontradoAdm = false;
                            for (Cliente c : clienteService.clientes) {
                                if (c.getCpf().equals(cpfAdm)) {
                                    encontradoAdm = true;
                                    cliente = c;
                                    System.out.println("✓ Cliente já cadastrado. Fazendo login como " + c.getNome() + "...");
                                    menuCliente(scanner, clienteService, produtoService, pedidoService, cliente, pedido);
                                    break;
                                }
                            }

                            if (!encontradoAdm) {
                                System.out.println("⚠ CPF não encontrado. Deseja cadastrar esse CPF?");
                                System.out.println("1 - Sim");
                                System.out.println("2 - Não");
                                System.out.print("Opção: ");
                                int opcaoCadastrar = scanner.nextInt();
                                scanner.nextLine();

                                if (opcaoCadastrar == 1) {
                                    System.out.print("Nome: ");
                                    String nomeNovo = scanner.nextLine();
                                    System.out.print("Email: ");
                                    String emailNovo = scanner.nextLine();

                                    cliente = clienteService.cadastrarCliente(cpfAdm, nomeNovo, emailNovo);
                                    System.out.println("✓ Cliente cadastrado com sucesso! Bem-vindo, " + nomeNovo + "!");
                                    menuCliente(scanner, clienteService, produtoService, pedidoService, cliente, pedido);
                                } else {
                                    System.out.println("Voltando ao menu admin...");
                                }
                            }
                            break;

                        case 3:
                            System.out.println("✓ Pedido criado com sucesso! (Use a área do cliente para criar pedidos detalhados)");
                            break;

                        case 4:
                            System.out.println("\n===== PRODUTOS CADASTRADOS =====");
                            produtoService.listarProdutos();
                            break;

                        case 5:
                            System.out.println("\n===== CLIENTES CADASTRADOS =====");
                            clienteService.listarClientes();
                            break;

                        case 6:
                            System.out.println("\n===== PEDIDOS REALIZADOS =====");
                            pedidoService.listarPedidos(cliente);
                            break;

                        case 0:
                            System.out.println("Voltando ao menu principal...");
                            break;

                        default:
                            System.out.println("⚠ Opção inválida.");
                    }

                } while (opcaoAdm != 0);
            }

            // ==========================
            // PERFIL CLIENTE
            // ==========================
            else if (perfilPrincipal == 2) {
                System.out.println("\n===== ÁREA DO CLIENTE =====");
                System.out.println("Você possui cadastro?");
                System.out.println("1 - Sim (Fazer Login)");
                System.out.println("2 - Não (Fazer Cadastro)");
                System.out.print("Opção: ");
                int possuiCadastro = scanner.nextInt();
                scanner.nextLine();

                // LOGIN CLIENTE
                if (possuiCadastro == 1) {
                    System.out.println("\n===== LOGIN =====");
                    System.out.print("Digite seu CPF: ");
                    String cpf = scanner.nextLine();

                    boolean encontrado = false;
                    for (Cliente c : clienteService.clientes) {
                        if (c.getCpf().equals(cpf)) {
                            encontrado = true;
                            cliente = c;
                            System.out.println("✓ Login realizado com sucesso! Bem-vindo, " + c.getNome() + "!");
                            menuCliente(scanner, clienteService, produtoService, pedidoService, cliente, pedido);
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("⚠ CPF não encontrado. Deseja fazer cadastro?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int opcaoCadastro = scanner.nextInt();
                        scanner.nextLine();

                        if (opcaoCadastro == 1) {
                            cliente = realizarCadastroCliente(scanner, clienteService);
                            if (cliente != null) {
                                menuCliente(scanner, clienteService, produtoService, pedidoService, cliente, pedido);
                            }
                        } else {
                            System.out.println("Voltando ao menu principal...");
                        }
                    }
                }
                // CADASTRO CLIENTE
                else if (possuiCadastro == 2) {
                    cliente = realizarCadastroCliente(scanner, clienteService);
                    if (cliente != null) {
                        menuCliente(scanner, clienteService, produtoService, pedidoService, cliente, pedido);
                    }
                } else {
                    System.out.println("⚠ Opção inválida.");
                }
            }

            else if (perfilPrincipal == 0) {
                System.out.println("\n========================================");
                System.out.println("   Obrigado por usar nosso sistema!");
                System.out.println("========================================");
            }

            else {
                System.out.println("⚠ Opção inválida. Tente novamente.");
            }

        } while (perfilPrincipal != 0);

        scanner.close();
    }


    private static Cliente realizarCadastroCliente(Scanner scanner, ClienteService clienteService) {
        System.out.println("\n===== CADASTRO DO CLIENTE =====");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Cliente cliente = clienteService.cadastrarCliente(cpf, nome, email);
        System.out.println("✓ Cliente cadastrado com sucesso! Bem-vindo, " + nome + "!");
        return cliente;
    }


    private static void menuCliente(
            Scanner scanner,
            ClienteService clienteService,
            ProdutoService produtoService,
            PedidoService pedidoService,
            Cliente cliente,
            Pedido pedidoAtual) {

        Pedido pedido = pedidoAtual;
        Produto produto;
        int opcaoCliente;

        do {
            System.out.println("\n===== MENU CLIENTE =====");
            System.out.println("1 - Criar Pedido");
            System.out.println("2 - Adicionar Item ao Pedido");
            System.out.println("3 - Calcular Total do Pedido");
            System.out.println("4 - Ver Status do Pedido");
            System.out.println("5 - Listar Itens do Pedido");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Opção: ");

            opcaoCliente = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoCliente) {

                case 1:
                    System.out.println("\n===== Produtos Disponiveis no Momento =====");
                    produtoService.listarProdutos();

                    if (produtoService.produtos.isEmpty()) {
                        System.out.println("⚠ Nenhum produto cadastrado ainda.");
                        break;
                    }

                    System.out.print("\nDigite o ID do produto desejado: ");
                    int idProd = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Quantidade: ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine();

                    produto = produtoService.buscaProdutoPorId(idProd);

                    if (produto != null && produto.getEstoque() >= qtd) {
                        pedido = pedidoService.cadastraNovoPedido(cliente);
                        pedido.adicionarItem(produto, qtd);
                        System.out.println("✓ Pedido criado e primeiro item adicionado com sucesso!");
                    } else {
                        System.out.println("⚠ Produto inexistente ou estoque insuficiente.");
                    }
                    break;

                case 2:
                    if (pedido == null) {
                        System.out.println("⚠ Nenhum pedido ativo. Crie um pedido primeiro (opção 1).");
                        break;
                    }

                    System.out.println("\n===== ADICIONAR ITEM =====");
                    produtoService.listarProdutos();
                    System.out.print("Digite o ID do produto: ");
                    idProd = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Quantidade: ");
                    int qtd2 = scanner.nextInt();
                    scanner.nextLine();

                    produto = produtoService.buscaProdutoPorId(idProd);

                    if (produto != null && produto.getEstoque() >= qtd2) {
                        pedido.adicionarItem(produto, qtd2);
                        System.out.println("✓ Item adicionado ao pedido.");
                    } else {
                        System.out.println("⚠ Produto não encontrado ou estoque insuficiente.");
                    }
                    break;

                case 3:
                    if (pedido == null) {
                        System.out.println("⚠ Nenhum pedido para calcular.");
                    } else {
                        System.out.println("\n===== TOTAL DO PEDIDO =====");
                        pedido.calculaTotalPedidos(cliente);
                    }
                    break;

                case 4:
                    if (pedido == null) {
                        System.out.println("⚠ Nenhum pedido criado.");
                    } else {
                        System.out.println("\n===== STATUS DO PEDIDO =====");
                        System.out.println("Status atual: " + pedido.getStatusPedido());
                    }
                    break;

                case 5:
                    if (pedido == null) {
                        System.out.println("⚠ Nenhum pedido criado.");
                    } else {
                        System.out.println("\n===== ITENS DO PEDIDO =====");
                        pedido.listaItensPedido(cliente);
                    }
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("⚠ Opção inválida.");
            }

        } while (opcaoCliente != 0);
    }
}
