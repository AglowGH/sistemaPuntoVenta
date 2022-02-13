/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cortes;

import impresion.Impresora;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ImprimirInventario 
{
    private Impresora impresora;
    
    public ImprimirInventario()
    {
        crearCarpeta();
        impresora = new Impresora();
    }
    private void crearCarpeta()
    {
        File carpeta = new File("Impresion_Inventarios");
        if(!carpeta.exists())
        {
            carpeta.mkdir();
        }
    }
    public void recuperarInventario()
    {
        try{
            //Crear archivo
            FileWriter archivo = new FileWriter(new File("Impresion_Inventarios\\1.txt"));
            archivo.write("Impersion de inventario\n\n");
            //Establecer connection con la base de datos
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",pass);
            PreparedStatement ps = cn.prepareStatement("select * from productos");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                String cadena = rs.getString("CÓDIGO") + "  " +rs.getString("NOMBRE") + "________\n";
                archivo.append(cadena);
            }
            rs.close();
            archivo.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImprimirInventario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        //imprimirInventario();
        try {
            impresora.imprimir("Impresion_Inventarios\\1.txt",1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error al imprimir!!!!!");
        }
        
    }
    
    
    private String pass = "A1b2C3";
}
