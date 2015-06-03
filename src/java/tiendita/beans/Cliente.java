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


public class Cliente implements Serializable {
    
    private Integer clieCliente;
    private String clieRfc;
    private String clieRazonSoc;
    private Integer cliePersona;
    
    
    private String error;

    public Integer getCliePersona() {
        return cliePersona;
    }

    public void setCliePersona(Integer cliePersona) {
        this.cliePersona = cliePersona;
    }
    

    public Integer getClieCliente() {
        return clieCliente;
    }

    public String getClieRazonSoc() {
        return clieRazonSoc;
    }

    public String getClieRfc() {
        return clieRfc;
    }

    public void setClieCliente(Integer clieCliente) {
        this.clieCliente = clieCliente;
    }

    public void setClieRazonSoc(String clieRazonSoc) {
        this.clieRazonSoc = clieRazonSoc;
    }

    public void setClieRfc(String clieRfc) {
        this.clieRfc = clieRfc;
    }
    
    
    public Cliente setId(Integer id) {
        
        Dao db = new Dao();
        Cliente clie = null;
        
        try {
            
            ResultSet rst = db.consultar("Select clie_cliente,clie_rfc,clie_razonsoc,clie_persona "
                                       + "From Cliente Where clie_cliente = '"+id+"' ");
            
            while (rst != null && rst.next()) {
                clie = new Cliente();
                clie.setClieCliente(rst.getInt("clie_cliente"));
                clie.setCliePersona(rst.getInt("clie_persona"));
                clie.setClieRazonSoc(rst.getString("clie_razonsoc"));
                clie.setClieRfc(rst.getString("clie_rfc"));
            }
                        
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
        
      return clie;   
    }
    
    public List<Cliente> setCondicion(String condicion) {
        Dao db = new Dao();
        Cliente clie = null;
        List<Cliente> lista = new ArrayList<Cliente>();
        
        try {
            
            ResultSet rst = db.consultar("Select clie_cliente,clie_rfc,clie_razonsoc,clie_persona "
                                       + "From Cliente "+condicion
                                        );
            
            while (rst != null && rst.next()) {
                clie = new Cliente();
                clie.setClieCliente(rst.getInt("clie_cliente"));
                clie.setCliePersona(rst.getInt("clie_persona"));
                clie.setClieRazonSoc(rst.getString("clie_razonsoc"));
                clie.setClieRfc(rst.getString("clie_rfc"));
                lista.add(clie);
            }
                        
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
             
      return lista;
    }    
    
    public Cliente agrCliente(Cliente clie) {
        
        Dao db = new Dao();
        
        try {
            
            List<Cliente> lc = this.setCondicion(" Where clie_rfc = '"+clie.getClieRfc()+"' ");
            
            if (lc.size() > 0) {
                error = "Ya existe el cliente con la RFC: "+clie.getClieRfc();
            } else {
                
                Integer seq = this.secuencia(db);
                
                int ret = db.modificar("Insert into Cliente (clie_cliente,clie_persona,clie_razonsoc,clie_rfc)"
                        + " Values ('"+seq+"','"+clie.getClieCliente()+"','"+clie.getClieRazonSoc()+"','"+clie.getClieRfc()+"')");
                 clie.setClieCliente(seq);
            }
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
                
      return clie;
    }
    
    
    public Cliente actCliente(Cliente clie) {
        
        Dao db = new Dao();
        
        try {
            
           int ret = db.modificar("Update Cliente Set clie_rfc = '"+clie.getClieRfc()+"', "
                                + "clie_persona = '"+clie.getCliePersona()+"', "
                                + "clie_razonsoc = '"+clie.getClieRazonSoc()+"' "
                                + "Where clie_cliente = '"+clie.getClieCliente()+"' "
                               );
            
        } catch (SQLException e) {
            error = e.getMessage();
        } finally {
            db.desconectar();
        }
        
     return clie;
    }
    
    
    public String getError() {
        return this.error;
    }
    
    
    private Integer secuencia(Dao db) throws SQLException {
         Integer seq = 0;         
         ResultSet rst = db.consultar("Select max(clie_cliente)+1 From Cliente");
         
         if (rst != null && rst.next()) {
             seq = rst.getInt(1);
         }              
         
      return seq;  
    }
    
    
}
