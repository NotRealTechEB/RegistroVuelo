package cl.dgac.registro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanVueloResponse {
    private String psGPS;
    private int fechaPDV;
    private double altMax;
    private double tiEst;
    private String region;
}
