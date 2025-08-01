package datos;

import Zonafit.datos.ClienteDAO;
import Zonafit.datos.IClienteDAO;
import Zonafit.dominio.Cliente;

public class pruebaDatos {
    public static void main(String[] args) {
        IClienteDAO clienteDao = new ClienteDAO();
        // System.out.println("****Listar Clientes****");
        // IClienteDAO clienteDao = new ClienteDAO();
        // var clientes = clienteDao.listarClientes();
        // clientes.forEach(System.out::println);

//        var cliente = new Cliente(1L);
//        System.out.println("Cliente antes de la busqueda: " + cliente);
//        var encontrado = clienteDao.buscarClientePorId(cliente);
//        if(encontrado){
//            System.out.println(cliente.toString());
//        }else{
//            System.out.println("No se encontro registro: " + cliente.getId());
//        }

//        var nuevoCliente = new Cliente("Mateo", "Viroche", 300L);
//        var agregado = clienteDao.agregarCliente(nuevoCliente);
//        if(agregado){
//            System.out.println("Cliente agregado: " + nuevoCliente);
//        }else{
//            System.out.println("No se agrego cliente: " + nuevoCliente);
//        }

//        var modificarCliente = new Cliente(2L, "Francisco", "Liendro", 300L);
//        var modificado = clienteDao.modificarCliente(modificarCliente);
//        if(modificado){
//            System.out.println("Cliente modificado: " + modificarCliente);
//        }else{
//            System.out.println("No se pudo modificar cliente: " + modificarCliente);
//        }

        var eliminarCliente = new Cliente(2L);
        var eliminado = clienteDao.eliminarCliente(eliminarCliente);
        if(eliminado){
            System.out.println("Cliente eliminado: " + eliminarCliente);
        }else{
            System.out.println("No se elimino el cliente: " + eliminarCliente);
        }

        System.out.println("****Listar Clientes****");
        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println);
    }
}
