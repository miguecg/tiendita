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


public class Rol implements Serializable {
    
  private Integer roleRol;
  private String roleNombre;
  
  private String error;

    public String getRoleNombre() {
        return roleNombre;
    }

    public Integer getRoleRol() {
        return roleRol;
    }

    public void setRoleNombre(String roleNombre) {
        this.roleNombre = roleNombre;
    }

    public void setRoleRol(Integer roleRol) {
        this.roleRol = roleRol;
    }

    public Rol setId(Integer rol) {        
        Dao db = new Dao();
        Rol rl = null;  
        try {
            
            ResultSet rst = db.consultar("Select rol_rol,rol_descrip "
                                       + "From Rol Where rol_rol = '"+rol+"'"
                                        );
            
            while (rst != null && rst.next()) {
                rl = new Rol();
                rl.setRoleRol(rst.getInt("rol_rol"));
                rl.setRoleNombre(rst.getString("rol_descrip"));
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
     return rl;   
    }
  
    public List<Rol> setCondicion(String condicion) {        
        Dao db = new Dao();
        Rol rl = null;  
        List<Rol> lrl = new ArrayList<Rol>();
        try {
            
            ResultSet rst = db.consultar("Select rol_rol,rol_descrip "
                                       + "From Rol "+condicion
                                        );
            
            while (rst != null && rst.next()) {
                rl = new Rol();
                rl.setRoleRol(rst.getInt("rol_rol"));
                rl.setRoleNombre(rst.getString("rol_descrip"));
                lrl.add(rl);
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
     return lrl;   
    }
    
    public Rol agrRol(Rol rol) {
        
        Dao dao = new Dao();
        
        try {
            Integer seq = this.secuencia(dao);
            dao.modificar("Insert into Rol (rol_rol,rol_descrip) Values ('"+seq+"','"+rol.getRoleNombre()+"') ");
            rol.setRoleRol(seq);           
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            dao.desconectar();
        }
                
      return rol;    
    }
    
    public Rol actRol(Rol rol) {
        
        Dao dao = new Dao();
        
        try {

            dao.modificar("Update Rol Set rol_descrip = '"+rol.getRoleNombre()+"' Where rol_rol = '"+rol.getRoleRol()+"' "); 
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            dao.desconectar();
        }
                
      return rol;    
    }
    
    
    
    
    
    public String getError() {
        return this.error;
    }
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(rol_rol)+1 From Rol");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1) == 0 ? 1 : rst.getInt(1);
         }              
         
      return seq;  
    }
    
    public static boolean autorizado(List<Rol> cred, Integer rol) {
        boolean loEsta = false;
        
        for (Rol r : cred) {
            if (r.getRoleRol().intValue() == rol.intValue()) {
                loEsta = true;
              break;  
            }
        }
        
     return loEsta;   
    }
    
    
}
