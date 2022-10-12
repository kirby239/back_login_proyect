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

import mi.login.com.entity.Roles;
import mi.login.com.repository.RolesRepository;

@RestController
@RequestMapping(path = "rest/v1/roles")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8086" })
public class RolesController {

    @Autowired
    private RolesRepository repository;

    @GetMapping("/listarRoles")
    public List<Roles> listarRoles() {
        return repository.findAll();
    }

    @PostMapping("/registrarRoles")
    public ResponseEntity<Map<String, Object>> registrarRoles(@RequestBody Roles roles) throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Roles> r = repository.findById(roles.getIdrol());
        try {
            if (r.isPresent()) {
                msg.put("Mensaje", "El Rol Ya Existe");
            } else {
                Roles obj = repository.save(roles);
                msg.put("Mensaje", "Rol registrado correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error crear Roles " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping("/editarRoles")
    public ResponseEntity<Map<String, Object>> editarRoles(@RequestBody Roles roles) throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Roles> r = repository.findById(roles.getIdrol());
        try {
            if (r.isPresent()) {
                Roles obj = repository.saveAndFlush(roles);
                msg.put("Mensaje", "Roles Actualizado correctamente");
            } else {
                msg.put("Mensaje", "Roles no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error editar Roles " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }
}
