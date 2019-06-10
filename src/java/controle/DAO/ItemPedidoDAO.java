package controle.DAO;

import controle.VO.Item;
import controle.VO.ItemPedido;
import controle.VO.PedidoCompra;
import controle.VO.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemPedidoDAO {

    Usuario usuario = new Usuario();
    Item item = new Item();
    PedidoCompra pedidoCompra = new PedidoCompra();
    ItemPedido itemPedido = new ItemPedido();

    public int cadastrarPedidoCompra(int codigoUsuario, double valorTotal) {
        int novoId = 0;
        String sql = "INSERT INTO pedidoCompra (codigoUsuario, dataPedido, valorTotal)"
                + " VALUES (?,?,?)";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);
        try {

            Date data = new Date(System.currentTimeMillis());

            prepStmt.setInt(1, codigoUsuario);
            prepStmt.setDate(2, data);
            prepStmt.setDouble(3, valorTotal);
            prepStmt.executeUpdate();
            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar o Cadastro do Pedido de Compra! Causa: \n: " + e.getMessage());

        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }
        return novoId;

    }

    public int cadastrarPedido(int codigoItem, int novoIdPedidoCompra, int qtdade) {

        int novoId = 0;
        String sql = "INSERT INTO itemPedido (codigoPedido, codigoItem, quantidadeItem)"
                + " VALUES (?,?,?)";
        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);
        try {
            prepStmt.setInt(1, novoIdPedidoCompra);
            prepStmt.setInt(2, codigoItem);
            prepStmt.setInt(3, qtdade);

            prepStmt.executeUpdate();
            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar o Cadastro do Consulta! Causa: \n: " + e.getMessage());

        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }
        return novoId;

    }

    public ArrayList<ItemPedido> buscarItensMaisVendidos() {

        String query = "select codigoItem, sum(quantidadeItem) "
                + " from itemPedido "
                + " group by codigoItem "
                + " order by sum(quantidadeItem) desc";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);

        ArrayList<ItemPedido> itensPedidos = new ArrayList<ItemPedido>();

        try {
            ResultSet resultado = prepStmt.executeQuery(query);

            while (resultado.next()) {
                ItemPedido itemPedido = new ItemPedido();

                Item item = DAOItem.consultarPorCodigoItem(resultado.getInt(1));
                item.getCodigoItem();
                itemPedido.setItem(item);
                itemPedido.setQuantidade(resultado.getInt(2));

                itensPedidos.add(itemPedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itensPedidos;
    }

    public ArrayList<PedidoCompra> BuscarTodosOsPedidos(int usuarioAutenticado) {
        String query = "select * from pedidoCompra where codigoUsuario = ?";

        PedidoCompra pedidoCompra = null;
        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);
        ArrayList<PedidoCompra> pedidosCompra = new ArrayList<PedidoCompra>();
        try {

            prepStmt.setInt(1, usuarioAutenticado);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {

                pedidoCompra = new PedidoCompra();
                pedidoCompra.setCodigoPedido(result.getInt(1));
                Usuario usuario = UsuarioDao.BuscarUsuarioPorCodigoDoUsuario(result.getInt(2));
                usuario.getCodigoUsuario();
                pedidoCompra.setUsuario(usuario);
                pedidoCompra.setDataPedido(result.getDate(3));
                pedidoCompra.setValorTotal(result.getDouble(4));
                pedidosCompra.add(pedidoCompra);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }
      
        return pedidosCompra;
    }

    public boolean cancelarCodigoDoPedidoComora(int codigoPedido) {
        
        boolean sucessoNaExclusao = false;
        
        String query = "DELETE from itemPedido where codigoPedido = ?";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);

        try {
            prepStmt.setInt(1, codigoPedido);
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
