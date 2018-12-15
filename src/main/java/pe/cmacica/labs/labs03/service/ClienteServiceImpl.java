package pe.cmacica.labs.labs03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listar() {
        return clienteRepository.listar();
    }

    @Override
    public Cliente listar(int id) {
        return clienteRepository.listar(id);
    }

    @Override
    public int eliminar(int id) {
        return clienteRepository.eliminar(id);
    }

    @Override
    public int actualizar(Cliente cliente) {
        return clienteRepository.actualizar(cliente);
    }

    @Override
    public void insertar(Cliente cliente) {
        clienteRepository.insertar(cliente);
    }

    @Override
    @Transactional
    public void insertar(List<Cliente> clientes) {
        clientes.forEach(cliente -> {

            clienteRepository.insertar(cliente);

            if (cliente.getPaterno().equalsIgnoreCase("ZEGARRA")){
                throw new RuntimeException();
            }
        });
    }

    @Override
    @Transactional
    public void transferir(String cuentaOri, String cuentaDest, double monto) {

        clienteRepository.debitarCuenta(cuentaOri,monto);
        clienteRepository.abonarCuenta(cuentaDest,monto);
    }
}
