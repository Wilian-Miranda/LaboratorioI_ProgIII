package com.unab.domain;

public class Contactos {


    private int id, edad;
    private String nombre, email, numeroDeTelefono;
    
    public Contactos(){
    }

    public Contactos(int id){
        this.id = id;
    }

    public Contactos(String nombre, int edad, String email, String numeroDeTelefono){
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.numeroDeTelefono = numeroDeTelefono;
    }
    
    public Contactos(int id, String nombre, int edad, String email, String numeroDeTelefono) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.numeroDeTelefono = numeroDeTelefono;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }
}
