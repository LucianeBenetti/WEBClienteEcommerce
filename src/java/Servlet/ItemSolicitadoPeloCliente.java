package Servlet;

import controle.DAO.DAOItem;
import controle.VO.Item;
import controle.integracao.ItemDAOJSON;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemSolicitadoPeloCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String descricao = request.getParameter("descricaoproduto");
        String resourceURI = "http://localhost:8080/EcommerceServico/pesquisaritem";

        String formatedURL = resourceURI;
        String httpParameters = "?descricaoproduto=" + URLEncoder.encode(descricao, "UTF-8");
        URL url = new URL(formatedURL+httpParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("GET");
        InputStream is = con.getInputStream();
        String respose = convertStreamToString(is);
        ItemDAOJSON itemDAOJSON = new ItemDAOJSON();
        ArrayList<Item> itensEncontrados = itemDAOJSON.desserializa(respose);
        System.out.println("O objeto item é: " + itensEncontrados);

        if (itemDAOJSON != null) {
            request.setAttribute("itensencontrados", itensEncontrados);
            String page = request.getSession().getAttribute("usuarioautenticado") == null ? "ResultadoDaPesquisa.jsp" : "WEB-INF/ResultadoDaPesquisaAutenticado.jsp";
            request.getRequestDispatcher(page).forward(request, response);
        }
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
