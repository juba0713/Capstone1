package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.dao.entity.EvaluatedApplicantEntity;

public interface EvaluatedApplicantDao extends JpaRepository<EvaluatedApplicantEntity, Integer>{

}
