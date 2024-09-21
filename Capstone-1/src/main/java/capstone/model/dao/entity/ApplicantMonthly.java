package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class ApplicantMonthly {
	public ApplicantMonthly(Object[] objects) {
		this(
				(Integer) objects[0],
				(String) objects[1]		
		);	
	}
	
	public int applicantIdPk;
	
	public String projectTitle;
	
}
