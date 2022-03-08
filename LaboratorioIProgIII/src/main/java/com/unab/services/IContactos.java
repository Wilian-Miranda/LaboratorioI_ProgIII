package com.unab.services;

import java.util.ArrayList;
import com.unab.entidades.Contactos;

public interface IContactos {
    
    ArrayList<Contactos> mostrarContactos();
    void guardarContacto(Contactos contacto);
    void actualizarContacto(Contactos contacto);
    void eliminarContacto(Contactos contacto);
    
}
