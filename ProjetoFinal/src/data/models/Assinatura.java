/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.models;

import data.controllers.Pagamentos;

import java.util.ArrayList;

/**
  * Classe que contém todos os atributos e métodos getters e setters da assinatura!
 *
 * @author Arthur e Gleisson.
 */
public class Assinatura implements Pagamentos {

    public static final String BASIC = "Basic";
    public static final String CORE = "Core";
    public static final String PREMIUM = "Premium";
    private int id;
    private String tipo;
    private double valor;
    private boolean aberto;
    private ArrayList<Qualificacao> livros;
    int c = 0;

    public Assinatura(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.valor = getValor();
        this.aberto = true;
        this.livros = new ArrayList<>();
    }

     public void addLivro(Livro livro, String estado) {
        int x = 0;
        for (Qualificacao qualificacao : livros) {
            if (qualificacao.getEstado().equals("Em leitura")) {
                x++;
            }
        }
        if (tipo.equals(BASIC)) {
            if (x < 3) {
                livros.add(new Qualificacao(livro, estado));
            }else{
                System.err.println("Quantidade de livros ja esta no limite");
            }
        }
        if (tipo.equals(CORE)) {
            if (x < 5) {
                livros.add(new Qualificacao(livro, estado));
            }else{
                System.err.println("Quantidade de livros ja esta no limite");
            }
        }
        if (tipo.equals(PREMIUM)) {
            if (x < 10) {
                livros.add(new Qualificacao(livro, estado));
            }else{
                System.err.println("Quantidade de livros ja esta no limite");
            }
        }
    }
     public void adiciona(Livro livro, String estado){
         livros.add(new Qualificacao(livro, estado));
     }
    public void removeLivro(Livro livro) {
        livros.remove(livro);
    }
    

    private double getValor() {
        return 0.0;
    }

    @Override
    public void pagarPorCartao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pagarPorBoleto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Qualificacao getQualificao(int idLivro) {
        for (Qualificacao livro : livros) {
            if (livro.getLivro().getId() == idLivro) {
                return livro;
            }
        }
        return null;
    }

    public ArrayList<Qualificacao> getAllQualificacao() {
        return livros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Qualificacao> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Qualificacao> livros) {
        this.livros = livros;
    }
    
}
