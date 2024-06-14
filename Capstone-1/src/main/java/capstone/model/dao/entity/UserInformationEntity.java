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
@Table(name="m_user_information")
public class UserInformationEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String mobileNumber;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private Boolean initialChangePass;
	

	private Timestamp createdDate;
	
	@Column(nullable = false)
	private Boolean deleteFlg;
	

	private Timestamp updatedDate;
}
