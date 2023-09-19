package modelo;

public class Aula extends Entidade{
    /* Atributos da classe Aula */
    protected int id;
    protected String materia;
    protected String[] descricao;
    protected int capacidade;
    protected int[] idAlunos;
    protected int idProfessor;
    protected int duracao;
    protected boolean frequencia;
    protected String[] dias = new String[7];

    /* Construtor simples para a classe Aula */ 
    public Aula(){
        this.id = 0;
        this.materia = null;
        this.descricao = null;
        this.capacidade = 0;
        this.idAlunos = null;
        this.idProfessor = 0;
        this.duracao = 0;
        this.frequencia = true;         //default: true
    }

    /* Construtor com valores para a classe Aula */ 
    public Aula(int id, String materia, String[] descricao, int capacidade, int[] idAlunos, int idProfessor, int duracao, boolean frequencia, String[] dias){
        this.id = id;
        this.materia = materia;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.idAlunos = new int[capacidade];
        this.idAlunos = idAlunos;
        this.idProfessor = idProfessor;
        this.duracao = duracao;
        this.frequencia = frequencia;
        this.dias = dias;
    }


    /* Getters */
    public int getId(){
        return this.id;
    }
    public String getMateria(){
        return this.materia;
    }
    public String[] getDescricao(){
        return this.descricao;
    }
    public int getCapacidade(){
        return this.capacidade;
    }
    public int[] getIdAlunos(){
        return this.idAlunos;
    }
    public int getIdProfessor(){
        return this.idProfessor;
    }
    public int getDuracao(){
        return this.duracao;
    }
    public boolean getFrequencia(){
        return this.frequencia;
    }
    public String[] getDias(){
        return this.dias;
    }

    /* Getters */
    public void setId(int id){
        this.id = id;
    }
    public void setMateria(String materia){
        this.materia = materia;
    }
    public void setDescricao(String[] descricao){
        this.descricao = descricao;
    }
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }
    public void setIdAlunos(int[] idAlunos){
        this.idAlunos = idAlunos;
    }
    public void setIdProfessor(int idProfessor){
        this.idProfessor = idProfessor;
    }
    public void setDuracao(int duracao){
        this.duracao = duracao;
    }
    public void setFrequencia(boolean frequencia){
        this.frequencia = frequencia;
    }
    public void setDias(String[] dias){
        this.dias = dias;
    }


}
