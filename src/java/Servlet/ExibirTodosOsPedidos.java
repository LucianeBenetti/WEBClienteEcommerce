package Servlet;

import controle.BO.UsuarioBo;
import controle.VO.ItemPedido;
import controle.VO.PedidoCompra;
import controle.VO.Usuario;
import controle.integracao.ItemPedidoDAOJSON;
import controle.integracao.PedidoCompraDAOJSON;
import controle.integracao.UsuarioDAOJSON;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExibirTodosOsPedidos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Object usuarioAutenticado = request.getSession().getAttribute("usuarioautenticado");
        Usuario dadosDoUsuario = (Usuario) usuarioAutenticado;

        Usuario usuario = null;
        UsuarioBo usuarioBo = null;

        usuario = new Usuario();
        usuario.setCodigoUsuario(dadosDoUsuario.getCodigoUsuario());
        usuario.setCodigoSeguranca(dadosDoUsuario.getCodigoSeguranca());
        usuario.setLogin(dadosDoUsuario.getLogin());
        usuario.setSenha(dadosDoUsuario.getSenha());
        usuario.setDataValidade(dadosDoUsuario.getDataValidade());
        usuario.setNumeroCartao(dadosDoUsuario.getNumeroCartao());
        String usuarioJSON;
        UsuarioDAOJSON usuarioDAOJSON = new UsuarioDAOJSON();
        usuarioJSON = usuarioDAOJSON.serializaParaJSON(usuario);

        String resourceURI = "http://localhost:8080/EcommerceServico/listartodospedidos";

        String formatedURL = resourceURI;
        String httpParameters = "?usuarioJSON=" + URLEncoder.encode(usuarioJSON, "UTF-8");
        URL url = new URL(formatedURL + httpParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("GET");
        InputStream is = con.getInputStream();
        String resp = convertStreamToString(is);

        PedidoCompraDAOJSON pedidoCompraDAOJSON = new PedidoCompraDAOJSON();
        ArrayList<PedidoCompra> pedidosCompra = pedidoCompraDAOJSON.desserializaExcluir(resp);

        if (pedidosCompra != null) {
            
            Usuario pedidosDoUsuario = (Usuario) usuario;
            int codigoUsuarioAutenticado = pedidosDoUsuario.getCodigoUsuario();
            ArrayList<PedidoCompra> pedidosDeCompraDoUsuario = new ArrayList<PedidoCompra>();
            int codigoDoUsuario = 0;

            for (int i = 0; i < pedidosCompra.size(); i++) {
                codigoDoUsuario = pedidosCompra.get(i).getUsuario().getCodigoUsuario();

                if (codigoUsuarioAutenticado == codigoDoUsuario) {

                    int codigoPedidoDoUsuario = pedidosCompra.get(i).getCodigoPedido();
                    Date dataDoPedidoDoUsuario = (Date) pedidosCompra.get(i).getDataPedido();
                    double valorTotalDoPedidoDoUsuario = pedidosCompra.get(i).getValorTotal();
                    String nomeDoUsuario = dadosDoUsuario.getLogin();

                    PedidoCompra pedidosDoUsuarioAutenticado = new PedidoCompra(codigoPedidoDoUsuario, dadosDoUsuario, dataDoPedidoDoUsuario, valorTotalDoPedidoDoUsuario);
                    pedidosDeCompraDoUsuario.add(pedidosDoUsuarioAutenticado);
                    request.setAttribute("dadosdopedidodousuario", pedidosDeCompraDoUsuario);
                }
            }
        }
        request.getRequestDispatcher("WEB-INF/ExibirTodosOsPedidos.jsp").forward(request, response);
 
    }

    private static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
