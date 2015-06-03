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
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import conector.Dao;


public class Marca implements Serializable {
 
    
    private Integer marcMarca;
    private String marcDescrip;
    
    private String error;

    public String getMarcDescrip() {
        return marcDescrip;
    }

    public Integer getMarcMarca() {
        return marcMarca;
    }

    public void setMarcDescrip(String marcDescrip) {
        this.marcDescrip = marcDescrip;
    }

    public void setMarcMarca(Integer marcMarca) {
        this.marcMarca = marcMarca;
    }

    
    
    
    
    public Marca agrMarca(Marca marca) {
        
      Dao db = new Dao();
      
      try { 
        Integer seq = this.secuencia(db);             
        int ret = db.modificar("Insert into marca (marc_marca,marc_descrip) VALUES ('"+seq+"','"+marca.getMarcDescrip()+"')");
        marca.setMarcMarca(seq);
      } catch (SQLException e) {
          error = e.getMessage();
      } finally {
          db.desconectar();
      }
        
      return marca;  
    }
    
    public Marca actMarca(Marca marca) {
        
        Dao db = new Dao();
        try {
         int ret = db.modificar("Update marca Set marc_descrip='"+marca.getMarcDescrip()+"' Where marc_marca = '"+marca.getMarcMarca()+"'");
        } catch (SQLException e) {
          error = e.getMessage();
        } finally {
            db.desconectar();
        }     
        
      return marca;  
    }
    
    
    
    public Marca setId(Integer id) {
      
         Marca marc = null;
         Dao db = new Dao();
         
         try {
             
             ResultSet rst = db.consultar("Select marc_marca,marc_descrip From Marca Where marc_marca = '"+id+"'");
             
             if (rst.next()) {
                 marc = new Marca();
                 marc.setMarcMarca(rst.getInt("marc_marca"));
                 marc.setMarcDescrip(rst.getString("marc_descrip"));
             }
             
         } catch (SQLException e) {
             error = e.getMessage();
         } finally {
             db.desconectar();
         }
         
      return marc;  
    }
    
    
    public List<Marca> setCondicion(String condicion) {
      
         List<Marca> lista = new ArrayList<Marca>();
         Marca marc = null;
         Dao db = new Dao();
         
         try {
             
             ResultSet rst = db.consultar("Select marc_marca,marc_descrip From Marca "+condicion);
             
             while (rst.next()) {
                 marc = new Marca();
                 marc.setMarcMarca(rst.getInt("marc_marca"));
                 marc.setMarcDescrip(rst.getString("marc_descrip"));
              lista.add(marc);
             }
             
         } catch (SQLException e) {
             error = e.getMessage();
         } finally {
             db.desconectar();
         }
         
         
      return lista;  
    }
    
    
    public String getError() {
        return this.error;
    }
    
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(marc_marca)+1 From Marca");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }              
         
      return seq;  
    }
    
}
