package data.controllers;

import data.DAO.AcervoDAO;
import data.DAO.AssinaturaDAO;
import data.DAO.DAO;
import data.DAO.LeitorDAO;
import data.models.Assinatura;
import data.models.Leitor;
import data.models.Livro;
import data.models.Qualificacao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável por controlar os User Stories!
 *
 * @author Arthur e Gleisson.
 */
public class Controlador {

//    @Override
//    public String toString() {
//        return "Controlador{" + "leitorDAO=" + leitorDAO + ", acervoDAO=" + acervoDAO + ", id=" + id + ", ler=" + ler + '}';
//    }
    LeitorDAO leitorDAO = LeitorDAO.getInstance();
    AcervoDAO acervoDAO = AcervoDAO.getInstance();

    //Acervo acervo;
    //Historico historico;
    private int id = 0;
    public static int idAssinatura = 0;
    private Scanner ler;

    public void idd() {//incrementa o id
try {
            
        id += leitorDAO.getAll().size();
        } catch (Exception e) {
            id++;
        }

    }

    /**
     * Cadastra um novo leitor.
     *
     */
    public void cadastrarLeitor() {

        Assinatura assinatura;
        System.out.println("Cadastrando novo leitor!");
        //int idLeitor, double cpf, String nome, Data dataDeNascimento, String email
        System.out.println("digite o nome: \n");
        String nome = entrada();
        System.out.println("digite o cpf: \n");
        int cpf = verifica();
        System.out.println("digite o e-mail: \n");
        String email = entrada();
        System.out.println("digite a data de nascimento: \n");
        int dataDeNascimento = verifica();
        //int a = 0; // int para interromper a verifica��o do cadastro
        idd();
        boolean verificar = true;
        for (Iterator<Leitor> it = leitorDAO.getAll().iterator(); it.hasNext();) {//roda os leitores cadastracos
            Leitor a = it.next();
            if (a.getCpf() == cpf || a.getNome() == nome || a.getEmail() == email) {//verifica se ja existe um leitor com o cpf digitado
                System.out.println("Já existe um leitor com esses dados!");
                verificar = false;
                break;
            }
        }
        if (verificar) {//cadastra o leitor
            Leitor leitorNew = new Leitor(id, cpf, email, nome, dataDeNascimento);
            System.out.println("Qual plano deseja assinar: \n Basic (1), Core (2), Premium (3)");
            int a = verifica();
            switch (a) {
                case 1:
                    idd();
                    assinatura = new Assinatura(id, Assinatura.BASIC);
                    leitorNew.setAssinatura(assinatura);
                    break;
                case 2:
                    idd();
                    assinatura = new Assinatura(id, Assinatura.CORE);
                    leitorNew.setAssinatura(assinatura);
                    break;
                case 3:
                    idd();
                    assinatura = new Assinatura(id, Assinatura.PREMIUM);
                    leitorNew.setAssinatura(assinatura);
                    break;
                default:

                    System.out.println("Op inválida");
            }
            System.out.println("leitor cadastrado! \n");
            leitorDAO.save(leitorNew);
            DAO.getInstance().saveData();
        }

    }

    /**
     * Adiciona um novo livro no acervo do leitor.
     *
     */
    public void addLivro() {
        String estado = null;
        boolean test = false;
        boolean test2 = false;
        try {
            
        if (leitorDAO.getAll().isEmpty()) {
            System.err.println("Nao existe leitores cadastrados\n");

        } else {
            listarLeitoresR();
            System.out.println("Digite o Id do leitor:");
            int id = verifica();
            for (int i = 0; i < leitorDAO.getAll().size(); i++) {
                if (leitorDAO.getAll().get(i).getId() == id) {
                    listarLivrosR();
                    System.out.println("Digite o id do livro: ");
                    int idlivro = verifica();
                    for (int k = 0; k < acervoDAO.getAll().size(); k++) {
                        if (acervoDAO.getAll().get(k).getId() == idlivro) {

                            System.out.println("Digite o estado de leitura: 1 =  \"Abandonado\" 2 = \"Lido\"  3 = \"Em leitura\" 4 =  \"A ler\"");
                            int m1 = verifica();
                            switch (m1) {
                                case 1:
                                    estado = "Abandonado";

                                    System.out.println("Justifique o motivo do abandono: ");
                                    test2 = true;
                                    test = true;

//                                            System.out.println("Justifique o motivo do abandono: ");
//                                            test = true;
                                    break;

                                case 2:
                                    estado = "Lido";
                                    System.out.println("Comente sobre sua experiencia com o livro: ");
                                    test = true;
                                    break;
                                case 3:
                                    estado = "Em leitura";
                                    break;
                                case 4:
                                    estado = "A ler";
                                    break;
                                default:
                                    System.out.println("Entrada invalida!");
                                    break;

                            }
                            Livro livro;

                            livro = acervoDAO.getAll().get(k);
                            leitorDAO.getAll().get(i).getAssinatura().addLivro(livro, estado);
                            DAO.getInstance().saveData();

                            if (test) {
                                String coment = entrada();
                                leitorDAO.getAll().get(i).getAssinatura().getQualificao(acervoDAO.getAll().get(k).getId()).setComentario(coment);
                                DAO.getInstance().saveData();

                            }
                        }
                    }

                }
            }
        }
        } catch (Exception e) {
        }
    }

    /**
     * Remove um leitor através do CPF.
     *
     */
    public void removerLeitor() {
        listarLeitoresR();
        System.out.println("Digite o id do leitor que deseja excluir");

        int id = verifica();

        try {
            
        if (leitorDAO.getAll().size() > 0) {
            for (int i = 0; i < leitorDAO.getAll().size(); i++) {
                if (leitorDAO.getAll().get(i).getId() == id) {//pega o indice i do objeo pessoa, pega o id da pessoa e compara com o id da pessoa digitada
                    leitorDAO.getAll().remove(i); //Remove a pessoa do tipo cpf com o indice i
                    DAO.getInstance().saveData();
                    System.err.println("cadastro removido");
                }
            }
        } else {
            System.err.println("Não existem leitores cadastrados");
        }
        
        } catch (Exception e) {
        }
//remove all remove todas as pessoas com tal nome	
    }

    /**
     * Lista nome, cpf, email, data de nasciment e id te todos os leitores.
     *
     */
    public void listarLeitores() {
        System.out.println("Leitores: \n");
        try {

            if (leitorDAO.getAll().isEmpty()) {

                System.err.println("Não existem leitores cadastrados");
            } else {
                for (Leitor leitor : leitorDAO.getAll()) {

                    System.out.println("Nome do leitor: " + leitor.getNome());
                    System.out.println("CPF do leitor: " + leitor.getCpf());
                    System.out.println("Email do leitor: " + leitor.getEmail());
                    System.out.println("Id do leitor: " + leitor.getId());
                    System.out.println("\n");
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Lista nome e id te todos os leitores.
     *
     */
    public void listarLeitoresR() {//Lista mais limpa para cadastros
        System.out.println("Leitores: \n");
        try {

            if (leitorDAO.getAll().isEmpty()) {

                System.err.println("Não existem leitores cadastrados");
            } else {
                for (Leitor leitor : leitorDAO.getAll()) {

                    System.out.println("Nome: " + leitor.getNome() + " Id: " + leitor.getId());

                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Lista os atributos de um leitor através do CPF.
     *
     */
    public void listarLeitoresFiltro() {
        if (leitorDAO.getAll().isEmpty()) {
            System.err.println("Nao existe leitores cadastrados\n");
        } else {
            System.out.println("Deseja encontrar o leitor por:\n(1) Nome\n(2) Cpf\n(3) E-MAIL");
            int k = verifica();
            switch (k) {
                case 1:
                    System.out.println("Digite o nome:");
                    String nome = entrada();
                    for (int i = 0; i < leitorDAO.getAll().size(); i++) {
                        if (leitorDAO.getAll().get(i).getNome().equals(nome)) {
                            System.out.println("\nDados:");
                            System.out.println("\nNome do leitor: " + leitorDAO.getAll().get(i).getNome());
                            System.out.println("\nCPF do leitor: " + leitorDAO.getAll().get(i).getCpf());
                            System.out.println("\nEmail do leitor: " + leitorDAO.getAll().get(i).getEmail());
                            System.out.println("\nData de nascimento do leitor: " + leitorDAO.getAll().get(i).getDataDeNascimento());

                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite o CPF:");
                    int cpf = verifica();
                    for (int i = 0; i < leitorDAO.getAll().size(); i++) {
                        if (leitorDAO.getAll().get(i).getCpf() == cpf) {//
                            System.out.println("\nDados:");
                            System.out.println("\nNome do leitor: " + leitorDAO.getAll().get(i).getNome());
                            System.out.println("\nCPF do leitor: " + leitorDAO.getAll().get(i).getCpf());
                            System.out.println("\nEmail do leitor: " + leitorDAO.getAll().get(i).getEmail());
                            System.out.println("\nData de nascimento do leitor: " + leitorDAO.getAll().get(i).getDataDeNascimento());;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Digite o E-Mail:");
                    String email = entrada();
                    for (int i = 0; i < leitorDAO.getAll().size(); i++) {
                        if (leitorDAO.getAll().get(i).getEmail().equals(email)) {//hospede
                            System.out.println("\nDados:");
                            System.out.println("\nNome do leitor: " + leitorDAO.getAll().get(i).getNome());
                            System.out.println("\nCPF do leitor: " + leitorDAO.getAll().get(i).getCpf());
                            System.out.println("\nEmail do leitor: " + leitorDAO.getAll().get(i).getEmail());
                            System.out.println("\nData de nascimento do leitor: " + leitorDAO.getAll().get(i).getDataDeNascimento());;
                        }
                    }

                    break;
            }
        }
    }

    /**
     * Lista o perfil completo de um leitor. A consulta é feita através do CPF.
     *
     */
    public void perfilLeitor() {
        try {
            
    if (leitorDAO.getAll().isEmpty()) {
            System.err.println("Nao existe leitores cadastrados\n");
        } else {
            listarLeitoresR();
            System.out.println("Digite o ID do Leitor");
            int ID = verifica();
            for (int i = 0; i < leitorDAO.getAll().size(); i++) {
                if (leitorDAO.getAll().get(i).getId() == ID) {//
                    System.out.println("\nDados:");
                    System.out.println("\nNome do leitor: " + leitorDAO.getAll().get(i).getNome());
                    System.out.println("\nCPF do leitor: " + leitorDAO.getAll().get(i).getCpf());
                    System.out.println("\nEmail do leitor: " + leitorDAO.getAll().get(i).getEmail());
                    System.out.println("\nData de nascimento do leitor: " + leitorDAO.getAll().get(i).getDataDeNascimento());

                    System.out.println("Tipo de assinatura: " + leitorDAO.getAll().get(i).getAssinatura().getTipo());

                    if (null != leitorDAO.getAll().get(i).getAssinatura().getTipo()) {
                        switch (leitorDAO.getAll().get(i).getAssinatura().getTipo()) {
                            case "Basic":
                                System.out.println("Valor de assinatura: 3.00R$");
                                break;
                            case "Core":
                                System.out.println("Valor de assinatura: 4.00R$");
                                break;
                            case "Premium":
                                System.out.println("Valor de assinatura: 99.00R$");
                                break;
                            default:
                                break;
                        }
                    }

                    for (int a = 0; a < leitorDAO.getAll().get(i).getAssinatura().getLivros().size(); a++) {
                        if (leitorDAO.getAll().get(i).getAssinatura().getLivros().get(a).getEstado().equals("Em leitura")) {
                            System.out.println(leitorDAO.getAll().get(i).getAssinatura().getLivros().get(a).getTitulo() + " (Em leitura) ");
                        } else if (leitorDAO.getAll().get(i).getAssinatura().getLivros().get(a).getEstado().equals("Lido")) {
                            System.out.println(leitorDAO.getAll().get(i).getAssinatura().getLivros().get(a).getTitulo() + " (Lido)");

                        } else if (leitorDAO.getAll().get(i).getAssinatura().getLivros().get(a).getEstado().equals("Abandonado")) {

                            System.out.println(leitorDAO.getAll().get(i).getAssinatura().getLivros().get(a).getTitulo() + " (Abandonado)");
                        } else if (leitorDAO.getAll().get(i).getAssinatura().getLivros().get(a).getEstado().equals("A ler")) {

                            System.out.println(leitorDAO.getAll().get(i).getAssinatura().getLivros().get(a).getTitulo() + " (A ler)");
                        }

                    }
                }

            }
        }
        } catch (Exception e) {
        }
    }

    /**
     * Edita um atributo do leitor .
     *
     */
    public void editarLeitor() {//edita um hospede existente
        System.out.println("Editar Leitor!");
        try {
            
        if (leitorDAO.getAll().isEmpty()) {
            System.err.println("Nao existe leitores cadastrados\n");

        } else {
            listarLeitoresR();
            System.out.println("Digite o CPF do leitor:");
            int CPF = verifica();
            for (int i = 0; i < leitorDAO.getAll().size(); i++) {
                if (leitorDAO.getAll().get(i).getCpf() == CPF) {//leitor
                    System.out.println("O que deseja editar:\n Nome(1)\n"
                            + "Cpf(2)\nE-MAIL(3)");
                    int a = verifica();
                    switch (a) {
                        case 1:
                            System.out.println("Digite o novo nome do leitor:\n");
                            String nome = entrada();
                            if (leitorDAO.getAll().get(i).getNome().contentEquals(nome)) {
                                System.err.println("Ja existem leitores com esse nome!");
                                break;
                            } else {
                                leitorDAO.getAll().get(i).setNome(nome);
                                System.err.println("Atualização concluída!\n");

                                DAO.getInstance().saveData();
                                break;

                            }

                        case 2:
                            System.out.println("Digite o novo CPF do leitor:\n");
                            int Cpf = verifica();
                            for (int j = 0; j < leitorDAO.getAll().size(); j++) {
                                if (leitorDAO.getAll().get(j).getCpf() == Cpf) {
                                    System.err.println("Ja existem leitores com esse CPF!");
                                    break;
                                } else {
                                    leitorDAO.getAll().get(i).setCpf(Cpf);
                                    System.err.println("Atualização concluída!\n");
                                    DAO.getInstance().saveData();
                                    break;

                                }
                            }
                            break;
                        case 3:
                            System.out.println("Digite o novo E-MAIL do leitor:\n");
                            String email = entrada();
                            for (int j = 0; j < leitorDAO.getAll().size(); j++) {
                                if (leitorDAO.getAll().get(j).getEmail().equals(email)) {
                                    System.err.println("Ja existem leitores com esse email!");
                                    break;
                                } else {
                                    leitorDAO.getAll().get(i).setEmail(email);
                                    System.err.println("Atualização concluída!\n");
                                    DAO.getInstance().saveData();
                                    break;
                                }
                            }
                            break;
                    }

                } else if (leitorDAO.getAll().size() == id) {
                    System.err.println("Nao existem leitores com esse id cadastrados!\n");
                }
            }
        }
        
        } catch (Exception e) {
        }
    }

    /**
     * Cadastra um novo leitor .
     *
     */
    public void cadastrarLivro() {
        System.out.println("Cadastrando livro");
        ler = new Scanner(System.in);
        //int idLeitor, double cpf, String nome, Data dataDeNascimento, String email
        System.out.println("Digite o ISNB: \n");
        String ISNB = entrada();
        System.out.println("Digite o autor: \n");
        String autor = entrada();
        System.out.println("Digite o titulo: \n");
        String titulo = entrada();
        System.out.println("Digite a edição: \n");
        String edicao = entrada();
        System.out.println("Digite a editora: \n");
        String editora = entrada();
        System.out.println("Digite o idioma: \n");
        String idioma = entrada();
        System.out.println("Digite o assunto: \n");
        String assunto = entrada();
        System.out.println("Digite a descrição: \n");
        String descricao = entrada();
        System.out.println("Digite o ano: \n");
        int ano = verifica();
        System.out.println("Digite o valor: \n");
        double valor = ler.nextDouble();
        System.out.println("Digite a quantidade: \n");
        int quantidade = verifica();
        System.out.println("Digite o id do livro: \n");
        idd();

        Livro livroNew = new Livro(id, titulo, ISNB, edicao, autor, editora, ano, idioma, assunto, descricao, valor);
        acervoDAO.save(livroNew);
        System.out.println("livro cadastrado! \n");
        DAO.getInstance().saveData();

    }

    ;
    
    /**
     * Edita um atributo do livro.
     * A pesquisa é feita através do id do livro.
     *
     */
    public void editarLivros() {
        ler = new Scanner(System.in);
        listarLivrosR();
        System.out.println("Digite o id do livro:");
        int id = verifica();
        try {

            if (acervoDAO.getAll().size() > 0) {
                for (int i = 0; i < acervoDAO.getAll().size(); i++) {
                    if (acervoDAO.getAll().get(i).getId() == id) {
                        System.out.println("O que deseja editar:\n Titulo do livro(1)\n"
                                + "ISNB(2)\nAutor(3)\n Edição (4)\nEditora (5)\nIdioma (6)\nAssunto (7)\nDescrição (8)\nValor (9)\n");
                        int a = verifica();
                        switch (a) {
                            case 1:
                                System.out.println("Digite o novo titulo do livro:\n");
                                String titulo = entrada();
                                if (acervoDAO.getAll().get(i).getTitulo().contentEquals(titulo)) {
                                    System.err.println("Ja existem livros com esse titulo!");
                                    break;
                                } else {
                                    acervoDAO.getAll().get(i).setTitulo(titulo);
                                    System.err.println("Atualização concluída!\n");
                                    DAO.getInstance().saveData();
                                    break;
                                }

                            case 2:
                                System.out.println("Digite o novo ISNB do livro:\n");
                                String ISNB = entrada();
                                if (acervoDAO.getAll().get(i).getISNB().contentEquals(ISNB)) {
                                    System.err.println("Ja existem livros com esse ISNB!");
                                    break;
                                } else {
                                    acervoDAO.getAll().get(i).setISNB(ISNB);
                                    System.err.println("Atualização concluída!\n");
                                    DAO.getInstance().saveData();
                                    break;
                                }

                            case 3:
                                System.out.println("Digite o novo Autor do livro:\n");
                                String autor = entrada();

                                acervoDAO.getAll().get(i).setAutor(autor);
                                System.err.println("Atualizacao concluída!\n");
                                DAO.getInstance().saveData();
                                break;

                            case 4:
                                System.out.println("Digite a nova edição do livro:\n");
                                String edicao = entrada();

                                acervoDAO.getAll().get(i).setEdicao(edicao);
                                System.err.println("Atualizacao concluída!\n");
                                DAO.getInstance().saveData();
                                break;

                            case 5:
                                System.out.println("Digite a nova editora do livro:\n");
                                String editora = entrada();

                                acervoDAO.getAll().get(i).setEditora(editora);
                                System.err.println("Atualizacao concluída!\n");
                                DAO.getInstance().saveData();
                                break;

                            case 6:
                                System.out.println("Digite o novo idioma do livro:\n");
                                String idioma = entrada();

                                acervoDAO.getAll().get(i).setIdioma(idioma);
                                System.err.println("Atualizacao concluída!\n");
                                DAO.getInstance().saveData();
                                break;

                            case 7:
                                System.out.println("Digite o novo assunto do livro:\n");
                                String Assunto = entrada();
                                acervoDAO.getAll().get(i).setAssunto(Assunto);
                                System.err.println("Atualizacao concluída!\n");
                                DAO.getInstance().saveData();
                                break;

                            case 8:
                                System.out.println("Digite a nova descrição do livro:\n");
                                String descricao = entrada();

                                acervoDAO.getAll().get(i).setDescricao(descricao);
                                System.err.println("Atualizacao concluída!\n");
                                DAO.getInstance().saveData();
                                break;
                            case 9:
                                System.out.println("Digite o novo valor do livro:\n");
                                double valor = ler.nextDouble();

                                acervoDAO.getAll().get(i).setValor(valor);
                                System.err.println("Atualizacao concluída!\n");
                                DAO.getInstance().saveData();
                                break;

                            default:
                                System.out.printf("Você digitou uma operação inválida.");

                        }
                    }

                }
            }
        } catch (Exception e) {
        }
    }

    ;
    
    /**
     * Exclui um livro.
     * A pesquisa é feita através do id do livro.
     *
     */
    public void excluirLivros() {
        listarLivrosR();
        System.out.println("Digite o id do livro que deseja excluir");

        int id = verifica();
        try {

            if (acervoDAO.getAll().size() > 0) {
                for (int i = 0; i < acervoDAO.getAll().size(); i++) {
                    if (acervoDAO.getAll().get(i).getId() == id) {//pega o indice i do obj livro, pega o id da nome e compara com o id do livro digitado

                        acervoDAO.getAll().remove(i); //Remove o livro 
                        System.err.println("Livro removido");

                    } else {
                        System.err.println("Não existem livros cadastrados");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Lista os atributos de todos os livros no acervo.
     *
     */
    public void listarLivros() {
        System.out.println("Livro: \n");
        try {

            if (acervoDAO.getAll().isEmpty()) {

                System.err.println("Não existem livros cadastrados");
            } else {
                for (int i = 0; i < acervoDAO.getAll().size(); i++) {
                    System.out.println("Título do livro: " + acervoDAO.getAll().get(i).getTitulo());
                    System.out.println("Id do livro: " + acervoDAO.getAll().get(i).getId());
                    System.out.println("ISNB do livro: " + acervoDAO.getAll().get(i).getISNB());
                    System.out.println("Autor do livro: " + acervoDAO.getAll().get(i).getAutor());
                    System.out.println("Editora do livro: " + acervoDAO.getAll().get(i).getEditora());
                    System.out.println("Edição do livro: " + acervoDAO.getAll().get(i).getEdicao());
                    System.out.println("Assunto do livro: " + acervoDAO.getAll().get(i).getAssunto());
                    System.out.println("Ano do livro: " + acervoDAO.getAll().get(i).getAno());
                    System.out.println("Descricao do livro: " + acervoDAO.getAll().get(i).getDescricao());
                    System.out.println("Idioma do livro: " + acervoDAO.getAll().get(i).getIdioma());
                    System.out.println("\n");
                }
            }
        } catch (Exception e) {
        }
    }

    ;
    public void listarLivrosR() {
        System.out.println("Livro: \n");
        try {

            if (acervoDAO.getAll().isEmpty()) {

                System.err.println("Não existem livros cadastrados");
            } else {
                for (int i = 0; i < acervoDAO.getAll().size(); i++) {
                    System.out.println("Título: " + acervoDAO.getAll().get(i).getTitulo() + " Id: " + acervoDAO.getAll().get(i).getId());
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Lista os livros por titulo, autor ou palavra chave afim de facilitar o
     * cadastro.
     *
     */
    public void listarLivrosCad() {
        System.out.println("Deseja filtrar os livros por: ");
        System.out.println("(1)> Titulo\n(2)> Autor\n(3)> Palavra chave");
        int a = verifica();
        switch (a) {
            case 1:
                if (acervoDAO.getAll().isEmpty()) {

                    System.err.println("Não existem livros cadastrados");
                } else {

                    System.out.println("Digite o titulo:");
                    String titulo = entrada();

                    for (int i = 0; i < acervoDAO.getAll().size(); i++) {

                        if (acervoDAO.getAll().get(i).getTitulo() == titulo) {
                            System.out.println("Título do livro: " + acervoDAO.getAll().get(i).getTitulo());
                            System.out.println("Id do livro: " + acervoDAO.getAll().get(i).getId());
                            System.out.println("ISNB do livro: " + acervoDAO.getAll().get(i).getISNB());
                            System.out.println("Autor do livro: " + acervoDAO.getAll().get(i).getAutor());
                            System.out.println("Editora do livro: " + acervoDAO.getAll().get(i).getEditora());
                            System.out.println("Edição do livro: " + acervoDAO.getAll().get(i).getEdicao());
                            System.out.println("Assunto do livro: " + acervoDAO.getAll().get(i).getAssunto());
                            System.out.println("Ano do livro: " + acervoDAO.getAll().get(i).getAno());
                            System.out.println("Descricao do livro: " + acervoDAO.getAll().get(i).getDescricao());
                            System.out.println("Idioma do livro: " + acervoDAO.getAll().get(i).getIdioma());
                        }

                    }
                }
                break;

            case 2:
                if (acervoDAO.getAll().isEmpty()) {

                    System.err.println("Não existem livros cadastrados");
                } else {

                    System.out.println("Digite o autor:");
                    String autor = entrada();

                    for (int i = 0; i < acervoDAO.getAll().size(); i++) {

                        try {
                            if (acervoDAO.getAll().get(i).getAutor() == autor) {
                                System.out.println("Título do livro: " + acervoDAO.getAll().get(i).getTitulo());
                                System.out.println("Id do livro: " + acervoDAO.getAll().get(i).getId());
                                System.out.println("ISNB do livro: " + acervoDAO.getAll().get(i).getISNB());
                                System.out.println("Autor do livro: " + acervoDAO.getAll().get(i).getAutor());
                                System.out.println("Editora do livro: " + acervoDAO.getAll().get(i).getEditora());
                                System.out.println("Edição do livro: " + acervoDAO.getAll().get(i).getEdicao());
                                System.out.println("Assunto do livro: " + acervoDAO.getAll().get(i).getAssunto());
                                System.out.println("Ano do livro: " + acervoDAO.getAll().get(i).getAno());
                                System.out.println("Descricao do livro: " + acervoDAO.getAll().get(i).getDescricao());
                                System.out.println("Idioma do livro: " + acervoDAO.getAll().get(i).getIdioma());
                            }

                        } catch (Exception e) {
                        }

                    }
                }
                break;
            case 3:
                procurarLivroPorChave();
                break;

        }
    }

    public void mostrarPerfilLivro() {
        try {

            if (acervoDAO.getAll().isEmpty()) {

                System.err.println("Não existem livros cadastrados");
            } else {

                listarLivrosR();
                System.out.println("Digite o id do livro:");

                int idlivro = verifica();

                for (int i = 0; i < acervoDAO.getAll().size(); i++) {
                    if (acervoDAO.getAll().get(i).getId() == idlivro) {
                        System.out.println("Título do livro: " + acervoDAO.getAll().get(i).getTitulo());
                        System.out.println("Id do livro: " + acervoDAO.getAll().get(i).getId());
                        System.out.println("ISNB do livro: " + acervoDAO.getAll().get(i).getISNB());
                        System.out.println("Autor do livro: " + acervoDAO.getAll().get(i).getAutor());
                        System.out.println("Editora do livro: " + acervoDAO.getAll().get(i).getEditora());
                        System.out.println("Edição do livro: " + acervoDAO.getAll().get(i).getEdicao());
                        System.out.println("Assunto do livro: " + acervoDAO.getAll().get(i).getAssunto());
                        System.out.println("Ano do livro: " + acervoDAO.getAll().get(i).getAno());
                        System.out.println("Descricao do livro: " + acervoDAO.getAll().get(i).getDescricao());
                        System.out.println("Idioma do livro: " + acervoDAO.getAll().get(i).getIdioma());
//                    for (int j = 0; j < leitorDAO.getAll().size(); j++) {
//                        if (leitorDAO.getAll().get(j).getAssinatura().getQualificao(i).getComentario().isEmpty()) {
//                        } else {
//                            System.out.println("Leitor:" + leitorDAO.getAll().get(j).getNome());
//                            System.out.println("Comentario" + leitorDAO.getAll().get(j).getAssinatura().getQualificao(i).getComentario());
//                        }
//                    }
                        System.out.println("Comentarios:");
                        for (int k = 0; k < leitorDAO.getAll().size(); k++) {
                            try {
                                if (leitorDAO.getAll().get(k).getAssinatura().getQualificao(idlivro).getComentario() != null) {
                                    System.out.println(leitorDAO.getAll().get(k).getNome() + ": " + leitorDAO.getAll().get(k).getAssinatura().getQualificao(idlivro).getComentario());
                                }

                            } catch (Exception e) {
                            }
                        }

                        System.out.println("\n");
                    }
                }

            }
        } catch (Exception e) {
        }
    }

    ;
    
    public void procurarLivroPorChave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a palavra-chave a ser buscada: ");
        String nome = entrada();

        ArrayList<Livro> achados = searchForName(nome);

        for (Livro livro : achados) {
//            System.out.println("Achei:" + livro.getTitulo());
        }
    }

    /**
     * Adiciona um comentário feito pelo leitor no livro determinado. A pesquisa
     * é feita através dos ids do leitor e livro.
     *
     */
    public void comentar() {
        ler = new Scanner(System.in);
        System.out.println("Digite o id do leitor: \n");
        listarLeitoresR();
        int i = verifica();
        System.out.println("Digite o id do livro: \n");
        listarLivrosR();
        int j = verifica();
        System.out.println("Digite o comentário: \n");
        String coment = entrada();
        try {
            
        leitorDAO.getInstance().get(i).getAssinatura().getQualificao(j).setComentario(coment);
        DAO.getInstance().saveData();
        
        } catch (Exception e) {
        }
    }

    ;
    
    /**
     * Pesquisa o id do livro e leitor e mostra o comentário feito por esse leitor.
     *
     */
    public void verComentario() {
        ler = new Scanner(System.in);
        System.out.println("Digite o id do leitor: \n");
        listarLeitoresR();
        int i = verifica();
        System.out.println("Digite o id do livro: \n");
        listarLivrosR();
        int j = verifica();
        try {
            if (leitorDAO.getInstance().get(i).getAssinatura().getQualificao(j).getComentario() != null) {
                System.out.println(leitorDAO.getInstance().get(i).getAssinatura().getQualificao(j).getComentario());
            }

        } catch (Exception e) {
            System.err.println("Nao existem comentarios desse leitor para esse livro");
        }

    }

    /**
     * Mostra todos os comentários do livro. A pesquisa é feita através do id do
     * livro.
     *
     */
    public void verTodosComentariosLivro() {
        System.out.println("Digite o id do livro: \n");
        listarLivrosR();
        int j = verifica();
        try {

            for (int i = 0; i < leitorDAO.getAll().size(); i++) {
                try {
                    if (leitorDAO.getAll().get(i).getAssinatura().getQualificao(j).getComentario() != null) {
                        System.out.println(leitorDAO.getAll().get(i).getNome() + ": " + leitorDAO.getAll().get(i).getAssinatura().getQualificao(j).getComentario());
                    }

                } catch (Exception e) {
                }
            }

        } catch (Exception e) {
        }
    }

    public void verTodosComentariosLeitor() {
        System.out.println("Digite o id do leitor: \n");
        listarLeitoresR();
        int i = verifica();
        try {
            for (int j = 0; j < leitorDAO.getInstance().get(i).getAssinatura().getLivros().size(); j++) {

                if (leitorDAO.getInstance().get(i).getAssinatura().getQualificao(j).getComentario() != null) {
                    System.out.println(leitorDAO.getInstance().get(i).getAssinatura().getQualificao(j).getComentario());
                }
            }

        } catch (Exception e) {
            System.err.println("Nao existem comentarios desse leitor para esse livro");
        }
    }

    /**
     * Método auxiliar na pesquisa de livro por palavra-chave.
     *
     */
    public ArrayList<Livro> searchForName(String nomeASerBuscado) {
        ArrayList<Livro> achados = new ArrayList<>();
        Pattern ABC = Pattern.compile(nomeASerBuscado);

        try {
            
        for (Livro livro : acervoDAO.getAll()) {
            Matcher m = ABC.matcher(livro.getTitulo());
            if (m.find()) {
                achados.add(livro);
                System.out.println("Achei :" + livro.getTitulo());
            }
        }
        
        } catch (Exception e) {
        }
        return achados;
    }

    /**
     * Método para evitar erros na leitura do Scanner.
     *
     *
     */
    public static String entrada() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String entrada = null;
        try {
            entrada = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entrada;
    }

    /**
     * Método responsável pela verificação de números inteiros.
     *
     * @return o valor caso seja válido.
     */
    public static int verifica() {//verifica se os dados digitados sao do tipo double
        boolean naoEInt = true;
        int valor = 0;
        while (naoEInt) {
            Scanner leitor = new Scanner(System.in);
            try {
                valor = leitor.nextInt();
                naoEInt = false;
            } catch (Exception e) {
                System.out.println("Os dados digitados não são válidos!");
            }
        }
        return valor;
    }

}

//DAO.getInstance().saveData();
