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

    public Object[][] textoTabelas(){
        return ((PersistenciaAula)persistencia).textoTabelas();
    }

    public Object[][] aulasProfessor(Entidade  entidade){
        return ((PersistenciaAula)persistencia).aulasProfessor(entidade);
    }

    public int numeroAlunos(Entidade entidade){
        return ((PersistenciaAula)persistencia).numeroAlunos(entidade);
    }
}
