package mi.login.com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mi.login.com.repository.Detalle_ClienteRepository;
import mi.login.com.entity.Detalle_Cliente;;

@RestController
@RequestMapping(path = "rest/v1/cliente")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8086" })

public class Detalle_ClienteController {
    @Autowired
    private Detalle_ClienteRepository repository;

    @GetMapping("/listarCliente")
    public List<Detalle_Cliente> listarCliente() {
        return repository.findAll();
    }

    @PostMapping("/registrarCliente")
    public ResponseEntity<Map<String, Object>> registrarcliente(@RequestBody Detalle_Cliente cliente) throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Detalle_Cliente> u = repository.findById(cliente.getIdDetalle_Cliente());
        try {
            if (u.isPresent()) {
                msg.put("Mensaje", "El Cliente Ya Existe");
            } else {
                Detalle_Cliente obj = repository.save(cliente);
                msg.put("Mensaje", "Cliente registrado correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error crear Cliente " + e.getMessage());
        }
        // }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping("/editarCliente/{id}")
    public ResponseEntity<Map<String, Object>> editarcliente(@RequestBody Detalle_Cliente cliente) throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Detalle_Cliente> u = repository.findById(cliente.getIdDetalle_Cliente());
        try {
            if (u.isPresent()) {
                Detalle_Cliente obj = repository.saveAndFlush(cliente);
                msg.put("Mensaje", "cliente Actualizado correctamente");
            } else {
                msg.put("Mensaje", "cliente no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error editar cliente " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
