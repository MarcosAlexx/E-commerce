package models;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private double precoMomentoCompra;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoMomentoCompra = produto.getPreco();
    }

    public Produto getProduto() {
        return produto;
    }

    public double getPrecoMomentoCompra() {
        return precoMomentoCompra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return this.quantidade * this.precoMomentoCompra;
    }
}
