package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.*;
import controle.*;

public class VisaoMain extends JFrame {
    /* Cria um atributo do tipo Controle para acessar as funções */
    private ControleUsuario cUsuario; //acho que vai ter q criar um desses para cada

    /* Atributo que vai guardar a única instância da interface */
    private static VisaoMain uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecario = new JPanel();
    JPanel jpanel_fundo = new JPanel();

    /* Botões */
    JButton bt_login = new JButton("Login");
    JButton bt_juntese = new JButton("Junte-se");

    /* Labels */
    JLabel projeto = new JLabel("PROJETO");

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

    /* Interface do cabecario */
    public void cabecario(){
        /* Botões */
        bt_login.setFont(new Font("ARIAL",Font.BOLD,12));
        bt_login.setBounds(0,0,100,40);
		bt_login.setLocation(520, 30);
        bt_login.setBackground(Color.white);
		bt_login.setForeground(Color.black);
        bt_login.addActionListener(this::login);

        bt_juntese.setFont(new Font("ARIAL",Font.BOLD,12));
        bt_juntese.setBounds(0,0,100,40);
		bt_juntese.setLocation(625, 30);
        bt_juntese.setBackground(Color.white);
		bt_juntese.setForeground(Color.black);
        bt_juntese.addActionListener(this::cadastro);

        /* Labels */
        projeto.setFont(new Font("ARIAL",Font.BOLD,30));
        projeto.setBounds(0,0,150,50);
		projeto.setLocation(20, 30);

        /* Paineis */
        jpanel_cabecario.setLayout(null);
        jpanel_cabecario.setBackground(new Color(0,204,155));
        jpanel_cabecario.setVisible(true);
        jpanel_cabecario.setSize(750, 100);
        jpanel_cabecario.setLocation(0, 0);

        jpanel_fundo.setLayout(null);
        jpanel_fundo.setBackground(Color.WHITE);
        jpanel_fundo.setVisible(true);
        jpanel_fundo.setSize(750, 600);
        jpanel_fundo.setLocation(0, 100);

        /* Adiciona os elementos no cabecario */
        jpanel_fundo.add(bt_login);
        jpanel_fundo.add(bt_juntese);
        jpanel_fundo.add(projeto);
        
        jpanel_fundo.add(jpanel_cabecario);

        add(jpanel_fundo);
    }
    
    public void menu(){
        setVisible(true);

        /* Chama a funcao que adiciona o cabecario */
        cabecario();
    }

    /* Faz com que ao apertar o botao seja redirecionado para o login */
    private void login(ActionEvent actionEvent){
        /* REMOVE O LAYOUT ANTERIOR */	
        remove(bt_juntese);
        remove(bt_login);
        jpanel_fundo.remove(jpanel_cabecario);
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
        jpanel_fundo.remove(jpanel_cabecario);
        remove(jpanel_fundo);

        /* Chama a instancia unica do VisaoUsuario e vai até ela na funcao cadastro */
        VisaoUsuario.getInstance().cadastro(cUsuario);

        setVisible(false);
    }
}
