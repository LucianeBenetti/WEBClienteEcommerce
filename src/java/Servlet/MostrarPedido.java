package Servlet;

import controle.VO.Item;
import controle.VO.Usuario;
import controle.integracao.ItemDAOJSON;
import controle.integracao.UsuarioDAOJSON;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MostrarPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Object listaDeItens = request.getSession().getAttribute("listadeitens");
        Object usuarioAutenticado = request.getSession().getAttribute("usuarioautenticado");

        Double valorTotalDoItem = 0.0;
        Double valorTotal = 0.0;
        Usuario dadosDoUsuario = (Usuario) usuarioAutenticado;
        String nomeUsuario = dadosDoUsuario.getLogin();
        int numeroCartao = dadosDoUsuario.getNumeroCartao();

        if (listaDeItens != null) {

            ArrayList<Item> itens = (ArrayList<Item>) listaDeItens;
            ArrayList<Item> pedidoCompra = new ArrayList<Item>();
            ArrayList<Integer> quantidades = new ArrayList<Integer>();
            DecimalFormat df = new DecimalFormat("0.00");

            for (int i = 0; i < itens.size(); i++) {

                int codigoDoItem = itens.get(i).getCodigoItem();
                String descricaoDoItem = itens.get(i).getDescricao();
                String detalheDoItem = itens.get(i).getDetalhes();
                String nomeDoItem = itens.get(i).getNome();
                Double valorDoItem = itens.get(i).getValor();
                String quantidade = request.getParameter("quantidade_" + i);

                int qtidade = Integer.valueOf(quantidade);
                quantidades.add(qtidade);

                Item item = new Item(codigoDoItem, descricaoDoItem, detalheDoItem, nomeDoItem, valorDoItem);
                pedidoCompra.add(item);

                valorTotalDoItem = valorDoItem * qtidade;
                valorTotal += valorTotalDoItem;

                request.setAttribute("valortotal", df.format(valorTotal));
                request.setAttribute("nomeusuario", nomeUsuario);
                request.setAttribute("numerocartao", numeroCartao);
            }
            HttpSession session = request.getSession();
            session.setAttribute("pedidocompra", pedidoCompra);
            session.setAttribute("quantidades", quantidades);
            session.setAttribute("valortotal", valorTotal);

            request.setAttribute("quantidades", quantidades);
            request.setAttribute("pedidocompra", pedidoCompra);
        }
        request.getRequestDispatcher("WEB-INF/MostrarPedido.jsp").forward(request, response);

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
