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
@Table(name="t_evaluation_details	")
public class EvaluationDetailsEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	@Column(nullable=false)
	private int evaluatedApplicantIdPk;
	
	@Column(nullable=false)
	private int ctOneRating;
	
	@Column(columnDefinition="text")
	private String ctOneComments;
	
	@Column(nullable=false)
	private int ctTwoRating;
	
	@Column(columnDefinition="text")
	private String ctTwoComments;
	
	@Column(nullable=false)
	private int ctThreeRating;
	
	@Column(columnDefinition="text")
	private String ctThreeComments;
	
	@Column(nullable=false)
	private int ctFourRating;
	
	@Column(columnDefinition="text")
	private String ctFourComments;
	
	@Column(nullable=false)
	private int ctFiveRating;
	
	@Column(columnDefinition="text")
	private String ctFiveComments;
	
	@Column(nullable=false)
	private int ctSixRating;
	
	@Column(columnDefinition="text")
	private String ctSixComments;
	
	@Column(nullable=false)
	private int ctSevenRating;
	
	@Column(columnDefinition="text")
	private String ctSevenComments;
	
	@Column(nullable=false)
	private int ctEightRating;
	
	@Column(columnDefinition="text")
	private String ctEightComments;
	
	@Column(columnDefinition = "text", nullable = false)
	private String tbiFeedback;
	
	@Column(nullable = false)
	private int total;
	
	@Column(nullable = false)
	private int createdBy;
	
	@Column(nullable = false)
	private Timestamp createdDate;
	
	@Column(nullable = false)
	private Boolean deleteFlg;
	
	
}
