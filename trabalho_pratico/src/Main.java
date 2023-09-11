import visao.*;
import modelo.Aluno;
import persistencia.*;

public class Main {
   
    public static void main(String[] args) {
		VisaoMain j = new VisaoMain(); 
		j.menu();
		j.setVisible(true);

		/*Aluno aluno = new Aluno("ADE", "ADE", "ADE", 1, 1, 1, 1, "ADE");
		PersistenciaAluno pAluno = new PersistenciaAluno();

		pAluno.remove(aluno);*/
	}
}	