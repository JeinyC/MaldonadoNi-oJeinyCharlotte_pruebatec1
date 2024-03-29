# Prueba Técnica Nº 1 - Java Básico

![Título](Empleados/docs/images/Titulo.png)

Este proyecto de prueba técnica evalúa los conocimientos en Java, incluyendo aspectos de sintaxis, estructuras repetitivas, estructuras selectivas, manejo de colecciones y operaciones CRUD (Crear, Leer, Actualizar y Borrar). Se utiliza JPA (Java Persistence API) para interactuar con una base de datos, implementando un sistema de gestión de empleados.

## Contenidos
1. [Funcionalidades Principales](#funcionalidades-principales)
2. [Uso del Programa](#uso-del-programa)
3. [Supuestos](#supuestos)
4. [Estructura del Proyecto](#estructura-del-proyecto)
5. [Comentario del Programa](#comentario-del-programa)

## Funcionalidades Principales
> Descripción de las acciones principales que el sistema.

- **Agregar un Nuevo Empleado :** Permite añadir información de un nuevo empleado al sistema.  
- **Mostrar Lista de Empleados :** Visualiza la lista completa de empleados registrados.  
- **Actualizar Datos de un Empleado :** Permite modificar información específica de un empleado existente.  
- **Eliminar un Empleado :** Elimina un empleado del sistema.  
- **Mostrar Empleados por Cargo :** Muestra la lista de empleados filtrada por un cargo específico.  
- **Salir :** Finaliza la ejecución del programa.

## Uso del Programa
> El programa de gestión de empleados permite al usuario realizar diversas acciones para administrar la información de los empleados de una empresa. Al iniciar el programa, se presenta un menú interactivo desde > donde el usuario puede realizar las siguientes acciones.

### 1. Agregar un Nuevo Empleado
Seleccionando esta opción, el usuario puede ingresar los datos de un nuevo empleado, como nombre, apellido, cargo y salario. El programa valida los datos ingresados antes de almacenarlos en la base de datos.

### 2. Mostrar Lista de Empleados
Al seleccionar esta opción, el programa muestra una lista detallada de todos los empleados registrados en la base de datos. Los datos incluyen el nombre, apellido, cargo, salario y fecha de inicio.

### 3. Actualizar Datos de un Empleado
El usuario puede seleccionar esta opción para modificar la información de un empleado existente. El programa mostrará una lista de empleados y permitirá seleccionar el empleado cuyos datos se desean actualizar.Luego, se proporcionan opciones para actualizar el nombre, apellido, cargo o salario del empleado seleccionado.

### 4. Eliminar un Empleado
Mediante esta opción, el usuario puede eliminar a un empleado de la base de datos. Se muestra una lista de empleados y se solicita al usuario que seleccione el empleado que desea eliminar.

### 5. Mostrar Empleados por Cargo
Al seleccionar esta opción, se presenta al usuario una lista de cargos existentes en la base de datos. El usuario elige un cargo específico, y el programa muestra todos los empleados asociados a ese cargo.

### 6. Salir
Esta opción permite al usuario finalizar la ejecución del programa y salir del menú interactivo.

## Supuestos
> Condiciones o premisas bajo las cuales se espera que funcione correctamente el software.

- Que al introducir un empleado al sistema de gestión se da por hecho que la fecha de ingreso es el dia de alta en el sistema.
- Que si hay una equivocación y se añade un numero no deseado a campos como nombre, apellido o cargo el usuario es conciente de su fallo y puede modificarlo cuando desee.
- Que es totalmente posible que hayan dos personas con mismmo nombre y apellido, pero no con el mismo ID ya que se genera automaticamente.

## Estructura del Proyecto
> Organización y disposición de los archivos y directorios que componen el proyecto.

### Clase Principal `Empleados`

- **Descripción :** Esta clase principal inicia la aplicación y gestiona la interacción del usuario con el sistema de gestión de empleados.
- **Funcionalidad :**
  - `public static void main(String[] args)`: Método de inicio de la aplicación. Aquí se instancia `ManagerEmpleados` y se invoca el método `menu()`.
  - El bloque `try-catch` maneja excepciones que pueden ocurrir durante la ejecución del programa. Si ocurre un error de `InvalidDataException`, se muestra un mensaje de error personalizado.
    
### Clase `Empleado`

- **Descripción :** Clase que representa la entidad `Empleado` en la base de datos.
- **Funcionalidad :**
  - Anotada con `@Entity` y `@Table` para mapear la clase a la tabla correspondiente en la base de datos.
  - Define atributos correspondientes a los campos de la tabla `Empleado`.
  - Métodos `get` y `set` para acceder y modificar los atributos.
  - Métodos `toString()` y `seleccionToString()` para representar los objetos `Empleado` de manera legible.

### Clase `ManagerEmpleados`

- **Descripción :** Esta clase es el controlador principal que gestiona la lógica de negocios y la interacción con el usuario a través del menú.
- **Funcionalidad :**
  - `menu()`: Presenta un menú interactivo al usuario, permitiendo realizar acciones como agregar, mostrar, actualizar o eliminar empleados.
  - Métodos privados que manejan las diferentes operaciones según la selección del usuario.

### Clase `ServiciosEmpleados` 

- **Descripción :** Esta clase contiene la lógica de servicios relacionados con los empleados, como agregar, mostrar, actualizar o eliminar empleados en la base de datos.
- **Funcionalidad :**
  - Métodos públicos que interactúan con la base de datos y gestionan la información de los empleados.

### Clase `InvalidDataException`

- **Descripción :** Excepción personalizada utilizada para manejar errores relacionados con datos inválidos o incompletos.
- **Funcionalidad :**
  - Define métodos para validar datos ingresados por el usuario y manejar errores en la entrada de datos.
  
### Directorio del archivo SQL
[Archivo SQL](https://github.com/JeinyC/MaldonadoNi-oJeinyCharlotte_pruebatec1/blob/main/Empleados/src/main/resources/db)

## Comentario del Programa
> Descripción detallada de algunos métodos.

### Método `listaSeleccion()` Clase `ManagerEmpleados`
Se encuentra en la clase ManagerEmpleados se utiliza para mostrar una lista de elementos, según el contexto proporcionado.
Este método genera y muestra una lista de elementos dependiendo del parámetro `idLista` proporcionado, imprimiendo cada elemento en un formato específico dentro de un marco visual definido por líneas divisorias y títulos.
![Título](Empleados/docs/images/listaSeleccion.png)

### Método `cargoExistente()` Clase `ManagerEmpleados`
Este método se encarga de obtener los cargos únicos existentes en la lista de empleados y almacenarlos en un HashMap con un identificador numérico.
- listaEmpleados: Obtiene la lista de empleados mediante el método `listaSeleccionServicio()` del servicio de empleados.
- cargosAgregados: Utiliza un conjunto (`Set`) para almacenar los cargos ya agregados y evitar duplicados.
- listaCargosUnicos: Es un mapa (`HashMap`) donde se almacenan los cargos únicos con un identificador numérico.
- Itera sobre la lista de empleados. Si encuentra un cargo que aún no ha sido agregado, lo añade al mapa `listaCargosUnicos` con un identificador numérico y lo agrega al conjunto `cargosAgregados` para evitar duplicados.
Esto hace que en el menú interactivo no se visualice cargos repetidos, de esta forma se minimiza el margen de error.
![Título](Empleados/docs/images/cargoExistente.png)

### Método `seleccionToString()` Clase `ManagerEmpleados`
Este método devuelve una representación simplificada del objeto Empleado que se utiliza para mostrar información básica, como el ID, nombre y apellido del empleado. Es útil cuando se necesitan detalles mínimos para la selección de empleados en algún proceso de interacción con el usuario, como al mostrar una lista de empleados para selección.
![Título](Empleados/docs/images/seleccionToString.png)


