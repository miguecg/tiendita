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


public class PedidoProducto implements Serializable {
    
    private Integer prpeItem;
    private Integer prpeProducto;
    private Integer prpePedido;
    private Float prpeCantidad;
    
    
    private String error;

    public Float getPrpeCantidad() {
        return prpeCantidad;
    }

    public void setPrpeCantidad(Float prpeCantidad) {
        this.prpeCantidad = prpeCantidad;
    }

    
    
    public Integer getPrpeItem() {
        return prpeItem;
    }

    public Integer getPrpePedido() {
        return prpePedido;
    }

    public Integer getPrpeProducto() {
        return prpeProducto;
    }

    public void setPrpeItem(Integer prpeItem) {
        this.prpeItem = prpeItem;
    }

    public void setPrpePedido(Integer prpePedido) {
        this.prpePedido = prpePedido;
    }

    public void setPrpeProducto(Integer prpeProducto) {
        this.prpeProducto = prpeProducto;
    }
    
    
    public PedidoProducto setId(Integer id) {
       
        PedidoProducto itt = null;        
        Dao db = new Dao();
        
        try {
            
            ResultSet rst = db.consultar("Select prpe_item,prpe_pedido,prpe_producto,prpe_cantidad "
                                       + "From PedidoProducto "
                                       + "Where prpe_item = '"+id+"' ");
            
            while (rst != null && rst.next()) {
                itt = new PedidoProducto();
                itt.setPrpeItem(rst.getInt("prpe_item"));
                itt.setPrpePedido(rst.getInt("prpe_pedido"));
                itt.setPrpeProducto(rst.getInt("prpe_producto"));
                itt.setPrpeCantidad(rst.getFloat("prpe_cantidad"));
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        
     return itt;   
    }
    
    
    public List<PedidoProducto> setCondicion(String condicion) {
        PedidoProducto itt = null;
        List<PedidoProducto> lpp = new ArrayList<PedidoProducto>();
        Dao db = new Dao();
        
        try {
            
            ResultSet rst = db.consultar("Select prpe_item,prpe_pedido,prpe_producto,prpe_cantidad From PedidoProducto "+condicion);
            
            while (rst != null && rst.next()) {
                itt = new PedidoProducto();
                itt.setPrpeItem(rst.getInt("prpe_item"));
                itt.setPrpePedido(rst.getInt("prpe_pedido"));
                itt.setPrpeProducto(rst.getInt("prpe_producto"));
                itt.setPrpeCantidad(rst.getFloat("prpe_cantidad"));
                lpp.add(itt);
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
      return lpp;  
    }
    
    
      
    public PedidoProducto agrProductoPedido(PedidoProducto prp) {        
        
        Dao db = new Dao();
        
        try {
          Integer seq = secuencia(db);  
          int ret = db.modificar("Insert into PedidoProducto (prpe_pedido,prpe_producto,prpe_cantidad) "
                  + "values ('"+seq+"',"+prp.getPrpePedido()+"','"+prp.getPrpeProducto()+"','"+prp.getPrpeCantidad()+"')  "
                  );                
          prp.setPrpeItem(seq);          
          Producto.restarExistencia(prp.getPrpeProducto(), prp.getPrpeCantidad(), db);
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
     return prp;   
    }
    
    public void borXItem(PedidoProducto prp)  {
        
        Dao db = new Dao();
        
        try {
          int ret = db.modificar("Delete From PedidoProducto Where prpe_item = '"+prp.getPrpeItem()+"' ");
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        } 
    }
    
    public static void borXPedido(Integer ped, Dao db) throws SQLException {
       int ret = db.modificar("Delete From PedidoProducto Where prpe_pedido = '"+ped+"'");
    }
    
    public static Float calcularTotal(Integer ped, Dao db) throws SQLException {
        
        Float total = 0.0F;
        
        ResultSet rst =
        db.consultar("Select sum(prod_precio * prpe_cantidad) "
                   + "From Producto,PedidoProducto "
                   + "Where prod_producto = prpe_producto "
                   + "And prpe_pedido = '"+ped+"' "
                    );
        
        while (rst != null && rst.next()) {
            total = rst.getFloat(1);
        }
        
      return total;  
    }
    
    public String getError() {
        return error;
    }
     
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(prpe_item)+1 From PedidoProducto");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }              
         
      return seq;  
    }
    
}
