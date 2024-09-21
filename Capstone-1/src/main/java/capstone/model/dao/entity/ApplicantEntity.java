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
	
	@Column(nullable = false)
	private Boolean agreeFlg;
	
	@Column(nullable = false)
	private int technologyAns;
	
	@Column(nullable = false)
	private int productDevelopmentAns;
	
	@Column(nullable = false)
	private int competitiveLandscapeAns;
	
	@Column(nullable = false)
	private int productDesignAns;
	
	@Column(nullable = false)
	private int teamAns;
	
	@Column(nullable = false)
	private int goToMarketAns;
	
	@Column(nullable = false)
	private int manufacturingAns;
	
	@Column(nullable = false)
	private Boolean eligibilityAgreeFlg;
	
	@Column(nullable = false)
	private Boolean commitmentOneFlg;
	
	@Column(nullable = false)
	private Boolean commitmentTwoFlg;
	
	@Column(nullable = false)
	private Boolean commitmentThreeFlg;
	
	@Column(nullable = false)
	private Boolean commitmentFourFlg;
	
	@Column(nullable = false)
	private Timestamp createdDate;
	
	@Column(nullable = false)
	private Boolean deleteFlg;
	
	/*
	 * 0 - Pending
	 *  00 - Resubmission - Officer
	 * 1 - Accept By Officer
	 * 2 - Reject By Officer
	 * 3 - createdApplicantAccount
	 * 4 - Pending for evaluation
	 * 04 - Pending for evaluation -resubmitted - Tbi
	 * 5 - Evaluated By Tbi
	 * 05 - Issued Certificate
	 * 6 - Qualified for Resubmission - Failed
	 * 7 - Not Qualified for Resubmission - Failed
	 * 8 - Evaluated By Manager
	 */
	@Column(nullable = false)
	private int status;
	
	@Column(nullable = false)
	private int createdBy;
	
	private String certificateName;
}
