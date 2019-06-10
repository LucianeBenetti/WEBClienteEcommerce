package controle.integracao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controle.VO.Usuario;
import java.util.ArrayList;

public class UsuarioDAOJSON {
    
    public String serializaParaJSON(ArrayList<Usuario> usuarios) {
        Gson gson = new GsonBuilder().create();
        String json=gson.toJson(usuarios);  
        return json;
    }
    
}
