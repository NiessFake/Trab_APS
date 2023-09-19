package controle;

import org.json.simple.JSONObject;

import modelo.Aluno;
import modelo.Entidade;
import persistencia.PersistenciaAluno;

public class ControleAluno extends Controle{
    protected PersistenciaAluno pAluno;

    public ControleAluno() {
        super(new PersistenciaAluno());
    }

    public void caminhoExiste(Entidade entidade){
        pAluno.caminhoExiste();
    }

    public void escreveArquivo(JSONObject jsonObject){
        pAluno.escreveArquivo(jsonObject);
    }

    public int devolveMaiorID(){
        return pAluno.devolveMaiorID();
    }

    public Aluno buscaID(int id){
        return pAluno.buscaID(id);
    }

}
