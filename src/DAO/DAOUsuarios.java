/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class DAOUsuarios {
     public boolean checklogin(String login, String senha){
         Connection con = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         boolean check = false;
         
         try{
             stmt = con.prepareStatement("SELECT * FROM usuarios WHERE login = ? AND senha = ?");
             stmt.setString(1, login);
             stmt.setString(2, senha);
             rs = stmt.executeQuery();
             
             if(rs.next()){
                 check = true;
             }
             
             
         }catch(SQLException ex){
             Logger.getLogger(DAOUsuarios.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return check;
     }
}
