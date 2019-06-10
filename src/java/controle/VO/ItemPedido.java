package controle.VO;

public class ItemPedido {

    private int codigoItemPedido;
    private PedidoCompra pedidoCompra;
    private Item item;
    private int quantidade;

    public ItemPedido() {
    }

    public ItemPedido(int codigoItemPedido, PedidoCompra pedidoCompra, Item item, int quantidade) {
        this.codigoItemPedido = codigoItemPedido;
        this.pedidoCompra = pedidoCompra;
        this.item = item;
        this.quantidade = quantidade;
    }

    public int getCodigoItemPedido() {
        return codigoItemPedido;
    }

    public void setCodigoItemPedido(int codigoItemPedido) {
        this.codigoItemPedido = codigoItemPedido;
    }

    public PedidoCompra getPedidoCompra() {
        return pedidoCompra;
    }

    public void setPedidoCompra(PedidoCompra pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido{" + "codigoItemPedido=" + codigoItemPedido + ", pedidoCompra=" + pedidoCompra + ", item=" + item + ", quantidade=" + quantidade + '}';
    }

   }
