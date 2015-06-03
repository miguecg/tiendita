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

public class Unidad implements Serializable {
    
    private Integer umedUmedida;
    private String umedSiglas;
    private String umedDescrip;
    
    private String error;

    public String getUmedDescrip() {
        return umedDescrip;
    }

    public String getUmedSiglas() {
        return umedSiglas;
    }

    public Integer getUmedUmedida() {
        return umedUmedida;
    }

    public void setUmedDescrip(String umedDescrip) {
        this.umedDescrip = umedDescrip;
    }

    public void setUmedSiglas(String umedSiglas) {
        this.umedSiglas = umedSiglas;
    }

    public void setUmedUmedida(Integer umedUmedida) {
        this.umedUmedida = umedUmedida;
    }
    
    
    public Unidad setId(Integer umedida) {
        
        Unidad unid = null;        
        Dao db = new Dao();
        
        try {
            
            ResultSet rst = db.consultar("Select umed_umedida,umed_siglas,umed_descrip From Unidad Where umed_umedida = '"+umedida+"'");
            
            while (rst != null && rst.next()) {
                unid = new Unidad();
                unid.setUmedUmedida(rst.getInt("umed_umedida"));
                unid.setUmedSiglas(rst.getString("umed_siglas"));
                unid.setUmedDescrip(rst.getString("umed_descrip"));
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
      return unid;  
    }
    
    public List<Unidad> setCondicion(String condicion) {
        
        Unidad unid = null;        
        Dao db = new Dao();
        List<Unidad> lu = new ArrayList<Unidad>();
        
        try {
            
            ResultSet rst = db.consultar("Select umed_umedida,umed_siglas,umed_descrip From Unidad "+condicion);
            
            while (rst != null && rst.next()) {
                unid = new Unidad();
                unid.setUmedUmedida(rst.getInt("umed_umedida"));
                unid.setUmedSiglas(rst.getString("umed_siglas"));
                unid.setUmedDescrip(rst.getString("umed_descrip"));
                lu.add(unid);
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
      return lu;  
    }
    
    
    public Unidad agrUnidad(Unidad unid) {
        
        Dao db = new Dao();
        
        try {            
            Integer seq = this.secuencia(db);            
            int ret = db.modificar("Insert Into Unidad(umed_umedida,umed_siglas,umed_descrip) "
                                 + "VALUES ('"+seq+"','"+unid.getUmedSiglas()+"','"+unid.getUmedDescrip()+"') ");
            unid.setUmedUmedida(seq);
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
      return unid;  
    }
    
    public Unidad actUnidad(Unidad unid) {
        
        Dao db = new Dao();
        
        try {
            
            int ret = db.modificar("Update Unidad "
                                 + "Set umed_siglas = '"+unid.getUmedSiglas()+"', "
                                 + "umed_descrip = '"+unid.getUmedDescrip()+"' "
                                 + "Where umed_umedida = '"+unid.getUmedUmedida()+"'"
                                   );
            
        } catch (SQLException e) {
            error = e.getMessage();
        }
        
     return unid;   
    }
    
    
    public String getError() {
        return error;
    }
    
    
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(umed_umedida)+1 From Unidad");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }              
         
      return seq;  
    }
    
    
    
}
