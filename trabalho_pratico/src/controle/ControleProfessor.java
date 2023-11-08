package controle;

import modelo.Professor;
import modelo.Usuario;
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

    public Professor buscaIDParcial(int id){
        return ((PersistenciaProfessor)persistencia).buscaIDParcial(id);
    }

    public int devolveIdPerdido(String email, int dia, int mes, int ano){
        return ((PersistenciaProfessor)persistencia).devolveIdPerdido(email, dia, mes, ano);
    }

    public Usuario procuraUsuario(String nome){
        return ((PersistenciaProfessor)persistencia).procuraUsuario(nome);
    }

}
