package models;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Cliente {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private int id;
    private String cpf;
    private String nome;
    private String email;

    public Cliente(String cpf, String nome, String email){
        this.id = COUNTER.incrementAndGet();
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        this.listaPedidos.add(pedido);
    }
}
