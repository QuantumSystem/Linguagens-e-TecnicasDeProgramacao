/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOVisitas;
import Model.Visitas;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiago
 */
public class ControllerVisitas extends Observable{
   public Visitas getVisitas(int id) {
        DAOVisitas dao = new DAOVisitas();
        return dao.get(id);
    }
   
   public DefaultTableModel getAllTable(){
        DAOVisitas daov = new DAOVisitas();
        ArrayList<Visitas> visitas = 
                (ArrayList<Visitas>) daov.getAll();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("ID_Residencia");
        modelo.addColumn("ID_Morador");
        modelo.addColumn("ID_Veiculo");
        modelo.addColumn("Entrada");
        modelo.addColumn("Saída");

        if(visitas != null) visitas.forEach((visita) -> {
          modelo.addRow(new Object[] {
              visita.getId(),
              visita.getResidencias_id(),
              visita.getPessoas_id(),
              visita.getVeiculos_id(),
              visita.getEntrada(),
              visita.getSaida()});
       });
        return modelo;
    }
   
   public void insertVisitas(int residencias_id, int pessoas_id, int veiculos_id, String entrada, String saida) {
        DAOVisitas dao = new DAOVisitas();
        Visitas v = new Visitas();
        v.setResidencias_id(residencias_id);
        v.setPessoas_id(pessoas_id);
        v.setVeiculos_id(veiculos_id);
        v.setEntrada(entrada);
        v.setSaida(saida);
        dao.insertVisitas(v);
        this.setChanged();
        this.notifyObservers();
    }
   
   public void deletaVisitas(int id) {
        DAOVisitas dao = new DAOVisitas();
        dao.deletaVisitas(id);
    }
   
   public void updateVisitas(Visitas visitas) {
        DAOVisitas dao = new DAOVisitas();
        dao.updateVisitas(visitas);
        this.setChanged();
        this.notifyObservers();
    }
   
}
