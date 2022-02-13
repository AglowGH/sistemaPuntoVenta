/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mavenproject1;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mycompany.mavenproject1.cortes.*;
import dinero.Venta;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author Usuario
 */
public class Caja extends javax.swing.JFrame {

    /**
     * Creates new form Caja
     */
    public Caja() {
        modelo.addColumn("Código");
        modelo.addColumn("Unidades");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio Unidad");
        modelo.addColumn("Precio");
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        setBounds(0,0, xSize,ySize);
    }
    
    public void showCaja(String user)
    {
        
        this.usuario = user;
        jTextField3.setText(user);
        jTextField2.requestFocus();
        setVisible(true);
    }

    private void cobrar(boolean omisionTeclaF5)
    {
        int nFila = jTable1.getRowCount();
        
        if(nFila == 0)
        {
            JOptionPane.showMessageDialog(null,"No hay productos para cobrar");
            return;
        }
        
        Cobro2 cobro = new Cobro2(this,true);
        double cambio = cobro.showDialog(venta.calcularTotal(jTable1,modelo),modelo,usuario,omisionTeclaF5);
        if(cambio != -1)
        {
            modelo.setRowCount(0);
            jTextField1.setText("0");
        }
        jTextField2.requestFocus();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setSize(new java.awt.Dimension(1024, 768));

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(modelo);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("0");
        jTextField1.setEnabled(false);

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Total:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jButton2.setText("Eliminar Producto");
        jButton2.setActionCommand("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar Venta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cobrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Más Opciones");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setText("Usuario:");

        jTextField3.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Home home = new Home(usuario);
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cobrar(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    
    private String checarSQLInjection(String input)
    {
        String newInput = input.replace('/',' ');
        newInput = newInput.replace('-',' ');
        newInput = newInput.replace('=',' ');
        newInput = newInput.replace('(',' ');
        newInput = newInput.replace(')',' ');
        newInput = newInput.replace('%',' ');
        newInput = newInput.replace("'","");
        newInput = newInput.replace(":","");
        return newInput;

    }
    
    private void agregarProducto()
    {
        String codigo = checarSQLInjection(jTextField2.getText().trim());
        String producto[] = venta.buscarProductoVenta(codigo);
        if(producto !=null)
        {
            modelo.addRow(producto);
            double total = Double.parseDouble(jTextField1.getText());
            total += Double.parseDouble(String.valueOf(producto[4]));
            jTextField1.setText(String.valueOf(total));
        }else
        {
            JOptionPane.showMessageDialog(this,"Producto no encontrado");
        }
        jTextField2.setText("");
    }
    
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        agregarProducto();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        if(i == -1)
        {
            JOptionPane.showMessageDialog(null,"Selecciona un producto!!!!");
            return;
        }
        double totalCuenta = Double.parseDouble(String.valueOf(jTextField1.getText()));
        double totalProducto = Double.parseDouble(String.valueOf(modelo.getValueAt(i,4)));
        totalCuenta -= totalProducto;
        jTextField1.setText(String.valueOf(totalCuenta));
        modelo.removeRow(i);
        jTextField2.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int nFilas = modelo.getRowCount();
        if(nFilas == -1)
        {
            JOptionPane.showMessageDialog(null, "La cuenta está vacía!!!!!");
            return;
        }
        for(int i=0; i < nFilas; i++)
        {
            modelo.removeRow(0);
        }
        jTextField1.setText("0");
        jTextField2.requestFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        Opciones masOpciones = new Opciones(this,true);
        int index = masOpciones.showDialog();
        
        if(index == 0)
        {
            LogInDialog log = new LogInDialog(this,true);
            String pass = log.showDialog("MONEY");
            if(pass == null)
            {
                return;
            }
            usuario = pass;
            jTextField3.setText(usuario);
            AdministracionTicket ticket = new AdministracionTicket();
            ticket.showFrame(pass);
            dispose();
            //JOptionPane.showMessageDialog(this,"Seguimos trabajando en ello!!");
        }
        
        if(index == 1)
        {
            LogInDialog log = new LogInDialog(this,true);
            String pass = log.showDialog("MONEY");
            if(pass == null)
            {
                return;
            }
            usuario = pass;
            jTextField3.setText(usuario);
            Retiro retiro = new Retiro(usuario);
            retiro.setVisible(true);
            dispose();
        }
        
        if(index == 2)
        {
            LogInDialog log = new LogInDialog(this,true);
            String pass = log.showDialog("CORTE");
            if(pass == null)
            {
                return;
            }
            usuario = pass;
            jTextField3.setText(usuario);
            CorteX corte = new CorteX(usuario);
            corte.crearCorte();
            return;
        }
        
        if(index == 3)
        {
            LogInDialog log = new LogInDialog(this,true);
            String pass = log.showDialog("CORTE");
            if(pass == null)
            {
                return;
            }
            usuario = pass;
            jTextField3.setText(usuario);
            CorteZ corte = new CorteZ(usuario);
            corte.crearCorte();
        }
        jTextField2.requestFocus();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_DOWN)
        {
            DefaultTableModel model = venta.buscarProductoConsulta(jTextField2.getText());
            BuscarProducto bp = new BuscarProducto(this,true);
            String seleccion = bp.showDialog(model);
            jTextField2.setText(seleccion);
            if(!seleccion.equals(""))
            {
                agregarProducto();
            }
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_F5)
        {
            cobrar(false);
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_F5)
        {
            cobrar(true);
        }
    }//GEN-LAST:event_jTable1KeyPressed

    
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
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja().setVisible(true);
            }
        });
    }

    private DefaultTableModel modelo = new DefaultTableModel()
    {
        @Override
        public void setValueAt(Object aValue, int row, int column) {
            
            if(column == 1 || column == 3)
            {
                if(!venta.esDoublePositivo(String.valueOf(aValue)))
                {
                    return;
                }
            }
            super.setValueAt(aValue, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            if(column < 4)
            {
                double cantdad = Double.parseDouble(String.valueOf(modelo.getValueAt(row,1)));
                double precio = Double.parseDouble(String.valueOf(modelo.getValueAt(row,3)));
                double total = cantdad * precio;
                double totalAnterior = Double.parseDouble(String.valueOf(getValueAt(row,4)));
                double totalCuenta = Double.parseDouble(jTextField1.getText());
                totalCuenta = totalCuenta - totalAnterior + total;
                jTextField1.setText(String.valueOf(totalCuenta));
                setValueAt(total,row,4);
            }
            
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            if(column == 1 /*|| column == 3*/)
            {
                return true;
            }
            return false;
        }
        
    };
    private String usuario = "UNKOWN";
    private final Venta venta = new Venta();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
