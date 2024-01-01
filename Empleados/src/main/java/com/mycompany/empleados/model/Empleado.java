package com.mycompany.empleados.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.lang.model.element.Name;
import javax.persistence.*;

/**
 * @author jeiny
 */

@Entity
@Table(name = "Empleado")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    private String apellido;
    private String cargo;
    private double salario;
    private LocalDate fechaInicio;


    public Empleado() {
    }

    public Empleado(int id, String nombre, String apellido, String cargo, double salario, LocalDate fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = fechaInicio;
    }

    public int getId() {
        return id;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaInicio() {return this.fechaInicio;}

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return id + ". " + nombre + " " + apellido + " | Cargo : " + cargo + " | Salario : " + salario + " | FechaInicio : " + fechaInicio + "\n";
    }

    public String seleccionToString() {
        return id + ". " + nombre + " " + apellido;
    }
}
