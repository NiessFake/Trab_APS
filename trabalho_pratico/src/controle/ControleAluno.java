package controle;

import modelo.Aluno;
import persistencia.PersistenciaAluno;

public class ControleAluno extends Controle{

    public ControleAluno() {
        super(new PersistenciaAluno());
    }

    public int devolveMaiorID(){
        return ((PersistenciaAluno)persistencia).devolveMaiorID();
    }

    public Aluno buscaID(int id){
        return ((PersistenciaAluno)persistencia).buscaID(id);
    }

    public int devolveIdPerdido(String email, int dia, int mes, int ano){
        return ((PersistenciaAluno)persistencia).devolveIdPerdido(email, dia, mes, ano);
    }

    public Object[][] textoAlunos(){
        return ((PersistenciaAluno)persistencia).textoAlunos();
    }
    
}
