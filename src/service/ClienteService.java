package service;

import models.Cliente;

import java.util.ArrayList;

public class ClienteService {

    public ArrayList<Cliente> clientes = new ArrayList<>();

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
}
