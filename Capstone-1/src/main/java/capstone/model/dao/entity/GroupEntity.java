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
@Table(name="m_group")
public class GroupEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	private int applicantIdPk;
	
	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String groupName;
	
	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String firstName;
	
	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String lastName;
	
	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String mobileNumber; 
	
	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String address; 
	
	@Column(columnDefinition = "varchar(255)")
	private String university;
	
	@Column(nullable = false)
	private Timestamp createdDate;
	
	@Column(nullable = false)
	private Boolean deleteFlg;
}
