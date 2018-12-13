/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.models;

/**
 * Classe que contém todos os atributos e métodos getters e setters do leitor!
 *
 * @author Arthur e Gleisson.
 */
public class Leitor{

    private int id;
    private int cpf;
    private String email;
    private String nome;
    private long dataDeNascimento;
    private Assinatura assinatura;

    public Leitor(int id, int cpf, String email, String nome, long dataDeNascimento) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(long dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }
    

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }
    
}
