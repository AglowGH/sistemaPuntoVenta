/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basedatos;

import java.sql.*;
/**
 *
 * @author rick
 */
public class RecuperarInfo 
{
    private String usuario = "root";
    private String password = "A1b2C3";
    private String connection = "jdbc:mysql://localhost/refaccionaria";
    
    public RecuperarInfo()
    {
        
    }
    
    public String[][] recuperarInfoVentas()
    {
        String arreglo[][] = null;
        try{
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps = cn.prepareStatement("SELECT COUNT(*) FROM ventas");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int cantidad = Integer.parseInt(rs.getString("COUNT(*)"));
                int i =0;
                arreglo = new String[cantidad][3];
                ps = cn.prepareStatement("SELECT * FROM ventas");
                rs = ps.executeQuery();
                
                while(rs.next())
                {
                    arreglo[i][0] = rs.getString("ID");
                    arreglo[i][1] = rs.getString("NUMERO_TICKET");
                    arreglo[i][2] = rs.getString("VENTA");
                    i = i + 1;
                }
                
                return arreglo;
            }
        }catch(SQLException e)
        {
            
        }
        return arreglo;
    }
    
    public String[] recuperarInfoDinero()
    {
        String arreglo[] = null;
        try{
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM dinero WHERE ID = 1");
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                arreglo = new String[14];
                arreglo[0] = rs.getString("ID");
                arreglo[1] = rs.getString("INICIAL_DIA");
                arreglo[2] = rs.getString("PAGO_CLIENTES");
                arreglo[3] = rs.getString("VENTA");
                arreglo[4] = rs.getString("OTROS");
                arreglo[5] = rs.getString("DEVOLUCIONES");
                arreglo[6] = rs.getString("ULTIMO_TICKET");
                arreglo[7] = rs.getString("PAGO_PROVEDORES");
                arreglo[8] = rs.getString("PAGO_SERVICIOS");
                arreglo[9] = rs.getString("OTROS_PAGOS");
                arreglo[10] = rs.getString("ULTIMO_CORTEZ");
                arreglo[11] = rs.getString("ULTIMO_CORTEX");
                arreglo[12] = rs.getString("GANANCIA");
                arreglo[13] = rs.getString("NoClientes");
                
                return arreglo;
            }
            
        }catch(SQLException e){
            
        }
        return arreglo;
    }
    
    public String[] recuperarProducto(String codigo)
    {
        String arreglo[] = null;
        try
        {
            Connection cn = DriverManager.getConnection(connection,usuario,password);
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM productos WHERE CÓDIGO = " + codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                arreglo = new String[10];
                arreglo[0] = rs.getString("ID");
                arreglo[1] = rs.getString("NOMBRE");
                arreglo[2] = rs.getString("DESCRIPCIÓN");
                arreglo[3] = rs.getString("CÓDIGO");
                arreglo[4] = rs.getString("% GANANCIA");
                arreglo[5] = rs.getString("% IMPUESTOS");
                arreglo[6] = rs.getString("PRECIO_DE_COMPRA");
                arreglo[7] = rs.getString("EXISTENCIA");
                arreglo[8] = rs.getString("INVENTARIO");
                arreglo[9] = rs.getString("CANTIDAD_MINIMA");
                
                return arreglo;
            }
            
        }catch(SQLException e)
        {
            
        }
        
        return arreglo;
    }
    
    
}
