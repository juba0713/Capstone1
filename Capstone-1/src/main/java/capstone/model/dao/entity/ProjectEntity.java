package capstone.model.dao.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="t_project")
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	@Column(nullable = false)
	private int applicantIdPk;
	
	@Column(columnDefinition = "text", nullable = false)
	private String projectTitle;
	
	@Column(columnDefinition = "text", nullable = false)
	private String projectDescription;
	
	private List<String> teams;
	
	@Column(columnDefinition = "text", nullable = false)
	private String problemStatement;
	
	@Column(columnDefinition = "text", nullable = false)
	private String targetMarket;
	
	@Column(columnDefinition = "text", nullable = false)
	private String solutionDescription;
	
	@Column(nullable = false)
	private List<String> historicalTimeline;
	
	@Column(nullable = false)
	private List<String> productServiceOffering;
	
	@Column(nullable = false)
	private List<String> pricingStrategy;
	
	@Column(columnDefinition = "text")
	private String intPropertyStatus;
	
	@Column(columnDefinition = "text", nullable = false)
	private String objectives;
	 
	@Column(columnDefinition = "text", nullable = false)
	private String scopeProposal;
	
	@Column(columnDefinition = "text", nullable = false)
	private String methodology;
	
	private String vitaeFile;
	
	@Column(columnDefinition = "text")
	private String supportLink;
	
	@Column(nullable = false)
	private Timestamp createdDate;
	
	@Column(nullable = false)
	private Boolean deleteFlg;
}
