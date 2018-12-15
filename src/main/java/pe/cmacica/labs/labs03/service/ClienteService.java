package pe.cmacica.labs.labs03.service;

import pe.cmacica.labs.labs03.dominio.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listar();
    Cliente listar(int id);
    int eliminar(int id);
    int actualizar(Cliente cliente);
    void insertar(Cliente cliente);
    void insertar(List<Cliente> clientes);

    void transferir(String cuentaOri, String cuentaDest, double monto);

}
