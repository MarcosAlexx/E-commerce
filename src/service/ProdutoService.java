package service;

import models.Produto;

import java.util.ArrayList;

public class ProdutoService {

    public ArrayList<Produto> produtos = new ArrayList<>();

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
