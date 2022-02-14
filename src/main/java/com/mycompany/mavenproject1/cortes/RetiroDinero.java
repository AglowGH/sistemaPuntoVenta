/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cortes;

import impresion.Impresora;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author rick
 */
public class RetiroDinero 
{
    private String user;
    private String motivo;
    private double total;
    private Impresora impresora;
    
    public RetiroDinero(String usuario,String razon,double pago)
    {
        user = usuario;
        motivo = razon;
        crearCarpeta();
        total = pago;
        impresora = new Impresora();
    }
    
    private void crearCarpeta()
    {
        File carpeta = new File("retiros");
        if(!carpeta.exists())
        {
            carpeta.mkdir();
        }
    }
    
    public void guardarComprobante()
    {
        String titulo = "***REFACCIONARIA GJ***";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fechaHora = dtf.format(LocalDateTime.now());
        Calendar calendario = Calendar.getInstance();
        int mes = calendario.get(Calendar.MONTH) + 1;
        String fecha = calendario.get(Calendar.DAY_OF_MONTH) + "-" + mes + "-" + calendario.get(Calendar.YEAR);
        String hora = calendario.get(Calendar.HOUR_OF_DAY) + "-" + calendario.get(Calendar.MINUTE) + "-" + calendario.get(Calendar.SECOND);
        
        try{
           FileWriter file = new FileWriter(new File("retiros/Retiro_"+fecha+"_"+hora+"_.txt"));
           file.write(titulo + "\n\n\n");
           file.append("Cajero: "+user + "\n");
           file.append("Fecha y hora:" + fechaHora + "\n");
           file.append("Motivo " + motivo + "\n\n\n\n\n");
           
           file.append("Total: $" + total + "\n\n\n\n\n");
           
           file.append("___________________________________\n");
           file.append("                FIRMA              ");
           
           file.close();
           
           impresora.imprimir("Retiro_"+fechaHora+"_.txt",1);
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
    }
}
