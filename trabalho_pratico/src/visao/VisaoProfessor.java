package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import controle.*;
import modelo.*;
import persistencia.*;

public class VisaoProfessor extends JFrame {
    /* Atributo que vai guardar a única instância da interface */
    private static VisaoProfessor uniqueInstance;

    Aula aula;
    Professor professor = new Professor();
    PersistenciaAula pAula = new PersistenciaAula();
    PersistenciaProfessor pProfessor = new PersistenciaProfessor();

    /* Variaveis auxiliares */
    String nome, sobrenome, email, dia, mes, ano , senha, cSenha, materia, capacidade, duracao;
    String[] dias = new String[7];
    int[] aulas_ministradas;
    int tamanho_vetor_aula;
    boolean condicao_alteracao, condicao_cadastro, frequencia;

    /* Paineis */
    JPanel jpanel_cabecario = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_aulas = new JPanel();
    JPanel jpanel_dados = new JPanel();
    JPanel jpanel_cAulas = new JPanel();

    /* Botões */
    JButton bt_aulas = new JButton("AULAS");
    JButton bt_mensagens = new JButton("MENSAGENS");
    JButton bt_professor = new JButton("");
    JButton bt_alterar = new JButton("ALTERAR DADOS");
    JButton bt_excluir = new JButton("EXCLUIR");
    JButton bt_confirmar = new JButton("CONFIRMAR");
    JButton bt_criar_aula = new JButton("CRIAR AULA");
    JButton bt_continuar_cadastro = new JButton("CRIAR AULA");

    /* Labels */
    JLabel projeto = new JLabel("PROJETO");
    JLabel label_alterar = new JLabel("ALTERAR DADOS");
    JLabel label_dados = new JLabel("DADOS PESSOAIS ");
    JLabel label_criar_aula = new JLabel("CRIAR NOVA AULA");
    JLabel label_nome = new JLabel();
    JLabel label_sobrenome = new JLabel("SOBRENOME: ");
    JLabel label_email = new JLabel();
    JLabel label_dataNasc = new JLabel();
    JLabel label_senha = new JLabel("SENHA:");
    JLabel label_cSenha = new JLabel("CONFIRMAR SENHA: ");
    JLabel label_materia = new JLabel("MATERIA: ");
    JLabel label_capacidade = new JLabel("CAPACIDADE: ");
    JLabel label_duracao = new JLabel("DURACAO: ");
    JLabel label_frequencia = new JLabel("FREQUENTE: ");
    JLabel label_dias = new JLabel("DIAS DA SEMANA: ");
    JLabel label_minutos = new JLabel("minutos: ");

    /* TextAreas */
    JTextArea tArea_nome = new JTextArea();
    JTextArea tArea_sobrenome = new JTextArea();
    JTextArea tArea_email = new JTextArea();
    JTextArea tArea_senha = new JTextArea();
    JTextArea tArea_cSenha = new JTextArea();
    JTextArea tArea_materia = new JTextArea();
    JTextArea tArea_capacidade = new JTextArea();
    JTextArea tArea_duracao = new JTextArea();

    /*ComboBoxes */
    JComboBox cbox_dia = new JComboBox<>(preencheVetor(32, 1, true));
    JComboBox cbox_mes = new JComboBox<>(preencheVetor(13, 1, true));
    JComboBox cbox_ano = new JComboBox<>(preencheVetor(151, 1874, false));
    
    /* CheckBoxes */
    JCheckBox check_frequencia = new JCheckBox("SIM");
    JCheckBox check_segunda = new JCheckBox("SEG");
    JCheckBox check_terca = new JCheckBox("TER");
    JCheckBox check_quarta = new JCheckBox("QUA");
    JCheckBox check_quinta = new JCheckBox("QUI");
    JCheckBox check_sexta = new JCheckBox("SEX");
    JCheckBox check_sabado = new JCheckBox("SAB");
    JCheckBox check_domingo = new JCheckBox("DOM");

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,20);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecario = new Color(0,204,155);
    Color cor_textos = new Color(163, 184, 204);

    /* Contrução do JFrame que será usado */
    public VisaoProfessor(){
        setSize(750,600);
        setTitle("TP Analise e Projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /* Cria uma instancia única para essa interface (Padrao Singleton) */
    public static VisaoProfessor getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoProfessor();
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

        bt_professor.setFont(texto_padrao);
        bt_professor.setBounds(0,0,125,40);
		bt_professor.setLocation(575, 30);
        bt_professor.setBackground(Color.white);
		bt_professor.setForeground(Color.black);

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
        jpanel_cabecario.add(bt_professor);
        jpanel_cabecario.add(projeto);
        
        add(jpanel_cabecario);
        add(jpanel_fundo);
    }


    /* Pagina do professor que leva para seus dados */
    public void paginaProfessor(Entidade entidade){
        setVisible(true);
        
        professor = (Professor)entidade;

        cabecario();

        /* Nomeia o botao como o nome do professor cadastrado */
        //bt_professor.setText(((professor)entidade).getNome());
        bt_professor.setText(professor.getNome());

        /* Botões */
        bt_alterar.setFont(texto_padrao);
        bt_alterar.setBounds(0,0,175,40);
        bt_alterar.setLocation(50, 335);
        bt_alterar.setBackground(Color.white);
        bt_alterar.setForeground(Color.black);
        bt_alterar.addActionListener(this::alterar);

        bt_criar_aula.setFont(texto_padrao);
        bt_criar_aula.setBounds(0,0,200,40);
        bt_criar_aula.setLocation(375, 300);
        bt_criar_aula.setBackground(Color.white);
        bt_criar_aula.setForeground(Color.black);
        bt_criar_aula.addActionListener(this::criarAula);

        /* Labels */
        label_dados.setFont(texto_padrao);
        label_dados.setBounds(0,0,300,40);
        label_dados.setLocation(15, 150);

        label_nome.setText("NOME: " + professor.getNome() + " " +professor.getSobrenome());
        label_nome.setFont(texto_padrao);
        label_nome.setBounds(0,0,300,40);
        label_nome.setLocation(25, 200);

        label_email.setText("EMAIL: " + professor.getEmail());
        label_email.setFont(texto_padrao);
        label_email.setBounds(0,0,300,40);
        label_email.setLocation(25, 235);

        label_dataNasc.setText("DATA DE NASCIMENTO: " + professor.getDiaNasc() + "/" + professor.getMesNasc() + "/" + professor.getAnoNasc());
        label_dataNasc.setFont(texto_padrao);
        label_dataNasc.setBounds(0,0,420,40);
        label_dataNasc.setLocation(25, 270);

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
        jpanel_dados.add(bt_alterar);

        jpanel_fundo.add(bt_criar_aula);
        jpanel_fundo.add(jpanel_dados);
    }


    /*Acoes dos botoes */
    /* Acao do botao alterar */
    private void alterar(ActionEvent actionEvent){
        setVisible(false);

        /* Botoes */
        bt_excluir.setFont(new Font("ARIAL",Font.BOLD,11));
        bt_excluir.setBounds(0,0,125,40);
		bt_excluir.setLocation(122, 375);
        bt_excluir.setBackground(Color.white);
		bt_excluir.setForeground(Color.black);
        bt_excluir.addActionListener(this::excluir);

        bt_confirmar.setBounds(0,0,125,40);
		bt_confirmar.setLocation(253, 375);
        bt_confirmar.setBackground(Color.white);
		bt_confirmar.setForeground(Color.black);
        bt_confirmar.addActionListener(this::confirmar);

        /* Labels */        
        label_alterar.setFont(texto_sub_titulo);
        label_alterar.setBounds(0,0,200,50);
		label_alterar.setLocation(150, 25);

        label_nome.setText("NOME: ");
        label_nome.setFont(texto_padrao);
        label_nome.setBounds(0,0,175,50);
		label_nome.setLocation(37, 110);

        label_sobrenome.setFont(texto_padrao);
        label_sobrenome.setBounds(0,0,175,50);
		label_sobrenome.setLocation(37, 145);

        label_email.setText("EMAIL: ");
        label_email.setFont(texto_padrao);
        label_email.setBounds(0,0,175,50);
		label_email.setLocation(37, 180);

        label_dataNasc.setText("DATA DE NASCIMENTO: ");
        label_dataNasc.setFont(texto_padrao);
        label_dataNasc.setBounds(0,0,175,50);
		label_dataNasc.setLocation(37, 215);

        label_senha.setFont(texto_padrao);
        label_senha.setBounds(0,0,175,50);
		label_senha.setLocation(37, 250);

        label_cSenha.setFont(texto_padrao);
        label_cSenha.setBounds(0,0,175,50);
		label_cSenha.setLocation(37, 285);

        /* Caixas de texto */
        tArea_nome.setFont(texto_padrao);
        tArea_nome.setBounds(0,0,250,25);
		tArea_nome.setLocation(218, 125);
        tArea_nome.setBackground(cor_textos);

        tArea_sobrenome.setFont(texto_padrao);
        tArea_sobrenome.setBounds(0,0,250,25);
		tArea_sobrenome.setLocation(218, 160);
        tArea_sobrenome.setBackground(cor_textos);

        tArea_email.setFont(texto_padrao);
        tArea_email.setBounds(0,0,250,25);
		tArea_email.setLocation(218, 195);
        tArea_email.setBackground(cor_textos);

        tArea_senha.setFont(texto_padrao);
        tArea_senha.setBounds(0,0,250,25);
		tArea_senha.setLocation(218, 265);
        tArea_senha.setBackground(cor_textos);

        tArea_cSenha.setFont(texto_padrao);
        tArea_cSenha.setBounds(0,0,250,25);
		tArea_cSenha.setLocation(218, 300);
        tArea_cSenha.setBackground(cor_textos);

        /* ComboBOXes */
        cbox_dia.setBounds(0,0,80,25);
		cbox_dia.setLocation(218, 230);

        cbox_mes.setBounds(0,0,80,25);
		cbox_mes.setLocation(303, 230);

        cbox_ano.setBounds(0,0,80,25);
		cbox_ano.setLocation(388, 230);

        /* Paineis */
        jpanel_dados.setSize(500, 500);
        jpanel_dados.setLocation(125, 100);

        /* Remove e adiciona elementos no painel */
        jpanel_fundo.remove(bt_criar_aula);

        jpanel_dados.remove(bt_alterar);
        jpanel_dados.remove(label_dados);
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
        /* Chama a funcao da persistencia que exclui o usuario */
        pProfessor.remove(professor);

        /* Imprime uma mensagem de sucesso */
        JOptionPane.showMessageDialog(null,"Seu cadastrato foi excluido", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
        
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

        /* Remove tudo do cabecario */
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

        jpanel_fundo.remove(bt_criar_aula);
        jpanel_fundo.remove(jpanel_dados);

        remove(jpanel_fundo);
        remove(jpanel_cabecario);

        /* Deixa essa tela invisivel para o usuario */
        setVisible(false);

        /* Leva ate o menu principal */
        VisaoMain.getInstance().menu();
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

        condicao_alteracao = nome.equals("") && sobrenome.equals("") && email.equals("") && dia.equals("") && mes.equals("") && ano.equals("") &&  senha.equals("")&& cSenha.equals("");

        /* Se todas campos estiverem vazios nao tem como alterar, entao apenas na condicao de
         * pelo menos um nao estiver vazio, ocorre a alteracao */
        if(!condicao_alteracao){
            /* Confere se as senhas digitas sao iguais, caso nao sejam, imprime uma mensagem de erro */
            if(!(senha.equals(cSenha)))
                JOptionPane.showMessageDialog(null,"Senhas divergem", "ERRO",JOptionPane.ERROR_MESSAGE);                
            else{
                /* Salva apenas aquilo que foi escrito pelo usuario */
                if(!(nome.equals("")))
                    professor.setNome(nome);
                if(!(sobrenome.equals("")))
                    professor.setSobrenome(sobrenome);
                if(!(email.equals("")))
                    professor.setEmail(email);
                if(!(dia.equals("")))
                    professor.setDiaNasc(Integer.parseInt(dia));
                if(!(mes.equals("")))
                    professor.setMesNasc(Integer.parseInt(mes));
                if(!(ano.equals("")))
                    professor.setAnoNasc(Integer.parseInt(ano));
                if(!(senha.equals("")))
                    professor.setSenha(senha);
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
            tArea_senha.setText("");
            tArea_senha.requestFocus();
            tArea_cSenha.setText("");
            tArea_cSenha.requestFocus();

            /* Exclui o cadastro antigo e adiciona o novo */
            pProfessor.remove(professor);
            pProfessor.insere(professor);

            JOptionPane.showMessageDialog(null,"Seu cadastrato foi alterado", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
            
        }

        /* Remove tudo do cabecario */
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

        jpanel_fundo.remove(bt_criar_aula);
        jpanel_fundo.remove(jpanel_dados);

        remove(jpanel_fundo);
        remove(jpanel_cabecario);

        setVisible(false);
        paginaProfessor(professor);
    }


    /* Acoes do botao criar aula */
    private void criarAula(ActionEvent actionEvent){
        setVisible(false);
        /* Exclui os elementos antigos */
        jpanel_dados.remove(label_dados);
        jpanel_dados.remove(label_nome);
        jpanel_dados.remove(label_email);
        jpanel_dados.remove(label_dataNasc);
        jpanel_dados.remove(bt_alterar);

        jpanel_fundo.remove(jpanel_dados);
        jpanel_fundo.remove(bt_criar_aula);

        /* Botões */
        bt_continuar_cadastro.setFont(texto_padrao);
        bt_continuar_cadastro.setBounds(0,0,125,40);
		bt_continuar_cadastro.setLocation(187, 385);
        bt_continuar_cadastro.setBackground(Color.white);
		bt_continuar_cadastro.setForeground(Color.black);
        bt_continuar_cadastro.addActionListener(this::continuarCadastro);

        /* Labels */
        label_criar_aula.setFont(texto_sub_titulo);
        label_criar_aula.setBounds(0,0,225,50);
		label_criar_aula.setLocation(112, 25);

        label_materia.setFont(texto_padrao);
        label_materia.setBounds(0,0,125,50);
		label_materia.setLocation(60, 140);

        label_capacidade.setFont(texto_padrao);
        label_capacidade.setBounds(0,0,125,50);
		label_capacidade.setLocation(60, 175);

        label_frequencia.setFont(texto_padrao);
        label_frequencia.setBounds(0,0,125,50);
		label_frequencia.setLocation(60, 210);

        label_duracao.setFont(texto_padrao);
        label_duracao.setBounds(0,0,125,50);
		label_duracao.setLocation(60, 245);

        label_dias.setFont(texto_padrao);
        label_dias.setBounds(0,0,125,50);
		label_dias.setLocation(60, 280);

        label_minutos.setFont(texto_padrao);
        label_minutos.setBounds(0,0,100,50);
		label_minutos.setLocation(295, 245);


        /* Caixas de texto */
        tArea_materia.setFont(texto_padrao);
        tArea_materia.setBounds(0,0,250,25);
		tArea_materia.setLocation(190, 150);
        tArea_materia.setBackground(cor_textos);

        tArea_capacidade.setFont(texto_padrao);
        tArea_capacidade.setBounds(0,0,250,25);
		tArea_capacidade.setLocation(190, 185);
        tArea_capacidade.setBackground(cor_textos);

        tArea_duracao.setFont(texto_padrao);
        tArea_duracao.setBounds(0,0,100,25);
		tArea_duracao.setLocation(190, 255);
        tArea_duracao.setBackground(cor_textos);

        /* CheckBOXes */
        check_frequencia.setBounds(0, 0, 100, 25);
        check_frequencia.setLocation(190, 220);

        check_segunda.setBounds(0, 0, 60, 25);
        check_segunda.setLocation(190, 290);

        check_terca.setBounds(0, 0, 60, 25);
        check_terca.setLocation(255, 290);

        check_quarta.setBounds(0, 0, 60, 25);
        check_quarta.setLocation(320, 290);

        check_quinta.setBounds(0, 0, 60, 25);
        check_quinta.setLocation(385, 290);

        check_sexta.setBounds(0, 0, 60, 25);
        check_sexta.setLocation(190, 315);

        check_sabado.setBounds(0, 0, 60, 25);
        check_sabado.setLocation(255, 315);
        
        check_domingo.setBounds(0, 0, 60, 25);
        check_domingo.setLocation(320, 315);

        /* Painel */   
        jpanel_cAulas.setLayout(null);
        jpanel_cAulas.setBackground(Color.WHITE);
        jpanel_cAulas.setSize(500, 500);
        jpanel_cAulas.setLocation(125, 100);
        jpanel_cAulas.setVisible(true);

        /* Adiciona elementos no painel */
        jpanel_cAulas.add(label_criar_aula);
        jpanel_cAulas.add(label_materia);
        jpanel_cAulas.add(label_capacidade);
        jpanel_cAulas.add(label_frequencia);
        jpanel_cAulas.add(label_duracao);
        jpanel_cAulas.add(label_dias);
        jpanel_cAulas.add(label_minutos);
        jpanel_cAulas.add(tArea_materia);
        jpanel_cAulas.add(tArea_capacidade);
        jpanel_cAulas.add(tArea_duracao);
        jpanel_cAulas.add(check_frequencia);
        jpanel_cAulas.add(check_segunda);
        jpanel_cAulas.add(check_terca);
        jpanel_cAulas.add(check_quarta);
        jpanel_cAulas.add(check_quinta);
        jpanel_cAulas.add(check_sexta);
        jpanel_cAulas.add(check_sabado);
        jpanel_cAulas.add(check_domingo);
        jpanel_cAulas.add(bt_continuar_cadastro);

        jpanel_fundo.add(jpanel_cAulas);

        setVisible(true);
    }

    private void continuarCadastro(ActionEvent actionEvent){
        /* Atribuicao  dos valores no texto para salvar nas variaveis locais*/
        materia = tArea_materia.getText();
        capacidade = tArea_capacidade.getText();
        duracao =  tArea_duracao.getText();

        /* Expressão para saber se ao menos um dia foi selecionado */
        condicao_cadastro = check_segunda.isSelected() || check_terca.isSelected() || check_quarta.isSelected() 
        || check_quinta.isSelected() || check_sexta.isSelected() || check_sabado.isSelected() || check_domingo.isSelected();

        /* Se nenhum dia foi selecionado exibe uma mensagem de erro */
        if(!condicao_cadastro)
            JOptionPane.showMessageDialog(null,"Nenhum dia selecionado", "ERRO",JOptionPane.ERROR_MESSAGE);
        else{
            /* Se uma das caixas de texto estiver em branco, exibe mensagem de erro */
            if(materia.equals("") && capacidade.equals("") && duracao.equals(""))
                JOptionPane.showMessageDialog(null,"Informação faltando", "ERRO",JOptionPane.ERROR_MESSAGE);
            else{
                /* Atribui um valor pra frequencia de acordo com o item selecionado */
                if(check_frequencia.isSelected())
                    frequencia = true;
                else
                    frequencia = false;

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

                aula = new Aula(0, materia, Integer.parseInt(capacidade), null, professor.getId(), Integer.parseInt(duracao), frequencia, dias);

                /* Insere a aula no sistema */
                pAula.insere(aula);

                if(professor.getIdAulaMinistradas() == null){
                    aulas_ministradas = new int[1];
                    aulas_ministradas[0] = pAula.devolveMaiorID();
                }
                else{
                    tamanho_vetor_aula = professor.getIdAulaMinistradas().length;
                    aulas_ministradas = new int[tamanho_vetor_aula+1];
                    aulas_ministradas[tamanho_vetor_aula] = pAula.devolveMaiorID();
                }

                professor.setIdAulaMinistradas(aulas_ministradas);

                pProfessor.remove(professor);
                pProfessor.insere(professor);

                /* Uma mensagem de sucesso aparece e apresenta o id do usuário */
                JOptionPane.showMessageDialog(null,"Parabéns, sua aula foi cadastrada com sucesso.", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                
        
                /* Deixa as caixas de texto em branco */
                tArea_materia.setText("");
                tArea_materia.requestFocus();
                tArea_capacidade.setText("");
                tArea_capacidade.requestFocus();
                tArea_duracao.setText("");
                tArea_duracao.requestFocus();
                check_frequencia.setSelected(false);
                check_segunda.setSelected(false);
                check_terca.setSelected(false);
                check_quarta.setSelected(false);
                check_quinta.setSelected(false);
                check_sexta.setSelected(false);
                check_sabado.setSelected(false);
                check_domingo.setSelected(false);

                /* Chama a funcao que redireciona para a pagina do professor */
                vaiPPaginaProfessor();
            }
        }
    }

    public void vaiPPaginaProfessor(){
        setVisible(false);

        jpanel_cAulas.remove(label_criar_aula);
        jpanel_cAulas.remove(label_materia);
        jpanel_cAulas.remove(label_capacidade);
        jpanel_cAulas.remove(label_frequencia);
        jpanel_cAulas.remove(label_duracao);
        jpanel_cAulas.remove(label_dias);
        jpanel_cAulas.remove(label_minutos);
        jpanel_cAulas.remove(tArea_materia);
        jpanel_cAulas.remove(tArea_capacidade);
        jpanel_cAulas.remove(tArea_duracao);
        jpanel_cAulas.remove(check_frequencia);
        jpanel_cAulas.remove(check_segunda);
        jpanel_cAulas.remove(check_terca);
        jpanel_cAulas.remove(check_quarta);
        jpanel_cAulas.remove(check_quinta);
        jpanel_cAulas.remove(check_sexta);
        jpanel_cAulas.remove(check_sabado);
        jpanel_cAulas.remove(check_domingo);
        jpanel_cAulas.remove(bt_continuar_cadastro);

        jpanel_fundo.remove(jpanel_cAulas);

        remove(jpanel_fundo);
        remove(jpanel_cabecario);
        
        paginaProfessor(professor);
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
