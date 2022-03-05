/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.unab.Services;

import com.unab.Entidades.Contactos;
import java.util.ArrayList;

/**
 *
 * @author Willian
 */
public interface IContactos {
    
    public ArrayList<Contactos> MostrarContactos();
    public void GuardarContacto(Contactos contacto);
    public void ActualizarContacto(Contactos contacto);
    public void EliminarContacto(int id);
    
}
