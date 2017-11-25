/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerPessoas;
import DAO.DAOPessoas;
import Model.Pessoas;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author tiago
 */
public class UIPessoas extends javax.swing.JInternalFrame implements Observer {

    private static UIPessoas uipessoas;
    private ControllerPessoas controller;
    private Pessoas pessoas = null;

    /**
     * Creates new form UIPessoas
     */
    public static UIPessoas getInstancia() {
        if (uipessoas == null) {
            uipessoas = new UIPessoas();
            ControllerPessoas ctrlPessoas = new ControllerPessoas();
            uipessoas.setController(ctrlPessoas);
            ctrlPessoas.addObserver(uipessoas);
            uipessoas.readJTable();
        }
        return uipessoas;
    }

    public UIPessoas() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) tblList.getModel();
        tblList.setRowSorter(new TableRowSorter(modelo));
        tblList.getColumnModel().getColumn(0).setPreferredWidth(10);//id
        tblList.getColumnModel().getColumn(1).setPreferredWidth(150);//nome
        tblList.getColumnModel().getColumn(2).setPreferredWidth(55);//rg
        tblList.getColumnModel().getColumn(3).setPreferredWidth(55);//cpf
        tblList.getColumnModel().getColumn(4).setPreferredWidth(55);//telefone
        tblList.getColumnModel().getColumn(5).setPreferredWidth(70);//celular
        padrao();
    }

    public void setController(ControllerPessoas _controller) {
        this.controller = _controller;
    }

    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) tblList.getModel();
        modelo.setNumRows(0);
        DAOPessoas pDAO = new DAOPessoas();

        for (Pessoas p : pDAO.getAll()) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getRg(),
                p.getCpf(),
                p.getTelefone(),
                p.getCelular(),
            });
        }
    }

    public void preenche() {
        txtNome.setText(tblList.getValueAt(tblList.getSelectedRow(), 1).toString());
        txtRg.setText(tblList.getValueAt(tblList.getSelectedRow(), 2).toString());
        txtCpf.setText(tblList.getValueAt(tblList.getSelectedRow(), 3).toString());
        txtTelefone.setText(tblList.getValueAt(tblList.getSelectedRow(), 4).toString());
        txtCelular.setText(tblList.getValueAt(tblList.getSelectedRow(), 5).toString());
    }

    public void padrao() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnCancelar.setEnabled(true);

        txtNome.setEnabled(false);
        txtRg.setEnabled(false);
        txtCpf.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtCelular.setEnabled(false);
    }

    public void novo() {
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnCancelar.setEnabled(true);

        txtNome.setEnabled(true);
        txtRg.setEnabled(true);
        txtCpf.setEnabled(true);
        txtTelefone.setEnabled(true);
        txtCelular.setEnabled(true);
    }

    public void alterar() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnCancelar.setEnabled(true);

        txtNome.setEnabled(false);
        txtRg.setEnabled(false);
        txtCpf.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtCelular.setEnabled(false);
    }

    public void excluir() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnCancelar.setEnabled(true);

        txtNome.setEnabled(false);
        txtRg.setEnabled(false);
        txtCpf.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtCelular.setEnabled(false);
    }

    public void tabela() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnCancelar.setEnabled(true);

        txtNome.setEnabled(true);
        txtRg.setEnabled(true);
        txtCpf.setEnabled(true);
        txtTelefone.setEnabled(true);
        txtCelular.setEnabled(true);
    }

    public void limpaCampo() {
        txtNome.setText("");
        txtRg.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        txtCpf = new javax.swing.JFormattedTextField();
        txtRg = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();

        setTitle("Cadastro de Pessoas");

        tblList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "RG", "CPF", "TELEFONE", "CELULAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList.getTableHeader().setReorderingAllowed(false);
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clica(evt);
            }
        });
        jScrollPane1.setViewportView(tblList);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Pessoas"));

        jLabel1.setText("NOME: *");

        jLabel4.setText("TELEFONE:");

        jLabel5.setText("CELULAR:");

        jLabel2.setText("RG:");

        jLabel3.setText("CPF:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
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

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir)
                    .addComponent(btnCancelar)
                    .addComponent(btnNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        limpaCampo();
        preenche();
        tabela();
    }//GEN-LAST:event_tblListMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampo();
        padrao();
        tblList.getSelectionModel().clearSelection();//Desfaz uma seleção da tabela
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (txtNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Campo NOME: Obrigatório", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            if (this.pessoas == null) {
                controller.insertPessoas(txtNome.getText(), txtRg.getText(), txtCpf.getText(), txtTelefone.getText(), txtCelular.getText());
                limpaCampo();
                padrao();
                readJTable();
                JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.pessoas.setNome(txtNome.getText());
                this.pessoas.setRg(txtRg.getText());
                this.pessoas.setCpf(txtCpf.getText());
                this.pessoas.setTelefone(txtTelefone.getText());
                this.pessoas.setCelular(txtCelular.getText());
                controller.updatePessoas(this.pessoas);
                JOptionPane.showMessageDialog(null, "Erro");
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int selected = this.tblList.getSelectedRow();
        if (selected < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um registro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            int id = (int) this.tblList.getValueAt(selected, 0);
            this.pessoas = new Pessoas();
            this.pessoas.setId(id);
            this.pessoas.setNome(txtNome.getText());
            this.pessoas.setRg(txtRg.getText());
            this.pessoas.setCpf(txtCpf.getText());
            this.pessoas.setTelefone(txtTelefone.getText());
            this.pessoas.setCelular(txtCelular.getText());

            controller.updatePessoas(this.pessoas);
            limpaCampo();
            readJTable();
            alterar();
            JOptionPane.showMessageDialog(rootPane, "Alterado com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int selected = this.tblList.getSelectedRow();
        if (selected < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um registro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) this.tblList.getValueAt(selected, 0);
        String nome = (String) this.tblList.getValueAt(selected, 1);
        int x = JOptionPane.showConfirmDialog(rootPane, "Deseja deletar : " + nome + " ?", "ATENÇÃO", JOptionPane.OK_CANCEL_OPTION, HEIGHT);
        if (x == 0) {
            this.controller.deletaPessoas(id);
            readJTable();
            limpaCampo();
            excluir();
            JOptionPane.showMessageDialog(rootPane, "Deletado com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void clica(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clica
        this.tblList.editingCanceled(null);
    }//GEN-LAST:event_clica

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limpaCampo();
        novo();
        tblList.getSelectionModel().clearSelection();//Desfaz uma seleção da tabela
    }//GEN-LAST:event_btnNovoActionPerformed

    @Override
    public void update(Observable o, Object arg) {
        readJTable();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblList;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
