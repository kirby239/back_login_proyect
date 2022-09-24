package mi.login.com.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Getter
@Setter
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
