package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.dao.entity.EvaluationDetailsEntity;

public interface EvaluationDetailsDao extends JpaRepository<EvaluationDetailsEntity, Integer> {

}
