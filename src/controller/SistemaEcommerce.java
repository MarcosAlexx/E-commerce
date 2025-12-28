package controller;

import models.Cliente;
import models.Pedido;
import models.Produto;

import java.util.ArrayList;

public class SistemaEcommerce {

    ArrayList<Produto> produtos = new ArrayList<>();

    ArrayList<Cliente> clientes = new ArrayList<>();

    ArrayList<Pedido> pedido = new ArrayList<>();

    public Produto cadastraNovoProduto(String nome, double preco, int estoque) {
        Produto novoProduto = new Produto(nome, preco, estoque);
        produtos.add(novoProduto);
        return novoProduto;

    }

    public void listarProdutos(){
        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId() + ", Nome: " + produto.getNome() + ", Preço: " + produto.getPreco() + ", Estoque: " + produto.getEstoque());
        }
    }

    public Cliente cadastrarCliente(String cpf, String nome, String email) {
        Cliente cliente = new Cliente(cpf, nome, email);
        clientes.add(cliente);
        return cliente;
    }

    public void listarClientes(){
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf() + ", Email: " + cliente.getEmail());
        }
    }

    public Pedido cadastraNovoPedido (Cliente cliente) {
        Pedido novoPedido = new Pedido(cliente);
        cliente.adicionarPedido(novoPedido);
        pedido.add(novoPedido);
        return novoPedido;
    }

    public void listarPedidos(){
        for (Pedido pedido : pedido){
            System.out.println("ID do Pedido: " + pedido.getId() + ", Cliente: " + pedido.getCliente().getNome() + ", Status: " + pedido.getStatusPedido() + ", Data: " + pedido.getData());
        }
    }

    public Produto buscaProdutoPorId(int id){
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                System.out.println("Produto encontrado: " + produto.getNome());
                return produto;
            }
        }
        System.out.println("Produto não encontrado.");
        return null;
    }
}
