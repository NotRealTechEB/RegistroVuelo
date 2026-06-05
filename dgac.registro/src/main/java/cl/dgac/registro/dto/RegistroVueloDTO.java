package cl.dgac.registro.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroVueloDTO {
    private double altFinal;
    private int tiTotal;
    private String detInc;

    private String codigoVuelo;
    private String numeroRegistro;
    private LocalDateTime fechaPV;
    private String psGPS;
    private double altMax;
    private String region;
}
