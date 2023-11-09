package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controle.*;
import modelo.*;


public class VisaoMensagem extends JFrame{
    private Aluno aluno;
    private Professor professor;
    private Noticias noticias;
    private Mensagem mensagem;
    private ControleAula cAula;
    private ControleNoticias cNoticias;
    private ControleMensagem cMensagem;
    private VisaoMain vMain;

    protected int funcao, tamanho_titulo;
    protected String data, dia, mes, ano, titulo, descricao;
    protected String[] colunas_mensagem = {"Id Remet", "Id Dest"};

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_noticias = new JPanel();
    JPanel jpanel_titulo = new JPanel();

    /* Scroll Panels */
    JScrollPane jScroll_mensagem;
    JScrollPane jScroll_alunos;
    
    /* Botões */
    JButton bt_projeto = new JButton("PROJETO");
    JButton bt_usuario = new JButton("");
    
    /* Labels */

    /* TextAreas */

    /* Tabelas */
    JTable tabela_mensagem;

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
    public VisaoMensagem(){
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
        bt_usuario.setFont(texto_padrao);
        bt_usuario.setBounds(575,30,125,40);
        bt_usuario.setBackground(Color.white);
        bt_usuario.setForeground(Color.black);
        bt_usuario.addActionListener(this::usuario);

        bt_projeto.setFont(texto_titulo);
        bt_projeto.setBounds(20, 30,200,50);
        bt_projeto.setBackground(cor_cabecalho);
		bt_projeto.setForeground(Color.black);
        bt_projeto.setBorderPainted(false);
        bt_projeto.addActionListener(this::projeto);
        
        /* Adiciona os elementos no cabecalho, em seguida adiciona-o no fundo e adiciona o fundo */
        jpanel_cabecalho.add(bt_projeto);
        jpanel_cabecalho.add(bt_usuario);            
        
        add(jpanel_cabecalho);
        add(jpanel_fundo);
    }

    public void menuMensagens(Controle controle, Entidade entidade, int tipo){
        setVisible(true);

        funcao = tipo;
        mensagem = new Mensagem();
        cMensagem = (ControleMensagem)controle;

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
        Object[][] objeto_tabela_mensagem = cMensagem.textoMensagem();

        
        /* Cria uma tabela de selecao unica e nao editavel */
        tabela_mensagem = new JTable(objeto_tabela_mensagem, colunas_mensagem){   
            public boolean isCellEditable(int row, int column) {                
                return false;               
            }
        };
        tabela_mensagem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        selecao = tabela_mensagem.getSelectionModel();
        selecao.addListSelectionListener(new ListSelectionListener() {
            @Override
                public void valueChanged(ListSelectionEvent e){
                    /* Confere se um item foi selecionado, em caso positivo, pega seu index */
                    if(!selecao.isSelectionEmpty()){
                        int selecionado = selecao.getMinSelectionIndex();

                        String aux = tabela_mensagem.getValueAt(selecionado, 0).toString();

                        mensagem.setId(Integer.parseInt(aux));

                        /* Apaga tabela antiga */
                        jpanel_fundo.remove(jScroll_mensagem);

                        mensagem = cMensagem.buscaID(mensagem.getId());

                        setVisible(false);
                        vMain = new VisaoMain();
                        switch (funcao) {
                            case 1:
                                vMain.mensagemPI(mensagem, aluno,funcao);
                                break;

                            case 2:
                                vMain.mensagemPI(mensagem, professor,funcao);
                                break;
                        
                            default:
                                vMain.mensagemPI(mensagem, null,funcao);
                                break;
                        }
                        dispose();
                }
            }
        });

        
        //Component celula = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        /* Cria um novo JScrollPane e coloca a tabela nele */
        jScroll_mensagem = new JScrollPane(tabela_mensagem);

        jScroll_mensagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScroll_mensagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScroll_mensagem.setBounds(100,150, 550,350);
        jScroll_mensagem.getVerticalScrollBar().setValue(0);
        jScroll_mensagem.setVisible(true);

        /* Adiciona o ScrollPane no painel */
        jpanel_fundo.add(jScroll_mensagem);
        
    }

    public void paginaIndividual(Controle controle, Entidade entidade, Entidade entidade2, int tipo){

    }





    /* Funcao que volta pro menu da Visao main */
    private void projeto(ActionEvent actionEvent){
        vMain = new VisaoMain();
        vMain.menu();
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
