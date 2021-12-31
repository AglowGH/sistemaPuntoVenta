/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cortes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CorteX 
{
    private String cajero = "UNKNOWN";
    
    public CorteX()
    {
        
    }
    
    public void crearCorte()
    {
        String titulo = "***REFACCIONARIA GJ***";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fechaHora = dtf.format(LocalDateTime.now());
        String[] ingresosEgresos = recuperarIngresosEgresos();
        String[] tickets = recuperarTickets();
        int numeroCorte = Integer.parseInt(ingresosEgresos[8]);
        
        try{
         FileWriter archivo = new FileWriter(new File("corte_X_" + numeroCorte + ".txt"));
         
         archivo.write(titulo + "\n");
         archivo.append(cajero + "\n");
         archivo.append("Fecha y Hora:" + fechaHora + "\n");
         archivo.append("Corte X : " + numeroCorte + "\n");
         
         
         
         archivo.close();
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error al crear el ticket.");
        }
        
    }
    
    public void leerCorte()
    {
        
    }
    
    private String[] recuperarTickets()
    {
        int numeroTickets = contarTickets();
        String tickets[] = new String[numeroTickets];
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root","");
            PreparedStatement ps = connection.prepareStatement("select * from ventas");
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next())
            {
                tickets[i] = rs.getString("NUMERO_TICKET") + " " +rs.getString("VENTA");
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return tickets;
    }
    
    private int contarTickets()
    {
        int numeroTickets = 0;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root","");
            PreparedStatement ps = connection.prepareStatement("select * from ventas");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                numeroTickets++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return numeroTickets;
    }
    
    //La información que se recupera del siguiente método proviene de la table dinero de la base de datos
    
    private String[] recuperarIngresosEgresos()
    {
        String columnas[] = new String[9];
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root","");
            PreparedStatement ps = connection.prepareStatement("select * from dinero where ID = 1");
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                //INGRESOS
                columnas[0] = rs.getString("INICIAL_DIA");
                columnas[1] = rs.getString("PAGO_CLIENTES");
                columnas[2] = rs.getString("VENTA");
                columnas[3] = rs.getString("OTROS");
                //EGRESOS
                columnas[4] = rs.getString("DEVOLUCIONES");
                columnas[5] = rs.getString("PAGO_PROVEDORES");
                columnas[6] = rs.getString("PAGO_SERVICIOS");
                columnas[7] = rs.getString("OTRO_PAGOS");
                //NUMERO DE CORTE
                columnas[8] = rs.getString("ULTIMO_CORTEX");
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return columnas;
    }
}
