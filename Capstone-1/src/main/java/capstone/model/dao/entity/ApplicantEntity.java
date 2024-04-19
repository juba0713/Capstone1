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
@Table(name="m_applicant")
public class ApplicantEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	@Column(columnDefinition = "varchar(255)")
	private String email;
	
	private Boolean agreeFlg;
	
	private int technologyAns;
	
	private int productDevelopmentAns;
	
	private int competitiveLandscapeAns;
	
	private int productDesignAns;
	
	private int teamAns;
	
	private int goToMarketAns;
	
	private int manufacturingAns;
	
	private Boolean eligibilityAgreeFlg;
	
	private Boolean commitmentOneFlg;
	
	private Boolean commitmentTwoFlg;
	
	private Boolean commitmentThreeFlg;
	
	private Boolean commitmentFourFlg;
	
	private Timestamp createdDate;
	
	private Boolean deleteFlg;
	
	private int status;
}
