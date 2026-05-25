package cl.dgac.registro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.dgac.registro.model.RegistroVuelo;
import cl.dgac.registro.repository.RVRepository;

@Service
public class RVService {

    private RVRepository rVRepository;

    //Método para listar todos los registros de vuelo

    public List<RegistroVuelo> listarRV(){
        return rVRepository.findAll();
    }

    //Método para guardar nuevos registros de vuelo

    public RegistroVuelo guardarRV(RegistroVuelo rV){
        return rVRepository.save(rV);
    }

    //Método para actualizar registros de vuelo existentes

    public RegistroVuelo actualizarRV(RegistroVuelo rV){
        return rVRepository.save(rV);
    }

    //Método para eliminar registros de vuelo

    public String eliminarRV(int idRV){
        rVRepository.deleteById(idRV);
        return "El registro de vuelo ha sido eliminado";
    }
    
}
