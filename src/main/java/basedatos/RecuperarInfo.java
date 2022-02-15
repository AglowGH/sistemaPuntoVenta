/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basedatos;

import dinero.Venta;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author rick
 */
public class RecuperarInfo 
{
    private String usuario = "root";
    private String password = "A1b2C3";
    private String connection = "jdbc:mysql://localhost/refaccionaria";
    private Venta venta = new Venta();
    
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
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void informacionTablaInventarioGeneral(DefaultTableModel modelo)throws SQLException
    {
        Connection cn = DriverManager.getConnection(connection,usuario,password);
        PreparedStatement ps = cn.prepareStatement("select * from productos");
        ResultSet rs = ps.executeQuery();
            
        while(rs.next())
        {
            String[] arreglo = new String[5];
            arreglo[0] = rs.getString("CÓDIGO");
            arreglo[1] = rs.getString("NOMBRE");
            arreglo[2] = rs.getString("EXISTENCIA");
            arreglo[3] = rs.getString("INVENTARIO");
            double diferencia = Double.parseDouble(arreglo[3]) - Double.parseDouble(arreglo[2]);
            arreglo[4] = String.valueOf(diferencia);
            modelo.addRow(arreglo);
        }   
    }
    
    public void informacionAlertas(String tipo_alerta,DefaultTableModel modelo) throws SQLException
    {
            
        Connection cn = DriverManager.getConnection(connection,usuario,password);
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM productos");
        ResultSet rs = ps.executeQuery();
        int nColumnas = 4;
            
        if(tipo_alerta.equals("Todas las alertas"))
        {
            while(rs.next())
             {
                String[] fila = new String[nColumnas];
                fila[0] = rs.getString("CÓDIGO");
                fila[1] = rs.getString("NOMBRE");
                fila[2] = rs.getString("EXISTENCIA");
                fila[3] = rs.getString("CANTIDAD_MINIMA");
                    
                double existencia = Double.parseDouble(fila[2]);
                double minimo = Double.parseDouble(fila[3]);
                    
                if(existencia < minimo)
                {
                    modelo.addRow(fila);
                }
                    
            }
        }else if(tipo_alerta.equals("Sin mercancia"))
        {
            while(rs.next())
            {
                String[] fila = new String[nColumnas];
                fila[0] = rs.getString("CÓDIGO");
                fila[1] = rs.getString("NOMBRE");
                fila[2] = rs.getString("EXISTENCIA");
                fila[3] = rs.getString("CANTIDAD_MINIMA");
                    
                double existencia = Double.parseDouble(fila[2]);
                    
                if(existencia <= 0)
                {
                    modelo.addRow(fila);
                }
                    
            }
                
        }else if(tipo_alerta.equals("Por acabar"))
        {
            while(rs.next())
            {
                String[] fila = new String[nColumnas];
                fila[0] = rs.getString("CÓDIGO");
                fila[1] = rs.getString("NOMBRE");
                fila[2] = rs.getString("EXISTENCIA");
                fila[3] = rs.getString("CANTIDAD_MINIMA");
                    
                double existencia = Double.parseDouble(fila[2]);
                double minimo = Double.parseDouble(fila[3]);
                    
                if(existencia < minimo && existencia >0)
                {
                    modelo.addRow(fila);
                }
                    
            }
        }
        
    }
    
    public void informacionProductos(DefaultTableModel modelo)throws SQLException
    {
           
           Connection cn = DriverManager.getConnection(connection,usuario,password);
           String sql = "SELECT * FROM productos";
           PreparedStatement ps = cn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           ResultSetMetaData rsMD = rs.getMetaData();
           int nColumnas = rsMD.getColumnCount();
           while(rs.next())
           {
               Object[] fila = new Object[nColumnas+1];
               for(int i=1;i<=nColumnas;i++)
               {
                   fila[i-1] = rs.getObject(i);
               }
               double precioCliente = venta.calcularPrecioCliente(Double.parseDouble(String.valueOf(fila[6])),Double.parseDouble(String.valueOf(fila[4])),Double.parseDouble(String.valueOf(fila[5])));
               fila[nColumnas] = String.valueOf(precioCliente);
               modelo.addRow(fila);
           }
    }
    
    public void buscarProductoEnInventario(String buscar,DefaultTableModel modelo)throws SQLException
    {
        Connection cn = DriverManager.getConnection(connection,usuario,password);
        PreparedStatement ps = cn.prepareStatement("select * from productos where CONCAT(NOMBRE,'',DESCRIPCIÓN,'',CÓDIGO) like '%" + buscar + "%'");
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsMD = rs.getMetaData();
        int nColumnas = rsMD.getColumnCount();
        while(rs.next())
        {
            Object[] fila = new Object[nColumnas+1];
            for(int i=0;i<nColumnas;i++)
            {
                fila[i] = rs.getObject(i+1);
            }
            double precioCliente = venta.calcularPrecioCliente(Double.parseDouble(String.valueOf(fila[6])),Double.parseDouble(String.valueOf(fila[4])),Double.parseDouble(String.valueOf(fila[5])));
            fila[nColumnas] = String.valueOf(precioCliente);
            modelo.addRow(fila);
            //String fila[] = {rs.getString("CÓDIGO"),rs.getString("NOMBRE"),rs.getString("EXISTENCIA"),rs.getString("INVENTARIO"),rs.getString("CANTIDAD_MINIMA")};
        }
    }
    
    public String[] buscarProductoParaEntrada(String codigo) throws SQLException
    {
        String[] producto = new String[5];
        Connection cn = DriverManager.getConnection(connection,usuario,password);
        PreparedStatement pst = cn.prepareStatement("select * from productos where CÓDIGO = ?");
        pst.setString(1,codigo);
        ResultSet rs = pst.executeQuery();

        if(rs.next())
        {
                producto[0] = "1";
                producto[1] = rs.getString("CÓDIGO");
                producto[2] = rs.getString("NOMBRE");
                producto[3] = rs.getString("PRECIO_DE_COMPRA");
                producto[4] = rs.getString("PRECIO_DE_COMPRA");
                return producto;
        }
        return null;
    }
    
    //La siguiente funcion verifica que el código de barras no se repita con otro producto ya existente
    public String verificarCodigoBarras(String codigo)throws SQLException
    {
        Connection cn = DriverManager.getConnection(connection,usuario,password);
        PreparedStatement ps = cn.prepareStatement("select NOMBRE from productos where CÓDIGO = '" + codigo + "'");
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            return rs.getString("NOMBRE");
        }
        return null;
    }
}
