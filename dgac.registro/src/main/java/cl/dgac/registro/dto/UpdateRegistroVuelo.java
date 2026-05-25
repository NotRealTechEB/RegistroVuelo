package cl.dgac.registro.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.Size;

public record UpdateRegistroVuelo (

    //Ubicación GPS
    @Size(max = 30, message = "La ubicación GPS no puede superar los 30 caracteres") String pscGPS,

    //Fecha de plan de vuelo
    @NegativeOrZero(message = "Fecha del REGISTRO DE VUELO no puede ser negativa o igual a zero")
    @Digits(integer = 6, fraction=0, message = "La fecha debe contener 6 digitos en formato DDMMAAAA") int fechaRV,

    //Altura máxima
    @NegativeOrZero(message = "La altura máxima no puede ser negativa o cero") double altMaxFinal,

    //Tiempo estimado
    @NegativeOrZero(message = "El tiempo estimado no puede ser negativo o cero") double tiTotal,

    //Region
    @Size(max = 60, message = "La región no puede superar los 60 caracteres") String region
)
{
}
