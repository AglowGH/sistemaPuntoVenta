/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basedatos;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rick
 */
public class ActualizarInfo
{
    private final String usuario = "root";
    private final String password = "A1b2C3";
    private final String connection = "jdbc:mysql://localhost/refaccionaria";
    
    public ActualizarInfo(){
        
    }
    
    
    public void actualizarInventarioGeneral(DefaultTableModel modelo)throws SQLException
    {
        int numeroElementos = modelo.getRowCount();

        Connection cn = DriverManager.getConnection(connection,usuario,password);
        for(int i=0;i<numeroElementos;i++)
        {
            String codigo = "'" + String.valueOf(modelo.getValueAt(i,0)) + "'";
            PreparedStatement ps = cn.prepareStatement("UPDATE productos SET EXISTENCIA = " + String.valueOf(modelo.getValueAt(i,3)) + " WHERE CÓDIGO = " + codigo);
            ps.executeUpdate();
        }       
    }
    
    public void recibirProductoProvedor(DefaultTableModel modelo)throws SQLException
    {
        int nFilas = modelo.getRowCount();
        double precioActual;
        double existencias[] = new double[nFilas];
        double existencia;
        
        Connection cn = DriverManager.getConnection(connection,usuario,password);
            
            for(int i=0;i<nFilas;i++)
            {
                PreparedStatement pst = cn.prepareStatement("select * from productos where CÓDIGO = '" + String.valueOf(modelo.getValueAt(i,1))+"'");
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                    existencias[i] = Double.parseDouble(rs.getString("EXISTENCIA"));
            }
            
            for(int i=0; i<nFilas ;i++)
            {
                PreparedStatement ps1 = cn.prepareStatement("update productos set EXISTENCIA = ?, PRECIO_DE_COMPRA = ? where CÓDIGO = '" + String.valueOf(modelo.getValueAt(i,1))+"'");

                precioActual = Double.parseDouble(String.valueOf(modelo.getValueAt(i, 3)));
                existencia = existencias[i] + Double.parseDouble(String.valueOf(modelo.getValueAt(i, 0)));

                ps1.setString(1,String.valueOf(existencia));
                ps1.setString(2,String.valueOf(precioActual));
                ps1.executeUpdate();
            }
    }
        
    public void guardarProductoNuevo(String datos[])throws SQLException
    {
        Connection cn = DriverManager.getConnection(connection,usuario,password);
        PreparedStatement pst = cn.prepareStatement("INSERT INTO productos VALUES(?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1,datos[0]);
        pst.setString(2,datos[1]);
        pst.setString(3,datos[2]);
        pst.setString(4,datos[3]);
        pst.setString(5,datos[4]);
        pst.setString(6,datos[5]);
        pst.setString(7,datos[6]);
        pst.setString(8,datos[7]);
        pst.setString(9,datos[8]);
        pst.setString(10,datos[9]);

        pst.executeUpdate();
    }
    
    public void eliminarProducto(String codigo)throws SQLException
    {
        Connection cn = DriverManager.getConnection(connection,usuario,password);
        PreparedStatement ps = cn.prepareStatement("delete from productos where CÓDIGO = '" + codigo+"'");
        ps.executeUpdate();
    }
    
    public void actualizarInfoTodosLosProductos(DefaultTableModel modelo)throws SQLException
    {
        int rows = modelo.getRowCount();
        Connection cn = DriverManager.getConnection(connection,usuario,password);
        for(int i = 0; i < rows;i++)
        {
            String id = String.valueOf(modelo.getValueAt(i,0));
            PreparedStatement ps = cn.prepareStatement("UPDATE productos SET NOMBRE = ?, DESCRIPCIÓN = ?, CÓDIGO = ?, `% GANANCIA` = ?, `% IMPUESTOS` = ?, PRECIO_DE_COMPRA = ?, EXISTENCIA = ?, INVENTARIO = ?, CANTIDAD_MINIMA = ? WHERE ID = " + id);
            
            ps.setString(1,String.valueOf(modelo.getValueAt(i,1)));
            ps.setString(2,String.valueOf(modelo.getValueAt(i,2)));
            ps.setString(3,String.valueOf(modelo.getValueAt(i,3)));
            ps.setString(4,String.valueOf(modelo.getValueAt(i,4)));
            ps.setString(5,String.valueOf(modelo.getValueAt(i,5)));
            ps.setString(6,String.valueOf(modelo.getValueAt(i,6)));
            ps.setString(7,String.valueOf(modelo.getValueAt(i,7)));
            ps.setString(8,String.valueOf(modelo.getValueAt(i,8)));
            ps.setString(9,String.valueOf(modelo.getValueAt(i,9)));
            
            ps.executeUpdate();
        }
    }
}
