package Zonafit.presentacion;

import Zonafit.datos.ClienteDAO;
import Zonafit.datos.IClienteDAO;
import Zonafit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        IClienteDAO clienteDao = new ClienteDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);
            }catch (Exception e){
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
        }
    }

    private int mostrarMenu(Scanner consola){
        System.out.print("""
                *** ZONA FIT GYM ***
                1. Listar clientes
                2. Buscar cliente
                3. Agregar cliente
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
                Elije una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {
                // Listar clientes
                System.out.println("---Listado de clientes---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                // Buscar cliente por IO
                System.out.print("Introduce el id del cliente: ");
                var idCliente = Long.parseLong(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if(encontrado){
                    System.out.println("Cliente encontrado: " + cliente);
                }else{
                    System.out.println("Cliente no encontrado: " + cliente);
                }
            }
            case 3 -> {
                // Agregar cliente
                System.out.println("--- Agregar Cliente ---");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Long.parseLong(consola.nextLine());

                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if(agregado){
                    System.out.println("Cliente agregado: " + cliente);
                }else{
                    System.out.println("Cliente no agregado: " + cliente);
                }
            }
            case 4 -> {
                // Modificar Cliente
                System.out.println("--- Modificar Cliente ---");
                System.out.print("ID Cliente: ");
                var idCliente = Long.parseLong(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Mebresia: ");
                var membresia = Long.parseLong(consola.nextLine());

                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if(modificado){
                    System.out.println("Cliente modificado: " + cliente);
                }else{
                    System.out.println("No se pudo modificar cliente: " + cliente);
                }
            }
            case 5 -> {
                // Eliminar cliente
                System.out.println("--- Eliminar Cliente ---");
                System.out.print("ID Cliente: ");
                var idCliente = Long.parseLong(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if(eliminado){
                    System.out.println("Cliente eliminado: " + cliente);
                }else{
                    System.out.println("No se elimino cliente: " + cliente);
                }
            }
            case 6 -> {
                System.out.println("Hasta pronto. Programa creado by Mauro Leonel Gomez");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida: " + opcion);
        }
        return salir;
    }
}
