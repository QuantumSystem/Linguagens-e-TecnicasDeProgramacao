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
import DAO.ConnectionFactory;
import DAO.DAOPessoas;
import DAO.DAOResidencias;
import DAO.DAOVeiculos;
import Model.Pessoas;
import Model.Residencias;
import Model.Veiculos;
import Model.Visitas;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author tiago
 */
public class UIVisitas extends javax.swing.JInternalFrame implements Observer {

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
            uivisitas.refreshTable();
        }
        return uivisitas;
    }

    public UIVisitas() {
        initComponents();
        //Combo Residencias
        loadCmbResidencias();
        //Combo Pessoas
        loadCmbPessoas();
        //Combo Veiculos
        loadCmbVeiculos();
    }

    public void setController(ControllerPessoas _ctrlPessoas, ControllerResidencias _ctrlResidencias, ControllerVeiculos _ctrlVeiculos, ControllerVisitas _ctrlVisitas) {
        this.ctrlPessoas = _ctrlPessoas;
        this.ctrlResidencias = _ctrlResidencias;
        this.ctrlVeiculos = _ctrlVeiculos;
        this.ctrlVisitas = _ctrlVisitas;
    }

    public void refreshTable() {
        this.tblList3.setModel(this.ctrlVisitas.getAllTable());
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

    public void preenche() {
        txtEntrada.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 4).toString());
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
        btnRelatorio = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblList3 = new javax.swing.JTable();
        txtData = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Visitas"));

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

        jLabel4.setText("Residencia: *");

        jLabel5.setText("Morador: *");

        jLabel6.setText("Veiculo: *");

        btnSaida.setText("Saída");
        btnSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaidaActionPerformed(evt);
            }
        });

        jLabel2.setText("Entrada:");

        btnRelatorio.setText("Relatório");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRelatorio)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaida)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbVeiculos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
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
                        .addComponent(btnRelatorio)))
                .addContainerGap())
        );

        tblList3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblList3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtData, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblList3MouseClicked
        preenche();
    }//GEN-LAST:event_tblList3MouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        ImageIcon info = new ImageIcon("src/img/info.png");
        String dataEntrada = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String saida = null;

        Residencias residencias = (Residencias) this.cmbResidencia.getSelectedItem();
        Pessoas pessoas = (Pessoas) this.cmbPessoas.getSelectedItem();
        Veiculos veiculos = (Veiculos) this.cmbVeiculos.getSelectedItem();

        if (this.visitas == null) {
            ctrlVisitas.insertVisitas(residencias.getId(), pessoas.getId(), veiculos.getId(), dataEntrada, saida);
            JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE, info);
        } else {
            this.visitas.setResidencias_id(residencias.getId());
            this.visitas.setPessoas_id(pessoas.getId());
            this.visitas.setVeiculos_id(veiculos.getId());
            this.visitas.setEntrada(dataEntrada);

            ctrlVisitas.updateVisitas(this.visitas);
        }
        refreshTable();

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        ImageIcon warning = new ImageIcon("src/img/warning.png");
        ImageIcon info = new ImageIcon("src/img/info.png");
        int selected = this.tblList3.getSelectedRow();
        if (selected < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selevione um registro!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE, info);
            return;
        }
        int id = (int) this.tblList3.getValueAt(selected, 0);
        int x = JOptionPane.showConfirmDialog(rootPane, "Deseja deletar o : " + id + " ?", "ATENÇÃO", JOptionPane.OK_CANCEL_OPTION, HEIGHT, warning);
        if (x == 0) {
            this.ctrlVisitas.deletaVisitas(id);
            this.refreshTable();
            JOptionPane.showMessageDialog(rootPane, "Deletado com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE, info);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
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
            JOptionPane.showMessageDialog(this, "Selecione um registro");
        } else {
            int id = (int) this.tblList3.getValueAt(selected, 0);

            this.visitas = new Visitas();
            this.visitas.setId(id);
            this.visitas.setEntrada(txtEntrada.getText());
            this.visitas.setSaida(timeStamp);
            ctrlVisitas.updateVisitas(this.visitas);
            refreshTable();
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!");
        }
    }//GEN-LAST:event_btnSaidaActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
        Connection conn = ConnectionFactory.getConnection();
        String src = "src/Relatorio/relatorio_visitas.jasper";
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(src, null, conn);
        } catch (JRException ex) {
            System.out.println("Error: " + ex);
        }

        JasperViewer view = new JasperViewer(jasperPrint, false);
        view.setVisible(true);
    }//GEN-LAST:event_btnRelatorioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnRelatorio;
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
        this.refreshTable();
    }
}
