/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unab.DAO;

import com.unab.DataBase.ConexionDB;
import com.unab.Entidades.Contactos;
import com.unab.Services.IContactos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class CDContactos implements IContactos{

    //intanciando la clase que se usara para la conexion
    ConexionDB clsConexion = new ConexionDB();
    
    @Override
    public ArrayList<Contactos> MostrarContactos() {
        //Abriendo una conexion con el servidor
        Connection conexion = clsConexion.ObtenerConexion();
        
        //array que será retornado
        ArrayList<Contactos> contacto = null;
        
        try{
            contacto = new ArrayList<Contactos>();
            
            //Preparando la consulta a la Base de Datos
            CallableStatement variableConsulta = conexion.prepareCall("call sp_MostrarContactos();");
            
            //Ejecutando la consulta y guardando el resultado
            ResultSet variableResultado = variableConsulta.executeQuery();
            
            //Cargando el resultado al Array
            while(variableResultado.next()){
                //Creando objeto de la entidad para cargar los datos
                Contactos contact = new Contactos();
                contact.setId(variableResultado.getInt("Id"));
                contact.setNombre(variableResultado.getString("Nombre"));
                contact.setEdad(variableResultado.getInt("Edad"));
                contact.setEmail(variableResultado.getString("Email"));
                contact.setNumeroDeTelefono(variableResultado.getString("NumeroDeTelefono"));
                
                //Agregando al array
                contacto.add(contact);
            
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al mostrar los contactos. \n"+ex.toString());
        }
        
        return contacto;
    }

    @Override
    public void GuardarContacto(Contactos contacto) {
        
        //Abriendo conexion con la Base de Datos
        Connection conexion = clsConexion.ObtenerConexion();
        
        try{
        //Preparando consulta a la Base de Dtos
        CallableStatement variableConsulta = conexion.prepareCall(
                "call sp_GuardarContacto("+"'"+contacto.getNombre()+"',"+
                                          +contacto.getEdad()+","+
                                          "'"+contacto.getEmail()+"',"+
                                          "'"+contacto.getNumeroDeTelefono()+"'"+
                                           ");");
        
        //ejecutando consulta
        variableConsulta.execute();
        
        JOptionPane.showMessageDialog(null, "Contacto agregado exitosamente.");
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al guardar el contacto en la Base de Datos. \n"+ ex);
        }
    }

    @Override
    public void ActualizarContacto(Contactos contacto) {
        //Abriendo conexion a la base de datos
        Connection conexion = clsConexion.ObtenerConexion();
        
        try{
        //Preparando consulta para actualizar contacto
        CallableStatement variableConsulta = conexion.prepareCall(     
                "call sp_ActualizarContacto("+contacto.getId()+","+
                                          "'"+contacto.getNombre()+"',"+
                                          +contacto.getEdad()+","+
                                          "'"+contacto.getEmail()+"',"+
                                          "'"+contacto.getNumeroDeTelefono()+"'"+
                                           ");");
        
        //ejecutando consulta
        variableConsulta.execute();
        
        JOptionPane.showMessageDialog(null, "Contacto actualizado exitosamente.");
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al actualizar contacto. \n"+ ex);
        } 
    }

    @Override
    public void EliminarContacto(int id) {
        
        //abriendo conexion
        Connection conexion = clsConexion.ObtenerConexion();
        
        try{
            //Preparando consulta
            CallableStatement variableConexion = conexion.prepareCall(
                    "call sp_EliminarContacto("+id+");");
            
            //ejecutando consulta
            variableConexion.execute();
            
            JOptionPane.showMessageDialog(null, "Contacto eliminado exitosamente.");

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al eliminar contacto. \n" + ex.toString());       
        }
    }
    
}
