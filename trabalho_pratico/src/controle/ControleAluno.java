package controle;

import modelo.Entidade;
import modelo.Usuario;
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

    public Aluno buscaIDParcial(int id){
        return ((PersistenciaAluno)persistencia).buscaIDParcial(id);
    }

    public int devolveIdPerdido(String email, int dia, int mes, int ano){
        return ((PersistenciaAluno)persistencia).devolveIdPerdido(email, dia, mes, ano);
    }

    public Object[][] textoAlunos(Entidade entidade){
        return ((PersistenciaAluno)persistencia).textoAlunos(entidade);
    }

    public Usuario procuraUsuario(String nome){
        return ((PersistenciaAluno)persistencia).procuraUsuario(nome);
    }
    
}
