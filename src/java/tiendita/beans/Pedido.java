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


public class Pedido implements Serializable {
    
    private Integer pediPedido;
    private Date pediFecha;
    private Date pediFechaCancel;
    private String pediEstatus;
    private String pediUsuario;
    
    
    
    private String error;

    public String getPediUsuario() {
        return pediUsuario;
    }

    public void setPediUsuario(String pediUsuario) {
        this.pediUsuario = pediUsuario;
    }

    public Date getPediFechaCancel() {
        return pediFechaCancel;
    }

    public void setPediFechaCancel(Date pediFechaCancel) {
        this.pediFechaCancel = pediFechaCancel;
    }
    

    public String getPediEstatus() {
        return pediEstatus;
    }

    public Date getPediFecha() {
        return pediFecha;
    }

    public Integer getPediPedido() {
        return pediPedido;
    }

    public void setPediEstatus(String pediEstatus) {
        this.pediEstatus = pediEstatus;
    }

    public void setPediFecha(Date pediFecha) {
        this.pediFecha = pediFecha;
    }

    public void setPediPedido(Integer pediPedido) {
        this.pediPedido = pediPedido;
    }
    
    
    public Pedido setId(Integer ped) {
        
        Dao db = new Dao();
        Pedido pd = null;
                
        try {            
            
            ResultSet rst = db.consultar("Select pedi_pedido,pedi_fecha,pedi_estatus,pedi_usuario,pedi_fechacancel "
                                       + "From Pedido Where pedi_pedido = '"+ped+"' "
                                        );

            while (rst != null && rst.next()) {
                pd = new Pedido();
                pd.setPediPedido(rst.getInt("pedi_pedido"));
                pd.setPediFecha(rst.getDate("pedi_fecha"));
                pd.setPediEstatus(rst.getString("pedi_estatus"));
                pd.setPediUsuario(rst.getString("pedi_usuario"));   
                pd.setPediFechaCancel(rst.getDate("pedi_fechacancel"));
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
     return pd;
    }
    
    public List<Pedido> setCondicion(String condicion) {
        
        Dao db = new Dao();
        Pedido pd = null;
        List<Pedido> lpp = new ArrayList<Pedido>();
                
        try {
            
            ResultSet rst = db.consultar("Select pedi_pedido,pedi_fecha,pedi_estatus,pedi_usuario,pedi_fechacancel "
                                       + "From Pedido  "+condicion
                                        );

            while (rst != null && rst.next()) {
                pd = new Pedido();
                pd.setPediPedido(rst.getInt("pedi_pedido"));
                pd.setPediFecha(rst.getDate("pedi_fecha"));
                pd.setPediEstatus(rst.getString("pedi_estatus"));
                pd.setPediUsuario(rst.getString("pedi_usuario")); 
                pd.setPediFechaCancel(rst.getDate("pedi_fechacancel"));
                lpp.add(pd);
                
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
        
      return lpp;
    }
    
    
    public Pedido agrPedido(Pedido ped) {
        
        Dao db = new Dao();
        
        try {
            Integer seq = this.secuencia(db);
            int ret = db.modificar("Insert into Pedido (pedi_pedido,pedi_fecha,pedi_estatus,pedi_usuario) "
                                 + "Values ('"+seq+"',now(),'"+ped.getPediEstatus()+"','"+ped.getPediUsuario()+"') ");
            ped.setPediPedido(seq);
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
      return ped;        
    }
    
    
    
    
    public static void cancelarPedido(Integer ped, Dao db) throws SQLException {
     
        int ret = db.modificar("Update Pedido "
                               + "Set pedi_fechacancel = now(), pedi_estatus = 'C'  "
                               + "Where pedi_pedido = '"+ped+"' "
                              );        
        
        if (ret > 0) {
            
            PedidoProducto prp = new PedidoProducto();
            List<PedidoProducto> lpr = prp.setCondicion(" Where prpe_pedido = '"+ped+"' ");
            
            for (PedidoProducto pp : lpr) {
                Producto.sumarExistencia(pp.getPrpeProducto(), pp.getPrpeCantidad(), db);                
            }            
            
            PedidoProducto.borXPedido(ped, db);
            
        }        
    }
   
  
    
    
    public String getError() {
        return this.error;
    }
    
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(pedi_pedido)+1 From Pedido");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }              
         
      return seq;  
    }
            
            
    
    
}
