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
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelo.Aluno;
import modelo.Entidade;
import modelo.Mensagem;
import modelo.Usuario;

public class PersistenciaMensagem implements Persistencia, Serializable{

    private String file = "src/arquivo/arquivoMensagem.json";

    public void insere(Entidade entidade){
       /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
       JSONParser conversorJson = new JSONParser();
       try {

           /* Chama uma função que confere se o caminho existe */
           caminhoExiste();


           HashMap<String,Object> hashJSON = new HashMap<String,Object>();
            hashJSON.put("Id", ((Mensagem)entidade).getId());
            hashJSON.put("titulo", ((Mensagem)entidade).getTitulo());
            hashJSON.put("texto", ((Mensagem)entidade).getTexto());
            hashJSON.put("data", ((Mensagem)entidade).getData());
            hashJSON.put("dest", ((Mensagem)entidade).getRemetente());
            hashJSON.put("remet", ((Mensagem)entidade).getDestnatario());

            /* Cria um objeto JSON que vai armazenar o objeto Hash */
            JSONObject insereObj = new JSONObject(hashJSON);
    
           /* Cria um objeto que armazena o objeto que contém todas as mensagens do sistema */
           JSONObject mensagens = (JSONObject) conversorJson.parse(new FileReader(file));

           /* Cria um vetor JSON que vai pegar o vetor contendo os usuários que já estão no arquivo,
           em seguida, adiciona o novo usuário criado */
           JSONArray vetorJSON = (JSONArray) mensagens.get("mensagem");          
           vetorJSON.add(insereObj);

           /* Armazena numa objeto da hash a string */
           HashMap<String,Object> hashGuarda = new HashMap<String,Object>();
           hashGuarda.put("aluno",vetorJSON);

           /* Cria um objeto que irá armazenar o objeto da hash */
           JSONObject guarda = new JSONObject(hashGuarda);

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

    public void remove(Entidade entidade, boolean condicao){
         /* Variavel auxiliar */
         String aux;
         Aluno[] vetor_aux;
 
 
         /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
         JSONParser conversorJson = new JSONParser();
         try {
             /* Converte os elementos no arquivo para um objeto JSON*/
             JSONObject mensagem = (JSONObject) conversorJson.parse(new FileReader(file));
             
             /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
             JSONArray vetorJson = (JSONArray) mensagem.get("mensagem");
             
             JSONArray vetorJSONAux;
 
             /* Loop for que percorre os elementos do vetor até o seu fim */
             for (int i = 0; i < vetorJson.size() ; i++){
                 /* Cria um objeto para aquele elemento que será analisado */
                 JSONObject elemento = (JSONObject) vetorJson.get(i);
 
                 /* Converte o id daquele elemento para String */
                 aux = elemento.get("Id").toString();
 
                 /* Se achar o id que deseja excluir, exclui e depois  */
                 if(Integer.parseInt(aux)== ((Mensagem)entidade).getId()){
            
                     vetorJson.remove(elemento);
                 }
             }
 
             /* Hash que converte o texto em um objeto */
             HashMap<String,Object> hashJSON = new HashMap<String,Object>();
             hashJSON.put("aluno",vetorJson);
 
             /* Cria um objeto JSON que irá armazenar o objeto gerado pela hash */
             JSONObject guarda = new JSONObject(hashJSON);
 
             /* Chama a função que escreve no arquivo */
             escreveArquivo(guarda);
 
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (org.json.simple.parser.ParseException e) {
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

    public ArrayList<Mensagem>  buscaMensagens(String usuario){

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

                /* se o usuario é o destinatario salvar mensagem*/
                if(usuario.equals(aux)){
                    auxMensagem.setId(Integer.parseInt(elemento.get("Id").toString()));
                    auxMensagem.setTitulo(elemento.get("titulo").toString());
                    auxMensagem.setTexto(elemento.get("texto").toString());
                    auxMensagem.setData(elemento.get("data").toString());
                    auxMensagem.setRemet((Usuario)elemento.get("remet"));
                    auxMensagem.setDest((Usuario)elemento.get("dest"));
                    //VetMensagem.add(elemento);
                    arrayM.add(auxMensagem);
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
        return arrayM;
    }

}
