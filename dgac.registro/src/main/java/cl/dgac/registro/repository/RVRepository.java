package cl.dgac.registro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.dgac.registro.model.RegistroVuelo;

@Repository
public interface RVRepository extends JpaRepository<RegistroVuelo, Integer>{
    RegistroVuelo findByCodigoVuelo(String codVuelo);
    List<RegistroVuelo> findByRutPiloto(String rutPiloto);

}
