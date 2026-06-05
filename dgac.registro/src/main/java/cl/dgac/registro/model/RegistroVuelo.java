package cl.dgac.registro.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RegistroVuelo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVuelo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idRV")
    private int idRV;

    @Column(name = "rutPiloto", nullable = false, length = 12)
    private int rutPiloto;

    @Column(name = "numeroRegistro")
    private String numeroRegistro;

    @Column(name="rutEmpresaMandante", length=15)
    private String rutEmpMandante;

    @Column(name = "codigoVuelo", nullable=false, length = 12)
    private String codigoVuelo;

    @Column(name = "tiempoTotalMinutos", nullable=false)
    private int tiempoTotal;

    @Column(name = "ubicacionGPS", length = 30)
    private String psGPS;

    @Column(name = "fechaPV")
    private LocalDateTime fechaPV;

    @Column(name = "alturaMaxima")
    private double altMax;

    @Column(name = "region", length=18)
    private String region;

    @Column(name = "detallesIncidente", length=255)
    private String detIncidente;

    @Column(name = "tokenBitacora", length=50)
    private String tokBitacora;


}
