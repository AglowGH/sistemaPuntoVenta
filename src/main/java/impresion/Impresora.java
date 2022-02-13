/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package impresion;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author rick
 */
public class Impresora 
{
    public Impresora()
    {
        
    }
    
    public void imprimir(String archivo,int numCopias) throws IOException
    {
        File file = new File(archivo);
        if(!file.exists())
        {
            throw new IOException("Archivo a imprimir no existe");
        }
        Desktop desktop = Desktop.getDesktop();
        for(int i=0;i<numCopias;i++)
        {
            desktop.print(file);
        }
    }
}
