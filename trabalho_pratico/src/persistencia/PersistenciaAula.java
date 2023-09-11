package persistencia;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelo.*;

public class PersistenciaAula implements Persistencia{
    /* Caminho para salvar no arquivo */
    private String file = "src/arquivo/arquivoAula.json";

    String[] dias = new String[7];


    public void insere(Entidade entidade){

        /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {

            /* Chama uma função que confere se o caminho existe */
            caminhoExiste();

            /* Atribui a variavel local, dias, a String dos dias da semana. Em seguida cria um vetor e 
             * para cada elemento nao vazio dessa string, adiciona no vetor */
            dias = ((Aula)entidade).getDias();
            JSONArray diasArray = new JSONArray();

            for(int i = 0; i < 7 ;i++){
                if(!(dias[i].equals("")))
                    diasArray.add(dias[i]);
            }

            /* Cria um objeto JSON que vai armazenar os dados da aula */
            JSONObject insereObj = new JSONObject();
            insereObj.put("id",devolveMaiorID()+1);
            insereObj.put("materia",((Aula)entidade).getMateria());
            insereObj.put("capacidade", ((Aula)entidade).getCapacidade());
            insereObj.put("alunos", ((Aula)entidade).getIdAlunos());
            insereObj.put("professor",((Aula)entidade).getIdProfessor());
            insereObj.put("duracao", ((Aula)entidade).getDuracao());
            insereObj.put("frequencia", ((Aula)entidade).getFrequencia());
            insereObj.put("dias", diasArray);

            /* Cria um objeto que armazena o objeto que contém todos os usuários do sistema */
            JSONObject aula = (JSONObject) conversorJson.parse(new FileReader(file));

            /* Cria um vetor JSON que vai pegar o vetor contendo os usuários que já estão no arquivo,
            em seguida, adiciona o novo usuário criado */
            JSONArray vetorJSON = (JSONArray) aula.get("aula");          
            vetorJSON.add(insereObj);

            /* Cria um objeto que irá armazenar esse vetor de usuários */
            JSONObject guarda = new JSONObject();
            guarda.put("aula",vetorJSON);

            /* Chama a função que escreve no arquivo */
            escreveArquivo(guarda);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void remove(Entidade entidade){

    }

    /* Função que confere a existência de um caminho para a leitura do arquivo */
    public void caminhoExiste(){
        /* Variaveis que auxiliam na verificação do caminho*/
        Path caminho = Paths.get(file);
        boolean naoExiste = Files.notExists(caminho);

        /* Confere se o caminho existe e, caso não exista, cria um */
        if(naoExiste){
            try{
                BufferedWriter bw = new BufferedWriter(
                    new FileWriter(file));    
                bw.write("{\"aula\": []}"); 
                bw.close();
            }
            catch(Exception ex){
                System.out.println("\nNao foi possivel escrever no arquivo\n");
                return; 
            }
        }
    }


    /* Função para escrever no arquivo  */
    public void escreveArquivo(JSONObject escreve){
        try{
                BufferedWriter bw = new BufferedWriter(
                    new FileWriter(file));    
                bw.write(escreve.toString()); 
                bw.close();
            }
            catch(Exception ex){
                return; 
            }
    }

    /* Função que percorre os elementos de um arquivo e devolve o maior ID entre eles */
    public int devolveMaiorID(){
        int maior = 0;
        String aux;

        /* Cria um conversor de JSON para texto para que seja possível percorrer o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {
            /* Converte os elementos no arquivo para um objeto JSON*/
            JSONObject aula = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) aula.get("aula");
            
            /* Loop for que percorre os elementos do vetor até o seu fim */
            for (int i = 0; i < vetorJson.size() ; i++){
                /* Cria um objeto para aquele elemento que será analisado */
                JSONObject elemento = (JSONObject) vetorJson.get(i);

                /* Converte o id daquele elemento para String */
                aux = elemento.get("id").toString();

                /* Se o id que deste elemento for maior que o até então maior id,
                 * este passa a ser o novo maior id */
                if(Integer.parseInt(aux) > maior)
                    maior = Integer.parseInt(aux);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* Retorna o maior id */
        return maior;
    }
}
