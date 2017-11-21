/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOPessoas;
import Model.Pessoas;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author tiago
 */
public class ControllerPessoas extends Observable{
    public Pessoas getPessoas(int id) {
        DAOPessoas dao = new DAOPessoas();
        return dao.get(id);
    }
    
    public void insertPessoas(String nome, String rg, String cpf, String telefone, String celular) {
        DAOPessoas dao = new DAOPessoas();
        Pessoas r = new Pessoas();
        r.setNome(nome);
        r.setRg(rg);
        r.setCpf(cpf);
        r.setTelefone(telefone);
        r.setCelular(celular);
        dao.insertPessoas(r);
        this.setChanged();
        this.notifyObservers();
    }
    
    public void deletaPessoas(int id) {
        DAOPessoas dao = new DAOPessoas();
        dao.deletaPessoas(id);
    }
    
    public void updatePessoas(Pessoas pessoas) {
        DAOPessoas dao = new DAOPessoas();
        dao.updatePessoas(pessoas);
        this.setChanged();
        this.notifyObservers();
    }
    
    public ArrayList<Pessoas> getAll()
    {
        DAOPessoas daoPessoas = new DAOPessoas();
        ArrayList<Pessoas> pessoas = (ArrayList<Pessoas>) daoPessoas.getAll();
        
        return pessoas;
    }
}
