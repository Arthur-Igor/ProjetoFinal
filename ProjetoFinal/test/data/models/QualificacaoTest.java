/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes da classe Qualificacao!
 *
 * @author Arthur e Gleisson.
 */
public class QualificacaoTest {
        Livro livro = new Livro(1, "amor", "123", "4", "joao",
                "paz", 2000, "ptbr", "forro", "muito bom",
                22.22);
    Qualificacao q1 = new Qualificacao(livro, "Em leitura"); 
    
    public QualificacaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        q1.setComentario("bom");
        q1.setEstado("Em leitura");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Teste do método getLivro, da classe qualificacao.
     */
    @Test
    public void testGetLivro() {
        assertEquals(livro, q1.getLivro());
    }

    /**
     * Teste do método getTitulo, da classe qualificacao.
     */
    @Test
    public void testGetTitulo() {
        assertEquals("amor", q1.getLivro().getTitulo());
    }

    /**
     * Teste do método getEstado, da classe qualificacao.
     */
    @Test
    public void testGetEstado() {
        assertEquals("Em leitura", q1.getEstado());
    }

    /**
     * Teste do método setEstado, da classe qualificacao.
     */
    @Test
    public void testSetEstado() {
        q1.setEstado("A ler");
    }

    /**
     * Teste do método getComentario, da classe qualificacao.
     */
    @Test
    public void testGetComentario() {
        assertEquals("bom", q1.getComentario());
    }

    /**
     * Teste do método setComentario, da classe qualificacao.
     */
    @Test
    public void testSetComentario() {
        q1.setComentario("otimo");
        assertEquals("otimo", q1.getComentario());
    }
    
}
