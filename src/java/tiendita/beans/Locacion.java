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

public class Locacion implements Serializable {
    
    private Integer locaLocacion;
    private Integer locaPosicion;
    private String locaDescrip;

    private String error;
    
    
    public String getLocaDescrip() {
        return locaDescrip;
    }

    public Integer getLocaLocacion() {
        return locaLocacion;
    }

    public Integer getLocaPosicion() {
        return locaPosicion;
    }

    public void setLocaDescrip(String locaDescrip) {
        this.locaDescrip = locaDescrip;
    }

    public void setLocaLocacion(Integer locaLocacion) {
        this.locaLocacion = locaLocacion;
    }

    public void setLocaPosicion(Integer locaPosicion) {
        this.locaPosicion = locaPosicion;
    }
    
    public Locacion setId(Integer loc) {
        
        Dao db = new Dao();
        Locacion locac = null; 
        
        try {
            
            ResultSet rst = db.consultar("Select loca_locacion,loca_posicion,loca_descrip "
                                       + "From Locacion Where loca_locacion = '"+loc+"' "
                                        );
            
            while (rst != null && rst.next()) {
                locac = new Locacion();
                locac.setLocaLocacion(rst.getInt("loca_locacion"));
                locac.setLocaPosicion(rst.getInt("loca_posicion"));
                locac.setLocaDescrip(rst.getString("loca_descrip"));
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
      return locac;        
    }
    
    public List<Locacion> setCondicion(String condicion) {
        Dao db = new Dao();
        Locacion locac = null; 
        List<Locacion> lcc = new ArrayList<Locacion>();
        
        try {
            
            ResultSet rst = db.consultar("Select loca_locacion,loca_posicion,loca_descrip "
                                       + "From Locacion  "+condicion
                                        );
            
            while (rst != null && rst.next()) {
                locac = new Locacion();
                locac.setLocaLocacion(rst.getInt("loca_locacion"));
                locac.setLocaPosicion(rst.getInt("loca_posicion"));
                locac.setLocaDescrip(rst.getString("loca_descrip"));
                lcc.add(locac);
                
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
     return lcc;
    }
    
    public Locacion agrLocacion(Locacion loc) {
        
        Dao db = new Dao();
        
        try {
            
            Integer seq = this.secuencia(db);
            
            int ret = db.modificar("Insert into Locacion (loca_locacion,loca_posicion,loca_descrip) "
                    + "Values ('"+seq+"','"+loc.getLocaPosicion()+"','"+loc.getLocaDescrip()+"')");
            loc.setLocaLocacion(seq);
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
     return loc;
    }
    
    public Locacion actLocacion(Locacion loc) {
        
        Dao db = new Dao();
        
        try {
            
            int ret = db.modificar("Update Locacion "
                                 + "Set loca_posicion = '"+loc.getLocaPosicion()+"', "
                                 + "loca_descrip = '"+loc.getLocaDescrip()+"' "
                                 + "Where loca_locacion = '"+loc.getLocaLocacion()+"' "
                                  );
            
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
     return loc;
    }
    
    
    public String getError() {
        return this.error;
    }
    
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(loca_locacion)+1 From Locacion");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }              
         
      return seq;  
    }
    
}
