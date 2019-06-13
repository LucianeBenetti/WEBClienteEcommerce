package Servlet;

import controle.BO.ItemPedidoBo;
import controle.BO.PedidoCompraBo;
import controle.VO.PedidoCompra;
import controle.VO.Usuario;
import controle.integracao.PedidoCompraDAOJSON;
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

public class ExcluirPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int codPedido = new Integer(request.getParameter("codigopedido"));

        String codigoPedido = Integer.toString(codPedido);
        String resourceURI = "http://localhost:8080/EcommerceServico/excluirpedido";

        String formatedURL = resourceURI;
        String httpParameters = "?codigopedido=" + URLEncoder.encode(codigoPedido, "UTF-8");
        URL url = new URL(formatedURL + httpParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("DELETE");
        InputStream is = con.getInputStream();
        String resp = convertStreamToString(is);

        PedidoCompraDAOJSON pedidoCompraDAOJSON = new PedidoCompraDAOJSON();
        PedidoCompra pedidoCompraCancelado = pedidoCompraDAOJSON.desserializa(resp);
    
        if (pedidoCompraCancelado != null) {

            request.setAttribute("pedidodecompracancelado", pedidoCompraCancelado);
            request.getRequestDispatcher("WEB-INF/ExibirTodosOsPedidos.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
