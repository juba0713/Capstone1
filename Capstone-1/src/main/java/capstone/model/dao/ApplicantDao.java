package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.dao.entity.ApplicantEntity;

public interface ApplicantDao extends JpaRepository<ApplicantEntity, Integer>{

}
