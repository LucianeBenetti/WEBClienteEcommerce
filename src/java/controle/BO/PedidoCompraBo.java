/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.BO;

import controle.DAO.PedidoCompraDAO;

/**
 *
 * @author Marco
 */
public class PedidoCompraBo {

    PedidoCompraDAO pedidoCompraDAO = new PedidoCompraDAO();
    
    public boolean excluirPedidoDoUsuario(int codigoPedido) {
        return pedidoCompraDAO.excluirPedidoDoUsuario(codigoPedido);
    }
    
}
