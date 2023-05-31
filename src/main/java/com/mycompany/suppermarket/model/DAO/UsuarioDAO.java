/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.UsuarioDAO to edit this template
 */
package com.mycompany.suppermarket.model.DAO;

/**
 *
 * @author gabri
 */
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    public void create(Object o) throws SQLException;
    public void update(Object o) throws SQLException;
    public void delete(Object o) throws SQLException;
    public List<Object> read() throws SQLException;

}
