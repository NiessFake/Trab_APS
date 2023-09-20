package controle;

import persistencia.*;

import org.json.simple.JSONObject;

import modelo.*;

public abstract class Controle {
    /* Cria um atributo do tipo persisntencia para usar dos métodos lá contidos */
    protected Persistencia persistencia;

    /* Construtor que recebe uma persistencia e atribui ela a esse método */
    public Controle(Persistencia persistencia){
        this.persistencia = persistencia;
    }

    /* Chama o metodo insere */
    public void insere(Entidade entidade) {
        persistencia.insere(entidade);
    }

    /* Chama o metodo remove*/
    public void remove(Entidade entidade, Boolean bool){
        persistencia.remove(entidade, bool);
    }

    /* Chama o meteodo caminhoExiste */
    public void caminhoExiste(){
        persistencia.caminhoExiste();
    }
    
    /* Chama o metodo escreveArquivo */
    public void escreveArquivo(JSONObject escreve){
        persistencia.escreveArquivo(escreve);
    }

}
