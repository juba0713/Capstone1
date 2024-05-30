package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import capstone.model.dao.entity.GroupMemberEntity;

@Transactional
public interface GroupMemberDao extends JpaRepository<GroupMemberEntity, Integer>{
	
	public final String DELETE_ALL_GROUP_MEMBER_BY_GROUP_ID = "DELETE FROM t_group_member WHERE group_id_pk = :groupIdPk";
	
	@Modifying
	@Query(value=DELETE_ALL_GROUP_MEMBER_BY_GROUP_ID, nativeQuery=true)
	public void deleteAllGroupMemberByGroupId(@Param("groupIdPk") int groupIdPk) throws DataAccessException;
	
	
}
