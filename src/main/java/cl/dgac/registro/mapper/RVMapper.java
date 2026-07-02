package cl.dgac.registro.mapper;

import cl.dgac.registro.dto.CreateRegistroVuelo;
import cl.dgac.registro.dto.UpdateRegistroVuelo;
import cl.dgac.registro.model.RegistroVuelo;

public class RVMapper {

    public static RegistroVuelo toModel(CreateRegistroVuelo request){
        return new RegistroVuelo(0, request.codVuelo(), request.rutPiloto(), request.tiTotal(), request.altFinal(), request.repInc(), null);
    }
    public static RegistroVuelo toModel(UpdateRegistroVuelo request){
        return new RegistroVuelo(0, request.codVuelo(), request.rutPiloto(), request.tiTotal(), request.altFinal(), request.repInc(), null);
    }
}
