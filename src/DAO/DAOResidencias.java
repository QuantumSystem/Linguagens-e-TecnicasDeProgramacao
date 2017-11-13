/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Residencias;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class DAOResidencias {
    public ArrayList<Residencias> getAll(){
        ArrayList<Residencias> lista = new ArrayList<>();
        Residencias r;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return null;
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM residencias");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                r = new Residencias();
                r.setId(rs.getInt("id"));
                r.setRua(rs.getString("rua"));
                r.setNumero(rs.getInt("numero"));
                lista.add(r);
            }
        }
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
        }
        return lista;
    }
    
     public Residencias get(int id){
        Residencias r = null;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return null;
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM residencias WHERE id = " + id);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                r = new Residencias();
                r.setId(rs.getInt("id"));
                r.setRua(rs.getString("rua"));
                r.setNumero(rs.getInt("numero"));
            }
        }
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
        }
        return r; 
    }
     
    public int insertResidencia(Residencias residencias) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO residencias"
                + "(rua,numero) VALUES ('"
                + residencias.getRua() + "','"
                + residencias.getNumero() + "')");
            System.out.println(residencias.getRua() +  " - " + residencias.getNumero() + " inserido com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int deletaResidencias(int id) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM residencias WHERE id="+id);
            System.out.println("Residencia "+ id + " deletado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int updateResidencias(Residencias residencias) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE residencias"
                + " SET rua = '"+residencias.getRua()+"',"
                + " numero = '"+residencias.getNumero()+"'"
                + " WHERE id ="+residencias.getId());
            System.out.println(residencias.getRua() +  " - " + residencias.getNumero() + " alterado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
}
