package cl.dgac.registro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.dgac.registro.dto.PlanVueloResponseDTO;
import cl.dgac.registro.dto.RegistroResponseDTO;
import cl.dgac.registro.dto.UpdateRegistroVuelo;
import cl.dgac.registro.model.RegistroVuelo;
import cl.dgac.registro.repository.RVRepository;

@Service
public class RVService {
    @Autowired
    private RVRepository rVRepository;
    private WebClient planVueloApiWebClient;

    //Método para listar todos los registros de vuelo

    public List<RegistroVuelo> listarRV(){
        return rVRepository.findAll();
    }

    //Método para mostrar Registro por ID (DTO)

    public RegistroResponseDTO obtenerRegistroById(int idRV) {
        RegistroVuelo registro = rVRepository.findById(idRV).orElseThrow(() -> new RuntimeException("Registro de vuelo no encontrado"));

        RegistroResponseDTO dto = new RegistroResponseDTO();
        dto.setIdRV(registro.getIdRV());
        dto.setFeRV(registro.getFechaRV());
        dto.setVInc(registro.isValIncidente());
        dto.setDetInc(registro.getDetIncidente());

        return dto;
    }

    //Método para guardar nuevos registros de vuelo

    public RegistroVuelo guardarRV(RegistroVuelo rV){
        return rVRepository.save(rV);
    }

    //Método para actualizar registros sin afectar otros campos

    public RegistroVuelo actualizarRVParcial(UpdateRegistroVuelo request) {
    RegistroVuelo rVExistente = rVRepository.findById(request.idRV()).orElseThrow(() 
        -> new RuntimeException("ID "+ request.idRV()+ " no corresponde a REGISTRO DE VUELO"));
    
        if (request.altMaxFinal() != null) {
            rVExistente.setAltMaxFinal(request.altMaxFinal());
        }
        
        if (request.tiTotal() != null) {
            rVExistente.setTiTotal(request.tiTotal());
        }
        
        if (request.repInc() != null) {
            rVExistente.setDetIncidente(request.repInc());
        }

        return rVRepository.save(rVExistente);
    }

    //Método para eliminar registros de vuelo

    public String eliminarRV(int idRV){
        rVRepository.deleteById(idRV);
        return "El registro de vuelo ha sido eliminado";
    }

    //Comunicación a API Plan de vuelo
    @Qualifier("planVueloApiWebClient")
    public PlanVueloResponseDTO consultarPlanVuelo(int idPlanVuelo) {
        try {
            return planVueloApiWebClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/dgac/PlanVuelo/buscar").queryParam("idPlanVuelo", idPlanVuelo).build())
                .retrieve().bodyToMono(PlanVueloResponseDTO.class).block();
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo validar el Plan de Vuelo ID " + idPlanVuelo + ".");
        }
    }
    
}
