package pe.cmacica.labs.labs03.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.cmacica.labs.labs03.controller.dto.ClientesDTO;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.service.ClienteService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public HttpEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public HttpEntity<Cliente> listar(@PathVariable("id") int id){

        if (id == 5) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clienteService.listar(id));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
    @PostMapping
    public HttpEntity<String> guardar(@RequestBody Cliente cliente){

        if(StringUtils.isBlank(cliente.getNombre())){
            return ResponseEntity.badRequest().build();
        }

        clienteService.insertar(cliente);

        LOGGER.debug("{}",cliente.getId());
        LOGGER.debug(cliente.getNombre());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/batch")
    public HttpEntity<String> guardar(@Valid @RequestBody ClientesDTO /*List<Cliente>*/ clientes){

        clienteService.insertar(clientes.getClientes());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<String> actualizar(@PathVariable("id") int id,
                                         @RequestBody Cliente cliente){

        cliente.setId(id);
        if (clienteService.actualizar(cliente) == 0){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<String> eliminar(@PathVariable("id") int id){

        if (clienteService.eliminar(id) == 0){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().build();
        }
    }
}