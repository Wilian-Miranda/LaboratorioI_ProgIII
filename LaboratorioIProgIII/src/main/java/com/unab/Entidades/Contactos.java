/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unab.Entidades;

/**
 *
 * @author Willian
 */
public class Contactos {


    private int Id, Edad;
    private String Nombre, Email, NumeroDeTelefono;
    
    public Contactos(){};
    
    public Contactos(int Id, String Nombre, int Edad, String Email, String NumeroDeTelefono) {
        this.Id = Id;
        this.Edad = Edad;
        this.Nombre = Nombre;
        this.Email = Email;
        this.NumeroDeTelefono = NumeroDeTelefono;
    }

    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNumeroDeTelefono() {
        return NumeroDeTelefono;
    }

    public void setNumeroDeTelefono(String NumeroDeTelefono) {
        this.NumeroDeTelefono = NumeroDeTelefono;
    }
}
