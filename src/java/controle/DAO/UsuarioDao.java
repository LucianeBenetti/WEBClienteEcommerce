package controle.DAO;

import controle.VO.Item;
import controle.VO.PedidoCompra;
import controle.VO.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

public class UsuarioDao {

    static Usuario BuscarUsuarioPorCodigoDoUsuario(int usarioAutenticado) {

        String query = "SELECT * FROM usuario where codigoUsuario = ?";

        Usuario usuario = null;
        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);

        try {
            prepStmt.setInt(1, usarioAutenticado);
            ResultSet resultado = prepStmt.executeQuery();

            while (resultado.next()) {
                usuario = new Usuario();

                usuario.setCodigoUsuario(resultado.getInt(1));
                usuario.setCodigoSeguranca(resultado.getString(2));
                usuario.setDataValidade(resultado.getDate(3));
                usuario.setLogin(resultado.getString(4));
                usuario.setNumeroCartao(resultado.getInt(5));
                usuario.setSenha(resultado.getString(6));

            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a Query de busc de Usuário!Causa: \n: " + e.getMessage());
        } finally {
            ConexaoComBanco.closeStatement(conn);
            ConexaoComBanco.closeConnection(conn);
        }
        System.out.println("controle.DAO.UsuarioDao.BuscarUsuarioPorCodigoDoUsuario()" + usuario);
        return usuario;

    }

    public int cadastrarUsuario(Usuario usuario) {

        int novoId = -1;
        String query = "INSERT INTO usuario (login, senha) VALUES (?,?)";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

        try {
            prepStmt.setString(1, usuario.getLogin());
            prepStmt.setString(2, usuario.getSenha());
            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Cadastro de Produto! Causa: \n: " + e.getMessage());

        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }
        return novoId;
    }

    public Usuario pesquisarUsuario(Usuario usuario) {

        String query = "SELECT * FROM usuario WHERE login = ? and senha = ?";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);

        try {
            prepStmt.setString(1, usuario.getLogin());
            prepStmt.setString(2, usuario.getSenha());
            ResultSet resultado = prepStmt.executeQuery();

            while (resultado.next()) {
                usuario = new Usuario();

                usuario.setCodigoUsuario(resultado.getInt(1));
                usuario.setCodigoSeguranca(resultado.getString(2));
                usuario.setDataValidade(resultado.getDate(3));
                usuario.setLogin(resultado.getString(4));
                usuario.setNumeroCartao(resultado.getInt(5));
                usuario.setSenha(resultado.getString(6));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a Query de validação de Usuário!Causa: \n: " + e.getMessage());
        } finally {
            ConexaoComBanco.closeStatement(conn);
            ConexaoComBanco.closeConnection(conn);
        }
        return usuario;
    }

    public Item pesquisarItem(String descricao) {

        String query = "SELECT * FROM item WHERE descricao = ?";
        Item item = null;

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);

        try {
            prepStmt.setString(1, '%' + descricao + '%');

            ResultSet resultado = prepStmt.executeQuery();

            while (resultado.next()) {
                item = new Item();

                item.setCodigoItem(resultado.getInt("codigoItem"));
                item.setDescricao(resultado.getString("descricao"));
                item.setDetalhes(resultado.getString("detalhes"));
                item.setNome(resultado.getString("nome"));
                item.setValor(resultado.getDouble("valor"));

            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a Query de Consulta de funcionarios!Causa: \n: " + e.getMessage());
        } finally {
            ConexaoComBanco.closeStatement(conn);
            ConexaoComBanco.closeConnection(conn);
        }
        return item;
    }

    public ArrayList<Usuario> listarTodosOsUsuarios() {

        Usuario usuario;
        String query = "select * from usuario";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            ResultSet resultado = prepStmt.executeQuery(query);

            while (resultado.next()) {

                usuario = new Usuario();

                usuario.setCodigoUsuario(resultado.getInt(1));
                usuario.setCodigoSeguranca(resultado.getString(2));
                usuario.setDataValidade(resultado.getDate(3));
                usuario.setLogin(resultado.getString(4));
                usuario.setNumeroCartao(resultado.getInt(5));
                usuario.setSenha(resultado.getString(6));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public boolean atualizarCartaoDoUsuario(Usuario usuario) {

        boolean atualizacao = false;
        System.out.println("usuario.DAO. )" + usuario);
        String query = "UPDATE usuario SET numeroCartao = ? WHERE codigoUsuario = ?";

        Connection conn = ConexaoComBanco.getConnection();
        PreparedStatement prepStmt = ConexaoComBanco.getPreparedStatement(conn, query);

        try {
            prepStmt.setInt(1, usuario.getNumeroCartao());
            prepStmt.setInt(2, usuario.getCodigoUsuario());

            int codigoRetorno = prepStmt.executeUpdate();

            if (codigoRetorno == 1) {
                atualizacao = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de atualização do Cartão! Causa: \n: " + e.getMessage());
        } finally {
            ConexaoComBanco.closePreparedStatement(prepStmt);
            ConexaoComBanco.closeConnection(conn);
        }

        return atualizacao;

    }

}
