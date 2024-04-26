package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.dao.entity.RejectedApplicantEntity;

public interface RejectedApplicantDao extends JpaRepository<RejectedApplicantEntity, Integer>{

}
