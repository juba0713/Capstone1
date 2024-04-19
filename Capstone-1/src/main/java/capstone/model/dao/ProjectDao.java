package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.dao.entity.ProjectEntity;

public interface ProjectDao extends JpaRepository<ProjectEntity, Integer>{

}
