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
import java.util.ArrayList;
import java.util.List;
import conector.Dao;


public class Producto implements Serializable {
    
    private Integer prodProducto;
    private String prodNombre;
    private Integer prodUmedida;
    private Float prodExistencia;
    private Float prodPrecio;
    private Integer prodMarca;
    private Integer prodLocacion;
    
    private String error;

    public Integer getProdMarca() {
        return prodMarca;
    }

    public void setProdMarca(Integer prodMarca) {
        this.prodMarca = prodMarca;
    }

    public Integer getProdLocacion() {
        return prodLocacion;
    }

    public void setProdLocacion(Integer prodLocacion) {
        this.prodLocacion = prodLocacion;
    }

    
    
    public Float getProdExistencia() {
        return prodExistencia;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public Float getProdPrecio() {
        return prodPrecio;
    }

    public Integer getProdProducto() {
        return prodProducto;
    }

    public Integer getProdUmedida() {
        return prodUmedida;
    }

    public void setProdExistencia(Float prodExistencia) {
        this.prodExistencia = prodExistencia;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public void setProdPrecio(Float prodPrecio) {
        this.prodPrecio = prodPrecio;
    }

    public void setProdProducto(Integer prodProducto) {
        this.prodProducto = prodProducto;
    }

    public void setProdUmedida(Integer prodUmedida) {
        this.prodUmedida = prodUmedida;
    }
    
    
    public Producto setId(Integer prod) {
        
        Dao db = new Dao();
        Producto pro = null;
        try {
            
           ResultSet rst = db.consultar("Select "
                   + " prod_producto,prod_nombre,prod_precio,prod_umedida,prod_existencia,prod_marca,prod_locacion From Producto Where prod_producto = '"+prod.intValue()+"' ");
           while (rst != null  && rst.next()) {  
               pro = new Producto();
               pro.setProdProducto(rst.getInt("PROD_PRODUCTO"));
               pro.setProdNombre(rst.getString("PROD_NOMBRE"));
               pro.setProdPrecio(rst.getFloat("PROD_PRECIO"));
               pro.setProdUmedida(rst.getInt("PROD_UMEDIDA"));
               pro.setProdExistencia(rst.getFloat("PROD_EXISTENCIA"));               
               pro.setProdMarca(rst.getInt("PROD_MARCA"));
               pro.setProdLocacion(rst.getInt("prod_locacion"));
           }         
                       
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        return pro;
    }
    
    public List<Producto> setCondicion(String condicion) {
        Dao db = new Dao();
        Producto pro = null;
        List<Producto> lista = new ArrayList<Producto>();
        try {
            
           ResultSet rst = db.consultar("Select prod_producto,prod_nombre,prod_precio,prod_umedida,prod_existencia,prod_marca,prod_locacion From Producto "+condicion);
           while (rst != null  && rst.next()) {  
               pro = new Producto();
               pro.setProdProducto(rst.getInt("PROD_PRODUCTO"));
               pro.setProdNombre(rst.getString("PROD_NOMBRE"));
               pro.setProdPrecio(rst.getFloat("PROD_PRECIO"));
               pro.setProdUmedida(rst.getInt("PROD_UMEDIDA"));
               pro.setProdExistencia(rst.getFloat("PROD_EXISTENCIA"));
               pro.setProdMarca(rst.getInt("PROD_MARCA"));
               pro.setProdLocacion(rst.getInt("prod_locacion"));
               lista.add(pro);
           }         
           
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        return lista;
    }
    
    
    public Producto agrProducto(Producto prod) {
        
        Dao db = new Dao();
        
        try {
            
          List<Producto> lp = this.setCondicion("Where prod_nombre = '"+prod.getProdNombre()+"' And prod_marca = '"+prod.getProdMarca()+"' ");          
          
          if (lp.size() > 0) {
              Marca mar = new Marca();
              mar.setMarcMarca(prod.getProdMarca());
              this.error = "Ya existe el producto "+prod.getProdNombre()+" Para la marca "+mar.getMarcDescrip();
          } else {
              
              Integer seq = this.secuencia(db);
              prod.setId(seq);
              int ret = db.modificar("Insert into Producto (prod_producto,prod_nombre,prod_marca,prod_umedida,prod_precio,prod_existencia,prod_locacion) "
                         + " Values ('"+seq+"','"+prod.getProdNombre()+"','"+prod.getProdMarca()+"','"+prod.getProdUmedida()+"','"+prod.getProdPrecio()+"','"+prod.getProdExistencia()+"','"+prod.getProdLocacion()+"') "
                          );             
              
          }
        } catch (SQLException e) {
            this.error = e.getMessage();
        } finally {
            db.desconectar();
        }
       
       return prod;
    }
    
    public Producto actProducto(Producto prod) {
        
        Dao db = new Dao();
      
        try {
            
            int ret = db.modificar("Update Producto "
                    + "Set prod_nombre = '"+prod.getProdNombre()+"', "
                    + "prod_umedida = '"+prod.getProdUmedida()+"', "
                    + "prod_marca = '"+prod.getProdMarca()+"', "
                    + "prod_precio = '"+prod.getProdPrecio()+"', "
                    + "prod_locacion = '"+prod.getProdLocacion()+"', "
                    + "prod_existencia = '"+prod.getProdExistencia()+"' "
                    + "Where prod_producto = '"+prod.getProdProducto()+"' "
                    );
                                    
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                        
      return prod;
    }
    
    public static void restarExistencia(Integer id, Float cantidad, Dao db) throws SQLException {
        db.modificar("Update Producto Set prod_existencia = -"+cantidad+" Where prod_producto = '"+id+"' ");
    }
    
    public static void sumarExistencia(Integer id, Float cantidad, Dao db) throws SQLException {
        db.modificar("Update Producto Set prod_existencia = +"+cantidad+" Where prod_producto = '"+id+"' ");
    }
    
    public String getError() {
        return this.error;
    }
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(prod_producto)+1 From Producto");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }                  
      return seq;  
    }
    
    
}
