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
@Table(name="t_accepted_applicant")
public class AcceptedApplicantEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	private int applicantIdPk;

	private Timestamp createdDate;
	
	private int createdBy;
	
	private Boolean deleteFlg;

}
