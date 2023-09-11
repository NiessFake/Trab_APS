package persistencia;

import modelo.*;

public interface Persistencia {
    
    public abstract void insere(Entidade entidade);
    public abstract void remove(Entidade entidade);
}
