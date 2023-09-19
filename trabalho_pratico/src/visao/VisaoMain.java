package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.*;
import controle.*;
import modelo.Usuario;

public class VisaoMain extends JFrame {
    Usuario usuario = new Usuario();

    /* Cria um atributo do tipo Controle para acessar as funções */
    private ControleUsuario cUsuario; //acho que vai ter q criar um desses para cada

    /* Atributo que vai guardar a única instância da interface */
    private static VisaoMain uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();

    /* Botões */
    JButton bt_login = new JButton("Login");
    JButton bt_juntese = new JButton("Junte-se");
    JButton bt_aula = new JButton("AULA");

    /* Labels */
    JLabel projeto = new JLabel("PROJETO");

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
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

        /* Labels */
        projeto.setFont(texto_titulo);
        projeto.setBounds(20, 30,150,50);

        /* Paineis */
        jpanel_cabecalho.setLayout(null);
        jpanel_cabecalho.setBackground(cor_cabecalho);
        jpanel_cabecalho.setVisible(true);
        jpanel_cabecalho.setSize(750, 100);
        jpanel_cabecalho.setLocation(0, 0);

        jpanel_fundo.setLayout(null);
        jpanel_fundo.setBackground(cor_fundo);
        jpanel_fundo.setVisible(true);
        jpanel_fundo.setSize(750, 600);
        jpanel_fundo.setLocation(0, 100);

        /* Adiciona os elementos no cabecalho */
        jpanel_fundo.add(bt_login);
        jpanel_fundo.add(bt_juntese);
        jpanel_fundo.add(projeto);
        
        jpanel_fundo.add(jpanel_cabecalho);

        add(jpanel_fundo);
    }
    
    public void menu(){
        setVisible(true);

        /* Chama a funcao que adiciona o cabecalho */
        cabecalho();

        /* BOTOES */
        bt_aula.setFont(texto_padrao);
        bt_aula.setBounds(400, 250,100,40);
        bt_aula.setBackground(Color.white);
		bt_aula.setForeground(Color.black);
        bt_aula.addActionListener(this::aula);

        jpanel_fundo.add(bt_aula);
    }

    /* Faz com que ao apertar o botao seja redirecionado para o login */
    private void login(ActionEvent actionEvent){
        /* REMOVE O LAYOUT ANTERIOR */	
        remove(bt_juntese);
        remove(bt_login);
        jpanel_fundo.remove(jpanel_cabecalho);
        remove(jpanel_fundo);

        /* Chama a instancia unica do VisaoUsuario e vai até ela na funcao login */
        VisaoUsuario.getInstance().login(cUsuario);

        setVisible(false);
    }

    /* Faz com que ao apertar o botao seja redirecionado para o cadastro */
    private void cadastro(ActionEvent actionEvent){
        /* REMOVE O LAYOUT ANTERIOR */
        remove(bt_juntese);
        remove(bt_login);
        jpanel_fundo.remove(jpanel_cabecalho);
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
        VisaoAula.getInstance().menuAulas(usuario,0);
        
        setVisible(false);
    }
}
