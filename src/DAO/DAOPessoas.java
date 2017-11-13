/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pessoas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class DAOPessoas {
    public ArrayList<Pessoas> getAll(){
        ArrayList<Pessoas> lista = new ArrayList<>();
        Pessoas p;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return null;
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM pessoas");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                p = new Pessoas();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setRg(rs.getString("rg"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setCelular(rs.getString("celular"));
                lista.add(p);
            }
        }
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
        }
        return lista;
    }
    
    public Pessoas get(int id){
        Pessoas p = null;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return null;
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM pessoas WHERE id = " + id);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                p = new Pessoas();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setRg(rs.getString("rg"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setCelular(rs.getString("celular"));
            }
        }
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
        }
        return p; 
    }
    
    public int insertPessoas(Pessoas pessoas) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO pessoas"
                + "(nome,rg,cpf,telefone,celular) VALUES ('"
                + pessoas.getNome() + "','"
                + pessoas.getRg() + "','"
                + pessoas.getCpf() + "','"
                + pessoas.getTelefone() + "','"
                + pessoas.getCelular() + "')");
            System.out.println(pessoas.getNome() + " inserido com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int deletaPessoas(int id) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM pessoas WHERE id="+id);
            System.out.println("Pessoa "+ id + " deletado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int updatePessoas(Pessoas pessoas) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE pessoas"
                + " SET nome = '"+pessoas.getNome()+"',"
                + " rg = '"+pessoas.getRg()+"',"
                + " cpf = '"+pessoas.getCpf()+"',"
                + " telefone = '"+pessoas.getTelefone()+"',"
                + " celular = '"+pessoas.getCelular()+"'"
                + " WHERE id ="+pessoas.getId());
            System.out.println(pessoas.getNome() + " alterado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
}
