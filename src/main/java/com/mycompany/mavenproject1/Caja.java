/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mycompany.mavenproject1.cortes.*;
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
        setVisible(true);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int nFila = jTable1.getRowCount();
        
        if(nFila == 0)
        {
            JOptionPane.showMessageDialog(null,"No hay productos para cobrar");
            return;
        }
        
        Cobro2 cobro = new Cobro2(this,true);
        double cambio = cobro.showDialog(calcularTotal(),modelo,usuario);
        completarCompra(cambio);
    }//GEN-LAST:event_jButton4ActionPerformed

    private double calcularPrecioCliente(double precioProvedor,double ganancia,double impuestos)
    {
        if(ganancia >= 100)
        {
            return 0;
        }
        double porcentajeGanancia = ganancia/100;
        double porcentajeImpuesto = (impuestos/100) + 1;
        double precio = precioProvedor/(1 - porcentajeGanancia);
        precio = Math.round(precio*100)/100;
        precio *= porcentajeImpuesto;
        precio = Math.round(precio*100)/100;
        return precio;
    }
    
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
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria", "root",password);
            PreparedStatement pst = connection.prepareStatement("select * from productos where CÓDIGO = ?");
            String codigo = checarSQLInjection(jTextField2.getText().trim());
            pst.setString(1,codigo.trim());
            ResultSet rs = pst.executeQuery();
            double precio;

            if(rs.next())
            {
                String[] nuevoP = new String[5];
                nuevoP[0] = rs.getString("CÓDIGO");
                nuevoP[1] = "1";
                nuevoP[2] = rs.getString("NOMBRE");
                precio = calcularPrecioCliente(Double.parseDouble(rs.getString("PRECIO_DE_COMPRA")),Double.parseDouble(rs.getString("% GANANCIA")),Double.parseDouble(rs.getString("% IMPUESTOS")));
                nuevoP[3] = String.valueOf(precio);
                nuevoP[4] = String.valueOf(precio);

                modelo.addRow(nuevoP);
                jTextField2.setText("");
                
                double total = Double.parseDouble(jTextField1.getText());
                total += precio;//Double.parseDouble(rs.getString("PRECIO_DE_COMPRA"));
                jTextField1.setText(String.valueOf(total));
            }else
            {
                JOptionPane.showMessageDialog(null,"Producto no encontardo!!!");
                jTextField2.setText("");
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());

        }
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        LogInDialog log = new LogInDialog(this,true);
        String pass = log.showDialog();
        if(pass == null)
        {
            return;
        }
        usuario = pass;
        Opciones masOpciones = new Opciones(this,true);
        int index = masOpciones.showDialog();
        
        if(index == 0)
        {
            AdministracionTicket ticket = new AdministracionTicket();
            ticket.showFrame(pass);
            dispose();
            //JOptionPane.showMessageDialog(this,"Seguimos trabajando en ello!!");
        }
        
        if(index == 1)
        {
            Retiro retiro = new Retiro();
            retiro.setVisible(true);
            dispose();
        }
        
        if(index == 2)
        {
            //Corte x
            CorteX corte = new CorteX(usuario);
            corte.crearCorte();
            return;
        }
        
        if(index == 3)
        {
            //Corte z
            CorteZ corte = new CorteZ(usuario);
            corte.crearCorte();
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_DOWN)
        {
            DefaultTableModel model = buscarProducto(jTextField2.getText());
            BuscarProducto bp = new BuscarProducto(this,true);
            String seleccion = bp.showDialog(model);
            jTextField2.setText(seleccion);
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private DefaultTableModel buscarProducto(String buscar)
    {
        DefaultTableModel modeloBuscar = new DefaultTableModel();
        modeloBuscar.addColumn("Código");
        modeloBuscar.addColumn("Nombre");
        modeloBuscar.addColumn("Descripción");
        modeloBuscar.addColumn("Existencia");
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            PreparedStatement ps = connection.prepareStatement("select * from productos where CONCAT(NOMBRE,'',DESCRIPCIÓN,'',CÓDIGO) like '%" + buscar + "%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String fila[] = {rs.getString("CÓDIGO"),rs.getString("NOMBRE"),rs.getString("DESCRIPCIÓN"),rs.getString("EXISTENCIA")};
                modeloBuscar.addRow(fila);
            }
        } catch (SQLException ex)
        {
            //Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return modeloBuscar;
    }
    
    private double calcularTotal()
    {
        int nProductos = jTable1.getRowCount();
        double total = 0;
        for(int i=0;i<nProductos;i++)
        {
            total += Double.parseDouble(String.valueOf(modelo.getValueAt(i, 4)));
        }
        return total;
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
                if(!esDoublePositivo(String.valueOf(aValue)))
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
    private boolean esDoublePositivo(String valor)
    {
        try{
            double conversion = Double.parseDouble(valor);    
            return conversion >= 0;
        }catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return false;
    }
    public void completarCompra(double cambio)
    {
        if(cambio != -1)
        {
            int nFilas = modelo.getRowCount();
            
            double existencias[] = new double[nFilas];
        
            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
         
                for(int i=0;i<nFilas;i++)
                {
                    PreparedStatement pst = connection.prepareStatement("select * from productos where CÓDIGO = '" + String.valueOf(modelo.getValueAt(i,0)) + "'");
                    ResultSet rs = pst.executeQuery();
                    //rs.next();
                    if(rs.next())
                        existencias[i] = Double.parseDouble(rs.getString("EXISTENCIA"));
                }
            }catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            
            double existencia;
            try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            for(int i=0; i<nFilas ;i++)
            {
                PreparedStatement ps1 = connection.prepareStatement("update productos set EXISTENCIA = ? where CÓDIGO = '" + String.valueOf(modelo.getValueAt(i,0)) + "'");

                existencia = existencias[i] - Double.parseDouble(String.valueOf(modelo.getValueAt(i, 1)));

                ps1.setString(1,String.valueOf(existencia));
                ps1.executeUpdate();
            }

            }catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            
            for(int i=0; i < nFilas; i++)
            {
                modelo.removeRow(0);
            }
            jTextField1.setText("0");
            
        }
    }
    private String password = "A1b2C3";
    private String usuario = "UNKOWN";
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
