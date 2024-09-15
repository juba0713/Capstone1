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
@Table(name="t_prescreen_details")
public class PrescreenDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int idPk;
	
	@Column(nullable = false)
	private int acceptedApplicantIdPk;
	
	@Column(nullable=false)
	private int rejectedApplicantIdPk;
	
	@Column(nullable = false)
	private Boolean ctOneFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctOneComments;
	
	@Column(nullable = false)
	private Boolean ctTwoFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctTwoComments;
	
	@Column(nullable = false)
	private Boolean ctThreeFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctThreeComments;
	
	@Column(nullable = false)
	private Boolean ctFourFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctFourComments;
	
	@Column(nullable = false)
	private Boolean ctFiveFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctFiveComments;
	
	@Column(nullable = false)
	private Boolean ctSixFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctSixComments;
	
	@Column(nullable = false)
	private Boolean ctSevenFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctSevenComments;
	
	@Column(nullable = false)
	private Boolean ctEightFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctEightComments;
	
	@Column(nullable = false)
	private Boolean ctNineFlg;
	
	@Column(columnDefinition = "text", nullable = false)
	private String ctNineComments;
	
	@Column(columnDefinition = "text", nullable = false)
	private String recommendation;
	
	@Column(nullable = false)
	private int createdBy;
	
	@Column(nullable = false)
	private Timestamp createdDate;
	
	@Column(nullable = false)
	private Boolean deleteFlg;
}
