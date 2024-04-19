package capstone.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.dao.entity.GroupMemberEntity;

public interface GroupMemberDao extends JpaRepository<GroupMemberEntity, Integer>{

}
