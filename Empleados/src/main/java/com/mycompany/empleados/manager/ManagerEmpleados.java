package com.mycompany.empleados.manager;

import com.mycompany.empleados.exceptions.InvalidDataException;
import com.mycompany.empleados.model.Empleado;
import com.mycompany.empleados.service.ServiciosEmpleados;

import java.time.LocalDate;
import java.util.*;

public class ManagerEmpleados {
    ServiciosEmpleados serviciosEmpleados = new ServiciosEmpleados();
    InvalidDataException invalidDataException = new InvalidDataException("");

    public void menu() throws InvalidDataException {

        boolean flag = true;
        Scanner sc = new Scanner(System.in);

        while (flag) {

            List<String> opciones = List.of(new String[]{
                    "1. Agregar un nuevo empleado",
                    "2. Mostrar lista de empleados ",
                    "3. Actualizar datos de un empleado",
                    "4. Eliminar un empleado",
                    "5. Mostrar empleados por cargo",
                    "6. Salir"
            });

            String separador = "+----------------------------------------+";
            String titulo = "|         GESTION DE EMPLEADOS         |";
            String formatoTitulo = String.format("|%-40s|", titulo);
            System.out.println(separador);
            System.out.println(formatoTitulo);
            System.out.println(separador);

            for (String opcion : opciones) {
                String formatoOpcion = String.format("|  %-38s|", opcion);
                System.out.println(formatoOpcion);
            }

            System.out.println(separador);
            System.out.println("Seleccione una opcion:");

            String menuOpcion = sc.nextLine();

            switch (invalidDataException.validarEntradaMenu(menuOpcion)) {
                case 1:
                    agregarEmpleado();
                    break;
                case 2:
                    listaSeleccion("|         LISTA DE EMPLEADOS         |", 1);
                    break;
                case 3:
                    listaSeleccion("|        SELECCIONE UN EMPLEADO        |", 2);
                    int id = almacenarID();
                    if (serviciosEmpleados.existeEmpleadoPorIdServicio(id)) {
                        listaSeleccion("|  SELECCIONE UN DATO PARA ACUALIZAR   |", 4);
                        menuActualizarEmpleado(id);
                    }
                    break;
                case 4:
                    listaSeleccion("|        SELECCIONE UN EMPLEADO        |", 2);
                    eliminarEmpleado();
                    break;
                case 5:
                    listaSeleccion("|         SELECCIONE UN CARGOS         |", 3);
                    mostrarListaEmpleadosPorCargo();
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.err.println("Opción no válida. Por favor, selecciona una opción válida.");
                    break;
            }
        }
    }

    private void agregarEmpleado() throws InputMismatchException, InvalidDataException {

        Scanner sc = new Scanner(System.in);
        Empleado empleado = new Empleado();
        LocalDate fechaActual = LocalDate.now();


        System.out.println("Introduce el nombre");
        empleado.setNombre(sc.nextLine());
        System.out.println("Introduce el apellido");
        empleado.setApellido(sc.nextLine());
        System.out.println("Introduce el Cargo");
        empleado.setCargo(sc.nextLine());
        System.out.println("Introduce el salario");
        empleado.setSalario(invalidDataException.validarDouble(sc.nextLine()));
        empleado.setFechaInicio(fechaActual);

        invalidDataException.validarDatos(empleado.getNombre(), empleado.getApellido(), empleado.getCargo(), empleado.getSalario(), empleado.getFechaInicio());
        serviciosEmpleados.agregarEmpleadoServicio(empleado);
    }

    private void listaSeleccion(String titulo, int idLista) {
        String separador = "+----------------------------------------+";
        String formatoTitulo = String.format("|%-30s|", titulo);
        System.out.println(separador);
        System.out.println(formatoTitulo);
        System.out.println(separador);

        for (Empleado empleado : serviciosEmpleados.listaSeleccionServicio()) {
            if (idLista == 1) {
                String formatoOpcion = String.format("|  %-38s|", empleado);
                System.out.println(formatoOpcion);
            }
            if (idLista == 2) {
                String formatoOpcion = String.format("|  %-38s|", empleado.seleccionToString());
                System.out.println(formatoOpcion);
            }
        }
        if (idLista == 3) {
            for (Map.Entry<Integer, String> entry : cargoExistente().entrySet()) {
                String formatoOpcion = String.format("|  %-38s|", entry.getKey() + ". " + entry.getValue());
                System.out.println(formatoOpcion);
            }
        }
        if (idLista == 4) {
            ArrayList<String> atributosEmpleados = new ArrayList<>(Arrays.asList("1. Nombre", "2. Apellido", "3. Cargo", "4. Salario"));
            for (String atributos : atributosEmpleados) {
                String formatoOpcion = String.format("|  %-38s|", atributos);
                System.out.println(formatoOpcion);
            }
        }
        System.out.println(separador);
    }

    private void mostrarListaEmpleadosPorCargo() throws InvalidDataException {

        List<Empleado> listaEmpleados = serviciosEmpleados.listaSeleccionServicio();
        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione un cargo: ");
        int id = invalidDataException.validarEntradaMenu(sc.nextLine());

        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCargo().equalsIgnoreCase(cargoExistente().get(id))) {
                String formatoOpcion = String.format("|  %-38s|", empleado);
                System.out.println(formatoOpcion);
            }
        }
    }

    private Map<Integer, String> cargoExistente() {

        List<Empleado> listaEmpleados = serviciosEmpleados.listaSeleccionServicio();
        Set<String> cargosAgregados = new HashSet<>();
        Map<Integer, String> listaCargosUnicos = new HashMap<>();
        int count = 1;

        for (Empleado empleado : listaEmpleados) {
            String cargoActual = empleado.getCargo().toLowerCase();
            if (!cargosAgregados.contains(cargoActual)) {
                listaCargosUnicos.put(count++, empleado.getCargo());
                cargosAgregados.add(cargoActual);
            }
        }
        return listaCargosUnicos;
    }

    private int almacenarID() throws InvalidDataException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione un identificador: ");
        return invalidDataException.validarEntradaMenu(sc.nextLine());
    }

    private void menuActualizarEmpleado(int idEmpleado) throws InvalidDataException {

        Scanner sc = new Scanner(System.in);
        Empleado empleadoEditar;
        empleadoEditar = serviciosEmpleados.traeEmpleadoServicio(idEmpleado);

        System.out.println("|  Empleado elegido: " + empleadoEditar);
        System.out.println("Seleccione un dato para actualizarlo: ");
        String opcion = sc.nextLine();
        int id = invalidDataException.validarEntradaMenu(opcion);

        switch (id) {
            case 1:
                actualizarNombre(empleadoEditar);
                break;
            case 2:
                actualizarApellido(empleadoEditar);
                break;
            case 3:
                actualizarCargo(empleadoEditar);
                break;
            case 4:
                actualizarSalario(empleadoEditar);
                break;
            default:
                System.err.println("|  Opción invalida");
                break;
        }
    }

    private void actualizarNombre(Empleado empleadoEditar) throws InvalidDataException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre: ");
        empleadoEditar.setNombre(invalidDataException.validarString(sc.nextLine()));
        serviciosEmpleados.editarEmpleadoServicio(empleadoEditar);
    }

    private void actualizarApellido(Empleado empleadoEditar) throws InvalidDataException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el apellido: ");
        empleadoEditar.setApellido(invalidDataException.validarString(sc.nextLine()));
        serviciosEmpleados.editarEmpleadoServicio(empleadoEditar);
    }

    private void actualizarCargo(Empleado empleadoEditar) throws InvalidDataException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el cargo:");
        empleadoEditar.setCargo(invalidDataException.validarString(sc.nextLine()));
        serviciosEmpleados.editarEmpleadoServicio(empleadoEditar);
    }

    private void actualizarSalario(Empleado empleadoEditar) throws InvalidDataException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el salario:");
        empleadoEditar.setSalario(invalidDataException.validarDouble(sc.nextLine()));
        serviciosEmpleados.editarEmpleadoServicio(empleadoEditar);
    }

    private void eliminarEmpleado() throws InvalidDataException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione un empleado para eliminarlo:");
        String id = sc.nextLine();
        serviciosEmpleados.eliminarEmpleadoServicio(invalidDataException.validarEntradaMenu(id));
    }
}

