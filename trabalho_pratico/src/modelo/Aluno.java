package modelo;

public class Aluno extends Usuario {
    int[] idAulasInscritas;

    /* Cria um novo Aluno com os dados em branco */
    public Aluno(){
        super();
        this.idAulasInscritas = null;
    }

    /* Contrutor para o Aluno, preenchendo os dados */
    public Aluno(String nome, String sobrenome, String email, int diaNasc, int mesNasc, int anoNasc, int id, String senha, int[] idAulasInscritas){
        super(nome, sobrenome, email, diaNasc, mesNasc, anoNasc, id, senha);
        this.idAulasInscritas = idAulasInscritas;
    }

    /* Getters */
    public int[] getIdAulaInscritas(){
        return this.idAulasInscritas;
    }

    /* Setters */
    public void setIdAulaInscritas(int[] idAulasInscritas){
        this.idAulasInscritas = idAulasInscritas;
    }

}
