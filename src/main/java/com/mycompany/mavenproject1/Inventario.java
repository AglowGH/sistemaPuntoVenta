/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Inventario extends javax.swing.JFrame {

    /**
     * Creates new form Inventario
     */
    public Inventario() {
        actualizarInfo();
        initComponents();
    }

    private void actualizarInfo()
    {
        modelo3.addColumn("Código");
        modelo3.addColumn("Nombre");
        modelo3.addColumn("Existencia");
        modelo3.addColumn("Cantida Miníma");
        
        actualizarAlerta("Todas las alertas");
        
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("Código");
        modelo2.addColumn("Nombre");
        modelo2.addColumn("Costo");
        modelo2.addColumn("Total");
        
        try{
           modelo.addColumn("Código");
           modelo.addColumn("Nombre");
           modelo.addColumn("Existencia");
           modelo.addColumn("Inventario");
           modelo.addColumn("Cantidad Minima");
           
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria", "root","");
           String sql = "SELECT CÓDIGO, NOMBRE, EXISTENCIA, INVENTARIO, CANTIDAD_MINIMA FROM productos  ";
           PreparedStatement ps = connection.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           ResultSetMetaData rsMD = rs.getMetaData();
           int nColumnas = rsMD.getColumnCount();
           //System.out.println(nColumnas);
           while(rs.next())
           {
               Object[] fila = new Object[nColumnas];
               for(int i=1;i<=nColumnas;i++)
               {
                   fila[i-1] = rs.getObject(i);
               }
               modelo.addRow(fila);
           }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void actualizarAlerta(String tipo_alerta)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root","");
            //String sql = "select CÓDIGO, NOMBRE, EXISTENCIA, CANTIDAD_MINIMA from productos";
            String sql ="select * from productos";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int nColumnas = 4;
            
            if(tipo_alerta.equals("Todas las alertas"))
            {
                while(rs.next())
                {
                    String[] fila = new String[nColumnas];
                    fila[0] = rs.getString("CÓDIGO");
                    fila[1] = rs.getString("NOMBRE");
                    fila[2] = rs.getString("EXISTENCIA");
                    fila[3] = rs.getString("CANTIDAD_MINIMA");
                    
                    double existencia = Double.parseDouble(fila[2]);
                    double minimo = Double.parseDouble(fila[3]);
                    
                    if(existencia < minimo)
                    {
                        modelo3.addRow(fila);
                    }
                    
                }
            }else if(tipo_alerta.equals("Sin mercancia"))
            {
                while(rs.next())
                {
                    String[] fila = new String[nColumnas];
                    fila[0] = rs.getString("CÓDIGO");
                    fila[1] = rs.getString("NOMBRE");
                    fila[2] = rs.getString("EXISTENCIA");
                    fila[3] = rs.getString("CANTIDAD_MINIMA");
                    
                    double existencia = Double.parseDouble(fila[2]);
                    
                    if(existencia <= 0)
                    {
                        modelo3.addRow(fila);
                    }
                    
                }
                
            }else if(tipo_alerta.equals("Por acabar"))
            {
                while(rs.next())
                {
                    String[] fila = new String[nColumnas];
                    fila[0] = rs.getString("CÓDIGO");
                    fila[1] = rs.getString("NOMBRE");
                    fila[2] = rs.getString("EXISTENCIA");
                    fila[3] = rs.getString("CANTIDAD_MINIMA");
                    
                    double existencia = Double.parseDouble(fila[2]);
                    double minimo = Double.parseDouble(fila[3]);
                    
                    if(existencia < minimo && existencia >0)
                    {
                        modelo3.addRow(fila);
                    }
                    
                }
            }
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 768));

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel2.setMinimumSize(new java.awt.Dimension(1000, 700));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 700));

        jTable1.setModel(modelo);
        jTable1.setRowHeight(26);
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("Eliminar Producto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(398, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButton4)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos", jPanel2);

        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 700));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 700));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripción:");

        jLabel3.setText("Código de barras:");

        jLabel4.setText("Porcentaje de ganancia:");

        jLabel5.setText("Porcentage de impuestos:");

        jLabel6.setText("Precio provedor:");

        jLabel7.setText("Costo a cliente:");

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField4.setText("0");
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.setText("0");
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.setText("0");
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel8.setText("0");

        jLabel13.setText("Cantidad Minima:");

        jTextField9.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGap(34, 34, 34))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField6)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField9)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addContainerGap(546, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alta Producto", jPanel1);

        jLabel9.setText("Tipo de Entrada:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los productos", "Por provedor" }));

        jTable2.setModel(modelo2);
        jScrollPane2.setViewportView(jTable2);

        jLabel10.setText("Provedores:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Código:");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel12.setText("Total:");

        jTextField8.setText("0");
        jTextField8.setEnabled(false);

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))))
                .addContainerGap(536, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jButton3)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Recibir Producto", jPanel3);

        jLabel14.setText("Alcance:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los productos" }));

        jLabel15.setText("Tipo de alerta:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas las alertas", "Sin mercancia", "Por acabar" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jTable3.setModel(modelo3);
        jScrollPane3.setViewportView(jTable3);

        jButton5.setText("Imprimir");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(532, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alertas", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addGap(478, 478, 478))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int nFilas = jTable2.getRowCount();
        if(nFilas == 0)
        {
            JOptionPane.showMessageDialog(null,"La tabla esta vacia.");
            return;
        }

        double precioActual;
        double existencias[];
        double existencia;
        existencias = actualizarExistencias();

        try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root","");
            for(int i=0; i<nFilas ;i++)
            {
                PreparedStatement ps1 = connection.prepareStatement("update productos set EXISTENCIA = ?, PRECIO_DE_COMPRA = ? where CÓDIGO = " + String.valueOf(modelo2.getValueAt(i,1)));

                precioActual = Double.parseDouble(String.valueOf(modelo2.getValueAt(i, 3)));
                existencia = existencias[i] + Double.parseDouble(String.valueOf(modelo2.getValueAt(i, 0)));

                ps1.setString(1,String.valueOf(existencia));
                ps1.setString(2,String.valueOf(precioActual));
                ps1.executeUpdate();
            }

        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
        try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria", "root","");
            PreparedStatement pst = connection.prepareStatement("select * from productos where CÓDIGO = ?");
            pst.setString(1,jTextField7.getText().trim());
            ResultSet rs = pst.executeQuery();

            if(rs.next())
            {
                String[] nuevoP = new String[5];
                nuevoP[0] = "1";
                nuevoP[1] = rs.getString("CÓDIGO");
                nuevoP[2] = rs.getString("NOMBRE");
                nuevoP[3] = rs.getString("PRECIO_DE_COMPRA");
                nuevoP[4] = rs.getString("PRECIO_DE_COMPRA");

                modelo2.addRow(nuevoP);
                jTextField7.setText("");

                double total = Double.parseDouble(jTextField8.getText());
                total += Double.parseDouble(rs.getString("PRECIO_DE_COMPRA"));
                jTextField8.setText(String.valueOf(total));
            }else
            {
                JOptionPane.showMessageDialog(null,"Producto no encontardo!!!");
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());

        }
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria", "root","");
            PreparedStatement pst = connection.prepareStatement("insert into productos values(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,"0");
            pst.setString(2,jTextField1.getText().trim());
            pst.setString(3,jTextField2.getText().trim());
            pst.setString(4,jTextField3.getText().trim());
            pst.setString(5,jTextField4.getText().trim());
            pst.setString(6,jTextField5.getText().trim());
            pst.setString(7,jTextField6.getText().trim());
            pst.setString(8,"0");
            pst.setString(9,"0");
            pst.setString(10,jTextField9.getText().trim());

            pst.executeUpdate();

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField9.setText("");

            //initComponents();
            Home home = new Home();
            home.setVisible(true);
            dispose();

        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        if(i == -1)
        {
            JOptionPane.showMessageDialog(null,"Selecciona un producto!!!!");
            return;
        }
        String codigo = String.valueOf(modelo.getValueAt(i,0));
        modelo.removeRow(i);
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root","");
            PreparedStatement ps = connection.prepareStatement("delete from productos where CÓDIGO = " + codigo);
            ps.executeUpdate();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
        double precio = calcularPrecioCliente(Double.parseDouble(jTextField6.getText()),Double.parseDouble(jTextField4.getText()),Double.parseDouble(jTextField5.getText()));
        if(precio <= 0)
        {
            //JOptionPane.showMessageDialog(null, "Checa tus datos!!!!");
            return;
        }
        jLabel8.setText(String.valueOf(precio));
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        double precio = calcularPrecioCliente(Double.parseDouble(jTextField6.getText()),Double.parseDouble(jTextField4.getText()),Double.parseDouble(jTextField5.getText()));
        if(precio <= 0)
        {
            //JOptionPane.showMessageDialog(null, "Checa tus datos!!!!");
            return;
        }
        jLabel8.setText(String.valueOf(precio));
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
        double precio = calcularPrecioCliente(Double.parseDouble(jTextField6.getText()),Double.parseDouble(jTextField4.getText()),Double.parseDouble(jTextField5.getText()));
        if(precio <= 0)
        {
            //JOptionPane.showMessageDialog(null, "Checa tus datos!!!!");
            return;
        }
        jLabel8.setText(String.valueOf(precio));
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        // TODO add your handling code here:
        double precio = calcularPrecioCliente(Double.parseDouble(jTextField6.getText()),Double.parseDouble(jTextField4.getText()),Double.parseDouble(jTextField5.getText()));
        if(precio <= 0)
        {
            //JOptionPane.showMessageDialog(null, "Checa tus datos!!!!");
            return;
        }
        jLabel8.setText(String.valueOf(precio));
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        // TODO add your handling code here:
        double precio = calcularPrecioCliente(Double.parseDouble(jTextField6.getText()),Double.parseDouble(jTextField4.getText()),Double.parseDouble(jTextField5.getText()));
        if(precio <= 0)
        {
            //JOptionPane.showMessageDialog(null, "Checa tus datos!!!!");
            return;
        }
        jLabel8.setText(String.valueOf(precio));
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
        // TODO add your handling code here:
        double precio = calcularPrecioCliente(Double.parseDouble(jTextField6.getText()),Double.parseDouble(jTextField4.getText()),Double.parseDouble(jTextField5.getText()));
        if(precio <= 0)
        {
            //JOptionPane.showMessageDialog(null, "Checa tus datos!!!!");
            return;
        }
        jLabel8.setText(String.valueOf(precio));
    }//GEN-LAST:event_jTextField6FocusLost

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        System.out.println(jComboBox4.getSelectedItem());
        String seleccion = String.valueOf(jComboBox4.getSelectedItem());
        if("Todas las alertas".equals(seleccion))
        {
            modelo3.setRowCount(0);
            actualizarAlerta(seleccion);
        }else if("Sin mercancia".equals(seleccion))
        {
            modelo3.setRowCount(0);
            actualizarAlerta(seleccion);
        }else if("Por acabar".equals(seleccion))
        {
            modelo3.setRowCount(0);
            actualizarAlerta(seleccion);
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    
    private double calcularPrecioCliente(double precioProvedor,double ganancia,double impuestos)
    {
        if(ganancia >= 100)
        {
            return 0;
        }
        double porcentajeGanancia = ganancia/100;
        double porcentajeImpuesto = (impuestos/100) + 1;
        double precio = precioProvedor/(1 - porcentajeGanancia);
        precio *= porcentajeImpuesto;
        precio = Math.round(precio*100)/100;
        return precio;
    }
    private double[] actualizarExistencias()
    {
        int nFilas = jTable2.getRowCount();
        double existencias[] = new double[nFilas];
        
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root","");
         
            for(int i=0;i<nFilas;i++)
            {
                PreparedStatement pst = connection.prepareStatement("select * from productos where CÓDIGO = " + String.valueOf(modelo2.getValueAt(i,1)));
                ResultSet rs = pst.executeQuery();
                //rs.next();
                if(rs.next())
                    existencias[i] = Double.parseDouble(rs.getString("EXISTENCIA"));
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return existencias;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    private DefaultTableModel modelo = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row,int column)
        {
            return false;
        }
    };
    private final DefaultTableModel modelo2 = new DefaultTableModel()
    {
        @Override
        public void setValueAt(Object aValue, int row, int column) {
            super.setValueAt(aValue, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            
            if(column < 4)
            {
                double cantdad = Double.parseDouble(String.valueOf(modelo2.getValueAt(row,0)));
                double precio = Double.parseDouble(String.valueOf(modelo2.getValueAt(row,3)));
                
                double total = cantdad * precio;
                double totalAnterior = Double.parseDouble(String.valueOf(getValueAt(row,4)));
                double totalCuenta = Double.parseDouble(jTextField8.getText());
                totalCuenta = totalCuenta - totalAnterior + total;
                jTextField8.setText(String.valueOf(totalCuenta));
                
                setValueAt(total,row,4);
            }
        }
        
        @Override
        public boolean isCellEditable(int row,int column)
        {
            if(column == 0 || column == 3)
                return true;
            return false;
        }
    };
    private DefaultTableModel modelo3 = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //return super.isCellEditable(row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            return false;
        }
        
    };
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}