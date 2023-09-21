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

public class VisaoAula extends JFrame{
    /* Classes usadas */
    private Usuario usuario = new Usuario();
    private Aluno aluno = new Aluno();
    private Professor professor = new Professor();
    private Aula aula = new Aula();

    private ControleUsuario cUsuario;
    private ControleAluno cAluno;
    private ControleProfessor cProfessor;
    private ControleAula cAula;


    /* Variaveis auxiliares */
    protected int funcao, id, tamanho_voltar,tamanho_capacidade;
    protected String capacidade;
    protected int[] aulas_inscritas,alunos_inscritos;
    protected String[] colunas_aula = {"ID", "Materia", "Professor", "Capacidade", "Duracao","Frequente"};
    protected String[] colunas_aluno = {"ID","Nome","Idade"};

    /* Variavel que guarda a instancia unica */
    private static VisaoAula uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_aulas = new JPanel();
    JPanel jpanel_dados = new JPanel();
    JPanel jpanel_botoes = new JPanel();
    JPanel jpanel_materia = new JPanel();

    /* Scroll Panels */
    JScrollPane jScroll_aula;
    JScrollPane jScroll_alunos;
    
    /* Botões */
    JButton bt_projeto = new JButton("PROJETO");
    JButton bt_aulas = new JButton("AULAS");
    JButton bt_mensagens = new JButton("MENSAGENS");
    JButton bt_login = new JButton("LOGIN");
    JButton bt_juntese = new JButton("JUNTE-SE");
    JButton bt_usuario = new JButton("");
    JButton bt_inscrever = new JButton("INSCREVER");
    JButton bt_editar = new JButton("INSCREVER");
    JButton bt_alunos = new JButton("alunos");
    JButton bt_tarefas = new JButton("tarefas");
    JButton bt_home = new JButton("home");

    
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

    /* TextAreas */
    JTextArea tArea_descricao = new JTextArea();

    /* Tabelas */
    JTable tabela_aulas;
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
        bt_aulas.setBounds(240,30,125,40);
        bt_aulas.setBackground(Color.white);
		bt_aulas.setForeground(Color.black);

        bt_mensagens.setFont(texto_padrao);
        bt_mensagens.setBounds(370,30,125,40);
        bt_mensagens.setBackground(Color.white);
		bt_mensagens.setForeground(Color.black);

        if(id == 0){
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
        else{
            bt_usuario.setFont(texto_padrao);
            bt_usuario.setBounds(575,30,125,40);
            bt_usuario.setBackground(Color.white);
            bt_usuario.setForeground(Color.black);
            bt_usuario.addActionListener(this::usuario);

            jpanel_cabecalho.add(bt_usuario);
        }

        bt_projeto.setFont(texto_titulo);
        bt_projeto.setBounds(20, 30,200,50);
        bt_projeto.setBackground(cor_cabecalho);
		bt_projeto.setForeground(Color.black);
        bt_projeto.setBorderPainted(false);
        bt_projeto.addActionListener(this::projeto);
        
        /* Adiciona os elementos no cabecalho, em seguida adiciona-o no fundo e adiciona o fundo */
        jpanel_cabecalho.add(bt_aulas);
        jpanel_cabecalho.add(bt_mensagens);
        jpanel_cabecalho.add(bt_projeto);
        
        add(jpanel_cabecalho);
        add(jpanel_fundo);
    }

    public void menuAulas(Controle controle, Entidade entidade, int tipo){
        setVisible(true);

        funcao = tipo;
        cAula = (ControleAula)controle;
        this.cUsuario = new ControleUsuario();
        this.cAluno = new ControleAluno();
        this.cProfessor = new ControleProfessor();

        switch (tipo) {
            case 1:                
                id = (((Aluno)entidade).getId());
                aluno = (Aluno)entidade;
                bt_usuario.setText(aluno.getNome());;
                break;
            
            case 2:                
                id = (((Professor)entidade).getId());
                professor = (Professor)entidade;
                bt_usuario.setText(professor.getNome());;
                break;
            
            default:
                id = (((Usuario)entidade).getId());
                break;
        }

        cabecalho();

        /* Chama a funcao que devolve um objeto contendo os dados do json */
        Object[][] objeto_tabela_aula = cAula.textoTabelas();

        
        /* Cria uma tabela de selecao unica e nao editavel */
        tabela_aulas = new JTable(objeto_tabela_aula, colunas_aula){   
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
                        jpanel_fundo.remove(jScroll_aula);

                        setVisible(false);
                        paginaIndividual();
                }
            }
        });

        
        //Component celula = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        /* Cria um novo JScrollPane e coloca a tabela nele */
        jScroll_aula = new JScrollPane(tabela_aulas);

        jScroll_aula.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScroll_aula.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScroll_aula.setBounds(100,150, 550,350);
        jScroll_aula.getVerticalScrollBar().setValue(0);
        jScroll_aula.setVisible(true);

        /* Adiciona o ScrollPane no painel */
        jpanel_fundo.add(jScroll_aula);
        
    }

    public void paginaIndividual(){
        setVisible(true);
        
        aula = cAula.buscaID(aula.getId());

        String vetor_dias[] = aula.getDias();
        tamanho_voltar = aula.getMateria().length()*15;

        for(int i =0; i<aula.getDias().length;i++){
            if(vetor_dias[i].equals("SEGUNDA"))
                label_segunda.setForeground(cor_cabecalho);

            if(vetor_dias[i].equals("TERCA"))
                label_terca.setForeground(cor_cabecalho);

            if(vetor_dias[i].equals("QUARTA"))
                label_quarta.setForeground(cor_cabecalho);

            if(vetor_dias[i].equals("QUINTA"))
                label_quinta.setForeground(cor_cabecalho);

            if(vetor_dias[i].equals("SEXTA"))
                label_sexta.setForeground(cor_cabecalho);

            if(vetor_dias[i].equals("SABADO"))
                label_sabado.setForeground(cor_cabecalho);

            if(vetor_dias[i].equals("DOMINGO"))
                label_domingo.setForeground(cor_cabecalho);
        }

        /* Botoes */
        bt_home.setFont(texto_pequeno);
        bt_home.setBounds(0,400,75,40);
        bt_home.setBackground(Color.white);
		bt_home.setForeground(Color.black);
        bt_home.setBorderPainted(false);

        bt_alunos.setFont(texto_pequeno);
        bt_alunos.setBounds(0,360,75,40);
        bt_alunos.setBackground(Color.lightGray);
		bt_alunos.setForeground(Color.black);
        bt_alunos.setBorderPainted(false);
        bt_alunos.addActionListener(this::alunos);

        bt_tarefas.setFont(texto_pequeno);
        bt_tarefas.setBounds(0,320,75,40);
        bt_tarefas.setBackground(Color.lightGray);
		bt_tarefas.setForeground(Color.black);
        bt_tarefas.setBorderPainted(false);

        if(funcao != 2){
            bt_inscrever.setFont(texto_padrao);
            bt_inscrever.setBounds(362,400,125,40);
            bt_inscrever.setBackground(Color.white);
            bt_inscrever.setForeground(Color.black);
            bt_inscrever.addActionListener(this::adicionaAluno);
            jpanel_dados.add(bt_inscrever);
        }
        else{
            bt_editar.setFont(texto_padrao);
            bt_editar.setBounds(362,400,125,40);
            bt_editar.setBackground(Color.white);
            bt_editar.setForeground(Color.black);
            jpanel_dados.add(bt_editar);
        }

        /* JLabels */
        label_aula.setText(aula.getMateria());
        label_aula.setFont(texto_sub_titulo);
        label_aula.setBounds(475-tamanho_voltar, 10,tamanho_voltar,40);
		label_aula.setForeground(Color.white);

        label_domingo.setFont(texto_padrao);
        label_domingo.setBounds(50, 110,125,40);

        label_segunda.setFont(texto_padrao);
        label_segunda.setBounds(100, 110,125,40);

        label_terca.setFont(texto_padrao);
        label_terca.setBounds(150, 110,125,40);

        label_quarta.setFont(texto_padrao);
        label_quarta.setBounds(200, 110,125,40);

        label_quinta.setFont(texto_padrao);
        label_quinta.setBounds(250, 110,125,40);

        label_sexta.setFont(texto_padrao);
        label_sexta.setBounds(300, 110,125,40);

        label_sabado.setFont(texto_padrao);
        label_sabado.setBounds(350, 110,125,40);

        /* Se for frequencia igual a true, escreve que eh frequente. Caso contrario escreve que nao eh frequente */
        if(aula.getFrequencia())
            label_frequencia.setText("FREQUENTE");
        else
            label_frequencia.setText("NAO FREQUENTE");
        label_frequencia.setForeground(cor_cabecalho);
        label_frequencia.setFont(texto_padrao);
        label_frequencia.setBounds(400, 110,100,40);

        professor = cProfessor.buscaID(aula.getIdProfessor());
        label_professor.setText("PROFESSOR: " + professor.getNome() + " " + professor.getSobrenome());
        label_professor.setFont(texto_padrao);
        label_professor.setBounds(50, 162,225,40);

        capacidade = "CAPACIDADE: " + cAula.numeroAlunos(aula) + "/" + aula.getCapacidade();
        tamanho_capacidade = capacidade.length() * 8;
        label_capacidade.setText(capacidade);
        label_capacidade.setFont(texto_padrao);
        label_capacidade.setBounds(475-tamanho_capacidade, 162,tamanho_capacidade,40);

        /* Caixas de texto */
        tArea_descricao.setFont(texto_padrao);
        tArea_descricao.setBounds(50, 225,275,225);
        tArea_descricao.setBackground(Color.white);
        tArea_descricao.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,cor_cabecalho));
        tArea_descricao.setLineWrap(true);
        tArea_descricao.setWrapStyleWord(true);
        tArea_descricao.setEditable(false);

        /*if(aula.getDescricao().equals(null))   
            tArea_descricao.setText("Ainda nao foi adicionada nenhuma descricao a aula. ");
        else   
            tArea_descricao.setText(aula.getDescricao());*/
        
    
        /* Paineis */
        jpanel_dados.setLayout(null);
        jpanel_dados.setBackground(Color.WHITE);
        jpanel_dados.setVisible(true);
        jpanel_dados.setSize(525, 500);
        jpanel_dados.setLocation(150, 100);

        jpanel_materia.setLayout(null);
        jpanel_materia.setBackground(cor_cabecalho);
        jpanel_materia.setVisible(true);
        jpanel_materia.setSize(525, 60);
        jpanel_materia.setLocation(0, 30);

        jpanel_botoes.setLayout(null);
        jpanel_botoes.setBackground(Color.gray);
        jpanel_botoes.setVisible(true);
        jpanel_botoes.setSize(75, 500);
        jpanel_botoes.setLocation(75, 100);

        jpanel_botoes.add(bt_home);
        jpanel_botoes.add(bt_tarefas);
        jpanel_botoes.add(bt_alunos);

        jpanel_materia.add(label_aula);

        jpanel_dados.add(label_professor);
        jpanel_dados.add(label_frequencia);
        jpanel_dados.add(label_capacidade);
        jpanel_dados.add(label_segunda);
        jpanel_dados.add(label_terca);
        jpanel_dados.add(label_quarta);
        jpanel_dados.add(label_quinta);
        jpanel_dados.add(label_sexta);
        jpanel_dados.add(label_sabado);
        jpanel_dados.add(label_domingo);
        jpanel_dados.add(tArea_descricao);
        jpanel_dados.add(jpanel_materia);

        jpanel_fundo.add(jpanel_dados);
        jpanel_fundo.add(jpanel_botoes);
        
    }


    private void adicionaAluno(ActionEvent actionEvent){
        /* Variaveis auxiliares */
        int[] aux_aluno,aux_aula;
        boolean count = true;

        /* Se o usuario nao estiver logado emite uma mensagem de erro */
        if(funcao == 0)
            JOptionPane.showMessageDialog(null,"Faca Login", "ERRO",JOptionPane.ERROR_MESSAGE);
        else{
            /* Se a aula ja estiver lotada, nao permite uma nova entrada de alunos */
            if((aula.getCapacidade()-cAula.numeroAlunos(aula)) < 0)
                JOptionPane.showMessageDialog(null,"Numero de inscritos excedidos", "ERRO",JOptionPane.ERROR_MESSAGE);
            else{
                /* Se o vetor de aulas estiver vazio, cria um novo vetor e armazena o id da aula na primeira posicao.
                 * Caso contrario, copia o vetor e adiciona o novo elemento a ele. */
                if(aluno.getIdAulaInscritas() == null){
                    aulas_inscritas = new int[30];
                    aulas_inscritas[0] = aula.getId();
                }
                else{
                    /*Insere as aulas no vetor de alunos */
                    aux_aluno = aluno.getIdAulaInscritas();

                    System.out.printf("\n"+aux_aluno+""+"\n");

                    aulas_inscritas = new int[30];
                    aulas_inscritas[0] = aux_aluno[0];
                    for(int i = 1; i < 30 ;i++){
                        if(aux_aluno[i] == 0 && count){
                            aulas_inscritas[i] = aula.getId();
                            count = false;
                        }
                        else
                            aulas_inscritas[i] = aux_aluno[i];
                    }
                }

                /* Se o vetor de alunos estiver vazio, cria um novo vetor e armazena o id do aluno na primeira posicao.
                 * Caso contrario, copia o vetor e adiciona o novo elemento a ele. */
                if(aula.getIdAlunos() == null){
                    alunos_inscritos = new int[aula.getCapacidade()];
                    alunos_inscritos[0] = aluno.getId();
                }
                else{
                    /* Insere os alunos no vetor da aula. Primeiro salva o vetor numa variavel auxiliar. Em seguida
                     * coloca atribui o valor do primeiro aluno ao primeiro elemento do vetor de armazenamento. Por fim
                     * armazena o restante dos elementos.  */
                    aux_aula = aula.getIdAlunos();
                    alunos_inscritos = new int[aula.getCapacidade()];
                    alunos_inscritos[0] = aux_aula[0];

                    for(int j = 1; j < aula.getCapacidade() ;j++){
                        if(aux_aula[j] == 0 && count){
                            alunos_inscritos[j] = aluno.getId();
                            count = false;
                        }
                        else
                            alunos_inscritos[j] = aux_aula[j];
                    }
                }
                
                aluno.setIdAulaInscritas(aulas_inscritas);
                cAluno.remove(aluno,false);
                cAluno.insere(aluno);

                aula.setIdAlunos(alunos_inscritos);
                cAula.remove(aula, false);
                cAula.insere(aula);

                JOptionPane.showMessageDialog(null,"Parabens "+aluno.getNome()+"!! Voce entrou na aula.", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
        
    }

    private void login(ActionEvent actionEvent){
        removeItens();
        setVisible(false);
        
        /* Chama a instancia unica do VisaoUsuario e vai até ela na funcao login */
        VisaoUsuario.getInstance().login(cUsuario);
    } 

    private void cadastro(ActionEvent actionEvent){
        removeItens();
        setVisible(false);

        /* Chama a instancia unica do VisaoUsuario e vai até ela na funcao cadastro */
        VisaoUsuario.getInstance().cadastro(cUsuario);
    }

    private void usuario(ActionEvent actionEvent){
        removeItens();
        setVisible(false);

        /* Chama a instancia unica do VisaoAluno ou do Visao Professor, redirecionando para suas
         * respectivas paginas de acordo com sua funcao*/
        if(funcao == 1)
            VisaoAluno.getInstance().paginaAluno(cAluno, aluno);
        else
            VisaoProfessor.getInstance().paginaProfessor(cProfessor, professor);
    }

    private void alunos(ActionEvent actionEvent){
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

        bt_alunos.setBackground(Color.white);
        bt_alunos.removeActionListener(this::alunos);

        bt_home.setBackground(Color.lightGray);
        bt_home.addActionListener(this::home);
        
        Object[][] objeto_tabela_aluno = cAluno.textoAlunos();
        
        /* Cria uma tabela de selecao unica e nao editavel */
        tabela_alunos = new JTable(objeto_tabela_aluno, colunas_aluno){   
            public boolean isCellEditable(int row, int column) {                
                return false;               
            }
        };
        tabela_alunos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /* Cria um novo JScrollPane e coloca a tabela nele */
        jScroll_alunos = new JScrollPane(tabela_alunos);

        jScroll_alunos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScroll_alunos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScroll_alunos.setBounds(112,145,300,260);
        jScroll_alunos.getVerticalScrollBar().setValue(0);
        jScroll_alunos.setVisible(true);

        /* Adiciona o ScrollPane no painel */
        jpanel_dados.add(jScroll_alunos);

        
        setVisible(true);
    }

    private void home(ActionEvent actionEvent){  
        setVisible(false);   
        jpanel_dados.remove(jScroll_alunos);

        bt_home.removeActionListener(this::home);

        paginaIndividual();     
    }

    private void menu(ActionEvent actionEvent){        
        bt_home.removeActionListener(this::home);
        bt_alunos.removeActionListener(this::alunos);

        
        switch (funcao) {
            case 1:                                
                menuAulas(cAula, aluno, 1);
                break;
            
            case 2:                       
                menuAulas(cAula, professor, 2);
                break;
            
            default:       
                menuAulas(cAula, usuario, 0);
                break;
        }
    }

    /* Funcao que volta pro menu da Visao main */
    private void projeto(ActionEvent actionEvent){
        removeItens();

        VisaoMain.getInstance().menu();

        setVisible(false);

    }


    /* Funcao que remove todos os itens */
    public void removeItens(){
        jpanel_cabecalho.remove(bt_aulas);
        jpanel_cabecalho.remove(bt_mensagens);
        jpanel_cabecalho.remove(bt_projeto);
        jpanel_cabecalho.remove(bt_login);
        jpanel_cabecalho.remove(bt_juntese);
        jpanel_cabecalho.remove(bt_usuario);
        
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
        jpanel_dados.remove(jpanel_materia);
        jpanel_dados.remove(bt_editar);
        jpanel_dados.remove(bt_inscrever);
        //jpanel_dados.remove(jScroll_alunos);
        

        jpanel_fundo.remove(jpanel_dados);
        jpanel_fundo.remove(jpanel_botoes);
        jpanel_fundo.remove(jScroll_aula);

        remove(jpanel_cabecalho);
        remove(jpanel_fundo);
    }


}
