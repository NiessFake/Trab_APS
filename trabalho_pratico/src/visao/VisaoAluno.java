package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import controle.Controle;
import controle.ControleAluno;
import controle.ControleAula;
import modelo.Entidade;
import modelo.Aluno;

public class VisaoAluno extends JFrame{
    /* Atributo que vai guardar a única instância da interface */
    //private static VisaoAluno uniqueInstance;

    /* Classes usadas */
    private Aluno aluno;
    private ControleAluno cAluno;
    private ControleAula cAula;
    private VisaoMain vMain;

    protected String nome, sobrenome, email, dia, mes, ano , senha, cSenha;
    protected boolean condicao;
    protected String[] coluna_aula = {"ID", "Materia", "Capacidade"};
    protected String[] botoes = { "Sim", "Nao" };

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_aulas = new JPanel();
    JPanel jpanel_dados = new JPanel();

    /* Scroll Panels */
    JScrollPane jScroll_aulas;

    /* Tabelas */
    JTable tabela_aulas;

    /* Botões */
    JButton bt_aulas = new JButton("AULAS");
    JButton bt_mensagens = new JButton("MENSAGENS");
    JButton bt_sair = new JButton("SAIR");
    JButton bt_aluno = new JButton("");
    JButton bt_alterar = new JButton("ALTERAR DADOS");
    JButton bt_excluir = new JButton("EXCLUIR");
    JButton bt_confirmar = new JButton("CONFIRMAR");
    JButton bt_entrar_aula = new JButton("ENTRAR AULA");
    JButton bt_projeto = new JButton("PROJETO");
    
    /* Labels */
    JLabel label_imagem_login = new JLabel("");
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

    /*ComboBox */
    JComboBox <String> cbox_dia = new JComboBox<>(preencheVetor(32, 1, true));
    JComboBox <String> cbox_mes = new JComboBox<>(preencheVetor(13, 1, true));
    JComboBox <String> cbox_ano = new JComboBox<>(preencheVetor(151, 1874, false));

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,20);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecalho = new Color(0,204,155);
    
    /* Imagens */
    ImageIcon imagem_login = new ImageIcon("src/imagens/login.png");
    
    /* Contrução do JFrame que será usado */
    public VisaoAluno(){
        setSize(750,600);
        setTitle("TP Analise e bt_projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /* Cria uma instancia única para essa interface (Padrao Singleton) */
    /* public static VisaoAluno getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoAluno();
		return uniqueInstance;
	} */

    /* Interface do cabecalho */
    public void cabecalho(){
        /* Botões */
        bt_aulas.setFont(texto_padrao);
        bt_aulas.setBounds(240,30,125,40);
        bt_aulas.setBackground(Color.white);
		bt_aulas.setForeground(Color.black);
        bt_aulas.addActionListener(this::irPAula);

        bt_mensagens.setFont(texto_padrao);
        bt_mensagens.setBounds(370,30,125,40);
        bt_mensagens.setBackground(Color.white);
		bt_mensagens.setForeground(Color.black);

        bt_aluno.setFont(texto_padrao);
        bt_aluno.setBounds(575,30,125,40);
        bt_aluno.setBackground(Color.white);
		bt_aluno.setForeground(Color.black);

        bt_projeto.setFont(texto_titulo);
        bt_projeto.setBounds(20, 30,200,50);
        bt_projeto.setBackground(cor_cabecalho);
		bt_projeto.setForeground(Color.black);
        bt_projeto.setBorderPainted(false);
        bt_projeto.addActionListener(this::projeto);

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
        
        /* Adiciona os elementos no cabecalho, em seguida adiciona-o no fundo e adiciona o fundo */
        jpanel_cabecalho.add(bt_aulas);
        jpanel_cabecalho.add(bt_mensagens);
        jpanel_cabecalho.add(bt_aluno);
        jpanel_cabecalho.add(bt_projeto);
        
        add(jpanel_cabecalho);
        add(jpanel_fundo);
    }

    /* Funcao que cria uma tabela de aulas */
    public void tabelaAulas(){

        /* Chama a funcao que devolve um objeto contendo os dados do json */
        Object[][] objeto_tabela = cAluno.textoAlunos(aluno);

        if(objeto_tabela != null){
            /* Cria uma tabela de selecao unica e nao editavel */
            tabela_aulas = new JTable(objeto_tabela, coluna_aula){   
                public boolean isCellEditable(int row, int column) {                
                    return false;               
                }
            };
            tabela_aulas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        else{
            tabela_aulas = new JTable();
        }

        /* Cria um novo JScrollPane e coloca a tabela nele */
        jScroll_aulas = new JScrollPane(tabela_aulas);

        jScroll_aulas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScroll_aulas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScroll_aulas.setBounds(385,125, 315,200);
        jScroll_aulas.getVerticalScrollBar().setValue(0);
        jScroll_aulas.setVisible(true);

        /* Adiciona o ScrollPane no painel */
        jpanel_fundo.add(jScroll_aulas);
    }

    /* Pagina do aluno que leva para seus dados */
    public void paginaAluno(Controle controle, Entidade entidade){
        setVisible(true);
        
        aluno = (Aluno)entidade;
        cAluno = (ControleAluno)controle;

        this.cAula = new ControleAula();

        cabecalho();
        tabelaAulas();

        /* Nomeia o botao como o nome do aluno cadastrado */
        bt_aluno.setText(aluno.getNome());

        /* Botões */
        bt_alterar.setFont(new Font("ARIAL",Font.BOLD,11));
        bt_alterar.setBounds(50,335,175,40);
        bt_alterar.setBackground(Color.white);
		bt_alterar.setForeground(Color.black);
        bt_alterar.addActionListener(this::alterar);

        /* Labels */
        label_dados.setFont(texto_padrao);
        label_dados.setBounds(15, 160,300,40);

        label_nome.setText("NOME: " + aluno.getNome() + " " +aluno.getSobrenome());
        label_nome.setFont(texto_padrao);
        label_nome.setBounds(25, 200,300,40);

        label_email.setText("EMAIL: " + aluno.getEmail());
        label_email.setFont(texto_padrao);
        label_email.setBounds(25, 235,300,40);

        label_dataNasc.setText("DATA DE NASCIMENTO: " + aluno.getDiaNasc() + "/" + aluno.getMesNasc() + "/" + aluno.getAnoNasc());
        label_dataNasc.setFont(texto_padrao);
        label_dataNasc.setBounds(25, 270,420,40);

        label_imagem_login.setBounds(100,50,85,85);
        imagem_login.setImage(imagem_login.getImage().getScaledInstance(label_imagem_login.getWidth(),label_imagem_login.getHeight(),1));
        label_imagem_login.setIcon(imagem_login);

        /* Painel */
        jpanel_dados.setLayout(null);
        jpanel_dados.setBackground(Color.WHITE);
        jpanel_dados.setVisible(true);
        jpanel_dados.setSize(285, 400);
        jpanel_dados.setLocation(50, 125);

        /* Adiciona elementos no painel */
        jpanel_dados.add(label_dados);
        jpanel_dados.add(label_nome);
        jpanel_dados.add(label_email);
        jpanel_dados.add(label_dataNasc);
        jpanel_dados.add(label_imagem_login);
        jpanel_dados.add(bt_alterar);

        jpanel_fundo.add(jpanel_dados);
    }


    /*Acoes dos botoes */
    /* Acao do botao alterar */
    private void alterar(ActionEvent actionEvent){
        setVisible(false);

        /* Botoes */
        bt_excluir.setFont(new Font("ARIAL",Font.BOLD,11));
        bt_excluir.setBounds(122, 375,125,40);
        bt_excluir.setBackground(Color.white);
		bt_excluir.setForeground(Color.black);
        bt_excluir.addActionListener(this::excluir);

        bt_confirmar.setBounds(253, 375,125,40);
        bt_confirmar.setBackground(Color.white);
		bt_confirmar.setForeground(Color.black);
        bt_confirmar.addActionListener(this::confirmar);

        /* Labels */        
        label_alterar.setFont(texto_sub_titulo);
        label_alterar.setBounds(150, 25,200,50);

        label_nome.setText("NOME: ");
        label_nome.setFont(texto_padrao);
        label_nome.setBounds(37, 110,175,50);

        label_sobrenome.setFont(texto_padrao);
        label_sobrenome.setBounds(37, 145,175,50);

        label_email.setText("EMAIL: ");
        label_email.setFont(texto_padrao);
        label_email.setBounds(37, 180,175,50);

        label_dataNasc.setText("DATA DE NASCIMENTO: ");
        label_dataNasc.setFont(texto_padrao);
        label_dataNasc.setBounds(37, 215,175,50);

        label_senha.setFont(texto_padrao);
        label_senha.setBounds(37, 250,175,50);

        label_cSenha.setFont(texto_padrao);
        label_cSenha.setBounds(37, 285,175,50);

        /* Caixas de texto */
        tArea_nome.setFont(texto_padrao);
        tArea_nome.setBounds(218, 125,250,25);
        tArea_nome.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        tArea_sobrenome.setFont(texto_padrao);
        tArea_sobrenome.setBounds(218, 160,250,25);
        tArea_sobrenome.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        tArea_email.setFont(texto_padrao);
        tArea_email.setBounds(218, 195,250,25);
        tArea_email.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        tArea_senha.setFont(texto_padrao);
        tArea_senha.setBounds(218, 265,250,25);
        tArea_senha.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        tArea_cSenha.setFont(texto_padrao);
        tArea_cSenha.setBounds(218, 300,250,25);
        tArea_cSenha.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        /* ComboBOXes */
        cbox_dia.setBounds(218, 230,80,25);

        cbox_mes.setBounds(303, 230,80,25);

        cbox_ano.setBounds(388, 230,80,25);

        /* Paineis */
        jpanel_dados.setSize(500, 500);
        jpanel_dados.setLocation(125, 100);

        /* Remove e adiciona elementos no painel */
        jpanel_dados.remove(bt_alterar);
        jpanel_dados.remove(label_dados);
        jpanel_dados.remove(label_imagem_login);
        jpanel_dados.add(label_alterar);
        jpanel_dados.add(label_sobrenome);
        jpanel_dados.add(label_senha);
        jpanel_dados.add(label_cSenha);
        jpanel_dados.add(tArea_nome);
        jpanel_dados.add(tArea_sobrenome);
        jpanel_dados.add(tArea_email);
        jpanel_dados.add(tArea_senha);
        jpanel_dados.add(tArea_cSenha);
        jpanel_dados.add(cbox_dia);
        jpanel_dados.add(cbox_mes);
        jpanel_dados.add(cbox_ano);
        jpanel_dados.add(bt_excluir);
        jpanel_dados.add(bt_confirmar);

        setVisible(true);
    }

    /* Acao do botao excluir */
    private void excluir(ActionEvent actionEvent){
        /* Recebe as senhas */
        senha = tArea_senha.getText();
        cSenha = tArea_cSenha.getText();

        /* Confere se a senha foi digitada, se sao iguais e se corresponde a do aluno. Se nao for exibe mensagem de erro*/
        if((senha.equals("") || cSenha.equals("")) || (!senha.equals(cSenha)) || (!senha.equals(aluno.getSenha())))
            JOptionPane.showMessageDialog(null,"As senha nao digitadas ou divergem", "ERRO",JOptionPane.ERROR_MESSAGE);
        else{
            /* Se o vetor de aulas estiver vazio exclui, senao avisa que tem dependencia */
            if(aluno.getAulaInscritas() != null){
                int resposta = JOptionPane.showOptionDialog(null,"Ha aulas que voce ainda esta inscrito, apagar esse cadastro significa apagar seu registro nelas. Deseja continuar?", "ULTIMA CHANCE",JOptionPane.WARNING_MESSAGE, 0, null,botoes,botoes[0]);
                
                /* Deixa as caixas de texto em branco */
                tArea_nome.setText("");
                tArea_nome.requestFocus();
                tArea_sobrenome.setText("");
                tArea_sobrenome.requestFocus();
                tArea_email.setText("");
                tArea_email.requestFocus();
                cbox_dia.setSelectedItem("");
                cbox_mes.setSelectedItem("");
                cbox_ano.setSelectedItem("");
                tArea_senha.setText("");
                tArea_senha.requestFocus();
                tArea_cSenha.setText("");
                tArea_cSenha.requestFocus();

                /* Remove tudo do cabecalho */
                jpanel_dados.remove(label_alterar);
                jpanel_dados.remove(label_sobrenome);
                jpanel_dados.remove(label_senha);
                jpanel_dados.remove(label_cSenha);
                jpanel_dados.remove(tArea_nome);
                jpanel_dados.remove(tArea_sobrenome);
                jpanel_dados.remove(tArea_email);
                jpanel_dados.remove(tArea_senha);
                jpanel_dados.remove(tArea_cSenha);
                jpanel_dados.remove(cbox_dia);
                jpanel_dados.remove(cbox_mes);
                jpanel_dados.remove(cbox_ano);
                jpanel_dados.remove(bt_excluir);
                jpanel_dados.remove(bt_confirmar);

                //jpanel_fundo.remove(jScroll_aulas);
                jpanel_fundo.remove(jpanel_dados);

                jpanel_cabecalho.remove(bt_aluno);

                remove(jpanel_fundo);
                remove(jpanel_cabecalho);

                /* Deixa essa tela invisivel para o usuario */
                setVisible(false);

                if(resposta == 0){
                    //cAluno.remove(aluno, true);

                    /* Imprime uma mensagem de sucesso */
                    JOptionPane.showMessageDialog(null,"Seu cadastrato foi excluido", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);

                    /* Leva ate o menu principal */
                    this.vMain = new VisaoMain();
                    vMain.menu();
                    dispose();
                }
                else
                    paginaAluno(cAluno, aluno);
            }
            else{
                //cAluno.remove(aluno, true);

                /* Imprime uma mensagem de sucesso */
                JOptionPane.showMessageDialog(null,"Seu cadastrato foi excluido", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);

                /* Leva ate o menu principal */
                this.vMain = new VisaoMain();
                vMain.menu();
                dispose();
            }
        }
    }

    /* Acao do botao confirmar */
    private void confirmar(ActionEvent actionEvent){
        /* Atribuicao  dos valores no texto para salvar nas variaveis locais*/
        nome = tArea_nome.getText();
        sobrenome = tArea_sobrenome.getText();
        email = tArea_email.getText();
        dia = cbox_dia.getSelectedItem()+"";
        mes = cbox_mes.getSelectedItem()+"";
        ano = cbox_ano.getSelectedItem()+"";
        senha = tArea_senha.getText();
        cSenha = tArea_cSenha.getText();

        condicao = nome.equals("") && sobrenome.equals("") && email.equals("") && dia.equals("") &&
                   mes.equals("") && ano.equals("") &&  senha.equals("")&& cSenha.equals("");

        /* Se todas campos estiverem vazios nao tem como alterar, entao apenas na condicao de
         * pelo menos um nao estiver vazio, ocorre a alteracao */
        if(!condicao){
            /* Confere se as senhas digitas sao iguais, caso nao sejam, imprime uma mensagem de erro */
            if(!(senha.equals(cSenha)))
                JOptionPane.showMessageDialog(null,"Senhas divergem", "ERRO",JOptionPane.ERROR_MESSAGE);                
            else{
                /* Salva apenas aquilo que foi escrito pelo usuario */
                if(!(nome.equals("")))
                    aluno.setNome(nome);
                if(!(sobrenome.equals("")))
                    aluno.setSobrenome(sobrenome);
                if(!(email.equals("")))
                    aluno.setEmail(email);
                if(!(dia.equals("")))
                    aluno.setDiaNasc(Integer.parseInt(dia));
                if(!(mes.equals("")))
                    aluno.setMesNasc(Integer.parseInt(mes));
                if(!(ano.equals("")))
                    aluno.setAnoNasc(Integer.parseInt(ano));
                if(!(senha.equals("")))
                    aluno.setSenha(senha);
            }

            /* Exclui o cadastro antigo e adiciona o novo */
            cAluno.remove(aluno,true);
            cAluno.insere(aluno);

            JOptionPane.showMessageDialog(null,"Seu cadastrato foi alterado", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
            
            /* Deixa as caixas de texto em branco */
            tArea_nome.setText("");
            tArea_nome.requestFocus();
            tArea_sobrenome.setText("");
            tArea_sobrenome.requestFocus();
            tArea_email.setText("");
            tArea_email.requestFocus();
            cbox_dia.setSelectedItem("");
            cbox_mes.setSelectedItem("");
            cbox_ano.setSelectedItem("");
            tArea_senha.setText("");
            tArea_senha.requestFocus();
            tArea_cSenha.setText("");
            tArea_cSenha.requestFocus();
        }

        /* Remove tudo do cabecalho */
        jpanel_dados.remove(label_alterar);
        jpanel_dados.remove(label_sobrenome);
        jpanel_dados.remove(label_senha);
        jpanel_dados.remove(label_cSenha);
        jpanel_dados.remove(tArea_nome);
        jpanel_dados.remove(tArea_sobrenome);
        jpanel_dados.remove(tArea_email);
        jpanel_dados.remove(tArea_senha);
        jpanel_dados.remove(tArea_cSenha);
        jpanel_dados.remove(cbox_dia);
        jpanel_dados.remove(cbox_mes);
        jpanel_dados.remove(cbox_ano);
        jpanel_dados.remove(bt_excluir);
        jpanel_dados.remove(bt_confirmar);
        
        jpanel_fundo.remove(jpanel_dados);

        remove(jpanel_fundo);
        remove(jpanel_cabecalho);

        this.vMain = new VisaoMain();
        vMain.alunoPagina(cAluno,aluno);
        dispose();
    }

    /* Funcao que vai para o menu da VisaoAula */
    private void irPAula(ActionEvent actionEvent){
        this.vMain = new VisaoMain();
        vMain.aulaMenu(cAula,aluno,1);
        dispose();
    }

    /* Funcao que volta pro menu da Visao main */
    private void projeto(ActionEvent actionEvent){
        this.vMain = new VisaoMain();
        vMain.menu();
        dispose();
    }

    /* Tamanho dos vetores para o dia, mês e ano */
    public String[] preencheVetor(int tamanho, int comeco, boolean orientacao) {

        /* Cria um vetor que vai armazenar os tempo correspondente e coloca o primeiro elemento*/
        String[] vetor = new String[tamanho];

        /* Deixa a primeira posição como null */
        vetor[0]= "";

        /* Preeche os elemetos seguintes até o fim do tamanho se acordo com a orientacao dada
         * se for TRUE é crescente, se for FALSE é descrescente*/
        if(orientacao){
            for(int i = 1; i<tamanho ;i++){
                vetor[i] = Integer.toString(comeco + i - 1);
            }
        }
        else{
            for(int i = 1; i<tamanho ;i++){
                vetor[i] = Integer.toString(comeco + tamanho - i - 1);
            }
        }
        /* Retorna o vetor */
        return vetor;
    }
}
