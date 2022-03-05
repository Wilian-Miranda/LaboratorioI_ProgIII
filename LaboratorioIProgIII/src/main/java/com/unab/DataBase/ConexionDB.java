/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unab.DataBase;

import com.unab.Entidades.Contactos;
import com.unab.Services.IContactos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class ConexionDB {
    
    public static Connection variableConexion = null;

    public Connection ObtenerConexion(){
        
        try{
            String url = "jdbc:mysql://localhost:3306/Lab";
            String user = "root";
            String pass = "root";

            variableConexion = DriverManager.getConnection(url, user, pass);
            
            System.out.println("Conexion exitosa.");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al conectarse a la Base de Datos: \n" + ex.toString());            
        
        }

     return variableConexion;
    }
    
}
