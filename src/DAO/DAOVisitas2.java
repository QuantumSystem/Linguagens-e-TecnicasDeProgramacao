/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Visitas2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class DAOVisitas2 {

    public ArrayList<Visitas2> getAll() {
        ArrayList<Visitas2> lista = new ArrayList<>();
        Visitas2 v;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) {
            return null;
        }
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM visitas2");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                v = new Visitas2();
                v.setId(rs.getInt("id"));
                v.setVisita(rs.getString("visita"));
                v.setRg(rs.getString("rg"));
                v.setCpf(rs.getString("cpf"));
                v.setTelefone(rs.getString("telefone"));
                v.setCelular(rs.getString("celular"));
                v.setResidencias_id(rs.getInt("residencias_id"));
                v.setPessoas_id(rs.getInt("pessoas_id"));
                v.setVeiculo(rs.getString("veiculo"));
                v.setPlaca(rs.getString("placa"));
                v.setCor(rs.getString("cor"));
                v.setAdmin_id(rs.getString("admin_id"));
                v.setEntrada(rs.getString("entrada"));
                v.setSaida(rs.getString("saida"));
                lista.add(v);
            }
        } catch (SQLException ex) {
            System.out.println("Falhou Metodo getAll :" + ex.getMessage());
        }
        return lista;
    }

    public Visitas2 get(int id) {
        Visitas2 v = null;
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) {
            return null;
        }
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM visitas2 WHERE id = " + id);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                v = new Visitas2();
                v.setId(rs.getInt("id"));
                v.setVisita(rs.getString("visita"));
                v.setRg(rs.getString("rg"));
                v.setCpf(rs.getString("cpf"));
                v.setTelefone(rs.getString("telefone"));
                v.setCelular(rs.getString("celular"));
                v.setResidencias_id(rs.getInt("residencias_id"));
                v.setPessoas_id(rs.getInt("pessoas_id"));
                v.setVeiculo(rs.getString("veiculo"));
                v.setPlaca(rs.getString("placa"));
                v.setCor(rs.getString("cor"));
                v.setAdmin_id(rs.getString("admin_id"));
                v.setEntrada(rs.getString("entrada"));
                v.setSaida(rs.getString("saida"));
            }
        } catch (SQLException ex) {
            System.out.println("Falhou Metodo get :" + ex.getMessage());
        }
        return v;
    }

    public int insertVisitas(Visitas2 visitas2) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) {
            return -2;
        }
        try {
            Statement st = conn.createStatement();
            if (visitas2.getSaida() == null) {
                visitas2.setSaida("1980-01-01 00:00:00");
            }
            st.executeUpdate("INSERT INTO visitas2"
                    + "(visita, rg, cpf, telefone, celular, residencias_id, pessoas_id, veiculo, placa, cor, admin_id, entrada, saida) VALUES ('"
                    + visitas2.getVisita() + "','"
                    + visitas2.getRg() + "','"
                    + visitas2.getCpf() + "','"
                    + visitas2.getTelefone() + "','"
                    + visitas2.getCelular() + "','"
                    + visitas2.getResidencias_id() + "','"
                    + visitas2.getPessoas_id() + "','"
                    + visitas2.getVeiculo() + "','"
                    + visitas2.getPlaca() + "','"
                    + visitas2.getCor() + "','"
                    + visitas2.getAdmin_id() + "','"
                    + visitas2.getEntrada() + "','"
                    + visitas2.getSaida() + "')");
            System.out.println(" inserido com sucesso!!");
        } catch (SQLException ex) {
            System.out.println("Falhou Metodo Insert:" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int deletaVisitas(int id) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM visitas2 WHERE id="+id);
            System.out.println("Visita2 "+ id + " deletado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Falhou metodo deleta :" + ex.getMessage());
            return -1;
        }
        return 1;
    }
    
    public int updateVisitas(Visitas2 visitas2) {
        Connection conn = ConnectionFactory.getConnection();
        if (conn == null) return -2;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE visitas2"
                + " SET visita = '"+visitas2.getVisita()+"',"
                + " rg = '"+visitas2.getRg()+"',"
                + " cpf = '"+visitas2.getCpf()+"',"
                + " telefone = '"+visitas2.getTelefone()+"',"
                + " celular = '"+visitas2.getCelular()+"',"
                + " residencias_id = '"+visitas2.getResidencias_id()+"',"
                + " pessoas_id = '"+visitas2.getPessoas_id()+"',"
                + " veiculo = '"+visitas2.getVeiculo()+"',"
                + " placa = '"+visitas2.getPlaca()+"',"
                + " cor = '"+visitas2.getCor()+"',"
                + " admin_id = '"+visitas2.getAdmin_id()+"',"
                + " entrada = '"+visitas2.getEntrada()+"',"
                + " saida = '"+visitas2.getSaida()+"'"
                + " WHERE id ="+visitas2.getId());
            System.out.println(" alterado com sucesso!!");
         } 
        catch (SQLException ex) {
            System.out.println("Falhou Metodo Update :" + ex.getMessage());
            return -1;
        }
        return 1;
    }

}
