package controle;

import persistencia.PersistenciaUsuario;

public class ControleUsuario extends Controle {
    protected PersistenciaUsuario pUsuario;

    public ControleUsuario() {
        super(new PersistenciaUsuario());
    }
  
}