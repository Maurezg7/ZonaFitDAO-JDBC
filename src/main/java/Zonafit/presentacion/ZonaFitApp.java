package Zonafit.presentacion;

import Zonafit.datos.IClienteDAO;
import Zonafit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    private final Scanner consola;
    private final IClienteDAO clienteDao;

    public ZonaFitApp(IClienteDAO clienteDao) {
        this.consola = new Scanner(System.in);
        this.clienteDao = clienteDao;
    }

    public void iniciar() {
        boolean salir = false;

        while(!salir) {
            try {
                mostrarMenu();
                int opcion = Integer.parseInt(consola.nextLine());
                salir = procesarOpcion(opcion);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        System.out.print("""
            \n*** ZONA FIT GYM ***
            1. Listar clientes
            2. Buscar cliente
            3. Agregar cliente
            4. Modificar cliente
            5. Eliminar cliente
            6. Salir
            Elije una opción:\s""");
    }

    private boolean procesarOpcion(int opcion) {
        return switch (opcion) {
            case 1 -> listarClientes();
            case 2 -> buscarCliente();
            case 3 -> agregarCliente();
            case 4 -> modificarCliente();
            case 5 -> eliminarCliente();
            case 6 -> {
                System.out.println("Hasta pronto. Programa creado by Mauro Leonel Gomez");
                yield true;
            }
            default -> {
                System.out.println("Opción no válida");
                yield false;
            }
        };
    }

    private boolean listarClientes() {
        System.out.println("\n--- Listado de clientes ---");
        clienteDao.listarClientes().forEach(System.out::println);
        return false;
    }

    private boolean buscarCliente() {
        System.out.println("\n--- Buscar Cliente ---");
        Long id = leerLong("Introduce el ID del cliente: ");
        Cliente cliente = new Cliente(id);

        if(clienteDao.buscarClientePorId(cliente)) {
            System.out.println("Cliente encontrado: " + cliente);
        } else {
            System.out.println("Cliente no encontrado");
        }
        return false;
    }

    private boolean agregarCliente() {
        System.out.println("\n--- Agregar Cliente ---");
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        Long membresia = leerLong("Membresía: ");

        Cliente cliente = new Cliente(nombre, apellido, membresia);

        if(clienteDao.agregarCliente(cliente)) {
            System.out.println("Cliente agregado: " + cliente);
        } else {
            System.out.println("No se pudo agregar el cliente");
        }
        return false;
    }

    private boolean modificarCliente() {
        System.out.println("\n--- Modificar Cliente ---");
        Long id = leerLong("ID Cliente: ");
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        Long membresia = leerLong("Membresía: ");

        Cliente cliente = new Cliente(id, nombre, apellido, membresia);

        if(clienteDao.modificarCliente(cliente)) {
            System.out.println("Cliente modificado: " + cliente);
        } else {
            System.out.println("No se pudo modificar el cliente");
        }
        return false;
    }

    private boolean eliminarCliente() {
        System.out.println("\n--- Eliminar Cliente ---");
        Long id = leerLong("ID Cliente: ");
        Cliente cliente = new Cliente(id);

        if(clienteDao.eliminarCliente(cliente)) {
            System.out.println("Cliente eliminado");
        } else {
            System.out.println("No se pudo eliminar el cliente");
        }
        return false;
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return consola.nextLine();
    }

    private Long leerLong(String mensaje) {
        while(true) {
            try {
                System.out.print(mensaje);
                return Long.parseLong(consola.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido");
            }
        }
    }
}