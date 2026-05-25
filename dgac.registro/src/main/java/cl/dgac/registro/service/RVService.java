package cl.dgac.registro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.dgac.registro.model.RegistroVuelo;
import cl.dgac.registro.repository.RVRepository;

@Service
public class RVService {

    private RVRepository rVRepository;

    public List<RegistroVuelo> listarRV(){
        return rVRepository.findAll();
    }

    public RegistroVuelo guardarRV(RegistroVuelo rV){
        return rVRepository.save(rV);
    }

    public RegistroVuelo actualizarRV(RegistroVuelo rV){
        return rVRepository.save(rV);
    }

    public String eliminarRV(int idRV){
        rVRepository.deleteById(idRV);
        return "El registro de vuelo ha sido eliminado";
    }
    
}
