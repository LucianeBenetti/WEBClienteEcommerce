package Servlet;

import controle.BO.UsuarioBo;
import controle.VO.Usuario;
import controle.integracao.UsuarioDAOJSON;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AtualizarCartao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Object usuarioAutenticado = request.getSession().getAttribute("usuarioautenticado");
        Usuario dadosDoUsuario = (Usuario) usuarioAutenticado;

        Usuario usuario = null;
        usuario = new Usuario();
        usuario.setCodigoUsuario(dadosDoUsuario.getCodigoUsuario());
        usuario.setCodigoSeguranca(dadosDoUsuario.getCodigoSeguranca());
        usuario.setLogin(dadosDoUsuario.getLogin());
        usuario.setSenha(dadosDoUsuario.getSenha());
        usuario.setDataValidade(dadosDoUsuario.getDataValidade());
        
        int numeroCartao = new Integer(request.getParameter("numerocartao"));
        usuario.setNumeroCartao(numeroCartao);
        
        String usuarioJSON;
        UsuarioDAOJSON usuarioDAOJSON = new UsuarioDAOJSON();
        usuarioJSON = usuarioDAOJSON.serializaParaJSON(usuario);

        String resourceURI = "http://localhost:8080/EcommerceServico/atualizarcartao";

        String formatedURL = resourceURI;
        String httpParameters = "?usuario=" + URLEncoder.encode(usuarioJSON, "UTF-8");
        URL url = new URL(formatedURL + httpParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("PUT");
        
        InputStream is = con.getInputStream();
        String resp = convertStreamToString(is);

        usuarioDAOJSON = new UsuarioDAOJSON();
        Usuario usuarioCartaoAtualizado = usuarioDAOJSON.desserializa(resp);

        if (usuarioCartaoAtualizado != null) {

            int numeroCartaoOK = 1;
            request.setAttribute("cartaoatualizado", numeroCartaoOK);
            request.getRequestDispatcher("WEB-INF/AtualizarCartao.jsp").forward(request, response);

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
