/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOVeiculos;
import Model.Veiculos;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiago
 */
public class ControllerVeiculos extends Observable{
    public Veiculos getVeiculos(int id) {
        DAOVeiculos dao = new DAOVeiculos();
        return dao.get(id);
    }
    
    public DefaultTableModel getAllTable(){
        DAOVeiculos daov = new DAOVeiculos();
        ArrayList<Veiculos> veiculos = 
                (ArrayList<Veiculos>) daov.getAll();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nome do Veículo");
        modelo.addColumn("Placa");
        modelo.addColumn("Cor");

        if(veiculos != null) veiculos.forEach((veiculo) -> {
          modelo.addRow(new Object[] {
              veiculo.getId(),
              veiculo.getNome(),
              veiculo.getPlaca(),
              veiculo.getCor()});
       });
        return modelo;
    }
    
    public void insertPessoas(String nome, String placa, String cor) {
        DAOVeiculos dao = new DAOVeiculos();
        Veiculos v = new Veiculos();
        v.setNome(nome);
        v.setPlaca(placa);
        v.setCor(cor);
        dao.insertVeiculos(v);
        this.setChanged();
        this.notifyObservers();
    }
    
    public void deletaVeiculos(int id) {
        DAOVeiculos dao = new DAOVeiculos();
        dao.deletaVeiculos(id);
    }
    
    public void updateVeiculos(Veiculos veiculos) {
        DAOVeiculos dao = new DAOVeiculos();
        dao.updateVeiculos(veiculos);
        this.setChanged();
        this.notifyObservers();
    }
    
    public ArrayList<Veiculos> getAll()
    {
        DAOVeiculos daoVeiculos = new DAOVeiculos();
        ArrayList<Veiculos> veiculos = (ArrayList<Veiculos>) daoVeiculos.getAll();
        
        return veiculos;
    }
}
