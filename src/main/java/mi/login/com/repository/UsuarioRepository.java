package mi.login.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mi.login.com.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
}
