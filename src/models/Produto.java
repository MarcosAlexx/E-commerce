package models;

import java.util.concurrent.atomic.AtomicInteger;

public class Produto {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private int id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque){
        this.id = COUNTER.incrementAndGet();
        this.nome=nome;
        this.preco=preco;
        this.estoque=estoque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }


    public void diminuiEstoque(int quantidade) {
        this.estoque -= quantidade;
    }
}
