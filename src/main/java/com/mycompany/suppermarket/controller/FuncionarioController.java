/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.suppermarket.controller;

/**
 *
 * @author gabri
 */


import com.mycompany.suppermarket.model.DAO.FuncionarioDAO;
import com.mycompany.suppermarket.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() throws SQLException, ClassNotFoundException {
        this.funcionarioDAO = new FuncionarioDAO();
    }
    
    public void cadastrarFuncionario(String nome, String rg, String cpf) throws SQLException {

        List<Object> funcionarios = funcionarioDAO.read();
        for (Object o : funcionarios) {
            Funcionario f = (Funcionario) o;
            f.setNome(nome);
            f.setRg(rg);
            f.setCpf(rg);

            if (cpf.equals(f.getCpf()) && cpf != null) {
            throw new RuntimeException("Já existe um funcionario cadastrado com o CPF informado.");
        }
            funcionarioDAO.create(f);
        }
        
    }
}