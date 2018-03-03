/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerPessoas;
import Controller.ControllerResidencias;
import Controller.ControllerVeiculos;
import Controller.ControllerVisitas;
import DAO.DAOPessoas;
import DAO.DAOResidencias;
import DAO.DAOVeiculos;
import DAO.DAOVisitas;
import Model.Pessoas;
import Model.Residencias;
import Model.Veiculos;
import Model.Visitas;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author tiago
 */
public class UIVisitas extends javax.swing.JInternalFrame implements Observer {
    private static JDesktopPane jDesktopPane;
    private static UIVisitas uivisitas;
    private static ControllerPessoas ctrlPessoas;
    private static ControllerResidencias ctrlResidencias;
    private static ControllerVeiculos ctrlVeiculos;
    private static ControllerVisitas ctrlVisitas;
    private Visitas visitas = null;

    /**
     * Creates new form UIVisitas
     */
    public static UIVisitas getInstancia() {
        if (uivisitas == null) {
            uivisitas = new UIVisitas();
            ControllerVisitas ctrlVisitas = new ControllerVisitas();
            uivisitas.setController(ctrlPessoas, ctrlResidencias, ctrlVeiculos, ctrlVisitas);
            ctrlVisitas.addObserver(uivisitas);
            uivisitas.readJTable();
        }
        return uivisitas;
    }

    public UIVisitas() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) tblList3.getModel();
        tblList3.setRowSorter(new TableRowSorter(modelo));
        tblList3.getColumnModel().getColumn(0).setPreferredWidth(15);//id
        tblList3.getColumnModel().getColumn(1).setPreferredWidth(15);//residencia
        tblList3.getColumnModel().getColumn(2).setPreferredWidth(15);//morador
        tblList3.getColumnModel().getColumn(3).setPreferredWidth(15);//veiculo
        tblList3.getColumnModel().getColumn(4).setPreferredWidth(140);//entrada
        tblList3.getColumnModel().getColumn(5).setPreferredWidth(140);//saida
        padrao();

        
    }

    public void setController(ControllerPessoas _ctrlPessoas, ControllerResidencias _ctrlResidencias, ControllerVeiculos _ctrlVeiculos, ControllerVisitas _ctrlVisitas) {
        this.ctrlPessoas = _ctrlPessoas;
        this.ctrlResidencias = _ctrlResidencias;
        this.ctrlVeiculos = _ctrlVeiculos;
        this.ctrlVisitas = _ctrlVisitas;
    }

    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) tblList3.getModel();
        modelo.setNumRows(0);
        DAOVisitas vDAO = new DAOVisitas();

        for (Visitas p : vDAO.getAll()) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getResidencias_id(),
                p.getPessoas_id(),
                p.getVeiculos_id(),
                p.getEntrada(),
                p.getSaida()});
        }
    }

    //Combo Residencias
    public void loadCmbResidencias() {
        DAOResidencias daor = new DAOResidencias();
        for (Residencias r : daor.getAll()) {
            cmbResidencia.addItem(r);
        }
    }

    //Combo Pessoas
    public void loadCmbPessoas() {
        DAOPessoas daop = new DAOPessoas();
        for (Pessoas r : daop.getAll()) {
            cmbPessoas.addItem(r);
        }
    }

    //Combo Veiculos
    public void loadCmbVeiculos() {
        DAOVeiculos daov = new DAOVeiculos();
        for (Veiculos v : daov.getAll()) {
            cmbVeiculos.addItem(v);
        }
    }

//    public void loadFormDataResidencias() {
//       ArrayList<Residencias> listar =  ctrlResidencias.getAll();
//       this.cmbResidencia.removeAllItems();
//       for(int i = 0; i < listar.size(); i++) this.cmbResidencia.addItem(listar.get(i));
//    }
//    public void loadFormDataPessoas() {
//       ArrayList<Pessoas> listap =  ctrlPessoas.getAll();
//       this.cmbPessoas.removeAllItems();
//       for(int i = 0; i < listap.size(); i++) this.cmbPessoas.addItem(listap.get(i));
//    }
//    
//    public void loadFormDataVeiculos() {
//       ArrayList<Veiculos> listav =  ctrlVeiculos.getAll();
//       this.cmbVeiculos.removeAllItems();
//       for(int i = 0; i < listav.size(); i++) this.cmbVeiculos.addItem(listav.get(i));
//    }
    public void setVisita(Visitas v) {
        this.visitas = v;
        if (v != null) {
            //residencia
            for (int i = 0; i < this.cmbResidencia.getItemCount(); i++) {
                if (this.cmbResidencia.getItemAt(i).getId() == v.getResidencias_id()) {
                    this.cmbResidencia.setSelectedIndex(i);
                }
            }
            //pessoas
            for (int i = 0; i < this.cmbPessoas.getItemCount(); i++) {
                if (this.cmbPessoas.getItemAt(i).getId() == v.getPessoas_id()) {
                    this.cmbPessoas.setSelectedIndex(i);
                }
            }
            //veiculos
            for (int i = 0; i < this.cmbVeiculos.getItemCount(); i++) {
                if (this.cmbVeiculos.getItemAt(i).getId() == v.getVeiculos_id()) {
                    this.cmbVeiculos.setSelectedIndex(i);
                }
            }
        }
    }

    public void padrao() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnSaida.setEnabled(false);
        btnCancelar.setEnabled(true);

        cmbResidencia.setEnabled(false);
        cmbPessoas.setEnabled(false);
        cmbVeiculos.setEnabled(false);
    }

    public void novo() {
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(false);
        btnSaida.setEnabled(false);
        btnCancelar.setEnabled(true);

        cmbResidencia.setEnabled(true);
        cmbPessoas.setEnabled(true);
        cmbVeiculos.setEnabled(true);
    }

    public void excluir() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnSaida.setEnabled(false);
        btnCancelar.setEnabled(true);

        cmbResidencia.setEnabled(false);
        cmbPessoas.setEnabled(false);
        cmbVeiculos.setEnabled(false);
    }

    public void tabela() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(true);
        btnSaida.setEnabled(true);
        btnCancelar.setEnabled(true);

        cmbResidencia.setEnabled(true);
        cmbPessoas.setEnabled(true);
        cmbVeiculos.setEnabled(true);
    }

    public void populaCampos() {
        txtEntrada.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 4).toString());
    }

    public void limpaCampos() {
        txtEntrada.setText("");
    }
    
    public void loadAllCombobox(){
        loadCmbResidencias();//Combo Residencias
        loadCmbPessoas();//Combo Pessoas
        loadCmbVeiculos();//Combo Veiculos
    }
    
    public void removeAllCombobox(){
        cmbResidencia.removeAllItems();
        cmbPessoas.removeAllItems();
        cmbVeiculos.removeAllItems();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cmbResidencia = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbPessoas = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbVeiculos = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnSaida = new javax.swing.JButton();
        txtEntrada = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblList3 = new javax.swing.JTable();
        txtData = new javax.swing.JLabel();

        setTitle("CADASTRO DE VISÍTAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Preencha os campos abaixo!"));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setText("RESIDÊNCIA: *");

        jLabel5.setText("MORADOR: *");

        jLabel6.setText("VEÍCULO: *");

        btnSaida.setText("Saída");
        btnSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaidaActionPerformed(evt);
            }
        });

        txtEntrada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("ENTRADA:");

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cmbResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbVeiculos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaida)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvar)
                        .addComponent(btnExcluir)
                        .addComponent(btnCancelar)
                        .addComponent(btnSaida)
                        .addComponent(jLabel2)
                        .addComponent(btnNovo)))
                .addContainerGap())
        );

        tblList3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblList3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RESIDENCIA", "MORADOR", "VEÍCULO", "ENTRADA", "SAÍDA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblList3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clica(evt);
            }
        });
        jScrollPane4.setViewportView(tblList3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblList3MouseClicked
        limpaCampos();
        populaCampos();
        tabela();
    }//GEN-LAST:event_tblList3MouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String dataEntrada = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String saida = null;

        Residencias residencias = (Residencias) this.cmbResidencia.getSelectedItem();
        Pessoas pessoas = (Pessoas) this.cmbPessoas.getSelectedItem();
        Veiculos veiculos = (Veiculos) this.cmbVeiculos.getSelectedItem();

        if (this.visitas == null) {
            ctrlVisitas.insertVisitas(residencias.getId(), pessoas.getId(), veiculos.getId(), dataEntrada, saida);
            readJTable();
            limpaCampos();
            padrao();
            JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.visitas.setResidencias_id(residencias.getId());
            this.visitas.setPessoas_id(pessoas.getId());
            this.visitas.setVeiculos_id(veiculos.getId());
            this.visitas.setEntrada(dataEntrada);

            ctrlVisitas.updateVisitas(this.visitas);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int selected = this.tblList3.getSelectedRow();
        if (selected < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um registro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) this.tblList3.getValueAt(selected, 0);
        int x = JOptionPane.showConfirmDialog(rootPane, "Deseja deletar : " + id + " ?", "ATENÇÃO", JOptionPane.OK_CANCEL_OPTION, HEIGHT);
        if (x == 0) {
            this.ctrlVisitas.deletaVisitas(id);
            readJTable();
            limpaCampos();
            excluir();
            JOptionPane.showMessageDialog(rootPane, "Deletado com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        padrao();
        removeAllCombobox();
        tblList3.getSelectionModel().clearSelection();//Desfaz uma seleção da tabela
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void clica(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clica
        this.tblList3.editingCanceled(null);
    }//GEN-LAST:event_clica

    private void btnSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaidaActionPerformed
        String timeStamp = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        txtEntrada.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 4).toString());
        int selected = this.tblList3.getSelectedRow();
        if (selected < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um registro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            int id = (int) this.tblList3.getValueAt(selected, 0);

            this.visitas = new Visitas();
            this.visitas.setId(id);
            this.visitas.setEntrada(txtEntrada.getText());
            this.visitas.setSaida(timeStamp);

            ctrlVisitas.updateVisitas(this.visitas);
            readJTable();
            limpaCampos();
            padrao();
            JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSaidaActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        novo();
        limpaCampos();
        loadAllCombobox();
        tblList3.getSelectionModel().clearSelection();//Desfaz uma seleção da tabela
    }//GEN-LAST:event_btnNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSaida;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Pessoas> cmbPessoas;
    private javax.swing.JComboBox<Residencias> cmbResidencia;
    private javax.swing.JComboBox<Veiculos> cmbVeiculos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblList3;
    private javax.swing.JLabel txtData;
    private javax.swing.JLabel txtEntrada;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        readJTable();
    }
}
