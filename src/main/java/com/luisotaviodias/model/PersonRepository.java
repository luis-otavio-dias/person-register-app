package com.luisotaviodias.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    public PersonRepository() {
        ConexaoBD.initializeDatabase();
    }

    public void save(Person person) {
        String sql = "INSERT INTO pessoa (nome, cpf, email, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getCpf());
            stmt.setString(3, person.getEmail());
            stmt.setString(4, person.getTelefone());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> listAll() {
        List<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Person p = new Person(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void clearAll() {
        String sql = "DELETE FROM pessoa";
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsCpf(String cpf) {
        if (cpf == null) return false;
        String sql = "SELECT 1 FROM pessoa WHERE cpf = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
