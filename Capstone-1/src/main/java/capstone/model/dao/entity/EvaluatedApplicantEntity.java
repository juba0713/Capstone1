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
@Table(name="t_evaluated_applicant")
public class EvaluatedApplicantEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int idPk;
	
	public int applicantIdPk;
	
	public int createdBy;
	
	public Timestamp createdDate;
	
	public Boolean deleteFlg;
	
	private String token;
	
	private Boolean resubmitFlg;

}
