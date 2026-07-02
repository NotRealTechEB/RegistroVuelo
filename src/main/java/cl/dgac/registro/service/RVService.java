package cl.dgac.registro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.dgac.registro.dto.PlanVueloDTO;
import cl.dgac.registro.dto.RegistroVueloDTO;
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
    public RegistroVuelo guardarRegistro(RegistroVuelo nuevoRegistro) {
    String codigo = nuevoRegistro.getCodigoVuelo();
    
    try {
        planVueloApiWebClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/planvuelo/integrar").queryParam("codVuelo", codigo).build())
            .retrieve().toBodilessEntity().block();    
    } catch (Exception ex) {
        throw new ResourceNotFoundException("No se encuentran planes de vuelo para el codigo de vuelo:" + codigo);
    }

    nuevoRegistro.setTiempoTotal(nuevoRegistro.getTiempoTotal());
    nuevoRegistro.setDetIncidente(nuevoRegistro.getDetIncidente());
    nuevoRegistro.setTokBitacora(java.util.UUID.randomUUID().toString());
    
    return rVRepository.save(nuevoRegistro);
    }

    //Visualizar registros de vuelo según el rut del piloto
    public List<RegistroVueloDTO> obtenerRegistrosPorRut(String rutPiloto) {

        List<RegistroVuelo> registrosBD = rVRepository.findByRutPiloto(rutPiloto);
        List<RegistroVueloDTO> listaResultados = new ArrayList<>();

        for (RegistroVuelo registro : registrosBD) {
            
            RegistroVueloDTO rvDTO = new RegistroVueloDTO();
            rvDTO.setAltFinal(registro.getAltFinal()); 
            rvDTO.setTiTotal(registro.getTiempoTotal());
            rvDTO.setDetInc(registro.getDetIncidente());

            try {
                String codigo = registro.getCodigoVuelo(); 
                PlanVueloDTO datosPV = planVueloApiWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/api/v1/planvuelo/integrar").queryParam("codVuelo", codigo).build()).retrieve()
                    .bodyToMono(PlanVueloDTO.class).block();                                             
                rvDTO.setPlanvuelo(datosPV);
                
            } catch (Exception ex) {
                rvDTO.setPlanvuelo(null);
            }
            listaResultados.add(rvDTO); 
        } 
        return listaResultados; 
    } 

}
