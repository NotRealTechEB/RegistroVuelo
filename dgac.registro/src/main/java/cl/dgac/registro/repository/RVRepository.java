package cl.dgac.registro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.dgac.registro.model.RegistroVuelo;

@Repository
public interface RVRepository extends JpaRepository<RegistroVuelo, Integer>{

}
