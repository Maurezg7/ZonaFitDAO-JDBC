package Zonafit.dominio;

import java.util.Objects;

public class Cliente {
    private Long id;
    private String name;
    private String surname;
    private Long membresia;

    public Cliente(){}

    public Cliente(Long id){
        this.id = id;
    }

    public Cliente(String name, String surname, Long membresia){
        this.name = name;
        this.surname = surname;
        this.membresia = membresia;
    }

    public Cliente(Long id, String name, String surname, Long membresia){
        this(name, surname, membresia);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getMembresia() {
        return membresia;
    }

    public void setMembresia(Long membresia) {
        this.membresia = membresia;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", membresia=" + membresia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(name, cliente.name) && Objects.equals(surname, cliente.surname) && Objects.equals(membresia, cliente.membresia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, membresia);
    }
}
