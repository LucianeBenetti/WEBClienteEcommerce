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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String resourceURI = "http://localhost:8080/EcommerceServico/validarusuario";

        String formatedURL = resourceURI;
        String httpParameters = "?login=" + URLEncoder.encode(login, "UTF-8") + "&senha=" + URLEncoder.encode(senha, "UTF-8");
        URL url = new URL(formatedURL + httpParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("GET");
        InputStream is = con.getInputStream();
        String resp = convertStreamToString(is);

        UsuarioDAOJSON usuarioDAOJSON = new UsuarioDAOJSON();
        Usuario usuarioEncontrado = usuarioDAOJSON.desserializa(resp);
   
        if (usuarioEncontrado != null) {

            HttpSession session = request.getSession();
            session.setAttribute("usuarioautenticado", usuarioEncontrado);

            request.setAttribute("datavalidade", usuarioEncontrado.getDataValidade());
            request.setAttribute("numerocartao", usuarioEncontrado.getNumeroCartao());
            request.setAttribute("codigoseguranca", usuarioEncontrado.getCodigoSeguranca());
            request.setAttribute("login", usuarioEncontrado.getLogin());
            request.setAttribute("senha", usuarioEncontrado.getSenha());
            request.getRequestDispatcher("WEB-INF/EcommerceValidado.jsp").forward(request, response);

        } else {
            Boolean validacao = false;
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
