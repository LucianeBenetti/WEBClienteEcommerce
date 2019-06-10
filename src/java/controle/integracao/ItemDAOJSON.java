
package controle.integracao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controle.VO.Item;
import java.util.ArrayList;

public class ItemDAOJSON {   
    
    
     public String serializa(ArrayList<Item> itensEncontrados) {
        Gson gson = new GsonBuilder().create();
        String itemJson = gson.toJson(itensEncontrados);
        return itemJson;
    }    
    
    public ArrayList<Item> desserializa(String s) {
        
        Gson gson = new GsonBuilder().create();
        
        ArrayList<Item> listaDeItensFromJSON = (ArrayList<Item>) gson.fromJson(s,
                        new TypeToken<ArrayList<Item>>() {
                        }.getType());
        return listaDeItensFromJSON;
    }

   
}
