package mi.login.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Setter
@Table(name = "roles")
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idrol")
    private Long idrol;

    @Column(name = "rol", nullable = false)
    private String rol;
}
