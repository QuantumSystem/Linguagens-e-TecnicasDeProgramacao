/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Veiculos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class DAOVeiculos {
    public ArrayList<Veiculos> getAll(){
        ArrayList<Veiculos> lista = new ArrayList<>();
        Veiculos v;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return null;
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM veiculos");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                v = new Veiculos();
                v.setId(rs.getInt("id"));
                v.setNome(rs.getString("nome"));
                v.setPlaca(rs.getString("placa"));
                v.setCor(rs.getString("cor"));
                lista.add(v);
            }
        }
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
        }
        return lista;
    }
    
     public Veiculos get(int id){
        Veiculos v = null;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return null;
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM veiculos WHERE id = " + id);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                v = new Veiculos();
                v.setId(rs.getInt("id"));
                v.setNome(rs.getString("nome"));
                v.setPlaca(rs.getString("placa"));
                v.setCor(rs.getString("cor"));
            }
        }
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
        }
        return v; 
    }
     
    public int insertVeiculos(Veiculos veiculos) {
        ImageIcon error = new ImageIcon("src/img/error.png");
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO veiculos"
                + "(nome,placa,cor) VALUES ('"
                + veiculos.getNome() + "','"
                + veiculos.getPlaca() + "','"
                + veiculos.getCor() + "')");
            System.out.println(veiculos.getNome() + " inserido com sucesso!!");
         } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Já existe uma placa com este número!", "ERRO!", JOptionPane.ERROR_MESSAGE, error);
            System.out.println("Erro :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int deletaVeiculos(int id) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM veiculos WHERE id="+id);
            System.out.println("Veiculo "+ id + " deletado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int updateVeiculos(Veiculos veiculos) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE veiculos"
                + " SET nome = '"+veiculos.getNome()+"',"
                + " placa = '"+veiculos.getPlaca()+"',"
                + " cor = '"+veiculos.getCor()+"'"
                + " WHERE id ="+veiculos.getId());
            System.out.println(veiculos.getNome() + " alterado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Statement :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    
}
