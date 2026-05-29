package cl.dgac.registro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import cl.dgac.registro.dto.CreateRegistroVuelo;
import cl.dgac.registro.dto.PlanVueloResponseDTO;
import cl.dgac.registro.dto.RegistroResponseDTO;
import cl.dgac.registro.dto.UpdateRegistroVuelo;
import cl.dgac.registro.mapper.RVMapper;
import cl.dgac.registro.model.RegistroVuelo;
import cl.dgac.registro.service.RVService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/dgac/registroVuelo")
public class RVController {

    private final RVService rVService;
    private final WebClient planVueloWebClient;

    public RVController(RVService rVService, WebClient planVueloWebClient){
        this.rVService = rVService;
        this.planVueloWebClient = planVueloWebClient;
    }

    //Obtener todos los registros de vuelo

    @GetMapping
    public ResponseEntity <List<RegistroVuelo>> mostrarRV(){
        List<RegistroVuelo> listRV = rVService.listarRV();
        return ResponseEntity.ok(listRV);
    }

    //Obtener registro de vuelo por ID

    @GetMapping("{id}")
    public ResponseEntity<RegistroResponseDTO> registroByID(int idRV){
        RegistroResponseDTO dto = rVService.obtenerRegistroById(idRV);
        return ResponseEntity.ok(dto);
    }


    //Obtener datos de API Plan de vuelo 

    @GetMapping("PlanVuelo/buscar")
    public ResponseEntity<PlanVueloResponseDTO> datosPlanVuelo(@RequestParam("idPlanVuelo") int idPlanVuelo){
        PlanVueloResponseDTO datosPV = rVService.consultarPlanVuelo(idPlanVuelo);

        return ResponseEntity.ok(datosPV);
    }

    //Ingresar un nuevo registro de vuelo

    @PostMapping
    public ResponseEntity<RegistroVuelo> guardarRV(@Valid @RequestBody CreateRegistroVuelo request){
        RegistroVuelo rVuelo = rVService.guardarRV(RVMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(rVuelo);
    }

    //Actualizar registros de vuelo de forma parcial

    @PutMapping
    public ResponseEntity<RegistroVuelo> actualizarRV(@Valid @RequestBody UpdateRegistroVuelo request){
        RegistroVuelo rVuelo = rVService.actualizarRVParcial(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(rVuelo);
    }

    //Eliminar registros de vuelo

    @DeleteMapping
    public ResponseEntity<String> eliminarRV(@PathVariable int idRV){
        rVService.eliminarRV(idRV);
        return ResponseEntity.noContent().build();
    }
}
