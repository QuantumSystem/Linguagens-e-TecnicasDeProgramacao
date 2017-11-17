/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author tiago
 */
public class Visitas {
    private int id;
    private int pessoas_id;
    private int residencias_id;
    private int veiculos_id;
    private String entrada;
    private String saida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPessoas_id() {
        return pessoas_id;
    }

    public void setPessoas_id(int pessoas_id) {
        this.pessoas_id = pessoas_id;
    }

    public int getResidencias_id() {
        return residencias_id;
    }

    public void setResidencias_id(int residencias_id) {
        this.residencias_id = residencias_id;
    }

    public int getVeiculos_id() {
        return veiculos_id;
    }

    public void setVeiculos_id(int veiculos_id) {
        this.veiculos_id = veiculos_id;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }
    
}
