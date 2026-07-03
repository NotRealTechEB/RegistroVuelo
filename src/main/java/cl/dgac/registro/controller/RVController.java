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

import cl.dgac.registro.dto.RegistroVueloDTO;
import cl.dgac.registro.model.RegistroVuelo;
import cl.dgac.registro.service.RVService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

    @Operation(
        summary = "Presenta registro de vuelo",
        description= "Muestra todos los registros de vuelo realizados, no se usan filtros"
    )
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Lista de registros de vuelo",
                value = "[{\"idRV\": 1, \"codigoVuelo\": \"vuelo1234\", \"rutPiloto\": \"12345678-9\", \"tiempoTotal\": 120, \"altFinal\": 4500, \"detIncidente\": \"Sin novedades\"}]"
            )
        ) 
    )
    @GetMapping
    public ResponseEntity <List<RegistroVuelo>> mostrarRV(){
        List<RegistroVuelo> listRV = rVService.listarRV();
        return ResponseEntity.ok(listRV);
    }

    //Eliminar registros de vuelo

    @Operation(
        summary = "Eliminar registro de vuelo",
        description= "Permite eliminar registros de vuelo"
    )
    @ApiResponse(
        responseCode = "204",
        description = "No Content - Registro de vuelo eliminado con éxito"
        ) 
    @DeleteMapping("/{idRV}")
    public ResponseEntity<Void> eliminarRV(@RequestParam("idRV") int idRV){
        rVService.eliminarRV(idRV);
        return ResponseEntity.noContent().build();
    }


    //-------------------------------Metodos HU - Piloto-------------------------------//

    //Obtener registros por rut de piloto
    @Operation(
        summary = "Presenta registro de vuelo",
        description= "Muestra todos los registros de vuelo realizados, se usa el rut del piloto como filtro"
    )
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Lista de registros de vuelo",
                value = "[{\"idRV\": 1, \"codigoVuelo\": \"vuelo1234\", \"rutPiloto\": \"12345678-9\", \"tiempoTotal\": 120, \"altFinal\": 4500, \"detIncidente\": \"Sin novedades\"}]"
            )
        ) 
    )
    @GetMapping("{rutPiloto}")
    public ResponseEntity<List<RegistroVueloDTO>> buscarPorRut(@RequestParam("rutPiloto") String rutPiloto) {
        List<RegistroVueloDTO> lista = rVService.obtenerRegistrosPorRut(rutPiloto);
        return ResponseEntity.ok(lista);
    }

    //Guardar registros de vuelo

    @Operation(
        summary = "Crear registro de vuelo",
        description= "Permite crear nuevos registros de vuelo"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos necesarios para crear un nuevo registro de vuelo",
        required = true,
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Ejemplo de crear registro de vuelo",
                value = "{\"idRV\": 1, \"codigoVuelo\": \"vuelo1234\", \"rutPiloto\": \"12345678-9\", \"tiempoTotal\": 120, \"altFinal\": 4500, \"detIncidente\": \"Sin novedades\"}"
            )
        ))
    @ApiResponse(
        responseCode = "201",
        description = "CREATED",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Registro de vuelo creado",
                value = "{\"idRV\": 1, \"codigoVuelo\": \"vuelo1234\", \"rutPiloto\": \"12345678-9\", \"tiempoTotal\": 120, \"altFinal\": 4500, \"detIncidente\": \"Sin novedades\"}"
            )
        ) 
    )
    @PostMapping("{codVuelo}")
    public ResponseEntity<RegistroVuelo> crearRegistro(@RequestParam("codVuelo") String codVuelo, @RequestBody RegistroVuelo nuevoRegistro) {
        RegistroVuelo registroGuardado = rVService.guardarRegistro(nuevoRegistro);
        return ResponseEntity.status(HttpStatus.CREATED).body(registroGuardado);
    }
}
