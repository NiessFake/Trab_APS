package controle;

import org.json.simple.JSONObject;

import modelo.Entidade;
import persistencia.PersistenciaUsuario;

public class ControleUsuario extends Controle {
    protected PersistenciaUsuario pUsuario;

    public ControleUsuario() {
        super(new PersistenciaUsuario());
    }
  
}