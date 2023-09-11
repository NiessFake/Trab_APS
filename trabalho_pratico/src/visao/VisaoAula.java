package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
//import controle.*;
import modelo.*;
import persistencia.*;

public class VisaoAula extends JFrame{ 
    private static VisaoAula uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecario = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_aulas = new JPanel();
    JPanel jpanel_dados = new JPanel();

    /* Botões */
    JButton bt_aulas = new JButton("AULAS");
    JButton bt_mensagens = new JButton("MENSAGENS");
    JButton bt_aluno = new JButton("");
    JButton bt_alterar = new JButton("ALTERAR DADOS");
    JButton bt_excluir = new JButton("EXCLUIR");
    JButton bt_confirmar = new JButton("CONFIRMAR");
    JButton bt_entrar_aula = new JButton("ENTRAR AULA");

    /* Labels */
    JLabel projeto = new JLabel("PROJETO");
    JLabel label_dados = new JLabel("DADOS PESSOAIS");
    JLabel label_alterar = new JLabel("ALTERAR DADOS");
    JLabel label_nome = new JLabel();
    JLabel label_sobrenome = new JLabel("SOBRENOME: ");
    JLabel label_email = new JLabel();
    JLabel label_dataNasc = new JLabel();
    JLabel label_senha = new JLabel("SENHA:");
    JLabel label_cSenha = new JLabel("CONFIRMAR SENHA: ");

    /* TextAreas */
    JTextArea tArea_nome = new JTextArea();
    JTextArea tArea_sobrenome = new JTextArea();
    JTextArea tArea_email = new JTextArea();
    JTextArea tArea_senha = new JTextArea();
    JTextArea tArea_cSenha = new JTextArea();

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,20);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecario = new Color(0,204,155);
    Color cor_textos = new Color(163, 184, 204);

    /* Cria uma instancia única para essa interface (Padrao Singleton) */
    public static VisaoAula getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoAula();
		return uniqueInstance;
	}

    /* Interface do cabecario */
    public void cabecario(){
        /* Botões */
        bt_aulas.setFont(texto_padrao);
        bt_aulas.setBounds(0,0,125,40);
		bt_aulas.setLocation(200, 30);
        bt_aulas.setBackground(Color.white);
		bt_aulas.setForeground(Color.black);

        bt_mensagens.setFont(texto_padrao);
        bt_mensagens.setBounds(0,0,125,40);
		bt_mensagens.setLocation(330, 30);
        bt_mensagens.setBackground(Color.white);
		bt_mensagens.setForeground(Color.black);

        bt_aluno.setFont(texto_padrao);
        bt_aluno.setBounds(0,0,125,40);
		bt_aluno.setLocation(575, 30);
        bt_aluno.setBackground(Color.white);
		bt_aluno.setForeground(Color.black);

        /* Labels */
        projeto.setFont(texto_titulo);
        projeto.setBounds(0,0,150,50);
		projeto.setLocation(20, 30);

        /* Paineis */
        jpanel_cabecario.setLayout(null);
        jpanel_cabecario.setBackground(cor_cabecario);
        jpanel_cabecario.setSize(750, 100);
        jpanel_cabecario.setLocation(0, 0);
        jpanel_cabecario.setVisible(true);
        
        jpanel_fundo.setLayout(null);
        jpanel_fundo.setBackground(cor_fundo);
        jpanel_fundo.setSize(750, 600);
        jpanel_fundo.setLocation(0, 0);
        jpanel_fundo.setVisible(true);
        
        /* Adiciona os elementos no cabecario, em seguida adiciona-o no fundo e adiciona o fundo */
        jpanel_cabecario.add(bt_aulas);
        jpanel_cabecario.add(bt_mensagens);
        jpanel_cabecario.add(bt_aluno);
        jpanel_cabecario.add(projeto);
        
        add(jpanel_cabecario);
        add(jpanel_fundo);
    }

    /* */
}
