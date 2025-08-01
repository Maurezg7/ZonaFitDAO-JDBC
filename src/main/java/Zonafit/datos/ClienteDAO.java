package Zonafit.datos;

import Zonafit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static Zonafit.conexion.conexion.getConexion;

public class ClienteDAO implements IClienteDAO{

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConexion();
        var sql = "SELECT * FROM cliente order by id";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setName(rs.getString("nombre"));
                cliente.setSurname(rs.getString("apellido"));
                cliente.setMembresia(rs.getLong("membresia"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        finally {
            try{
                conn.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var conn = getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Math.toIntExact(cliente.getId()));
            rs = ps.executeQuery();
            if(rs.next()){
                cliente.setName(rs.getString("nombre"));
                cliente.setSurname(rs.getString("apellido"));
                cliente.setMembresia(rs.getLong("membresia"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al recuperar cliente por id: " + e.getMessage());
        }finally {
            try {
                conn.close();
            }catch (Exception e){
                System.out.println("Error al cerrrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conn = getConexion();
        String sql = "INSERT INTO cliente(nombre, apellido, membresia)" + "VALUES (?, ?, ?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getName());
            ps.setString(2, cliente.getSurname());
            ps.setInt(3, Math.toIntExact(cliente.getMembresia()));
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }finally{
            try{
                conn.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conn = getConexion();
        var sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=?" + " WHERE id = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getName());
            ps.setString(2, cliente.getSurname());
            ps.setLong(3, cliente.getMembresia());
            ps.setLong(4, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }finally {
            try{
                conn.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conn = getConexion();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }finally {
            try{
                conn.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }
}
