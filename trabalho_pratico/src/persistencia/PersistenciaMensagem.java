package persistencia;
import java.io.BufferedWriter;
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

import modelo.*;

public class PersistenciaMensagem implements Persistencia, Serializable{

    private String file = "src/arquivo/arquivoMensagem.json";

    public void insere(Entidade entidade){
       /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
       JSONParser conversorJson = new JSONParser();
       try {

           /* Chama uma função que confere se o caminho existe */
           caminhoExiste();


            HashMap<String,Object> hashJSON = new HashMap<String,Object>();
            hashJSON.put("id", ((Mensagem)entidade).getId());
            hashJSON.put("titulo", ((Mensagem)entidade).getTitulo());
            hashJSON.put("texto", ((Mensagem)entidade).getTexto());
            hashJSON.put("data", ((Mensagem)entidade).getData());
            hashJSON.put("dest", (((Mensagem)entidade).getRemetente()).getId());
            hashJSON.put("remet", (((Mensagem)entidade).getDestnatario()).getId());
            hashJSON.put("TR", ((Mensagem)entidade).getTR());
            hashJSON.put("TD", ((Mensagem)entidade).getTD());

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
            hashGuarda.put("mensagem",vetorJSON);

            /* Cria um objeto que irá armazenar o objeto da hash */
            JSONObject guarda = new JSONObject(hashGuarda);

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

    public void remove(Entidade entidade, boolean condicao){
         /* Variavel auxiliar */
         String aux; 
 
         /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
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
                 aux = elemento.get("id").toString();
 
                 /* Se achar o id que deseja excluir, exclui e depois  */
                 if(Integer.parseInt(aux)== ((Mensagem)entidade).getId()){
            
                     vetorJson.remove(elemento);
                 }
             }
 
             /* Hash que converte o texto em um objeto */
             HashMap<String,Object> hashJSON = new HashMap<String,Object>();
             hashJSON.put("mensagem",vetorJson);
 
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
                bw.write("{\"mensagem\": []}"); 
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

    /* Funcao que busca um id no banco de dados e retorna o usuario*/
    public Mensagem buscaID(int id){
        Aluno aluno = new Aluno();
        Professor professor = new Professor();
        PersistenciaAluno pAluno = new PersistenciaAluno();
        PersistenciaProfessor pProfessor = new PersistenciaProfessor();

        Mensagem mMensagem = new Mensagem();

        /* Variavel auxiliar */
        String aux;

        /* Cria um conversor de JSON para texto para que seja possível percorrer o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {
            caminhoExiste();
            /* Converte os elementos no arquivo para um objeto JSON*/
            JSONObject noticias = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) noticias.get("noticias");
            
            /* Loop for que percorre os elementos do vetor até o seu fim */
            for (int i = 0; i < vetorJson.size() ; i++){
                /* Cria um objeto para aquele elemento que será analisado */
                JSONObject elemento = (JSONObject) vetorJson.get(i);

                /* Converte o id daquele elemento para String */
                aux = elemento.get("id").toString();

                /* Se achar o id no banco de dados, retorna-o */
                if(id == Integer.parseInt(aux)){
                    mMensagem.setTitulo(elemento.get("descricao").toString());
                    mMensagem.setData(elemento.get("titulo").toString());
                    mMensagem.setTexto(elemento.get("texto").toString());

                    if(Integer.parseInt(elemento.get("TR").toString()) == 0){
                        aluno = pAluno.buscaIDParcial(Integer.parseInt(elemento.get("remet").toString()));
                        mMensagem.setRemet(aluno);
                    }
                    else{
                        professor = pProfessor.buscaIDParcial(Integer.parseInt(elemento.get("remet").toString()));
                        mMensagem.setRemet(professor);
                    }

                    if(Integer.parseInt(elemento.get("TD").toString())  == 0){
                        aluno = pAluno.buscaIDParcial(Integer.parseInt(elemento.get("dest").toString()));
                        mMensagem.setRemet(aluno);
                    }
                    else{
                        professor = pProfessor.buscaIDParcial(Integer.parseInt(elemento.get("dest").toString()));
                        mMensagem.setRemet(professor);
                    }

                    mMensagem.setId(Integer.parseInt(elemento.get("id").toString()));
                    mMensagem.setTR(Integer.parseInt(elemento.get("TR").toString()));
                    mMensagem.setTD(Integer.parseInt(elemento.get("TD").toString()));
                    return mMensagem;
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        /* Retorna 0 caso não encontre o id */;
        return mMensagem;
    }
    
    /* Texto de mensagem para tabelas */
    public Object[][] textoMensagem(){

        /* Cria um conversor de JSON para texto para que seja possível percorrer o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {
            caminhoExiste();
            
            /* Converte os elementos no arquivo para um objeto JSON*/
            JSONObject mensagem = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) mensagem.get("mensagem");

            /* Cria um objeto que vai guardar os dados dos elementos no JSON */
            Object[][] objeto = new Object[vetorJson.size()][2];

            /* Loop for que percorre os elementos do vetor até o seu fim */
            for (int i = 0; i < vetorJson.size() ; i++){
                /* Cria um objeto para aquele elemento que será analisado */
                JSONObject elemento = (JSONObject) vetorJson.get(i);

                objeto[i][0] = elemento.get("dest").toString();
                objeto[i][1] = elemento.get("remet").toString();
            }
            
            return objeto;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        /* Retorna o maior id */
        return null;
    }


    /* Função que percorre os elementos de um arquivo e devolve o maior ID entre eles */
    public int devolveMaiorID(){
        int maior = 0;
        String aux;

        /* Cria um conversor de JSON para texto para que seja possível percorrer o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {
            caminhoExiste();
            /* Converte os elementos no arquivo para um objeto JSON*/
            JSONObject mensagem = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) mensagem.get("mensagem");
            
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
            e.printStackTrace();
        }

        /* Retorna o maior id */
        return maior;
    }

}
