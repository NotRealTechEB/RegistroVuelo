package cl.dgac.registro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateRegistroVuelo (

    //ID Plan de vuelo
    @NotNull(message="Debe ingresar el ID de Plan de vuelo") 
    @Positive(message="El ID Plan de vuelo no puede ser negativo o cero")Integer idPV,

    //Altura máxima
    @Positive(message = "La altura máxima no puede ser negativa o cero.") Double altMaxFinal,

    //Tiempo estimado
    @Positive(message = "El tiempo estimado no puede ser negativo o cero.") Integer tiTotal,

    //Validador de incidente
    @NotNull(message = "Debe registrar si hubo algún incidente.") Boolean vInc,

    //Reporte de incidente
    @Size(max=255, message="El reporte de incidentes no puede superar los 255 caracteres.") String repInc
)
{
}
