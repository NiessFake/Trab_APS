package controle;

import modelo.Noticias;
import persistencia.PersistenciaNoticias;

public class ControleNoticias extends Controle{
    public ControleNoticias() {
        super(new PersistenciaNoticias());
    }

    public int devolveMaiorID(){
        return ((PersistenciaNoticias)persistencia).devolveMaiorID();
    }

    public Noticias buscaID(int id){
        return ((PersistenciaNoticias)persistencia).buscaID(id);
    }

    public Object[][] textoNoticias(){
        return ((PersistenciaNoticias)persistencia).textoNoticias();
    }
}
