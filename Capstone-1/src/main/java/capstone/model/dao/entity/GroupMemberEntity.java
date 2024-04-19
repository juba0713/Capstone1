package capstone.model.dao.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="t_group_member")
public class GroupMemberEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	private int groupIdPk;
	
	@Column(columnDefinition = "varchar(255)")
	private String firstName;
	
	@Column(columnDefinition = "varchar(255)")
	private String lastName;
	
	private Timestamp createdDate;
	
	private Boolean deleteFlg;
}
