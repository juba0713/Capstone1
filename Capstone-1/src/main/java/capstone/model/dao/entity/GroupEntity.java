package capstone.model.dao.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="m_group")
public class GroupEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	private int applicantIdPk;
	
	private String groupName;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNumber; 
	
	private String emailAddress;
	
	private String university;
	
	private Timestamp createdDate;
	
	private Boolean deleteFlg;
}
