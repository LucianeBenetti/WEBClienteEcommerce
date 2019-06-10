package controle.BO;

import controle.DAO.PedidoCompraDAO;
import controle.DAO.UsuarioDao;
import controle.VO.Item;
import controle.VO.ItemPedido;
import controle.VO.Usuario;
import java.util.ArrayList;

public class UsuarioBo {

    UsuarioDao usuarioDao = new UsuarioDao();
    PedidoCompraDAO pedidoCompraDAO = new PedidoCompraDAO(); 
    Item item = new Item();

    public int cadastrarUsuario(Usuario usuario) {
        int novoId;
        if (usuarioDao.pesquisarUsuario(usuario) != null) {
            novoId = 0;
        } else {
            novoId = usuarioDao.cadastrarUsuario(usuario);
        }
        return novoId;
    }

    public Usuario validarUsuario(Usuario usuario) {
  
        usuario = usuarioDao.pesquisarUsuario(usuario);
        if (usuario.getCodigoUsuario() == 0) {
            return null;
        } else {
               
            return usuario;
        }
    }

    public Item pesquisarItem(String descricao) {

        if (usuarioDao.pesquisarItem(descricao) == null) {
            return null;
        } else {
            return item;
        }
    }

    public ArrayList<Usuario> PesquisarTodosUsuarios() {
        return usuarioDao.listarTodosOsUsuarios();
    }

    public boolean atualizarCartaoDoUsuario(Usuario usuario) {

        boolean atualizacao = false;

        if (usuarioDao.atualizarCartaoDoUsuario(usuario)) {
            atualizacao = true;
            return atualizacao;
        }

        return atualizacao;
    }

    public boolean excluirPedidoDoUsuario(int codigoDoPedido) {
        return pedidoCompraDAO.excluirPedidoDoUsuario(codigoDoPedido);
    }

       
}
