/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.suppermarket.model.DAO;

import com.mycompany.suppermarket.model.ConexaoBD;
import com.mycompany.suppermarket.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class FuncionarioDAO implements UsuarioDAO{
    private Connection conexao;
    
    public FuncionarioDAO() throws SQLException, ClassNotFoundException {
        this.conexao = ConexaoBD.getConnection();
    }
    
    @Override
    public void create(Object o) throws SQLException {
        Funcionario funcionario = (Funcionario) o;

        String sql = "INSERT INTO tb_funcionario (nome, rg, cpf) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> read() throws SQLException {
        List<Object> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM tb_funcionario";
        try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String rg = rs.getString("rg");
                String cpf = rs.getString("cpf");

                Funcionario f = new Funcionario(nome, rg, cpf);

                funcionarios.add(f);
            }
        }

        return funcionarios;
    }
    
}
