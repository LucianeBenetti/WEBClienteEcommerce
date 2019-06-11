
package controle.integracao;

import controle.VO.Item;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PesquisarItemCliente {
    public void clienteServicoGET() throws MalformedURLException, IOException {
        String resourceURI = "http://localhost:8080/WEBServicosEcommerce/pesquisaritem";
        
        String formatedURL = resourceURI;//+ httpParameters;
        URL url = new URL(formatedURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "JSON");
        con.setRequestMethod("get");
        InputStream is = con.getInputStream();
        String respose = convertStreamToString(is);
//        ItemDAOJSON itemDAOJSON = new ItemDAOJSON ();
//        ArrayList<Item> item = itemDAOJSON.desserializa(respose);
//        System.out.println("O objeto item é: " +item);
//        return item;
    }

    private static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    
}
