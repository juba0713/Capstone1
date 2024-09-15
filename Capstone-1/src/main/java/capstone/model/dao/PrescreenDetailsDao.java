package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.dao.entity.PrescreenDetailsEntity;

public interface PrescreenDetailsDao extends JpaRepository<PrescreenDetailsEntity, Integer> {
	
}
