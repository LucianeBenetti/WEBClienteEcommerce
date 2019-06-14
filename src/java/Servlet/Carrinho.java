package Servlet;

import controle.VO.Item;
import controle.VO.ItemPedido;
import controle.integracao.ItemDAOJSON;
import controle.integracao.ItemPedidoDAOJSON;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Carrinho extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String resourceURI = "http://localhost:8080/EcommerceServico/carrinho";

        String formatedURL = resourceURI;//+ httpParameters;
        URL url = new URL(formatedURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("GET");
        InputStream is = con.getInputStream();
        String respose = convertStreamToString(is);
        ItemDAOJSON itemDAOJSON = new ItemDAOJSON();
        ArrayList<Item> listaDeItens = itemDAOJSON.desserializa(respose);
    
        if (itemDAOJSON != null) {

            HttpSession session = request.getSession();
            session.setAttribute("listadeitens", listaDeItens);

            request.setAttribute("listadeitens", listaDeItens);
            request.getRequestDispatcher("WEB-INF/Carrinho.jsp").forward(request, response);

        } else {
            PrintWriter out = response.getWriter();
            out.println("Não foi possível listar os Itens para montar o Carrinho de Compras!");
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
