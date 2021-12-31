/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cortes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Ticket 
{
    private String cajero = "UNKNOWN";
    private int numeroTicket;
    private DefaultTableModel modelo;
    private double total;
    private double cambio;
    private double pago;
    
    public Ticket(int numeroTicket,DefaultTableModel modelo,double total,double cambio,double pago)
    {
        this.numeroTicket = numeroTicket;
        this.modelo = modelo;
        this.total = total;//es el total de toda la venta
        this.cambio = cambio;
        this.pago = pago;
    }
    
    public void guardarTicket() throws IOException
    {
        String titulo = "***REFACCIONARIA GJ***";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fechaHora = dtf.format(LocalDateTime.now());
        //String ticket = String.valueOf(numeroTicket);
        String productos[] = obtenerProductos();
        //String total = String.valueOf(this.total);
        //String pago = String.valueOf(this.pago);
        //String cambio = String.valueOf(this.cambio);
        
        try{
         FileWriter archivo = new FileWriter(new File(""));
         
         archivo.write(titulo + "\n");
         archivo.append("Fecha y Hora:" + fechaHora + "\n");
         archivo.append("Ticket: " + numeroTicket + "\n");
         
         int nProductos = modelo.getRowCount();
         for(int i=0;i<nProductos;i++)
         {
            archivo.append(productos[i] + "\n");
         }
         
         archivo.append("Total: " + total + "\n");
         archivo.append("Pago: " + pago + "\n");
         archivo.append("Cambio: " + cambio + "\n");
         
         archivo.close();
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error al crear el ticket.");
        }
    }
    
    public void leerTicket()
    {
        
    }
    
    private String[] obtenerProductos()
    {
        int numeroProductos = modelo.getRowCount();
        String productos[] = new String[numeroProductos];
        
        String codigo;
        String unidades;
        String producto;
        String precioUnidad;
        String totalPorProducto;//es el total del costo del producto, es decir las unidades de dicho producto por el costo de una unidad
        
        
        for(int i=0; i < numeroProductos; i++)
        {
            codigo = String.valueOf(modelo.getValueAt(i,0));
            unidades = String.valueOf(modelo.getValueAt(i,1));
            producto = String.valueOf(modelo.getValueAt(i,2));
            precioUnidad = String.valueOf(modelo.getValueAt(i,3));
            totalPorProducto = String.valueOf(modelo.getValueAt(i,4));
            
            productos[i] = codigo + " " + unidades + " " + producto + " " + precioUnidad + " " + totalPorProducto;
        }
        return productos;
    }
}
