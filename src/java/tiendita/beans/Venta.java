/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita.beans;

/**
 *
 * @author miguel
 */
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import conector.Dao;


public class Venta implements Serializable {
    
    private Integer ventVenta;
    private Date ventFecha;
    private Float ventTotal;
    private Integer ventPedido;
    private String ventEstatus;
    private String ventUsuario;

    
    private String error;
    
    public String getVentUsuario() {
        return ventUsuario;
    }

    public void setVentPedido(Integer ventPedido) {
        this.ventPedido = ventPedido;
    }

    public void setVentTotal(Float ventTotal) {
        this.ventTotal = ventTotal;
    }

    public void setVentUsuario(String ventUsuario) {
        this.ventUsuario = ventUsuario;
    }

    public void setVentVenta(Integer ventVenta) {
        this.ventVenta = ventVenta;
    }
    
    public String getVentEstatus() {
        return ventEstatus;
    }

    public Date getVentFecha() {
        return ventFecha;
    }

    public Integer getVentPedido() {
        return ventPedido;
    }

    public Float getVentTotal() {
        return ventTotal;
    }

    public Integer getVentVenta() {
        return ventVenta;
    }

    public void setVentEstatus(String estatus) {
        this.ventEstatus = estatus;
    }

    public void setVentFecha(Date ventFecha) {
        this.ventFecha = ventFecha;
    }
    
    
    public Venta setId(Integer id) {
        
        Venta vent = null;
        Dao db = new Dao();
    
        try {
            
            ResultSet rst = db.consultar("Select vent_venta,vent_fecha,vent_total,vent_estatus,vent_usuario,vent_pedido,vent_usuario "
                                       + "Where vent_venta = '"+id+"'"
                                       );
            
            while (rst != null && rst.next()) {
                vent = new Venta();
                vent.setVentVenta(rst.getInt("vent_venta"));
                vent.setVentFecha(rst.getDate("vent_fecha"));
                vent.setVentTotal(rst.getFloat("vent_total"));
                vent.setVentEstatus(rst.getString("vent_estatus"));
                vent.setVentUsuario(rst.getString("vent_usuario"));
                vent.setVentPedido(rst.getInt("vent_pedido"));
                vent.setVentUsuario(rst.getString("vent_usuario"));
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        
     return vent;   
    }
    
    public List<Venta> setCondicion(String condicion) {
        
        Venta vent = null;
        Dao db = new Dao();
        List<Venta> lvent = new ArrayList<Venta>();
    
        try {
            
            ResultSet rst = db.consultar("Select vent_venta,vent_fecha,vent_total,vent_estatus,vent_usuario,vent_pedido,vent_usuario From Venta "+condicion                                       
                                       );
            
            while (rst != null && rst.next()) {
                vent = new Venta();
                vent.setVentVenta(rst.getInt("vent_venta"));
                vent.setVentFecha(rst.getDate("vent_fecha"));
                vent.setVentTotal(rst.getFloat("vent_total"));
                vent.setVentEstatus(rst.getString("vent_estatus"));
                vent.setVentUsuario(rst.getString("vent_usuario"));
                vent.setVentPedido(rst.getInt("vent_pedido"));
                vent.setVentUsuario(rst.getString("vent_usuario"));
                lvent.add(vent);
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
     return lvent;   
    }
    
    
    
    public Venta agrVenta(Venta vent) {
        
        Dao db = new Dao();
        
        try {

            Integer seq = this.secuencia(db);
            int ret = db.modificar("Insert into Venta (vent_venta,vent_fecha,vent_total,vent_estatus,vent_usuario,vent_pedido) "
                                 + "Values ('"+seq+"',now(),'"+vent.ventTotal+"','"+vent.getVentEstatus()+"','"+vent.getVentUsuario()+"','"+vent.getVentPedido()+"') ");
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
     return vent;
    }
    
    public static void actVentaTotal(Integer venta, Float total, Dao db) throws SQLException {        
        int ret = db.modificar("Update Venta Set vent_total = '"+total+"' Where vent_venta = '"+venta+"' ");                          
    }
    
    public void cancelarVenta(Integer venta,Integer pedido) {
        
        Dao db = new Dao();
        
        try {        
        Pedido.cancelarPedido(pedido, db);        
        int ret = db.modificar("Update Venta Set vent_estatus = 'C' Where vent_venta = '"+venta+"' ");
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
    } 
    
    public void saldarVenta(Integer venta) {
        Dao db = new Dao();
        
        try {                
        int ret = db.modificar("Update Venta Set vent_estatus = 'S' Where vent_venta = '"+venta+"' ");
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
    }
    
    
    
    public String getError() {
        return this.error;
    }
    
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(vent_venta)+1 From Venta");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }              
         
      return seq;  
    }
    
}
