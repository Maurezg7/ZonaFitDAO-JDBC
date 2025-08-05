package Zonafit.datos;

import Zonafit.conexion.conexion;
import Zonafit.dominio.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        EntityManager em = conexion.getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c ORDER BY c.id", Cliente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        EntityManager em = conexion.getEntityManager();
        try {
            Cliente clienteEncontrado = em.find(Cliente.class, cliente.getId());
            if (clienteEncontrado != null) {
                cliente.setName(clienteEncontrado.getName());
                cliente.setSurname(clienteEncontrado.getSurname());
                cliente.setMembresia(clienteEncontrado.getMembresia());
                return true;
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al agregar cliente: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al modificar cliente: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente clienteAEliminar = em.find(Cliente.class, cliente.getId());
            if (clienteAEliminar != null) {
                em.remove(clienteAEliminar);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
}