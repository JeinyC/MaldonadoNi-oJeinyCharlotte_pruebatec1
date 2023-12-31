package com.mycompany.empleados.service;

import com.mycompany.empleados.model.Empleado;
import com.mycompany.empleados.persistence.ControladoraPersistencia;
import java.util.*;

public class ServiciosEmpleados {

    ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();

    public ServiciosEmpleados() {
    }

    public void agregarEmpleadoServicio(Empleado empleado) {
        controlPersistencia.crearEmpleado(empleado);
        System.out.println("|   Empleado: " + empleado.getNombre() + " " + empleado.getApellido() + " agregado al sistema");
    }

    public List<Empleado> listaSeleccionServicio() {
        return controlPersistencia.traerEmpleado();
    }

    public Empleado traeEmpleadoServicio(int id) {
        return controlPersistencia.empleadoPorId(id);
    }
    public void editarEmpleadoServicio(Empleado empleado){
        if (existeEmpleadoPorIdServicio(empleado.getId())){
            controlPersistencia.editarEmpleado(empleado);
            System.out.println("|  Empleado con id " + empleado.getId() + " ha sido actualizado");
        }
    }

    public void eliminarEmpleadoServicio(int id) {
        if (existeEmpleadoPorIdServicio(id)){
            controlPersistencia.eliminarEmpleado(id);
            System.out.println("|  Empleado con el ID "+ id +" elimindo del sistema");
        }
    }

    public Boolean existeEmpleadoPorIdServicio(int id) {
        if (!controlPersistencia.existeEmpleadoPorId(id)) {
            System.err.println("|  No se encontró ningún empleado con el ID: " + id);
            return false;
        }
        return true;
    }
}
