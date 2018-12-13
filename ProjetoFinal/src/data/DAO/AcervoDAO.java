package data.DAO;

import data.models.Livro;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que manipula, ler e escreve os livros na persistência de dados!
 *
 * @author Arthur e Gleisson.
 */
public class AcervoDAO {
    AcervoDAO acervoDAO = AcervoDAO.getInstance();
    int b=0;
    private static AcervoDAO INSTANCE = new AcervoDAO();
    private ArrayList<Livro> livrosCadastrados = new ArrayList();

    private AcervoDAO() {
        this.livrosCadastrados = new ArrayList<>();
    }

    static public AcervoDAO getInstance(){
        return INSTANCE;
    }

    public static void readInstance(AcervoDAO acervoDAO){
        INSTANCE = acervoDAO;
    }
    public ArrayList<Livro> getLivros() {
        return livrosCadastrados;
    }

    public void setLivros(Livro livro) {
        this.livrosCadastrados.add(livro);
    }
    public Livro pega(int id){
       
        return livrosCadastrados.get(id);
    }
    public Livro get(int id){
        for (Livro livro : livrosCadastrados) {
            if (livro.getId() == id) return livro;
        }
        System.err.println("NAO");
        return null;
    }
    
    public void save(Livro livro){
        livrosCadastrados.add(livro);  
 
    }
    
    public void remove(Livro livro){
        livrosCadastrados.remove(livrosCadastrados);
    }
    
    public ArrayList<Livro> getAll(){
        return livrosCadastrados;
    }
        
    public void update(Livro livro){
        for (Livro livroAux : livrosCadastrados) {
            if (livro.getId() == livro.getId()){
                remove(livro);
                livrosCadastrados.add(livro);
            }
        }
    }
    
//    public ArrayList<Livro> searchForName(String nomeASerBuscado) {
//        System.out.println("Digite a palavra-chave a ser buscada: ");
//        ArrayList<Livro> achados = new ArrayList<>();
//        Pattern ABC = Pattern.compile(nomeASerBuscado);
//        try {
//                    for (Livro livro : acervoDAO.getAll()) {
//            Matcher m = ABC.matcher(livro.getTitulo());
//            if (m.find()) {
//                achados.add(livro);
//                System.out.println("Achei " + livro.getTitulo() + " = " + nomeASerBuscado);
//            }
//        }
//        } catch (Exception e) {
//        }
//
//        return achados;
//    }

    public ArrayList<Livro> getLivrosCadastrados() {
        return livrosCadastrados;
    }

    public void setLivrosCadastrados(ArrayList<Livro> livrosCadastrados) {
        this.livrosCadastrados = livrosCadastrados;
    }
}
