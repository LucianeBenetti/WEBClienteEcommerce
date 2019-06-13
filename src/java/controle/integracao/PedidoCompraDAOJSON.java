package controle.integracao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controle.VO.ItemPedido;
import controle.VO.PedidoCompra;
import controle.VO.Usuario;
import java.util.ArrayList;

public class PedidoCompraDAOJSON {
    
    public String serializa(ArrayList<PedidoCompra> pedidosEncontrados) {
        Gson gson = new GsonBuilder().create();
        String itemJson = gson.toJson(pedidosEncontrados);
        return itemJson;
    }    
    
     public PedidoCompra desserializa(String jsonString) {

        Gson gson = new GsonBuilder().create();
        PedidoCompra pedidoCompraFromJSON = (PedidoCompra) gson.fromJson(jsonString, PedidoCompra.class);
        return pedidoCompraFromJSON;
    }
     
     public ArrayList<PedidoCompra> desserializaExcluir(String s) {
        
        Gson gson = new GsonBuilder().create();
        
        ArrayList<PedidoCompra> listaDePedidosFromJSON = (ArrayList<PedidoCompra>) gson.fromJson(s,
                        new TypeToken<ArrayList<PedidoCompra>>() {
                        }.getType());
        return listaDePedidosFromJSON;
    }
}
