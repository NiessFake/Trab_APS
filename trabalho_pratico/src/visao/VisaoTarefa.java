package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controle.*;
import modelo.*;
import persistencia.AlunoDAO;
import persistencia.NoticiasDAO;
import strategies.Estrategia1;
import strategies.Estrategia2;
import strategies.EstrategiaTabelas;
import strategies.EstrategiasOpBasicas;

public class VisaoTarefa extends JFrame{

    EstrategiasOpBasicas e1,e3;
    EstrategiaTabelas e2;
    
    /* Classes usadas */
    private Usuario usuario = new Usuario();
    private Aluno aluno = new Aluno();
    private Professor professor = new Professor();
    private Professor professor_aux = new Professor();
    private Aula aula = new Aula();
    private Tarefa tarefa = new Tarefa();

    private ControleAluno cAluno;
    private ControleAula cAula;
    private ControleNoticias cNoticias;
    private ControleTarefa cTarefa;
    private VisaoMain vMain;

    /* Variaveis auxiliares */
    protected int funcao, tamanho_voltar,tamanho_capacidade, inscrito;
    protected String materia, capacidade, duracao, descricao;
    protected boolean frequencia;
    protected Aula[] aulas_inscritas;
    protected Aluno[] alunos_inscritos;
    protected String[] dias = new String[7];
    protected String[] colunas_tarefas = {"ID","Titulo","Entregues","Prazo"};

    /* Variavel que guarda a instancia unica */
    //private static VisaoAula uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_aulas = new JPanel();
    JPanel jpanel_dados = new JPanel();
    JPanel jpanel_botoes = new JPanel();
    JPanel jpanel_tarefas = new JPanel();
    JPanel jpanel_cadastro = new JPanel();

    /* Scroll Panels */
    JScrollPane jScroll_tarefa;
    JScrollPane jScroll_alunos;
    
    /* Botões */
    JButton bt_projeto = new JButton("PROJETO");
    JButton bt_aulas = new JButton("AULAS");
    JButton bt_noticias = new JButton("NOTICIAS");
    JButton bt_login = new JButton("LOGIN");
    JButton bt_juntese = new JButton("JUNTE-SE");
    JButton bt_usuario = new JButton("");
    JButton bt_inscrever = new JButton("INSCREVER");
    JButton bt_editar = new JButton("EDITAR AULA");
    JButton bt_alunos = new JButton("alunos");
    JButton bt_tarefas = new JButton("tarefas");
    JButton bt_home = new JButton("home");
    JButton bt_continuar_cadastro = new JButton("CONTINUAR");
    
    /* Labels */
    JLabel label_aula = new JLabel("");
    JLabel label_professor = new JLabel("");
    JLabel label_capacidade = new JLabel("");
    JLabel label_frequencia = new JLabel("");
    JLabel label_segunda = new JLabel("S");
    JLabel label_terca = new JLabel("T");
    JLabel label_quarta = new JLabel("Q");
    JLabel label_quinta = new JLabel("Q");
    JLabel label_sexta = new JLabel("S");
    JLabel label_sabado = new JLabel("S");
    JLabel label_domingo = new JLabel("D");
    JLabel label_editar_aula = new JLabel("EDITA AULA");
    JLabel label_materia = new JLabel("MATERIA");
    JLabel label_duracao = new JLabel("DURACAO");
    JLabel label_dias = new JLabel("DIAS DA SEMANA");
    JLabel label_minutos = new JLabel("minutos");
    JLabel label_descricao = new JLabel("DESCRICAO");

    /* TextAreas */
    JTextArea tArea_descricao = new JTextArea();
    JTextArea tArea_descricao_cadastro = new JTextArea();
    JTextArea tArea_materia = new JTextArea();
    JTextArea tArea_capacidade = new JTextArea();
    JTextArea tArea_duracao = new JTextArea();

    /* CheckBoxes */
    JCheckBox check_frequencia = new JCheckBox("SIM");
    JCheckBox check_segunda = new JCheckBox("SEG");
    JCheckBox check_terca = new JCheckBox("TER");
    JCheckBox check_quarta = new JCheckBox("QUA");
    JCheckBox check_quinta = new JCheckBox("QUI");
    JCheckBox check_sexta = new JCheckBox("SEX");
    JCheckBox check_sabado = new JCheckBox("SAB");
    JCheckBox check_domingo = new JCheckBox("DOM");

    /* Tabelas */
    JTable tabela_tarefas;
    JTable tabela_alunos;

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
    public VisaoTarefa(){
        setSize(750,600);
        setTitle("TP Analise e Projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /* public static VisaoAula getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoAula();
		return uniqueInstance;
	} */
    
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

    public void menuTarefas(Controle controle, Entidade entidade, Entidade entidade2, int tipo){
        setVisible(true);

        funcao = tipo;
        cTarefa = (ControleTarefa)controle;

        this.aula = (Aula)entidade;
        e1 = new Estrategia1(AlunoDAO.getInstancia());
        this.cAluno = new ControleAluno(e1);

        bt_aulas.removeActionListener(this::menu);

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

        cabecalho();

        /* Botoes */
        bt_home.setFont(texto_pequeno);
        bt_home.setBounds(0,400,75,40);
        bt_home.setBackground(Color.lightGray);
		bt_home.setForeground(Color.black);
        bt_home.setBorderPainted(false);
        bt_home.addActionListener(this::home);

        bt_alunos.setFont(texto_pequeno);
        bt_alunos.setBounds(0,360,75,40);
        bt_alunos.setBackground(Color.lightGray);
		bt_alunos.setForeground(Color.black);
        bt_alunos.setBorderPainted(false);
        bt_alunos.addActionListener(this::alunos);

        bt_tarefas.setFont(texto_pequeno);
        bt_tarefas.setBounds(0,320,75,40);
        bt_tarefas.setBackground(Color.white);
		bt_tarefas.setForeground(Color.black);
        bt_tarefas.setBorderPainted(false);
        
        /* Paineis */
        jpanel_dados.setLayout(null);
        jpanel_dados.setBackground(Color.WHITE);
        jpanel_dados.setVisible(true);
        jpanel_dados.setSize(525, 500);
        jpanel_dados.setLocation(150, 100);

        jpanel_botoes.setLayout(null);
        jpanel_botoes.setBackground(Color.gray);
        jpanel_botoes.setVisible(true);
        jpanel_botoes.setSize(75, 500);
        jpanel_botoes.setLocation(75, 100);

        jpanel_botoes.add(bt_home);
        jpanel_botoes.add(bt_tarefas);
        jpanel_botoes.add(bt_alunos);

        jpanel_fundo.add(jpanel_dados);
        jpanel_fundo.add(jpanel_botoes);


        /* Chama a funcao que devolve um objeto contendo os dados do json */
        Object[][] objeto_tabela_tarefas = cTarefa.textoTabelas();

        
        /* Cria uma tabela de selecao unica e nao editavel */
        tabela_tarefas = new JTable(objeto_tabela_tarefas, colunas_tarefas){   
            public boolean isCellEditable(int row, int column) {                
                return false;               
            }
        };
        tabela_tarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        selecao = tabela_tarefas.getSelectionModel();
        selecao.addListSelectionListener(new ListSelectionListener() {
            @Override
                public void valueChanged(ListSelectionEvent e){
                    /* Confere se um item foi selecionado, em caso positivo, pega seu index */
                    if(!selecao.isSelectionEmpty()){
                        int selecionado = selecao.getMinSelectionIndex();

                        String aux = tabela_tarefas.getValueAt(selecionado, 0).toString();

                        aula.setId(Integer.parseInt(aux));

                        /* Apaga tabela antiga */
                        jpanel_fundo.remove(jScroll_tarefa);

                        tarefa = (Tarefa) cTarefa.buscaID(aula.getId());

                        setVisible(false);
                        vMain = new VisaoMain();
                        switch (funcao) {
                            case 1:
                                vMain.aulaPI(tarefa, aluno,funcao);
                                break;

                            case 2:
                                vMain.aulaPI(tarefa, professor,funcao);
                                break;
                        
                            default:
                                vMain.aulaPI(aula, null,funcao);
                                break;
                        }
                        dispose();
                }
            }
        });

        
        //Component celula = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        /* Cria um novo JScrollPane e coloca a tabela nele */
        jScroll_tarefa = new JScrollPane(tabela_tarefas);

        jScroll_tarefa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScroll_tarefa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScroll_tarefa.setBounds(75,75,400,310);
        jScroll_tarefa.getVerticalScrollBar().setValue(0);
        jScroll_tarefa.setVisible(true);

        /* Adiciona o ScrollPane no painel */
        jpanel_dados.add(jScroll_tarefa);
        
    }

    public void paginaIndividual(Controle controle, Entidade entidade, Entidade entidade2, Entidade entidade3,int tipo){
        e1 = new Estrategia1(AlunoDAO.getInstancia());
        cAluno = new ControleAluno(e1); 
        cTarefa = (ControleTarefa)controle;
        this.tarefa = (Tarefa)entidade;
        this.aula = (Aula)entidade2;

        funcao = tipo;
        cabecalho();

        switch (tipo) {
            case 1:        
                aluno = (Aluno)entidade3;        
                bt_usuario.setText(aluno.getNome());
                break;
            
            case 2: 
                professor = (Professor)entidade3;               
                bt_usuario.setText(professor.getNome());
                break;
            
            default:
                break;
        }

        setVisible(true);
        
        
        
    
        /* Paineis */
        jpanel_dados.setLayout(null);
        jpanel_dados.setBackground(Color.WHITE);
        jpanel_dados.setVisible(true);
        jpanel_dados.setSize(525, 500);
        jpanel_dados.setLocation(150, 100);

        jpanel_tarefas.setLayout(null);
        jpanel_tarefas.setBackground(cor_cabecalho);
        jpanel_tarefas.setVisible(true);
        jpanel_tarefas.setSize(525, 60);
        jpanel_tarefas.setLocation(0, 30);

        jpanel_botoes.setLayout(null);
        jpanel_botoes.setBackground(Color.gray);
        jpanel_botoes.setVisible(true);
        jpanel_botoes.setSize(75, 500);
        jpanel_botoes.setLocation(75, 100);

        jpanel_botoes.add(bt_home);
        jpanel_botoes.add(bt_tarefas);
        jpanel_botoes.add(bt_alunos);



        jpanel_dados.add(jpanel_tarefas);

        jpanel_fundo.add(jpanel_dados);
        jpanel_fundo.add(jpanel_botoes);
        
    }


    private void adicionaAluno(ActionEvent actionEvent){
        /* Se o usuario nao estiver logado emite uma mensagem de erro */
        if(funcao == 0){
            JOptionPane.showMessageDialog(null,"Faca Login", "ERRO",JOptionPane.ERROR_MESSAGE);
            return;
        }
        /* Se a aula ja estiver lotada, nao permite uma nova entrada de alunos */
        if((aula.getCapacidade() - cAula.numeroAlunos(aula)) <= 0){
            JOptionPane.showMessageDialog(null,"Numero de inscritos excedidos", "ERRO",JOptionPane.ERROR_MESSAGE);
            return;
        }
        /* Se o vetor de aulas estiver vazio, cria um novo vetor e armazena o id da aula na primeira posicao.
            * Caso contrario, copia o vetor e adiciona o novo elemento a ele. */
        Aula[] aulas_inscritas;
        if (aluno.getAulaInscritas() == null) {
            aulas_inscritas = new Aula[1];
            aulas_inscritas[0] = aula;
        } else {
            Aula[] aux_aluno = aluno.getAulaInscritas();
            aulas_inscritas = Arrays.copyOf(aux_aluno, aux_aluno.length + 1);
            aulas_inscritas[aulas_inscritas.length - 1] = aula;
        }

        aluno.setAulaInscritas(aulas_inscritas);
        cAluno.remove(aluno,false);
        cAluno.insere(aluno);

        /* Adiciona o aluno à aula */
        Aluno[] alunos_inscritos;
        if (aula.getAlunos() == null) {
            alunos_inscritos = new Aluno[1];
            alunos_inscritos[0] = aluno;
        } 
        else {
            Aluno[] aux_aula = aula.getAlunos();
            alunos_inscritos = Arrays.copyOf(aux_aula, aux_aula.length + 1);
            alunos_inscritos[alunos_inscritos.length - 1] = aluno;
        }

        /* Atualiza o vetor de alunos inscritos na aula */
        aula.setAlunos(alunos_inscritos);
        cAula.remove(aula, false);
        cAula.insere(aula);

        aula.setAlunos(alunos_inscritos);
        cAula.remove(aula, false);
        cAula.insere(aula);

        JOptionPane.showMessageDialog(null,"Parabens "+aluno.getNome()+"!! Voce entrou na aula.", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
        
        
        
    }

    private void edita(ActionEvent actionEvent){
        setVisible(false);

        jpanel_dados.remove(label_professor);
        jpanel_dados.remove(label_frequencia);
        jpanel_dados.remove(label_capacidade);
        jpanel_dados.remove(label_segunda);
        jpanel_dados.remove(label_terca);
        jpanel_dados.remove(label_quarta);
        jpanel_dados.remove(label_quinta);
        jpanel_dados.remove(label_sexta);
        jpanel_dados.remove(label_sabado);
        jpanel_dados.remove(label_domingo);
        jpanel_dados.remove(tArea_descricao);
        jpanel_dados.remove(bt_editar);
        jpanel_dados.remove(bt_inscrever);

        jpanel_fundo.remove(jpanel_dados);
        jpanel_fundo.remove(jpanel_botoes);

        /* Botões */
        bt_continuar_cadastro.setFont(texto_padrao);
        bt_continuar_cadastro.setBounds(187, 385, 125,40);
        bt_continuar_cadastro.setBackground(Color.white);
        bt_continuar_cadastro.setForeground(Color.black);
        bt_continuar_cadastro.addActionListener(this::continuarCadastro);

        /* Labels */
        label_editar_aula.setFont(texto_sub_titulo);
        label_editar_aula.setBounds(162, 25, 175,50);

        label_materia.setFont(texto_padrao);
        label_materia.setBounds(60, 90, 125,50);

        label_capacidade.setText("CAPACIDADE");
        label_capacidade.setFont(texto_padrao);
        label_capacidade.setBounds(60, 125, 125,50);

        label_frequencia.setText("FREQUENTE");
        label_frequencia.setForeground(Color.black);
        label_frequencia.setFont(texto_padrao);
        label_frequencia.setBounds(60, 160,125,50);

        label_duracao.setFont(texto_padrao);
        label_duracao.setBounds(60, 195,125,50);

        label_dias.setFont(texto_padrao);
        label_dias.setBounds(60, 230,125,50);

        label_minutos.setFont(texto_padrao);
        label_minutos.setBounds(295, 195,100,50);

        label_descricao.setFont(texto_padrao);
        label_descricao.setBounds(60, 280,100,50);


        /* Caixas de texto */
        tArea_materia.setFont(texto_padrao);
        tArea_materia.setBounds(190, 100,250,25);
        tArea_materia.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        tArea_capacidade.setFont(texto_padrao);
        tArea_capacidade.setBounds(190, 135,250,25);
        tArea_capacidade.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        tArea_duracao.setFont(texto_padrao);
        tArea_duracao.setBounds(190, 205,100,25);
        tArea_duracao.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));

        tArea_descricao_cadastro.setFont(texto_padrao);
        tArea_descricao_cadastro.setBounds(190, 300,250,65);
        tArea_descricao_cadastro.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));


        /* CheckBOXes */
        check_frequencia.setBounds(190, 170, 100, 25);

        check_segunda.setBounds(190, 240, 60, 25);

        check_terca.setBounds(255, 240, 60, 25);

        check_quarta.setBounds(320, 240, 60, 25);

        check_quinta.setBounds(385, 240, 60, 25);

        check_sexta.setBounds(190, 270, 60, 25);

        check_sabado.setBounds(255, 270, 60, 25);
        
        check_domingo.setBounds(320, 270, 60, 25);

        /* Painel */   
        jpanel_cadastro.setLayout(null);
        jpanel_cadastro.setBackground(Color.WHITE);
        jpanel_cadastro.setSize(500, 500);
        jpanel_cadastro.setLocation(125, 100);
        jpanel_cadastro.setVisible(true);

        /* Adiciona elementos no painel */
        jpanel_cadastro.add(label_editar_aula);
        jpanel_cadastro.add(label_materia);
        jpanel_cadastro.add(label_capacidade);
        jpanel_cadastro.add(label_frequencia);
        jpanel_cadastro.add(label_duracao);
        jpanel_cadastro.add(label_dias);
        jpanel_cadastro.add(label_minutos);
        jpanel_cadastro.add(label_descricao);
        jpanel_cadastro.add(tArea_materia);
        jpanel_cadastro.add(tArea_capacidade);
        jpanel_cadastro.add(tArea_duracao);
        jpanel_cadastro.add(tArea_descricao_cadastro);
        jpanel_cadastro.add(check_frequencia);
        jpanel_cadastro.add(check_segunda);
        jpanel_cadastro.add(check_terca);
        jpanel_cadastro.add(check_quarta);
        jpanel_cadastro.add(check_quinta);
        jpanel_cadastro.add(check_sexta);
        jpanel_cadastro.add(check_sabado);
        jpanel_cadastro.add(check_domingo);
        jpanel_cadastro.add(bt_continuar_cadastro);

        jpanel_fundo.add(jpanel_cadastro);

        setVisible(true);
    }

    private void continuarCadastro(ActionEvent actionEvent){
        /* Atribuicao  dos valores no texto para salvar nas variaveis locais*/
        materia = tArea_materia.getText();
        capacidade = tArea_capacidade.getText();
        duracao =  tArea_duracao.getText();
        descricao =  tArea_descricao_cadastro.getText();

        boolean condicao = !(check_segunda.isSelected() && check_terca.isSelected() && check_quarta.isSelected() 
        && check_quinta.isSelected() && check_sexta.isSelected() && check_sabado.isSelected() && check_domingo.isSelected())
        && materia.equals("") && capacidade.equals("") && duracao.equals("")&& descricao.equals("");

        if(!condicao){
        
            /* Se uma das caixas de texto estiver em branco, exibe mensagem de erro */
            if(!(materia.equals("")))
                aula.setMateria(materia);
            if(!(capacidade.equals("")))
                aula.setCapacidade(Integer.parseInt(capacidade));
            if(!(duracao.equals("")))
                aula.setDuracao(Integer.parseInt(duracao));
            if(!(descricao.equals("")))
                aula.setDescricao(descricao);
            
            /* Atribui um valor pra frequencia de acordo com o item selecionado */
            if(check_frequencia.isSelected())
                aula.setFrequencia(true);
            else
                aula.setFrequencia(false);

            /* Escreve os dias da semana selecionados*/            
            if(check_segunda.isSelected())
                dias[0]= "SEGUNDA";
            else
                dias[0]= "";
            if(check_terca.isSelected())
                dias[1]= "TERCA";
            else
                dias[1]= "";
            if(check_quarta.isSelected())
                dias[2]= "QUARTA";
            else
                dias[2]= "";
            if(check_quinta.isSelected())
                dias[3]= "QUINTA";
            else
                dias[3]= "";
            if(check_sexta.isSelected())
                dias[4]= "SEXTA";
            else
                dias[4]= "";
            if(check_sabado.isSelected())
                dias[5]= "SABADO";
            else
                dias[5]= "";
            if(check_domingo.isSelected())
                dias[6]= "DOMINGO";
            else
                dias[6]= "";

            aula.setDias(dias);

            cAula.remove(aula, false);
            cAula.insere(aula);

            /* Deixa as caixas de texto em branco */
            tArea_materia.setText("");
            tArea_materia.requestFocus();
            tArea_capacidade.setText("");
            tArea_capacidade.requestFocus();
            tArea_duracao.setText("");
            tArea_duracao.requestFocus();
            tArea_descricao.setText("");
            tArea_descricao.requestFocus();
            check_frequencia.setSelected(false);
            check_segunda.setSelected(false);
            check_terca.setSelected(false);
            check_quarta.setSelected(false);
            check_quinta.setSelected(false);
            check_sexta.setSelected(false);
            check_sabado.setSelected(false);
            check_domingo.setSelected(false);

            this.vMain = new VisaoMain();
            switch (funcao) {
                case 1:
                    vMain.aulaPI(aula, aluno,funcao);
                    break;

                case 2:
                    vMain.aulaPI(aula, professor,funcao);
                    break;
            
                default:
                    break;
            }
            dispose(); 
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

    private void alunos(ActionEvent actionEvent){
        setVisible(false);
        vMain = new VisaoMain();
        switch (funcao) {
            case 1:
                vMain.aulaPI(aula, aluno,funcao);
                break;

            case 2:
                vMain.aulaPI(aula, professor,funcao);
                break;
        
            default:
                vMain.aulaPI(aula, null,funcao);
                break;
        }
        dispose(); 
    }

    private void noticia(ActionEvent actionEvent){
        e1 = new Estrategia1(NoticiasDAO.getInstancia());
        e2 = new Estrategia2();

        this.cNoticias = new ControleNoticias(e1,e2);
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

    private void home(ActionEvent actionEvent){  
        vMain = new VisaoMain();
        switch (funcao) {
            case 1:
                vMain.aulaPI(aula, aluno,funcao);
                break;

            case 2:
                vMain.aulaPI(aula, professor,funcao);
                break;
        
            default:
                vMain.aulaPI(aula, null,funcao);
                break;
        }
        dispose(); 
    }

    private void menu(ActionEvent actionEvent){       
        this.vMain = new VisaoMain();

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
                vMain.aulaMenu(cAula, usuario, 0);
                dispose();
                break;
        }
    }

    /* Funcao que volta pro menu da Visao main */
    private void projeto(ActionEvent actionEvent){
        vMain = new VisaoMain();
        vMain.menu();
        dispose();
    }
    
}