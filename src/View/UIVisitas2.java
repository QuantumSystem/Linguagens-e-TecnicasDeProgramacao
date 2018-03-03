/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerPessoas;
import Controller.ControllerResidencias;
import Controller.ControllerVisitas2;
import DAO.DAOPessoas;
import DAO.DAOResidencias;
import DAO.DAOVisitas2;
import Model.Pessoas;
import Model.Residencias;
import Model.Visitas2;
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
public class UIVisitas2 extends javax.swing.JInternalFrame implements Observer {

    private static JDesktopPane jDesktopPane;
    private static UIVisitas2 uivisitas2;
    private static ControllerPessoas ctrlPessoas;
    private static ControllerResidencias ctrlResidencias;
    private static ControllerVisitas2 ctrlVisitas2;
    private Visitas2 visitas2 = null;

    /**
     * Creates new form UIVisitas2
     */
    public UIVisitas2() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) tblList3.getModel();
        tblList3.setRowSorter(new TableRowSorter(modelo));
        tblList3.getColumnModel().getColumn(0).setPreferredWidth(5);//id
        tblList3.getColumnModel().getColumn(1).setPreferredWidth(20);//visita
        tblList3.getColumnModel().getColumn(2).setPreferredWidth(20);//rg
        tblList3.getColumnModel().getColumn(3).setPreferredWidth(20);//cpf
        tblList3.getColumnModel().getColumn(4).setPreferredWidth(20);//telefone
        tblList3.getColumnModel().getColumn(5).setPreferredWidth(20);//celular
        tblList3.getColumnModel().getColumn(6).setPreferredWidth(5);//residencia
        tblList3.getColumnModel().getColumn(7).setPreferredWidth(5);//morador
        tblList3.getColumnModel().getColumn(8).setPreferredWidth(20);//veiculo
        tblList3.getColumnModel().getColumn(9).setPreferredWidth(15);//placa
        tblList3.getColumnModel().getColumn(10).setPreferredWidth(15);//cor
        tblList3.getColumnModel().getColumn(11).setPreferredWidth(15);//admin
        tblList3.getColumnModel().getColumn(12).setPreferredWidth(100);//entrada
        tblList3.getColumnModel().getColumn(13).setPreferredWidth(100);//saida
        padrao();
    }

    public static UIVisitas2 getInstancia() {
        if (uivisitas2 == null) {
            uivisitas2 = new UIVisitas2();
            ControllerVisitas2 ctrlVisitas2 = new ControllerVisitas2();
            uivisitas2.setController(ctrlVisitas2, ctrlPessoas, ctrlResidencias);
            ctrlVisitas2.addObserver((Observer) uivisitas2);
            uivisitas2.readJTable();
        }
        return uivisitas2;
    }

    public void setController(ControllerVisitas2 _ctrlVisitas2, ControllerPessoas _ctrlPessoas, ControllerResidencias _ctrlResidencias) {
        this.ctrlVisitas2 = _ctrlVisitas2;
        this.ctrlPessoas = _ctrlPessoas;
        this.ctrlResidencias = _ctrlResidencias;
    }

    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) tblList3.getModel();
        modelo.setNumRows(0);
        DAOVisitas2 vDAO = new DAOVisitas2();

        for (Visitas2 p : vDAO.getAll()) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getVisita(),
                p.getRg(),
                p.getCpf(),
                p.getTelefone(),
                p.getCelular(),
                p.getResidencias_id(),
                p.getPessoas_id(),
                p.getVeiculo(),
                p.getPlaca(),
                p.getCor(),
                p.getAdmin_id(),
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

    public void setVisita(Visitas2 v) {
        this.visitas2 = v;
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

        txtVisita.setEnabled(false);
        txtRg.setEnabled(false);
        txtCpf.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtCelular.setEnabled(false);
        txtVeiculo.setEnabled(false);
        txtPlaca.setEnabled(false);
        txtCor.setEnabled(false);
    }

    public void novo() {
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(false);
        btnSaida.setEnabled(false);
        btnCancelar.setEnabled(true);

        cmbResidencia.setEnabled(true);
        cmbPessoas.setEnabled(true);

        txtVisita.setEnabled(true);
        txtRg.setEnabled(true);
        txtCpf.setEnabled(true);
        txtTelefone.setEnabled(true);
        txtCelular.setEnabled(true);
        txtVeiculo.setEnabled(true);
        txtPlaca.setEnabled(true);
        txtCor.setEnabled(true);
    }

    public void excluir() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnSaida.setEnabled(false);
        btnCancelar.setEnabled(true);

        cmbResidencia.setEnabled(false);
        cmbPessoas.setEnabled(false);
    }

    public void tabela() {
        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(true);
        btnSaida.setEnabled(true);
        btnCancelar.setEnabled(true);

        cmbResidencia.setEnabled(false);
        cmbPessoas.setEnabled(false);
        cmbResidencia.removeAllItems();
        cmbPessoas.removeAllItems();

        txtVisita.setEnabled(false);
        txtRg.setEnabled(false);
        txtCpf.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtCelular.setEnabled(false);
        txtVeiculo.setEnabled(false);
        txtPlaca.setEnabled(false);
        txtCor.setEnabled(false);
    }

    public void populaCampos() {
        txtVisita.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 1).toString());
        txtRg.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 2).toString());
        txtCpf.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 3).toString());
        txtTelefone.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 4).toString());
        txtCelular.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 5).toString());
        txtResidencia.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 6).toString());
        txtMorador.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 7).toString());
        txtVeiculo.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 8).toString());
        txtPlaca.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 9).toString());
        txtCor.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 10).toString());

        txtEntrada.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 12).toString());

    }

    public void limpaCampos() {
        txtEntrada.setText("");
        txtVisita.setText("");
        txtRg.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtVeiculo.setText("");
        txtPlaca.setText("");
        txtCor.setText("");
    }

    public void loadAllCombobox() {
        loadCmbResidencias();//Combo Residencias
        loadCmbPessoas();//Combo Pessoas
    }

    public void removeAllCombobox() {
        cmbResidencia.removeAllItems();
        cmbPessoas.removeAllItems();
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
        txtVisita = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        txtRg = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbResidencia = new javax.swing.JComboBox<>();
        cmbPessoas = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        txtVeiculo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JFormattedTextField();
        txtCor = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEntrada = new javax.swing.JLabel();
        btnSaida = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblList3 = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtAdmin = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtResidencia = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMorador = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        setTitle("CADASTRO DE VISITAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Insira os dados da visíta abaixo!"));

        jLabel1.setText("NOME: *");

        jLabel2.setText("RG:");

        jLabel3.setText("CPF:");

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

        jLabel5.setText("CELULAR:");

        jLabel4.setText("TELEFONE:");

        jLabel6.setText("RESIDÊNCIA: *");

        jLabel7.setText("MORADOR: *");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Insira os dados do veículo abaixo!"));

        jLabel8.setText("NOME/MODELO: *");

        jLabel10.setText("PLACA:");

        jLabel11.setText("COR:");

        try {
            txtPlaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setText("ENTRADA:");

        txtEntrada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSaida.setText("Saída");
        btnSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaida)
                        .addGap(137, 137, 137))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaida)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );

        tblList3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblList3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VISITA", "RG", "CPF", "TELEFONE", "CELULAR", "RESIDENCIA", "MORADOR", "VEICULO", "PLACA", "COR", "ADMIN", "ENTRADA", "SAIDA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
                tblList3clica(evt);
            }
        });
        jScrollPane4.setViewportView(tblList3);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

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

        txtAdmin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("ADMINISTRADOR");

        jLabel12.setText("RESIDÊNCIA:");

        txtResidencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("MORADOR:");

        txtMorador.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
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
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMorador, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvar)
                            .addComponent(btnExcluir)
                            .addComponent(btnNovo)
                            .addComponent(btnCancelar)))
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtResidencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMorador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaidaActionPerformed
        String admin = "ADMINISTRADOR";

        String timeStamp = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        txtEntrada.setText(tblList3.getValueAt(tblList3.getSelectedRow(), 12).toString());
        int selected = this.tblList3.getSelectedRow();
        if (selected < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um registro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            int id = (int) this.tblList3.getValueAt(selected, 0);

            this.visitas2 = new Visitas2();
            this.visitas2.setId(id);
            this.visitas2.setVisita(txtVisita.getText());
            this.visitas2.setRg(txtRg.getText());
            this.visitas2.setCpf(txtCpf.getText());
            this.visitas2.setTelefone(txtTelefone.getText());
            this.visitas2.setCelular(txtCelular.getText());
            this.visitas2.setResidencias_id(Integer.parseInt(txtResidencia.getText()));
            this.visitas2.setPessoas_id(Integer.parseInt(txtMorador.getText()));
            this.visitas2.setVeiculo(txtVeiculo.getText());
            this.visitas2.setPlaca(txtPlaca.getText());
            this.visitas2.setCor(txtCor.getText());
            this.visitas2.setAdmin_id(admin);
            this.visitas2.setEntrada(txtEntrada.getText());
            this.visitas2.setSaida(timeStamp);

            ctrlVisitas2.updateVisitas(this.visitas2);
            readJTable();
            limpaCampos();
            padrao();
            JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSaidaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        padrao();
        removeAllCombobox();
        tblList3.getSelectionModel().clearSelection();//Desfaz uma seleção da tabela
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblList3MouseClicked
        limpaCampos();
        populaCampos();
        tabela();
    }//GEN-LAST:event_tblList3MouseClicked

    private void tblList3clica(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblList3clica
        this.tblList3.editingCanceled(null);
    }//GEN-LAST:event_tblList3clica

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        novo();
        limpaCampos();
        loadAllCombobox();
        tblList3.getSelectionModel().clearSelection();//Desfaz uma seleção da tabela
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String dataEntrada = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String saida = null;
        String admin = "ADMINISTRADOR";

        Residencias residencias = (Residencias) this.cmbResidencia.getSelectedItem();
        Pessoas pessoas = (Pessoas) this.cmbPessoas.getSelectedItem();
        if (txtVisita.getText().trim().equals("") && txtVeiculo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Campo NOME* e Campo VEÍCULO*: Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            if (this.visitas2 == null) {
                ctrlVisitas2.insertVisitas(txtVisita.getText(), txtRg.getText(), txtCpf.getText(), txtTelefone.getText(), txtCelular.getText(), residencias.getId(), pessoas.getId(), txtVeiculo.getText(), txtPlaca.getText(), txtCor.getText(), admin, dataEntrada, saida);
                readJTable();
                limpaCampos();
                removeAllCombobox();
                padrao();
                JOptionPane.showMessageDialog(rootPane, "Salvo com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.visitas2.setVisita(txtVisita.getText());
                this.visitas2.setRg(txtRg.getText());
                this.visitas2.setCpf(txtCpf.getText());
                this.visitas2.setTelefone(txtTelefone.getText());
                this.visitas2.setCelular(txtCelular.getText());
                this.visitas2.setResidencias_id(residencias.getId());
                this.visitas2.setPessoas_id(pessoas.getId());
                this.visitas2.setVeiculo(txtVeiculo.getText());
                this.visitas2.setPlaca(txtPlaca.getText());
                this.visitas2.setCor(txtCor.getText());
                this.visitas2.setAdmin_id(admin);
                this.visitas2.setEntrada(dataEntrada);
                //this.visitas2.setSaida(saida);

                ctrlVisitas2.updateVisitas(this.visitas2);
                JOptionPane.showMessageDialog(rootPane, "Erro, tente novamente!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
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
            this.ctrlVisitas2.deletaVisitas(id);
            readJTable();
            limpaCampos();
            excluir();
            JOptionPane.showMessageDialog(rootPane, "Deletado com sucesso!", "INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSaida;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Pessoas> cmbPessoas;
    private javax.swing.JComboBox<Residencias> cmbResidencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblList3;
    private javax.swing.JLabel txtAdmin;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCor;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JLabel txtEntrada;
    private javax.swing.JLabel txtMorador;
    private javax.swing.JFormattedTextField txtPlaca;
    private javax.swing.JLabel txtResidencia;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtVeiculo;
    private javax.swing.JTextField txtVisita;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        readJTable();
    }
}
