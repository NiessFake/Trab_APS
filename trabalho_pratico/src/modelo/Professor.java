package modelo;

public class Professor extends Usuario {
    int[] idAulasMinistradas;

    /* Cria um novo Professor com os dados em branco */
    public Professor(){
        super();
        this.idAulasMinistradas = null;
    }

    /* Contrutor para o Professor, preenchendo os dados */
    public Professor(String nome, String sobrenome, String email, int diaNasc, int mesNasc, int anoNasc, int id, String senha, int[] idAulasMinistradas){
        super(nome, sobrenome, email, diaNasc, mesNasc, anoNasc, id, senha);
        this.idAulasMinistradas = idAulasMinistradas;
    }

    /* Getters */
    public int[] getIdAulaMinistradas(){
        return this.idAulasMinistradas;
    }

    /* Setters */
    public void setIdAulaMinistradas(int[] idAulasMinistradas){
        this.idAulasMinistradas = idAulasMinistradas;
    }
}
