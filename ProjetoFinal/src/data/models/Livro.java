/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe que contém todos os atributos e métodos getters e setters do livro!
 *
 * @author Arthur e Gleisson.
 */
public class Livro {

    private int id;
    private String titulo;
    private String ISNB;
    private String edicao;
    private String autor;
    private String editora;
    private int ano;
    private String idioma;
    private String assunto;
    private String descricao;
    private double valor;

    public Livro(int id, String titulo, String ISNB, String edicao, String autor,
                 String editora, int ano, String idioma, String assunto, String descricao,
                 double valor) {
        this.id = id;
        this.titulo = titulo;
        this.ISNB = ISNB;
        this.edicao = edicao;
        this.autor = autor;
        this.editora = editora;
        this.ano = ano;
        this.idioma = idioma;
        this.assunto = assunto;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getISNB() {
        return ISNB;
    }

    public void setISNB(String ISNB) {
        this.ISNB = ISNB;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
