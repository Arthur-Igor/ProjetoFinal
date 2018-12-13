package data.models;

/**
 * Classe que contém todos os atributos e métodos getters e setters da qualificação do livro!
 *
 * @author Arthur e Gleisson.
 */

public class Qualificacao {

    final public static String LIDO = "Lido";
    final public static String ABANDONADO = "Abandonado";
    final public static String EM_LEITURA = "Em leitura";
    final public static String A_LER = "A ler";
    private Livro livro;
    private String estado;
    private String comentario;

    Qualificacao(Livro livro, String estado) {
        this.estado = estado;
        this.livro = livro;

    }

    public Livro getLivro() {
        return livro;
    }
    public String getTitulo(){
        return livro.getTitulo();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    

}
