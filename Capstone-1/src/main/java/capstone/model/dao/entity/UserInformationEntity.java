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
@Table(name="m_user_information")
public class UserInformationEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	private String username;
	
	private String email;
	
	private String mobileNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String role;
	
	private Boolean initialChangePass;
	
	private Timestamp createdDate;
	
	private Boolean deleteFlg;
}
