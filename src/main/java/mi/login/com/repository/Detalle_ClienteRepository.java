package mi.login.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mi.login.com.entity.Detalle_Cliente;

@Repository
public interface Detalle_ClienteRepository extends JpaRepository<Detalle_Cliente,Long> {
    
}
