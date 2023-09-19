package controle;

import org.json.simple.JSONObject;

import modelo.Entidade;
import modelo.Professor;
import persistencia.PersistenciaProfessor;

public class ControleProfessor extends Controle{
    protected PersistenciaProfessor pProfessor;

    public ControleProfessor() {
        super(new PersistenciaProfessor());
    }

    public void caminhoExiste(Entidade entidade){
        pProfessor.caminhoExiste();
    }

    public void escreveArquivo(JSONObject jsonObject){
        pProfessor.escreveArquivo(jsonObject);
    }

    public int devolveMaiorID(){
        return pProfessor.devolveMaiorID();
    }

    public Professor buscaID(int id){
        return pProfessor.buscaID(id);
    }

}
