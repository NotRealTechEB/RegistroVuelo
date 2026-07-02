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

    @Column(name = "codigoVuelo", nullable=false, length = 12)
    private String codigoVuelo;

    @Column(name = "rutPiloto", nullable=false, length = 12)
    private String rutPiloto;

    @Column(name = "tiempoTotalMinutos", nullable=false)
    private int tiempoTotal;

    @Column(name = "alturafinal", nullable=false)
    private int altFinal;

    @Column(name = "detallesIncidente", length=255)
    private String detIncidente;

    @Column(name = "tokenBitacora", length=50)
    private String tokBitacora;


}
