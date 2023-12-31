package com.mycompany.empleados.exceptions;

import java.time.LocalDate;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String mensaje){
        super(mensaje);
    }

    public void validarDatos(String nombre, String apellido, String cargo, double salario, LocalDate fechaInicio) throws InvalidDataException {
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()
        || cargo == null || cargo.isEmpty() || salario <=0 || fechaInicio == null){
            throw new InvalidDataException("Los datos ingresados son invalidos o incompletos");
        }
    }

    public String validarString(String atributo) throws InvalidDataException {
        if (atributo == null || atributo.isEmpty()){
            throw new InvalidDataException("El datos ingresados es invalido o incompleto");
        }
        return atributo;
    }

    public double validarDouble(String atributo) throws InvalidDataException {
        try {
            return Double.parseDouble(atributo);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("El salario ha de ser ingrresado con numeros, no con letras");
        }
    }

    public int validarEntradaMenu(String entrada) throws InvalidDataException {
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Se esperaba un número entero, se ingresó un texto");
        }
    }
}
