package controle;

import modelo.Entidade;
import modelo.Mensagem;
import persistencia.PersistenciaMensagem;

public class ControleMensagem extends Controle {
    public ControleMensagem() {
        super(new PersistenciaMensagem());
    }
    
    public Mensagem buscaID(int id){
        return ((PersistenciaMensagem)persistencia).buscaID(id);
    }

    public Object[][] textoMensagem(Entidade entidade, int tipo){
        return ((PersistenciaMensagem)persistencia).textoMensagem(entidade, tipo);
    }

    public int devolveMaiorID(){
        return ((PersistenciaMensagem)persistencia).devolveMaiorID();
    }
}
