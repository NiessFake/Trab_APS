package controle;

import modelo.Mensagem;
import persistencia.PersistenciaMensagem;

public class ControleMensagem extends Controle {
    public ControleMensagem() {
        super(new PersistenciaMensagem());
    }
    
    public Mensagem buscaID(int id){
        return ((PersistenciaMensagem)persistencia).buscaID(id);
    }

    public Object[][] textoMensagem(){
        return ((PersistenciaMensagem)persistencia).textoMensagem();
    }

    public int devolveMaiorID(){
        return ((PersistenciaMensagem)persistencia).devolveMaiorID();
    }
}
