package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controle.*;
import modelo.*;

public class VisaoNoticias extends JFrame{
    private Aluno aluno;
    private Professor professor;
    private ControleAula cAula;
    private Noticias noticias;
    private ControleNoticias cNoticias;
    private VisaoMain vMain;

    protected int funcao, tamanho_titulo;
    protected String data;
    protected String[] colunas_noticias = {"Id", "Titulo", "Descricao", "Data"};

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_noticias = new JPanel();
    JPanel jpanel_titulo = new JPanel();

    /* Scroll Panels */
    JScrollPane jScroll_noticias;
    JScrollPane jScroll_alunos;
    
    /* Botões */
    JButton bt_projeto = new JButton("PROJETO");
    JButton bt_aulas = new JButton("AULAS");
    JButton bt_noticias = new JButton("NOTICIAS");
    JButton bt_login = new JButton("LOGIN");
    JButton bt_juntese = new JButton("JUNTE-SE");
    JButton bt_usuario = new JButton("");
    JButton bt_alterar = new JButton("ALTERAR");
    
    /* Labels */
    JLabel label_titulo = new JLabel();
    JLabel label_descricao = new JLabel("Descricao");
    JLabel label_professor = new JLabel();
    JLabel label_data = new JLabel();

    /* TextAreas */
    JTextArea tArea_descricao = new JTextArea();

    /* Tabelas */
    JTable tabela_noticias;

    /* Modelo de lista de selecao */
    ListSelectionModel selecao;

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_pequeno = new Font("ARIAL",Font.BOLD,10);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,20);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecalho = new Color(0,204,155);
    Color cor_textos = new Color(163, 184, 204);


    /* Contrução do JFrame que será usado */
    public VisaoNoticias(){
        setSize(750,600);
        setTitle("TP Analise e Projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
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
        bt_aulas.setBounds(240,30,125,40);
        bt_aulas.setBackground(Color.white);
		bt_aulas.setForeground(Color.black);
        bt_aulas.addActionListener(this::aula);

        bt_noticias.setFont(texto_padrao);
        bt_noticias.setBounds(370,30,125,40);
        bt_noticias.setBackground(Color.white);
		bt_noticias.setForeground(Color.black);
        bt_noticias.addActionListener(this::noticia);

        if(funcao == 1 || funcao == 2){
            bt_usuario.setFont(texto_padrao);
            bt_usuario.setBounds(575,30,125,40);
            bt_usuario.setBackground(Color.white);
            bt_usuario.setForeground(Color.black);
            bt_usuario.addActionListener(this::usuario);

            jpanel_cabecalho.add(bt_usuario);            
        }
        else{
            bt_login.setFont(texto_padrao);
            bt_login.setBounds(520, 30,100,40);
            bt_login.setBackground(Color.white);
            bt_login.setForeground(Color.black);
            bt_login.addActionListener(this::login);

            bt_juntese.setFont(texto_padrao);
            bt_juntese.setBounds(625, 30,100,40);
            bt_juntese.setBackground(Color.white);
            bt_juntese.setForeground(Color.black);
            bt_juntese.addActionListener(this::cadastro);

            jpanel_cabecalho.add(bt_login);
            jpanel_cabecalho.add(bt_juntese);
        }

        bt_projeto.setFont(texto_titulo);
        bt_projeto.setBounds(20, 30,200,50);
        bt_projeto.setBackground(cor_cabecalho);
		bt_projeto.setForeground(Color.black);
        bt_projeto.setBorderPainted(false);
        bt_projeto.addActionListener(this::projeto);
        
        /* Adiciona os elementos no cabecalho, em seguida adiciona-o no fundo e adiciona o fundo */
        jpanel_cabecalho.add(bt_aulas);
        jpanel_cabecalho.add(bt_noticias);
        jpanel_cabecalho.add(bt_projeto);
        
        add(jpanel_cabecalho);
        add(jpanel_fundo);
    }

    public void menuNoticias(Controle controle, Entidade entidade, int tipo){
        setVisible(true);

        funcao = tipo;
        noticias = new Noticias();
        cNoticias = (ControleNoticias)controle;

        switch (tipo) {
            case 1:                
                aluno = (Aluno)entidade;
                bt_usuario.setText(aluno.getNome());
                break;
            
            case 2:                
                professor = (Professor)entidade;
                bt_usuario.setText(professor.getNome());
                break;
            
            default:
                break;
        }

        cabecalho();

        /* Chama a funcao que devolve um objeto contendo os dados do json */
        Object[][] objeto_tabela_noticias = cNoticias.textoNoticias();

        
        /* Cria uma tabela de selecao unica e nao editavel */
        tabela_noticias = new JTable(objeto_tabela_noticias, colunas_noticias){   
            public boolean isCellEditable(int row, int column) {                
                return false;               
            }
        };
        tabela_noticias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        selecao = tabela_noticias.getSelectionModel();
        selecao.addListSelectionListener(new ListSelectionListener() {
            @Override
                public void valueChanged(ListSelectionEvent e){
                    /* Confere se um item foi selecionado, em caso positivo, pega seu index */
                    if(!selecao.isSelectionEmpty()){
                        int selecionado = selecao.getMinSelectionIndex();

                        String aux = tabela_noticias.getValueAt(selecionado, 0).toString();

                        noticias.setId(Integer.parseInt(aux));

                        /* Apaga tabela antiga */
                        jpanel_fundo.remove(jScroll_noticias);

                        noticias = cNoticias.buscaID(noticias.getId());

                        setVisible(false);
                        vMain = new VisaoMain();
                        switch (funcao) {
                            case 1:
                                vMain.noticiasPI(noticias, aluno,funcao);
                                break;

                            case 2:
                                vMain.noticiasPI(noticias, professor,funcao);
                                break;
                        
                            default:
                                vMain.noticiasPI(noticias, null,funcao);
                                break;
                        }
                        dispose();
                }
            }
        });

        
        //Component celula = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        /* Cria um novo JScrollPane e coloca a tabela nele */
        jScroll_noticias = new JScrollPane(tabela_noticias);

        jScroll_noticias.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScroll_noticias.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScroll_noticias.setBounds(100,150, 550,350);
        jScroll_noticias.getVerticalScrollBar().setValue(0);
        jScroll_noticias.setVisible(true);

        /* Adiciona o ScrollPane no painel */
        jpanel_fundo.add(jScroll_noticias);
        
    }

    public void paginaIndividual(Controle controle, Entidade entidade, Entidade entidade2, int tipo){
        setVisible(true);

        cNoticias = (ControleNoticias)controle;
        this.noticias = (Noticias)entidade;

        tamanho_titulo = noticias.getTitulo().length()*15;

        funcao = tipo;
        cabecalho();

        switch (tipo) {
            case 1:        
                aluno = (Aluno)entidade2;        
                bt_usuario.setText(aluno.getNome());
                break;
            
            case 2: 
                professor = (Professor)entidade2;               
                bt_usuario.setText(professor.getNome());
                break;
            
            default:
                break;
        }

        /* Botoes */
        if(funcao == 2){
            if(professor.getId() == (noticias.getAutor()).getId()){
                bt_alterar.setFont(texto_pequeno);
                bt_alterar.setBounds(225,400,100,40);
                bt_alterar.setBackground(Color.white);
                bt_alterar.setForeground(Color.black);
                bt_alterar.setBorderPainted(false);
                bt_alterar.addActionListener(this::alterar);
                jpanel_noticias.add(bt_alterar);
            }
        }

        /* JLabels */
        label_titulo.setText(noticias.getTitulo());
        label_titulo.setFont(texto_sub_titulo);
        label_titulo.setBounds(550-tamanho_titulo, 50,tamanho_titulo,40);
		label_titulo.setForeground(Color.white);

        professor = noticias.getAutor();
        label_professor.setText("Autor: " + professor.getNome() + " " + professor.getSobrenome());
        label_professor.setFont(texto_padrao);
        label_professor.setBounds(50, 110,225,40);

        if(noticias.getDia() < 10)
			data = "0";
		data = data + noticias.getDia() + "/";

		if(noticias.getMes() < 10)
			data = data + "0";
		data = data + noticias.getMes() + "/" + noticias.getAno();

        label_data.setText("Data de publicacao: "+ data);
        label_data.setFont(texto_padrao);
        label_data.setBounds(275, 110,225,40);

        label_descricao.setFont(texto_padrao);
        label_descricao.setBounds(50, 150,125,40);

        /* TextAreas */
        tArea_descricao.setFont(texto_padrao);
        tArea_descricao.setBounds(50,185,450,200);
        tArea_descricao.setBackground(Color.white);
        tArea_descricao.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));
        tArea_descricao.setLineWrap(true);
        tArea_descricao.setWrapStyleWord(true);
        tArea_descricao.setEditable(false);


        /* Paineis */
        jpanel_noticias.setLayout(null);
        jpanel_noticias.setBackground(Color.WHITE);
        jpanel_noticias.setVisible(true);
        jpanel_noticias.setSize(550, 500);
        jpanel_noticias.setLocation(100, 100);

        jpanel_titulo.setLayout(null);
        jpanel_titulo.setBackground(cor_cabecalho);
        jpanel_titulo.setVisible(true);
        jpanel_titulo.setSize(550, 60);
        jpanel_titulo.setLocation(0, 30);

        jpanel_noticias.add(label_titulo);
        jpanel_noticias.add(label_descricao);
        jpanel_noticias.add(label_professor);
        jpanel_noticias.add(label_data);
        jpanel_noticias.add(tArea_descricao);
        jpanel_noticias.add(jpanel_titulo);
        jpanel_noticias.add(bt_alterar);

        jpanel_fundo.add(jpanel_noticias);
    }

    public void alterar(Controle controle, Entidade entidade, Entidade entidade2){
        professor = (Professor)entidade2;               
        bt_usuario.setText(professor.getNome());
        cabecalho();

        setVisible(false);
        /* Exclui os elementos antigos */
        jpanel_dados.remove(label_dados);
        jpanel_dados.remove(label_imagem_login);
        jpanel_dados.remove(label_nome);
        jpanel_dados.remove(label_email);
        jpanel_dados.remove(label_dataNasc);
        jpanel_dados.remove(bt_alterar);

        jpanel_fundo.remove(jpanel_dados);
        jpanel_fundo.remove(jScroll_aulas);
        jpanel_fundo.remove(bt_criar_aula);
        jpanel_fundo.remove(bt_criar_noticias);

        /* Botões */
        bt_continuar_noticias.setFont(texto_padrao);
        bt_continuar_noticias.setBounds(180, 385, 150,40);
        bt_continuar_noticias.setBackground(Color.white);
		bt_continuar_noticias.setForeground(Color.black);
        bt_continuar_noticias.addActionListener(this::continuarNoticias);

        /* Labels */
        label_criar_noticias.setFont(texto_sub_titulo);
        label_criar_noticias.setBounds(125, 25, 250,50);

        label_titulo.setFont(texto_padrao);
        label_titulo.setBounds(60, 100, 125,50);

        label_dias.setFont(texto_padrao);
        label_dias.setBounds(60, 135,125,50);

        label_descricao.setFont(texto_padrao);
        label_descricao.setBounds(60, 170, 125,50);


        /* Caixas de texto */
        tArea_titulo.setFont(texto_padrao);
        tArea_titulo.setBounds(190, 110,250,25);
        tArea_titulo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        tArea_descricao.setFont(texto_padrao);
        tArea_descricao.setBounds(190, 180,250,125);
        tArea_descricao.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        /* CheckBOXes */
        cbox_dia.setBounds(190, 145, 80, 25);

        cbox_mes.setBounds(275, 145, 80, 25);

        cbox_ano.setBounds(360, 145, 80, 25);

        /* Painel */   
        jpanel_noticias.setLayout(null);
        jpanel_noticias.setBackground(Color.WHITE);
        jpanel_noticias.setSize(500, 500);
        jpanel_noticias.setLocation(125, 100);
        jpanel_noticias.setVisible(true);

        /* Adiciona elementos no painel */
        jpanel_noticias.add(label_criar_aula);
        jpanel_noticias.add(label_criar_noticias);
        jpanel_noticias.add(label_titulo);
        jpanel_noticias.add(label_descricao);
        jpanel_noticias.add(label_dias);
        jpanel_noticias.add(tArea_titulo);
        jpanel_noticias.add(tArea_descricao);
        jpanel_noticias.add(cbox_dia);
        jpanel_noticias.add(cbox_mes);
        jpanel_noticias.add(cbox_ano);
        jpanel_noticias.add(bt_continuar_noticias);

        jpanel_fundo.add(jpanel_noticias);

        setVisible(true);
    }

    private void alterar(ActionEvent actionEvent){
        vMain = new VisaoMain();              
        vMain.noticiasMenu(cNoticias, professor, 2);
        dispose();
    }

    /* Funcao que volta pro menu da Visao main */
    private void projeto(ActionEvent actionEvent){
        vMain = new VisaoMain();
        vMain.menu();
        dispose();
    }

    private void aula(ActionEvent actionEvent){
        this.cAula = new ControleAula();
        vMain = new VisaoMain();
        
        switch (funcao) {
            case 1:                               
                vMain.aulaMenu(cAula, aluno, 1);
                dispose();
                break;
            
            case 2:                       
                vMain.aulaMenu(cAula, professor, 2);
                dispose();
                break;
            
            default:       
                vMain.aulaMenu(cAula, null, 0);
                dispose();
                break;
        }
    }

    private void noticia(ActionEvent actionEvent){
        vMain = new VisaoMain();
        
        switch (funcao) {
            case 1:                               
                vMain.noticiasMenu(cNoticias, aluno, 1);
                dispose();
                break;
            
            case 2:                       
                vMain.noticiasMenu(cNoticias, professor, 2);
                dispose();
                break;
            
            default:       
                vMain.noticiasMenu(cNoticias, null, 0);
                dispose();
                break;
        }
    }

    private void login(ActionEvent actionEvent){
        this.vMain = new VisaoMain();
        vMain.usuarioLogin();
        dispose();
    } 

    private void cadastro(ActionEvent actionEvent){
        this.vMain = new VisaoMain();
        vMain.usuarioLogin();
        dispose();
    }

    private void usuario(ActionEvent actionEvent){
        vMain = new VisaoMain();
        /* Chama a instancia unica do VisaoAluno ou do Visao Professor, redirecionando para suas
         * respectivas paginas de acordo com sua funcao*/
        if(funcao == 1)
            vMain.aulaAP(aluno,1);
        else
            vMain.aulaAP(professor,2);
        dispose();
    }

}
