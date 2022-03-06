package com.unab.dao;

import com.unab.db.Conexion;
import com.unab.domain.Contactos;
import com.unab.services.IContactos;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ContactosDao implements IContactos{
    
    @Override
    public ArrayList<Contactos> mostrarContactos() {
        Contactos contacto = null;
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultado = null;
        
        //array que será retornado
        ArrayList<Contactos> contactos = new ArrayList<>();
        
        try{            
            //Abriendo la conexion con la BD
            conexion = Conexion.getConnection();
            //Preparando la consulta a la Base de Datos
            consulta = conexion.prepareCall("call sp_MostrarContactos();");
            
            //Ejecutando la consulta y guardando el resultado
            resultado = consulta.executeQuery();
            
            //Cargando el resultado al Array
            while(resultado.next()){
                //Creando objeto de la entidad para cargar los datos
                contacto = new Contactos();
                contacto.setId(resultado.getInt("Id"));
                contacto.setNombre(resultado.getString("Nombre"));
                contacto.setEdad(resultado.getInt("Edad"));
                contacto.setEmail(resultado.getString("Email"));
                contacto.setNumeroDeTelefono(resultado.getString("NumeroDeTelefono"));
                
                //Agregando al array
                contactos.add(contacto);
            
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se han podido mostrar los contactos. \n"+ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        finally{
            try{
                Conexion.close(resultado);
                Conexion.close(consulta);
                Conexion.close(conexion);
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexión", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return contactos;
    }

    @Override
    public void guardarContacto(Contactos contacto) {
        Connection conexion = null;
        CallableStatement consulta = null;        
        try{
            conexion = Conexion.getConnection();

            //Preparando consulta a la Base de Dtos
            consulta = conexion.prepareCall("call sp_GuardarContacto(?, ?, ?, ?)");

            consulta.setString(1, contacto.getNombre());
            consulta.setInt(2, contacto.getEdad());
            consulta.setString(3, contacto.getEmail());
            consulta.setString(4, contacto.getNumeroDeTelefono());
        
            //ejecutando consulta
            consulta.execute();
        
        JOptionPane.showMessageDialog(null, "Contacto agregado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se han podido guardar el contacto. \n"+ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        finally{
            try {
                Conexion.close(consulta);
                Conexion.close(conexion);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexión", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actualizarContacto(Contactos contacto) {
        Connection conexion = null;
        CallableStatement consulta = null;

        try{
            conexion = Conexion.getConnection();

            //Preparando consulta para actualizar contacto
            consulta = conexion.prepareCall("call sp_ActualizarContacto(?, ?, ?, ?, ?)");

            consulta.setInt(1, contacto.getId());
            consulta.setString(2, contacto.getNombre());
            consulta.setInt(3, contacto.getEdad());
            consulta.setString(4, contacto.getEmail());
            consulta.setString(5, contacto.getNumeroDeTelefono());
            
            //ejecutando consulta
            consulta.execute();
            
            JOptionPane.showMessageDialog(null, "Contacto actualizado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se han podido actualizar el contacto. \n"+ex.toString(), "Error", 2);
        } 

        finally{
            try {
                Conexion.close(consulta);
                Conexion.close(conexion);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexión", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void eliminarContacto(Contactos contacto) {
        Connection conexion = null;
        CallableStatement consulta = null;
        
        try{
            conexion = Conexion.getConnection();
            
            //Preparando consulta
            consulta = conexion.prepareCall("call sp_EliminarContacto(?)");
            
            consulta.setInt(1, contacto.getId());
            
            //ejecutando consulta
            consulta.execute();
            
            JOptionPane.showMessageDialog(null, "Contacto eliminado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se han podido eliminar el contacto. \n"+ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);     
        }

        finally{
            try {
                Conexion.close(consulta);
                Conexion.close(conexion);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexión", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }  
}
