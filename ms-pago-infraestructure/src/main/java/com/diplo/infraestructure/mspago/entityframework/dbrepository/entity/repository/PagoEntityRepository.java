package com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.repository;

import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.DeudaEntity;
import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.PagoEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoEntityRepository
	extends CrudRepository<PagoEntity, String> {}
