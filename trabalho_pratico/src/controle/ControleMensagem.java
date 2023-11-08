package controle;

import java.util.ArrayList;

import modelo.Entidade;
import modelo.Mensagem;
import persistencia.PersistenciaMensagem;

public class ControleMensagem extends Controle {
    public ControleMensagem() {
        super(new PersistenciaMensagem());
    }

    public ArrayList<Mensagem> buscaMensagens(String usuario){
        return ((PersistenciaMensagem)persistencia).buscaMensagens(usuario);
    }
}
