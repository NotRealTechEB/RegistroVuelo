package cl.dgac.registro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroVueloDTO {
    
    private PlanVueloDTO planvuelo;

    private double altFinal;
    private int tiTotal;
    private String detInc;
}
