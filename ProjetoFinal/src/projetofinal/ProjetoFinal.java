/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import data.DAO.DAO;

import data.controllers.Controlador;

/**
 * Classe Main.
 *
 * @author Arthur e Gleisson.
 */
public class ProjetoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DAO dao = DAO.getInstance();
        //fds
        dao.readData();
        dao.saveData();
        // TODO code application logic here
        Controlador control = new Controlador();

        boolean contador = true;
        while (contador) {
            System.out.println("Netflix de livros");
            System.out.println("============Menu Principal============\n");

            System.out.println("Leitor:(1)\nLivros:(2)\n");
            int m1 = control.verifica();

            switch (m1) {//menu principal
                case 1://menu de leitor
                    System.err.println("!=======================!Leitor:!=======================!\n\n");
                    System.out.println("Cadastrar:(1)\nExcluir:(2)\nEditar:(3)\nListar:(4)\nVer Perfil:(5)\nAdicionar Livro:(6)\nAdicionar Comentario:(7)\nVer comentario do leitor a um livro:(8)\nVer todos os comentarios de um leitor:(9)\n");
                    int m2 = control.verifica();

                    switch (m2) {//menu de leitor
                        case 1:
                            control.cadastrarLeitor();
                            break;
                        case 2:
                            control.removerLeitor();
                            break;
                        case 3:
                            control.editarLeitor();
                            break;
                        case 4:
                            control.listarLeitores();
                            break;
                        case 5:
                            control.perfilLeitor();
                            break;
                        case 6:
                            control.addLivro();
                            break;
                        case 7:
                            control.comentar();
                            break;
                        case 8:
                            control.verComentario();
                            break;
                        case 9:
                            break;
                        default:
                            System.out.println("Entrada invalida!");
                            break;
                    }
                    break;
                case 2://menu de livro
                    System.out.println("!=======================!Livro:!=======================!\n\n");
                    System.out.println("Cadastrar:(1)\nExcluir:(2)\nListar:(3)\nEditar:(4)\nVer Perfil:(5)\nVer todos os comentarios:(6)\nVer livros por palavra chave:(7)\n");
                    int m3 = control.verifica();

                    switch (m3) {//menu de livro
                        case 1:
                            control.cadastrarLivro();
                            break;
                        case 2:
                            control.excluirLivros();
                            break;
                        case 3:
                            control.listarLivros();
                            break;
                        case 4:
                            control.editarLivros();
                            break;
                        case 5:
                            control.mostrarPerfilLivro();
                            break;
                        case 6:
                            control.verTodosComentariosLivro();
                            break;
                        case 7:
//                            System.out.println("Digite a palavra chave");
//                            String nome = control.entrada();
                            control.procurarLivroPorChave();
                            break;
                        default:
                            System.out.println("Entrada invalida!");
                            break;
                    }
                    break;
            }
            if (m1 == 0) {
                System.out.println("Bye");
                contador = false;
            }
        }
    }

}
