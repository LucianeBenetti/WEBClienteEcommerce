package controle.BO;

import controle.DAO.ItemPedidoDAO;
import controle.VO.ItemPedido;
import controle.VO.PedidoCompra;
import java.util.ArrayList;

public class ItemPedidoBo {

    ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();

    public int cadastrarPedidoCompra(int codigoUsuario, double valorTotal) {
        return itemPedidoDAO.cadastrarPedidoCompra(codigoUsuario, valorTotal);
    }

    public int CadastrarPedido(int codigoItem, int novoIdPedidoCompra, int qtdade) {
        return itemPedidoDAO.cadastrarPedido(codigoItem, novoIdPedidoCompra, qtdade);
    }

    public ArrayList<ItemPedido> buscarItensMaisVendidos() {
        return itemPedidoDAO.buscarItensMaisVendidos();
    }

    public ArrayList<PedidoCompra> BuscarTodosOsPedidos(int usuarioAutenticado) {
        return itemPedidoDAO.BuscarTodosOsPedidos(usuarioAutenticado);
    }

    public boolean cancelarCodigoDoPedidoCompra(int codigoPedido) {
        return itemPedidoDAO.cancelarCodigoDoPedidoComora(codigoPedido);
    }

   

}
