/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conector.Dao;
/**
 *
 * @author miguel
 */
public class Usuario implements Serializable {
 
    private String usuaUsuario;
    private String usuaPassword;
    private String usuaEstatus;
    private Integer usuaPersona;
    
    private String error;

    public String getUsuaEstatus() {
        return usuaEstatus;
    }

    public String getUsuaPassword() {
        return usuaPassword;
    }

    public Integer getUsuaPersona() {
        return usuaPersona;
    }

    public String getUsuaUsuario() {
        return usuaUsuario;
    }

    public void setUsuaEstatus(String usuaEstatus) {
        this.usuaEstatus = usuaEstatus;
    }

    public void setUsuaPassword(String usuaPassword) {
        this.usuaPassword = usuaPassword;
    }

    public void setUsuaPersona(Integer usuaPersona) {
        this.usuaPersona = usuaPersona;
    }

    public void setUsuaUsuario(String usuaUsuario) {
        this.usuaUsuario = usuaUsuario;
    }
    
     
    public Usuario setId(String id) {
        
        Usuario usua = null;       
        Dao db = new Dao();
        
        try {
            
            
            ResultSet rst = db.consultar("Select "
                                       + "usua_usuario,usua_passwd,usua_estatus,usua_persona "
                                       + "From Usuario Where usua_usuario = '"+id+"' "
                                        );
            
            while (rst != null && rst.next()) {
                usua = new Usuario();
                usua.setUsuaUsuario(rst.getString("usua_usuario"));
                usua.setUsuaPassword(rst.getString("usua_passwd"));
                usua.setUsuaEstatus(rst.getString("usua_estatus"));
                usua.setUsuaPersona(rst.getInt("usua_persona"));
            }
            
            
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        
     return usua;   
    }
    
    public List<Usuario> setCondicion(String condicion) {
        
        Usuario usua = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        Dao db = new Dao();
        
        try {            
            
            ResultSet rst = db.consultar("Select "
                                       + "usua_usuario,usua_passwd,usua_estatus,usua_persona "
                                       + "From Usuario "+condicion
                                        );
            
            while (rst != null && rst.next()) {
                usua = new Usuario();
                usua.setUsuaUsuario(rst.getString("usua_usuario"));
                usua.setUsuaPassword(rst.getString("usua_passwd"));
                usua.setUsuaEstatus(rst.getString("usua_estatus"));
                usua.setUsuaPersona(rst.getInt("usua_persona"));
                usuarios.add(usua);
            }
            
                        
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
     return usuarios;   
    }
    
    public String getError() {
        return error;
    }
    
    public Usuario agrUsuario(Usuario usu) {
        
        Dao db = new Dao();
        
        try {
            
            int ret = db.modificar("Insert into Usuario (usua_usuario,usua_passwd,usua_estatus,usua_persona) "
                                 + " Values ('"+usu.getUsuaUsuario()+"','"+usu.getUsuaPassword()+"','"+usu.getUsuaEstatus()+"','"+usu.getUsuaPersona()+"') "
                                  );
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        
     return usu;
    }
    
    public Usuario actUsuario(Usuario usu) {
     
        Dao db = new Dao();
        
        try {
            
            int ret = db.modificar("Update Usuario Set usua_estatus,usua_password "
                                 + "Where usua_usuario = '"+usu.getUsuaUsuario()+"' "
                                  );            
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
     return usu;
    }
    
    public void agrRolUsuario(String usuario, Integer rol) {
        
        Dao db = new Dao();
        
        try {
            
            int ret = db.modificar("Insert into RolUsuario (rlus_usuario,rlus_rol) Values ('"+usuario+"','"+rol+"') ");
                        
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
    }
    
    public void borRolUsuario(String usuario, Integer rol) {
        
        Dao db = new Dao();
        
        try {            
            int ret = db.modificar("Delete From RolUsuario Where rlus_usuario = '"+usuario+"' And rlus_rol = '"+rol+"' ");            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
    }
    
    
    
    public static void cambiarPasswd(String usuario,String passwd, Dao db) throws SQLException {        
        int ret = db.modificar("Update Set usua_passwd = '"+passwd+"' Where usua_usuario = '"+usuario+"'");        
    }
    
    
}
