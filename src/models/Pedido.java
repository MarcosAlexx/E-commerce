package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Pedido {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private int idPedido;
    private Cliente cliente;
    private StatusPedido statusPedido = StatusPedido.CRIADO;
    private LocalDate data = LocalDate.now();

    public Pedido( Cliente cliente) {
        this.idPedido = COUNTER.incrementAndGet();
        this.cliente = cliente;
        this.statusPedido = StatusPedido.CRIADO;
    }

    public int getId() {
        return idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public LocalDate getData() {
        return data;
    }

    ArrayList<ItemPedido> itensDoPedido = new ArrayList<ItemPedido>();

    public void adicionarItem(Produto produto, int quantidade) {

        if (statusPedido == StatusPedido.CANCELADO || statusPedido == StatusPedido.ENVIADO) {
            System.out.println("Não é possível adicionar itens quando o pedido está " + statusPedido + ".");
            return;
        }

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        if (quantidade > produto.getEstoque()) {
            System.out.println("Produto Indisponível no momento, sentimos muito!");
            return;
        }

        // Cria o ItemPedido
        ItemPedido item = new ItemPedido(produto, quantidade);

        // Pedido pede ao Produto para diminuir o estoque (Produto executa)
        try {
            produto.diminuiEstoque(quantidade);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Adiciona o item na lista somente após o produto confirmar a diminuição do estoque
        itensDoPedido.add(item);

        // Atualiza status dinamicamente quando o primeiro item é adicionado
        if (statusPedido == StatusPedido.CRIADO) {
            mudaStatus(); // passa para PAGO
        }
    }

    public void calculaTotalPedidos(){
        double total = 0;
        for (ItemPedido item : itensDoPedido) {
            total += item.getSubtotal();
        }
        System.out.printf("Total do pedido: R$ %.2f%n", total);
    }

    public void mudaStatus(){
        switch (statusPedido) {
            case CRIADO:
                statusPedido = StatusPedido.PAGO;
                break;
            case PAGO:
                statusPedido = StatusPedido.ENVIADO;
                break;
            case ENVIADO:
                System.out.println("O pedido já foi enviado e não pode ser alterado.");
                break;
            case CANCELADO:
                System.out.println("O pedido foi cancelado e não pode ser alterado.");
                break;
        }
    }
    

    public void listaItensPedido() {
        if (itensDoPedido.isEmpty()) {
            System.out.println("Nenhum item no pedido.");
            return;
        }

        System.out.println("Itens do pedido:");
        for (ItemPedido item : itensDoPedido) {
            System.out.println("Produto: " + item.getProduto() + ", Quantidade: " + item.getQuantidade() + " , Preço do Pedido: R$ " + item.getPrecoMomentoCompra());
        }
    }
    
}
