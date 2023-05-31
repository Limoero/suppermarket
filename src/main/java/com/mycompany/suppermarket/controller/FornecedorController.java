/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.suppermarket.controller;

/**
 *
 * @author gabri
 */

import com.mycompany.suppermarket.model.DAO.FornecedorDAO;
import com.mycompany.suppermarket.model.Fornecedor;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;


public class FornecedorController {
    private FornecedorDAO fornecedorDAO;
    
    public FornecedorController() throws SQLException, ClassNotFoundException {
        this.fornecedorDAO = new FornecedorDAO();
    }
    
        public void cadastrarFornecedor(Fornecedor fornecedor) throws SQLException {

        List<Object> fornecedores = fornecedorDAO.read();
        for (Object o : fornecedores) {
            Fornecedor c = (Fornecedor) o;
            String cnpj = c.getCnpj();
            String fornecedorCnpj = fornecedor.getCnpj();
            if (cnpj != null && fornecedorCnpj != null && cnpj.equals(fornecedorCnpj)) {
            throw new RuntimeException("Já existe um fornecedor cadastrado com o CNPJ informado.");
        }
        }

        fornecedorDAO.create(fornecedor);
    }
    
        public Fornecedor buscarCNPJ(String cnpj) throws SQLException {
            try {
                Fornecedor fornecedor = fornecedorDAO.procurarFornecedorCNPJ(cnpj);

                if (fornecedor != null) {
                    StringBuilder sb = new StringBuilder();
                    String nome = fornecedor.getNome();
                    sb.append("Nome: ").append(nome).append("\n");
                    sb.append("CNPJ: ").append(fornecedor.getCnpj()).append("\n");
                    sb.append("IE: ").append(fornecedor.getIe()).append("\n");
                    sb.append("Endereço: ").append(fornecedor.getEndereco().getEndereco()).append(" Cep: ").append(fornecedor.getEndereco().getCep()).append("\n");

                    JOptionPane.showMessageDialog(null, sb.toString(), "Informações do Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Fornecedor não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar fornecedor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        return null;
    }
        
        public void deletarCNPJ(String cnpj) throws SQLException{
            Fornecedor fornecedor = fornecedorDAO.procurarFornecedorCNPJ(cnpj);
            fornecedorDAO.delete(fornecedor);
        }
}
