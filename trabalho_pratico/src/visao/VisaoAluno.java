package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
//import controle.*;
import modelo.*;
import persistencia.*;

public class VisaoAluno extends JFrame{
    /* Atributo que vai guardar a única instância da interface */
    private static VisaoAluno uniqueInstance;

    Aluno aluno = new Aluno();
    PersistenciaAluno pAluno = new PersistenciaAluno();

    String nome, sobrenome, email, dia, mes, ano , senha, cSenha;
    boolean condicao;

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

    /*ComboBox */
    JComboBox cbox_dia = new JComboBox<>(preencheVetor(32, 1, true));
    JComboBox cbox_mes = new JComboBox<>(preencheVetor(13, 1, true));
    JComboBox cbox_ano = new JComboBox<>(preencheVetor(151, 1874, false));

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,20);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecario = new Color(0,204,155);
    Color cor_textos = new Color(163, 184, 204);
    
    /* Contrução do JFrame que será usado */
    public VisaoAluno(){
        setSize(750,600);
        setTitle("TP Analise e Projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /* Cria uma instancia única para essa interface (Padrao Singleton) */
    public static VisaoAluno getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoAluno();
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


    /* Pagina do aluno que leva para seus dados */
    public void paginaAluno(Entidade entidade){
        setVisible(true);
        
        aluno = (Aluno)entidade;

        cabecario();

        /* Nomeia o botao como o nome do aluno cadastrado */
        bt_aluno.setText(aluno.getNome());

        /* Botões */
        bt_alterar.setFont(new Font("ARIAL",Font.BOLD,11));
        bt_alterar.setBounds(0,0,175,40);
		bt_alterar.setLocation(50, 335);
        bt_alterar.setBackground(Color.white);
		bt_alterar.setForeground(Color.black);
        bt_alterar.addActionListener(this::alterar);

        bt_entrar_aula.setFont(texto_padrao);
        bt_entrar_aula.setBounds(0,0,200,40);
        bt_entrar_aula.setLocation(375, 300);
        bt_entrar_aula.setBackground(Color.white);
        bt_entrar_aula.setForeground(Color.black);
        bt_entrar_aula.addActionListener(this::entrarAula);

        /* Labels */
        label_dados.setFont(texto_padrao);
        label_dados.setBounds(0,0,300,40);
		label_dados.setLocation(15, 160);

        label_nome.setText("NOME: " + aluno.getNome() + " " +aluno.getSobrenome());
        label_nome.setFont(texto_padrao);
        label_nome.setBounds(0,0,300,40);
		label_nome.setLocation(25, 200);

        label_email.setText("EMAIL: " + aluno.getEmail());
        label_email.setFont(texto_padrao);
        label_email.setBounds(0,0,300,40);
		label_email.setLocation(25, 235);

        label_dataNasc.setText("DATA DE NASCIMENTO: " + aluno.getDiaNasc() + "/" + aluno.getMesNasc() + "/" + aluno.getAnoNasc());
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

        jpanel_fundo.add(bt_entrar_aula);
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
        pAluno.remove(aluno);

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
        
        jpanel_fundo.remove(jpanel_dados);

        remove(jpanel_fundo);
        remove(jpanel_cabecario);


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
            pAluno.remove(aluno);
            pAluno.insere(aluno);

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
        
        jpanel_fundo.remove(jpanel_dados);

        remove(jpanel_fundo);
        remove(jpanel_cabecario);

        setVisible(false);
        paginaAluno(aluno);
    }

    private void entrarAula(ActionEvent actionEvent){
        int aux [] = {1,9};
        aluno.setIdAulaInscritas(aux);
        pAluno.remove(aluno);
        pAluno.insere(aluno);
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
