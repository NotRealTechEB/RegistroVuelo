package cl.dgac.registro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateRegistroVuelo (

    //RUT PILOTO
    @NotNull(message="Debe ingresar el rut del piloto") 
    @Size(min=9, max=10, message = "El rut debe contener entre 9 y 10 caracteres")String rutPiloto,

    //Codigo de vuelo
    @NotNull(message="Debe ingresar el codigo de vuelo") 
    @Size(max=12, message="El codigo de vuelo no puede ser mayor a 12 caracteres") String codVuelo,

    //Altura máxima
    @Positive(message = "La altura máxima no puede ser negativa o cero.") Double altMaxFinal,

    //Tiempo estimado
    @Positive(message = "El tiempo estimado no puede ser negativo o cero.") Integer tiTotal,

    //Reporte de incidente
    @Size(max=255, message="El reporte de incidentes no puede superar los 255 caracteres.") String repInc,

    String psGPS,
    String fechaPV,

    String numeroRegistro,

    String region,
    String rutEmpMandante,
    
    String tokBitacora


)
{
}
