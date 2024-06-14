package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import capstone.model.dao.entity.AcceptedApplicantEntity;

@Transactional
public interface AcceptedApplicantDao extends JpaRepository<AcceptedApplicantEntity, Integer> {

}
