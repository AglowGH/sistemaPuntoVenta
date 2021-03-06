/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cortes;

import impresion.Impresora;
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
    private String password = "A1b2C3";
    private Impresora impresora;
    
    public CorteX(String cajero)
    {
        this.cajero = cajero;
        crearCarpeta();
        impresora = new Impresora();
    }
    private void crearCarpeta()
    {
        File carpeta = new File("Cortes_X");
        if(!carpeta.exists())
        {
            carpeta.mkdir();
        }
    }
    public void crearCorte()
    {
        String titulo = "***REFACCIONARIA GJ***";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fechaHora = dtf.format(LocalDateTime.now());
        String[] ingresosEgresos = recuperarIngresosEgresos();
        String[] tickets = recuperarTickets();
        int numeroCorte = Integer.parseInt(ingresosEgresos[8]) + 1;
        
        try{
         FileWriter archivo = new FileWriter(new File("Cortes_X/corte_X_" + numeroCorte + ".txt"));
         
         archivo.write(titulo + "\n");
         archivo.append("Cajero: " + cajero + "\n");
         archivo.append("Fecha y Hora:" + fechaHora + "\n");
         archivo.append("Corte X : " + numeroCorte + "\n");
         
         archivo.append("---------Ingresos--------------\n");
         archivo.append("Inicial día: $" + ingresosEgresos[0] +"\n");
         archivo.append("Pago de clientes: $" + ingresosEgresos[1] +"\n");
         archivo.append("Ventas: $" + ingresosEgresos[2] +"\n");
         archivo.append("Otros ingresos: $" + ingresosEgresos[3] +"\n");
         
         double totalI = Double.parseDouble(ingresosEgresos[0]) + Double.parseDouble(ingresosEgresos[1]) + Double.parseDouble(ingresosEgresos[2]) + Double.parseDouble(ingresosEgresos[3]);
         archivo.append("Total ingresos: $" + totalI + "\n");
         
         archivo.append("\n");
         archivo.append("---------Egresos-----------------\n");
         archivo.append("Devoluciones: $" + ingresosEgresos[4] +"\n");
         archivo.append("Pago provedores: $" + ingresosEgresos[5] +"\n");
         archivo.append("Servicios: $" + ingresosEgresos[6] +"\n");
         archivo.append("Otros pagos: $" + ingresosEgresos[7] +"\n");
         
         double totalE = Double.parseDouble(ingresosEgresos[4]) + Double.parseDouble(ingresosEgresos[5]) + Double.parseDouble(ingresosEgresos[6]) + Double.parseDouble(ingresosEgresos[7]);
         archivo.append("Total egresos: $" + totalE + "\n");
         
         archivo.append("\n");
         double total = totalI - totalE;
         archivo.append("Total en caja: $" + total + "\n");
         
         
         archivo.append("Tickets: \n");
         archivo.append("\n");
         
         for(String i:tickets)
         {
             archivo.append(i + "\n");
         }
         archivo.append("\n\n\n\n\nv8");
         archivo.close();
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error al crear el ticket.");
        }
        
        try {
            impresora.imprimir("Cortes_X\\corte_X_" + numeroCorte + ".txt",1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error al imprimir!!!!!");
        }
        actualizarContador(numeroCorte);
    }
    
    
    public void leerCorte()
    {
        
    }
    
    private String[] recuperarTickets()
    {
        int numeroTickets = contarTickets();
        String tickets[] = new String[numeroTickets];
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            PreparedStatement ps = connection.prepareStatement("select * from ventas");
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next())
            {
                tickets[i] = rs.getString("NUMERO_TICKET") + "  $" +rs.getString("VENTA");
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
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
    
    //Actualiza el contador de los cortes x
    private void actualizarContador(int corteActual)
    {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            PreparedStatement ps = connection.prepareStatement("update dinero set ULTIMO_CORTEX = ? where ID = 1");
            
            ps.setString(1,String.valueOf(corteActual));
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
