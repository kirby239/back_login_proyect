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

import mi.login.com.repository.Detalle_EmpleadoRepository;
import mi.login.com.entity.Detalle_Empleado;

@RestController
@RequestMapping(path = "rest/v1/empleado")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8086" })

public class Detalle_EmpleadoController {

    @Autowired
    private Detalle_EmpleadoRepository repository;

    @GetMapping("/listarEmpleado")
    public List<Detalle_Empleado> listarEmpleados() {
        return repository.findAll();
    }

    @PostMapping("/registrarEmpleado")
    public ResponseEntity<Map<String, Object>> registrarEmpleado(@RequestBody Detalle_Empleado empleado)
            throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Detalle_Empleado> u = repository.findById(empleado.getIdDetalle_empleado());
        try {
            if (u.isPresent()) {
                msg.put("Mensaje", "El Empleado Ya Existe");
            } else {
                Detalle_Empleado obj = repository.save(empleado);
                msg.put("Mensaje", "Empleado registrado correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error crear Empleado " + e.getMessage());
        }
        // }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping("/editarEmpleado/{id}")
    public ResponseEntity<Map<String, Object>> editarEmpleado(@RequestBody Detalle_Empleado empleado) throws Exception {
        Map<String, Object> msg = new HashMap<>();
        Optional<Detalle_Empleado> u = repository.findById(empleado.getIdDetalle_empleado());
        try {
            if (u.isPresent()) {
                Detalle_Empleado obj = repository.saveAndFlush(empleado);
                msg.put("Mensaje", "Empleado Actualizado correctamente");
            } else {
                msg.put("Mensaje", "Empleado no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.put("mensaje", "Error editar Empleado " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
