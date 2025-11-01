package org.team.sivi.Model;
import jakarta.persistence.*;
import java.util.List;

//Creamos la clase rol
//Indicamos con @Entity que va a ser una entidad en la bd
@Entity
public class Rol {
    //Atributos de la clase
    //Indicamos que el atributo id va a ser nuestro primary key en la bd y que se va a generar automaticamente y autoIncrementandose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
   //Indicamos que Rol va a tener una relacion N a N con usuario y que usuario será el dueño de la relación
    @ManyToMany(mappedBy = "listaRol")
    private List<Usuario>listaUsuario;

    //Creamos el constructor vacío
    public Rol() {
    }

//Creamos el constructor con sus atributos
    public Rol(Long id, String nombre, List<Usuario> listaUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.listaUsuario = listaUsuario;
    }
//Creamos los getters y setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
}
