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
import org.springframework.web.bind.annotation.RestController;

import cl.dgac.registro.dto.CreateRegistroVuelo;
import cl.dgac.registro.dto.UpdateRegistroVuelo;
import cl.dgac.registro.mapper.RVMapper;
import cl.dgac.registro.model.RegistroVuelo;
import cl.dgac.registro.service.RVService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/dgac/registroVuelo")
public class RVController {

    private final RVService rVService;

    public RVController(RVService rVService){
        this.rVService = rVService;
    }

    @GetMapping
    public ResponseEntity <List<RegistroVuelo>> mostrarRV(){
        List<RegistroVuelo> listRV = rVService.listarRV();
        return ResponseEntity.ok(listRV);
    }

    @PostMapping
    public ResponseEntity<RegistroVuelo> guardarRV(@Valid @RequestBody CreateRegistroVuelo request){
        RegistroVuelo rVuelo = rVService.guardarRV(RVMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(rVuelo);
    }

    @PutMapping
    public ResponseEntity<RegistroVuelo> actualizarRV(@Valid @RequestBody UpdateRegistroVuelo request){
        RegistroVuelo rVuelo = rVService.actualizarRV(RVMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(rVuelo);
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarRV(@PathVariable int idRV){
        rVService.eliminarRV(idRV);
        return ResponseEntity.noContent().build();
    }
}
