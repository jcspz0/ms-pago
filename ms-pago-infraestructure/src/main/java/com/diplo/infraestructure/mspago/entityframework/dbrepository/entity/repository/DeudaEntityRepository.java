package com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.repository;

import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.DeudaEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeudaEntityRepository
	extends CrudRepository<DeudaEntity, String> {
	@Query(
		value = "SELECT * FROM deuda WHERE reserva_id = :reservaid limit 1",
		nativeQuery = true
	)
	DeudaEntity findDeudaByReservaId(@Param("reservaid") String reservaid);
}
