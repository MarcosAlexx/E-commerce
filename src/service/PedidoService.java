package service;

import models.Cliente;
import models.Pedido;

import java.util.ArrayList;

public class PedidoService {

    public ArrayList<Pedido> pedido = new ArrayList<>();

    public Pedido cadastraNovoPedido (Cliente cliente) {
        Pedido novoPedido = new Pedido(cliente);
        cliente.adicionarPedido(novoPedido);
        pedido.add(novoPedido);
        return novoPedido;
    }

    public void listarPedidos(Cliente cliente){
        for (Pedido pedido : pedido){
            System.out.println("ID do Pedido: " + pedido.getId() + ", Cliente: " + pedido.getCliente().getNome() + ", Status: " + pedido.getStatusPedido() + ", Data: " + pedido.getData());
        }
    }
}
