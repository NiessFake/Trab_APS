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

import modelo.Entidade;
import modelo.Professor;

public class PersistenciaProfessor implements Persistencia{
    /* Caminho para salvar no arquivo */
    private String file = "src/arquivo/arquivoProfessor.json";

    /* Variaveis auxiliares */
    int vetor_aulas[];

    /* Função que insere um usuário no arquivo */
    public void insere(Entidade entidade){

        /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {

            /* Chama uma função que confere se o caminho existe */
            caminhoExiste();

            /* Cria um objeto JSON que vai armazenar os dados do professor */
            JSONObject insereObj = new JSONObject();
            insereObj.put("nome", ((Professor)entidade).getNome());
            insereObj.put("sobrenome", ((Professor)entidade).getSobrenome());
            insereObj.put("email", ((Professor)entidade).getEmail());
            insereObj.put("diaNasc", ((Professor)entidade).getDiaNasc());
            insereObj.put("mesNasc", ((Professor)entidade).getMesNasc());
            insereObj.put("anoNasc", ((Professor)entidade).getAnoNasc());
            insereObj.put("id", devolveMaiorID()+1);
            insereObj.put("senha", ((Professor)entidade).getSenha());

            /* Se existir alguma aula no professor, cria um vetor JSON q armazena elas e depois 
             * coloca no objeto. Em caso negativo salva um vetor vazio */
            if(((Professor) entidade).getIdAulaMinistradas() != null){
                vetor_aulas = ((Professor) entidade).getIdAulaMinistradas();
                JSONArray vetorAulas = new JSONArray();

                for(int j = 0; j < vetor_aulas.length ;j++)
                    vetorAulas.add(vetor_aulas[j]);

                insereObj.put("aulas", vetorAulas);
            }
            else
                insereObj.put("aulas", null);


            /* Cria um objeto que armazena o objeto que contém todos os usuários do sistema */
            JSONObject professor = (JSONObject) conversorJson.parse(new FileReader(file));

            /* Cria um vetor JSON que vai pegar o vetor contendo os usuários que já estão no arquivo,
            em seguida, adiciona o novo usuário criado */
            JSONArray vetorJSON = (JSONArray) professor.get("professor");          
            vetorJSON.add(insereObj);

            /* Cria um objeto que irá armazenar esse vetor de usuários */
            JSONObject guarda = new JSONObject();
            guarda.put("professor",vetorJSON);

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

    /* Funcao que remove um usuario do sistema*/
    public void remove(Entidade entidade){
        /* Variavel auxiliar */
            String aux;

        /* Cria um conversor de JSON para texto para que seja possível escrever o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {
            /* Converte os elementos no arquivo para um objeto JSON*/
            JSONObject professor = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) professor.get("professor");
            
            /* Loop for que percorre os elementos do vetor até o seu fim */
            for (int i = 0; i < vetorJson.size() ; i++){
                /* Cria um objeto para aquele elemento que será analisado */
                JSONObject elemento = (JSONObject) vetorJson.get(i);

                /* Converte o id daquele elemento para String */
                aux = elemento.get("id").toString();

                /* Se achar o id que deseja excluir, exclui e depois  */
                if(Integer.parseInt(aux)== ((Professor)entidade).getId())
                    vetorJson.remove(elemento);
            }

            /* Cria um objeto que irá armazenar esse vetor de usuários */
            JSONObject guarda = new JSONObject();
            guarda.put("professor",vetorJson);

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
                bw.write("{\"professor\": []}"); 
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
            JSONObject professor = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) professor.get("professor");
            
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



    /* Funcao que busca um id no banco de dados e retorna o usuario*/
    public Professor buscaID(int id){
        Professor mProfessor = new Professor();

        /* Variavel auxiliar */
        String aux;

        /* Cria um conversor de JSON para texto para que seja possível percorrer o arquivo */
        JSONParser conversorJson = new JSONParser();
        try {
            /* Converte os elementos no arquivo para um objeto JSON*/
            JSONObject professor = (JSONObject) conversorJson.parse(new FileReader(file));
            
            /* Pega o vetor dentro do objeto JSON e o guarda em um vetor JSON */
            JSONArray vetorJson = (JSONArray) professor.get("professor");
            
            /* Loop for que percorre os elementos do vetor até o seu fim */
            for (int i = 0; i < vetorJson.size() ; i++){
                /* Cria um objeto para aquele elemento que será analisado */
                JSONObject elemento = (JSONObject) vetorJson.get(i);

                /* Converte o id daquele elemento para String */
                aux = elemento.get("id").toString();

                /* Se achar o id no banco de dados, retorna-o */
                if(id == Integer.parseInt(aux)){
                    mProfessor.setNome(elemento.get("nome").toString());
                    mProfessor.setSobrenome(elemento.get("sobrenome").toString());
                    mProfessor.setEmail(elemento.get("email").toString());
                    mProfessor.setDiaNasc(Integer.parseInt(elemento.get("diaNasc").toString()));
                    mProfessor.setMesNasc(Integer.parseInt(elemento.get("mesNasc").toString()));
                    mProfessor.setAnoNasc(Integer.parseInt(elemento.get("anoNasc").toString()));
                    mProfessor.setId(Integer.parseInt(elemento.get("id").toString()));
                    mProfessor.setSenha(elemento.get("senha").toString());
                    return mProfessor;
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
        return mProfessor;
    }
}

