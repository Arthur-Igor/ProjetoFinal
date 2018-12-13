package data.DAO;

import data.models.Assinatura;
import data.models.Leitor;

import java.util.ArrayList;

/**
 * Classe que manipula, ler e escreve os leitores na persistência de dados!
 *
 * @author Arthur e Gleisson.
 */
public class LeitorDAO {
    static int id = 0;
    private static LeitorDAO INSTANCE = new LeitorDAO();
    ArrayList<Leitor> leitores;

    private LeitorDAO() {
        this.leitores = new ArrayList<>();
    }

    public static LeitorDAO getInstance(){
        return INSTANCE;
    }

    public static void readInstance(LeitorDAO leitorDAO){
        INSTANCE = leitorDAO;
    }
    
    public Leitor get(int id){
        try {
            
        for (Leitor leitor : leitores) {
            if (leitor.getId() == id) return leitor;
        }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void remove(Leitor leitor){
        leitores.remove(leitor);
    }
    
    public void update(Leitor leitor){
        for (Leitor leitorAux : leitores) {
            if (leitor.getId() == leitor.getId()){
                remove(leitor);
                leitores.add(leitor);
            }
        }
    }

    public void save(Leitor leitorNew) {
        leitores.add(leitorNew);
    }
    
    public ArrayList<Leitor> getAll(){
        return leitores;
    }
        public static int nextID(){
        return id++;
    }
}
