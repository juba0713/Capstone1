package capstone.model.dao.entity;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class UserDetailsEntity {

	public UserDetailsEntity(Object[] objects) {
		 this(
				 (Integer) objects[0],
				 (String) objects[1],
				 (String) objects[2],
				 (String) objects[3],
				 (String) objects[4],
				 (Timestamp) objects[5],
				 (Timestamp) objects[6],
				 (Boolean) objects[7]
		);
	}
	
	private int idPk;
	
	private String email;
	
	private String fullName;
	
	private String mobileNumber;
	
	private String role;
	
	private Timestamp createdDate;
	
	private Timestamp updatedDate;
	
	private Boolean deletable;
}
