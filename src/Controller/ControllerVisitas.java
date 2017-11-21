/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOVisitas;
import Model.Visitas;
import java.util.Observable;

/**
 *
 * @author tiago
 */
public class ControllerVisitas extends Observable{
   public Visitas getVisitas(int id) {
        DAOVisitas dao = new DAOVisitas();
        return dao.get(id);
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
