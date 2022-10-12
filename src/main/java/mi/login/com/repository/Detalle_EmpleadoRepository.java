package mi.login.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mi.login.com.entity.Detalle_Empleado;

@Repository
public interface Detalle_EmpleadoRepository extends JpaRepository<Detalle_Empleado, Long> {

}
