package modelo;

public class Mensagem extends Entidade{
    protected String titulo;
    protected String data;
    protected String texto;
    protected Usuario remetente;
    protected Usuario destinatario;
    protected Aula materia;
    protected int id;

    public Mensagem(){
        super();
        this.titulo = null;
        this.data = null;
        this.texto = null;
        this.destinatario = null;
        this.remetente = null;
        this.materia = null;
        this.id = 0;
    }

    public Mensagem(String titulo, String data, String texto, Usuario remetente, Usuario destinatario){
        super();
        this.titulo = titulo;
        this.data = data;
        this.texto = texto;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.id = 0;
    }

    /* Getters */
    public String getTitulo(){
        return this.titulo;
    }

    public String getTexto(){
        return this.texto;
    }
    public String getData(){
        return this.data;
    }
    public Usuario getRemetente(){
        return this.remetente;
    }
    public Usuario getDestnatario(){
        return this.destinatario;
    }
    public int getId(){
        return this.id;
    }

    /* Setters */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setTexto(String texto){
        this.texto = texto;
    }
    public void setData(String data){
        this.data = data;
    }
    public void setRemet(Usuario remet){
        this.remetente = remet;
    }
    public void setDest(Usuario dest){
        this.destinatario = dest;
    }
    public void setId(int id){
        this.id = id;
    }

    /* Retorna o texto contendo todos os elementos (normalmente usada para testes) */
    @Override
    public String toString(){
        String text = "Titulo: "+ titulo + "\nData:" + data +"\nusuario:" + destinatario +
        "\nMatéria: " + materia + "\nMensagem " + texto;
        return super.toString() + text;
    }
}

