/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.model;

/**
 *
 * @author cesar.rodriguez
 */
public class Person {

    private String nombre;
    private String apellido;
    private String edad;
    private String cargo;
    private String profesion;

    public Person(String nombre, String apellido, String edad, String cargo, String profesion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.cargo = cargo;
        this.profesion = profesion;
    }

    public Person() {
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return "Person{" + 
                "nombre=" + nombre + '\'' +
                ", apellido=" + apellido + '\'' +
                ", edad=" + edad + '\'' +
                ", cargo=" + cargo + '\'' + 
                ", profesion=" + profesion + '\'' +
                '}';
    }

    
}
