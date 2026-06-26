package cl.dgac.registro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.dgac.registro.dto.PlanVueloDTO;
import cl.dgac.registro.exception.ResourceNotFoundException;
import cl.dgac.registro.model.RegistroVuelo;
import cl.dgac.registro.repository.RVRepository;

@Service
public class RVService {
    @Autowired
    private RVRepository rVRepository;
    private WebClient planVueloApiWebClient;

    //-------------------------------Metodos de administracion-------------------------------//

    //Método para listar todos los registros de vuelo

    public List<RegistroVuelo> listarRV(){
        return rVRepository.findAll();
    }

    //Método para eliminar registros de vuelo

    public String eliminarRV(int idRV){
        rVRepository.deleteById(idRV);
        return "El registro de vuelo ha sido eliminado";
    }

    //-------------------------------Metodos HU - Piloto-------------------------------//

    //Crear nuevos registros de vuelo
    public PlanVueloDTO consultarPlanVuelo(String codVuelo) {
        try {
            return planVueloApiWebClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/planvuelo/integrar").queryParam("codVuelo", codVuelo).build())
                .retrieve().bodyToMono(PlanVueloDTO.class).block();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No se encontraron planes de vuelo con el codigo: " + codVuelo);
        }
    }

    public RegistroVuelo guardarRegistro(RegistroVuelo nuevoRegistro) {

    String codigo = nuevoRegistro.getCodigoVuelo();
    PlanVueloDTO plan = consultarPlanVuelo(codigo);
    nuevoRegistro.setRutPiloto(nuevoRegistro.getRutPiloto());
    nuevoRegistro.setRutEmpMandante(plan.getRutEmpresaMandante());
    nuevoRegistro.setPsGPS(plan.getPsGPS());
    nuevoRegistro.setFechaPV(plan.getFechaPV());
    nuevoRegistro.setAltMax(plan.getAltMax());
    nuevoRegistro.setNumeroRegistro(plan.getNumeroRegistro());

    nuevoRegistro.setTiempoTotal(nuevoRegistro.getTiempoTotal());
    nuevoRegistro.setDetIncidente(nuevoRegistro.getDetIncidente());
    nuevoRegistro.setRegion(plan.getRegion());
    nuevoRegistro.setTokBitacora(java.util.UUID.randomUUID().toString());
    return rVRepository.save(nuevoRegistro);
}

    //Visualizar registros de vuelo según el rut del piloto
    public List<RegistroVuelo> obtenerRegistrosPorRut(String rutPiloto) {
    return rVRepository.findByRutPiloto(rutPiloto);
}
}
    

