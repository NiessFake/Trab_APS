package modelo;

public class Noticias extends Entidade{
    
    protected String[] titulo;
    protected String data;
    protected String[] texto;
    protected Professor professor;
    protected Aula materia;

    public Noticias(){
        this.titulo = null;
        this.data = null;
        this.texto = null;
        this.professor = null;
        this.materia = null;
    }

    public Noticias(String[] titulo,String data, String[] texto, Professor professor, Aula materia){
        this.titulo = titulo;
        this.data = data;
        this.texto = texto;
        this.professor = professor;
        this.materia = materia;
    }

    /* Getters */
    public String[] getTitulo(){
        return this.titulo;
    }

    public String[] getTexto(){
        return this.texto;
    }
    public String getData(){
        return this.data;
    }
    public Professor getProfessor(){
        return this.professor;
    }
    public Aula getAula(){
        return this.materia;
    }

    /* Setters */
    public void setTitulo(String[] titulo){
        this.titulo = titulo;
    }

    public void setTexto(String[] texto){
        this.texto = texto;
    }
    public void setData(String data){
        this.data = data;
    }
    public void getProfessor(Professor prof){
        this.professor = prof;
    }
    public void getAula(Aula materia){
        this.materia = materia;
    }

    /* Retorna o texto contendo todos os elementos (normalmente usada para testes) */
    @Override
    public String toString(){
        String texto = "Titulo: "+ titulo + "\nData:" + data +"\nProfessor:" + professor +
        "\nMatéria: " + materia + "\nMensagem " + mensagem;
        return super.toString() + texto;
    }
}
