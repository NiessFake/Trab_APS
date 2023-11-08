package persistencia;

import org.json.simple.JSONObject;

import modelo.*;

public interface Persistencia {
    
    public abstract void insere(Entidade entidade);
    public abstract void remove(Entidade entidade, boolean condicao);
    public abstract void caminhoExiste();
    public abstract void escreveArquivo(JSONObject escreve);
}
