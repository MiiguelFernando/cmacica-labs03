package pe.cmacica.labs.labs03.dominio;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class Cliente {

    private int id;

    @NotNull
    @Min(18)
    @Max(100)
    private int edad;

    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @NotNull
    @Size(min=5, max=100)
    private String nombre;

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    @NotNull
    @Size(min=5, max=100)
    private String paterno;

    @NotNull
    @Size(min=5, max=100)
    private String materno;
}
