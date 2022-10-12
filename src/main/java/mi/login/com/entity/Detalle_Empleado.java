package mi.login.com.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

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
@Table(name = "detalle_empleado")
public class Detalle_Empleado implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalle_empleado")
    private Long idDetalle_empleado;

    @Column(name = "dni", length = 8, nullable = false)
    private int dni;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "provincias")
    private String provincias;

    @Column(name = "profesion")
    private String profesion;

    @Column(name = "tiempo")
    private String Tiempo;

    @Column(name = "fecrea")
    //@Temporal(TemporalType.TIMESTAMP)
    //@CreatedDate
    private Date  fecrea;

    @Column(name = "horcrea")
    private LocalTime horcrea;

    /* @OneToOne(mappedBy = "detalleEmpleado", fetch = FetchType.LAZY)
    private Usuario usuario; */
}
