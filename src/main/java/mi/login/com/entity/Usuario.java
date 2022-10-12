package mi.login.com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Setter
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long idUsuario;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 45)
    private String apellido;

    @Column(name = "estado", nullable = true)
    private String estado;

    @Column(name = "cod_confir", length = 6, updatable = false, nullable = true)
    private String cod_confir;

    // Relacion de Uno a Uno de Detalle_Cliente
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDetalle_cliente")
    private Detalle_Cliente detalleCliente;

    // Relacion de Uno a Uno de Detalle_Cliente
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDetalle_empleado")
    private Detalle_Empleado detalleEmpleado;

    // Relacion de mucho a Uno de Roles
	/* @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns =
	@JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"),
			uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<Roles> roles; 
 */
}
