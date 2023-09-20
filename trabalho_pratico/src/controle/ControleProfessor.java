package controle;

import modelo.Professor;
import persistencia.PersistenciaProfessor;

public class ControleProfessor extends Controle{

    public ControleProfessor() {
        super(new PersistenciaProfessor());
    }

    public int devolveMaiorID(){
        return ((PersistenciaProfessor)persistencia).devolveMaiorID();
    }

    public Professor buscaID(int id){
        return ((PersistenciaProfessor)persistencia).buscaID(id);
    }

    public int devolveIdPerdido(String email, int dia, int mes, int ano){
        return ((PersistenciaProfessor)persistencia).devolveIdPerdido(email, dia, mes, ano);
    }

}
