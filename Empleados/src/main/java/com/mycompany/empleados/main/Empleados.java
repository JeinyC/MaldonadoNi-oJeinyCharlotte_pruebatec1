/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.empleados.main;
import com.mycompany.empleados.exceptions.InvalidDataException;
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
        }catch (InvalidDataException e){
            System.err.println("|  Error : " + e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("|  Error : " + e.getMessage());
        }
    }
}
