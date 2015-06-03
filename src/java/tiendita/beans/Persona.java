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


public class Persona implements Serializable {
    
    private Integer persPersona;
    private String persNombre;
    private String persApepat;
    private String persApemat;
    private String persCorreo;
    private String persDireccion;
    
    private String error;

    public String getPersApemat() {
        return persApemat;
    }

    public String getPersApepat() {
        return persApepat;
    }

    public String getPersCorreo() {
        return persCorreo;
    }

    public String getPersDireccion() {
        return persDireccion;
    }

    public String getPersNombre() {
        return persNombre;
    }

    public Integer getPersPersona() {
        return persPersona;
    }

    public void setPersApemat(String persApemat) {
        this.persApemat = persApemat;
    }

    public void setPersApepat(String persApepat) {
        this.persApepat = persApepat;
    }

    public void setPersCorreo(String persCorreo) {
        this.persCorreo = persCorreo;
    }

    public void setPersDireccion(String persDireccion) {
        this.persDireccion = persDireccion;
    }

    public void setPersNombre(String persNombre) {
        this.persNombre = persNombre;
    }

    public void setPersPersona(Integer persPersona) {
        this.persPersona = persPersona;
    }
    
    public Persona setId(Integer id) {
        
        Persona pers = null;      
        Dao db = new Dao();
        
        try {
            
            ResultSet rst = db.consultar(
                                         "Select pers_persona,pers_nombre,pers_apepat,pers_apemat,pers_correo,pers_direccion "
                                       + "From Persona Where pers_persona = '"+id+"' "
                                        );
            while (rst != null && rst.next()) {
                pers = new Persona();
                pers.setPersPersona(rst.getInt("pers_persona"));
                pers.setPersNombre(rst.getString("pers_nombre"));
                pers.setPersApepat(rst.getString("pers_apepat"));
                pers.setPersApemat(rst.getString("pers_apemat"));
                pers.setPersCorreo(rst.getString("pers_correo"));
                pers.setPersDireccion(rst.getString("pers_direccion"));
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
      return pers;  
    }
    
    
    public List<Persona> setCondicion(String condicion) {
        
        List<Persona> lp = new ArrayList<Persona>();
        Persona pers = null;
        Dao db = new Dao();
        
        try {
            
            ResultSet rst = db.consultar(
                                         "Select pers_persona,pers_nombre,pers_apepat,pers_apemat,pers_correo,pers_direccion "
                                       + "From Persona  "+condicion
                                        );
            while (rst != null && rst.next()) {
                pers = new Persona();
                pers.setPersPersona(rst.getInt("pers_persona"));
                pers.setPersNombre(rst.getString("pers_nombre"));
                pers.setPersApepat(rst.getString("pers_apepat"));
                pers.setPersApemat(rst.getString("pers_apemat"));
                pers.setPersCorreo(rst.getString("pers_correo"));
                pers.setPersDireccion(rst.getString("pers_direccion"));
                lp.add(pers);
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        
     return lp;   
    }
    
    
    public Persona agrPersona(Persona pers) {
        
        Dao db = new Dao();
        
        try {
            Integer seq = this.secuencia(db);
            int ret = db.modificar("Insert into Persona (pers_persona,pers_nombre,pers_apepat,pers_apemat,pers_correo,pers_direccion) "
                                 + " Values ('"+seq+"','"+pers.getPersNombre()+"','"+pers.getPersApepat()+"','"+pers.getPersApemat()+"','"+pers.getPersCorreo()+"','"+pers.getPersDireccion()+"') "
                                  );
            pers.setPersPersona(seq);
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
       
     return pers; 
    }
    
    public Persona actPersona(Persona pers) {
        
        Dao db = new Dao();
        
        try {
            
            int ret = db.modificar("Update Persona Set pers_nombre = '"+pers.getPersNombre()+"',"
                                 + "pers_apepat = '"+pers.getPersApepat()+"', "
                                 + "pers_apemat = '"+pers.getPersApemat()+"', "
                                 + "pers_correo = '"+pers.getPersCorreo()+"', "
                                 + "pers_direccion = '"+pers.persDireccion+"' "
                                 + "Where pers_persona = '"+pers.getPersPersona()+"' "
                                  );
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        
     return pers;   
    }
    
    public String getError() {
        return error;
    }
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(pers_persona)+1 From Persona");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }              
         
      return seq;  
    }
    
}
