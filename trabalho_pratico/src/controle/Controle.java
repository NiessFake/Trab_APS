package controle;

import persistencia.*;
import modelo.*;

public abstract class Controle {
    /* Cria um atributo do tipo persisntencia para usar dos métodos lá contidos */
    protected Persistencia persistencia;

    /* Construtor que recebe uma persistencia e atribui ela a esse método */
    public Controle(Persistencia persistencia){
        this.persistencia = persistencia;
    }

    /* Chama o método insere */
    public void insere(Entidade entidade) {
        persistencia.insere(entidade);
    }

}
