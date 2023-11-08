package visao;

import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Tarefa;

public class VisaoTarefa extends JFrame {
    
    private JTable table;


    public void criarTarefa(){

    }

    public void verTarefas(){

        String[] colunas = {"Título", "Data de Postagem", "Descrição", "Prazo", "Aula Relacionada", "Campo de Entrega"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Tarefa tarefa : tarefas) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String[] rowData = {
                    tarefa.getTitulo(),
                    dateFormat.format(tarefa.getDia() + tarefa.getMes() + tarefa.getAno()),
                    tarefa.getDescricao(),
                    dateFormat.format(tarefa.getPrazo()),
                    tarefa.getAula().toString(),
                    tarefa.getEntrega()
            };
            model.addRow(rowData);
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        this.pack();

    }
   
}

