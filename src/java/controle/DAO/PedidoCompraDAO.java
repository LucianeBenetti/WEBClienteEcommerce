/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 80119050
 */
public class PedidoCompraDAO {

    public boolean excluirPedidoDoUsuario(int codigoDoPedido) {
        boolean sucessoNaExclusao = false;
        
        String query = "DELETE from pedidoCompra where codigoPedido = ? ";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);

        try {
            prepStmt.setInt(1, codigoDoPedido);
            int codigoRetorno = prepStmt.executeUpdate();
            if (codigoRetorno == 1) {
                sucessoNaExclusao = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Exclusão do Código do Pedido! Causa: \n: " + e.getMessage());
        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }
        return sucessoNaExclusao;
    }
    
}
