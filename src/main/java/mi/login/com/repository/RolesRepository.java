package mi.login.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mi.login.com.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
