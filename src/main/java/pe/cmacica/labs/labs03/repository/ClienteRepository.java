package pe.cmacica.labs.labs03.repository;

import pe.cmacica.labs.labs03.dominio.Cliente;

import java.util.List;

public interface ClienteRepository {

    List<Cliente> listar();
    Cliente listar(int id);
    int eliminar(int id);
    int actualizar(Cliente cliente);
}
