package co.edu.unbosque.backSucursal1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.backSucursal1.models.Usuarios;

@Repository
public interface UsuariosDAO extends MongoRepository<Usuarios, String>{

}
