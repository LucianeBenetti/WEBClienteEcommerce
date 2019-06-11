package Servlet;

import controle.DAO.DAOItem;
import controle.VO.Item;
import controle.integracao.ItemDAOJSON;
import controle.integracao.PesquisarItemCliente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisarItemJSON extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String descricaoProduto = request.getParameter("descricaoproduto");
        request.setAttribute("descricaoproduto", descricaoProduto);

        PesquisarItemCliente pesquisarItem = new PesquisarItemCliente();

        pesquisarItem.clienteServicoGET();

        // if (itemDAOJSON != null) {
        // request.setAttribute("itensencontrados", itensEncontrados);
//            String page = request.getSession().getAttribute("usuarioautenticado") == null ? "ResultadoDaPesquisa.jsp" : "WEB-INF/ResultadoDaPesquisaAutenticado.jsp";
//           request.getRequestDispatcher(page).forward(request, response);
        //    }
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
