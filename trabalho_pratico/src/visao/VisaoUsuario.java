package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.Border;

import controle.*;
import modelo.*;
import persistencia.*;

public class VisaoUsuario extends JFrame {
    /* Cria atributos do tipo modelo e controle */
    private Aluno aluno = new Aluno();
    private Professor professor = new Professor();

    private PersistenciaAluno pAluno = new PersistenciaAluno();
    private PersistenciaProfessor pProfessor = new PersistenciaProfessor();

    private ControleUsuario cUsuario;
    //private ControleAluno cAluno;
    //private ControleProfessor cProfessor;
    

    /* Variaveis que serao usadas */
    String[] papelVetor = {"","Aluno","Professor"};
    String nome, sobrenome, email, dia, mes, ano, papel, id, senha, cSenha;
    int idAux = 0;
    boolean condicao;

    /* Atributo que vai guardar a única instância da interface */
    private static VisaoUsuario uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecario = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_login = new JPanel();
    JPanel jpanel_cadastro = new JPanel();

    /* Botões */
    JButton bt_login = new JButton("Login");
    JButton bt_juntese = new JButton("Junte-se");
    JButton bt_continuar_cadastro = new JButton("Continuar");
    JButton bt_continuar_login = new JButton("Continuar");

    /* Labels */
    JLabel projeto = new JLabel("PROJETO");
    JLabel label_cadatro = new JLabel("CADASTRO");
    JLabel label_login = new JLabel("LOGIN");
    JLabel label_nome = new JLabel("NOME:");
    JLabel label_sobrenome = new JLabel("SOBRENOME:");
    JLabel label_email = new JLabel("EMAIL:");
    JLabel label_dataNasc = new JLabel("DATA DE NASCIMENTO:");
    JLabel label_papel = new JLabel("FUNCAO:");
    JLabel label_id = new JLabel("ID:");
    JLabel label_senha = new JLabel("SENHA:");
    JLabel label_cSenha = new JLabel("CONFIRME A SENHA:");

    /* Caixas de Texto */
    JTextArea tArea_nome = new JTextArea();
    JTextArea tArea_sobrenome = new JTextArea();
    JTextArea tArea_email = new JTextArea();
    JTextArea tArea_id = new JTextArea();
    JTextArea tArea_senha = new JTextArea();
    JTextArea tArea_cSenha = new JTextArea();

    /*ComboBox */
    JComboBox cbox_dia = new JComboBox<>(preencheVetor(32, 1, true));
    JComboBox cbox_mes = new JComboBox<>(preencheVetor(13, 1, true));
    JComboBox cbox_ano = new JComboBox<>(preencheVetor(151, 1874, false));
    JComboBox cbox_papel = new JComboBox<>(papelVetor);

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,20);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecario = new Color(0,204,155);
    Color cor_textos = new Color(163, 184, 204);

    /* Contrução do JFrame que será usado */
    public VisaoUsuario(){
        setSize(750,600);
        setTitle("TP Analise e Projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /* Cria uma instancia única para essa interface (Padrao Singleton) */
    public static VisaoUsuario getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoUsuario();
		return uniqueInstance;
	}

    /* Interface do cabecario */
    public void cabecario(){
        /* Botões */
        bt_login.setFont(texto_padrao);
        bt_login.setBounds(0,0,100,40);
		bt_login.setLocation(520, 30);
        bt_login.setBackground(Color.white);
		bt_login.setForeground(Color.black);
        bt_login.addActionListener(this::vaiPLogin);

        bt_juntese.setFont(texto_padrao);
        bt_juntese.setBounds(0,0,100,40);
		bt_juntese.setLocation(625, 30);
        bt_juntese.setBackground(Color.white);
		bt_juntese.setForeground(Color.black);
        bt_juntese.addActionListener(this::vaiPCadastro);

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
        jpanel_cabecario.add(bt_login);
        jpanel_cabecario.add(bt_juntese);
        jpanel_cabecario.add(projeto);
        
        add(jpanel_cabecario);
        add(jpanel_fundo);
    }

    /* Interface para o cadastro */
    public void cadastro(Controle controle){
        setVisible(true);

        cabecario();

        /* Botões */
        bt_continuar_cadastro.setFont(texto_padrao);
        bt_continuar_cadastro.setBounds(0,0,125,40);
		bt_continuar_cadastro.setLocation(187, 375);
        bt_continuar_cadastro.setBackground(Color.white);
		bt_continuar_cadastro.setForeground(Color.black);
        bt_continuar_cadastro.addActionListener(this::continuarCadastro);

        /* Labels */
        label_cadatro.setFont(texto_sub_titulo);
        label_cadatro.setBounds(0,0,125,50);
		label_cadatro.setLocation(187, 25);

        label_nome.setFont(texto_padrao);
        label_nome.setBounds(0,0,175,50);
		label_nome.setLocation(37, 110);

        label_sobrenome.setFont(texto_padrao);
        label_sobrenome.setBounds(0,0,175,50);
		label_sobrenome.setLocation(37, 140);

        label_email.setFont(texto_padrao);
        label_email.setBounds(0,0,175,50);
		label_email.setLocation(37, 170);

        label_dataNasc.setFont(texto_padrao);
        label_dataNasc.setBounds(0,0,175,50);
		label_dataNasc.setLocation(37, 200);

        label_papel.setFont(texto_padrao);
        label_papel.setBounds(0,0,175,50);
		label_papel.setLocation(37, 230);

        label_senha.setFont(texto_padrao);
        label_senha.setBounds(0,0,175,50);
		label_senha.setLocation(37, 260);

        label_cSenha.setFont(texto_padrao);
        label_cSenha.setBounds(0,0,175,50);
		label_cSenha.setLocation(37, 290);

        /* Caixas de texto */
        tArea_nome.setFont(texto_padrao);
        tArea_nome.setBounds(0,0,250,25);
		tArea_nome.setLocation(218, 125);
        tArea_nome.setBackground(cor_textos);

        tArea_sobrenome.setFont(texto_padrao);
        tArea_sobrenome.setBounds(0,0,250,25);
		tArea_sobrenome.setLocation(218, 155);
        tArea_sobrenome.setBackground(cor_textos);

        tArea_email.setFont(texto_padrao);
        tArea_email.setBounds(0,0,250,25);
		tArea_email.setLocation(218, 185);
        tArea_email.setBackground(cor_textos);

        tArea_senha.setFont(texto_padrao);
        tArea_senha.setBounds(0,0,250,25);
		tArea_senha.setLocation(218, 275);
        tArea_senha.setBackground(cor_textos);

        tArea_cSenha.setFont(texto_padrao);
        tArea_cSenha.setBounds(0,0,250,25);
		tArea_cSenha.setLocation(218, 305);
        tArea_cSenha.setBackground(cor_textos);

        /* ComboBOXes */
        cbox_dia.setBounds(0,0,80,25);
		cbox_dia.setLocation(218, 215);

        cbox_mes.setBounds(0,0,80,25);
		cbox_mes.setLocation(303, 215);

        cbox_ano.setBounds(0,0,80,25);
		cbox_ano.setLocation(388, 215);

        cbox_papel.setBounds(0,0,250,25);
		cbox_papel.setLocation(218, 245);

        /* Painel */   
        jpanel_cadastro.setLayout(null);
        jpanel_cadastro.setBackground(Color.WHITE);
        jpanel_cadastro.setSize(500, 500);
        jpanel_cadastro.setLocation(125, 100);
        jpanel_cadastro.setVisible(true);

        /* Adiciona elementos no painel */
        jpanel_cadastro.add(label_cadatro);
        jpanel_cadastro.add(label_nome);
        jpanel_cadastro.add(tArea_nome);
        jpanel_cadastro.add(label_sobrenome);
        jpanel_cadastro.add(tArea_sobrenome);
        jpanel_cadastro.add(label_email);
        jpanel_cadastro.add(tArea_email);
        jpanel_cadastro.add(label_dataNasc);
        jpanel_cadastro.add(cbox_dia);
        jpanel_cadastro.add(cbox_mes);
        jpanel_cadastro.add(cbox_ano);
        jpanel_cadastro.add(label_papel);
        jpanel_cadastro.add(cbox_papel);
        jpanel_cadastro.add(label_senha);
        jpanel_cadastro.add(tArea_senha);
        jpanel_cadastro.add(label_cSenha);
        jpanel_cadastro.add(tArea_cSenha);
        jpanel_cadastro.add(bt_continuar_cadastro);

        
        jpanel_fundo.add(jpanel_cadastro);
    }


    /* Interface para o cadastro */
    public void login(Controle controle){
        setVisible(true);

        cabecario();

        /* Botões */
        bt_continuar_login.setFont(texto_padrao);
        bt_continuar_login.setBounds(0,0,125,25);
		bt_continuar_login.setLocation(125, 325);
        bt_continuar_login.setBackground(Color.white);
		bt_continuar_login.setForeground(Color.black);
        bt_continuar_login.addActionListener(this::continuarLogin);

        /* Labels */
        label_login.setFont(texto_sub_titulo);
        label_login.setBounds(0,0,70,50);
		label_login.setLocation(152, 100);

        label_id.setFont(texto_padrao);
        label_id.setBounds(0,0,100,50);
		label_id.setLocation(62, 175);

        label_senha.setFont(texto_padrao);
        label_senha.setBounds(0,0,100,50);
		label_senha.setLocation(62, 210);

        label_papel.setFont(texto_padrao);
        label_papel.setBounds(0,0,100,50);
		label_papel.setLocation(62, 245);

        /* Caixas de texto */
        tArea_id.setFont(texto_padrao);
        tArea_id.setBounds(0,0,150,25);
		tArea_id.setLocation(162, 185);
        tArea_id.setBackground(cor_textos);

        tArea_senha.setFont(texto_padrao);
        tArea_senha.setBounds(0,0,150,25);
		tArea_senha.setLocation(162, 220);
        tArea_senha.setBackground(cor_textos);

        /* ComboBoxes */
        cbox_papel.setBounds(0,0,150,25);
		cbox_papel.setLocation(162, 255);

        /* Paineis */
        jpanel_login.setLayout(null);
        jpanel_login.setBackground(Color.WHITE);
        jpanel_login.setSize(375, 500);
        jpanel_login.setLocation(375, 100);
        jpanel_login.setVisible(true);

        /* Adiciona elementos no painel */
        jpanel_login.add(bt_continuar_login);
        jpanel_login.add(label_login);
        jpanel_login.add(label_id);
        jpanel_login.add(tArea_id);
        jpanel_login.add(label_senha);
        jpanel_login.add(tArea_senha);
        jpanel_login.add(label_papel);
        jpanel_login.add(cbox_papel);

        jpanel_fundo.add(jpanel_login);

    }



    /* Ações dos botões */
    /* Acoes do botao continuar do cadastro */
    private void continuarCadastro(ActionEvent actionEvent){
        /* Atribuicao  dos valores no texto para salvar nas variaveis locais*/
        nome = tArea_nome.getText();
        sobrenome = tArea_sobrenome.getText();
        email = tArea_email.getText();
        dia = cbox_dia.getSelectedItem()+"";
        mes = cbox_mes.getSelectedItem()+"";
        ano = cbox_ano.getSelectedItem()+"";
        senha = tArea_senha.getText();
        cSenha = tArea_cSenha.getText();

        /* expressão para saber se os dados do usuario fotam preenchidas */
        condicao = nome.equals("") && sobrenome.equals("") && email.equals("") && dia.equals("") &&
        mes.equals("") && ano.equals("") && senha.equals("") && cSenha.equals("");
        if(condicao){
            tArea_senha.setText("");
            tArea_senha.requestFocus();
            tArea_cSenha.setText("");
            tArea_cSenha.requestFocus();
            JOptionPane.showMessageDialog(null,"Informação faltando", "ERRO",JOptionPane.ERROR_MESSAGE);
        }
        else{
            /* Compara se as senhas digitas são iguais. Em caso de serem diferentes, imprime uma 
             * mensagem de erro. Do contrário prossegue com a inserção */
            if(!(senha.equals(cSenha)))
                JOptionPane.showMessageDialog(null,"Senhas divergem", "ERRO",JOptionPane.ERROR_MESSAGE);
            else{
                /* Variável que converte o item do ComboBox para texto, especificamente da funcao */
                String aux = cbox_papel.getSelectedItem()+"";

                /* Insere o usuário de acordo com a funcao selecionada */
                if(aux.equals("Aluno")){
                    /* Pega os dados digitados e insere o usuário no sistema */
                    aluno.setNome(nome);
                    aluno.setSobrenome(sobrenome);
                    aluno.setEmail(email);
                    aluno.setDiaNasc(Integer.parseInt(dia));
                    aluno.setMesNasc(Integer.parseInt(mes));
                    aluno.setAnoNasc(Integer.parseInt(ano));
                    aluno.setSenha(senha);

                    /* Insere o aluno no sistema */
                    pAluno.insere(aluno);

                    /* Uma mensagem de sucesso aparece e apresenta o id do usuário */
                    JOptionPane.showMessageDialog(null,"Parabéns, você foi cadastrado com sucesso.\nSeu id é: "+ pAluno.devolveMaiorID(), "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    /* Pega os dados digitados e insere o usuário no sistema */
                    professor.setNome(nome);
                    professor.setSobrenome(sobrenome);
                    professor.setEmail(email);
                    professor.setDiaNasc(Integer.parseInt(dia));
                    professor.setMesNasc(Integer.parseInt(mes));
                    professor.setAnoNasc(Integer.parseInt(ano));
                    professor.setSenha(senha);

                    /* Insere o professor no sistema */
                    pProfessor.insere(professor);

                    /* Uma mensagem de sucesso aparece e apresenta o id do usuário */
                    JOptionPane.showMessageDialog(null,"Parabéns, você foi cadastrado com sucesso.\nSeu id é: "+ pProfessor.devolveMaiorID(), "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                }
        
                
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
                cbox_papel.setSelectedItem("");
                tArea_senha.setText("");
                tArea_senha.requestFocus();
                tArea_cSenha.setText("");
                tArea_cSenha.requestFocus();

                /* Chama a funcao que redireciona para o login */
                vaiPLogin(actionEvent);
            }
        }
    }

    /* Acoes do botao continuar do login */
    public void continuarLogin(ActionEvent actionEvent){
        /* Armazena o id e a senha em uma variavel local, que sera usada para comparacao */
        id = tArea_id.getText();
        senha = tArea_senha.getText();

        /* Variável que converte o item do ComboBox para texto, especificamente da funcao */
        String aux = cbox_papel.getSelectedItem()+"";

        /* Insere o usuário de acordo com a funcao selecionada */
        if(aux.equals("Aluno")){
            aluno = pAluno.buscaID(Integer.parseInt(id));
            
            /* Se o id for igual a 0, o usuario nao existe e uma mensagem de erro eh exibida */
            if(aluno.getId()==0)
                JOptionPane.showMessageDialog(null,"ID não encontrado", "ERRO",JOptionPane.ERROR_MESSAGE);
            else{
                /* Caso a senha digitada nao seja igual a armazenada pelo usuario, exibe mensagem de erro.
                 * Caso seja igual imprime uma mensagem de sucesso e redireciona para a pagina do usuario. */
                if(!(aluno.getSenha().equals(senha)))
                    JOptionPane.showMessageDialog(null,"Senha incorreta", "ERRO",JOptionPane.ERROR_MESSAGE);
                else{
                    JOptionPane.showMessageDialog(null,"Seja bem-vinde", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                    continuarUsuario(true, aluno);
                }
            }
                
        }
        else{
           professor = pProfessor.buscaID(Integer.parseInt(id));
            /* Se o id for igual a 0, o usuario nao existe e uma mensagem de erro eh exibida */
            if(professor.getId()==0)
                JOptionPane.showMessageDialog(null,"ID não encontrado", "ERRO",JOptionPane.ERROR_MESSAGE);
            else{
                /* Caso a senha digitada nao seja igual a armazenada pelo usuario, exibe mensagem de erro.
                 * Caso seja igual imprime uma mensagem de sucesso e redireciona para a pagina do usuario. */
                if(professor.getSenha().equals(senha)){
                    JOptionPane.showMessageDialog(null,"Seja bem-vinde", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                    continuarUsuario(false, professor);
                }
                else
                    JOptionPane.showMessageDialog(null,"Senha incorreta", "ERRO",JOptionPane.ERROR_MESSAGE);
            } 
        }
    }

    /* Funcao que redireciona o usuario para a sua pagina apos o login */
    public void continuarUsuario(Boolean tipoUsuario, Entidade entidade){
        tArea_id.setText("");
        tArea_id.requestFocus();
        tArea_senha.setText("");
        tArea_senha.requestFocus();
        cbox_papel.setSelectedItem("");

        /* Exclui o layout do cadastro e do login */
        jpanel_login.remove(bt_continuar_login);
        jpanel_login.remove(label_login);
        jpanel_login.remove(label_id);
        jpanel_login.remove(tArea_id);
        jpanel_login.remove(label_senha);
        jpanel_login.remove(tArea_senha);
        jpanel_login.remove(label_papel);
        jpanel_login.remove(cbox_papel);

        jpanel_fundo.remove(jpanel_login);

        jpanel_cabecario.remove(bt_login);
        jpanel_cabecario.remove(bt_juntese);
        jpanel_cabecario.remove(projeto);

        remove(jpanel_cabecario);
        remove(jpanel_fundo);

        setVisible(false);

        /* Considerando true para Aluno e false para professor, uma comparacao eh feita e 
         * leva o usuario para sua pagina correspondente de acordo com seu papel */
        if(tipoUsuario)
            VisaoAluno.getInstance().paginaAluno(entidade);
        else
            VisaoProfessor.getInstance().paginaProfessor(entidade);
    }


    /* Funcao que remove tudo do painel e vai para o cdastro */    
    public void vaiPLogin(ActionEvent actionEvent){
        /* remove tudo do painel */
        jpanel_cadastro.remove(label_cadatro);
        jpanel_cadastro.remove(label_nome);
        jpanel_cadastro.remove(tArea_nome);
        jpanel_cadastro.remove(label_sobrenome);
        jpanel_cadastro.remove(tArea_sobrenome);
        jpanel_cadastro.remove(label_email);
        jpanel_cadastro.remove(tArea_email);
        jpanel_cadastro.remove(label_dataNasc);
        jpanel_cadastro.remove(cbox_dia);
        jpanel_cadastro.remove(cbox_mes);
        jpanel_cadastro.remove(cbox_ano);
        jpanel_cadastro.remove(label_papel);
        jpanel_cadastro.remove(cbox_papel);
        jpanel_cadastro.remove(label_senha);
        jpanel_cadastro.remove(tArea_senha);
        jpanel_cadastro.remove(label_cSenha);
        jpanel_cadastro.remove(tArea_cSenha);
        jpanel_cadastro.remove(bt_continuar_cadastro);

        jpanel_fundo.remove(jpanel_cadastro);

        jpanel_cabecario.remove(bt_login);
        jpanel_cabecario.remove(bt_juntese);
        jpanel_cabecario.remove(projeto);

        remove(jpanel_cabecario);
        remove(jpanel_fundo);

        setVisible(false);

        /* Manda pro login */
        login(cUsuario);
    }

    /* Funcao que remove tudo do painel e vai para o cdastro */
    public void vaiPCadastro(ActionEvent actionEvent){
        /* Deixa as caixas de texto em branco */
        tArea_id.setText("");
        tArea_id.requestFocus();
        tArea_senha.setText("");
        tArea_senha.requestFocus();
        cbox_papel.setSelectedItem("");

        /* Apaga tudo do painel */
		jpanel_login.remove(bt_continuar_login);
        jpanel_login.remove(label_login);
        jpanel_login.remove(label_id);
        jpanel_login.remove(tArea_id);
        jpanel_login.remove(label_senha);
        jpanel_login.remove(tArea_senha);
        jpanel_login.remove(label_papel);
        jpanel_login.remove(cbox_papel);

        jpanel_fundo.remove(jpanel_login);

        jpanel_cabecario.remove(bt_login);
        jpanel_cabecario.remove(bt_juntese);
        jpanel_cabecario.remove(projeto);

        remove(jpanel_cabecario);
        remove(jpanel_fundo);

        setVisible(false);

        /* Manda pro cadastro */
        cadastro(cUsuario);
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
