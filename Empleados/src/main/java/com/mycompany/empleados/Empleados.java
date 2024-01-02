package com.mycompany.empleados;
import com.mycompany.empleados.manager.ManagerEmpleados;

/**
 *
 * @author jeiny
 */
public class Empleados {
    public static void main(String[] args) {

        ManagerEmpleados managerEmpleados;

        try {
            managerEmpleados = new ManagerEmpleados();
            managerEmpleados.menu();
        }catch (Exception e){
            System.err.println("|  Error : " + e.getMessage());
        }
    }
}
