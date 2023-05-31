/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.suppermarket.model.DAO;

/**
 *
 * @author gabri
 */

import com.mycompany.suppermarket.model.ConexaoBD;
import com.mycompany.suppermarket.model.Endereco;
import com.mycompany.suppermarket.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO implements UsuarioDAO{
    private Connection conexao;
    
    public FornecedorDAO() throws SQLException, ClassNotFoundException {
        this.conexao = ConexaoBD.getConnection();
    }
    
    
    @Override
    public void create(Object o) throws SQLException {
    Fornecedor fornecedor = (Fornecedor) o;

    String sql = "INSERT INTO tb_fornecedor (nome, cnpj, ie) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, fornecedor.getNome());
        stmt.setString(2, fornecedor.getCnpj());
        stmt.setString(3, fornecedor.getIe());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            int fornecedorId = rs.getInt(1);

            // Insere o endereço na tabela tb_enderecos
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.create(fornecedorId, fornecedor.getEndereco().getEndereco(), fornecedor.getEndereco().getCep());
        }
    }
}

    @Override
    public void update(Object o) throws SQLException {
        Fornecedor fornecedor = (Fornecedor) o;
        String sql = "UPDATE tb_fornecedor SET nome = ? WHERE cnpj = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            
            stmt.execute();
        }
        
    }

    @Override
    public void delete(Object o) throws SQLException {
        Fornecedor fornecedor = (Fornecedor) o;
        String deleteEnderecoSql = "DELETE FROM tb_enderecos WHERE fornecedor_id = (SELECT id FROM tb_fornecedor WHERE cnpj = ?)";
        String deleteFornecedorSql = "DELETE FROM tb_fornecedor WHERE cnpj = ?";
            try (PreparedStatement stmtEndereco = conexao.prepareStatement(deleteEnderecoSql);
                 PreparedStatement stmtFornecedor = conexao.prepareStatement(deleteFornecedorSql)) {

                stmtEndereco.setString(1, fornecedor.getCnpj());
                stmtEndereco.executeUpdate();

                stmtFornecedor.setString(1, fornecedor.getCnpj());
                stmtFornecedor.executeUpdate();
            }
        
    }

    @Override
    public List<Object> read() throws SQLException {
        List<Object> fornecedores = new ArrayList<>();
        String sql = "SELECT f.id, f.nome, f.cnpj, f.ie, e.endereco, e.cep FROM tb_fornecedor f JOIN tb_enderecos e ON f.id = e.fornecedor_id";
        try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");
                String ie = rs.getString("ie");
                String endereco = rs.getString("endereco");
                String cep = rs.getString("cep");

                Endereco enderecoObj = new Endereco(endereco, cep); // Removi o uso de "enderecoId"
                Fornecedor fornecedor = new Fornecedor(nome, cnpj, ie, enderecoObj);

                fornecedores.add(fornecedor);
            }
        }

        return fornecedores;
    }
    

    public Fornecedor procurarFornecedorCNPJ(String cnpj) throws SQLException {
            String sql = "SELECT f.id, f.nome, f.cnpj, f.ie, e.endereco, e.cep " +
                         "FROM tb_fornecedor f " +
                         "JOIN tb_enderecos e ON f.id = e.fornecedor_id " +
                         "WHERE f.cnpj = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, cnpj);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        String ie = rs.getString("ie");
                        String endereco = rs.getString("endereco");
                        String cep = rs.getString("cep");

                        Endereco enderecoObj = new Endereco(endereco, cep);
                        Fornecedor fornecedor = new Fornecedor(nome, cnpj, ie, enderecoObj);

                        return fornecedor;
                    }
                }
            }

            return null;
}

}
