package co.edu.unbosque.Consolidacion.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.Consolidacion.models.Consolidacion;

@Repository
public interface ConsolidacionDAO  extends MongoRepository<Consolidacion, String>{

}
