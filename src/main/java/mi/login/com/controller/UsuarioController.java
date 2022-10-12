package mi.login.com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mi.login.com.entity.Usuario;
import mi.login.com.repository.UsuarioRepository;

@RestController
@RequestMapping(path = "rest/v1/usuarios")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8086" })
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/listarUsuarios")
    public List<Usuario> listarUsuario() {
        return repository.findAll();
    }

    @PostMapping("/registrarUsuario")
    public ResponseEntity<Map<String, Object>> registrarUsuario(@RequestBody Usuario usuario) throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Usuario> u = repository.findById(usuario.getIdUsuario());
        try {
            if (u.isPresent()) {
                msg.put("Mensaje", "El Usuario Ya Existe");
            } else {
                Usuario obj = repository.save(usuario);
                msg.put("Mensaje", "Usuario registrado correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error crear Usuario " + e.getMessage());
        }
        // }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping("/editarUsuario/{id}")
    public ResponseEntity<Map<String, Object>> editarUsuario(@RequestBody Usuario usuario) throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Usuario> u = repository.findById(usuario.getIdUsuario());
        try {
            if (u.isPresent()) {
                Usuario obj = repository.saveAndFlush(usuario);
                msg.put("Mensaje", "Usuario Actualizado correctamente");
            } else {
                msg.put("Mensaje", "Usuario no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error editar Usuario " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @DeleteMapping("/eliminarUsuario/{idusuario}")
    public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable("idusuario") Long idusuario)
            throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Usuario> u = repository.findById(idusuario);

        try {
            if (u.isPresent()) {
                repository.deleteById(idusuario);
                msg.put("Mensaje", "Usuario Eliminado correctamente");
            } else {
                msg.put("Mensaje", "Usuario no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error eliminar Usuario " + e.getMessage());
        }
        return ResponseEntity.ok(msg);
    }
}
