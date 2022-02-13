/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cortes;

import impresion.Impresora;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
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
public class Ticket implements Printable
{
    private String cajero = "UNKNOWN";
    private int numeroTicket;
    private DefaultTableModel modelo;
    private double total;
    private double cambio;
    private double pago;
    private Impresora impresora;
    
    public Ticket(int numeroTicket,DefaultTableModel modelo,double total,double cambio,double pago,String cajero)
    {
        crearCarpeta();
        this.numeroTicket = numeroTicket;
        this.modelo = modelo;
        this.total = total;//es el total de toda la venta
        this.cambio = cambio;
        this.pago = pago;
        this.cajero = cajero;
        impresora = new Impresora();
    }
    
    private void crearCarpeta()
    {
        File carpeta = new File("tickets");
        if(!carpeta.exists())
        {
            carpeta.mkdir();
        }
    }
    
    public void guardarTicket()
    {
        String titulo = "***REFACCIONARIA GJ***";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fechaHora = dtf.format(LocalDateTime.now());
        String productos[] = obtenerProductos();
        
        try{
         FileWriter archivo = new FileWriter(new File("tickets\\ticket_" + numeroTicket + ".txt"));
         
         archivo.write(titulo + "\n\n\n");
         archivo.append("Cajero: " + cajero + "\n");
         archivo.append("Fecha y Hora:" + fechaHora + "\n");
         archivo.append("Ticket: " + numeroTicket + "\n");
         
         archivo.append("\n\n\n");
         archivo.append("Productos: \n\n");
         int nProductos = modelo.getRowCount();
         for(int i=0;i<nProductos;i++)
         {
            archivo.append(productos[i] + "\n");
         }
         archivo.append("\n\n\n");
         
         archivo.append("Total: $" + total + "\n");
         archivo.append("Pago: $" + pago + "\n");
         archivo.append("Cambio $: " + cambio + "\n");
         
         archivo.append("\n\n\n");
         archivo.append("¡Muchas gracias por su compra!");
         archivo.append("\n\n\nv8");
         archivo.close();
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error al crear el ticket.");
        }
        
        try
        {
            impresora.imprimir("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\mavenproject1\\tickets\\ticket_" + numeroTicket + ".txt",2);
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error al imprimir!!!!!");
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
            producto = producto.replace(" ","_");
            precioUnidad = String.valueOf(modelo.getValueAt(i,3));
            totalPorProducto = String.valueOf(modelo.getValueAt(i,4));
            
            productos[i] = codigo + " " + unidades + " " + producto + " $" + precioUnidad + " $" + totalPorProducto;
        }
        return productos;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException 
    {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
