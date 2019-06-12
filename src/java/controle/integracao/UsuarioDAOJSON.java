package controle.integracao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controle.VO.Usuario;
import java.util.ArrayList;

public class UsuarioDAOJSON {
    
    public String serializaParaJSON(ArrayList<Usuario> usuarios) {
        Gson gson = new GsonBuilder().create();
        String json=gson.toJson(usuarios);  
        return json;
    }
    
    public ArrayList<Usuario> desserializa(String s) {
        Gson gson = new GsonBuilder().create();
        ArrayList<Usuario> listaDeUsuariosFromJSON = (ArrayList<Usuario>) gson.fromJson(s,
                new TypeToken<ArrayList<Usuario>>() {
                }.getType());
        return listaDeUsuariosFromJSON;
    }
    
}
