/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.suppermarket.controller;

/**
 *
 * @author gabri
 */

import com.mycompany.suppermarket.model.DAO.EnderecoDAO;
import com.mycompany.suppermarket.model.Endereco;
import java.sql.SQLException;

public class EnderecoController {
    private EnderecoDAO enderecoDAO;

    
    public EnderecoController() {
        this.enderecoDAO = new EnderecoDAO();
    }
    
    public void cadastrarEndereco(int fornecedorId, String endereco, String cep) throws SQLException {
        Endereco enderecoObj = new Endereco(endereco, cep);
        enderecoDAO.create(fornecedorId, enderecoObj.getEndereco(), enderecoObj.getCep());
    }



     
}
