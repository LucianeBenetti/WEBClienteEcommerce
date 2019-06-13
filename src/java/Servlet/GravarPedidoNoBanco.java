package Servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controle.VO.Item;
import controle.VO.ItemPedido;
import controle.VO.Usuario;
import controle.integracao.ItemDAOJSON;
import controle.integracao.ItemPedidoDAOJSON;
import controle.integracao.UsuarioDAOJSON;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GravarPedidoNoBanco extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws MalformedURLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Object usuarioAutenticado = request.getSession().getAttribute("usuarioautenticado");
        Object fecharPedido = request.getSession().getAttribute("pedidocompra");
        Object quantidadeItem = request.getSession().getAttribute("quantidades");
        Object total = request.getSession().getAttribute("valortotal");

        Usuario dadosDoUsuario = (Usuario) usuarioAutenticado;
        ArrayList<Item> pedidoFechado = (ArrayList<Item>) fecharPedido;
        ArrayList<Integer> qtidades = (ArrayList<Integer>) quantidadeItem;
        int codigoUsuario = dadosDoUsuario.getCodigoUsuario();
        String codUsuario = Integer.toString(codigoUsuario);
        double valorTotal = new Double((double) total);
        String totalDoPedido = Double.toString(valorTotal);
        String itemJson = null;

        ItemDAOJSON itemDAOJSON = new ItemDAOJSON();
        itemJson = itemDAOJSON.serializa(pedidoFechado);

        Gson gson = new GsonBuilder().create();
        String qtdJson = gson.toJson(qtidades);

        PrintWriter out = response.getWriter();
        out.print(itemJson);
        out.print(qtdJson);

        String resourceURI = "http://localhost:8080/EcommerceServico/gravarpedidonobanco";
        String formatedURL = resourceURI;
        String httpParameters = "?codUsuario=" + URLEncoder.encode(codUsuario, "UTF-8")
                + "&itemJson=" + URLEncoder.encode(itemJson, "UTF-8")
                + "&qtdJson=" + URLEncoder.encode(qtdJson, "UTF-8")
                + "&totalDoPedido=" + URLEncoder.encode(totalDoPedido, "UTF-8");

        URL url = new URL(formatedURL + httpParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("POST");
        InputStream is = con.getInputStream();
        String resp = convertStreamToString(is);

        ItemPedidoDAOJSON itempedidoDAOJSON = new ItemPedidoDAOJSON();
        ArrayList<ItemPedido> itemPedido = itempedidoDAOJSON.desserializa(resp);

//        if (usuarioEncontrado != null) {
//
//
//        } else {
//            Boolean validacao = false;
//            request.getRequestDispatcher("Login.jsp").forward(request, response);
//        }
    }

    private static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

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
