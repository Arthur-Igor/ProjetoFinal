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
 * Classe de testes da classe Leitor!
 *
 * @author Arthur e Gleisson.
 */
public class LeitorTest {

    Leitor l1 = new Leitor(1, 1111111, "jose@123", "jose", 111111);

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        l1.setCpf(1111111);
        l1.setNome("jose");
        l1.setId(1);
        l1.setEmail("jose@123");
        l1.setDataDeNascimento(111111);
    }

    @After
    public void tearDown() {

    }

    /**
     * Teste do método getId, da classe leitor.
     */
    @Test
    public void testGetId() {

        assertEquals(1, l1.getId());
    }

    /**
     * Teste do método setId, da classe leitor.
     */
    @Test
    public void testSetId() {
        l1.setId(2);
        assertEquals(2, l1.getId());
    }

    /**
     * Teste do método getCpf, da classe leitor.
     */
    @Test
    public void testGetCpf() {
        assertEquals(1111111, l1.getCpf());
    }

    /**
     * Teste do método setCpf, da classe leitor.
     */
    @Test
    public void testSetCpf() {
        l1.setCpf(222222);
        assertEquals(222222, l1.getCpf());
    }

    /**
     * Teste do método getEmail, da classe leitor.
     */
    @Test
    public void testGetEmail() {
        assertEquals("jose@123", l1.getEmail());
    }

    /**
     * Teste do método setEmail, da classe leitor.
     */
    @Test
    public void testSetEmail() {
        l1.setEmail("jose@1234");
        assertEquals("jose@1234", l1.getEmail());
    }

    /**
     * Teste do método getNome, da classe leitor.
     */
    @Test
    public void testGetNome() {
        assertEquals("jose", l1.getNome());
    }

    /**
     * Teste do método setNome, da classe leitor.
     */
    @Test
    public void testSetNome() {
        l1.setNome("josee");
        assertEquals("josee", l1.getNome());
    }

    /**
     * Teste do método getDataDeNascimento, da classe leitor.
     */
    @Test
    public void testGetDataDeNascimento() {
        assertEquals(111111, l1.getDataDeNascimento());
    }

    /**
     * Teste do método setDataDeNascimento, da classe leitor.
     */
    @Test
    public void testSetDataDeNascimento() {
        l1.setDataDeNascimento(222222);
        assertEquals(222222, l1.getDataDeNascimento());
    }

}
