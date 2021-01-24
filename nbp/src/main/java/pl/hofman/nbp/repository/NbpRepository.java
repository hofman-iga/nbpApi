package pl.hofman.nbp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.hofman.nbp.model.Nbp;

import java.util.Optional;

public interface NbpRepository extends CrudRepository<Nbp,Integer> {

    Optional<Nbp> findByCode(String code);
    // Optional<Nbp> findByRate(Double rate);

}

