/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinero;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rick
 */
public class Venta 
{
    private final String usuario = "root";
    private final String password = "A1b2C3";
    private final String connection = "jdbc:mysql://localhost/refaccionaria";
    
    public Venta()
    {
        
    }
    ///////////////////////////////////////////////////////////////CALCULOS/////////////////////////////////////////////////////////////////////////////////////////////
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
    
    public double calcularGanancia(double precioProvedor,double ganancia,double cantidad)
    {
        if(ganancia >= 100)
        {
            return 0;
        }
        double porcentajeGanancia = ganancia/100;
        double precio = cantidad * precioProvedor/(1 - porcentajeGanancia);
        precio = Math.round(precio*100)/100;
        precio -= (cantidad * precioProvedor);
        return precio;
    }
    
    public boolean esDoublePositivo(String valor)
    {
        try{
            double conversion = Double.parseDouble(valor);    
            return conversion >= 0;
        }catch(NumberFormatException e)
        {
            
        }
        return false;
    }
    
    public double calcularTotal(JTable jTable, DefaultTableModel modelo)
    {
        int nProductos = jTable.getRowCount();
        double total = 0;
        for(int i=0;i<nProductos;i++)
        {
            total += Double.parseDouble(String.valueOf(modelo.getValueAt(i, 4)));
        }
        return total;
    }
    ////////////////////////////////////////////////////////////QUERIES A BASES DE DATOS/////////////////////////////////////////////////////////////////////////////////////
    public String[] buscarProductoVenta(String codigo)
    {
        String arreglo[] = null;
        try
        {
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM productos WHERE CÓDIGO = " + "'" + codigo.trim() + "'");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                double precio = calcularPrecioCliente(Double.parseDouble(rs.getString("PRECIO_DE_COMPRA")),Double.parseDouble(rs.getString("% GANANCIA")),Double.parseDouble(rs.getString("% IMPUESTOS")));
                arreglo = new String[5];
                arreglo[0] = rs.getString("CÓDIGO");
                arreglo[1] = "1";
                arreglo[2] = rs.getString("NOMBRE");
                arreglo[3] = String.valueOf(precio);
                arreglo[4] = String.valueOf(precio);
                
                return arreglo;
            }
            
        }catch(SQLException e)
        {
            
        }
        
        return arreglo;
    }
    
    public DefaultTableModel buscarProductoConsulta(String buscar)
    {
        DefaultTableModel modeloBuscar = new DefaultTableModel();
        modeloBuscar.addColumn("Código");
        modeloBuscar.addColumn("Nombre");
        modeloBuscar.addColumn("Precio");
        modeloBuscar.addColumn("Existencia");
        modeloBuscar.addColumn("Descripción");
        try{
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps = cn.prepareStatement("select * from productos where CONCAT(NOMBRE,'',DESCRIPCIÓN,'',CÓDIGO) like '%" + buscar + "%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                double precio = calcularPrecioCliente(Double.parseDouble(rs.getString("PRECIO_DE_COMPRA")),Double.parseDouble(rs.getString("% GANANCIA")),Double.parseDouble(rs.getString("% IMPUESTOS")));
                String fila[] = {rs.getString("CÓDIGO"),rs.getString("NOMBRE"),String.valueOf(precio),rs.getString("EXISTENCIA"),rs.getString("DESCRIPCIÓN")};
                modeloBuscar.addRow(fila);
            }
        } catch (SQLException ex)
        {
           
        }
        return modeloBuscar;
    }
    /////////////////////////////////////////////////////////////////////////Métodos para consolidar una venta///////////////////////////////////////////////////////////////////
    
    public void descontarExistencias(DefaultTableModel modelo)
    {
        int nFilas = modelo.getRowCount();
            
        double existencias[] = new double[nFilas];
        
        try{
        
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            //Obtner existencias
            for(int i=0;i<nFilas;i++)
            {
                PreparedStatement ps = cn.prepareStatement("select * from productos where CÓDIGO = '" + String.valueOf(modelo.getValueAt(i,0)) + "'");
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    existencias[i] = Double.parseDouble(rs.getString("EXISTENCIA"));
            }
            //Cambiar existencias
            double existencia;
                
            for(int i=0; i<nFilas ;i++)
            {
                PreparedStatement ps = cn.prepareStatement("update productos set EXISTENCIA = ? where CÓDIGO = '" + String.valueOf(modelo.getValueAt(i,0)) + "'");

                existencia = existencias[i] - Double.parseDouble(String.valueOf(modelo.getValueAt(i, 1)));

                ps.setString(1,String.valueOf(existencia));
                ps.executeUpdate();
            }    
            }catch(SQLException e)
            {
                
            }
    }
    
    public void actualizarTickets(double total)
    {
        try
        {
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            
            //Obtener contador actual de tickets
            PreparedStatement ps = cn.prepareStatement("SELECT ULTIMO_TICKET FROM dinero");
            ResultSet rs = ps.executeQuery();
            int noAnterior = 1;
            if(rs.next())
            {
                noAnterior += Integer.parseInt(rs.getString("ULTIMO_TICKET"));
            }
            
            //Aumentar Contador de tickets
            ps = cn.prepareStatement("UPDATE dinero SET ULTIMO_TICKET = "+ noAnterior + " WHERE ID = 1");
            ps.executeUpdate();
            
            //Guardar ticket
            ps = cn.prepareStatement("INSERT INTO ventas(NUMERO_TICKET,VENTA) VALUES (" + String.valueOf(noAnterior) + "," + String.valueOf(total) + ")");
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    
    public void agregarGanancias(DefaultTableModel modelo)
    {
        try{
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps;
            ResultSet rs;
            double ganancia = 0;
            //Calcular ganancias de todos los productos vendidos
            for(int i = 0; i<modelo.getRowCount();i++)
            {
                double cantidad = Double.parseDouble(String.valueOf(modelo.getValueAt(i, 1)));
                String codigo = String.valueOf(modelo.getValueAt(i,0));
                
                ps = cn.prepareStatement("SELECT * FROM productos WHERE CÓDIGO = '" + codigo + "'");
                rs = ps.executeQuery();
                if(rs.next())
                {
                    ganancia += calcularGanancia(Double.parseDouble(rs.getString("PRECIO_DE_COMPRA")),Double.parseDouble(rs.getString("% GANANCIA")),cantidad);
                }
            }
            //Obtener las ganancias del dia
            ps = cn.prepareStatement("SELECT GANANCIA FROM dinero WHERE ID = 1");
            rs = ps.executeQuery();
            if(rs.next())
            {
                ganancia += Double.parseDouble(rs.getString("GANANCIA"));
            }
            //Actualizar las ganacias
            ps = cn.prepareStatement("update dinero set GANANCIA = " + String.valueOf(ganancia) + " where ID = 1");
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            
        }
    }
    
    public void agregarDinero(double total)
    {
        try
        {
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps;
            ResultSet rs;
            
            //Recuperar dinero que hay en caja actualmente
            ps = cn.prepareStatement("SELECT VENTA FROM dinero WHERE ID = 1");
            rs = ps.executeQuery();
            if(rs.next())
            {
                total += Double.parseDouble(rs.getString("VENTA"));
            }
            
            //Actualizar cantidad de dinero actual
            ps = cn.prepareStatement("UPDATE dinero set VENTA = " + String.valueOf(total) + " WHERE ID = 1");
            ps.executeUpdate();  
        }catch(SQLException e)
        {
            
        }
    }
    
    public void actualizarNumeroClientes()
    {
        try{
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps;
            ResultSet rs;
            int clientes = 1;
            
            //Recuperar numero de clientes
            ps = cn.prepareStatement("SELECT NoClientes FROM dinero WHERE ID = 1");
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                clientes += Integer.parseInt(rs.getString("NoClientes"));
            }
            
            //Actualizar numero de clientes
            ps = cn.prepareStatement("UPDATE dinero set NoClientes = " + String.valueOf(clientes) + " WHERE ID = 1");
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            
        }
    }
    
    /////////////////////////////////////////////////////////////////////Otros////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getUltimoTicket()
    {
        try{
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps = cn.prepareStatement("select ULTIMO_TICKET from dinero where ID = 1");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                String ultimoTicket = rs.getString("ULTIMO_TICKET");
                int numero = Integer.parseInt(ultimoTicket);
                return numero;
            }
        }catch(SQLException e)
        {
            
        }
        return -1;
    }
}
