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

    @Column(name = "idPlanVuelo", nullable=false)
    private int idPlanVuelo;

    @Column(name = "fechaRV", nullable=false)
    private LocalDateTime fechaRV;

    @Column(name = "alturaMaxFinal", nullable=false)
    private double altMaxFinal;

    @Column(name = "tiempoTotalMinutos", nullable=false)
    private int tiTotal;

    @Column(name = "validIncidente", nullable=false)
    private boolean valIncidente;

    @Column(name = "detallesIncidente", length=255)
    private String detIncidente;

    @Column(name = "tokenBitacora", nullable=false, length=50)
    private String tokBitacora;

    

}
