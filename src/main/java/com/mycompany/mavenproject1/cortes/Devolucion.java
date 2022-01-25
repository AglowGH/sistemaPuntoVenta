/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cortes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Devolucion 
{
    String numeroTicket;
    public Devolucion(String numeroTicket)
    {
        this.numeroTicket = numeroTicket;
    }
    
    private boolean esDoublePositivo(String valor,double limit)
    {
        try{
            double conversion = Double.parseDouble(valor);    
            return conversion >= 0 && conversion <= limit;
        }catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return false;
    }
    
    public DefaultTableModel obtenerVentas()
    {

        DefaultTableModel modelo = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                if(column == 5)
                    return true;
                return false;
            }
            
            @Override
            public void setValueAt(Object aValue, int row, int column) 
            {
            
                if(column == 5)
                {
                    double limit = Double.parseDouble(String.valueOf(getValueAt(row,1)));
                    if(!esDoublePositivo(String.valueOf(aValue),limit))
                    {
                        return;
                    }
                }
                super.setValueAt(aValue, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                double totalDev = Double.parseDouble(String.valueOf(getValueAt(row,column))) * Double.parseDouble(String.valueOf(getValueAt(row,3)));
                super.setValueAt(totalDev,row,column+1);
            }
        };
        
        modelo.addColumn("Código");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio Unidad");
        modelo.addColumn("Total");
        modelo.addColumn("Devolver");
        modelo.addColumn("Total Devolución");
        
        try
        {
            File ticket = new File("tickets\\ticket_" + numeroTicket + ".txt");
            Scanner sc = new Scanner(ticket);
            
            boolean bandera = false;
            String linea;
            
            while(sc.hasNextLine())
            {
                linea = sc.nextLine();
                if(linea.contains("Productos: "))
                {
                    bandera = true;
                    continue;
                }
                if(linea.contains("Total: $"))
                {
                    sc.close();
                    return modelo;
                }
                
                if(bandera)
                {
                    String[] arreglo = recuperarDatos(linea);
                    if(arreglo != null)
                    {
                        modelo.addRow(arreglo);
                    }
                }
            }
            
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error al recuperrar información del ticket.");
        }
        
        return null;
    }
    
    public String[] recuperarDatos(String cadena)
    {
        int n = cadena.length();
        if(n == 0)
            return null;
        String[] arreglo = new String[7];
        arreglo[0] = "";
        arreglo[1] = "";
        arreglo[2] = "";
        arreglo[3] = "";
        arreglo[4] = "";
        arreglo[5] = "";
        arreglo[6] = "";
        
        //código de barras
        int contador = 0;
        while(cadena.charAt(contador) != 32)
        {
            arreglo[0] += Character.toString(cadena.charAt(contador));
            contador +=1;
        }
        //Unidades
        contador +=1;
        while(cadena.charAt(contador) != 32)
        {
            arreglo[1] += Character.toString(cadena.charAt(contador));
            contador +=1;
        }
        //nombre
        contador +=1;
        while(cadena.charAt(contador) != '$')
        {
            arreglo[2] += Character.toString(cadena.charAt(contador));
            contador +=1;
        }
        //precio por unidad
        contador +=1;
        while(cadena.charAt(contador) != '$')
        {
            arreglo[3] += Character.toString(cadena.charAt(contador));
            contador +=1;
        }
        //precio total
        contador +=1;
        while(contador<n)
        {
            arreglo[4] += Character.toString(cadena.charAt(contador));
            contador +=1;
        }
        
        arreglo[5] = "0";
        arreglo[6] = "0";
        
        return arreglo;
    }
    
    public void relizarDevolucion(String usuario,DefaultTableModel modelo,double total)
    {
        String titulo = "***REFACCIONARIA GJ***";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fechaHora = dtf.format(LocalDateTime.now());
        String productos[] = obtenerProductos(modelo);
        
        try{
         FileWriter archivo = new FileWriter(new File("tickets\\devolucion_" + numeroTicket + ".txt"));
         
         archivo.write(titulo + "\n\n\n");
         archivo.append("Autorizó: " + usuario + "\n");
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
         
         archivo.append("\n\n\n");
         archivo.append("¡........................!");
         archivo.append("\n\n\nv10");
         archivo.close();
        }catch(IOException e)
        {
            
        }
        
        
        imprimir("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\mavenproject1\\tickets\\devolucion_" + numeroTicket + ".txt");
    }
    
    private String[] obtenerProductos(DefaultTableModel modelo)
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
            unidades = String.valueOf(modelo.getValueAt(i,5));
            producto = String.valueOf(modelo.getValueAt(i,2));
            precioUnidad = String.valueOf(modelo.getValueAt(i,3));
            
            totalPorProducto = String.valueOf(modelo.getValueAt(i,6));
            
            productos[i] = codigo + " " + unidades + " " + producto + " $" + precioUnidad + " $" + totalPorProducto;
        }
        return productos;
    }

    private void imprimir(String archivo)
    {
        try{
            File file = new File(archivo);
            Desktop desktop = Desktop.getDesktop();
            desktop.print(file);
            desktop.print(file);
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error al imprimir");
        }
    }
}
