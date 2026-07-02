package cl.dgac.registro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateRegistroVuelo (

    //Codigo de vuelo
    @NotNull(message="Debe ingresar el codigo de vuelo") 
    @Size(max=12, message="El codigo de vuelo no puede ser mayor a 12 caracteres") String codVuelo,

    //Rut Piloto
    @NotNull(message="Debe ingresar el rut del piloto") 
    @Size(max=12, message="El codigo de vuelo no puede ser mayor a 12 caracteres") String rutPiloto,

    //Tiempo total
    @NotNull(message="Debe ingresar el tiempo total del vuelo") 
    @Positive(message = "El tiempo estimado no puede ser negativo o cero.") Integer tiTotal,

    //Altura final
    @NotNull(message="Debe ingresar la altura alcanzada del vuelo") 
    @Positive(message = "El tiempo estimado no puede ser negativo o cero.") Integer altFinal,

    //Reporte de incidente
    @Size(max=255, message="El reporte de incidentes no puede superar los 255 caracteres.") String repInc,

    //Creacion automatica token
    String tokBitacora


)
{
}
