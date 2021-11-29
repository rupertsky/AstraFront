package co.edu.unbosque.backAstraCali.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.backAstraCali.models.Proveedores;

@Repository
public interface ProveedoresDAO extends MongoRepository<Proveedores, String>{

}
