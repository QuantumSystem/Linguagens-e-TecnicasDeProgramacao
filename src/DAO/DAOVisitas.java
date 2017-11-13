/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Visitas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author tiago
 */
public class DAOVisitas {
    public ArrayList<Visitas> getAll(){
        ArrayList<Visitas> lista = new ArrayList<>();
        Visitas v;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return null;
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM visitas");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                v = new Visitas();
                v.setId(rs.getInt("id"));
                v.setPessoas_id(rs.getInt("pessoas_id"));
                v.setResidencias_id(rs.getInt("residencias_id"));
                v.setVeiculos_id(rs.getInt("veiculos_id"));
                v.setEntrada(rs.getString("entrada"));
                v.setSaida(rs.getString("saida"));
                lista.add(v);
            }
        }
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
        }
        return lista;
    }
    
    public Visitas get(int id){
        Visitas v = null;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return null;
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM visitas WHERE id = " + id);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                v = new Visitas();
                v.setId(rs.getInt("id"));
                v.setPessoas_id(rs.getInt("pessoas_id"));
                v.setResidencias_id(rs.getInt("residencias_id"));
                v.setVeiculos_id(rs.getInt("veiculos_id"));
                v.setEntrada(rs.getString("entrada"));
            }
        }
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
        }
        return v; 
    }
    
    public int insertVisitas(Visitas visitas) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            if (visitas.getSaida() == null) visitas.setSaida("1980-01-01 00:00:00");
            st.executeUpdate("INSERT INTO visitas"
                + "(pessoas_id,residencias_id,veiculos_id, entrada, saida) VALUES ('"
                + visitas.getPessoas_id() + "','"
                + visitas.getResidencias_id()+ "','"
                + visitas.getVeiculos_id()+ "','"
                + visitas.getEntrada()+ "','"
                + visitas.getSaida() + "')");
            System.out.println(" inserido com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int deletaVisitas(int id) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM visitas WHERE id="+id);
            System.out.println("Visita "+ id + " deletado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int updateVisitas(Visitas visitas) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE visitas"
                + " SET pessoas_id = '"+visitas.getPessoas_id()+"',"
                + " residencias_id = '"+visitas.getResidencias_id()+"',"
                + " veiculos_id = '"+visitas.getVeiculos_id()+"'," 
                + " entrada = '"+visitas.getEntrada()+"',"
                + " saida = '"+visitas.getSaida()+"'"
                + " WHERE id ="+visitas.getId());
            System.out.println(" alterado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
}
