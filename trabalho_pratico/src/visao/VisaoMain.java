package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import controle.*;
import modelo.Usuario;

public class VisaoMain extends JFrame {
    Usuario usuario = new Usuario();

    /* Cria um atributo do tipo Controle para acessar as funções */
    private ControleUsuario cUsuario;
    private ControleAula cAula;

    /* Atributo que vai guardar a única instância da interface */
    private static VisaoMain uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_aulas = new JPanel();
    JPanel jpanel_noticias = new JPanel();

    /* Botões */
    JButton bt_login = new JButton("LOGIN");
    JButton bt_juntese = new JButton("JUNTE-SE");
    JButton bt_aula = new JButton("AULA");
    JButton bt_noticias = new JButton("NOTICIAS");
    JButton bt_projeto = new JButton("PROJETO");

    /* Labels */
    JLabel label_bem_vindos = new JLabel("SEJAM BEM-VINDES");
    JLabel label_aulas = new JLabel("CONHECA AS AULAS");
    JLabel label_noticias = new JLabel("CONFIRA AS  NOVIDADES");

    /* Text Areas */
    JTextArea tArea_aulas = new JTextArea();
    JTextArea tArea_noticias = new JTextArea();

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,15);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecalho = new Color(0,204,155);

    /* Contrução do JFrame que será usado */
    public VisaoMain(){
        setSize(750,600);
        setTitle("TP Analise e Projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /* Cria uma instancia única para essa interface (Padrao Singleton) */
    public static VisaoMain getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoMain();
		return uniqueInstance;
	}

    /* Interface do cabecalho */
    public void cabecalho(){
        setVisible(false);
        /* Botões */
        bt_login.setFont(texto_padrao);
        bt_login.setBounds(520,30,100,40);
        bt_login.setBackground(Color.white);
		bt_login.setForeground(Color.black);
        bt_login.addActionListener(this::login);

        bt_juntese.setFont(texto_padrao);
        bt_juntese.setBounds(625,30,100,40);
        bt_juntese.setBackground(Color.white);
		bt_juntese.setForeground(Color.black);
        bt_juntese.addActionListener(this::cadastro);

        bt_projeto.setFont(texto_titulo);
        bt_projeto.setBounds(20, 30,200,50);
        bt_projeto.setBackground(cor_cabecalho);
		bt_projeto.setForeground(Color.black);
        bt_projeto.setBorderPainted(false);

        /* Paineis */
        jpanel_fundo.setLayout(null);
        jpanel_fundo.setBackground(cor_fundo);
        jpanel_fundo.setVisible(true);
        jpanel_fundo.setSize(750, 600);
        jpanel_fundo.setLocation(0, 0);

        jpanel_cabecalho.setLayout(null);
        jpanel_cabecalho.setBackground(cor_cabecalho);
        jpanel_cabecalho.setVisible(true);
        jpanel_cabecalho.setSize(750, 100);
        jpanel_cabecalho.setLocation(0, 0);

        /* Adiciona os elementos no cabecalho */
        jpanel_cabecalho.add(bt_login);
        jpanel_cabecalho.add(bt_juntese);
        jpanel_cabecalho.add(bt_projeto);
        
        add(jpanel_cabecalho);
        add(jpanel_fundo);

        setVisible(true);
    }
    
    public void menu(){
        setVisible(true);

        /* Chama a funcao que adiciona o cabecalho */
        cabecalho();

        /* instancia ControleAula e Usuario */
        this.cUsuario = new ControleUsuario();
        this.cAula = new ControleAula();

        /* BOTOES */
        bt_aula.setFont(texto_padrao);
        bt_aula.setBounds(75, 210,100,30);
        bt_aula.setBackground(cor_cabecalho);
		bt_aula.setForeground(Color.white);
		bt_aula.setBorderPainted(false);
        bt_aula.addActionListener(this::aula);

        bt_noticias.setFont(texto_padrao);
        bt_noticias.setBounds(75, 210,100,30);
        bt_noticias.setBackground(cor_cabecalho);
		bt_noticias.setForeground(Color.white);
		bt_noticias.setBorderPainted(false);

        /* LABELS */
        label_bem_vindos.setFont(texto_titulo);
        label_bem_vindos.setBounds(207,125,335,50);

        label_aulas.setFont(texto_sub_titulo);
        label_aulas.setBounds(40,20,170,20);

        label_noticias.setFont(texto_sub_titulo);
        label_noticias.setBounds(19,20,215,20);

        /* Text Areas */
        tArea_aulas.setText("Temos uma grande variedade de aulas disponiveis, venha conferir, se increver e crescer com a gente");
        tArea_aulas.setFont(texto_padrao);
        tArea_aulas.setBounds(25,60,200,135);
        tArea_aulas.setLineWrap(true);
        tArea_aulas.setWrapStyleWord(true);
        tArea_aulas.setEditable(false);

        tArea_noticias.setText("Venha acompanhas as ultimas noticias e avisos da semana.");
        tArea_noticias.setFont(texto_padrao);
        tArea_noticias.setBounds(25,60,200,135);
        tArea_noticias.setLineWrap(true);
        tArea_noticias.setWrapStyleWord(true);
        tArea_noticias.setEditable(false);

        /* Paineis */
        jpanel_aulas.setLayout(null);
        jpanel_aulas.setBackground(Color.WHITE);
        jpanel_aulas.setVisible(true);
        jpanel_aulas.setSize(260, 275);
        jpanel_aulas.setLocation(83, 300);

        jpanel_noticias.setLayout(null);
        jpanel_noticias.setBackground(Color.WHITE);
        jpanel_noticias.setVisible(true);
        jpanel_noticias.setSize(250, 275);
        jpanel_noticias.setLocation(417, 300);
        
        /* Adiciona no Layout */
        jpanel_aulas.add(bt_aula);        
        jpanel_aulas.add(label_aulas);  
        jpanel_aulas.add(tArea_aulas);

        jpanel_noticias.add(bt_noticias);
        jpanel_noticias.add(label_noticias);
        jpanel_noticias.add(tArea_noticias);
        
        jpanel_fundo.add(label_bem_vindos);
        jpanel_fundo.add(jpanel_aulas);
        jpanel_fundo.add(jpanel_noticias);
    }

    /* Faz com que ao apertar o botao seja redirecionado para o login */
    private void login(ActionEvent actionEvent){
        /* REMOVE O LAYOUT ANTERIOR */	
        jpanel_aulas.remove(bt_aula);        
        jpanel_aulas.remove(label_aulas);  
        jpanel_aulas.remove(tArea_aulas);

        jpanel_noticias.remove(bt_noticias);
        jpanel_noticias.remove(label_noticias);
        jpanel_noticias.remove(tArea_noticias);
        
        jpanel_cabecalho.remove(bt_login);
        jpanel_cabecalho.remove(bt_juntese);
        jpanel_cabecalho.remove(bt_projeto);

        jpanel_fundo.remove(label_bem_vindos);
        jpanel_fundo.remove(jpanel_aulas);
        jpanel_fundo.remove(jpanel_noticias);

        remove(jpanel_cabecalho);
        remove(jpanel_fundo);

        /* Chama a instancia unica do VisaoUsuario e vai até ela na funcao login */
        VisaoUsuario.getInstance().login(cUsuario);

        setVisible(false);
    }

    /* Faz com que ao apertar o botao seja redirecionado para o cadastro */
    private void cadastro(ActionEvent actionEvent){
        /* REMOVE O LAYOUT ANTERIOR */
        jpanel_aulas.remove(bt_aula);        
        jpanel_aulas.remove(label_aulas);  
        jpanel_aulas.remove(tArea_aulas);

        jpanel_noticias.remove(bt_noticias);
        jpanel_noticias.remove(label_noticias);
        jpanel_noticias.remove(tArea_noticias);
        
        jpanel_cabecalho.remove(bt_login);
        jpanel_cabecalho.remove(bt_juntese);
        jpanel_cabecalho.remove(bt_projeto);

        jpanel_fundo.remove(label_bem_vindos);
        jpanel_fundo.remove(jpanel_aulas);
        jpanel_fundo.remove(jpanel_noticias);

        remove(jpanel_cabecalho);
        remove(jpanel_fundo);

        /* Chama a instancia unica do VisaoUsuario e vai até ela na funcao cadastro */
        VisaoUsuario.getInstance().cadastro(cUsuario);
        
        setVisible(false);
    }

     /* Faz com que ao apertar o botao seja redirecionado para o cadastro */
    private void aula(ActionEvent actionEvent){
        /* REMOVE O LAYOUT ANTERIOR */
        remove(bt_juntese);
        remove(bt_login);
        jpanel_fundo.remove(jpanel_cabecalho);
        remove(jpanel_fundo);

        usuario.setId(0);
        /* Chama a instancia unica do VisaoUsuario e vai até ela na funcao cadastro */
        VisaoAula.getInstance().menuAulas(cAula,usuario,0);
        
        setVisible(false);
    }
}
