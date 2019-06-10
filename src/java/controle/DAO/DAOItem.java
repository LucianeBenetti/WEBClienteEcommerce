package controle.DAO;

import controle.VO.Item;
import controle.VO.PedidoCompra;
import controle.VO.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOItem {

    static Item consultarPorCodigoItem(int codigoItem) {
        String query = "SELECT *from item " + " where codigoItem = ?";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);
        Item item = null;

        try {
            prepStmt.setInt(1, codigoItem);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                item = new Item();
                item.setCodigoItem(result.getInt(1));
                item.setDescricao(result.getString(2));
                item.setDetalhes(result.getString(3));
                item.setNome(result.getString(4));
                item.setValor(result.getDouble(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }
        return item;
    }

           
    public ArrayList<Item> pesquisarItem(String descricaoProduto) {

        String query = "SELECT * from item where descricao like ? ";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);
        Item item = null;
        ArrayList<Item> itens = new ArrayList<>();

        try {
            prepStmt.setString(1, '%' + descricaoProduto + '%');
            ResultSet result = prepStmt.executeQuery();
            while (result.next()) {
                item = new Item();
                item.setCodigoItem(result.getInt(1));
                item.setDescricao(result.getString(2));
                item.setDetalhes(result.getString(3));
                item.setNome(result.getString(4));
                item.setValor(Double.parseDouble(result.getString(5)));
                itens.add(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }
        return itens;

    }

    public ArrayList<Item> buscarItensParaAdiconarAoCarrinho() {
        String query = "SELECT * from item ";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);
        Item item = null;

        ArrayList<Item> itens = new ArrayList<>();
        try {
            ResultSet result = prepStmt.executeQuery();
            while (result.next()) {
                item = new Item();
                item.setCodigoItem(result.getInt(1));
                item.setDescricao(result.getString(2));
                item.setDetalhes(result.getString(3));
                item.setNome(result.getString(4));
                item.setValor(Double.parseDouble(result.getString(5)));
                itens.add(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }
        return itens;
    }

}
