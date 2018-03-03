/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOVisitas2;
import Model.Visitas2;
import java.util.Observable;

/**
 *
 * @author tiago
 */
public class ControllerVisitas2 extends Observable{
    public Visitas2 getVisitas(int id) {
        DAOVisitas2 dao = new DAOVisitas2();
        return dao.get(id);
    }
    
    public void insertVisitas(String visita, String rg, String cpf, String telefone, String celular, int residencias_id, int pessoas_id, String veiculo, String placa, String cor, String admin_id, String entrada, String saida) {
        DAOVisitas2 dao = new DAOVisitas2();
        Visitas2 v = new Visitas2();
        v.setVisita(visita);
        v.setRg(rg);
        v.setCpf(cpf);
        v.setTelefone(telefone);
        v.setCelular(celular);
        v.setResidencias_id(residencias_id);
        v.setPessoas_id(pessoas_id);
        v.setVeiculo(veiculo);
        v.setPlaca(placa);
        v.setCor(cor);
        v.setAdmin_id(admin_id);
        v.setEntrada(entrada);
        v.setSaida(saida);
        dao.insertVisitas(v);
        this.setChanged();
        this.notifyObservers();
    }
    
    public void deletaVisitas(int id) {
        DAOVisitas2 dao = new DAOVisitas2();
        dao.deletaVisitas(id);
    }
    
    public void updateVisitas(Visitas2 visitas2) {
        DAOVisitas2 dao = new DAOVisitas2();
        dao.updateVisitas(visitas2);
        this.setChanged();
        this.notifyObservers();
    }
}
