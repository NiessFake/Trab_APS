package persistencia;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelo.Aluno;
import modelo.Entidade;
import modelo.Mensagem;

public class PersistenciaMensagem implements Persistencia, Serializable{

    private String file = "src/arquivo/arquivoMensagem.json";

    public void insere(Entidade mensagem){
       /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
       JSONParser conversorJson = new JSONParser();
       try {

           /* Chama uma função que confere se o caminho existe */
           caminhoExiste();

           /* Cria um objeto JSON que vai armazenar os dados do aluno */
           JSONObject insereObj = new JSONObject();
           insereObj.put("Id", ((Mensagem)mensagem).getId());
           insereObj.put("titulo", ((Mensagem)mensagem).getTitulo());
           insereObj.put("texto", ((Mensagem)mensagem).getTexto());
           insereObj.put("data", ((Mensagem)mensagem).getData());
           insereObj.put("dest", ((Mensagem)mensagem).getDestnatario());
           insereObj.put("remet", ((Mensagem)mensagem).getRemetente());
           /* Cria um objeto que armazena o objeto que contém todas as mensagens do sistema */
           JSONObject mensagens = (JSONObject) conversorJson.parse(new FileReader(file));

           /* Cria um vetor JSON que vai pegar o vetor contendo os usuários que já estão no arquivo,
           em seguida, adiciona o novo usuário criado */
           JSONArray vetorJSON = (JSONArray) mensagens.get("mensagem");          
           vetorJSON.add(insereObj);

           /* Cria um objeto que irá armazenar esse vetor de usuários */
           JSONObject guarda = new JSONObject();
           guarda.put("mensagem",vetorJSON);

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

    public void remove(Entidade mensagem){
        /* Variavel auxiliar */
        String aux;

        /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {
            /* Converte os elementos no arquivo para um objeto JSON*/
            JSONObject msg = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) msg.get("mensagem");
            
            /* Loop for que percorre os elementos do vetor até o seu fim */
            for (int i = 0; i < vetorJson.size() ; i++){
                /* Cria um objeto para aquele elemento que será analisado */
                JSONObject elemento = (JSONObject) vetorJson.get(i);

                /* Converte o id daquele elemento para String */
                aux = elemento.get("id").toString();

                /* Se achar o id que deseja excluir, exclui e depois  */
                if(Integer.parseInt(aux)== ((Mensagem)mensagem).getId())
                    vetorJson.remove(elemento);
            }

            /* Cria um objeto que irá armazenar esse vetor de usuários */
            JSONObject guarda = new JSONObject();
            guarda.put("aluno",vetorJson);

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
                bw.write("{\"Mensagem\": []}"); 
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

    public JSONArray buscaMensagens(String[] usuario){

        JSONArray VetMensagem = new JSONArray();
        Mensagem auxMensagem = new Mensagem();
        ArrayList<Mensagem> arrayM = new ArrayList<>();
        /* Variavel auxiliar */
        String aux;

        /* Cria um conversor de JSON para texto para que seja possível percorrer o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {
            /* Converte os elementos no arquivo para um objeto JSON*/
            JSONObject mensagem = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) mensagem.get("mensagem");
            
            /* Loop for que percorre os elementos do vetor até o seu fim */
            for (int i = 0; i < vetorJson.size() ; i++){
                /* Cria um objeto para aquele elemento que será analisado */
                JSONObject elemento = (JSONObject) vetorJson.get(i);

                /* Converte o id daquele elemento para String */
                aux = elemento.get("dest").toString();

                /* Se achar o id no banco de dados, retorna-o */
                if(usuario.equals(aux)){
                    auxMensagem.setId(Integer.parseInt(elemento.get("Id").toString()));
                    auxMensagem.setTitulo(elemento.get("titulo").toString());
                    auxMensagem.setTexto(elemento.get("texto").toString());
                    auxMensagem.setData(elemento.get("data").toString());
                    auxMensagem.setRemet(elemento.get("remet").toString()));
                    auxMensagem.setDest(elemento.get("dest").toString());
                    VetMensagem.add(elemento);
                    
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* Retorna 0 caso não encontre o id */;
        return VetMensagem;
    }

}
