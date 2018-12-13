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
 * Classe de testes da classe Livro!
 *
 * @author Arthur e Gleisson.
 */
public class LivroTest {
    Livro livro = new Livro(1, "amor", "123", "4", "joao",
                "paz", 2000, "ptbr", "forro", "muito bom",
                22.22);
    
    public LivroTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        livro.setId(1);
        livro.setTitulo("amor");
        livro.setISNB("123");
        livro.setEdicao("4");
        livro.setAutor("joao");
        livro.setEditora("paz");
        livro.setAno(2000);
        livro.setIdioma("ptbr");
        livro.setAssunto("forro");
        livro.setDescricao("muito bom");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Teste do método getId, da classe livro.
     */
    @Test
    public void testGetId() {
        assertEquals(1, livro.getId());
    }

    /**
     * Teste do método setId, da classe livro.
     */
    @Test
    public void testSetId() {
        livro.setId(2);
        assertEquals(2, livro.getId());
    }

    /**
     * Teste do método getTitulo, da classe livro.
     */
    @Test
    public void testGetTitulo() {
        assertEquals("amor", livro.getTitulo());
    }

    /**
     * Teste do método setTitulo, da classe livro.
     */
    @Test
    public void testSetTitulo() {
        livro.setTitulo("amor1");
        assertEquals("amor1", livro.getTitulo());
    }

    /**
     * Teste do método getISNB, da classe livro.
     */
    @Test
    public void testGetISNB() {
        assertEquals("123", livro.getISNB());
    }

    /**
     * Teste do método setISNB, da classe livro.
     */
    @Test
    public void testSetISNB() {
        livro.setISNB("1234");
        assertEquals("1234", livro.getISNB());
    }

    /**
     * Teste do método getEdicao, da classe livro.
     */
    @Test
    public void testGetEdicao() {
        assertEquals("4", livro.getEdicao());
    }

    /**
     * Teste do método setEdicao, da classe livro.
     */
    @Test
    public void testSetEdicao() {
        livro.setEdicao("5");
        assertEquals("5", livro.getEdicao());
    }

    /**
     * Teste do método getAutor, da classe livro.
     */
    @Test
    public void testGetAutor() {
        assertEquals("joao", livro.getAutor());
    }

    /**
     * Teste do método setAutor, da classe livro.
     */
    @Test
    public void testSetAutor() {
        livro.setAutor("maria");
        assertEquals("maria", livro.getAutor());
    }

    /**
     * Teste do método getEditora, da classe livro.
     */
    @Test
    public void testGetEditora() {
        assertEquals("paz", livro.getEditora());
    }

    /**
     * Teste do método setEditora, da classe livro.
     */
    @Test
    public void testSetEditora() {
        livro.setEditora("aaa");
        assertEquals("aaa", livro.getEditora());
    }

    /**
     * Teste do método getAno, da classe livro.
     */
    @Test
    public void testGetAno() {
        assertEquals(2000, livro.getAno());
    }

    /**
     * Teste do método setAno, da classe livro.
     */
    @Test
    public void testSetAno() {
        livro.setAno(2001);
        assertEquals(2001, livro.getAno());
    }

    /**
     * Teste do método getIdioma, da classe livro.
     */
    @Test
    public void testGetIdioma() {
        assertEquals("ptbr", livro.getIdioma());
    }

    /**
     * Teste do método setIdioma, da classe livro.
     */
    @Test
    public void testSetIdioma() {
        livro.setIdioma("ruru");
        assertEquals("ruru", livro.getIdioma());
    }

    /**
     * Teste do método getAssunto, da classe livro.
     */
    @Test
    public void testGetAssunto() {
        assertEquals("forro", livro.getAssunto());
    }

    /**
     * Teste do método setAssunto, da classe livro.
     */
    @Test
    public void testSetAssunto() {
        livro.setAssunto("forrozao");
        assertEquals("forrozao", livro.getAssunto());
    }

    /**
     * Teste do método getDescricao, da classe livro.
     */
    @Test
    public void testGetDescricao() {
        assertEquals("muito bom", livro.getDescricao());
    }

    /**
     * Teste do método setDescricao, da classe livro.
     */
    @Test
    public void testSetDescricao() {
        livro.setDescricao("bom demais");
        assertEquals("bom demais", livro.getDescricao());
    }

}
