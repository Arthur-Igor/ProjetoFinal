package data.DAO;

import data.models.Assinatura;
import data.models.Leitor;
import data.models.Livro;
import data.models.Qualificacao;

import java.util.ArrayList;

/**
 * Classe que manipula, ler e escreve as assinaturas na persistência de dados!
 *
 * @author Arthur e Gleisson.
 */
public class AssinaturaDAO {

    static int id = 0;
    private static AssinaturaDAO INSTANCE = new AssinaturaDAO();
    ArrayList<Assinatura> assinaturas;

    private AssinaturaDAO() {
        this.assinaturas = new ArrayList<>();
    }

    static public AssinaturaDAO getInstance() {
        return INSTANCE;
    }

    public static void readInstance(AssinaturaDAO assinaturaDAO) {
        INSTANCE = assinaturaDAO;
    }

//    public Assinatura get(int idAssinante) {;
//        for (Assinatura assinatura : assinaturas) {
//            if (assinatura.getAssinante().getId() == idAssinante) {
//                return assinatura;
//            }
//        }
//        return null;
//    }

    public static int nextID() {
        return id++;
    }

    public void save(Assinatura assinatura) {
        assinaturas.add(assinatura);
        id++;
    }

    public ArrayList<Assinatura> getAll() {
        return assinaturas;
    }

}
