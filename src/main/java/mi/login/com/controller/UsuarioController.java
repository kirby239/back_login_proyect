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
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/listarUsuarios")
    public List<Usuario> listarUsuario() {
        return repository.findAll();
    }

    @PostMapping("/registrarUsuario")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> registrarUsuario(@RequestBody Usuario usuario) throws Exception {
        Map<String, Object> response = new HashMap<>();
        Optional<Usuario> u = repository.findById(usuario.getIdUsuario());
        try {
            if (u != null) {
                response.put("Mensaje", "El Usuario Ya Existe");
            } else {
                Usuario obj = repository.save(usuario);
                response.put("Mensaje", "Usuario registrado correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("mensaje", "Error Crear Usuario " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editarUsuario/{id}")
    public ResponseEntity<Map<String, Object>> editarUsuario(@RequestBody Usuario usuario) throws Exception {
        Map<String, Object> response = new HashMap<>();
        Optional<Usuario> u = repository.findById(usuario.getIdUsuario());
        try {
            if (u != null) {
                Usuario obj = repository.saveAndFlush(usuario);
                response.put("Mensaje", "Usuario Actualizado correctamente");
            } else {
                response.put("Mensaje", "Usuario Error Actualizar");

            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("mensaje", "Error editar Usuario " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable("idusuario") Long idusuario)
            throws Exception {
        Map<String, Object> response = new HashMap<>();
        List<Usuario> usuarioX = repository.findAll();
        try {
            if (idusuario != null) {
                usuarioX.stream().filter(item -> (item.getIdUsuario() == idusuario)).findFirst();
                repository.deleteById(idusuario);
                response.put("Mensaje", "Usuario Eliminado correctamente");
            } else {
                response.put("Mensaje", "Usuario Error Eliminado");

            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("mensaje", "Error editar Usuario " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
