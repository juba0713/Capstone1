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
@Table(name="t_rejected_applicant")
public class RejectedApplicantEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	private int applicantIdPk;
	
	private Boolean resubmitFlg;
	
	private Timestamp createdDate;
	
	private Boolean deleteFlg;
	
	private String token;
	
	private int createdBy;
}
