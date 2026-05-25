package cl.dgac.registro.model;

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

    @Column(name = "ubicacionGPS", nullable=false, length = 30)
    private String pscGPS;

    @Column(name = "fechaRV", nullable=false)
    private int fechaRV;

    @Column(name = "alturaMaxFinal", nullable=false)
    private double altMaxFinal;

    @Column(name = "tiempoTotal", nullable=false)
    private double tiTotal;

    @Column(name = "region", nullable=false, length=60)
    private String region;
}
