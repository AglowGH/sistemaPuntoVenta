/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cortes;

import impresion.Impresora;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CorteZ 
{
    private String cajero = "UNKNOWN";
    private String password = "A1b2C3";
    private Impresora impresora;
    
    public CorteZ(String cajero)
    {
        this.cajero = cajero;
        crearCarpeta();
        impresora = new Impresora();
    }
    private void crearCarpeta()
    {
        File carpeta = new File("Cortes_Z");
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
        double ganancias = recuperarGanancia();
        int numeroClientes = Integer.parseInt(recuperarNumeroClientes());
        double totalProducto = totalMercancia();
        
        try{
         FileWriter archivo = new FileWriter(new File("Cortes_Z\\corte_Z_" + numeroCorte + ".txt"));
         
         archivo.write(titulo + "\n");
         archivo.append("Cajero: " + cajero + "\n");
         archivo.append("Fecha y Hora:" + fechaHora + "\n");
         archivo.append("Corte Z : " + numeroCorte + "\n");
         
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
         
         archivo.append("------Información Adicional-----------------");
         archivo.append("\n\n\n");
         archivo.append("No. Clientes : " + numeroClientes + "\n");
         archivo.append("Valor de mercancia : $" + totalProducto + "\n");
         archivo.append("Ganancias por venta $" + ganancias + "\n");
         totalE -= Double.parseDouble(ingresosEgresos[4]);
         archivo.append("Egresos: $" + totalE + "\n" );
         total = ganancias - totalE;
         archivo.append("Total: $" + total+ "\n");
         archivo.append("\n\n\n\n\nv8");
         archivo.close();
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error al crear el ticket.");
        }
        
        try {
            impresora.imprimir("Cortes_Z\\corte_Z_" + numeroCorte + ".txt",1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error al imprimir!!!!!");
        }
        
        actualizarContador(numeroCorte);
        actualizarTablas();
    }
    
    
    public void leerCorte()
    {
        
    }
    
    private String recuperarNumeroClientes()
    {
        String noClientes = "";
        
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            PreparedStatement ps = connection.prepareStatement("select * from dinero where ID = 1");
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                noClientes = rs.getString("NoClientes");
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al recuperar clientes, error: " + e.getMessage());
        }
        
        return noClientes; 
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
                columnas[8] = rs.getString("ULTIMO_CORTEZ");
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
            PreparedStatement ps = connection.prepareStatement("update dinero set ULTIMO_CORTEZ = ? where ID = 1");
            
            ps.setString(1,String.valueOf(corteActual));
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void actualizarTablas()
    {
        //Limpiar la tabla de la base de datos que almacena los tickets de un día
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            PreparedStatement ps = connection.prepareStatement("delete from ventas");
            ps.executeUpdate();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        //Limpiar la informacion necesaria de la tabla dinero
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            PreparedStatement ps = connection.prepareStatement("update dinero set INICIAL_DIA = ?, PAGO_CLIENTES = ?, VENTA = ?, OTROS = ?, DEVOLUCIONES = ?, PAGO_PROVEDORES = ?, PAGO_SERVICIOS = ?, OTRO_PAGOS = ?, NoClientes = ?, GANANCIA = ? where ID = 1");
            ps.setString(1,"0");
            ps.setString(2,"0");
            ps.setString(3,"0");
            ps.setString(4,"0");
            ps.setString(5,"0");
            ps.setString(6,"0");
            ps.setString(7,"0");
            ps.setString(8,"0");
            ps.setString(9,"0");
            ps.setString(10,"0");
            ps.executeUpdate();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    private double recuperarGanancia()
    {
        double gananciaAnterior = 0;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            PreparedStatement ps = connection.prepareStatement("select GANANCIA from dinero where ID = 1");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                gananciaAnterior = Double.parseDouble(rs.getString("GANANCIA"));
            }
        }catch(SQLException e)
        {
            
        }
        return gananciaAnterior;
    }
    
    private double totalMercancia()
    {
        int totalProductos = 0;
        double monto = 0;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/refaccionaria","root",password);
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) As total from productos");
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                totalProductos = rs.getShort("total");
            }
            
            ps = connection.prepareStatement("select * from productos");
            rs = ps.executeQuery();
            
            for(int i =0;i<totalProductos;i++)
            {
                if(rs.next())
                {
                    double precioProvedor = Double.parseDouble(rs.getString("PRECIO_DE_COMPRA"));
                    double ganancia = Double.parseDouble(rs.getString("% GANANCIA"));
                    double impuestos = Double.parseDouble(rs.getString("% IMPUESTOS"));
                    double precio = calcularPrecioCliente(precioProvedor,ganancia,impuestos);
                    monto += Double.parseDouble(rs.getString("EXISTENCIA")) * precio;
                }
            }
            rs.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error al calcular mercancia.");
        }
        System.out.println(monto);
        return monto;
    }
    
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
}
