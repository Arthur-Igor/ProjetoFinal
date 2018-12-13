/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.controllers;

/**
 * Classe responsável pelos pagamentos.
 *
 * @author Arthur e Gleisson.
 */
public interface Pagamentos {
    
    public abstract void pagarPorCartao();
        
    public abstract void pagarPorBoleto();

}
