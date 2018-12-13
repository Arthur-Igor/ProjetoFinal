/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.controllers;

import data.DAO.AcervoDAO;
import data.DAO.AssinaturaDAO;
import data.DAO.LeitorDAO;
import data.models.Assinatura;
import data.models.Leitor;
import data.models.Livro;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes da classe Controlador!
 *
 * @author Arthur e Gleisson.
 */
public class ControladorTest {

    LeitorDAO leitorDAO = LeitorDAO.getInstance();
    AcervoDAO acervoDAO = AcervoDAO.getInstance();
    AssinaturaDAO assinaturaDAO = AssinaturaDAO.getInstance();
    Controlador c1 = new Controlador();
    Assinatura a1 = new Assinatura(1, "Basic");
    Leitor l1 = new Leitor(1, 1111111, "jose@123", "jose", 111111);
    Livro livro = new Livro(1, "amor", "123", "4", "joao",
            "paz", 2000, "ptbr", "forro", "muito bom",
            22.22);

    public ControladorTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Teste do método cadrastarLeitor, da classe Controlador.
     */
    @Test
    public void testCadastrarLeitor() {
        Leitor l2 = new Leitor(2, 1111111, "jose@123", "jose", 111111);
        leitorDAO.save(l2);

        assertTrue(leitorDAO.getAll().contains(l2));
    }

    /**
     * Teste do método removerLeitor, da classe Controlador.
     */
    @Test
    public void testRemoverLeitor() {
        Leitor l2 = new Leitor(2, 1111111, "jose@123", "jose", 111111);

        leitorDAO.save(l2);
        leitorDAO.remove(l2);

        assertFalse(leitorDAO.getAll().contains(l2));
    }

    /**
     * Teste do método editarLeitor, da classe Controlador.
     */
    @Test
    public void testEditarLeitor() {
        leitorDAO.save(l1);
        leitorDAO.get(1).setDataDeNascimento(222222);
        assertEquals(leitorDAO.get(1).getDataDeNascimento(), 222222);
    }

    /**
     * Teste do método cadrastarLivro, da classe Controlador.
     */
    @Test
    public void testCadastrarLivro() {
        acervoDAO.save(livro);

        assertTrue(acervoDAO.getAll().contains(livro));
    }

    /**
     * Teste do método editarLivro, da classe Controlador.
     */
    @Test
    public void testEditarLivros() {
        acervoDAO.save(livro);
        acervoDAO.get(1).setTitulo("paz");

        assertTrue(acervoDAO.get(1).getTitulo().equals("paz"));
    }

    /**
     * Teste do método excluirLivro, da classe Controlador.
     */
    @Test
    public void testExcluirLivros() {
        Livro livro2 = new Livro(2, "amor", "123", "4", "joao",
                "paz", 2000, "ptbr", "forro", "muito bom",
                22.22);

        acervoDAO.remove(livro2);

        assertFalse(acervoDAO.getAll().contains(livro2));

    }


}
