package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controle.*;
import modelo.*;
import persistencia.PersistenciaAula;

public class VisaoAula extends JFrame{

    Usuario usuario = new Usuario();
    Aluno aluno = new Aluno();
    Professor professor = new Professor();
    
    Aula aula = new Aula();
    PersistenciaAula pAula = new PersistenciaAula();

    /* Variaveis auxiliares */
    String[] colunas = {"ID", "Materia", "Professor", "Capacidade", "Duracao","Frequente"};
    int id;


    /* Variavel que guarda a instancia unica */
    private static VisaoAula uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_aulas = new JPanel();
    JPanel jpanel_dados = new JPanel();

    /* Scroll Panels */
    JScrollPane barraRolagem;
    
    /* Botões */
    JButton bt_aulas = new JButton("AULAS");
    JButton bt_mensagens = new JButton("MENSAGENS");
    JButton bt_login = new JButton("LOGIN");
    JButton bt_juntese = new JButton("JUNTE-SE");
    JButton bt_usuario = new JButton("");
    JButton bt_voltar = new JButton("VOLTAR");
    JButton bt_inscrever = new JButton("INSCREVER");

    
    /* Labels */
    JLabel projeto = new JLabel("PROJETO");
    JLabel label_aula = new JLabel("");
    JLabel label_professor = new JLabel("PROFESSOR: ");
    JLabel label_segunda = new JLabel("S");
    JLabel label_terca = new JLabel("T");
    JLabel label_quarta = new JLabel("Q");
    JLabel label_quinta = new JLabel("Q");
    JLabel label_sexta = new JLabel("S");
    JLabel label_sabado = new JLabel("S");
    JLabel label_domingo = new JLabel("D");

    /* TextAreas */
    TextArea tArea_descricao = new TextArea();

    /* Tabelas */
    JTable tabela_aulas;
    JTable tabela_alunos;

    /* Modelo de lista de selecao */
    ListSelectionModel selecao;

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,20);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecalho = new Color(0,204,155);
    Color cor_textos = new Color(163, 184, 204);

    /* Contrução do JFrame que será usado */
    public VisaoAula(){
        setSize(750,600);
        setTitle("TP Analise e Projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static VisaoAula getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoAula();
		return uniqueInstance;
	}
    
    /* Interface do cabecalho */
    public void cabecalho(){
        /* Paineis */
        jpanel_cabecalho.setLayout(null);
        jpanel_cabecalho.setBackground(cor_cabecalho);
        jpanel_cabecalho.setSize(750, 100);
        jpanel_cabecalho.setLocation(0, 0);
        jpanel_cabecalho.setVisible(true);
        
        jpanel_fundo.setLayout(null);
        jpanel_fundo.setBackground(cor_fundo);
        jpanel_fundo.setSize(750, 600);
        jpanel_fundo.setLocation(0, 0);
        jpanel_fundo.setVisible(true);

        /* Botões */
        bt_aulas.setFont(texto_padrao);
        bt_aulas.setBounds(200,30,125,40);
        bt_aulas.setBackground(Color.white);
		bt_aulas.setForeground(Color.black);

        bt_mensagens.setFont(texto_padrao);
        bt_mensagens.setBounds(330,30,125,40);
        bt_mensagens.setBackground(Color.white);
		bt_mensagens.setForeground(Color.black);

        if(id == 0){
            bt_login.setFont(texto_padrao);
            bt_login.setBounds(520, 30,100,40);
            bt_login.setBackground(Color.white);
            bt_login.setForeground(Color.black);

            bt_juntese.setFont(texto_padrao);
            bt_juntese.setBounds(625, 30,100,40);
            bt_juntese.setBackground(Color.white);
            bt_juntese.setForeground(Color.black);

            jpanel_cabecalho.add(bt_login);
            jpanel_cabecalho.add(bt_juntese);
        }
        else{
            bt_usuario.setFont(texto_padrao);
            bt_usuario.setBounds(575,30,125,40);
            bt_usuario.setBackground(Color.white);
            bt_usuario.setForeground(Color.black);

            jpanel_cabecalho.add(bt_usuario);
        }

        /* Labels */
        projeto.setFont(texto_titulo);
        projeto.setBounds(20,30,150,50);
        
        /* Adiciona os elementos no cabecalho, em seguida adiciona-o no fundo e adiciona o fundo */
        jpanel_cabecalho.add(bt_aulas);
        jpanel_cabecalho.add(bt_mensagens);
        jpanel_cabecalho.add(projeto);
        
        add(jpanel_cabecalho);
        add(jpanel_fundo);
    }

    public void menuAulas(Entidade entidade, int tipo){
        setVisible(true);

        switch (tipo) {
            case 1:                
                id = (((Aluno)entidade).getId());
                break;
            
            case 2:                
                id = (((Professor)entidade).getId());
                break;
            
            default:
                id = (((Usuario)entidade).getId());
                break;
        }

        cabecalho();

        /* Chama a funcao que devolve um objeto contendo os dados do json */
        Object[][] objeto_tabela = pAula.textoTabelas();

        
        /* Cria uma tabela de selecao unica e nao editavel */
        tabela_aulas = new JTable(objeto_tabela, colunas){   
            public boolean isCellEditable(int row, int column) {                
                return false;               
            }
        };
        tabela_aulas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        selecao = tabela_aulas.getSelectionModel();
        selecao.addListSelectionListener(new ListSelectionListener() {
            @Override
                public void valueChanged(ListSelectionEvent e){
                    /* Confere se um item foi selecionado, em caso positivo, pega seu index */
                    if(!selecao.isSelectionEmpty()){
                        int selecionado = selecao.getMinSelectionIndex();

                        String aux = tabela_aulas.getValueAt(selecionado, 0).toString();

                        aula.setId(Integer.parseInt(aux));

                        /* Apaga tabela antiga */
                        jpanel_fundo.remove(barraRolagem);

                        setVisible(false);
                        paginaIndividual();
                }
            }
        });

        //Component celula = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        /* Cria um novo JScrollPane e coloca a tabela nele */
        barraRolagem = new JScrollPane(tabela_aulas);

        barraRolagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        barraRolagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        barraRolagem.setBounds(100,150, 550,350);
        barraRolagem.getVerticalScrollBar().setValue(0);
        barraRolagem.setVisible(true);

        /* Adiciona o ScrollPane no painel */
        jpanel_fundo.add(barraRolagem);
        
    }

    
    

    public void paginaIndividual(){
        setVisible(true);

        aula = pAula.buscaID(aula.getId());

        /* Botoes */
        bt_voltar.setFont(texto_padrao);
        bt_voltar.setBounds(450,400,100,40);
        bt_voltar.setBackground(Color.white);
		bt_voltar.setForeground(Color.black);

        bt_inscrever.setFont(texto_padrao);
        bt_inscrever.setBounds(400,300,125,40);
        bt_inscrever.setBackground(Color.white);
		bt_inscrever.setForeground(Color.black);

        /* JLabels */
        label_aula.setText(aula.getMateria());
        label_aula.setFont(texto_sub_titulo);
        label_aula.setBounds(150, 35,125,40);

        label_segunda.setFont(texto_sub_titulo);
        label_segunda.setBounds(25, 75,125,40);

        label_terca.setFont(texto_sub_titulo);
        label_terca.setBounds(25, 75,125,40);

        label_quarta.setFont(texto_sub_titulo);
        label_quarta.setBounds(25, 75,125,40);

        label_quinta.setFont(texto_sub_titulo);
        label_quinta.setBounds(25, 75,125,40);

        label_sexta.setFont(texto_sub_titulo);
        label_sexta.setBounds(25, 75,125,40);

        label_sabado.setFont(texto_sub_titulo);
        label_sabado.setBounds(25, 75,125,40);

        label_domingo.setFont(texto_sub_titulo);
        label_domingo.setBounds(25, 75,125,40);

        label_professor.setText(aula.getMateria());
        label_professor.setFont(texto_sub_titulo);
        label_professor.setBounds(25, 25,125,40);


    JLabel label_segunda = new JLabel("S");
    JLabel label_terca = new JLabel("T");
    JLabel label_quarta = new JLabel("Q");
    JLabel label_quinta = new JLabel("Q");
    JLabel label_sexta = new JLabel("S");
    JLabel label_sabado = new JLabel("S");
    JLabel label_domingo = new JLabel("D");

        /* Caixas de texto */
        tArea_descricao.setFont(texto_sub_titulo);
        tArea_descricao.setBounds(35, 300,265,150);
        tArea_descricao.setBackground(Color.BLACK);


        /* Paineis */
        jpanel_dados.setLayout(null);
        jpanel_dados.setBackground(Color.WHITE);
        jpanel_dados.setVisible(true);
        jpanel_dados.setSize(600, 500);
        jpanel_dados.setLocation(75, 100);

        jpanel_dados.add(label_aula);
        jpanel_dados.add(label_professor);
        jpanel_dados.add(tArea_descricao);
        jpanel_dados.add(bt_voltar);
        jpanel_dados.add(bt_inscrever);

        jpanel_fundo.add(jpanel_dados);
        
    }


}
