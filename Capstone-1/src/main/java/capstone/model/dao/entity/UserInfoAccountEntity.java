package capstone.model.dao.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="m_user_info_account")
public class UserInfoAccountEntity {
	
	@Id
	private int userIdPk;
	
	private String password;
	
	private Timestamp createdDate;
	
	private Boolean deleteFlg;
}
