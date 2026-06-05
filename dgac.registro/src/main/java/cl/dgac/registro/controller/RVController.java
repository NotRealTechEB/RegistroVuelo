package cl.dgac.registro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import cl.dgac.registro.model.RegistroVuelo;
import cl.dgac.registro.service.RVService;

@RestController
@RequestMapping("api/v1/registrovuelo")
public class RVController {

    private final RVService rVService;
    private final WebClient planVueloWebClient;

    public RVController(RVService rVService, WebClient planVueloWebClient){
        this.rVService = rVService;
        this.planVueloWebClient = planVueloWebClient;
    }

    //-------------------------------Metodos de administracion-------------------------------//

    //Obtener todos los registros de vuelo

    @GetMapping
    public ResponseEntity <List<RegistroVuelo>> mostrarRV(){
        List<RegistroVuelo> listRV = rVService.listarRV();
        return ResponseEntity.ok(listRV);
    }

    //Eliminar registros de vuelo

    @DeleteMapping("/{idRV}")
    public ResponseEntity<Void> eliminarRV(@RequestParam("idRV") int idRV){
        rVService.eliminarRV(idRV);
        return ResponseEntity.noContent().build();
    }


    //-------------------------------Metodos HU - Piloto-------------------------------//

    //Obtener registros por rut de piloto
    @GetMapping("{rutPiloto}")
    public ResponseEntity<List<RegistroVuelo>> buscarPorRut(@RequestParam("rutPiloto") String rutPiloto) {
        List<RegistroVuelo> lista = rVService.obtenerRegistrosPorRut(rutPiloto);
        return ResponseEntity.ok(lista);
    }

    //Guardar registros de vuelo

    @PostMapping("{codVuelo}")
    public ResponseEntity<RegistroVuelo> crearRegistro(@RequestParam("codVuelo") String codVuelo, @RequestBody RegistroVuelo nuevoRegistro) {
        RegistroVuelo registroGuardado = rVService.guardarRegistro(nuevoRegistro);
        return ResponseEntity.status(HttpStatus.CREATED).body(registroGuardado);
    }
}
