package cl.dgac.registro.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroResponseDTO {
    private int idRV;
    private int idPV;
    private LocalDateTime feRV;
    private double altFinal;
    private int tiTotal;
    private boolean vInc;
    private String detInc;

    private PlanVueloResponseDTO planVuelo;
}
