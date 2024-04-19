package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.dao.entity.GroupEntity;

public interface GroupDao extends JpaRepository<GroupEntity, Integer>{

}
