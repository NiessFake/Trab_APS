package controle;

import modelo.Aula;
import modelo.Entidade;
import persistencia.PersistenciaAula;

public class ControleAula extends Controle {

    public ControleAula(){
        super(new PersistenciaAula());
    }

    public int devolveMaiorID(){
        return ((PersistenciaAula)persistencia).devolveMaiorID();
    }

    public Aula buscaID(int id){
        return ((PersistenciaAula)persistencia).buscaID(id);
    }

    public Aula buscaIDParcial(int id){
        return ((PersistenciaAula)persistencia).buscaIDParcial(id);
    }

    public Object[][] textoTabelas(){
        return ((PersistenciaAula)persistencia).textoTabelas();
    }

    public Object[][] aulasProfessor(Entidade  entidade){
        return ((PersistenciaAula)persistencia).aulasProfessor(entidade);
    }

    public Object[][] textoAlunos(Entidade entidade){
        return ((PersistenciaAula)persistencia).textoAlunos(entidade);
    }

    public int numeroAlunos(Entidade entidade){
        return ((PersistenciaAula)persistencia).numeroAlunos(entidade);
    }

    public int jaInscrito(Entidade entidade, int id_aluno){
        return ((PersistenciaAula)persistencia).jaInscrito(entidade, id_aluno);
    }
}
