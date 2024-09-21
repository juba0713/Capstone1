package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class UserCertificateEntity {
	public UserCertificateEntity(Object[] objects) {
		this(
				(Integer) objects[0],
				(String) objects[1],
				(String) objects[2],
				(String) objects[3],
				(Integer) objects[4]
				);
	}
	
	private int userIdPk;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private int totalRating;
}
