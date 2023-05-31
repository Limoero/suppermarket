/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.suppermarket.model.DAO;

import com.mycompany.suppermarket.model.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class EnderecoDAO {
        private Connection conexao;
        
        public EnderecoDAO(){
            this.conexao = ConexaoBD.getConnection();
        }
        
        public void create(int fornecedorId, String endereco, String cep) throws SQLException {
        String sql = "INSERT INTO tb_enderecos (fornecedor_id, cep, endereco) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, fornecedorId);
            stmt.setString(2, cep);
            stmt.setString(3, endereco);

            stmt.execute();
        }
        }
        
        public void cadastrarFornecedorId(int enderecoId, int fornecedorId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tb_enderecos SET fornecedor_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, fornecedorId);
            stmt.setInt(2, enderecoId);
            stmt.executeUpdate();
            
            }
   
        }
    }
