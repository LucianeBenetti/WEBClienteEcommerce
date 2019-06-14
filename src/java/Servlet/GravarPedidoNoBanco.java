package Servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controle.BO.UsuarioBo;
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

        Usuario usuario = new Usuario();
        usuario.setCodigoUsuario(dadosDoUsuario.getCodigoUsuario());
        usuario.setCodigoSeguranca(dadosDoUsuario.getCodigoSeguranca());
        usuario.setLogin(dadosDoUsuario.getLogin());
        usuario.setSenha(dadosDoUsuario.getSenha());
        usuario.setDataValidade(dadosDoUsuario.getDataValidade());
        usuario.setNumeroCartao(dadosDoUsuario.getNumeroCartao());
        String usuarioJSON;
        UsuarioDAOJSON usuarioDAOJSON = new UsuarioDAOJSON();
        usuarioJSON = usuarioDAOJSON.serializaParaJSON(usuario);

        ArrayList<Item> pedidoFechado = (ArrayList<Item>) fecharPedido;
        ArrayList<Integer> qtidades = (ArrayList<Integer>) quantidadeItem;

        usuario.setCodigoUsuario(dadosDoUsuario.getCodigoUsuario());
        double valorTotal = new Double((double) total);
        String totalDoPedido = Double.toString(valorTotal);
        String itemJson = null;

        ItemDAOJSON itemDAOJSON = new ItemDAOJSON();
        itemJson = itemDAOJSON.serializa(pedidoFechado);
        PrintWriter out = response.getWriter();
        out.print(itemJson);

        Gson gson = new GsonBuilder().create();
        String qtdJson = gson.toJson(qtidades);

        out = response.getWriter();
        out.print(qtdJson);

        String resourceURI = "http://localhost:8080/EcommerceServico/gravarpedidonobanco";
        String formatedURL = resourceURI;
        String httpParameters = "?usuarioJSON=" + URLEncoder.encode(usuarioJSON, "UTF-8")
                + "&itemJson=" + URLEncoder.encode(itemJson, "UTF-8")
                + "&qtdJson=" + URLEncoder.encode(qtdJson, "UTF-8")
                + "&totalDoPedido=" + URLEncoder.encode(totalDoPedido, "UTF-8");

        URL url = new URL(formatedURL + httpParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("POST");
        InputStream is = con.getInputStream();
        String resp = convertStreamToString(is);

        ItemPedidoDAOJSON itemPedidoCadastradoDAOJSON = new ItemPedidoDAOJSON();
        ItemPedido itemPedidoCadastradoJSON = itemPedidoCadastradoDAOJSON.desserializaParaJSON(resp);

        if (itemPedidoCadastradoJSON != null) {

            System.out.print("Pedido incluido com sucesso");
            request.setAttribute("nomedousuario", dadosDoUsuario.getLogin());
            request.setAttribute("codigoseguranca", dadosDoUsuario.getCodigoSeguranca());
            request.getRequestDispatcher("WEB-INF/PedidoFechado.jsp").forward(request, response);

       } else {

            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
